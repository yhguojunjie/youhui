package com.yoxi.hudongtui.model.cj.poker;

public class ActPokerRecord implements java.io.Serializable{

private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;
	
	private java.lang.Integer activityId;
	
	private java.lang.String mpOpenId;
	
	private java.lang.String prizeName;
	
	private java.lang.String mailAddress;
	
	private java.lang.String qq;
	
	private java.lang.String wechatId;
	
	private java.lang.String otherInfo;
	
	private java.lang.String tel;
	
	private java.lang.String username;
	
	private java.util.Date prizeTime;
	
	private java.util.Date exchangeTime;
	
	private java.lang.String opState;
	
	private java.lang.Integer temp;//临时变量用于去除重复数据

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

	public java.lang.String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(java.lang.String prizeName) {
		this.prizeName = prizeName;
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

	public java.lang.String getOpState() {
		return opState;
	}

	public void setOpState(java.lang.String opState) {
		this.opState = opState;
	}

	public java.lang.Integer getTemp() {
		return temp;
	}

	public void setTemp(java.lang.Integer temp) {
		this.temp = temp;
	}
	
	
}
