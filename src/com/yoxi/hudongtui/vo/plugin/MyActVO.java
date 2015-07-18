package com.yoxi.hudongtui.vo.plugin;

import java.io.Serializable;
import java.util.Date;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 *
 * 我的活动VO
 *
 * @author wql
 *
 * @Date 2015年3月27日
 *
 */
public class MyActVO implements Serializable {
	
	private static final long serialVersionUID = -8186729953347416213L;
	
	/**用户使用模板id*/
	private Integer userPluginId;
	/**活动id*/
	private Integer actId;
	/**活动标题*/
	private String actTitle;
	/**活动图标*/
	private String actIcon;
	/**活动访问路径-用于生成二维码*/
	private String actAccessUrl;
	/**活动访问路径-用于平台展示*/
	private String pcShowActAccUrl;
	/**活动新增地址*/
	private String actAddUrl;
	/**活动编辑地址*/
	private String actEditUrl;
	/**活动浏览量*/
	private Integer browseNum;
	/**活动参与人数*/
	private Integer joinNum;
	/**活动开始时间*/
	private Date startTime;
	/**活动结束时间*/
	private Date endTime;
	/**模板(插件)过期时间*/
	private Date overdueTime;
	/**插件id*/
	private Integer pluginId;
	/**插件图标*/
	private String pluginIcon;
	/**插件名称*/
	private String pluginName;
	/**插件描述*/
	private String pluginDesc;
	/**活动状态*/
	private String actState;
	/**模板状态*/
	private String pluginState;
	
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public String getActTitle() {
		return actTitle;
	}
	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}
	public String getActIcon() {
		if(!StringUtils.isNullBlank(actIcon)){
			return ConvertUtil.procImgPath(actIcon);
		}
		return actIcon;
	}
	public void setActIcon(String actIcon) {
		this.actIcon = actIcon;
	}
	public String getActAccessUrl() {
		return actAccessUrl;
	}
	public void setActAccessUrl(String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}
	public String getActAddUrl() {
		actAddUrl = WebApplicationUtils.getBasePath() + "/"+actAddUrl+"/"+this.getUserPluginId();
		return actAddUrl;
	}
	public void setActAddUrl(String actAddUrl) {
		this.actAddUrl = actAddUrl;
	}
	public String getActEditUrl() {
		actEditUrl = WebApplicationUtils.getBasePath() +"/"+actEditUrl+"/"+this.getActId();
		return actEditUrl;
	}
	public void setActEditUrl(String actEditUrl) {
		this.actEditUrl = actEditUrl;
	}
	public Integer getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	public Integer getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
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
	public Date getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(Date overdueTime) {
		this.overdueTime = overdueTime;
	}
	public Integer getPluginId() {
		return pluginId;
	}
	public void setPluginId(Integer pluginId) {
		this.pluginId = pluginId;
	}
	public String getPluginIcon() {
		if(!StringUtils.isNullBlank(pluginIcon)){
			return ConvertUtil.procImgPath(pluginIcon);
		}
		return pluginIcon;
	}
	public void setPluginIcon(String pluginIcon) {
		this.pluginIcon = pluginIcon;
	}
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getPluginDesc() {
		return pluginDesc;
	}
	public void setPluginDesc(String pluginDesc) {
		this.pluginDesc = pluginDesc;
	}
	public String getActState() {
		return actState;
	}
	public void setActState(String actState) {
		this.actState = actState;
	}
	public String getPluginState() {
		return pluginState;
	}
	public void setPluginState(String pluginState) {
		this.pluginState = pluginState;
	}
	public Integer getUserPluginId() {
		return userPluginId;
	}
	public void setUserPluginId(Integer userPluginId) {
		this.userPluginId = userPluginId;
	}
	public String getPcShowActAccUrl() {
		return pcShowActAccUrl;
	}
	public void setPcShowActAccUrl(String pcShowActAccUrl) {
		this.pcShowActAccUrl = pcShowActAccUrl;
	}
	
}
