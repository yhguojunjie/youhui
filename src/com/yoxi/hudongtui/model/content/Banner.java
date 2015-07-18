package com.yoxi.hudongtui.model.content;

/**
 * 
 * Banner
 * 
 * @author gjj
 * 
 * @Date 2015年4月20日
 * 
 */
public class Banner implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4543712002648878393L;
	/** agentId */
	private java.lang.Integer agentId;

	/** auditorId */
	private java.lang.Integer auditorId;

	/** auditstate */
	private java.lang.String auditstate;

	/** 审核时间 */
	private java.util.Date audittime;

	/** 创建时间 */
	private java.util.Date createTime;

	/** hideState */
	private java.lang.String hideState;

	/** id */
	private java.lang.Integer id;

	/** linktype */
	private java.lang.String linktype;

	/** objid */
	private java.lang.Integer objid;

	/** pcLink */
	private java.lang.String pcLink;
	/** pcLogo */
	private java.lang.String pcLogo;

	/** sbannerId */
	private java.lang.Integer sbannerId;

	/** 顺序 */
	private java.lang.Integer seq;

	/** type */
	private java.lang.String type;

	/** 更新时间 */
	private java.util.Date updateTime;
	/** wxLink */
	private java.lang.String wxLink;

	/** wxLogo */
	private java.lang.String wxLogo;
	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.Integer getAuditorId() {
		return auditorId;
	}

	public java.lang.String getAuditstate() {
		return auditstate;
	}

	public java.util.Date getAudittime() {
		return audittime;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.String getHideState() {
		return hideState;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer id
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	public java.lang.String getLinktype() {
		return linktype;
	}

	public java.lang.Integer getObjid() {
		return objid;
	}

	public java.lang.String getPcLink() {
		return pcLink;
	}

	public java.lang.String getPcLogo() {
		return pcLogo;
	}

	public java.lang.Integer getSbannerId() {
		return sbannerId;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 顺序
	 */
	public java.lang.Integer getSeq() {
		return this.seq;
	}

	public java.lang.String getType() {
		return type;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public java.lang.String getWxLink() {
		return wxLink;
	}

	public java.lang.String getWxLogo() {
		return wxLogo;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setAuditorId(java.lang.Integer auditorId) {
		this.auditorId = auditorId;
	}

	public void setAuditstate(java.lang.String auditstate) {
		this.auditstate = auditstate;
	}

	public void setAudittime(java.util.Date audittime) {
		this.audittime = audittime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setHideState(java.lang.String hideState) {
		this.hideState = hideState;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setLinktype(java.lang.String linktype) {
		this.linktype = linktype;
	}

	public void setObjid(java.lang.Integer objid) {
		this.objid = objid;
	}

	public void setPcLink(java.lang.String pcLink) {
		this.pcLink = pcLink;
	}

	public void setPcLogo(java.lang.String pcLogo) {
		this.pcLogo = pcLogo;
	}

	public void setSbannerId(java.lang.Integer sbannerId) {
		this.sbannerId = sbannerId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 顺序
	 */
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setWxLink(java.lang.String wxLink) {
		this.wxLink = wxLink;
	}

	public void setWxLogo(java.lang.String wxLogo) {
		this.wxLogo = wxLogo;
	}
}
