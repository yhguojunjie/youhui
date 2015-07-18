package com.yoxi.hudongtui.model.user;

/**
 * 
 * 用户交易记录
 * 
 * @author wql
 * 
 * 2014-11-12
 * 
 */
public class UserTradeLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** id */
	private java.lang.Integer id;
	/** 用户Id */
	private java.lang.Integer userId;
	/**交易类型(1购买，2获得代币)*/
	private java.lang.String tradeType;
	/**产品id*/
	private java.lang.Integer productId;
	/** 产品类型 */
	private java.lang.String productType;
	/** 金额 */
	private java.lang.Double amount;
	/** 订单id(购买情况下) */
	private java.lang.Integer orderId;
	/** 交易时间 */
	private java.util.Date createTime;
	/** 来源(1,pc 2,微信) */
	private java.lang.String sourceType;
	/**  描述 */
	private java.lang.String description;
	/**  支付类型 */
	private java.lang.String purchaseType;
	/**代理商Id*/
	private java.lang.Integer agentId;

	public java.lang.String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(java.lang.String purchaseType) {
		this.purchaseType = purchaseType;
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
	 * 方法: 取得java.lang.Double
	 * @return: java.lang.Double 金额
	 */
	public java.lang.Double getAmount() {
		return this.amount;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * @param: java.lang.Double 金额
	 */
	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * @return: java.lang.Integer 订单id(购买情况下)
	 */
	public java.lang.Integer getOrderId() {
		return this.orderId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * @param: java.lang.Integer 订单id(购买情况下)
	 */
	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 方法: 取得java.util.Date
	 * @return: java.util.Date 交易时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * @param: java.util.Date 交易时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.String getTradeType() {
		return tradeType;
	}

	public void setTradeType(java.lang.String tradeType) {
		this.tradeType = tradeType;
	}

	public java.lang.String getSourceType() {
		return sourceType;
	}

	public void setSourceType(java.lang.String sourceType) {
		this.sourceType = sourceType;
	}

	public java.lang.Integer getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}

	public java.lang.String getProductType() {
		return productType;
	}

	public void setProductType(java.lang.String productType) {
		this.productType = productType;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	
}
