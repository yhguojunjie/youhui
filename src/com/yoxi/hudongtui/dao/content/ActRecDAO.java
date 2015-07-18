package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.vo.content.ActRecVO;

/**
 * 
 * 活动推荐
 * 
 * @author wql
 * 
 * @Date 2015年3月25日
 * 
 */
@DAO
public interface ActRecDAO {

	/**
	 * 查询列表
	 * 
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 */
	// @SQL(" SELECT b.id as actId,b.title as actTitle,b.countOpen,b.accessUrl,b.createTime,"
	// +" b.joinNum as actJoinNum, b.browseNum as actBrowseNum,b.pluginId,b.userId,b.agentId,"
	// +" CASE WHEN b.icon is null THEN (select d.icon from t_plugin d WHERE d.id = b.pluginId) ELSE b.icon END as actIcon,"
	// +" (SELECT c.headimgUrl from t_user c WHERE c.userId = b.userId) as headimgUrl,"
	// +" (SELECT c.introduce from t_user c WHERE c.userId = b.userId) as introduce,"
	// +" (SELECT c.nickName from t_user c WHERE c.userId = b.userId) as nickName,"
	// +" (SELECT d.name from t_plugin d WHERE d.id = b.pluginId) as pluginName,"
	// +" (SELECT d.actAccessUrl from t_plugin d WHERE d.id = b.pluginId) as actAccessUrl,"
	// +" (SELECT e.id from t_channel e WHERE e.userId = b.userId LIMIT 0,1) as channelId "
	// +" FROM t_act_rec a LEFT JOIN t_plugin_act b ON a.actId = b.id ##(:condition) LIMIT :3,:4")
	// public List<ActRecVO> findActList(Integer agentId,@SQLParam("condition")
	// String condition,int start,int count);

	@SQL(" SELECT b.id as actId,b.title as actTitle,b.countOpen,b.accessUrl,b.createTime,f.overdueTime," + " b.joinNum as actJoinNum, b.browseNum as actBrowseNum,b.pluginId,b.userId,b.agentId,"
			+ " CASE WHEN b.icon is null THEN (select d.icon from t_plugin d WHERE d.id = b.pluginId) ELSE b.icon END as actIcon,"
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = b.userId) as headimgUrl," + " (SELECT c.introduce from t_user c WHERE c.userId = b.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = b.userId) as nickName," + " (SELECT d.name from t_plugin d WHERE d.id = b.pluginId) as pluginName,"
			+ " (SELECT d.actAccessUrl from t_plugin d WHERE d.id = b.pluginId) as actAccessUrl," + " (SELECT d.showActId from t_plugin d WHERE d.id = b.pluginId) as showActId,"
			+ " (SELECT d.showUrl from t_plugin d WHERE d.id = b.pluginId) as showUrl," + " (SELECT e.id from t_channel e WHERE e.userId = b.userId and e.auditState=1 LIMIT 0,1) as channelId "
			+ " FROM t_plugin_act b LEFT JOIN t_user_plugin f on b.userPluginId = f.id  WHERE b.agentId = :1  ##(:condition) LIMIT :3,:4")
	public List<ActRecVO> findActList(Integer agentId, @SQLParam("condition") String condition, int start, int count);

	/**
	 * 查询列表（代理商）
	 * 
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 */
	@SQL(" SELECT b.id as actId,b.title as actTitle,b.countOpen,b.accessUrl,b.createTime,f.overdueTime," + " b.joinNum as actJoinNum, b.browseNum as actBrowseNum,b.pluginId,b.userId,b.agentId,"
			+ " CASE WHEN b.icon is null THEN (select d.icon from t_plugin d WHERE d.id = b.pluginId) ELSE b.icon END as actIcon,"
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = b.userId) as headimgUrl," + " (SELECT c.introduce from t_user c WHERE c.userId = b.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = b.userId) as nickName," + " (SELECT d.name from t_plugin d WHERE d.id = b.pluginId) as pluginName,"
			+ " (SELECT d.actAccessUrl from t_plugin d WHERE d.id = b.pluginId) as actAccessUrl," + " (SELECT d.showActId from t_plugin d WHERE d.id = b.pluginId) as showActId,"
			+ " (SELECT d.showUrl from t_plugin d WHERE d.id = b.pluginId) as showUrl," + " (SELECT e.id from t_channel e WHERE e.userId = b.userId and e.auditState=1 LIMIT 0,1) as channelId "
			+ " FROM t_agent_act_rec g LEFT JOIN  t_plugin_act b on g.actId=b.id LEFT JOIN t_user_plugin f on b.userPluginId = f.id  WHERE b.agentId = :1  ##(:condition) LIMIT :3,:4")
	public List<ActRecVO> findActAgentList(Integer agentId, @SQLParam("condition") String condition, int start, int count);
}
