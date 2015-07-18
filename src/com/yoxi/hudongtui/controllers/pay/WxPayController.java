package com.yoxi.hudongtui.controllers.pay;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.model.agent.AgentOrder;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.service.order.IOrderService;
import com.yoxi.hudongtui.service.pay.IRepreCoinService;
import com.yoxi.hudongtui.service.pay.IWxPayService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.pay.wxpay.UnifiedorderUtil;
import com.yoxi.hudongtui.utils.pay.wxpay.WxPayCommonUtil;
import com.yoxi.hudongtui.utils.pay.wxpay.WxPayHelper;
import com.yoxi.hudongtui.utils.wx.MessageUtils;
import com.yoxi.hudongtui.vo.pay.wxpay.DeliverNotifyReq;
import com.yoxi.hudongtui.vo.pay.wxpay.JsApiReq;
import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderReq;
import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderResp;


/**
 * 
 * 微信支付
 * 
 * @author wql
 * 
 * 2014-11-22
 *
 */
public class WxPayController {

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
	private IWxPayService wxPayService;
	
	/**
	 * js 支付
	 * @param inv
	 * @param userId
	 * @param openId
	 * @return
	 */
	@Get("jsPay")
	public String pay(Invocation inv,@Param("orderId") Integer orderId,
			@Param("openId") String openId)throws Exception{
		
		AgentOrder order = orderService.findByOrderId(orderId);
		openId = "odabDtwdWmJBeqNOCEb6PwfYDmL8";
		if(order != null){

			UnifiedorderReq ufReq = new UnifiedorderReq();
			Plugin plugin = pluginService.getById(order.getProductId());
			
			ufReq.setBody(plugin.getName());
			ufReq.setOut_trade_no(String.valueOf(orderId));
			Double afPluginPrice = plugin.getPromPrice() != null && !plugin.getPromPrice().equals(0.0) ? plugin.getPromPrice() : plugin.getPrice();
			ufReq.setTotal_fee(afPluginPrice.intValue());
			ufReq.setTrade_type("JSAPI");
			ufReq.setOpenid(openId);
			ufReq.setSpbill_create_ip(inv.getRequest().getLocalAddr());
			ufReq.setNotify_url(ReadProperties.getPara("httpPath")+"/pay/wxPay/notify");
			//调用统一支付接口
			UnifiedorderResp ufResp = wxPayService.unifiedorder(ufReq);
			if(ufResp != null){
				if(ufResp.getReturn_code().equals("SUCCESS")){
					if(ufResp.getResult_code().equals("SUCCESS")){
						JsApiReq jsApiReq = new JsApiReq();
						jsApiReq.setAppId(ReadProperties.getPara("wx_mp_appid"));
						String nonceStr = WxPayCommonUtil.CreateNoncestr();
						jsApiReq.setNonceStr(nonceStr);
						String timeStamp = String.valueOf(System.currentTimeMillis());
						jsApiReq.setTimeStamp(timeStamp);
						jsApiReq.setPackageInfo("prepay_id="+ufResp.getPrepay_id());
						//生成签名
					     SortedMap<Object,Object> params = new TreeMap<Object,Object>();
					     params.put("appId", ReadProperties.getPara("wx_mp_appid"));
					     params.put("timeStamp", Long.toString(new Date().getTime()));
					     params.put("nonceStr", nonceStr);
					     params.put("package", "prepay_id="+ufResp.getPrepay_id());
					     params.put("signType","MD5");
					     String paySign =  UnifiedorderUtil.createSign("UTF-8",ReadProperties.getPara("wxpay_key"),params);
					    
					     jsApiReq.setSignType("MD5");
					     jsApiReq.setPaySign(paySign);
					     
//					     String userAgent = inv.getRequest().getHeader("user-agent");
//					     char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
//					     jsApiReq.setAgent( new String(new char[]{agent}));
						 
//					     inv.getRequest().setAttribute("jsApiReq",jsApiReq);
					     inv.addModel("jsApiReq",jsApiReq);
					     log.info("************appId**********="+jsApiReq.getAppId());
					     log.info("************getTimeStamp**********="+jsApiReq.getTimeStamp());
					     log.info("************getNonceStr**********="+jsApiReq.getNonceStr());
					     log.info("************getPackageInfo**********="+jsApiReq.getPackageInfo());
					     log.info("************getPaySign**********="+jsApiReq.getPaySign());
					     log.info("************getSignType**********="+jsApiReq.getSignType());
					
					}else{
						log.error("*****微信支付统一支付接口第二层返回业务结果：err_code *********="+ufResp.getErr_code()
								+",err_code_des="+ufResp.getErr_code_des());
					}
					
					
				}else{
					log.error("*****微信支付统一支付接口第一层返回信息 *********="+ufResp.getReturn_msg());
				}
			}
			
		}
		
		return "wxpay/jspay";
		
	}
	
	/**
	 * 支付回调
	 * @param inv
	 * @return
	 */
	@Post("notify")
	public void notify(Invocation inv)throws Exception{
		
        PrintWriter out = inv.getResponse().getWriter(); 
        out.print(wxPayService.callBack(inv.getRequest())); 
        out.close(); 
	}
	
	/**
	 * Native原生支付回调URL
	 * @param inv
	 * @return
	 */
	@Get("getpackage")
	public String getpackage(Invocation inv){
		
		return null;
	}
	
	/**
	 * 维权通知URL
	 * @param inv
	 * @return
	 */
	@Post("payfeedback")
	public String payfeedback(Invocation inv){
		log.info("***********维权URL被调用 *********************");
		
		try {
			Map<String, String> requestMap = MessageUtils.parseXml(inv.getRequest());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "@success";
	}
	
	/**
	 * 告警通知URL
	 * @param inv
	 * @return
	 */
	@Post("warning")
	public String warning(Invocation inv){
		log.info("***********告警通知URL被调用 *********************");
		try {
			Map<String, String> requestMap = MessageUtils.parseXml(inv.getRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "@success";
	}
	
	
	
	/**
	 * 发货通知
	 * @return
	 */
	@Get("delivernotify")
	public String delivernotify(){
		
		//发货通知，生成发货通知内容 demo
		DeliverNotifyReq deliverNotifyRes = new DeliverNotifyReq();
		deliverNotifyRes.setAppid(ReadProperties.getPara("wx_mp_appid"));
		deliverNotifyRes.setDeliver_status("1");
		deliverNotifyRes.setDeliver_msg("ok");
		deliverNotifyRes.setDeliver_timestamp(String.valueOf(System.currentTimeMillis()/1000));
		deliverNotifyRes.setOpenid("o7srStzmOE5__j12eNKRBBTybgsA");
		deliverNotifyRes.setOut_trade_no("1000000053");
		deliverNotifyRes.setSign_method("sha1");
		deliverNotifyRes.setTransid("1220491701201409033205975716");
		WxPayHelper wxPayHelper = new WxPayHelper();
	
		String deliverStatus;
		try {
			deliverNotifyRes.setApp_signature(wxPayHelper.CreateDeliverNotifyAppSign(deliverNotifyRes));
			log.info("提交json="+JsonUtils.toJson(deliverNotifyRes));
			deliverStatus = wxPayService.deliverNotify(JsonUtils.toJson(deliverNotifyRes));
			log.info("发货通知成功状态="+deliverStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "@success";
	}
	
}
