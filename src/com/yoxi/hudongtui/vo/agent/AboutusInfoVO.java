package com.yoxi.hudongtui.vo.agent;

import java.io.Serializable;

/**
 * 
 * 关于我们VO
 * 
 * @author gjj
 * 
 * @Date 2015年3月31日
 * 
 */
public class AboutusInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6992681784006703372L;
	/** 代理商用户id */
	private java.lang.Integer agentId;
	/** 内容 */
	private java.lang.String content;

	/** 创建时间 */
	private java.util.Date createTime;

	/** id */
	private java.lang.Integer id;

	/** 修改时间 */
	private java.util.Date updateTime;

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.String getContent() {
		return content;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

}
