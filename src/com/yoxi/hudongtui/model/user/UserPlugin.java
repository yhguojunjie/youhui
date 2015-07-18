package com.yoxi.hudongtui.model.user;

/**
 * 
 * 用户插件
 * 
 * @author wql
 * 
 * 2014-11-12
 *
 */
public class UserPlugin implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**id  用户插件id 对应活动表中的userPluginId*/
	private java.lang.Integer id;
	/**用户Id*/
	private java.lang.Integer userId;
	/**插件id*/
	private java.lang.Integer pluginId;
	/**插件活动数*/
	private java.lang.Integer actNum;
	/**过期时间*/
	private java.util.Date overdueTime;
	/**使用类型0 试用 1购买*/
	private java.lang.String useType;
	/**创建(购买)时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**代理商id 对应后台用户角色为代理商的用户id*/
	private java.lang.Integer agentId;
	
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
	 *@return: java.lang.Integer  用户Id
	 */
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户Id
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  插件活动数
	 */
	public java.lang.Integer getActNum(){
		return this.actNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  插件活动数
	 */
	public void setActNum(java.lang.Integer actNum){
		this.actNum = actNum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建(购买)时间
	 */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建(购买)时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  过期时间
	 */
	public java.util.Date getOverdueTime(){
		return this.overdueTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  过期时间
	 */
	public void setOverdueTime(java.util.Date overdueTime){
		this.overdueTime = overdueTime;
	}

	public java.lang.String getUseType() {
		return useType;
	}

	public void setUseType(java.lang.String useType) {
		this.useType = useType;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}
	
}
