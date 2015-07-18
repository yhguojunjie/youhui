package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 
 * 微信支付Js api 支付接口参数
 * 
 * @author wql 
 * 
 * 2014-11-22
 *
 */
public class GetBrandWCPayReq {
	
	private String appId; //公众号Id  商户注册具有支付权限的公众号成功后即可获得
	
	private String timeStamp; //时间戳  商户生成，从 商户生成，从 1970197019701970年 1月 1日 00 ：00 ：00 至 今的秒数，即当前时间且最终需要转换为 今的秒数，
							  //即当前时间且最终需要转换为 今的秒数，即当前时间且最终需要转换为 今的秒数，即当前时间且最终需要转换为字符串形式;
	
	private String nonceStr; //随机字符串  商户随生成的随机字符串
	
	private String signType; //签名方式 目前仅支付SHA1

	private String signPackage; //经过转化后的订单详情扩展字符串
	
	private String paySign;//签名

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getSignPackage() {
		return signPackage;
	}

	public void setSignPackage(String signPackage) {
		this.signPackage = signPackage;
	}
	
}
