package com.yoxi.hudongtui.model.user;

/**
 * 
 * 用户插件试用记录
 * 
 * @author wql
 * 
 * 2014-11-24
 *
 */
public class UserPluginTryout implements java.io.Serializable {
	
	private static final long serialVersionUID = 2390819114261675163L;
	
	/**id*/
	private java.lang.Integer id;
	/**用户id*/
	private java.lang.Integer userId;
	/**插件id*/
	private java.lang.Integer pluginId;
	/**代理商id */
	private java.lang.Integer agentId;
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

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	
}
