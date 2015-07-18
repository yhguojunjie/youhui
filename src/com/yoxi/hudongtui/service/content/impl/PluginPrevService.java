package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.PluginPrevDAO;
import com.yoxi.hudongtui.service.content.IPluginPrevService;
import com.yoxi.hudongtui.vo.content.ChannelVO;
import com.yoxi.hudongtui.vo.content.PluginPrevVO;

@Service
public class PluginPrevService implements IPluginPrevService {

	@Autowired
	private PluginPrevDAO PluginPrevDAO;
	
	@Override
	public List<PluginPrevVO> findPluginPrevList(int start,
			int count) throws Exception {
		List<PluginPrevVO> pluginPrevVO= PluginPrevDAO.findPluginPrevVO(start, count);
		return pluginPrevVO;
	}

}
