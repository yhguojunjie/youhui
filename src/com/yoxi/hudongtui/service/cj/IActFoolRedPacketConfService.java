package com.yoxi.hudongtui.service.cj;

import com.yoxi.hudongtui.model.cj.foolredpacket.ActFoolRedPacketConf;

/**
 * 
 * 愚人红包
 * 
 * @author jjb
 *
 * 2015-3-22
 * 
 */
public interface IActFoolRedPacketConfService {

	public Integer save(ActFoolRedPacketConf actFoolRedPacketConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActFoolRedPacketConf actFoolRedPacketConf);
	
	public ActFoolRedPacketConf findByActId(final Integer activityId);
}
