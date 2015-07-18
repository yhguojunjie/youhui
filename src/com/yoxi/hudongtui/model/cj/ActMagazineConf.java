package com.yoxi.hudongtui.model.cj;


/**
 * 
 * @author wql
 * 
 * 微场景活动
 *
 */
public class ActMagazineConf implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**id*/
	private java.lang.Integer id;
	/**活动id*/
	private java.lang.Integer activityId;
	/**名称*/
	private java.lang.String name;
	
	private java.lang.String shareImgUrl;
	/**分享标题*/
	private java.lang.String shareTitle;
	/**分享简介*/
	private java.lang.String shareDescription;
	/**分享后跳转链接*/
	private java.lang.String shareLink;
	/**微场景效果*/
	private java.lang.String effect;
	/**背景音乐*/
	private java.lang.String bgMusic;
	/**背景音乐链接*/
	private java.lang.String bgMusicUrl;
	/**音乐图表及效果*/
	private java.lang.String iconEffect;
	/**内容配置*/
	private java.lang.Object content;
	/**开始时间*/
	private java.util.Date startTime;
	/**结束时间*/
	private java.util.Date endTime;
	/**创建时间*/
	private java.util.Date createTime;
	
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
	 *@return: java.lang.Integer  活动id
	 */
	public java.lang.Integer getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  活动id
	 */
	public void setActivityId(java.lang.Integer activityId){
		this.activityId = activityId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	
	public java.lang.String getShareImgUrl() {
		return shareImgUrl;
	}

	public void setShareImgUrl(java.lang.String shareImgUrl) {
		this.shareImgUrl = shareImgUrl;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分享标题
	 */
	public java.lang.String getShareTitle(){
		return this.shareTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分享标题
	 */
	public void setShareTitle(java.lang.String shareTitle){
		this.shareTitle = shareTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分享简介
	 */
	public java.lang.String getShareDescription(){
		return this.shareDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分享简介
	 */
	public void setShareDescription(java.lang.String shareDescription){
		this.shareDescription = shareDescription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分享后跳转链接
	 */
	public java.lang.String getShareLink(){
		return this.shareLink;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分享后跳转链接
	 */
	public void setShareLink(java.lang.String shareLink){
		this.shareLink = shareLink;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微场景效果
	 */
	public java.lang.String getEffect(){
		return this.effect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微场景效果
	 */
	public void setEffect(java.lang.String effect){
		this.effect = effect;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  背景音乐
	 */
	public java.lang.String getBgMusic(){
		return this.bgMusic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  背景音乐
	 */
	public void setBgMusic(java.lang.String bgMusic){
		this.bgMusic = bgMusic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  背景音乐链接
	 */
	public java.lang.String getBgMusicUrl(){
		return this.bgMusicUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  背景音乐链接
	 */
	public void setBgMusicUrl(java.lang.String bgMusicUrl){
		this.bgMusicUrl = bgMusicUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  音乐图表及效果
	 */
	public java.lang.String getIconEffect(){
		return this.iconEffect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  音乐图表及效果
	 */
	public void setIconEffect(java.lang.String iconEffect){
		this.iconEffect = iconEffect;
	}
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  内容配置
	 */
	public java.lang.Object getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  内容配置
	 */
	public void setContent(java.lang.Object content){
		this.content = content;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
}
