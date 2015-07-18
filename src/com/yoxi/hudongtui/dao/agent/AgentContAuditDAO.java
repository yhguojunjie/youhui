package com.yoxi.hudongtui.dao.agent;

import com.yoxi.hudongtui.model.agent.AgentContAudit;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

/**
 *
 * 代理商内容审核
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
@DAO
public interface AgentContAuditDAO {
	
	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * @param getStr
	 * @return
	 */
	@SQL("SELECT * FROM t_agent_cont_audit WHERE ##(:getStr) LIMIT 0,1")
	public AgentContAudit getByStr(@SQLParam("getStr") String getStr);
}
