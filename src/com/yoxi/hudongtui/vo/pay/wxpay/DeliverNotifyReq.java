package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 
 * 发货通知提交参数
 * 
 * @author wql
 * 
 * 2014-11-22
 *
 */
public class DeliverNotifyReq {
	
	private String appid; //公众平台账户的APPID
	private String openid; //购买用户的openid;
	private String transid; //交易单号
	private String out_trade_no; //第三方订单号
	private String deliver_timestamp; //发货时间戳，这里指Linux时间戳
	private String deliver_status;//发货状态 1表明成功 0表明失败 ，失败时需要在deliver_msg填上原因
	private String deliver_msg;//发货状态信息
	private String app_signature;//根据支付签名(paySing)生成方法中所讲的签名方式生成
	private String sign_method;//签名方法
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getDeliver_status() {
		return deliver_status;
	}
	public void setDeliver_status(String deliver_status) {
		this.deliver_status = deliver_status;
	}
	public String getDeliver_msg() {
		return deliver_msg;
	}
	public void setDeliver_msg(String deliver_msg) {
		this.deliver_msg = deliver_msg;
	}
	public String getApp_signature() {
		return app_signature;
	}
	public void setApp_signature(String app_signature) {
		this.app_signature = app_signature;
	}
	public String getSign_method() {
		return sign_method;
	}
	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}
	public String getDeliver_timestamp() {
		return deliver_timestamp;
	}
	public void setDeliver_timestamp(String deliver_timestamp) {
		this.deliver_timestamp = deliver_timestamp;
	}
}
