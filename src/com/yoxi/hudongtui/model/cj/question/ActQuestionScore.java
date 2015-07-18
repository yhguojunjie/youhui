package com.yoxi.hudongtui.model.cj.question;

import java.io.Serializable;

public class ActQuestionScore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.Integer playTime;
	
	private java.lang.Integer rightNum;
	
	private java.util.Date updateTime;

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

	public java.lang.Integer getPlayTime() {
		return playTime;
	}

	public void setPlayTime(java.lang.Integer playTime) {
		this.playTime = playTime;
	}

	public java.lang.Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(java.lang.Integer rightNum) {
		this.rightNum = rightNum;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
