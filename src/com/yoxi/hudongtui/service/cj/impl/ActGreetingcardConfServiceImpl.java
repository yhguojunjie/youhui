package com.yoxi.hudongtui.service.cj.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.ActGreetingcardConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActGreetingcardConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActGreetingcardConfService;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActGreetingcardConfServiceImpl implements IActGreetingcardConfService{

	@Autowired
	private ActGreetingcardConfDAO actGreetingcardConfDAO;
	
	@Autowired
	private PluginActDAO pluginActDAO;
	
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActGreetingcardConf actGreetingcardConf, final Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actGreetingcardConf.getName());
		pluginAct.setStartTime(actGreetingcardConf.getStartTime());
		pluginAct.setEndTime(actGreetingcardConf.getEndTime());
		pluginAct.setIcon(actGreetingcardConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actGreetingcardConf.setActivityId(activityId);
			ret = actGreetingcardConfDAO.add(actGreetingcardConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	public ActGreetingcardConf findByActId(Integer activityId) {
		ActGreetingcardConf actGreetingcardConf = actGreetingcardConfDAO.findByActivityId(activityId);
		return actGreetingcardConf;
	}
	
	@Override
	@Transactional
	public Integer update(ActGreetingcardConf actGreetingcardConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actGreetingcardConf.getActivityId());
		infoVo.setIcon(actGreetingcardConf.getShareImgUrl());
		infoVo.setStartTime(actGreetingcardConf.getStartTime());
		infoVo.setEndTime(actGreetingcardConf.getEndTime());
		infoVo.setTitle(actGreetingcardConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actGreetingcardConfDAO.update(actGreetingcardConf);
		return ret;
	}

}
