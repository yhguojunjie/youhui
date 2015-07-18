package com.yoxi.hudongtui.exception;

/**
 * 微信支付异常类
 * 
 * @author wangql
 * 
 * 2014-04-12
 *
 */
public class SDKRuntimeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SDKRuntimeException(String str) {
	        super(str);
	 }
}
