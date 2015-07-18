package com.yoxi.hudongtui.model.agent;


/**
 * 
 * 代理商定单
 * 
 * @author wql
 *
 * 2014-11-12
 * 
 */
public class AgentOrder implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8890361408248918313L;
	/**定单Id*/
	private java.lang.Integer id;
	/**用户Id*/
	private java.lang.Integer userId;
	/**插件Id*/
	private java.lang.Integer productId;
	/**产品种类 插件(1) */
	private java.lang.String productType;
	/**支付金额*/
	private java.lang.Double charge;
	/**代码支付金额*/
	private java.lang.Double repreCoin;
	/**购买人userId*/
	private java.lang.Integer purchaserId;
	/**支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)*/
	private java.lang.String purchaseType;
	/**下单时间*/
	private java.util.Date orderTime;
	/**支付交易状态(0：未支付、1：已支付、2：支付失败)*/
	private java.lang.String payOrderStatus;
	/**支付交易号(第三方订单号)*/
	private java.lang.String payTradeNo;
	/**支付通知时间*/
	private java.util.Date payNoticeTime;
	/**支付来源(1PC,2,微信)*/
	private java.lang.String sourceType;
	/**产品开始使用时间*/
	private java.util.Date startDate;
	/**产品结束时间*/
	private java.util.Date endDate;
	/**代理商销售定价*/
	private java.lang.Double salePrice;
	/**成本价*/
	private java.lang.Double costPrice;
	/**购买数量*/
	private java.lang.Integer buyNum;
	/**交易状态*/
	private java.lang.String tradeState;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**用户插件id**/
	private java.lang.Integer userPluginId;
	/**收款账号*/
	private java.lang.String sellerAccount;
	
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}


	public java.lang.Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(java.lang.Double costPrice) {
		this.costPrice = costPrice;
	}

	public java.lang.Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(java.lang.Integer buyNum) {
		this.buyNum = buyNum;
	}

	public java.lang.String getTradeState() {
		return tradeState;
	}

	public void setTradeState(java.lang.String tradeState) {
		this.tradeState = tradeState;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户Id
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户Id
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  支付金额
	 */
	public java.lang.Double getCharge(){
		return this.charge;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  支付金额
	 */
	public void setCharge(java.lang.Double charge){
		this.charge = charge;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  购买人userId
	 */
	public java.lang.Integer getPurchaserId(){
		return this.purchaserId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  购买人userId
	 */
	public void setPurchaserId(java.lang.Integer purchaserId){
		this.purchaserId = purchaserId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)
	 */
	public java.lang.String getPurchaseType(){
		return this.purchaseType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)
	 */
	public void setPurchaseType(java.lang.String purchaseType){
		this.purchaseType = purchaseType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单时间
	 */
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单时间
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付交易状态(0：未支付、1：已支付、2：支付失败)
	 */
	public java.lang.String getPayOrderStatus(){
		return this.payOrderStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付交易状态(0：未支付、1：已支付、2：支付失败)
	 */
	public void setPayOrderStatus(java.lang.String payOrderStatus){
		this.payOrderStatus = payOrderStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付交易号(第三方订单号)
	 */
	public java.lang.String getPayTradeNo(){
		return this.payTradeNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付交易号(第三方订单号)
	 */
	public void setPayTradeNo(java.lang.String payTradeNo){
		this.payTradeNo = payTradeNo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  支付通知时间
	 */
	public java.util.Date getPayNoticeTime(){
		return this.payNoticeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  支付通知时间
	 */
	public void setPayNoticeTime(java.util.Date payNoticeTime){
		this.payNoticeTime = payNoticeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付来源(1PC,2,微信)
	 */
	public java.lang.String getSourceType(){
		return this.sourceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付来源(1PC,2,微信)
	 */
	public void setSourceType(java.lang.String sourceType){
		this.sourceType = sourceType;
	}

	public java.lang.Integer getProductId() {
		return productId;
	}

	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}

	public java.lang.Double getRepreCoin() {
		return repreCoin;
	}

	public void setRepreCoin(java.lang.Double repreCoin) {
		this.repreCoin = repreCoin;
	}

	public java.lang.String getProductType() {
		return productType;
	}

	public void setProductType(java.lang.String productType) {
		this.productType = productType;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public java.lang.Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}

	public java.lang.Integer getUserPluginId() {
		return userPluginId;
	}

	public void setUserPluginId(java.lang.Integer userPluginId) {
		this.userPluginId = userPluginId;
	}

	public java.lang.String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(java.lang.String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	
}
