package com.yoxi.hudongtui.vo.content;

import java.io.Serializable;

import com.yoxi.hudongtui.utils.common.ConvertUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;

/**
 *
 * 插件推荐VO
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public class PluginRecVO implements Serializable{
	
	private static final long serialVersionUID = -5809490903665533715L;
	/** 已购买人数 */
	private java.lang.Integer buyNum;
	/** 插件描述 */
	private java.lang.String description;
	/** 插件图标 */
	private java.lang.String icon;
	/** 插件id */
	private java.lang.Integer id;
	/** 插件名称 */
	private java.lang.String name;
	/** 开发者名称 */
	private java.lang.String nickName;
	/** 发布时间 */
	private java.util.Date publishTime;
	/** 销售价格*/
	private java.lang.Double salePrice;
	/**演示活动id**/
	private java.lang.Integer showActId;
	/** 状态值(0 未发布，1已发布) */
	private java.lang.String status;
	/** 试用总人数 */
	private java.lang.Integer tryoutNum;
	/** 插件类型(1 即插即用型，2用户定制) */
	private java.lang.String type;
	/**更新时间*/
	private java.util.Date updateTime;
	/** 上传时间 */
	private java.util.Date uploadTime;
	/** 上传者(开发者)userId */
	private java.lang.Integer userId;
	/** 有效期(以月为单位) */
	private java.lang.Integer valid;
	
	public java.lang.Integer getBuyNum() {
		if(this.buyNum != null){
			return (this.buyNum+1)*7;
		}
		return this.buyNum;
	}
	public java.lang.String getDescription() {
		return description;
	}
	public java.lang.String getIcon() {
		if(!StringUtils.isNullBlank(icon)){
			return ConvertUtil.procImgPath(icon);
		}
		return icon;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public java.lang.String getName() {
		return name;
	}
	public java.lang.String getNickName() {
		return nickName;
	}
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	public java.lang.Double getSalePrice() {
		return salePrice;
	}
	public java.lang.Integer getShowActId() {
		return showActId;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public java.lang.Integer getTryoutNum() {
		if(tryoutNum != null){
			return (tryoutNum+1)*7;
		}
		return tryoutNum;
	}
	public java.lang.String getType() {
		return type;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public java.util.Date getUploadTime() {
		return uploadTime;
	}
	public java.lang.Integer getUserId() {
		return userId;
	}
	public java.lang.Integer getValid() {
		return valid;
	}
	public void setBuyNum(java.lang.Integer buyNum) {
		this.buyNum = buyNum;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	public void setSalePrice(java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}
	public void setShowActId(java.lang.Integer showActId) {
		this.showActId = showActId;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public void setTryoutNum(java.lang.Integer tryoutNum) {
		this.tryoutNum = tryoutNum;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	public void setValid(java.lang.Integer valid) {
		this.valid = valid;
	}
	
	
}
