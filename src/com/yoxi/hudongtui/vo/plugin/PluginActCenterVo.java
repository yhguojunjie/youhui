package com.yoxi.hudongtui.vo.plugin;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 活动圈 VO
 * 
 */
public class PluginActCenterVo implements java.io.Serializable {

	private static final long serialVersionUID = 2843412041668648057L;
	/** 活动访问页面地址 */
	private java.lang.String accessUrl;
	/** 插件活动访问地址 */
	private java.lang.String actAccessUrl;
	/** 浏览数 */
	private java.lang.Integer actBrowseNum;
	/** 活动图标 */
	private java.lang.String actIcon;
	/** 活动ID */
	private java.lang.Integer actId;
	/** 参与人数 */
	private java.lang.Integer actJoinNum;
	/** 是否在平台公开(0公开,1不公开) */
	private java.lang.String actOpen;
	/** 活动名称 */
	private java.lang.String actTitle;
	/** 是否在代表商平台下公开(0公开,1不公开) */
	private java.lang.String agentActOpen;
	/** 代理商ID */
	private java.lang.Integer agentId;
	/** 渠道ID */
	private java.lang.Integer channelId;
	/** 统计数据是否公开(0公开,1不公开) */
	private java.lang.String countOpen;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 头像url */
	private java.lang.String headimgUrl;
	/** 简介描述 */
	private java.lang.String introduce;
	/** 呢称 */
	private java.lang.String nickName;
	/** 插件图标 */
	private java.lang.String pluginIcon;
	/** 插件id */
	private java.lang.Integer pluginId;
	/** 插件名称 */
	private java.lang.String pluginName;
	/** 类型 */
	private java.lang.String type;

	/** 用户Id */
	private java.lang.Integer userId;

	public java.lang.String getAccessUrl() {
		return accessUrl;
	}

	public java.lang.String getActAccessUrl() {
		return actAccessUrl;
	}

	public java.lang.Integer getActBrowseNum() {
		return actBrowseNum;
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

	public java.lang.Integer getActJoinNum() {
		return actJoinNum;
	}

	public java.lang.String getActOpen() {
		return actOpen;
	}

	public java.lang.String getActTitle() {
		return actTitle;
	}

	public java.lang.String getAgentActOpen() {
		return agentActOpen;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.Integer getChannelId() {
		return channelId;
	}

	public java.lang.String getCountOpen() {
		return countOpen;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.String getHeadimgUrl() {
		return headimgUrl;
	}

	public java.lang.String getIntroduce() {
		return introduce;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public java.lang.String getPluginIcon() {
		return pluginIcon;
	}

	public java.lang.Integer getPluginId() {
		return pluginId;
	}

	public java.lang.String getPluginName() {
		return pluginName;
	}

	public java.lang.String getType() {
		return type;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setAccessUrl(java.lang.String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public void setActAccessUrl(java.lang.String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}

	public void setActBrowseNum(java.lang.Integer actBrowseNum) {
		this.actBrowseNum = actBrowseNum;
	}

	public void setActIcon(java.lang.String actIcon) {
		this.actIcon = actIcon;
	}

	public void setActId(java.lang.Integer actId) {
		this.actId = actId;
	}

	public void setActJoinNum(java.lang.Integer actJoinNum) {
		this.actJoinNum = actJoinNum;
	}

	public void setActOpen(java.lang.String actOpen) {
		this.actOpen = actOpen;
	}

	public void setActTitle(java.lang.String actTitle) {
		this.actTitle = actTitle;
	}

	public void setAgentActOpen(java.lang.String agentActOpen) {
		this.agentActOpen = agentActOpen;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setChannelId(java.lang.Integer channelId) {
		this.channelId = channelId;
	}

	public void setCountOpen(java.lang.String countOpen) {
		this.countOpen = countOpen;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public void setPluginIcon(java.lang.String pluginIcon) {
		this.pluginIcon = pluginIcon;
	}

	public void setPluginId(java.lang.Integer pluginId) {
		this.pluginId = pluginId;
	}

	public void setPluginName(java.lang.String pluginName) {
		this.pluginName = pluginName;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

}
