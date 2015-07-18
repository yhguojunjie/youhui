package com.yoxi.hudongtui.vo.user;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 渠道VO
 * 
 * @author gjj
 * 
 * @Date 2015年4月1日
 * 
 */
public class ChannelVO {

	/** 关注链接 */
	private java.lang.String attentionLink;
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

	/** 渠道类型 */
	private java.lang.String type;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 用户id(平台用户) */
	private java.lang.Integer userId;

	/** 网址 */
	private java.lang.String website;

	public java.lang.String getAttentionLink() {
		return attentionLink;
	}

	public java.util.Date getCreateTime() {
		return createTime;
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

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getIntroduce() {
		return introduce;
	}

	public java.lang.String getLogo() {
		if (!StringUtils.isNullBlank(logo)) {
			return ConvertUtil.procImgPath(logo);
		}
		return logo;
	}

	public java.lang.String getMicroNum() {
		return microNum;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public java.lang.String getName() {
		return name;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	public java.lang.String getQq() {
		return qq;
	}

	public java.lang.String getQrcode() {
		return qrcode;
	}

	public java.lang.String getType() {
		return type;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public java.lang.String getWebsite() {
		return website;
	}

	public void setAttentionLink(java.lang.String attentionLink) {
		this.attentionLink = attentionLink;
	}

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

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}

	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}

	public void setMicroNum(java.lang.String microNum) {
		this.microNum = microNum;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	public void setQrcode(java.lang.String qrcode) {
		this.qrcode = qrcode;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public void setWebsite(java.lang.String website) {
		this.website = website;
	}

}
