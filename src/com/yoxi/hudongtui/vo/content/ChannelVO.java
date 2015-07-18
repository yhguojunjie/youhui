package com.yoxi.hudongtui.vo.content;

import java.io.Serializable;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 *  渠道VO
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public class ChannelVO implements Serializable {
	
	private static final long serialVersionUID = -3899140561777093703L;
	
	/**id*/
	private java.lang.Integer id;
	/**用户id(平台用户)*/
	private java.lang.Integer userId;
	/**渠道类型*/
	private java.lang.String type;
	/**渠道名称*/
	private java.lang.String name;
	/**LOGO*/
	private java.lang.String logo;
	/**简介*/
	private java.lang.String introduce;
	/**二维码*/
	private java.lang.String qrcode;
	/**网址*/
	private java.lang.String website;
	/**粉丝数*/
	private java.lang.String fansNum;
	/**发布价位 元/条*/
	private java.lang.Double price;
	/**联系人QQ号*/
	private java.lang.String qq;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.Integer getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getLogo() {
		if(!StringUtils.isNullBlank(logo)){
			return ConvertUtil.procImgPath(logo);
		}
		return logo;
	}
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}
	public java.lang.String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	public java.lang.String getQrcode() {
		return qrcode;
	}
	public void setQrcode(java.lang.String qrcode) {
		this.qrcode = qrcode;
	}
	public java.lang.String getWebsite() {
		return website;
	}
	public void setWebsite(java.lang.String website) {
		this.website = website;
	}
	public java.lang.String getFansNum() {
		return fansNum;
	}
	public void setFansNum(java.lang.String fansNum) {
		this.fansNum = fansNum;
	}
	public java.lang.Double getPrice() {
		return price;
	}
	public void setPrice(java.lang.Double price) {
		this.price = price;
	}
	public java.lang.String getQq() {
		return qq;
	}
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
