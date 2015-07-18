package com.yoxi.hudongtui.vo.pay.wxpay;

/**
 * 统一支付接口回传参数
 * 
 * @author wql
 * 
 * 2014-12-16
 * 
 */
public class UnifiedorderResp {
	
	private String return_code; //是，返回状态码
	private String return_msg;//否，返回信息
	private String appid;//是，公众账号ID
	private String mch_id;//是，商户号
	private String device_info;//否，设备号
	private String nonce_str; //是，随机字符串
	private String sign;//是，签名
	private String result_code;//是，业务结果
	private String err_code;//否，错误代码
	private String err_code_des;//否，错误代码描述
	private String trade_type;//是，交易类型 JSAPI、NATIVE、APP
	private String prepay_id;//是，预支付ID 微信生成的预支付ID，用于后续接口调用中使用
	private String code_url;//否，二维码链接trade_type为NATIVE是有返回，此参数可直接生成二维码展示出来进行扫码支付
	
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	
}
