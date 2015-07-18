package com.yoxi.hudongtui.model.user;

/**
 * 
 * 邮件找回密码表
 * 
 * @author gjj
 * 
 *         2015-3-20
 */
public class EmailValidationCode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -175240087947482392L;
	/** id */
	private java.lang.Integer id;
	/** 过期时间 */
	private java.util.Date outDate;
	/** 用户id */
	private java.lang.Integer userId;
	/** 数字签名字段 */
	private java.lang.String validationCode;

	public java.lang.Integer getId() {
		return id;
	}

	public java.util.Date getOutDate() {
		return outDate;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public java.lang.String getValidationCode() {
		return validationCode;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setOutDate(java.util.Date outDate) {
		this.outDate = outDate;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public void setValidationCode(java.lang.String validationCode) {
		this.validationCode = validationCode;
	}
}
