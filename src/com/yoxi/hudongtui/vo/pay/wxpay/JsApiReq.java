package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * js api 方式 需要从后台传到页面的参数
 * 
 * @author wql
 * 
 * 2014-11-22
 *
 */
public class JsApiReq {
	
	private String appId; //公众号id
	private String timeStamp;//时间戳
	private String nonceStr;//随机字符串
	private String packageInfo;//统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***
	private String signType;//签名方式
	private String paySign;//签名
	
	//自定义
	private String agent; //浏览器版本
	
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
	public String getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
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
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
}
