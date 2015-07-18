package com.yoxi.hudongtui.service.plugin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginTryoutDAO;
import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.model.user.UserPluginTryout;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;


@Service
public class PluginBusServiceImpl implements IPluginBusService {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private UserPluginDAO userPluginDAO;
	@Autowired
	private UserPluginTryoutDAO pluginTryoutDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * 试用
	 */
	@Override
	@Transactional
	public Map<String,String> tryOut(Plugin plugin, Integer userId) {
		//查找用户所属的代理商id
		User user = userDAO.findByUserId(userId);
		log.error("************tryOut user**********="+user);
		Integer agentId = user.getAgentId();
		
		String getStr1 = "useType = "+Globals.PLUGINUSER_USETYPE_BUY+" AND agentId = "+agentId+" AND userId = "+userId+" AND pluginId = "+plugin.getId();
	
		Map<String,String> dataMap = new HashMap<String,String>();
		
		UserPlugin uplugin1 = userPluginDAO.getByStr(getStr1);//查看是否是购买过产品
		
		UserPluginTryout uptyTemp = pluginTryoutDAO.getByUidPid(userId, plugin.getId(),agentId);//查看是否试用过
		
		if(uplugin1 != null && uptyTemp != null){ //已购买,已试用
			dataMap.put("status", "3");
			return dataMap;
		}
		
		if(uplugin1 != null && uptyTemp == null){ //已购买,未试用
			dataMap.put("status", "4");
			return dataMap;
		}
		
		if(uplugin1 == null && uptyTemp != null){ //未购买,已试用
			dataMap.put("status", "5");
			return dataMap;
		}
		
		
//		String getStr2 = "useType = "+Globals.PLUGINUSER_USETYPE_TRYOUT+" AND overdueTime > NOW() AND userId = "+userId+" AND pluginId = "+plugin.getId();
//		
//		UserPlugin uplugin2 = userPluginDAO.getByStr(getStr2);
//		
//		if(uptyTemp != null && uplugin2 != null){//已有试用记录，并且未过期
//			return "4";
//		}
//     
//		if(uptyTemp != null && uplugin2 == null){//已有试用记录，并且已过期
//			return "5";
//		}
		
		String getStr3 = "useType = "+Globals.PLUGINUSER_USETYPE_TRYOUT+" AND agentId = "+agentId+" AND userId = "+userId+" AND pluginId = "+plugin.getId();
		UserPlugin uplugin3 = userPluginDAO.getByStr(getStr3);
		
		if(uptyTemp == null && uplugin3 == null){//试用表没有试用记录，用户插件表也没有试用记录
			//新增用户插件表记录
			UserPlugin userPlugin = new UserPlugin();
			userPlugin.setActNum(0);
			userPlugin.setCreateTime(new Date());
			userPlugin.setPluginId(plugin.getId());
			userPlugin.setUserId(userId);
			userPlugin.setAgentId(agentId);
			userPlugin.setUseType(Globals.PLUGINUSER_USETYPE_TRYOUT);
			//计算过期日期
			Date overdueTime = DateUtils.addDays(new Date(), Globals.PLUGIN_TRYOU_LIMITDAY);
			userPlugin.setOverdueTime(overdueTime);
			int userPluginId = userPluginDAO.add(userPlugin).intValue();
			
			if(userPluginId != 0){
				//新增试用记录
				UserPluginTryout upty = new UserPluginTryout();
				upty.setUserId(userId);
				upty.setPluginId(plugin.getId());
				upty.setCreateTime(new Date());
				upty.setAgentId(agentId);
				pluginTryoutDAO.add(upty);
				
				//插件表试用数更新
				String upstr = ", tryoutNum = tryoutNum+1";
				pluginDAO.upByStr(plugin.getId(), upstr);
				
				//修改用户拥有插件数量
				String userUpStr = ", pluginNum = pluginNum+1";
				userDAO.upByStr(userId, userUpStr);
				
				dataMap.put("status", "1");
			}
			return dataMap;
		}
		dataMap.put("status","0");
		return dataMap;
	}


	/**
	 * 购买
	 */
	@Override
	@Transactional(propagation=Propagation.NESTED)
	public void buy(PluginDetailVO plugin,Integer userId,Integer buyNum) {
		//查找用户所属的代理商id
		User user = userDAO.findByUserId(userId);
		Integer agentId = user.getAgentId();
		UserPlugin userPlugin = new UserPlugin();
		userPlugin.setActNum(0);
		userPlugin.setCreateTime(new Date());
		userPlugin.setPluginId(plugin.getId());
		userPlugin.setUserId(userId);
		userPlugin.setAgentId(agentId);
		userPlugin.setUseType(Globals.PLUGINUSER_USETYPE_BUY);
		//计算到期时间
		Date  overdueTime = DateUtils.addMonths(new Date(), plugin.getValid() * buyNum);
		userPlugin.setOverdueTime(overdueTime);
		int upId = userPluginDAO.add(userPlugin).intValue();
		//修改插件够买人数
		if(upId != 0){
			String upstr = ", buyNum = buyNum+1";
			pluginDAO.upByStr(plugin.getId(), upstr);
		}
		
		//修改用户拥有插件数量
		String userUpStr = ", pluginNum = pluginNum+1";
		userDAO.upByStr(userId, userUpStr);
		
	}
	

	/**
	 * 续费购买
	 */
	@Override
	@Transactional(propagation=Propagation.NESTED)
	public void renewal(PluginDetailVO plugin,UserPlugin userPlugin,Integer userId, Integer buyNum) {
		//计算到期时间
		if(userPlugin != null){
			Date startDate = new Date();
			if(userPlugin.getOverdueTime() != null){
				if(userPlugin.getUseType().equals(Globals.PLUGINUSER_USETYPE_BUY)
					&& userPlugin.getOverdueTime().after(new Date())){
						startDate = userPlugin.getOverdueTime();
					}
			}
			Date overdueTime = DateUtils.addMonths(startDate, plugin.getValid() * buyNum);
			userPlugin.setOverdueTime(overdueTime);
			userPlugin.setUpdateTime(new Date());
			//已经试用或过期，改变过期时间和试用状态
			userPlugin.setUseType(Globals.PLUGINUSER_USETYPE_BUY);
			int upFlag = userPluginDAO.update(userPlugin);
			//修改插件够买人数
			if(upFlag != 0){
				String upstr = ", buyNum = buyNum+1";
			    pluginDAO.upByStr(plugin.getId(), upstr);
			}
		}

	}

	
	
	
	/**
	 * 为购买作条件判断和返回数据
	 */
	@Override
	public Map<String,String> prepareBuy(Plugin plugin, Integer userId)throws Exception {
		//查找用户所属的代理商id
		User user = userDAO.findByUserId(userId);
		log.error("************prepareBuy user**********="+user);
		Map<String,String> dataMap = new HashMap<String,String>();
		if(user.getRepreCoin() != null){
			dataMap.put("repreCoin",String.valueOf(user.getRepreCoin()));
		}else{
			dataMap.put("repreCoin","0");
		}
		
		//查找代理商模板定价
		PluginDetailVO pgDetail = pluginDAO.getAgentPlugin(plugin.getId(), user.getAgentId());
		if(pgDetail.getSalePrice() != null){
			dataMap.put("salePrice",String.valueOf(pgDetail.getSalePrice().intValue()));
		}
		dataMap.put("agid",String.valueOf(user.getAgentId()));
		if(pgDetail.getStatus().equals("0")|| pgDetail.getOnlineState().equals("1")){
			dataMap.put("plgStatus","0");//无法购买
		}else if(pgDetail.getSalePrice().equals(0.0)){
			dataMap.put("plgStatus","5");//销售价为0
		}else{
			dataMap.put("plgStatus","1");//正常购买
		}
		//查看当前用户是否有插件记录
//		Integer agentId = user.getAgentId();
	/*	String getStr = "useType = "+Globals.PLUGINUSER_USETYPE_BUY+" AND agentId = "+agentId+" AND userId = "+userId+" AND pluginId = "+plugin.getId();
		UserPlugin uplg = userPluginDAO.getByStr(getStr);
		if(uplg != null){
			if(uplg.getOverdueTime().before(new Date())){//已购买，插件过期，重新购买,可以进入购买流程
				dataMap.put("status","3");
			}
			else{//已购买，未过期，可正常使用
				dataMap.put("status","4");
			}
		}else{*/
		dataMap.put("status","1");//进入购买流程
//		}
		return dataMap;
	}


	/**
	 * 免费使用
	 */
	@Override
	public Map<String, String> freeUser(Plugin plugin, Integer userId) {
		//查找用户所属的代理商id
		User user = userDAO.findByUserId(userId);
		Integer agentId = user.getAgentId();
		Map<String,String> dataMap = new HashMap<String,String>();
		//查看用户插件表中是否有记录
//		UserPlugin upTemp = userPluginDAO.getByUidPid(userId, plugin.getId(),agentId);
		String getStr3 = "useType = "+Globals.PLUGINUSER_USETYPE_FREE+" AND agentId = "+agentId+" AND userId = "+userId+" AND pluginId = "+plugin.getId();
		UserPlugin uplugin3 = userPluginDAO.getByStr(getStr3);
	    plugin = pluginDAO.findById(plugin.getId());
		if(uplugin3 == null){//生成免费购买插件记录
			UserPlugin userPlugin = new UserPlugin();
			userPlugin.setActNum(0);
			userPlugin.setCreateTime(new Date());
			userPlugin.setPluginId(plugin.getId());
			userPlugin.setUserId(userId);
			userPlugin.setAgentId(agentId);
			userPlugin.setUseType(Globals.PLUGINUSER_USETYPE_BUY);
			//计算到期时间
			Date  overdueTime = DateUtils.addMonths(new Date(), plugin.getValid());
			userPlugin.setOverdueTime(overdueTime);
			int upId = userPluginDAO.add(userPlugin).intValue();
			//修改插件够买人数
			if(upId != 0){
				String upstr = ", buyNum = buyNum+1";
			    pluginDAO.upByStr(plugin.getId(), upstr);
			}
			//修改用户拥有插件数量
			String userUpStr = ", pluginNum = pluginNum+1";
			userDAO.upByStr(userId, userUpStr);
			dataMap.put("addUrl", plugin.getActAddUrl()+"/"+ String.valueOf(upId));
			dataMap.put("status","1");
		}else{
			//查看是否过期
			if(uplugin3.getOverdueTime() != null){
				if(uplugin3.getOverdueTime().before(new Date())){//过期则更新过期时间
					Date newVaild = DateUtils.addMonths(new Date(), plugin.getValid());
					userPluginDAO.updateOverdueTime(uplugin3.getId(), newVaild);
				}
				dataMap.put("status","3");
			}else{
				dataMap.put("status", "0");//系统错误
			}
		}
		return dataMap;
	}


}
