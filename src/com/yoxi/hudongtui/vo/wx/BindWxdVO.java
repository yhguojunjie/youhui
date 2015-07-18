package com.yoxi.hudongtui.vo.wx;

/**
 * 
 * 绑定微信账号返回VO
 *
 * @author wql
 *
 * @Date 2015年3月11日
 *
 */
public class BindWxdVO {
	
	/**微信unionId*/
	private String unionId;
	/**系统平台用户id*/
	private Integer userId;
	/**第三方表id*/
	private Integer thirdDbId;
	/**是否绑定过*/
	private Boolean binded;
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getThirdDbId() {
		return thirdDbId;
	}
	public void setThirdDbId(Integer thirdDbId) {
		this.thirdDbId = thirdDbId;
	}
	public Boolean getBinded() {
		return binded;
	}
	public void setBinded(Boolean binded) {
		this.binded = binded;
	}
	
	
}
