package com.yoxi.hudongtui.model.cj.poker;

import java.io.Serializable;
import java.util.Date;

public class ActPokerConf implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer activityId;
	
	private String name;
	
	private String bAdvert;
	
	private String advertImgUrl;
	
	private String advertUrl;
	
	private String shareImgUrl;
	
	private String shareTitle;
	
	private String shareDescription;
	
	private String bShowNum;
	
	private String bShowList;
	
	private Object awards;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer playTimes;
	
	private String bUrlType;
	
	private String followUrl;
	
	private String rule;
	
	private String exExplain;
	
	private String userinfo;
	
	private String coverUrl;
	
	private String bgUrl;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getShareImgUrl() {
		return shareImgUrl;
	}

	public void setShareImgUrl(String shareImgUrl) {
		this.shareImgUrl = shareImgUrl;
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

	public String getbShowNum() {
		return bShowNum;
	}

	public void setbShowNum(String bShowNum) {
		this.bShowNum = bShowNum;
	}

	public String getbShowList() {
		return bShowList;
	}

	public void setbShowList(String bShowList) {
		this.bShowList = bShowList;
	}

	public Object getAwards() {
		return awards;
	}

	public void setAwards(Object awards) {
		this.awards = awards;
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

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}
	
	public String getbUrlType() {
		return bUrlType;
	}

	public void setbUrlType(String bUrlType) {
		this.bUrlType = bUrlType;
	}

	public String getFollowUrl() {
		return followUrl;
	}

	public void setFollowUrl(String followUrl) {
		this.followUrl = followUrl;
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

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
