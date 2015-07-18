package com.yoxi.hudongtui.vo.user;

import java.io.Serializable;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 * 
 * 开发者信息VO
 *
 * @author wql
 *
 * @Date 2015年3月23日
 *
 */
public class DevVO implements Serializable {
	
	private static final long serialVersionUID = -5981813335952690524L;
	/** userId */
	private java.lang.Integer userId;
	/** 呢称 */
	private java.lang.String nickName;
	/** 头像url */
	private java.lang.String headimgUrl;
	/** email */
	private java.lang.String email;
	/** 手机号 */
	private java.lang.String mobile;
	/** 手机号是否公众(0不公开,1公开) */
	private java.lang.String mobileOpen;
	/** qq号码 */
	private java.lang.String qqAccount;
	/** qq号是否公众(0不公开,1公开) */
	private java.lang.String qqOpen;
	/** 微信账号 */
	private java.lang.String weixinAccount;
	/** 微信号是否公众(0不公开,1公开) */
	private java.lang.String weixinOpen;
	/** 性别(0,男性 1 女性) */
	private java.lang.String sex;
	/** 简介描述 */
	private java.lang.String introduce;
	/** 省 */
	private java.lang.String province;
	/** 市 */
	private java.lang.String city;
	/** 区 */
	private java.lang.String district;
	
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
	public java.lang.String getHeadimgUrl() {
		if(!StringUtils.isNullBlank(headimgUrl)){
			return ConvertUtil.procImgPath(headimgUrl);
		}
		return headimgUrl;
	}
	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.lang.String getMobile() {
		return mobile;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	public java.lang.String getMobileOpen() {
		return mobileOpen;
	}
	public void setMobileOpen(java.lang.String mobileOpen) {
		this.mobileOpen = mobileOpen;
	}
	public java.lang.String getSex() {
		return sex;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	public java.lang.String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	public java.lang.String getProvince() {
		return province;
	}
	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	public java.lang.String getCity() {
		return city;
	}
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	public java.lang.String getDistrict() {
		return district;
	}
	public void setDistrict(java.lang.String district) {
		this.district = district;
	}
	public java.lang.String getQqAccount() {
		return qqAccount;
	}
	public void setQqAccount(java.lang.String qqAccount) {
		this.qqAccount = qqAccount;
	}
	public java.lang.String getQqOpen() {
		return qqOpen;
	}
	public void setQqOpen(java.lang.String qqOpen) {
		this.qqOpen = qqOpen;
	}
	public java.lang.String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(java.lang.String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	public java.lang.String getWeixinOpen() {
		return weixinOpen;
	}
	public void setWeixinOpen(java.lang.String weixinOpen) {
		this.weixinOpen = weixinOpen;
	}
	
}
