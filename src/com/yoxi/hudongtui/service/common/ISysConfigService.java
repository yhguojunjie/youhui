package com.yoxi.hudongtui.service.common;

import com.yoxi.hudongtui.model.SysConfig;

/**
 * 系统配置
 * 
 * @author wql
 *
 * 2014-11-18
 */
public interface ISysConfigService {

	/**
	 * 按id查
	 * @param id
	 * @return
	 */
	public SysConfig findById(Integer id);
}
