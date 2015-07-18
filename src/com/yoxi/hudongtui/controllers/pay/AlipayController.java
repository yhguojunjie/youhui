package com.yoxi.hudongtui.controllers.pay;

import java.io.PrintWriter;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.service.pay.IAliPayService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.vo.pay.alipay.DirectPaySyncBack;

/**
 * 
 * 支付宝
 * 
 * @author wql
 *
 */
public class AlipayController {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IAliPayService alipayService;
	
	@Autowired
	private IPluginService pluginSerivce;

	/**
	 * 跳转支付发起页面
	 * @param inv
	 * @return
	 * @throws Exception
	 */
//	@Get("toAlipay")
//	public String toAlipay(Invocation inv)throws Exception{
//		return "alipay/directpay/index.jsp";
//	}

	/**
	 * 发起支付申请
	 * @param inv
	 * @return
	 * @throws Exception
	 */
//	@Get("toAlipayApi")
//	public String toAlipayApi(Invocation inv,@Param("pluginId") Integer pluginId,
//			@Param("useDaipi") String useDaipi)throws Exception{
//		
//		Plugin plugin = pluginSerivce.getById(pluginId);
//		
//		inv.getRequest().setAttribute("WIDseller_email", ReadProperties.getPara("SELLER_ACCOUNT_NAME"));
//		inv.getRequest().setAttribute("WIDout_trade_no", "1000000008");
//		inv.getRequest().setAttribute("WIDsubject", "微信场景");
//		inv.getRequest().setAttribute("WIDtotal_fee","0.01");
//		inv.getRequest().setAttribute("userId", "1000000001");
////		request.setAttribute("WIDtotal_fee", "0.01");
//		return "alipay/directpay/alipayapi.jsp";
//	}
	
	/**
	 * 即时到账支付回调
	 * @param directPaySyncBack
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Post("back")
	public void directPayback(DirectPaySyncBack directPaySyncBack,
			Invocation inv) throws Exception{
		log.info("*************支付宝即时到账回调*********************");
		String ret = alipayService.directPayBack(directPaySyncBack, inv.getRequest());
        PrintWriter out = inv.getResponse().getWriter(); 
        out.print(ret); 
        out.close(); 
	}
	
	
	/**
	 * wap支付回调
	 * @param notify_data
	 * @param request
	 * @throws Exception
	 */
	@Post("wapBack")
	public void wapBack(@Param("notify_data") String notify_data, 
			Invocation inv)throws Exception{
		log.info("*************支付宝WAP回调*********************");
		String ret = alipayService.wapPayBack(notify_data, inv.getRequest());
        PrintWriter out = inv.getResponse().getWriter(); 
        out.print(ret); 
        out.close(); 
	}
	
}
