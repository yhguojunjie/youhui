package com.yoxi.hudongtui.vo.content;

import java.io.Serializable;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 活动推荐VO
 *
 * @author wql
 *
 * @Date 2015年3月25日
 *
 */
public class ActRecVO implements Serializable{
	
	private static final long serialVersionUID = -938412522505480184L;
	/** 活动ID */
	private java.lang.Integer actId;
	/** 活动图标 */
	private java.lang.String actIcon;
	/** 活动名称 */
	private java.lang.String actTitle;
	/** 参与人数 */
	private java.lang.Integer actJoinNum;
	/** 浏览数 */
	private java.lang.Integer actBrowseNum;
	/** 插件id */
	private java.lang.Integer pluginId;
	/** 活动所属用户Id */
	private java.lang.Integer userId;
	/** 头像url */
	private java.lang.String headimgUrl;
	/** 呢称 */
	private java.lang.String nickName;
	/** 插件名称 */
	private java.lang.String pluginName;
	/** 简介描述 */
	private java.lang.String introduce;
	/**活动访问路径-用于生成二维码*/
	private String actAccessUrl;
	/**活动访问路径-用于平台展示*/
	private String pcShowActAccUrl;
	/**创建时间*/
	private java.util.Date createTime;
	/** 类型 */
	private java.lang.String type;
	/** 活动访问页面地址 */
	private java.lang.String accessUrl;
	/**是否在平台公开(0公开,1不公开)*/
	private java.lang.String actOpen;
	/**统计数据是否公开(0公开,1不公开)*/
	private java.lang.String countOpen;
	/**是否在代表商平台下公开(0公开,1不公开)*/
	private java.lang.String agentActOpen;
	/** 插件图标 */
	private java.lang.String pluginIcon;
	/**代理商ID*/
	private java.lang.Integer agentId;
	/**渠道ID*/
	private java.lang.Integer channelId;
	/**演示活动id*/
	private java.lang.Integer showActId;
	/**演示活动地址*/
	private java.lang.String showUrl;
	/**模板过期时间*/
	private java.util.Date overdueTime;
	
	
	public java.lang.String getShowUrl() {
		return showUrl;
	}
	public void setShowUrl(java.lang.String showUrl) {
		this.showUrl = showUrl;
	}
	public java.lang.Integer getShowActId() {
		return showActId;
	}
	public void setShowActId(java.lang.Integer showActId) {
		this.showActId = showActId;
	}
	public java.lang.Integer getActId() {
		return actId;
	}
	public void setActId(java.lang.Integer actId) {
		this.actId = actId;
	}

	public java.lang.String getActTitle() {
		return actTitle;
	}
	public void setActTitle(java.lang.String actTitle) {
		this.actTitle = actTitle;
	}
	public java.lang.Integer getActJoinNum() {
		return actJoinNum;
	}
	public void setActJoinNum(java.lang.Integer actJoinNum) {
		this.actJoinNum = actJoinNum;
	}
	public java.lang.Integer getActBrowseNum() {
		return actBrowseNum;
	}
	public void setActBrowseNum(java.lang.Integer actBrowseNum) {
		this.actBrowseNum = actBrowseNum;
	}
	public java.lang.Integer getPluginId() {
		return pluginId;
	}
	public void setPluginId(java.lang.Integer pluginId) {
		this.pluginId = pluginId;
	}
	public java.lang.Integer getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.String getNickName() {
		return nickName;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	public java.lang.String getPluginName() {
		return pluginName;
	}
	public void setPluginName(java.lang.String pluginName) {
		this.pluginName = pluginName;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getActOpen() {
		return actOpen;
	}
	public void setActOpen(java.lang.String actOpen) {
		this.actOpen = actOpen;
	}
	public java.lang.String getCountOpen() {
		return countOpen;
	}
	public void setCountOpen(java.lang.String countOpen) {
		this.countOpen = countOpen;
	}
	public java.lang.String getAgentActOpen() {
		return agentActOpen;
	}
	public void setAgentActOpen(java.lang.String agentActOpen) {
		this.agentActOpen = agentActOpen;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	public java.lang.Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(java.lang.Integer channelId) {
		this.channelId = channelId;
	}
	public java.lang.String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(java.lang.String accessUrl) {
		this.accessUrl = accessUrl;
	}
	
	public java.lang.String getPluginIcon() {
		if(!StringUtils.isNullBlank(pluginIcon)){
			return ConvertUtil.procImgPath(pluginIcon);
		}
		return pluginIcon;
	}
	public void setPluginIcon(java.lang.String pluginIcon) {
		this.pluginIcon = pluginIcon;
	}
	
	public String getPcShowActAccUrl() {
		return pcShowActAccUrl;
	}
	public void setPcShowActAccUrl(String pcShowActAccUrl) {
		this.pcShowActAccUrl = pcShowActAccUrl;
	}
	
	public java.lang.String getActAccessUrl() {
		return actAccessUrl;
	}
	public void setActAccessUrl(java.lang.String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}
	
	public java.lang.String getIntroduce() {
		if(StringUtils.isNullBlank(introduce)){
			return "还没有简介";
		}
		return introduce;
	}
	
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	
	public java.lang.String getHeadimgUrl() {
		if(!StringUtils.isNullBlank(headimgUrl)){
			return ConvertUtil.procImgPath(headimgUrl);
		}
		return headimgUrl;
	}
	
	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}
	
	public java.lang.String getActIcon() {
		if(!StringUtils.isNullBlank(actIcon)){
			return ConvertUtil.procImgPath(actIcon);
		}
		return actIcon;
	}
	public void setActIcon(java.lang.String actIcon) {
		this.actIcon = actIcon;
	}
	public java.util.Date getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(java.util.Date overdueTime) {
		this.overdueTime = overdueTime;
	}
	
	
}
