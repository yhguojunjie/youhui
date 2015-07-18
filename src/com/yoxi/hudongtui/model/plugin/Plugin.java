package com.yoxi.hudongtui.model.plugin;


/**
 * 
 * 插件
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class Plugin implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**id*/
	private java.lang.Integer id;
	/**插件名称*/
	private java.lang.String name;
	/**开发者userId(为平台用户id)*/
	private java.lang.Integer userId;
	/**插件图标*/
	private java.lang.String icon;
	/**插件描述*/
	private java.lang.String description;
	/**演示地址*/
	private java.lang.String showUrl;
	/**版本信息*/
	private java.lang.String version;
	/**版本信息*/
	private java.lang.String copyright;
	/**服务器路径未带参数*/
	private java.lang.String serverUrl;
	/**已购买人数*/
	private java.lang.Integer buyNum;
	/**试用总人数*/
	private java.lang.Integer tryoutNum;
	/**价格*/
	private java.lang.Double price;
	/**优惠、促销价格*/
	private java.lang.Double promPrice;
	/**插件类型(1 即插即用型，2用户定制)*/
	private java.lang.String type;
	/**使用说明*/
	private java.lang.String guide;
	/**有效期(以月为单位)*/
	private java.lang.Integer valid;
	/**源代码文件地址*/
	private java.lang.String filePath;
	/**视频录像地址*/
	private java.lang.String videoUrl;
	/**插件新增活动地址*/
	private java.lang.String actAddUrl;
	/**插件活动编辑地址*/
	private java.lang.String actEditUrl;
	/**活动访问地址*/
	private java.lang.String actAccessUrl;
	/**演示活动id*/
	private java.lang.Integer showActId;
	/**状态值(0 未发布，1已发布)*/
	private java.lang.String status;
	/**发布人userId(后台用户Id)*/
	private java.lang.Integer publisherId;
	/**发布时间*/
	private java.util.Date publishTime; 
	/**审核人用户Id(后台用户Id)*/
	private java.lang.Integer auditUserId;
	/**审核状态 0 待审核，1审核通过，2审核不通过*/
	private java.lang.String auditState;
	/**审核意见*/
	private java.lang.String auditDesc;
	/**审核时间*/
	private java.util.Date auditTime;
	/**上传时间*/
	private java.util.Date uploadTime;
	/**更新时间*/
	private java.util.Date updateTime;
	
	
	public Plugin() {}
	
	
	public Plugin(Integer id) {
		super();
		this.id = id;
	}


	public java.lang.String getGuide() {
		return guide;
	}

	public void setGuide(java.lang.String guide) {
		this.guide = guide;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  上传者userId
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  上传者userId
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上传时间
	 */
	public java.util.Date getUploadTime(){
		return this.uploadTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上传时间
	 */
	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime = uploadTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件图标
	 */
	public java.lang.String getIcon(){
		return this.icon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件图标
	 */
	public void setIcon(java.lang.String icon){
		this.icon = icon;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件描述
	 */
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  演示地址
	 */
	public java.lang.String getShowUrl(){
		return this.showUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  演示地址
	 */
	public void setShowUrl(java.lang.String showUrl){
		this.showUrl = showUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本信息
	 */
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本信息
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本信息
	 */
	public java.lang.String getCopyright(){
		return this.copyright;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本信息
	 */
	public void setCopyright(java.lang.String copyright){
		this.copyright = copyright;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务器路径未带参数
	 */
	public java.lang.String getServerUrl(){
		return this.serverUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务器路径未带参数
	 */
	public void setServerUrl(java.lang.String serverUrl){
		this.serverUrl = serverUrl;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已购买人数
	 */
	public java.lang.Integer getBuyNum(){
		return this.buyNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已购买人数
	 */
	public void setBuyNum(java.lang.Integer buyNum){
		this.buyNum = buyNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  价格
	 */
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  价格
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件类型(1 即插即用型，2用户定制)
	 */
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件类型(1 即插即用型，2用户定制)
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效期(以月为单位)
	 */
	public java.lang.Integer getValid(){
		return this.valid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效期(以月为单位)
	 */
	public void setValid(java.lang.Integer valid){
		this.valid = valid;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.util.Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}

	public java.lang.Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(java.lang.Integer publisherId) {
		this.publisherId = publisherId;
	}

	public java.lang.Double getPromPrice() {
		return promPrice;
	}

	public void setPromPrice(java.lang.Double promPrice) {
		this.promPrice = promPrice;
	}

	public java.lang.Integer getTryoutNum() {
		return tryoutNum;
	}

	public void setTryoutNum(java.lang.Integer tryoutNum) {
		this.tryoutNum = tryoutNum;
	}


	public java.util.Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	public java.lang.String getActAddUrl() {
		return actAddUrl;
	}


	public void setActAddUrl(java.lang.String actAddUrl) {
		this.actAddUrl = actAddUrl;
	}


	public java.lang.String getActEditUrl() {
		return actEditUrl;
	}


	public void setActEditUrl(java.lang.String actEditUrl) {
		this.actEditUrl = actEditUrl;
	}


	public java.lang.String getActAccessUrl() {
		return actAccessUrl;
	}


	public void setActAccessUrl(java.lang.String actAccessUrl) {
		this.actAccessUrl = actAccessUrl;
	}


	public java.lang.Integer getShowActId() {
		return showActId;
	}


	public void setShowActId(java.lang.Integer showActId) {
		this.showActId = showActId;
	}

	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	public java.lang.String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(java.lang.String videoUrl) {
		this.videoUrl = videoUrl;
	}


	public java.lang.Integer getAuditUserId() {
		return auditUserId;
	}


	public void setAuditUserId(java.lang.Integer auditUserId) {
		this.auditUserId = auditUserId;
	}


	public java.lang.String getAuditState() {
		return auditState;
	}


	public void setAuditState(java.lang.String auditState) {
		this.auditState = auditState;
	}


	public java.lang.String getAuditDesc() {
		return auditDesc;
	}


	public void setAuditDesc(java.lang.String auditDesc) {
		this.auditDesc = auditDesc;
	}


	public java.util.Date getAuditTime() {
		return auditTime;
	}


	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}
	
	
	
}
