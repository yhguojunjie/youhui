package com.yoxi.hudongtui.vo.content;

import java.io.Serializable;
import java.util.List;

import com.yoxi.hudongtui.model.content.ActClassicPic;
import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 经典活动VO
 * 
 * @author wql
 * 
 * @Date 2015年3月22日
 * 
 */
public class ActClassicVO implements Serializable {

	private static final long serialVersionUID = 4043252802672510545L;

	/** 模板访问地址 */
	private java.lang.String actAccessUrl;
	/** 图片列表 */
	private List<ActClassicPic> actClassicPicList;
	/** 案例第一张图 */
	private java.lang.String actClassicPicUrl;

	/** 活动图片 */
	private java.lang.String actIcon;

	/** 活动id */
	private java.lang.Integer actId;

	/** 活动标题 */
	private java.lang.String actTitle;
	/** agentId */
	private java.lang.Integer agentId;
	/** 品牌logo(此字段为空，则使用活动默认) */
	private java.lang.String bannerLogo;
	/** 品牌名(此字段为空，则使用活动默认) */
	private java.lang.String bannerName;
	/** 浏览量 */
	private java.lang.Integer browseNum;
	/** 活动结束时间 */
	private java.util.Date endTime;
	private java.lang.Integer id;
	/** 参与人数 */
	private java.lang.Integer joinNum;
	/** 模板id */
	private java.lang.Integer pluginId;
	/** 模板名称 */
	private java.lang.String pluginName;
	/** 顺序 */
	private java.lang.Integer seq;

	/** 活动开始时间 */
	private java.util.Date startTime;

	/** 活动更新时间 */
	private java.util.Date updateTime;

	/** userId */
	private java.lang.Integer userId;

	public java.lang.String getActAccessUrl() {
		return actAccessUrl;
	}

	public List<ActClassicPic> getActClassicPicList() {
		return actClassicPicList;
	}

	public java.lang.String getActClassicPicUrl() {
		if (!StringUtils.isNullBlank(actClassicPicUrl)) {
			return ConvertUtil.procImgPath(actClassicPicUrl);
		}
		return actClassicPicUrl;
	}

	public java.lang.String getActIcon() {
		if (!StringUtils.isNullBlank(actIcon)) {
			return ConvertUtil.procImgPath(actIcon);
		}
		return actIcon;
	}

	public java.lang.Integer getActId() {
		return actId;
	}

	public java.lang.String getActTitle() {
		return actTitle;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.String getBannerLogo() {
		if (!StringUtils.isNullBlank(bannerLogo)) {
			return ConvertUtil.procImgPath(bannerLogo);
		}
		return bannerLogo;
	}

	public java.lang.String getBannerName() {
		return bannerName;
	}

	public java.lang.Integer getBrowseNum() {
		return browseNum;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.Integer getJoinNum() {
		return joinNum;
	}

	public java.lang.Integer getPluginId() {
		return pluginId;
	}

	public java.lang.String getPluginName() {
		return pluginName;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setActAccessUrl(java.lang.String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}

	public void setActClassicPicList(List<ActClassicPic> actClassicPicList) {
		this.actClassicPicList = actClassicPicList;
	}

	public void setActClassicPicUrl(java.lang.String actClassicPicUrl) {
		this.actClassicPicUrl = actClassicPicUrl;
	}

	public void setActIcon(java.lang.String actIcon) {
		this.actIcon = actIcon;
	}

	public void setActId(java.lang.Integer actId) {
		this.actId = actId;
	}

	public void setActTitle(java.lang.String actTitle) {
		this.actTitle = actTitle;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setBannerLogo(java.lang.String bannerLogo) {
		this.bannerLogo = bannerLogo;
	}

	public void setBannerName(java.lang.String bannerName) {
		this.bannerName = bannerName;
	}

	public void setBrowseNum(java.lang.Integer browseNum) {
		this.browseNum = browseNum;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setJoinNum(java.lang.Integer joinNum) {
		this.joinNum = joinNum;
	}

	public void setPluginId(java.lang.Integer pluginId) {
		this.pluginId = pluginId;
	}

	public void setPluginName(java.lang.String pluginName) {
		this.pluginName = pluginName;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
}
