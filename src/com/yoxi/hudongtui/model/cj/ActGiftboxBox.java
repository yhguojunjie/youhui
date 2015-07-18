package com.yoxi.hudongtui.model.cj;

import java.io.Serializable;

public class ActGiftboxBox implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.Integer helpTimes;
	
	private java.lang.String type;
	
	private java.lang.String state;
	
	private java.lang.String isPrize;
	
	private java.util.Date createTime;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.Integer activityId) {
		this.activityId = activityId;
	}

	public java.lang.String getMpOpenId() {
		return mpOpenId;
	}

	public void setMpOpenId(java.lang.String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}

	public java.lang.Integer getHelpTimes() {
		return helpTimes;
	}

	public void setHelpTimes(java.lang.Integer helpTimes) {
		this.helpTimes = helpTimes;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getIsPrize() {
		return isPrize;
	}

	public void setIsPrize(java.lang.String isPrize) {
		this.isPrize = isPrize;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

}
