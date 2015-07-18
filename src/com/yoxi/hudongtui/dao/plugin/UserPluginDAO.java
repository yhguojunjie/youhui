package com.yoxi.hudongtui.dao.plugin;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.vo.plugin.UserPluginVo;

/**
 * 用户插件信息维护DAO
 * @author xielj 2014-11-24
 */
@DAO
public interface UserPluginDAO {
	/**
	 * 查询用户插件总数
	 * @param condition 查询条件
	 */
	@SQL("SELECT count(*) FROM t_user_plugin b WHERE 1 =1 ##(:condition)")
	public int getUserPluginCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查询用户插件列表信息
	 * @param condition 查询条件[1.是否过期，2.过滤用户数据]
	 * @param startRow 每页开始行
	 * @param pageSize 每页显示数
	 * @return List<UserPluginVo>
	 */
	@SQL("SELECT b.id,b.pluginId,b.createTime,b.overdueTime,b.actNum,a.icon,a.name,a.description,a.showUrl,a.userId as devId,a.actAddUrl,a.actEditUrl,"
			+ " (SELECT DISTINCT c.nickName from t_user c where c.userId = a.userId) AS nickName "
			+ " FROM t_plugin a,t_user_plugin b where a.id = b.pluginId ##(:condition) ORDER BY b.updateTime DESC LIMIT :2,:3")
	public List<UserPluginVo> findUserPlugins(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 查询用户插件总数
	 * @param condition 查询条件
	 */
	@SQL("SELECT a.id,b.icon,b.name,a.overdueTime FROM t_user_plugin a,t_plugin b where a.pluginId = b.id ##(:condition)")
	public List<UserPluginVo> findUserPlugins4AddAct(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 按用户id插件id查找
	 * @param userId
	 * @param pluginId
	 * @return
	 */
	@SQL("SELECT * FROM t_user_plugin t WHERE t.userId = :1 and pluginId = :2 and t.agentId = :3 ")
	public UserPlugin getByUidPid(Integer userId, Integer pluginId, Integer agentId);

	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * @param getstr
	 * @return
	 */
	@SQL("SELECT * FROM t_user_plugin WHERE ##(:getStr) LIMIT 0,1")
	public UserPlugin getByStr(@SQLParam("getStr") String getStr);

	/**
	 * 增加
	 * @param userPlugin
	 * @return
	 */
	@SQL("insert into t_user_plugin(userId,pluginId,#if(:1.actNum){actNum,}#if(:1.overdueTime){overdueTime,}"
			+ "#if(:1.useType){useType,}#if(:1.agentId){agentId,}#if(:1.updateTime){updateTime,}createTime) "
			+ "values (:1.userId,:1.pluginId,#if(:1.actNum){:1.actNum,}#if(:1.overdueTime){:1.overdueTime,}"
			+ "#if(:1.useType){:1.useType,}#if(:1.agentId){:1.agentId,}#if(:1.updateTime){:1.updateTime,}:1.createTime)")
	public Identity add(UserPlugin userPlugin);

	/**
	 * 按指定属性修改字段
	 * @param third
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_user_plugin set ##(:key) = :value where id = :1")
	public int updateByCriteria(Integer id, @SQLParam("key") String key, @SQLParam("value") String value);

	/**
	 * 修改插件过期日期
	 * @param id
	 * @param overdueTime
	 * @return
	 */
	@SQL("update t_user_plugin set overdueTime = :2,updateTime = NOW() where id = :1")
	public int updateOverdueTime(Integer id, Date overdueTime);

	/**
	 * 更新实体
	 * @param userPlugin
	 * @return
	 */
	@SQL("update t_user_plugin set userId = :1.userId, pluginId = :1.pluginId, actNum = :1.actNum,"
			+ "overdueTime = :1.overdueTime,createTime = :1.createTime,useType = :1.useType,updateTime = :1.updateTime where id = :1.id")
	public int update(UserPlugin userPlugin);

	/**
	 * 按拼凑字符串更新
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_user_plugin set updateTime = NOW(), ##(:upstr) where id = :1")
	public int upByStr(Integer pluginId, @SQLParam("upstr") String upstr);

	/**
	 * 查询对象
	 * @param id
	 * @return
	 */
	@SQL("select * from t_user_plugin where id = :1")
	public UserPlugin getUserPluginById(Integer id);
}
