package com.yoxi.hudongtui.model.content;

/**
 * 
 * 品牌
 * 
 * @author wql
 * 
 * @Date 2015年3月19日
 * 
 */
public class Brand implements java.io.Serializable {

	private static final long serialVersionUID = -5598945762704492191L;

	/** 创建时间 */
	private java.util.Date createTime;
	/** 用户头像 */
	private java.lang.String headimgUrl;
	/** id */
	private java.lang.Integer id;
	/** logo */
	private java.lang.String logo;
	/** 用户呢称 */
	private java.lang.String nickName;
	/** 顺序 */
	private java.lang.Integer seq;
	/** 更新时间 */
	private java.util.Date updateTime;

	/** 用户id */
	private java.lang.Integer userId;

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.String getHeadimgUrl() {
		return headimgUrl;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer id
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String logo
	 */
	public java.lang.String getLogo() {
		return this.logo;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 用户呢称
	 */
	public java.lang.String getNickName() {
		return this.nickName;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 顺序
	 */
	public java.lang.Integer getSeq() {
		return this.seq;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 用户id
	 */
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String logo
	 */
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 用户呢称
	 */
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 顺序
	 */
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 用户id
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
}
