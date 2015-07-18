package com.yoxi.hudongtui.model.cj.vote;

import java.util.Date;

public class ActVoteVoter implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer activityId;
	
	private Integer voterId;
	
	private String picUrl;
	
	private String title;
	
	private String descs;
	
	private String otherInfo1;
	
	private String otherInfo2;
	
	private String otherInfo3;
	
	private String tel;
	
	private String username;
	
	private String mailAddress;
	
	private String qq;
	
	private String wechatId;
	
	private String otherInfo4;
	
	private Integer voteNum;
	
	private Date finalTime;

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
	
	public Integer getVoterId() {
		return voterId;
	}

	public void setVoterId(Integer voterId) {
		this.voterId = voterId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getOtherInfo1() {
		return otherInfo1;
	}

	public void setOtherInfo1(String otherInfo1) {
		this.otherInfo1 = otherInfo1;
	}

	public String getOtherInfo2() {
		return otherInfo2;
	}

	public void setOtherInfo2(String otherInfo2) {
		this.otherInfo2 = otherInfo2;
	}

	public String getOtherInfo3() {
		return otherInfo3;
	}

	public void setOtherInfo3(String otherInfo3) {
		this.otherInfo3 = otherInfo3;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getOtherInfo4() {
		return otherInfo4;
	}

	public void setOtherInfo4(String otherInfo4) {
		this.otherInfo4 = otherInfo4;
	}

	public Integer getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}
	
	
}
