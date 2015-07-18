package com.yoxi.hudongtui.model.plugin;

/**
 * 
 * 插件图片
 * 
 * @author wql
 * 
 * 2014-11-14
 *
 */
public class PluginPic  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**id*/
	private java.lang.Integer id;
	/**插件id*/
	private java.lang.Integer pluginId;
	/**图片url*/
	private java.lang.String url;
	/**上传时间*/
	private java.util.Date uploadTime;
	/**上传人userId*/
	private java.lang.Integer uploadUser;
	
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
	 *@return: java.lang.Integer  插件id
	 */
	public java.lang.Integer getPluginId(){
		return this.pluginId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  插件id
	 */
	public void setPluginId(java.lang.Integer pluginId){
		this.pluginId = pluginId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片url
	 */
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片url
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  上传人userId
	 */
	public java.lang.Integer getUploadUser(){
		return this.uploadUser;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  上传人userId
	 */
	public void setUploadUser(java.lang.Integer uploadUser){
		this.uploadUser = uploadUser;
	}
}
