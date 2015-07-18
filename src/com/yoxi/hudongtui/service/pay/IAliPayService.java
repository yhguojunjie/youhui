package com.yoxi.hudongtui.service.pay;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.hudongtui.vo.pay.alipay.DirectPaySyncBack;

/**
 * 
 * 第三方支付接口
 * 
 * @author wql
 *
 */
public interface IAliPayService {
	
	/**
	 * 支付宝即时账支付回调
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String directPayBack(DirectPaySyncBack directPaySyncBack,
			HttpServletRequest request) throws Exception;
	
	/**
	 * wap支付回调
	 * @param notify_data
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String wapPayBack( String notify_data, 
			HttpServletRequest request)throws Exception;
	
	
}
