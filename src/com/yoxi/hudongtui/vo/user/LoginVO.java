package com.yoxi.hudongtui.vo.user;

/**
 * 
 *  登录VO
 *
 * @author wql
 *
 * @Date 2015年3月16日
 *
 */
public class LoginVO {

	private String account;
	private String autoLogin;
	private String password;
	private String userId;
	public String getAccount() {
		return account;
	}
	public String getAutoLogin() {
		return autoLogin;
	}
	
	public String getPassword() {
		return password;
	}
	public String getUserId() {
		return userId;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
