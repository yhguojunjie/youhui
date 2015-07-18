package com.yoxi.hudongtui.model.agent;

/**
 * 
 * 代理商插件
 * 
 * @author wql
 *
 */
public class PluginAgent implements java.io.Serializable {
	
	private static final long serialVersionUID = -3464026109829660242L;
	
	/**id*/
	private java.lang.Integer id;
	/**插件id*/
	private java.lang.Integer pluginId;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**销售价格*/
	private java.lang.Double salePrice;
	/**0 下架，1上架*/
	private java.lang.String onlineState;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
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
	 *@return: java.lang.Integer  代理商id
	 */
	public java.lang.Integer getAgentId(){
		return this.agentId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  代理商id
	 */
	public void setAgentId(java.lang.Integer agentId){
		this.agentId = agentId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  销售价格
	 */
	public java.lang.Double getSalePrice(){
		return this.salePrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  销售价格
	 */
	public void setSalePrice(java.lang.Double salePrice){
		this.salePrice = salePrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 下架，1上架
	 */
	public java.lang.String getOnlineState(){
		return this.onlineState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 下架，1上架
	 */
	public void setOnlineState(java.lang.String onlineState){
		this.onlineState = onlineState;
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
