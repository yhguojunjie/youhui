package com.yoxi.hudongtui.service.cj.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.coreball.ActCoreBallConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.coreball.ActCoreBallConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActCoreBallConfService;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActCoreBallConfServiceImpl implements IActCoreBallConfService {

	@Autowired
	private ActCoreBallConfDAO actCoreBallConfDAO;
	@Autowired
	private PluginActDAO pluginActDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActCoreBallConf actCoreBallConf, Integer userPluginId) throws Exception{
		///保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actCoreBallConf.getName());
		pluginAct.setStartTime(actCoreBallConf.getStartTime());
		pluginAct.setEndTime(actCoreBallConf.getEndTime());
		pluginAct.setIcon(actCoreBallConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actCoreBallConf.setActivityId(activityId);
			ret = actCoreBallConfDAO.add(actCoreBallConf).intValue();
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
	public Integer update(ActCoreBallConf actCoreBallConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actCoreBallConf.getActivityId());
		infoVo.setIcon(actCoreBallConf.getShareImgUrl());
		infoVo.setStartTime(actCoreBallConf.getStartTime());
		infoVo.setEndTime(actCoreBallConf.getEndTime());
		infoVo.setTitle(actCoreBallConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actCoreBallConfDAO.update(actCoreBallConf);
		return ret;
	}

	@Override
	public ActCoreBallConf findByActId(Integer activityId) {
		ActCoreBallConf actCoreBallConf = actCoreBallConfDAO.findByActivityId(activityId);
		return actCoreBallConf;
	}
}
