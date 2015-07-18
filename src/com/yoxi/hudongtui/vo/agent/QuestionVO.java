package com.yoxi.hudongtui.vo.agent;

import java.io.Serializable;

/**
 * 
 * 帮助中心VO
 * 
 * @author gjj
 * 
 * @Date 2015年3月31日
 * 
 */
public class QuestionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6237156995628554309L;
	/** 代理商用户id */
	private java.lang.Integer agentId;
	/** 回答 */
	private java.lang.String answer;
	/** 创建时间 */
	private java.util.Date createTime;
	/** id */
	private java.lang.Integer id;
	/** 问题 */
	private java.lang.String question;
	/** 顺序 */
	private java.lang.Integer seq;
	/** 修改时间 */
	private java.util.Date updateTime;

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.String getAnswer() {
		return answer;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getQuestion() {
		return question;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setAnswer(java.lang.String answer) {
		this.answer = answer;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public void setQuestion(java.lang.String question) {
		this.question = question;
	}
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
