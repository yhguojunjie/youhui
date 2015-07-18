package com.yoxi.hudongtui.model.cj.bottle;

import java.io.Serializable;
import java.util.Date;

public class ActBottleHelp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.Integer contentId;
	
	private java.lang.String helpOpenId;
	
	private java.lang.String helpedOpenId;
	
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

	public java.lang.Integer getContentId() {
		return contentId;
	}

	public void setContentId(java.lang.Integer contentId) {
		this.contentId = contentId;
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

	public Date getHelpTime() {
		return helpTime;
	}

	public void setHelpTime(Date helpTime) {
		this.helpTime = helpTime;
	}
	
}
