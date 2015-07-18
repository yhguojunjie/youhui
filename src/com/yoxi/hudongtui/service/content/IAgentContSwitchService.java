package com.yoxi.hudongtui.service.content;

import com.yoxi.hudongtui.model.content.AgentContSwitch;

/**
 * 
 * AgentContSwitch
 * 
 * @author gjj
 * 
 * @Date 2015年4月30日
 * 
 */
public interface IAgentContSwitchService {

	/**
	 * 按id查找
	 * 
	 * @param pluginId
	 * @return
	 */
	public AgentContSwitch getById(Integer agentId);
}
