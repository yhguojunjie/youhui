package com.yoxi.hudongtui.vo.plugin;

/**
 * 
 * 用户插件VO
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class UserPluginVo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 用户插件id 对应活动表中的userPluginId */
	private java.lang.Integer id;
	/** 用户Id */
	private java.lang.Integer userId;
	/** 插件id */
	private java.lang.Integer pluginId;
	/** 插件活动数 */
	private java.lang.Integer actNum;
	/** 创建(购买)时间 */
	private java.util.Date createTime;
	/** 过期时间 */
	private java.util.Date overdueTime;
	/** 插件名称 */
	private java.lang.String name;
	/** 插件图标 */
	private java.lang.String icon;
	/** 插件描述 */
	private java.lang.String description;
	/** 是否已过期 */
	private java.lang.String isOld;
	/** 开发者名称 */
	private java.lang.String nickName;
	/** 演示地址 */
	private java.lang.String showUrl;
	/**插件新增活动地址*/
	private java.lang.String actAddUrl;
	/**插件活动编辑地址*/
	private java.lang.String actEditUrl;
	/**快过期情况 */
	private java.lang.String overdueFlag;
	/** 开发者ID */
	private java.lang.Integer devId;

	public java.lang.Integer getDevId() {
		return devId;
	}

	public void setDevId(java.lang.Integer devId) {
		this.devId = devId;
	}

	public java.lang.String getOverdueFlag() {
		return overdueFlag;
	}

	public void setOverdueFlag(java.lang.String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	public java.lang.String getIsOld() {
		return isOld;
	}

	public void setIsOld(java.lang.String isOld) {
		this.isOld = isOld;
	}

	public java.lang.String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(java.lang.String showUrl) {
		this.showUrl = showUrl;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
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
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 用户Id
	 */
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 用户Id
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 插件id
	 */
	public java.lang.Integer getPluginId() {
		return this.pluginId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 插件id
	 */
	public void setPluginId(java.lang.Integer pluginId) {
		this.pluginId = pluginId;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 插件活动数
	 */
	public java.lang.Integer getActNum() {
		return this.actNum;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 插件活动数
	 */
	public void setActNum(java.lang.Integer actNum) {
		this.actNum = actNum;
	}

	/**
	 * 方法: 取得java.util.Date
	 * @return: java.util.Date 创建(购买)时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * @param: java.util.Date 创建(购买)时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 方法: 取得java.util.Date
	 * @return: java.util.Date 过期时间
	 */
	public java.util.Date getOverdueTime() {
		return this.overdueTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * @param: java.util.Date 过期时间
	 */
	public void setOverdueTime(java.util.Date overdueTime) {
		this.overdueTime = overdueTime;
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
	
}
