package com.yoxi.hudongtui.utils.pay.wxpay;

import com.yoxi.hudongtui.exception.SDKRuntimeException;

/**
 * 微信支付md5签名工具类
 * 
 * @author wql
 * 
 * 2014-12-18
 *
 */
public class MD5SignUtil {
	
	/**
	 * 生成签名
	 * @param content
	 * @param key
	 * @return
	 * @throws SDKRuntimeException
	 */
	public static String Sign(String content, String key) throws SDKRuntimeException {
		
		String signStr = "";

		if ("" == key) {
			throw new SDKRuntimeException("签名key不能为空！");
		}
		if ("" == content) {
			throw new SDKRuntimeException("签名内容不能为空");
		}
		signStr = content + "&key=" + key;

		return MD5Util.MD5(signStr).toUpperCase();

	}
	
	/**
	 * 验证签名
	 * @param content
	 * @param sign
	 * @param md5Key
	 * @return
	 */
	public static boolean VerifySignature(String content, String sign, String md5Key) {
		String signStr = content + "&key=" + md5Key;
		String calculateSign = MD5Util.MD5(signStr).toUpperCase();
		String tenpaySign = sign.toUpperCase();
		return (calculateSign == tenpaySign);
	}
}
