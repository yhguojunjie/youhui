package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 
 * 支付告警通知接收内容
 * 
 * @author wql 
 * 
 * 2014-11-22
 *
 */
public class PayWarnningRecv {
	
	private String AppId;//公众平台账户的APPID
	private String ErrorType; //错误类型
	private String Description; //错误描述
	private String AlarmContent;//错误详情
	private String TimeStamp; //时间戳
	private String AppSignatur;
	private String SignMethod;
	public String getAppId() {
		return AppId;
	}
	public void setAppId(String appId) {
		AppId = appId;
	}
	public String getErrorType() {
		return ErrorType;
	}
	public void setErrorType(String errorType) {
		ErrorType = errorType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAlarmContent() {
		return AlarmContent;
	}
	public void setAlarmContent(String alarmContent) {
		AlarmContent = alarmContent;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getAppSignatur() {
		return AppSignatur;
	}
	public void setAppSignatur(String appSignatur) {
		AppSignatur = appSignatur;
	}
	public String getSignMethod() {
		return SignMethod;
	}
	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}
	
	
}
