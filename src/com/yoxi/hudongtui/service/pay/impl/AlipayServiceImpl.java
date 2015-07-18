package com.yoxi.hudongtui.service.pay.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.dao.agent.AgentOrderDAO;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.dao.user.UserTradeLogDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.pay.IAliPayService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.utils.common.BeanConverter;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.pay.alipay.directpay.util.AlipayNotify;
import com.yoxi.hudongtui.vo.pay.alipay.AlipayConfigVO;
import com.yoxi.hudongtui.vo.pay.alipay.DirectPaySyncBack;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 支付宝支付
 * 
 * @author wql
 * 
 * 2014-11-14
 *
 */
@Service
public class AlipayServiceImpl implements IAliPayService {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private AgentOrderDAO orderDAO;
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private UserTradeLogDAO userTradeLogDAO;
	@Autowired
	private IPluginBusService pluginBusService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private AgentInfoDAO agentInfoDAO;
	@Autowired
	private IAgentBusService agentBusService;
	
	/**
	 * 即时到账service
	 */
	@Override
	public String directPayBack(DirectPaySyncBack directPaySyncBack,
			HttpServletRequest request) throws Exception {
		
		//判断是否接收过通知
		AgentOrder orderTemp = orderDAO.findBypayTradeNo(directPaySyncBack.getTrade_no(), Globals.PURCHASETYPE_ALIPAYDERIC);
		
		if(orderTemp != null){
			return "success";
		}
		
		Map<String,String> checkParams = new HashMap<String,String>();
		checkParams = BeanConverter.toMap(directPaySyncBack);
	
		String ret = "fail";
		
		//签名验证通过
		String traNo = directPaySyncBack.getOut_trade_no();
		Integer orderId = Integer.parseInt(traNo.substring(4, traNo.length()));
		AgentOrder order = orderDAO.findByOrderId(orderId);
		AlipayConfigVO alipayConfig = agentBusService.getAgentAliConfg(order.getAgentId());
		if(AlipayNotify.verify(checkParams,alipayConfig) == true){
			order.setPayNoticeTime(new Date());
			if(directPaySyncBack.getTrade_status().equals(Globals.ALIPAY_TRADE_SUCCESS)
					|| directPaySyncBack.getTrade_status().equals(Globals.ALIPAY_TRADE_FINISHED)){
				order.setPayOrderStatus(Globals.ORDERSTATUS_DONE);
			}
			order.setPayTradeNo(directPaySyncBack.getTrade_no());
			order.setPurchaseType(Globals.PURCHASETYPE_ALIPAYDERIC);
			//计算使用开始与结束时间
			PluginDetailVO plugin = pluginService.getAgentPlugin(order.getProductId(), order.getAgentId());
			int vaild =  plugin.getValid();
			//查找用户当前是否购买，是否是续费
			Date startDate = new Date();
			UserPlugin uplg = null;
			if(order.getUserPluginId() != null){
				uplg = userPluginDAO.getUserPluginById(order.getUserPluginId());
				if(uplg.getOverdueTime() != null){
					if(uplg.getUseType().equals(Globals.PLUGINUSER_USETYPE_BUY)
						&& uplg.getOverdueTime().after(new Date())){
							startDate = uplg.getOverdueTime();
						}
				}
				
			}
			order.setStartDate(startDate);
			Date endDate = DateUtils.addMonths(new Date(), vaild * order.getBuyNum());
			order.setEndDate(endDate);
			order.setTradeState(Globals.ORDERTRADESTATE_DONE);
		 	int upFlag = orderDAO.update(order);
			
		 	ret = "success";
			if(upFlag > 0){
				//插件业务处理
				if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINBUY)){//新购买
					pluginBusService.buy(plugin,order.getUserId(),order.getBuyNum());
				}
				if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINRENEWAL)){//续费购买
					pluginBusService.renewal(plugin,uplg,order.getUserId(),order.getBuyNum());
				}
				
				//计算代理商账号金额相关
				AgentInfo agentInfo =  agentInfoDAO.getById(order.getAgentId());
				if(agentInfo != null){
					Double totalIncome = agentInfo.getTotalIncome();
					if(totalIncome == null){
						totalIncome = 0.0;
					}
					totalIncome = totalIncome + order.getCharge();
					//扣减账户余额
					Double costIncome = ConvertUtil.calPluginPrice(order.getBuyNum(),order.getCostPrice());
					Double blance =  agentInfo.getBlance();
					if(blance == null){
						blance = 0.0;
					}
					blance =  blance - costIncome;
					String upStr = " , blance = "+blance+", totalIncome = "+totalIncome;
					if(blance < 0.0 && agentInfo.getDebtTime() == null){
						upStr = upStr + ", debtTime = NOW() ";
					}
					agentInfoDAO.upByStr(order.getAgentId(), upStr);
				}
				
				//如果有使用代币购买，则扣减用户账户中的代币数
				if(order.getRepreCoin() != null && order.getRepreCoin() != 0.0){
					User user = userDAO.findByUserId(order.getUserId());
					Double afDeduction = user.getRepreCoin() - order.getRepreCoin();
				  	userDAO.updateByCriteria(order.getUserId(),"repreCoin",String.valueOf(afDeduction));
				}
				//写用户交易日志
				if(order.getCharge() != null && order.getCharge() != 0.0){
					UserTradeLog utLog = new UserTradeLog();
					utLog.setOrderId(orderId);
					utLog.setTradeType(Globals.TRADETYPE_BUY);
					utLog.setAmount(order.getCharge());
					utLog.setUserId(order.getUserId());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductId(order.getProductId());
					utLog.setProductType(order.getProductType());
					utLog.setSourceType(order.getSourceType());
					utLog.setPurchaseType(order.getPurchaseType());
					utLog.setAgentId(order.getAgentId());
					utLog.setDescription("使用支付宝购买插件：\""+plugin.getName()+"\"，交易金额："+order.getCharge()+"元");
					userTradeLogDAO.add(utLog);
				}
				if(order.getRepreCoin() != null && order.getRepreCoin() != 0.0){
					UserTradeLog utLog = new UserTradeLog();
					utLog.setOrderId(orderId);
					utLog.setTradeType(Globals.TRADETYPE_CONSUMEDAIPI);
					utLog.setAmount(order.getRepreCoin());
					utLog.setUserId(order.getUserId());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductId(order.getProductId());
					utLog.setProductType(order.getProductType());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductType(order.getPurchaseType());
					utLog.setAgentId(order.getAgentId());
					utLog.setDescription("使用代币购买插件：\""+plugin.getName()+"\"，交易金额："+order.getRepreCoin()+"代币");
					userTradeLogDAO.add(utLog);
				}

			}
			
		}else{
			log.info("**********支付宝即时到账回调签名失败!***********");
			ret = "fail";
		}
		
		return ret;
	}

	@Override
	public String wapPayBack(String notify_data, HttpServletRequest request)
			throws Exception {
		
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Entry<String, String[]> entry : requestParams.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// XML解析notify_data数据
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		// 商户订单号
		String out_trade_no = doc_notify_data.selectSingleNode("//notify/out_trade_no").getText();
		Integer orderId = Integer.parseInt(out_trade_no.substring(4, out_trade_no.length()));
		// 支付宝交易号
		String trade_no = doc_notify_data.selectSingleNode("//notify/trade_no").getText();
		// 交易状态
		String trade_status = doc_notify_data.selectSingleNode("//notify/trade_status").getText();
		String price = doc_notify_data.selectSingleNode("//notify/total_fee").getText();
		
		//判断是否接收过通知
		AgentOrder orderTemp = orderDAO.findBypayTradeNo(trade_no, Globals.PURCHASETYPE_ALIPAYWAP);
		if(orderTemp != null){
			return "success";
		}
		
		String ret = "fail";
		AgentOrder order = orderDAO.findByOrderId(orderId);
		AlipayConfigVO alipayConfig = agentBusService.getAgentAliConfg(order.getAgentId());
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		if (com.yoxi.hudongtui.utils.pay.alipay.wap.util.AlipayNotify.verifyNotify(params,alipayConfig)) {// 验证成功
			order.setPayNoticeTime(new Date());
			if(trade_status.equals(Globals.ALIPAY_TRADE_SUCCESS)
					|| trade_status.equals(Globals.ALIPAY_TRADE_FINISHED)){
				order.setPayOrderStatus(Globals.ORDERSTATUS_DONE);
			}
			order.setPayTradeNo(trade_no);
			order.setPurchaseType(Globals.PURCHASETYPE_ALIPAYWAP);
			//计算使用开始与结束时间
			PluginDetailVO plugin = pluginService.getAgentPlugin(order.getProductId(), order.getAgentId());
			int vaild =  plugin.getValid();
			//查找用户当前是否购买，是否是续费
			Date startDate = new Date();
			UserPlugin uplg = null;
			if(order.getUserPluginId() != null){
				uplg = userPluginDAO.getUserPluginById(order.getUserPluginId());
				if(uplg.getOverdueTime() != null){
					if(uplg.getUseType().equals(Globals.PLUGINUSER_USETYPE_BUY)
						&& uplg.getOverdueTime().after(new Date())){
							startDate = uplg.getOverdueTime();
						}
				}
				
			}
			order.setStartDate(startDate);
			Date endDate = DateUtils.addMonths(new Date(), vaild * order.getBuyNum());
			order.setEndDate(endDate);
			order.setTradeState(Globals.ORDERTRADESTATE_DONE);
		 	int upFlag = orderDAO.update(order);
		 	ret = "success";
			if(upFlag > 0){
				//插件业务处理
				if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINBUY)){//新购买
					pluginBusService.buy(plugin,order.getUserId(),order.getBuyNum());
				}
				if(order.getProductType().equals(Globals.PRODUCTTYPE_PLUGINRENEWAL)){//续费购买
					pluginBusService.renewal(plugin,uplg,order.getUserId(),order.getBuyNum());
				}
				//计算代理商账号金额相关
				AgentInfo agentInfo = agentInfoDAO.getById(order.getAgentId());
				if(agentInfo != null){
					Double totalIncome = agentInfo.getTotalIncome();
					totalIncome = totalIncome + order.getCharge();
					//扣减账户余额
					Double costIncome = ConvertUtil.calPluginPrice(order.getBuyNum(),order.getCostPrice());
					Double blance =  agentInfo.getBlance() - costIncome;
					String upStr = " , blance = "+blance+", totalIncome = "+totalIncome;
					if(blance < 0.0 && agentInfo.getDebtTime() == null){
						upStr = upStr + ", debtTime = NOW() ";
					}
					 agentInfoDAO.upByStr(order.getAgentId(), upStr);
				}
				//如果有使用代币购买，则扣减用户账户中的代币数
				if(order.getRepreCoin() != null && order.getRepreCoin() != 0.0){
					User user = userDAO.findByUserId(order.getUserId());
					Double afDeduction = user.getRepreCoin() - order.getRepreCoin();
				  	userDAO.updateByCriteria(order.getUserId(),"repreCoin",String.valueOf(afDeduction));
				}
				
				//写用户交易日志
				if(order.getCharge() != null && order.getCharge() != 0.0){
					UserTradeLog utLog = new UserTradeLog();
					utLog.setOrderId(orderId);
					utLog.setTradeType(Globals.TRADETYPE_BUY);
					utLog.setAmount(order.getCharge());
					utLog.setUserId(order.getUserId());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductId(order.getProductId());
					utLog.setProductType(order.getProductType());
					utLog.setSourceType(order.getSourceType());
					utLog.setPurchaseType(order.getPurchaseType());
					utLog.setAgentId(order.getAgentId());
					utLog.setDescription("使用支付宝购买插件：\""+plugin.getName()+"\"，交易金额："+order.getCharge()+"元");
					userTradeLogDAO.add(utLog);
				}
				
				if(order.getRepreCoin() != null && order.getRepreCoin() != 0.0){
					UserTradeLog utLog = new UserTradeLog();
					utLog.setOrderId(orderId);
					utLog.setTradeType(Globals.TRADETYPE_CONSUMEDAIPI);
					utLog.setAmount(order.getRepreCoin());
					utLog.setUserId(order.getUserId());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductId(order.getProductId());
					utLog.setProductType(order.getProductType());
					utLog.setSourceType(order.getSourceType());
					utLog.setProductType(order.getPurchaseType());
					utLog.setAgentId(order.getAgentId());
					utLog.setDescription("使用代币购买插件：\""+plugin.getName()+"\"，交易金额："+order.getCharge()+"代币");
					userTradeLogDAO.add(utLog);
				}

			}
				
		} else {// 验证失败
			log.error("支付宝WAP支付回调签名失败!");
			ret = "fail";
		
		}
		
		return ret;
	}

}
