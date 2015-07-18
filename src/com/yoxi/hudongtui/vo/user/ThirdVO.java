package com.yoxi.hudongtui.vo.user;

/**
 * 
 * 第三方用户登录注册返回
 * 
 * @author wql
 *
 */
public class ThirdVO {

	/**
	 * 用户id
	 */
	public Integer userId;
	
	/**
	 * 是否已更新
	 */
	private Boolean isUpdated;

	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
}
