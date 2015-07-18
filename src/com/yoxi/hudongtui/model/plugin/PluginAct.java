package com.yoxi.hudongtui.model.plugin;


/**
 * 
 * 插件活动
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class PluginAct implements java.io.Serializable {
	
	private static final long serialVersionUID = -8206860890038578126L;
	
	/**id*/
	private java.lang.Integer id;
	/**插件Id*/
	private java.lang.Integer userPluginId;
	/**活动名称*/
	private java.lang.String title;
	/**活动图标*/
	private java.lang.String icon;
	/**参与人数*/
	private java.lang.Integer joinNum;
	/**浏览数*/
	private java.lang.Integer browseNum;
	/**开始时间*/
	private java.util.Date startTime;
	/**结束时间*/
	private java.util.Date endTime;
	/**活动访问页面地址*/
	private java.lang.String accessUrl;
	/**是否申请推广*/
	private java.lang.String aplyRecommend;
	/**排序*/
	private java.lang.Integer sequence;
	/**数据状态 0正常，1删除*/
	private java.lang.String dataStatus;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**二维码图片地址*/
	private java.lang.String qrcodeUrl;
	/**是否在平台公开(0公开,1不公开)*/
	private java.lang.String actOpen;
	/**是否在代表商平台下公开(0公开,1不公开)*/
	private java.lang.String agentActOpen;
	/**统计数据是否公开(0公开,1不公开)*/
	private java.lang.String countOpen;
	/**活动类型(0站内,1站外)*/
	private java.lang.String type;
	/**用户ID*/
	private java.lang.Integer userId;
	/**插件ID*/
	private java.lang.Integer pluginId;
	/**代理商id*/
	private java.lang.Integer agentId;

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	
	public java.lang.Integer getPluginId() {
		return pluginId;
	}

	public void setPluginId(java.lang.Integer pluginId) {
		this.pluginId = pluginId;
	}

	public java.lang.String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(java.lang.String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public java.lang.String getActOpen() {
		return actOpen;
	}

	public void setActOpen(java.lang.String actOpen) {
		this.actOpen = actOpen;
	}

	public java.lang.String getCountOpen() {
		return countOpen;
	}

	public void setCountOpen(java.lang.String countOpen) {
		this.countOpen = countOpen;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  插件Id
	 */
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  参与人数
	 */
	public java.lang.Integer getJoinNum(){
		return this.joinNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  参与人数
	 */
	public void setJoinNum(java.lang.Integer joinNum){
		this.joinNum = joinNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  浏览数
	 */
	public java.lang.Integer getBrowseNum(){
		return this.browseNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  浏览数
	 */
	public void setBrowseNum(java.lang.Integer browseNum){
		this.browseNum = browseNum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动访问页面地址
	 */
	public java.lang.String getAccessUrl(){
		return this.accessUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动访问页面地址
	 */
	public void setAccessUrl(java.lang.String accessUrl){
		this.accessUrl = accessUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否申请推广
	 */
	public java.lang.String getAplyRecommend(){
		return this.aplyRecommend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否申请推广
	 */
	public void setAplyRecommend(java.lang.String aplyRecommend){
		this.aplyRecommend = aplyRecommend;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序
	 */
	public java.lang.Integer getSequence(){
		return this.sequence;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSequence(java.lang.Integer sequence){
		this.sequence = sequence;
	}

	public java.lang.Integer getUserPluginId() {
		return userPluginId;
	}

	public void setUserPluginId(java.lang.Integer userPluginId) {
		this.userPluginId = userPluginId;
	}

	public java.lang.String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(java.lang.String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
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

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public java.lang.String getAgentActOpen() {
		return agentActOpen;
	}

	public void setAgentActOpen(java.lang.String agentActOpen) {
		this.agentActOpen = agentActOpen;
	}
	
}
