package com.yoxi.hudongtui.service.pay;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderReq;
import com.yoxi.hudongtui.vo.pay.wxpay.UnifiedorderResp;

/**
 * 
 * 微信支付service
 * 
 * @author wql
 * 
 * 2014-11-22
 *
 */
public interface IWxPayService {

	/**
	 * 微信支付回调
	 * @param request
	 * @return
	 */
	public String callBack(HttpServletRequest request)throws Exception;
	
	/**
	 * 发货通知
	 * @return
	 * @throws Exception
	 */
	public String deliverNotify(String notifyContent)throws Exception;
	
	/**
	 * 统一支付接口
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public UnifiedorderResp unifiedorder(UnifiedorderReq ufReq)throws Exception;
}
