package com.yoxi.hudongtui.model.user;

/**
 * 
 * @Description
 *
 * @author wql
 *
 * @Date 2015年3月10日
 *
 */

public class ThirdBind implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1346252555406268189L;
	/**id*/
	private java.lang.Integer id;
	/**用户id*/
	private java.lang.Integer userId;
	/**第三方呢称*/
	private java.lang.String thirdNickName;
	/**第三方id，如微信的unionid*/
	private java.lang.String thirdId;
	/**2QQ，3微信web 4新浪微博 5腾讯微博 6微信公众平台*/
	private java.lang.String source;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	
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
	 *@return: java.lang.String  第三方呢称
	 */
	public java.lang.String getThirdNickName(){
		return this.thirdNickName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方呢称
	 */
	public void setThirdNickName(java.lang.String thirdNickName){
		this.thirdNickName = thirdNickName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  第三方id，如微信的unionid
	 */
	public java.lang.String getThirdId(){
		return this.thirdId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  第三方id，如微信的unionid
	 */
	public void setThirdId(java.lang.String thirdId){
		this.thirdId = thirdId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  2QQ，3微信web 4新浪微博 5腾讯微博 6微信公众平台
	 */
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  2QQ，3微信web 4新浪微博 5腾讯微博 6微信公众平台
	 */
	public void setSource(java.lang.String source){
		this.source = source;
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
}
