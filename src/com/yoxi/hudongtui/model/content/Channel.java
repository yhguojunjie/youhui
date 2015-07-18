package com.yoxi.hudongtui.model.content;

/**
 * 
 * 渠道
 * 
 * @author wql
 * 
 * @Date 2015年3月10日
 * 
 */
public class Channel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6517237083955879193L;

	/** 所属代理商id */
	private java.lang.Integer agentId;
	/** 关注链接 */
	private java.lang.String attentionLink;
	/** 审核人id */
	private java.lang.Integer auditorId;

	/** 审核状态 0 未审核，1已审核 */
	private java.lang.String auditState;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 下载链接 */
	private java.lang.String downloadLink;

	/** email */
	private java.lang.String email;

	/** 粉丝数 */
	private java.lang.Integer fansNum;

	/** id */
	private java.lang.Integer id;

	/** 简介 */
	private java.lang.String introduce;

	/** LOGO */
	private java.lang.String logo;

	/** 微信号 */
	private java.lang.String microNum;

	/** 联系人电话号 */
	private java.lang.String mobile;

	/** 渠道名称 */
	private java.lang.String name;
	/** 发布价位 元/条 */
	private java.lang.Double price;

	/** 联系人QQ号 */
	private java.lang.String qq;

	/** 二维码 */
	private java.lang.String qrcode;

	/** 顺序 */
	private java.lang.Integer seq;

	/** 渠道类型 */
	private java.lang.String type;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 用户id(平台用户) */
	private java.lang.Integer userId;

	/** 网址 */
	private java.lang.String website;
	public java.lang.Integer getAgentId() {
		return agentId;
	}
	public java.lang.String getAttentionLink() {
		return attentionLink;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 审核人id
	 */
	public java.lang.Integer getAuditorId() {
		return this.auditorId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 审核状态 0 未审核，1已审核
	 */
	public java.lang.String getAuditState() {
		return this.auditState;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.String getDownloadLink() {
		return downloadLink;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public java.lang.Integer getFansNum() {
		return fansNum;
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
	 * @return: java.lang.String 简介
	 */
	public java.lang.String getIntroduce() {
		return this.introduce;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String LOGO
	 */
	public java.lang.String getLogo() {
		return this.logo;
	}

	public java.lang.String getMicroNum() {
		return microNum;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 渠道名称
	 */
	public java.lang.String getName() {
		return this.name;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 联系人QQ号
	 */
	public java.lang.String getQq() {
		return this.qq;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 二维码
	 */
	public java.lang.String getQrcode() {
		return this.qrcode;
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
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 渠道类型
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 网址
	 */
	public java.lang.String getWebsite() {
		return this.website;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setAttentionLink(java.lang.String attentionLink) {
		this.attentionLink = attentionLink;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 审核人id
	 */
	public void setAuditorId(java.lang.Integer auditorId) {
		this.auditorId = auditorId;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 审核状态 0 未审核，1已审核
	 */
	public void setAuditState(java.lang.String auditState) {
		this.auditState = auditState;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setDownloadLink(java.lang.String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public void setFansNum(java.lang.Integer fansNum) {
		this.fansNum = fansNum;
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
	 * @param: java.lang.String 简介
	 */
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String LOGO
	 */
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}

	public void setMicroNum(java.lang.String microNum) {
		this.microNum = microNum;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 渠道名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 联系人QQ号
	 */
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 二维码
	 */
	public void setQrcode(java.lang.String qrcode) {
		this.qrcode = qrcode;
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
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 渠道类型
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 网址
	 */
	public void setWebsite(java.lang.String website) {
		this.website = website;
	}
}
