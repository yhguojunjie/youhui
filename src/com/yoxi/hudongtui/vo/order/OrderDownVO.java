package com.yoxi.hudongtui.vo.order;

import com.yoxi.hudongtui.vo.ResponseCode;

/**
 * 
 * 下订单
 * 
 * @author wql
 *
 * 2014-11-15
 * 
 */
public class OrderDownVO extends ResponseCode {

	private static final long serialVersionUID = -8935451420195109753L;

	/**定单id*/
	private Integer orderId;
	
	/**产品id*/
	private Integer productId;
	
	/**产品类型 1 购买新模板，2模板续费购买*/
	private String productType;
	
	/**产品名称*/
	private String productName;
	
	/**用户id*/
	private Integer userId;
	
	/**金额*/
	private Double charge;
	
	/**代币*/
	private Double repreCoin;
	
	/**支付类型*/
	private String purchaseType;
	
	/**来源*/
	private String sourceType;
	
	/**鉴权串*/
	private String authStr;
	
	/**异步回调地址*/
	private String notifyUrl;
	
	/**是否选择代币支付 0 否,1是*/
	private String isRepreCoin;
	
	/**购买月数*/
	private Integer buyMon;
	
	/**代理商id*/
	private java.lang.Integer agentId;
	
	/**续费购买的对应用户插件id*/
	private java.lang.Integer userPluginId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getRepreCoin() {
		return repreCoin;
	}

	public void setRepreCoin(Double repreCoin) {
		this.repreCoin = repreCoin;
	}


	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getIsRepreCoin() {
		return isRepreCoin;
	}

	public void setIsRepreCoin(String isRepreCoin) {
		this.isRepreCoin = isRepreCoin;
	}

	public String getAuthStr() {
		return authStr;
	}

	public void setAuthStr(String authStr) {
		this.authStr = authStr;
	}

	public Integer getBuyMon() {
		return buyMon;
	}

	public void setBuyMon(Integer buyMon) {
		this.buyMon = buyMon;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public java.lang.Integer getUserPluginId() {
		return userPluginId;
	}

	public void setUserPluginId(java.lang.Integer userPluginId) {
		this.userPluginId = userPluginId;
	}
	
}
