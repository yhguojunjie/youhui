package com.yoxi.hudongtui.model.cj.zanrenqi;

import java.io.Serializable;

public class ActZanrenqiPrize implements Serializable{

	private static final long serialVersionUID = 1L;

	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.Integer prizeType;
	
	private java.lang.Integer realNum;
	
	private java.lang.Integer deliverNum;
	
	private java.lang.Integer version;//数据版本 乐观锁
	
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
	
	public java.lang.Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(java.lang.Integer prizeType) {
		this.prizeType = prizeType;
	}

	public java.lang.Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(java.lang.Integer realNum) {
		this.realNum = realNum;
	}

	public java.lang.Integer getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(java.lang.Integer deliverNum) {
		this.deliverNum = deliverNum;
	}

	public java.lang.Integer getVersion() {
		return version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	public java.util.Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(java.util.Date finalTime) {
		this.finalTime = finalTime;
	}
	
}
