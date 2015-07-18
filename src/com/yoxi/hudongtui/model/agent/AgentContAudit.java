package com.yoxi.hudongtui.model.agent;


/**
 * 
 * 代理商内容审核
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
public class AgentContAudit implements java.io.Serializable {
	
	private static final long serialVersionUID = -5830696486918185825L;
	
	/**id*/
	private java.lang.Integer id;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**提交审内容Id*/
	private java.lang.Integer contId;
	/**1 banner，2入驻品牌，3活动推荐，4经典案例，5插件推荐，6常见问题，7关于我们，8联系我们，9渠道管理*/
	private java.lang.String type;
	/**提交时间*/
	private java.util.Date applyTime;
	/**0 未审核，1审核通过*/
	private java.lang.String auditState;
	/**审核时间*/
	private java.util.Date auditTime;
	/**审核人*/
	private java.lang.Integer auditUserId;
	/**'0 未启用，1启用*/
	private java.lang.String status;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交审内容Id
	 */
	public java.lang.Integer getContId() {
		return contId;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交审内容Id
	 */

	public void setContId(java.lang.Integer contId) {
		this.contId = contId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  1 banner，2入驻品牌，3活动推荐，4经典案例，5插件推荐，6常见问题，7关于我们，8联系我们，9渠道管理
	 */
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  1 banner，2入驻品牌，3活动推荐，4经典案例，5插件推荐，6常见问题，7关于我们，8联系我们，9渠道管理
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提交时间
	 */
	public java.util.Date getApplyTime(){
		return this.applyTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提交时间
	 */
	public void setApplyTime(java.util.Date applyTime){
		this.applyTime = applyTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 未审核，1审核通过
	 */
	public java.lang.String getAuditState(){
		return this.auditState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 未审核，1审核通过
	 */
	public void setAuditState(java.lang.String auditState){
		this.auditState = auditState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核时间
	 */
	public java.util.Date getAuditTime(){
		return this.auditTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核时间
	 */
	public void setAuditTime(java.util.Date auditTime){
		this.auditTime = auditTime;
	}

	public java.lang.Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(java.lang.Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
}
