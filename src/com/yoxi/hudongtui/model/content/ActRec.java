package com.yoxi.hudongtui.model.content;

/**
 * 
 * 活动推荐
 *
 * @author wql
 *
 * @Date 2015年3月25日
 *
 */
public class ActRec implements java.io.Serializable {

	private static final long serialVersionUID = 1329885873265805712L;
	/**推荐ID*/
	private java.lang.Integer id;
	/**活动id*/
	private java.lang.Integer actId;
	/**排列顺序*/
	private java.lang.Integer seq;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  推荐ID
	 */
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  推荐ID
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  活动id
	 */
	public java.lang.Integer getActId(){
		return this.actId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  活动id
	 */
	public void setActId(java.lang.Integer actId){
		this.actId = actId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排列顺序
	 */
	public java.lang.Integer getSeq(){
		return this.seq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排列顺序
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
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
	 *@return: java.util.Date  修改时间
	 */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
