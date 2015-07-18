package com.yoxi.hudongtui.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.AgentContSwitchDAO;
import com.yoxi.hudongtui.model.content.AgentContSwitch;
import com.yoxi.hudongtui.service.content.IAgentContSwitchService;

@Service
public class AgentContSwitchService implements IAgentContSwitchService {
	@Autowired
	private AgentContSwitchDAO agentContSwitchDAO;

	@Override
	public AgentContSwitch getById(Integer agentId) {
		return agentContSwitchDAO.findById(agentId);
	}

}
