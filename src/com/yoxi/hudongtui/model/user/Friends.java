package com.yoxi.hudongtui.model.user;

/**
 * 
 * 朋友
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class Friends implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**id*/
	private java.lang.Integer id;
	/**用户id*/
	private java.lang.Integer userId;
	/**朋友id*/
	private java.lang.Integer friendId;
	/**添加时间*/
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  朋友id
	 */
	public java.lang.Integer getFriendId(){
		return this.friendId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  朋友id
	 */
	public void setFriendId(java.lang.Integer friendId){
		this.friendId = friendId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  添加时间
	 */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  添加时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
}
