package com.yoxi.hudongtui.service.cj.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.dao.cj.coupon.ActCouponConfDAO;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.cj.coupon.ActCouponConf;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.cj.IActCouponConfService;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;

@Service
public class ActCouponConfServiceImpl implements IActCouponConfService {

	@Autowired
	private ActCouponConfDAO actCouponConfDAO;
	
	@Autowired
	private PluginActDAO pluginActDAO;
	
	@Autowired
	private UserPluginDAO userPluginDAO;
	
	@Override
	@Transactional
	public Integer save(ActCouponConf actCouponConf, final Integer userPluginId) throws Exception{
		
		//保存活动插件表里面
		PluginAct pluginAct = new PluginAct();
		pluginAct.setUserPluginId(userPluginId);
		pluginAct.setTitle(actCouponConf.getName());
		pluginAct.setStartTime(actCouponConf.getStartTime());
		pluginAct.setEndTime(actCouponConf.getEndTime());
		pluginAct.setIcon(actCouponConf.getShareImgUrl());
		pluginAct.setType("0");
		UserPlugin userPlugin = userPluginDAO.getUserPluginById(userPluginId);
		pluginAct.setUserId(userPlugin.getUserId());
		pluginAct.setPluginId(userPlugin.getPluginId());
		pluginAct.setAgentId(userPlugin.getAgentId());
		
		int ret = 0;
		int activityId = pluginActDAO.add(pluginAct).intValue();
		if(activityId != 0){
			actCouponConf.setActivityId(activityId);
			ret = actCouponConfDAO.add(actCouponConf).intValue();
			//更新用户插件表活动数
			if(ret != 0){
				String upstr = " actNum = actNum + 1 ";
				userPluginDAO.upByStr(userPluginId, upstr);
			}
		}
		return ret;
	}

	@Override
	public ActCouponConf findByActId(Integer activityId) {
		ActCouponConf actCouponConf = actCouponConfDAO.findByActivityId(activityId);
		return actCouponConf;
	}
	
	@Override
	@Transactional
	public Integer update(ActCouponConf actCouponConf) {
		PluginInfoVo infoVo = new PluginInfoVo();
		infoVo.setActivityId(actCouponConf.getActivityId());
		infoVo.setIcon(actCouponConf.getShareImgUrl());
		infoVo.setStartTime(actCouponConf.getStartTime());
		infoVo.setEndTime(actCouponConf.getEndTime());
		infoVo.setTitle(actCouponConf.getName());
		pluginActDAO.updatePluginInfo(infoVo);
		int ret = actCouponConfDAO.update(actCouponConf);
		return ret;
	}
	
}
