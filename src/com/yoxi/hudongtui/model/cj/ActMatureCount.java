package com.yoxi.hudongtui.model.cj;

import java.io.Serializable;

public class ActMatureCount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.Integer totalNum;
	
	private java.lang.Integer getNum;
	
	private java.lang.Integer stealNum;
	
	private java.lang.Integer todayNum;
	
	private java.lang.Integer totalExNum;
	
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

	public java.lang.Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(java.lang.Integer totalNum) {
		this.totalNum = totalNum;
	}

	public java.lang.Integer getGetNum() {
		return getNum;
	}

	public void setGetNum(java.lang.Integer getNum) {
		this.getNum = getNum;
	}

	public java.lang.Integer getStealNum() {
		return stealNum;
	}

	public void setStealNum(java.lang.Integer stealNum) {
		this.stealNum = stealNum;
	}

	public java.lang.Integer getTodayNum() {
		return todayNum;
	}

	public void setTodayNum(java.lang.Integer todayNum) {
		this.todayNum = todayNum;
	}
	
	public java.lang.Integer getTotalExNum() {
		return totalExNum;
	}

	public void setTotalExNum(java.lang.Integer totalExNum) {
		this.totalExNum = totalExNum;
	}

	public java.util.Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(java.util.Date finalTime) {
		this.finalTime = finalTime;
	}
	
	
}
