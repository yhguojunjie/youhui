package com.yoxi.hudongtui.vo.user;

/**
 * 
 * 代理商VO
 * 
 * @author gjj
 * 
 * @Date 2015年4月17日
 * 
 */
public class JoinboardVO {

	/** 联系人 */
	private java.lang.String contact;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 联系人email */
	private java.lang.String email;

	/** 联系人手机 */
	private java.lang.String mobile;

	/** qq */
	private java.lang.String qq;

	/** 备注 */
	private java.lang.String remark;

	public java.lang.String getContact() {
		return contact;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public java.lang.String getQq() {
		return qq;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

}
