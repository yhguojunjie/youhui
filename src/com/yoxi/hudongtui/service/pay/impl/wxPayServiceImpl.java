package com.yoxi.hudongtui.service.pay.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.dao.agent.AgentOrderDAO;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.dao.user.UserTradeLogDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.model.user.UserTradeLog;
import com.yoxi.hudongtui.service.pay.IWxPayService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.wx.IWxConfigService;
import com.yoxi.hudongtui.utils.common.BeanUtils;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.XMLUtil;
import com.yoxi.hudongtui.utils.pay.wxpay.UnifiedorderUtil;
import com.yoxi.hudongtui.utils.pay.wxpay.WxPayCommonUtil;
import com.yoxi.hudongtui.utils.pay.wxpay.WxPayUtil;
import com.yoxi.hudongtui.utils.wx.WeixinUtils;
import com.yoxi.hudongtui.vo.pay.wxpay.PayResultRecv;
import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderReq;
import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderResp;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

@Service
public class wxPayServiceImpl implements IWxPayService {

	private static Logger log = LoggerFactory.getLogger(wxPayServiceImpl.class);
	
	@Autowired
	private IWxConfigService wxConfigService;
	@Autowired
	private AgentOrderDAO orderDAO;
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private UserTradeLogDAO userTradeLogDAO;
	@Autowired
	private IPluginBusService pluginBusService;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private AgentInfoDAO agentInfoDAO;
	
	
	@Override
	public String callBack(HttpServletRequest request)throws Exception {
		PayResultRecv payResultRecv = new PayResultRecv();
		BeanUtils.toJavaBean(request, payResultRecv);
		// 判读是否接收过通知
		AgentOrder orderTemp = orderDAO.findBypayTradeNo(payResultRecv.getTransaction_id(), Globals.PURCHASETYPE_WENXINPAY);
		
		if (orderTemp != null) {
			return "success";
		}
		
		if (! WxPayUtil.processSign(payResultRecv)) {
			log.info("****************回调签名检查错误！***************");
			return "fail";
		}
		
		Integer orderId = Integer.parseInt(payResultRecv.getOut_trade_no());
		AgentOrder order = orderDAO.findByOrderId(orderId);
		order.setPayNoticeTime(new Date());
		if(payResultRecv.getTrade_state() == 0){
			order.setPayOrderStatus(Globals.ORDERSTATUS_DONE);
		}else{
			order.setPayOrderStatus(Globals.ORDERSTATUS_FAIL);
		}
		order.setPayTradeNo(payResultRecv.getTransaction_id());
		order.setPurchaseType(Globals.PURCHASETYPE_WENXINPAY);
		//计算使用开始与结束时间
		PluginDetailVO plugin = pluginService.getAgentPlugin(order.getProductId(), order.getAgentId());
		int vaild =  plugin.getValid();
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
		
		if(upFlag > 0){
			//插件相关业务
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
			//生成业务表数据
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
			utLog.setDescription("使用微信支付购买插件：\""+plugin.getName()+"\"，交易金额："+order.getCharge()+"元");
			userTradeLogDAO.add(utLog);
		}
		
		return "success";
	}

	

	@Override
	public String deliverNotify(String notifyContent) throws Exception {
		
		String apiUrl = MpConstants.getDelivernotify().replace("ACCESS_TOKEN",wxConfigService.getAccessToken(MpConstants.getAppid(),MpConstants.getAppsecret()).getAccessToken());
		
		JSONObject jsonObject = WeixinUtils.httpsRequest(apiUrl, "POST", notifyContent);
		
		 if (null != jsonObject) { 
				if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
					 log.error("微信支付发货接口出错：errcode="+jsonObject.getInt("errcode")+",errmsg="+jsonObject.getString("errmsg")); 
					 //accessToken出错情况下，重新刷新
					 if(jsonObject.getInt("errcode") == 40001 && "invalid credential".equals(jsonObject.getString("errmsg"))){
//						//  wxConfigService.updateDBStorAccessToken();
					 }
					 return "fail";
				}
				
				if(jsonObject.getInt("errcode") == 0 && jsonObject.getString("errmsg").equalsIgnoreCase("ok")){
					return "success";
				}
		  } 
		return "fail";
	}

	/**
	 * 统一支付接口
	 */
	@Override
	public UnifiedorderResp unifiedorder(UnifiedorderReq ufReq) throws Exception {
		
		ufReq.setAppid(MpConstants.getAppid());
		ufReq.setMch_id(ReadProperties.getPara("wxpay_Mchid"));
		ufReq.setSpbill_create_ip("127.0.0.1");
		ufReq.setNonce_str(WxPayCommonUtil.CreateNoncestr());
		
		//生成签名
		// 设置预支付参数  
	    SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();  
	   
	    signParams.put("appid", ufReq.getAppid());  
	    signParams.put("body", ufReq.getBody()); // 商品描述  
	    signParams.put("notify_url", ufReq.getNotify_url()); // 通知地址  
	    signParams.put("out_trade_no", ufReq.getOut_trade_no()); // 商户订单号  
	    signParams.put("mch_id", ufReq.getMch_id()); // 设置商户号  
	    signParams.put("total_fee", String.valueOf(ufReq.getTotal_fee()));
	    signParams.put("spbill_create_ip", ufReq.getSpbill_create_ip()); // 订单生成机器IP，指用户浏览器端IP  
	    signParams.put("trade_type", "JSAPI");  
	    signParams.put("nonce_str", ufReq.getNonce_str());  
	    signParams.put("openid",ufReq.getOpenid());  
	    
		
	    String afMD5 = UnifiedorderUtil.createSign("UTF-8", ReadProperties.getPara("wxpay_key"), signParams);
	    		
		ufReq.setSign(afMD5);
		
		signParams.put("sign", afMD5);
		
		String postXml = UnifiedorderUtil.getRequestXml(signParams);
		
		log.info("******postXml********="+postXml);
		
		String respXml =  UnifiedorderUtil.httpsRequest(MpConstants.getUnifiedorder(),"POST", postXml.toString());
		
		log.info("******respXml********="+respXml);

		UnifiedorderResp ufResp = null;

		Map<String, String> map = XMLUtil.doXMLParse(respXml);
		
		if(map != null){
			ufResp = new UnifiedorderResp();
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
					map.entrySet());
			
			for (int i = 0; i < infoIds.size(); i++) {
				Map.Entry<String, String> item = infoIds.get(i);
				if (item.getKey() != "") {
					if(item.getKey().equals("return_code")){
						ufResp.setReturn_code(item.getValue());
					}
					if(item.getKey().equals("return_msg")){
						ufResp.setReturn_msg(item.getValue());
					}
					if(item.getKey().equals("appid")){
						ufResp.setAppid(item.getValue());
					}
					if(item.getKey().equals("mch_id")){
						ufResp.setMch_id(item.getValue());
					}
					if(item.getKey().equals("device_info")){
						ufResp.setDevice_info(item.getValue());
					}
					if(item.getKey().equals("nonce_str")){
						ufResp.setNonce_str(item.getValue());
					}
					if(item.getKey().equals("sign")){
						ufResp.setSign(item.getValue());
					}
					if(item.getKey().equals("result_code")){
						ufResp.setResult_code(item.getValue());
					}
					if(item.getKey().equals("err_code")){
						ufResp.setErr_code(item.getValue());
					}
					if(item.getKey().equals("err_code_des")){
						ufResp.setErr_code_des(item.getValue());
					}
					if(item.getKey().equals("trade_type")){
						ufResp.setTrade_type(item.getValue());
					}
					if(item.getKey().equals("prepay_id")){
						ufResp.setPrepay_id(item.getValue());
					}
					if(item.getKey().equals("code_url")){
						ufResp.setCode_url(item.getValue());
					}
				}
			}

		}
		
		return ufResp;
	}


	
}
