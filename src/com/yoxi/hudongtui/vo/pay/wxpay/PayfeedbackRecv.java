package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 
 * 微创服务器post的用户维权投诉通知
 * 
 * @author wql 
 * 
 * 2014-11-22
 *
 */
public class PayfeedbackRecv {
	
	private String AppId; //公众号id
	private String TimeStamp; //时间戳
	private String OpenId; //支付该订单的openId
	private String MsgType; //通知类型  request 用户提交投诉 confirm 用户确认消除 reject 用户拒绝消除投诉
	private String FeedBackId; //投诉单号
	private String TransId; //交易订单号
	private String Reason; //用户投诉原因
	private String Solution; //用户希望解决文案
	private String ExtInfo; //备注信息+电话
	private String AppSignature; //签名
	private String SignMethod; //签名方法
	public String getAppId() {
		return AppId;
	}
	public void setAppId(String appId) {
		AppId = appId;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getFeedBackId() {
		return FeedBackId;
	}
	public void setFeedBackId(String feedBackId) {
		FeedBackId = feedBackId;
	}
	public String getTransId() {
		return TransId;
	}
	public void setTransId(String transId) {
		TransId = transId;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getSolution() {
		return Solution;
	}
	public void setSolution(String solution) {
		Solution = solution;
	}
	public String getExtInfo() {
		return ExtInfo;
	}
	public void setExtInfo(String extInfo) {
		ExtInfo = extInfo;
	}
	public String getAppSignature() {
		return AppSignature;
	}
	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
	}
	public String getSignMethod() {
		return SignMethod;
	}
	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}
	
}
