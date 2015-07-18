package com.yoxi.hudongtui.dao.plugin;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.UserPluginTryout;



/**
 * 
 * 用户插件试用记录
 * 
 * @author wql
 * 
 * 2014-11-24
 *
 */
@DAO
public interface UserPluginTryoutDAO {

	/**
	 * 新增记录
	 * @param third
	 * @return
	 */
	@SQL("insert into t_user_plugin_tryout (userId,pluginId,agentId,createTime) "+
			"values (:1.userId,:1.pluginId,:1.agentId,:1.createTime)")
	public Identity add(UserPluginTryout userPluginTryout);
	
	/**
	 * 按用户id插件id查找
	 * @param userId 
	 * @param pluginId
	 * @return
	 */
	@SQL("select * from t_user_plugin_tryout where userId = :1 and pluginId = :2 and agentId = :3")
	public UserPluginTryout getByUidPid(Integer userId,Integer pluginId,Integer agentId);
	
}
