package com.yoxi.hudongtui.vo.plugin;

/**
 * 
 * 礼物
 * 
 * @author wql
 * 
 */
public class GiftVo implements java.io.Serializable {

	private static final long serialVersionUID = -6657034777377055002L;

	/** id */
	private java.lang.Integer id;
	/** 类型(1代币 2插件 3话费) */
	private java.lang.String type;
	/** 对象id(比如插件id) */
	private java.lang.Integer objectId;
	/** 使用期限(如插件使用长度，以多少个月为单位) */
	private java.lang.Integer valid;
	/** 价格 */
	private java.lang.Double cost;
	/** 数量 */
	private java.lang.Integer count;
	/** 名称 */
	private java.lang.String name;
	/** 图片 */
	private java.lang.String icon;
	/** 描述 */
	private java.lang.String description;
	/** 创建时间 */
	private java.util.Date createTime;

	private java.lang.String drawUserName;
	private java.lang.String drawUserheadimgUrl;

	public java.lang.String getDrawUserheadimgUrl() {
		return drawUserheadimgUrl;
	}

	public void setDrawUserheadimgUrl(java.lang.String drawUserheadimgUrl) {
		this.drawUserheadimgUrl = drawUserheadimgUrl;
	}

	public java.lang.String getDrawUserName() {
		return drawUserName;
	}

	public void setDrawUserName(java.lang.String drawUserName) {
		this.drawUserName = drawUserName;
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
	 * @return: java.lang.String 类型(1代币 2插件 3话费)
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 类型(1代币 2插件 3话费)
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 对象id(比如插件id)
	 */
	public java.lang.Integer getObjectId() {
		return this.objectId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 对象id(比如插件id)
	 */
	public void setObjectId(java.lang.Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 使用期限(如插件使用长度，以多少个月为单位)
	 */
	public java.lang.Integer getValid() {
		return this.valid;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 使用期限(如插件使用长度，以多少个月为单位)
	 */
	public void setValid(java.lang.Integer valid) {
		this.valid = valid;
	}

	/**
	 * 方法: 取得java.lang.Double
	 * @return: java.lang.Double 价格
	 */
	public java.lang.Double getCost() {
		return this.cost;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * @param: java.lang.Double 价格
	 */
	public void setCost(java.lang.Double cost) {
		this.cost = cost;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 数量
	 */
	public java.lang.Integer getCount() {
		return this.count;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 数量
	 */
	public void setCount(java.lang.Integer count) {
		this.count = count;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 名称
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 图片
	 */
	public java.lang.String getIcon() {
		return this.icon;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 图片
	 */
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	/**
	 * 方法: 取得java.lang.String
	 * @return: java.lang.String 描述
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * 方法: 设置java.lang.String
	 * @param: java.lang.String 描述
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * 方法: 取得java.util.Date
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
}
