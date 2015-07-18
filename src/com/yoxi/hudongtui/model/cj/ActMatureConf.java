package com.yoxi.hudongtui.model.cj;

import java.util.Date;

public class ActMatureConf implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer activityId;
	
	private String title;
	
	private String shareImgUrl;
	
	private String bAdvert;
	
	private String advertImgUrl;
	
	private String advertUrl;
	
	private String shareTitle;
	
	private String shareDescription;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer needCount;
	
	private String rule;
	
	private String exExplain;
	
	private String userinfo;
	
	private String coverUrl;
	
	private Date createTime;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShareImgUrl() {
		return shareImgUrl;
	}

	public void setShareImgUrl(String shareImgUrl) {
		this.shareImgUrl = shareImgUrl;
	}

	public String getbAdvert() {
		return bAdvert;
	}

	public void setbAdvert(String bAdvert) {
		this.bAdvert = bAdvert;
	}

	public String getAdvertImgUrl() {
		return advertImgUrl;
	}

	public void setAdvertImgUrl(String advertImgUrl) {
		this.advertImgUrl = advertImgUrl;
	}

	public String getAdvertUrl() {
		return advertUrl;
	}

	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareDescription() {
		return shareDescription;
	}

	public void setShareDescription(String shareDescription) {
		this.shareDescription = shareDescription;
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

	public Integer getNeedCount() {
		return needCount;
	}

	public void setNeedCount(Integer needCount) {
		this.needCount = needCount;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getExExplain() {
		return exExplain;
	}

	public void setExExplain(String exExplain) {
		this.exExplain = exExplain;
	}

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	
	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
