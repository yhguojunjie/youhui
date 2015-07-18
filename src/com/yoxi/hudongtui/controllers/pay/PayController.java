package com.yoxi.hudongtui.controllers.pay;

import java.net.URLDecoder;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.order.IOrderService;
import com.yoxi.hudongtui.service.pay.IPayBackService;
import com.yoxi.hudongtui.service.pay.IRepreCoinService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.EncryptUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.pay.alipay.AlipayConfigVO;
import com.yoxi.hudongtui.vo.pay.daipi.BuyResp;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 
 * 支付入口
 * 
 * @author wql
 * 
 */
public class PayController {

	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IUserService userService;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IRepreCoinService repreCoinService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IPayBackService payBackService;
	@Autowired
	private IAgentInfoService agentInfoService;
	@Autowired
	private IAgentBusService agentBusService;
	/**
	 * 平台支付
	 * 
	 * @param inv
	 * @param orderDownVO
	 * @return
	 * @throws Exception
	 */
	@Post("pf")
	public String pfpay(Invocation inv, OrderDownVO orderDownVO) throws Exception {

		// log.error("***********pluginId************="+orderDownVO.getProductId());
//		 log.error("***********isRepreCoin************="+orderDownVO.getIsRepreCoin());
//		log.error("***********getUserPluginId************="+orderDownVO.getUserPluginId());
		User sessionUser = SessionUtil.getUser(inv.getRequest());
		User user = userService.findByUserId(sessionUser.getUserId());
		PluginDetailVO plugin = pluginService.getAgentPlugin(orderDownVO.getProductId(), user.getAgentId());
		Double salePrice = plugin.getSalePrice();
		Double afterPrice = ConvertUtil.calPluginPrice(orderDownVO.getBuyMon(), salePrice);
//		log.error("***********salePrice************=" + salePrice);
//		log.error("***********orderDownVO.getBuyMon()************=" + orderDownVO.getBuyMon());
//		log.error("***********afterPrice************=" + afterPrice);
//		log.error("***********orderDownVO.getCharge()************=" + orderDownVO.getCharge().intValue());
		if (afterPrice.intValue() != orderDownVO.getCharge().intValue()) {
			log.error("*********支付金额与购买数量不符合*************");
			WebApplicationUtils.redirectMsg("支付金额与购买数量不一致", "", WebApplicationUtils.getBasePath() + "/pc/plugin/detail/" + orderDownVO.getProductId(), true);
			return null;
		}
		// 封装订单数据
		orderDownVO.setUserId(user.getUserId());
		orderDownVO.setSourceType(Globals.SOURCETYPE_PC);
		orderDownVO.setAgentId(user.getAgentId());
		// 且用户选择代币购买
		if (!StringUtils.isNullBlank(orderDownVO.getIsRepreCoin())) {
			if (orderDownVO.getIsRepreCoin().equals("on")) {
				// 账户代币大于购买价格,直接代币购买，跳转到我的淘插件
				if ((user.getRepreCoin() > orderDownVO.getCharge() || user.getRepreCoin().equals(orderDownVO.getCharge()))) {
					orderDownVO.setRepreCoin(orderDownVO.getCharge());
					orderDownVO.setPurchaseType(Globals.PURCHASETYPE_REPRECOIN);
					BuyResp buyResp = repreCoinService.buy(orderDownVO, plugin);
					inv.getRequest().setAttribute("buyResp", buyResp);
					return "r:" + WebApplicationUtils.getBasePath() + "/pc/my/actList";

				} else {// 账户代币小于购买价格 ，进入支付宝购买模式
					orderDownVO.setRepreCoin(user.getRepreCoin());
					orderDownVO.setCharge(orderDownVO.getCharge() - user.getRepreCoin());
					orderDownVO.setPurchaseType(Globals.PURCHASETYPE_ALIPAYDERIC);
					OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
					if (retOrderDown != null && retOrderDown.getOrderId() != null) {
						inv.getRequest().setAttribute("WIDseller_email", ReadProperties.getPara("alipay_seller_account"));
						inv.getRequest().setAttribute("notify_url",WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_notify_url"));
						inv.getRequest().setAttribute("return_url",WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_return_url"));
						// log.info("************return_url**************"+ReadProperties.getPara("alipay_directpay_return_url"));
						// log.info("************notify_url**************"+ReadProperties.getPara("alipay_directpay_notify_url"));
						inv.getRequest().setAttribute("WIDout_trade_no", "hdt_" + retOrderDown.getOrderId());
						inv.getRequest().setAttribute("WIDsubject", plugin.getName());
						 inv.getRequest().setAttribute("WIDtotal_fee",orderDownVO.getCharge()-user.getRepreCoin());
//						inv.getRequest().setAttribute("WIDtotal_fee", "0.01");
						return "alipay/directpay/alipayapi";
					}

				}
			}
		}
		// 用户未选择代币支付，进入支付宝购买模式
		if (orderDownVO.getIsRepreCoin() == null) {
			orderDownVO.setCharge(orderDownVO.getCharge());
			orderDownVO.setPurchaseType(Globals.PURCHASETYPE_ALIPAYDERIC);
			OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
			if (retOrderDown != null && retOrderDown.getOrderId() != null) {
				inv.getRequest().setAttribute("WIDseller_email", ReadProperties.getPara("alipay_seller_account"));
				inv.getRequest().setAttribute("notify_url",WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_notify_url"));
				inv.getRequest().setAttribute("return_url",WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_return_url"));
				inv.getRequest().setAttribute("WIDout_trade_no", "hdt_" + retOrderDown.getOrderId());
				inv.getRequest().setAttribute("WIDsubject", plugin.getName());
				 inv.getRequest().setAttribute("WIDtotal_fee",orderDownVO.getCharge());
//				inv.getRequest().setAttribute("WIDtotal_fee", "0.01");
				return "alipay/directpay/alipayapi";
			}

		}
		return null;
	}

	/**
	 * 支付宝WAP支付
	 * 
	 * @param inv
	 * @param orderDownVO
	 * @return
	 * @throws Exception
	 */
	@Post("alipayWap")
	public String alipayWap(Invocation inv, OrderDownVO orderDownVO, @Param("state") String state) throws Exception {
		// 签权
		String key = ReadProperties.getPara("authKey");
		StringBuffer authBuffer = new StringBuffer();
		authBuffer.append(orderDownVO.getUserId());
		authBuffer.append(orderDownVO.getProductId());
		authBuffer.append(key);

		String authorcator = EncryptUtils.encodeByMd5(authBuffer.toString());
		String acceptAuth = URLDecoder.decode(orderDownVO.getAuthStr(), "UTF-8").replace(" ", "+");
		if (StringUtils.isBlank(orderDownVO.getAuthStr()) || !StringUtils.equalsIgnoreCase(acceptAuth, authorcator)) {
			BuyResp buyResp = new BuyResp();
			buyResp.setCode(Globals.ORDERDOWNSTATUS_AUTHFAIL);
			return "@json:" + JsonUtils.toJson(buyResp);
		}

		User user = userService.findByUserId(orderDownVO.getUserId());
		PluginDetailVO plugin = pluginService.getAgentPlugin(orderDownVO.getProductId(), user.getAgentId());
		Double salePrice = plugin.getSalePrice();
		Double afterPrice = ConvertUtil.calPluginPrice(orderDownVO.getBuyMon(), salePrice);
		if (afterPrice.intValue() != orderDownVO.getCharge().intValue()) {
			log.error("*********支付金额与购买数量不符合*************");
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/plugin/detail/" + orderDownVO.getProductId());
			return null;
		}

		// 封装订单数据
		orderDownVO.setUserId(user.getUserId());
		orderDownVO.setPurchaseType(Globals.PURCHASETYPE_ALIPAYWAP);
		orderDownVO.setAgentId(user.getAgentId());
		if (state.equals("4")) {// 纯支付宝支付
			orderDownVO.setCharge(orderDownVO.getCharge());
		} else if (state.equals("5")) {
			orderDownVO.setRepreCoin(user.getRepreCoin());
			orderDownVO.setCharge(orderDownVO.getCharge() - user.getRepreCoin());
		}
		OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
		if (retOrderDown != null && retOrderDown.getOrderId() != null) {
			return "@json:" + JsonUtils.toJson(retOrderDown);
		} else {
			BuyResp buyResp = new BuyResp();
			buyResp.setCode(Globals.ORDERDOWNSTATUS_SYSTEMERROR);
			return "@json:" + JsonUtils.toJson(buyResp);
		}
	}

	/**
	 * 跳转到支付宝WAP支付API
	 * 
	 * @param inv
	 * @param OrderId
	 *            定单id
	 * @param sourceType
	 *            来源 2 微信，3移动手机
	 * @return
	 * @throws Exception
	 */
	@Get("toAlipayWapApi")
	public String toAlipayWapApi(Invocation inv, @Param("orderId") Integer orderId, @Param("sourceType") String sourceType) throws Exception {
		AgentOrder order = orderService.findByOrderId(orderId);
		if (order != null) {
			AlipayConfigVO alipayConfig = agentBusService.getAgentAliConfg(order.getAgentId());
			inv.getRequest().setAttribute("alipayConfig",alipayConfig);
			inv.getRequest().setAttribute("notify_url", WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_notify_url"));
			inv.getRequest().setAttribute("return_url", WebApplicationUtils.getBasePath() + ReadProperties.getPara("alipay_directpay_return_url"));
			inv.getRequest().setAttribute("WIDout_trade_no", "hdt_" + order.getId());
			PluginDetailVO plugin = pluginService.getAgentPlugin(order.getProductId(), order.getAgentId());
			inv.getRequest().setAttribute("WIDsubject", plugin.getName());
			inv.getRequest().setAttribute("WIDtotal_fee",order.getCharge());
//			inv.getRequest().setAttribute("WIDtotal_fee", "0.01");
			return "alipay/directpay/alipayapi";
		}

		return null;
	}

	/**
	 * 代币支付
	 * 
	 * @param inv
	 * @param orderDownVO
	 * @return
	 * @throws Exception
	 */
	@Post("repreCoinPay")
	public String repreCoinPay(Invocation inv, OrderDownVO orderDownVO) throws Exception {

		// 签权
		String key = ReadProperties.getPara("authKey");
		StringBuffer authBuffer = new StringBuffer();
		authBuffer.append(orderDownVO.getUserId());
		authBuffer.append(orderDownVO.getProductId());
		authBuffer.append(key);

		String authorcator = EncryptUtils.encodeByMd5(authBuffer.toString());
		String acceptAuth = URLDecoder.decode(orderDownVO.getAuthStr(), "UTF-8").replace(" ", "+");
		if (StringUtils.isBlank(orderDownVO.getAuthStr()) || !StringUtils.equalsIgnoreCase(acceptAuth, authorcator)) {
			BuyResp buyResp = new BuyResp();
			buyResp.setCode(Globals.ORDERDOWNSTATUS_AUTHFAIL);
			return "@json:" + JsonUtils.toJson(buyResp);
		}

		User user = userService.findByUserId(orderDownVO.getUserId());
		PluginDetailVO plugin = pluginService.getAgentPlugin(orderDownVO.getProductId(), user.getAgentId());
		// 封装订单数据
		orderDownVO.setUserId(user.getUserId());
		orderDownVO.setSourceType(orderDownVO.getSourceType());
		orderDownVO.setRepreCoin(orderDownVO.getRepreCoin());
		orderDownVO.setPurchaseType(Globals.PURCHASETYPE_REPRECOIN);
		BuyResp buyResp = repreCoinService.buy(orderDownVO, plugin);
		return "@json:" + JsonUtils.toJson(buyResp);

	}

	/**
	 * 微信支付下单
	 * 
	 * @param inv
	 * @param orderDownVO
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Post("weixinPay")
	public String weixinPay(Invocation inv, OrderDownVO orderDownVO, @Param("state") String state) throws Exception {
		// 签权
		String key = ReadProperties.getPara("authKey");
		StringBuffer authBuffer = new StringBuffer();
		authBuffer.append(orderDownVO.getUserId());
		authBuffer.append(orderDownVO.getProductId());
		authBuffer.append(key);

		String authorcator = EncryptUtils.encodeByMd5(authBuffer.toString());
		String acceptAuth = URLDecoder.decode(orderDownVO.getAuthStr(), "UTF-8").replace(" ", "+");
		if (StringUtils.isBlank(orderDownVO.getAuthStr()) || !StringUtils.equalsIgnoreCase(acceptAuth, authorcator)) {
			BuyResp buyResp = new BuyResp();
			buyResp.setCode(Globals.ORDERDOWNSTATUS_AUTHFAIL);
			return "@json:" + JsonUtils.toJson(buyResp);
		}

		User user = userService.findByUserId(orderDownVO.getUserId());
		PluginDetailVO plugin = pluginService.getAgentPlugin(orderDownVO.getProductId(), user.getAgentId());

		// 封装订单数据
		orderDownVO.setUserId(user.getUserId());
		orderDownVO.setPurchaseType(Globals.PURCHASETYPE_WENXINPAY);
		orderDownVO.setAgentId(user.getAgentId());
		if (state.equals("2")) {// 纯微信支付
			orderDownVO.setCharge(orderDownVO.getCharge());
		} else if (state.equals("3")) {// 代币+微信
			orderDownVO.setRepreCoin(user.getRepreCoin());
			orderDownVO.setCharge(orderDownVO.getCharge() - user.getRepreCoin());
		}
		OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
		if (retOrderDown != null && retOrderDown.getOrderId() != null) {
			retOrderDown.setNotifyUrl(WebApplicationUtils.getBasePath() + "/pay/wxPay/back");
			return "@json:" + JsonUtils.toJson(retOrderDown);
		} else {
			BuyResp buyResp = new BuyResp();
			buyResp.setCode(Globals.ORDERDOWNSTATUS_SYSTEMERROR);
			return "@json:" + JsonUtils.toJson(buyResp);
		}
	}
	
	/**
	 * 本系统回调
	 * @return 0处理成功 1 签权失败   2该定单已被处理过 3 交易状态值不明确 4 该定单不存在
	 * @throws Exception
	 */
	@Post("back")
	public String back(Invocation inv,OrderDownVO orderDownVO)throws Exception{
//		log.error("**********orderId=*********"+orderDownVO.getOrderId());
//		log.error("**********getAuthStr=*********"+orderDownVO.getAuthStr());
		// 签权处理
		String key = ReadProperties.getPara("paybackAuthKey");
		AgentOrder agentOrder = orderService.findByOrderId(orderDownVO.getOrderId());
		StringBuffer authBuffer = new StringBuffer();
		authBuffer.append(agentOrder.getUserId());
		authBuffer.append(agentOrder.getProductId());
		authBuffer.append(agentOrder.getAgentId());
		authBuffer.append(key);
		String authorcator = EncryptUtils.encodeByMd5(authBuffer.toString());
		String acceptAuth = URLDecoder.decode(orderDownVO.getAuthStr(), "UTF-8").replace(" ", "+");
		if (StringUtils.isBlank(orderDownVO.getAuthStr()) || !StringUtils.equalsIgnoreCase(acceptAuth, authorcator)) {
			return "@json:" + "{\"status\":\"1\"}";//签权失败
		}
		orderDownVO.setCharge(0.0);
		String  status = payBackService.back(orderDownVO,agentOrder);
		return "@json:" + "{\"status\":\""+status+"\"}";
	}
	
}
