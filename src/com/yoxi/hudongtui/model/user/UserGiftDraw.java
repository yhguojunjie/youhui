package com.yoxi.hudongtui.model.user;

/**
 * 
 * 用户礼包中奖
 * 
 * @author wql
 *
 * 2014-11-12
 *
 */
public class UserGiftDraw implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**id*/
	private java.lang.Integer id;
	/**用户id*/
	private java.lang.Integer userId;
	/**领用人id*/
	private java.lang.Integer drawUserId;
	/**领用时间*/
	private java.util.Date drawTime;
	/**礼物id*/
	private java.lang.Integer giftId;
	
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
	 *@return: java.lang.Integer  领用人id
	 */
	public java.lang.Integer getDrawUserId(){
		return this.drawUserId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  领用人id
	 */
	public void setDrawUserId(java.lang.Integer drawUserId){
		this.drawUserId = drawUserId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  领用时间
	 */
	public java.util.Date getDrawTime(){
		return this.drawTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  领用时间
	 */
	public void setDrawTime(java.util.Date drawTime){
		this.drawTime = drawTime;
	}

	public java.lang.Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(java.lang.Integer giftId) {
		this.giftId = giftId;
	}
	
	
}
