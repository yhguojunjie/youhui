package com.yoxi.hudongtui.model.cj.guessgame;

import java.io.Serializable;

public class ActGuessGameBetRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.String mpOpenId;
	
	private java.lang.Integer matchId;
	
	private java.lang.Integer activityId;
	
	private java.lang.String comment;
	
	private java.lang.String bJoinType;
	
	private java.lang.String bPrize;
	
	private java.lang.Integer leftScore;
	
	private java.lang.Integer rightScore;
	
	private java.util.Date betTime;
	
	private java.lang.String mailAddress;
	
	private java.lang.String qq;
	
	private java.lang.String wechatId;
	
	private java.lang.String otherInfo;
	
	private java.lang.String tel;
	
	private java.lang.String username;
	
	private java.util.Date exchangeTime;
	
	private java.lang.String opState;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.String getMpOpenId() {
		return mpOpenId;
	}

	public void setMpOpenId(java.lang.String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}

	public java.lang.Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(java.lang.Integer matchId) {
		this.matchId = matchId;
	}

	public java.lang.Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(java.lang.Integer activityId) {
		this.activityId = activityId;
	}

	public java.lang.String getComment() {
		return comment;
	}

	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}

	public java.lang.String getbJoinType() {
		return bJoinType;
	}

	public void setbJoinType(java.lang.String bJoinType) {
		this.bJoinType = bJoinType;
	}

	public java.lang.String getbPrize() {
		return bPrize;
	}

	public void setbPrize(java.lang.String bPrize) {
		this.bPrize = bPrize;
	}

	public java.lang.Integer getLeftScore() {
		return leftScore;
	}

	public void setLeftScore(java.lang.Integer leftScore) {
		this.leftScore = leftScore;
	}

	public java.lang.Integer getRightScore() {
		return rightScore;
	}

	public void setRightScore(java.lang.Integer rightScore) {
		this.rightScore = rightScore;
	}

	public java.util.Date getBetTime() {
		return betTime;
	}

	public void setBetTime(java.util.Date betTime) {
		this.betTime = betTime;
	}

	public java.lang.String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(java.lang.String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public java.lang.String getQq() {
		return qq;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	public java.lang.String getWechatId() {
		return wechatId;
	}

	public void setWechatId(java.lang.String wechatId) {
		this.wechatId = wechatId;
	}

	public java.lang.String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(java.lang.String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.util.Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(java.util.Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public java.lang.String getOpState() {
		return opState;
	}

	public void setOpState(java.lang.String opState) {
		this.opState = opState;
	}

}
