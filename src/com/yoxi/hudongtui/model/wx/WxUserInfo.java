package com.yoxi.hudongtui.model.wx;

/**
 * 
 * 微信用户信息
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class WxUserInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**userId*/
	private java.lang.Integer userId;
	/**公众平台openId*/
	private java.lang.String mpOpenId;
	/**app openid*/
	private java.lang.String appOpenId;
	/**网站 openid*/
	private java.lang.String webOpenId;
	
	/**用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息*/
	private java.lang.Integer subscribe;
	/**用户的昵称*/
	private java.lang.String nickname;
	/**值为1时是男性，值为2时是女性，值为0时是未知*/
	private java.lang.Integer sex;
	/**用户所在城市*/
	private java.lang.String city;
	/**国家*/
	private java.lang.String country;
	/**省份*/
	private java.lang.String province;
	/**语言*/
	private java.lang.String language;
	/**最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空*/
	private java.lang.String headimgurl;
	/**用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间*/
	private java.lang.String subscribe_time;
	/**取消关注时间*/
	private java.util.Date unsubscribe_time;
	/**更新时间*/
	private java.util.Date updateTime;
	/**绑定ID*/
	private java.lang.String unionid;
	/**户特权信息，json数组，如微信沃卡用户为（chinaunicom）*/
	private java.lang.String privilege;
	
	
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  userId
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  userId
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	public java.lang.Integer getSubscribe(){
		return this.subscribe;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	public void setSubscribe(java.lang.Integer subscribe){
		this.subscribe = subscribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户的昵称
	 */
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户的昵称
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public java.lang.Integer getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public void setSex(java.lang.Integer sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户所在城市
	 */
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户所在城市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家
	 */
	public java.lang.String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家
	 */
	public void setCountry(java.lang.String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语言
	 */
	public java.lang.String getLanguage(){
		return this.language;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语言
	 */
	public void setLanguage(java.lang.String language){
		this.language = language;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 */
	public java.lang.String getHeadimgurl(){
		return this.headimgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 */
	public void setHeadimgurl(java.lang.String headimgurl){
		this.headimgurl = headimgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public java.lang.String getSubscribe_time(){
		return this.subscribe_time;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public void setSubscribe_time(java.lang.String subscribe_time){
		this.subscribe_time = subscribe_time;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  取消关注时间
	 */
	public java.util.Date getUnsubscribe_time(){
		return this.unsubscribe_time;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  取消关注时间
	 */
	public void setUnsubscribe_time(java.util.Date unsubscribe_time){
		this.unsubscribe_time = unsubscribe_time;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  绑定ID
	 */
	public java.lang.String getUnionid(){
		return this.unionid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  绑定ID
	 */
	public void setUnionid(java.lang.String unionid){
		this.unionid = unionid;
	}

	public java.lang.String getMpOpenId() {
		return mpOpenId;
	}

	public void setMpOpenId(java.lang.String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}

	public java.lang.String getAppOpenId() {
		return appOpenId;
	}

	public void setAppOpenId(java.lang.String appOpenId) {
		this.appOpenId = appOpenId;
	}

	public java.lang.String getWebOpenId() {
		return webOpenId;
	}

	public void setWebOpenId(java.lang.String webOpenId) {
		this.webOpenId = webOpenId;
	}

	public java.lang.String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(java.lang.String privilege) {
		this.privilege = privilege;
	}
	
	
}
