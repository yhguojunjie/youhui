package com.yoxi.hudongtui.controllers.pay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.rest.Post;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.order.IOrderService;
import com.yoxi.hudongtui.service.pay.IRepreCoinService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.order.OrderDownVO;
import com.yoxi.hudongtui.vo.pay.daipi.BuyResp;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 
 * 下单处理
 *
 * @author wql
 *
 * @Date 2015年4月16日
 *
 */
public class OrderController {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IRepreCoinService repreCoinService;
	
	/**
	 * 下单
	 * @param inv
	 * @param orderDownVO
	 * @return
	 * @throws Exception
	 */
	@LoginRequired
	@Post("down")
	public String down(Invocation inv, OrderDownVO orderDownVO)throws Exception{
		User sessionUser = SessionUtil.getUser(inv.getRequest());
		User user = userService.findByUserId(sessionUser.getUserId());
		PluginDetailVO plugin = pluginService.getAgentPlugin(orderDownVO.getProductId(), user.getAgentId());
		Double salePrice = plugin.getSalePrice();
		Double afterPrice = ConvertUtil.calPluginPrice(orderDownVO.getBuyMon(), salePrice);
		if(afterPrice != 0.0 && orderDownVO.getCharge() != null && orderDownVO.getCharge() != 0.0 ){
			if(afterPrice.intValue() != orderDownVO.getCharge().intValue()) {
				log.error("*********支付金额与购买数量不符合*************");
				WebApplicationUtils.redirectMsg("支付金额与购买数量不一致", "", WebApplicationUtils.getBasePath() + "/pc/plugin/detail/" + orderDownVO.getProductId(), true);
				return null;
			}
		}

		// 封装订单数据
		orderDownVO.setUserId(user.getUserId());
		orderDownVO.setSourceType(Globals.SOURCETYPE_PC);
		orderDownVO.setAgentId(user.getAgentId());
		//用户选择代币购买
		if (!StringUtils.isNullBlank(orderDownVO.getIsRepreCoin())
				&& orderDownVO.getIsRepreCoin().equals("on")){
				//代币大于或等于购买金额	
				if ((user.getRepreCoin() > orderDownVO.getCharge() || user.getRepreCoin().equals(orderDownVO.getCharge()))){
					orderDownVO.setRepreCoin(orderDownVO.getCharge());
					orderDownVO.setPurchaseType(Globals.PURCHASETYPE_REPRECOIN);
					BuyResp buyResp = repreCoinService.buy(orderDownVO, plugin);
					inv.getRequest().setAttribute("buyResp", buyResp);
					return "r:" + WebApplicationUtils.getBasePath() + "/pc/my/actList";
					
				}else{//代币小于购买金额
//					orderDownVO.setRepreCoin(user.getRepreCoin());
//					orderDownVO.setCharge(orderDownVO.getCharge() - user.getRepreCoin());
//					orderDownVO.setPurchaseType(Globals.PURCHASETYPE_ALIPAYDERIC);
//					OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
//					if(retOrderDown.getOrderId() != null){
					inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/my/orderList");
//					}else{
					WebApplicationUtils.redirectMsg("当前代币数不够！", null, WebApplicationUtils.getBasePath() + "/pc/my/actList", true);
//					}
				}
		}else{//第三方支付
			orderDownVO.setCharge(orderDownVO.getCharge());
			orderDownVO.setPurchaseType(Globals.PURCHASETYPE_ALIPAYDERIC);
			OrderDownVO retOrderDown = orderService.down(orderDownVO,plugin);
			if(retOrderDown.getOrderId() != null){
				inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/my/orderList");
			}else{
				WebApplicationUtils.redirectMsg("提交定单失败！", null, WebApplicationUtils.getBasePath() + "/pc/my/actList", true);
			}
		}
		return null;
	}
}
