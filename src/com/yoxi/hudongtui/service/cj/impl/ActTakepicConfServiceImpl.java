package com.yoxi.hudongtui.service.cj.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.takepic.ActTakepicConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.takepic.ActTakepicConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActTakepicConfService;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActTakepicConfServiceImpl implements IActTakepicConfService {

	@Autowired
	private ActTakepicConfDAO actTakepicConfDAO;
	
	@Autowired
	private PluginActDAO pluginActDAO;
	
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActTakepicConf actTakepicConf, final Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actTakepicConf.getName());
		pluginAct.setStartTime(actTakepicConf.getStartTime());
		pluginAct.setEndTime(actTakepicConf.getEndTime());
		pluginAct.setIcon(actTakepicConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actTakepicConf.setActivityId(activityId);
			ret = actTakepicConfDAO.add(actTakepicConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	public ActTakepicConf findByActId(Integer activityId) {
		ActTakepicConf actTakepicConf = actTakepicConfDAO.findByActivityId(activityId);
		return actTakepicConf;
	}
	
	@Override
	@Transactional
	public Integer update(ActTakepicConf actTakepicConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actTakepicConf.getActivityId());
		infoVo.setIcon(actTakepicConf.getShareImgUrl());
		infoVo.setStartTime(actTakepicConf.getStartTime());
		infoVo.setEndTime(actTakepicConf.getEndTime());
		infoVo.setTitle(actTakepicConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actTakepicConfDAO.update(actTakepicConf);
		return ret;
	}
}
