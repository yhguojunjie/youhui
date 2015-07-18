package com.yoxi.hudongtui.service.agent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.agent.AgentInfoDAO;
import com.yoxi.hudongtui.model.agent.AgentInfo;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;

@Service
public class AgentInfoService implements IAgentInfoService {

	@Autowired
	private AgentInfoDAO agentInfoDAO;

	@Override
	public AgentInfo getByStr(String getStr) {
		return agentInfoDAO.getByStr(getStr);
	}

	@Override
	public List<AgentInfo> findByStr(String findStr) {
		return agentInfoDAO.findByStr(findStr);
	}
	
	@Override
	public String getDomainById(Integer id) {
		return agentInfoDAO.getDomainById(id);
	}

	@Override
	public AgentInfo getById(Integer id) {
		return agentInfoDAO.getById(id);
	}
	
	
}
