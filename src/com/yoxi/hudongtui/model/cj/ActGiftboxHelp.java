package com.yoxi.hudongtui.model.cj;

import java.io.Serializable;
import java.util.Date;

public class ActGiftboxHelp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String helpOpenId;
	
	private java.lang.String helpedOpenId;
	
	private java.lang.Integer boxId;
	
	private Date helpTime;

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

	public java.lang.String getHelpOpenId() {
		return helpOpenId;
	}

	public void setHelpOpenId(java.lang.String helpOpenId) {
		this.helpOpenId = helpOpenId;
	}

	public java.lang.String getHelpedOpenId() {
		return helpedOpenId;
	}

	public void setHelpedOpenId(java.lang.String helpedOpenId) {
		this.helpedOpenId = helpedOpenId;
	}

	public java.lang.Integer getBoxId() {
		return boxId;
	}

	public void setBoxId(java.lang.Integer boxId) {
		this.boxId = boxId;
	}

	public Date getHelpTime() {
		return helpTime;
	}

	public void setHelpTime(Date helpTime) {
		this.helpTime = helpTime;
	}

	
}
