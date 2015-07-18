package com.yoxi.hudongtui.model.cj.guessgame;

import java.util.Date;

public class ActGuessGameAdmin implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer activityId;
	
	private String name;
	
	private String leftTeam;
	
	private String leftPicUrl;
	
	private String rightTeam;
	
	private String rightPicUrl;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer leftScore;
	
	private Integer rightScore;
	
	private Integer leftPeople;
	
	private Integer rightPeople;
	
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeftTeam() {
		return leftTeam;
	}

	public void setLeftTeam(String leftTeam) {
		this.leftTeam = leftTeam;
	}

	public String getLeftPicUrl() {
		return leftPicUrl;
	}

	public void setLeftPicUrl(String leftPicUrl) {
		this.leftPicUrl = leftPicUrl;
	}

	public String getRightTeam() {
		return rightTeam;
	}

	public void setRightTeam(String rightTeam) {
		this.rightTeam = rightTeam;
	}

	public String getRightPicUrl() {
		return rightPicUrl;
	}

	public void setRightPicUrl(String rightPicUrl) {
		this.rightPicUrl = rightPicUrl;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getLeftScore() {
		return leftScore;
	}

	public void setLeftScore(Integer leftScore) {
		this.leftScore = leftScore;
	}

	public Integer getRightScore() {
		return rightScore;
	}

	public void setRightScore(Integer rightScore) {
		this.rightScore = rightScore;
	}

	public Integer getLeftPeople() {
		return leftPeople;
	}

	public void setLeftPeople(Integer leftPeople) {
		this.leftPeople = leftPeople;
	}

	public Integer getRightPeople() {
		return rightPeople;
	}

	public void setRightPeople(Integer rightPeople) {
		this.rightPeople = rightPeople;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
