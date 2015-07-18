package com.yoxi.hudongtui.model.cj.question;

public class ActQuestionBank implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private Object question;
	
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

	public Object getQuestion() {
		return question;
	}

	public void setQuestion(Object question) {
		this.question = question;
	}

	public java.util.Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(java.util.Date finalTime) {
		this.finalTime = finalTime;
	}
	
	
}
