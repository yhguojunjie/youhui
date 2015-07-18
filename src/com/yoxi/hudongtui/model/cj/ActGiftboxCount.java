package com.yoxi.hudongtui.model.cj;

public class ActGiftboxCount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.Integer leftTimes;
	
	private java.util.Date finalTime;

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

	public java.lang.Integer getLeftTimes() {
		return leftTimes;
	}

	public void setLeftTimes(java.lang.Integer leftTimes) {
		this.leftTimes = leftTimes;
	}

	public java.util.Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(java.util.Date finalTime) {
		this.finalTime = finalTime;
	}
	
	
}
