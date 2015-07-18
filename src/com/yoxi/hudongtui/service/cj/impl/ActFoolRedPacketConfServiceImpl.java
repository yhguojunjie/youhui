package com.yoxi.hudongtui.service.cj.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.foolredpacket.ActFoolRedPacketConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.foolredpacket.ActFoolRedPacketConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActFoolRedPacketConfService;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActFoolRedPacketConfServiceImpl implements
		IActFoolRedPacketConfService {

	@Autowired
	private ActFoolRedPacketConfDAO actFoolRedPacketConfDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActFoolRedPacketConf actFoolRedPacketConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actFoolRedPacketConf.getName());
		pluginAct.setStartTime(actFoolRedPacketConf.getStartTime());
		pluginAct.setEndTime(actFoolRedPacketConf.getEndTime());
		pluginAct.setIcon(actFoolRedPacketConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actFoolRedPacketConf.setActivityId(activityId);
			ret = actFoolRedPacketConfDAO.add(actFoolRedPacketConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public Integer update(ActFoolRedPacketConf actFoolRedPacketConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actFoolRedPacketConf.getActivityId());
		infoVo.setIcon(actFoolRedPacketConf.getShareImgUrl());
		infoVo.setStartTime(actFoolRedPacketConf.getStartTime());
		infoVo.setEndTime(actFoolRedPacketConf.getEndTime());
		infoVo.setTitle(actFoolRedPacketConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actFoolRedPacketConfDAO.update(actFoolRedPacketConf);
		return ret;
	}

	@Override
	public ActFoolRedPacketConf findByActId(Integer activityId) {
		ActFoolRedPacketConf actFoolRedPacketConf = actFoolRedPacketConfDAO.findByActivityId(activityId);
		return actFoolRedPacketConf;
	}
}
