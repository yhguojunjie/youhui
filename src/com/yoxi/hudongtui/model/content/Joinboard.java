package com.yoxi.hudongtui.model.content;

/**
 * 
 * 渠道
 * 
 * @author gjj
 * 
 * @Date 2015年4月17日
 * 
 */
public class Joinboard implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7604014769227098946L;
	/** 联系人 */
	private java.lang.String contact;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 联系人email */
	private java.lang.String email;
	/** id */
	private java.lang.Integer id;

	/** 联系人手机 */
	private java.lang.String mobile;

	/** qq */
	private java.lang.String qq;

	/** 备注 */
	private java.lang.String remark;
	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 联系人
	 */
	public java.lang.String getContact() {
		return this.contact;
	}
	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 联系人email
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer id
	 */

	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 联系人手机
	 */
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public java.lang.String getQq() {
		return qq;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 备注
	 */
	public java.lang.String getRemark() {
		return this.remark;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 联系人
	 */
	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 联系人email
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 联系人手机
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
