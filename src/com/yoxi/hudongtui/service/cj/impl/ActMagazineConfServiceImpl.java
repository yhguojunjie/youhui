package com.yoxi.hudongtui.service.cj.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoxi.hudongtui.dao.cj.ActMagazineConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.ActMagazineConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActMagazineConfService;
import com.yoxi.hudongtui.vo.cj.magazine.MagazineContentVo;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

/**
 * 微场景
 * @author jjb
 *
 */
@Service
public class ActMagazineConfServiceImpl implements IActMagazineConfService{

	@Autowired
	private ActMagazineConfDAO actMagazineConfDAO;
	
	@Autowired
	private PluginActDAO pluginActDAO;
	
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActMagazineConf actMagazineConf, final Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actMagazineConf.getName());
		pluginAct.setStartTime(actMagazineConf.getStartTime());
		pluginAct.setEndTime(actMagazineConf.getEndTime());
		pluginAct.setIcon(actMagazineConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actMagazineConf.setActivityId(activityId);
			ret = actMagazineConfDAO.add(actMagazineConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	public ActMagazineConf findByActId(Integer activityId) {
		ActMagazineConf actMagazineConf = actMagazineConfDAO.findByActivityId(activityId);
		return actMagazineConf;
	}

	@Override
	public List<MagazineContentVo> parseJson(String content) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<MagazineContentVo>>(){}.getType();
		List<MagazineContentVo> magazineContentVoList = gson.fromJson(content, type);
		return magazineContentVoList;
	}
	
	@Override
	@Transactional
	public Integer update(ActMagazineConf actMagazineConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actMagazineConf.getActivityId());
		infoVo.setIcon(actMagazineConf.getShareImgUrl());
		infoVo.setStartTime(actMagazineConf.getStartTime());
		infoVo.setEndTime(actMagazineConf.getEndTime());
		infoVo.setTitle(actMagazineConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actMagazineConfDAO.update(actMagazineConf);
		return ret;
	}
}
