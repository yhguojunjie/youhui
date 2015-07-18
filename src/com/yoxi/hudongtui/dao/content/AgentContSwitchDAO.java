package com.yoxi.hudongtui.dao.content;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.model.content.AgentContSwitch;

/**
 * 开关DAO
 * 
 * @author gjj 2015-4-30
 */
@DAO
public interface AgentContSwitchDAO {
	/**
	 * 按id查找
	 * 
	 * @param userId
	 * @return
	 */
	@SQL("SELECT * FROM t_agent_cont_switch WHERE agentId = :1")
	public AgentContSwitch findById(Integer agentId);
}
