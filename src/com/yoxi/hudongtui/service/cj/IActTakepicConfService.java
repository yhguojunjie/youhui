package com.yoxi.hudongtui.service.cj;

import com.yoxi.hudongtui.model.cj.takepic.ActTakepicConf;

public interface IActTakepicConfService {

	public Integer save(ActTakepicConf actTakepicConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActTakepicConf actTakepicConf);
	
	public ActTakepicConf findByActId(final Integer activityId);
}
