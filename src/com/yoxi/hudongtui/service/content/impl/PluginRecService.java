package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yoxi.hudongtui.dao.content.PluginRecDAO;
import com.yoxi.hudongtui.service.content.IPluginRecService;
import com.yoxi.hudongtui.vo.content.PluginRecVO;

@Service
public class PluginRecService implements IPluginRecService {

	@Autowired
	private PluginRecDAO pluginRecDAO;

	@Override
	public List<PluginRecVO> findPluginList(Integer agentId,String condition, int start,
			int count) throws Exception {
		List<PluginRecVO> pluginRecVO = pluginRecDAO.findPluginRecVO(
				condition, start, count);
		return pluginRecVO;
	}

}
