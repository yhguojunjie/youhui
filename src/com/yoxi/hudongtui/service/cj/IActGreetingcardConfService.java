package com.yoxi.hudongtui.service.cj;

import com.yoxi.hudongtui.model.cj.ActGreetingcardConf;


public interface IActGreetingcardConfService {

	public Integer save(ActGreetingcardConf actGreetingcardConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActGreetingcardConf actGreetingcardConf);
	
	public ActGreetingcardConf findByActId(final Integer activityId);
}
