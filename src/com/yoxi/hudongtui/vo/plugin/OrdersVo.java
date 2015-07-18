package com.yoxi.hudongtui.vo.plugin;

/**
 * 
 * 我的订单记录
 * 
 * @author gjj
 * 
 *         2015-3-2
 * 
 */
public class OrdersVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8322214600438881647L;
	/** 代理商Id */
	private java.lang.Integer agentId;
	/** 购买数量 */
	private java.lang.Integer buyNum;

	/** 实际支付金额 */
	private java.lang.Double charge;

	/** 成本价 */
	private java.lang.Double costPrice;

	/** id */
	private java.lang.Integer id;

	/** 下单时间(表记录生成时间) */
	private java.lang.String orderTime;

	/** 原来要付的总金额 */
	private java.lang.Double payAll;

	/** 支付状态(0：未支付、1：已支付、2：支付失败) */
	private java.lang.String payOrderStatus;

	/** 支付交易号(第三方订单号) */
	private java.lang.String payTradeNo;

	/** 产品图标 */
	private java.lang.String productIcon;

	/** 产品id */
	private java.lang.Integer productId;

	/** 产品名称 */
	private java.lang.String productName;

	/** 产品类型 */
	private java.lang.String productType;

	/** 购买人userId */
	private java.lang.Integer purchaserId;

	/** 支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式) */
	private java.lang.String purchaseType;
	
	/** 代币支付金额 */
	private java.lang.Double repreCoin;

	/** 代理商定价 */
	private java.lang.Double salePrice;

	/** 0 进行中，1关闭 */
	private java.lang.String tradeState;

	/** 用户Id */
	private java.lang.Integer userId;

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.Integer getBuyNum() {
		return buyNum;
	}

	public java.lang.Double getCharge() {
		return charge;
	}

	public java.lang.Double getCostPrice() {
		return costPrice;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer id
	 */

	public java.lang.Integer getId() {
		return this.id;
	}

	public java.lang.String getOrderTime() {
		return orderTime;
	}

	public java.lang.Double getPayAll() {
		return payAll;
	}

	public java.lang.String getPayOrderStatus() {
		return payOrderStatus;
	}

	public java.lang.String getPayTradeNo() {
		return payTradeNo;
	}

	public java.lang.String getProductIcon() {
		return productIcon;
	}

	public java.lang.Integer getProductId() {
		return productId;
	}

	public java.lang.String getProductName() {
		return productName;
	}

	public java.lang.String getProductType() {
		return productType;
	}

	public java.lang.Integer getPurchaserId() {
		return purchaserId;
	}

	public java.lang.String getPurchaseType() {
		return purchaseType;
	}

	public java.lang.Double getRepreCoin() {
		return repreCoin;
	}

	public java.lang.Double getSalePrice() {
		return salePrice;
	}

	public java.lang.String getTradeState() {
		return tradeState;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setBuyNum(java.lang.Integer buyNum) {
		this.buyNum = buyNum;
	}

	public void setCharge(java.lang.Double charge) {
		this.charge = charge;
	}

	public void setCostPrice(java.lang.Double costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setOrderTime(java.lang.String orderTime) {
		this.orderTime = orderTime;
	}

	public void setPayAll(java.lang.Double payAll) {
		this.payAll = payAll;
	}

	public void setPayOrderStatus(java.lang.String payOrderStatus) {
		this.payOrderStatus = payOrderStatus;
	}

	public void setPayTradeNo(java.lang.String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	public void setProductIcon(java.lang.String productIcon) {
		this.productIcon = productIcon;
	}

	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}

	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}

	public void setProductType(java.lang.String productType) {
		this.productType = productType;
	}

	public void setPurchaserId(java.lang.Integer purchaserId) {
		this.purchaserId = purchaserId;
	}

	public void setPurchaseType(java.lang.String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public void setRepreCoin(java.lang.Double repreCoin) {
		this.repreCoin = repreCoin;
	}

	public void setSalePrice(java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}

	public void setTradeState(java.lang.String tradeState) {
		this.tradeState = tradeState;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

}
