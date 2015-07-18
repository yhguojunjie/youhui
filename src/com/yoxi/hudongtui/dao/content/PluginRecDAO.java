package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.vo.content.PluginRecVO;

/**
 * 
 * 模板推荐
 * 
 * @author gjj
 * 
 * @Date 2015年3月23日
 * 
 */
@DAO
public interface PluginRecDAO {

	/**
	 * 模版推荐列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<PluginRecVO>
	 */
	// @SQL("select d.id,b.salePrice, a.icon, "
	// + " a.name,(SELECT c.nickName from t_user c WHERE "
	// + " c.userId = a.userId) as nickName, a.description,"
	// + " a.publishTime,a.tryoutNum ,a.valid,a.status, "
	// + " a.uploadTime,a.updateTime,a.type,a.buyNum "
	// + " from t_plugin_rec d "
	// + " LEFT JOIN t_plugin a on d.pluginId=a.id left "
	// + " join t_plugin_agent b on  b.pluginId=a.id "
	// +
	// " where a.status='1' and b.onlineState='0' ##(:condition) LIMIT :2,:3 ")
	// public List<PluginRecVO> findPluginRecVO(@SQLParam("condition") String
	// condition,int start, int count) throws Exception;

	@SQL("select b.id,a.salePrice, b.icon,b.userId, " + " b.name,(SELECT c.nickName from t_user c WHERE " + " c.userId = b.userId) as nickName, b.description,"
			+ " b.publishTime,b.tryoutNum ,b.valid,b.status, " + " b.uploadTime,b.updateTime,b.type,b.buyNum " + " from t_plugin_agent a " + " LEFT JOIN t_plugin b on a.pluginId=b.id "
			+ " where b.status='1' and a.onlineState='0' ##(:condition) LIMIT :2,:3 ")
	public List<PluginRecVO> findPluginRecVO(@SQLParam("condition") String condition, int start, int count) throws Exception;

	/**
	 * 模版推荐列表信息（代理商）
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<PluginRecVO>
	 */
	@SQL("select b.id,e.salePrice, b.icon,b.userId, " + " b.name,(SELECT c.nickName from t_user c WHERE " + " c.userId = b.userId) as nickName, b.description,"
			+ " b.publishTime,b.tryoutNum ,b.valid,b.status, " + " b.uploadTime,b.updateTime,b.type,b.buyNum "
			+ " from t_agent_plugin_rec  a  LEFT JOIN t_plugin_agent e on a.pluginAgentId=e.id LEFT JOIN t_plugin b on a.pluginId=b.id "
			+ " where b.status='1' and e.onlineState='0' ##(:condition) LIMIT :2,:3 ")
	public List<PluginRecVO> findPluginAgentRecVO(@SQLParam("condition") String condition, int start, int count) throws Exception;

}
