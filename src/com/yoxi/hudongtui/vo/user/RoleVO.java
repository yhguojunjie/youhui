package com.yoxi.hudongtui.vo.user;

import java.io.Serializable;

/**
 * 
 * 权限信息VO
 * 
 * @author gjj
 * 
 * @Date 2015年3月31日
 * 
 */
public class RoleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659425248874752979L;

	/** id */
	private java.lang.Integer id;
	/** 权限编号 */
	private java.lang.String roleCode;
	/** 权限名称 */
	private java.lang.String roleName;

	/** userId */
	private java.lang.Integer userId;

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getRoleCode() {
		return roleCode;
	}

	public java.lang.String getRoleName() {
		return roleName;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setRoleCode(java.lang.String roleCode) {
		this.roleCode = roleCode;
	}

	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

}
