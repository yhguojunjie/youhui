package com.yoxi.hudongtui.service.plugin;

import java.util.Map;

import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;

/**
 * 
 * 插件业务操作类
 * 
 * @author wql
 * 
 * 2014-11-23
 *
 */
public interface IPluginBusService {
	
	
	/**
	 * 试用
	 * @param plugin
	 * @param userId
	 */
	public Map<String,String> tryOut(Plugin plugin,Integer userId);
	
	/**
	 * 为购买准备数据
	 * @param plugin
	 * @param userId
	 * @return
	 */
	public Map<String,String> prepareBuy(Plugin plugin,Integer userId)throws Exception;
	
	/**
	 * 购买生成插件相关表记录，不包括下单，用户记录操作
	 * @param productId
	 * @param userId
	 */
	public void buy(PluginDetailVO plugin,Integer userId,Integer buyNum);
	
	/**
	 * 续费购买 不包括下单，用户记录操作
	 * @param plugin
	 * @param userId
	 * @param buyNum
	 */
	public void renewal(PluginDetailVO plugin,UserPlugin userPlugin,Integer userId,Integer buyNum);
	
	/**
	 * 免费使用
	 * @param plugin
	 * @param userId
	 * @return
	 */
	public Map<String,String> freeUser(Plugin plugin,Integer userId);
	
	
	
}
