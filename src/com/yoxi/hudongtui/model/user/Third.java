package com.yoxi.hudongtui.model.user;

import java.util.Date;

/**
 * 第三方用户
 * 
 * @author wangql
 * 
 * 2014-11-12
 *
 */
public class Third implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8152921417712355487L;
	
	/**id*/
	private java.lang.Integer id;
	/**用户id*/
	private java.lang.Integer userId;
	/**第三方用户标识ID*/
	private java.lang.String thirdId;
	/**2QQ，3微信 4新浪微博 5腾讯微博*/
	private java.lang.String source;
	
	private java.lang.String accessToken;
	
	private java.lang.String refreshToken;
	
	private java.util.Date acessTokenExpireTime;
	
	private java.util.Date createTime;
	
	private java.util.Date updateTime;
	
	
	public Third() {
	
	}

	public Third(Integer userId, String thirdId, String source,
			String accessToken, Date createTime) {
		super();
		this.userId = userId;
		this.thirdId = thirdId;
		this.source = source;
		this.accessToken = accessToken;
		this.createTime = createTime;
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
	 *@return: java.lang.Integer  用户id
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户id
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第三方用户标识ID
	 */
	public java.lang.String getThirdId(){
		return this.thirdId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方用户标识ID
	 */
	public void setThirdId(java.lang.String thirdId){
		this.thirdId = thirdId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  2QQ，3微信 4新浪微博 5腾讯微博
	 */
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  2QQ，3微信 4新浪微博 5腾讯微博
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}

	public java.lang.String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(java.lang.String accessToken) {
		this.accessToken = accessToken;
	}

	public java.lang.String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(java.lang.String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public java.util.Date getAcessTokenExpireTime() {
		return acessTokenExpireTime;
	}

	public void setAcessTokenExpireTime(java.util.Date acessTokenExpireTime) {
		this.acessTokenExpireTime = acessTokenExpireTime;
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
	
}
