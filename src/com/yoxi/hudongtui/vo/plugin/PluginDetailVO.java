package com.yoxi.hudongtui.vo.plugin;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 插件VO
 * 
 * @author wql
 * 
 * 2014-11-12
 * 
 */
public class PluginDetailVO implements java.io.Serializable {

	private static final long serialVersionUID = 7668090900633567050L;
	/** id */
	private java.lang.Integer id;
	/** 插件名称 */
	private java.lang.String name;
	/** 上传者(开发者)userId */
	private java.lang.Integer userId;
	/** 开发者名称 */
	private java.lang.String nickName;
	/** 上传时间 */
	private java.util.Date uploadTime;
	/** 插件图标 */
	private java.lang.String icon;
	/** 插件描述 */
	private java.lang.String description;
	/** 已购买人数 */
	private java.lang.Integer buyNum;
	/**成本价*/
	private java.lang.Double price;
	/** 销售价格*/
	private java.lang.Double salePrice;
	/** 插件类型(1 即插即用型，2用户定制) */
	private java.lang.String type;
	/** 有效期(以月为单位) */
	private java.lang.Integer valid;
	/** 状态值(0 未发布，1已发布) */
	private java.lang.String status;
	/** 发布时间 */
	private java.util.Date publishTime;
	/** 试用总人数 */
	private java.lang.Integer tryoutNum;
	/**更新时间*/
	private java.util.Date updateTime;
	/**插件新增活动地址*/
	private java.lang.String actAddUrl;
	/**插件活动编辑地址*/
	private java.lang.String actEditUrl;
	/** 插件活动访问地址*/
	private java.lang.String actAccessUrl;
	/**演示活动id**/
	private java.lang.Integer showActId;
	/**使用说明*/
	private java.lang.String guide;
	/**视频录像地址*/
	private java.lang.String videoUrl;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**代理商是否上架状态 0 上架，1下架*/
	private java.lang.String onlineState;
	/**演示地址*/
	private java.lang.String showUrl;
	
	

	public java.lang.String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(java.lang.String showUrl) {
		this.showUrl = showUrl;
	}

	public java.lang.String getGuide() {
		return guide;
	}

	public void setGuide(java.lang.String guide) {
		this.guide = guide;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	private java.lang.Double promprice;

	public java.lang.Double getPromprice() {
		return promprice;
	}

	public void setPromprice(java.lang.Double promprice) {
		this.promprice = promprice;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public java.lang.Integer getTryoutNum() {
		if(tryoutNum != null){
			return (tryoutNum+1)*7;
		}
		return tryoutNum;
	}

	public void setTryoutNum(java.lang.Integer tryoutNum) {
		this.tryoutNum = tryoutNum;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer id
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 插件名称
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 插件名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 上传者userId
	 */
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 上传者userId
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	/**
	 * 方法: 取得java.util.Date
	 * @return: java.util.Date 上传时间
	 */
	public java.util.Date getUploadTime() {
		return this.uploadTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * @param: java.util.Date 上传时间
	 */
	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 插件图标
	 */
	public java.lang.String getIcon() {
		if(!StringUtils.isNullBlank(icon)){
			return ConvertUtil.procImgPath(icon);
		}
		return this.icon;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 插件图标
	 */
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 插件描述
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 插件描述
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}


	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 已购买人数
	 */
	public java.lang.Integer getBuyNum() {
		if(this.buyNum != null){
			return (this.buyNum+1)*7;
		}
		return this.buyNum;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 已购买人数
	 */
	public void setBuyNum(java.lang.Integer buyNum) {
		this.buyNum = buyNum;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 插件类型(1 即插即用型，2用户定制)
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 插件类型(1 即插即用型，2用户定制)
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 有效期(以月为单位)
	 */
	public java.lang.Integer getValid() {
		return this.valid;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 有效期(以月为单位)
	 */
	public void setValid(java.lang.Integer valid) {
		this.valid = valid;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.util.Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}

	public java.lang.String getActAddUrl() {
		return actAddUrl;
	}

	public void setActAddUrl(java.lang.String actAddUrl) {
		this.actAddUrl = actAddUrl;
	}

	public java.lang.String getActEditUrl() {
		return actEditUrl;
	}

	public void setActEditUrl(java.lang.String actEditUrl) {
		this.actEditUrl = actEditUrl;
	}

	public java.lang.String getActAccessUrl() {
		return actAccessUrl;
	}

	public void setActAccessUrl(java.lang.String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}

	public java.lang.Integer getShowActId() {
		return showActId;
	}

	public void setShowActId(java.lang.Integer showActId) {
		this.showActId = showActId;
	}

	public java.lang.Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}

	public java.lang.String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(java.lang.String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public java.lang.String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(java.lang.String onlineState) {
		this.onlineState = onlineState;
	}
	
	
}
