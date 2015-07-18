package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.ActRecDAO;
import com.yoxi.hudongtui.service.content.IActRecService;
import com.yoxi.hudongtui.vo.content.ActRecVO;

@Service
public class ActRecService implements IActRecService {

	@Autowired
	private ActRecDAO actRecDAO;
		
	@Override
	public List<ActRecVO> findActList(Integer agentId,String condition,int start, int count)
			throws Exception {
		List<ActRecVO> actRecList = actRecDAO.findActList(agentId, condition, start, count);
		return actRecList;
	}

}
