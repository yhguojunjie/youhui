package com.yoxi.hudongtui.model.cj;

import java.io.Serializable;

public class ActGivegiftRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.String prizeName;
	
	private java.lang.String tel;
	
	private java.lang.String opState;
	
	private java.util.Date prizeTime;
	
	private java.util.Date exchangeTime;

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

	public java.lang.String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(java.lang.String prizeName) {
		this.prizeName = prizeName;
	}

	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public java.lang.String getOpState() {
		return opState;
	}

	public void setOpState(java.lang.String opState) {
		this.opState = opState;
	}

	public java.util.Date getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(java.util.Date prizeTime) {
		this.prizeTime = prizeTime;
	}

	public java.util.Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(java.util.Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

}
