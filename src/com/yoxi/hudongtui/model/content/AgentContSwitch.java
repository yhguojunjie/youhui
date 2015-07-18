package com.yoxi.hudongtui.model.content;

/**
 * 
 * AgentContSwitch
 * 
 * @author gjj
 * 
 * @Date 2015年4月29日
 * 
 */

public class AgentContSwitch implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1344484445864413028L;
	/** 活动推荐开关 0开启 1关闭 */
	private java.lang.String actRecState;
	/** agentId */
	private java.lang.Integer agentId;

	/* private AgentInfo agentInfo; */
	/** banner推荐开关 0 开启 1关闭 */
	private java.lang.String bannerState;

	/** 经典案例内容设置 0使用平台数据，1自己管理数据 **/
	private java.lang.String caseConType;

	/** 经典案例开关 0开启 1关闭 */
	private java.lang.String caseState;

	/** 渠道内容设置 0使用平台数据，1自己管理数据 **/
	private java.lang.String channelConType;

	/** 渠道推荐开关 0开启 1关闭 */
	private java.lang.String channelState;

	/** 常见问题开关 0开启 1关闭 */
	private java.lang.String faqState;

	/** 模板推荐开启状态 0 开启，1关闭 */
	private java.lang.String pluginRecState;

	public java.lang.String getActRecState() {
		return actRecState;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.String getBannerState() {
		return bannerState;
	}

	public java.lang.String getCaseConType() {
		return caseConType;
	}

	public java.lang.String getCaseState() {
		return caseState;
	}

	public java.lang.String getChannelConType() {
		return channelConType;
	}

	public java.lang.String getChannelState() {
		return channelState;
	}

	public java.lang.String getFaqState() {
		return faqState;
	}

	public java.lang.String getPluginRecState() {
		return pluginRecState;
	}

	public void setActRecState(java.lang.String actRecState) {
		this.actRecState = actRecState;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setBannerState(java.lang.String bannerState) {
		this.bannerState = bannerState;
	}
	public void setCaseConType(java.lang.String caseConType) {
		this.caseConType = caseConType;
	}
	public void setCaseState(java.lang.String caseState) {
		this.caseState = caseState;
	}
	public void setChannelConType(java.lang.String channelConType) {
		this.channelConType = channelConType;
	}

	public void setChannelState(java.lang.String channelState) {
		this.channelState = channelState;
	}

	public void setFaqState(java.lang.String faqState) {
		this.faqState = faqState;
	}
	public void setPluginRecState(java.lang.String pluginRecState) {
		this.pluginRecState = pluginRecState;
	}

}
