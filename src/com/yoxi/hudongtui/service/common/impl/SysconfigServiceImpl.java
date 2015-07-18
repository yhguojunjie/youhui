package com.yoxi.hudongtui.service.common.impl;

import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.SysConfigDAO;
import com.yoxi.hudongtui.model.SysConfig;
import com.yoxi.hudongtui.service.common.ISysConfigService;

@Service
public class SysconfigServiceImpl implements ISysConfigService {

	private SysConfigDAO sysConfigDAO;
	
	@Override
	public SysConfig findById(Integer id) {
		
		return sysConfigDAO.findById(id);
	}

}
