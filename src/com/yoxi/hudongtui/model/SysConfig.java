package com.yoxi.hudongtui.model;

import java.util.Date;


/**
 * 
 * 系统配置
 * 
 * @author wql 
 * 
 * 2014-11-18
 *
 */
public class SysConfig implements java.io.Serializable {
	
	private static final long serialVersionUID = 1608301848095468195L;
	/**id*/
	private java.lang.Integer id;
	/**代号*/
	private java.lang.String code;
	/**值*/
	private java.lang.String value;
	/**描述说明*/
	private java.lang.String remark;
	/**创建时间*/
	private java.util.Date createTime;
	
	
	public SysConfig() {
	}

	public SysConfig(String code, String value, String remark,
			Date createTime) {
		this.code = code;
		this.value = value;
		this.remark = remark;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  代号
	 */
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代号
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  值
	 */
	public java.lang.String getValue(){
		return this.value;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  值
	 */
	public void setValue(java.lang.String value){
		this.value = value;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述说明
	 */
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述说明
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
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
