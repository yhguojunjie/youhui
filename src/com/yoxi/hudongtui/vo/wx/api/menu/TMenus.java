package com.yoxi.hudongtui.vo.wx.api.menu;

import java.util.Date;

/**
 * 微信菜单表
 * 
 * @author liyh
 * @date 2014-6-10下午2:20:06
 */
public class TMenus {
	/**
	 * 菜单Id
	 */
	private Integer mId;
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	private String name;
	/**
	 * 菜单的响应动作类型，目前有click、view两种类型
	 */
	private String type;
	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节
	 */
	private String menuKey;
	/**
	 * view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节
	 */
	private String url;
	/**
	 * 父菜单
	 */
	private int parentMenuId;
	/**
	 * 资源Id
	 */
	private int resourceId;
	/**
	 * 资源类型(1、文本，2、图片，3、语音，4、视频，5、地理位置)
	 */
	private int resourceType;
	/**
	 * 描述
	 */
	private String memo;
	/**
	 * 排序
	 */
	private int sequence;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
