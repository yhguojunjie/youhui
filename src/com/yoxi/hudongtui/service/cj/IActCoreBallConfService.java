package com.yoxi.hudongtui.service.cj;

import com.yoxi.hudongtui.model.cj.coreball.ActCoreBallConf;

/**
 * 
 * 心球
 * 
 * @author jjb
 *
 * 2015-4-24
 * 
 */
public interface IActCoreBallConfService {

	public Integer save(ActCoreBallConf actCoreBallConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActCoreBallConf actCoreBallConf);
	
	public ActCoreBallConf findByActId(final Integer activityId);
}
