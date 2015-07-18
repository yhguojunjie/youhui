package com.yoxi.hudongtui.vo.pay.alipay;

import java.io.Serializable;

/**
 * 
 * 支付宝支付信息类
 *
 * @author wql
 *
 * @Date 2015年4月27日
 *
 */
public class AlipayConfigVO implements Serializable {

	private static final long serialVersionUID = 1536797355863972452L;
	
	private String partner;
	private String key;
	private String sellerAccount;
	private String input_charset = "utf-8";
	private String sign_type = "MD5";; 
	
	
	public AlipayConfigVO() {
	}

	public AlipayConfigVO(String partner, String key, String sellerAccount) {
		this.partner = partner;
		this.key = key;
		this.sellerAccount = sellerAccount;
	}

	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
}
