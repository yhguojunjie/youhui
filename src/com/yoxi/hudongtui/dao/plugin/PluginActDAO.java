package com.yoxi.hudongtui.dao.plugin;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.vo.content.ActRecVO;
import com.yoxi.hudongtui.vo.plugin.MyActVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.plugin.PluginInfoVo;
import com.yoxi.hudongtui.vo.plugin.PluginShowActInfoVo;

/**
 * 用户插件信息维护DAO
 * 
 * @author xielj 2014-11-24
 */
@DAO
public interface PluginActDAO {

	/**
	 * 按id查找
	 * 
	 * @param id
	 * @return
	 */
	@SQL("select * from t_plugin_act where id = :1")
	public PluginAct getPluginActById(Integer id);

	/**
	 * 按拼凑字符串更新
	 * 
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_plugin_act set updateTime = NOW(), ##(:upstr) where id = :1")
	public int upByStr(Integer id, @SQLParam("upstr") String upstr);

	/**
	 * 按拼凑字符串来查
	 * 
	 * @param getStr
	 * @return
	 */
	@SQL("SELECT * FROM t_plugin_act WHERE ##(:getStr)")
	public List<PluginActVo> getByStr(@SQLParam("getStr") String getStr);

	/**
	 * 查询用户插件活动总数
	 * 
	 * @param condition
	 *            查询条件
	 */
	@SQL("SELECT count(*) from " + " (SELECT a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,a.pluginId,b.overdueTime,"
			+ " (select m.icon from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as pluginIcon,"
			+ " (select m.actAddUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAddUrl,"
			+ " (select m.actEditUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actEditUrl,"
			+ " (select m.actAccessUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAccessUrl"
			+ " FROM t_plugin_act a,t_user_plugin b WHERE a.userPluginId = b.id and a.type = '0' and a.dataStatus = '0' ##(:condition)" + " UNION "
			+ " SELECT a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,a.pluginId,now() as overdueTime,'' as pluginIcon,'' as actAddUrl,'' as actEditUrl,'' as actAccessUrl"
			+ " FROM t_plugin_act a  WHERE a.type = '1' and a.dataStatus = '0' ##(:zwCondition))f ")
	public int getUserPluginActCount(@SQLParam("condition") String condition, @SQLParam("zwCondition") String zwCondition) throws Exception;

	/**
	 * 查询用户插件活动列表信息
	 * 
	 * @param condition
	 *            查询条件[0.全部，1.未开始，2.正在进行,3.已结束]
	 * @param zwCondition
	 *            站外活动条件
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<UserPluginVo>
	 */
	@SQL("SELECT f.* from " + " (SELECT a.createTime,a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,b.pluginId,b.overdueTime,"
			+ " (select m.icon from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as pluginIcon,"
			+ " (select m.actAddUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAddUrl,"
			+ " (select m.actEditUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actEditUrl,"
			+ " (select m.actAccessUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAccessUrl"
			+ " FROM t_plugin_act a,t_user_plugin b WHERE a.userPluginId = b.id and a.type = '0' and a.dataStatus = '0' ##(:condition)" + " UNION "
			+ " SELECT a.createTime,a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,a.pluginId,now() as overdueTime,'' as pluginIcon,'' as actAddUrl,'' as actEditUrl,'' as actAccessUrl"
			+ " FROM t_plugin_act a  WHERE a.type = '1' and a.dataStatus = '0' ##(:zwCondition)) f ORDER BY f.createTime DESC LIMIT :3,:4")
	public List<PluginActVo> findUserPluginActs(@SQLParam("condition") String condition, @SQLParam("zwCondition") String zwCondition, int startRow, int pageSize) throws Exception;

	/**
	 * 查询开发者开发模版列表信息
	 * 
	 * @param condition
	 *            查询条件
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<PluginDetailVO>
	 */
	public List<PluginDetailVO> findUserPluginDetail(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 获取推荐插件活动
	 * 
	 * @param tjCondition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.icon,a.accessUrl,b.pluginId,b.overdueTime,b.userId,"
			+ " (select m.nickName from t_user m where m.userId = b.userId) as nickName,"
			+ " (select m.icon from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as pluginIcon,"
			+ " (select m.actAddUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAddUrl,"
			+ " (select m.actEditUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actEditUrl,"
			+ " (select m.actAccessUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAccessUrl"
			+ "  FROM t_plugin_act a,t_user_plugin b WHERE a.userPluginId = b.id and a.type = '0' and a.dataStatus = '0' ##(:tjCondition) ORDER BY a.browseNum DESC LIMIT 0,3")
	public List<PluginActVo> findTjPluginActes(@SQLParam("tjCondition") String tjCondition) throws Exception;

	/**
	 * 获取活动
	 * 
	 * @param condition
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT f.* from " + " (SELECT a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,b.pluginId,b.overdueTime,"
			+ " (select m.icon from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as pluginIcon,"
			+ " (select m.actAddUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAddUrl,"
			+ " (select m.actEditUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actEditUrl,"
			+ " (select m.actAccessUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAccessUrl"
			+ " FROM t_plugin_act a,t_user_plugin b WHERE a.userPluginId = b.id and a.type = '0' and a.dataStatus = '0' ##(:condition)" + " UNION "
			+ " SELECT a.id,a.userPluginId,a.title,a.joinNum,a.browseNum,a.startTime,a.endTime,a.accessUrl,a.aplyRecommend,a.sequence,a.icon,"
			+ " a.qrcodeUrl,a.actOpen,a.countOpen,a.type,a.userId,a.pluginId,now() as overdueTime,'' as pluginIcon,'' as actAddUrl,'' as actEditUrl,'' as actAccessUrl"
			+ " FROM t_plugin_act a  WHERE a.type = '1' and a.dataStatus = '0'  ##(:zwCondition)) f ORDER BY f.browseNum DESC LIMIT 0,:3")
	public List<PluginActVo> getPluginActes(@SQLParam("condition") String condition, @SQLParam("zwCondition") String zwCondition, int count) throws Exception;

	/**
	 * 获取活动个数
	 * 
	 * @param condition
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT count(*) from t_plugin_act a where a.dataStatus = '0' ##(:condition)")
	public int getPluginActeCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 获取正在进行的活动
	 * 
	 * @param condition
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.*,b.userId,(select k.nickName from t_user k where k.userId = b.userId ) as nickName,"
			+ " (select m.icon from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as pluginIcon,"
			+ " (select m.actAddUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAddUrl,"
			+ " (select m.actEditUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actEditUrl,"
			+ " (select m.actAccessUrl from t_plugin m where m.id = (select DISTINCT s.pluginId from t_user_plugin s where s.id = a.userPluginId)) as actAccessUrl"
			+ " from t_plugin_act a,t_user_plugin b" + " where a.userPluginId = b.id and a.dataStatus = '0' "
			+ " and now() <= b.overdueTime and now() >= a.startTime and now() <= a.endTime and a.browseNum > " + Globals.ACT_BROWSERNUM_LIMIT + " and b.pluginId = :1 ORDER BY a.browseNum DESC")
	public List<PluginActVo> findIngPluginActes(int pluginId) throws Exception;

	/**
	 * 删除插件活动
	 * 
	 * @param removeIds
	 *            插件ID
	 * @throws Exception
	 */
	@SQL("UPDATE  t_plugin_act a set a.dataStatus = '1',a.updateTime = NOW() where a.id in ##(:removeIds)")
	public void removePluginAct(@SQLParam("removeIds") String removeIds) throws Exception;

	/**
	 * 添加活动
	 * 
	 * @param pluginAct
	 * @return
	 */
	@SQL("insert into t_plugin_act(#if(:1.title){title,}#if(:1.icon){icon,}#if(:1.joinNum){joinNum,}#if(:1.browseNum){browseNum,}"
			+ "#if(:1.qrcodeUrl){qrcodeUrl,}#if(:1.actOpen){actOpen,}#if(:1.countOpen){countOpen,}" + "#if(:1.userId){userId,}#if(:1.pluginId){pluginId,}#if(:1.agentId){agentId,}#if(:1.type){type,}"
			+ "#if(:1.startTime){startTime,}#if(:1.endTime){endTime,}#if(:1.accessUrl){accessUrl,}"
			+ "#if(:1.aplyRecommend){aplyRecommend,}#if(:1.sequence){sequence,}#if(:1.updateTime){updateTime,}#if(:1.userPluginId){userPluginId,}createTime) "
			+ "values (#if(:1.title){:1.title,}#if(:1.icon){:1.icon,}#if(:1.joinNum){:1.joinNum,}#if(:1.browseNum){:1.browseNum,}"
			+ "#if(:1.qrcodeUrl){:1.qrcodeUrl,}#if(:1.actOpen){:1.actOpen,}#if(:1.countOpen){:1.countOpen,}"
			+ "#if(:1.userId){:1.userId,}#if(:1.pluginId){:1.pluginId,}#if(:1.agentId){:1.agentId,}#if(:1.type){:1.type,}"
			+ "#if(:1.startTime){:1.startTime,}#if(:1.endTime){:1.endTime,}#if(:1.accessUrl){:1.accessUrl,}"
			+ "#if(:1.aplyRecommend){:1.aplyRecommend,}#if(:1.sequence){:1.sequence,}#if(:1.updateTime){:1.updateTime,}#if(:1.userPluginId){:1.userPluginId,}NOW())")
	public Identity add(PluginAct pluginAct);

	/**
	 * 更新插件信息
	 * 
	 * @param pluginInfoVo
	 * @return
	 */
	@SQL("update t_plugin_act set #if(:1.title){title=:1.title,}#if(:1.icon){icon=:1.icon,}" + "#if(:1.startTime){startTime=:1.startTime,}#if(:1.endTime){endTime=:1.endTime,}"
			+ "updateTime = NOW() where id = :1.activityId")
	public int updatePluginInfo(PluginInfoVo pluginInfoVo);

	/**
	 * 设置活动公开状态
	 */
	@SQL("UPDATE t_plugin_act a set a.actOpen = ##(:actOpenFlag),a.countOpen = ##(:countOpenFlag),a.updateTime = NOW() where a.id in ##(:ActIds)")
	public void editActToOpen(@SQLParam("actOpenFlag") String actOpenFlag, @SQLParam("countOpenFlag") String countOpenFlag, @SQLParam("ActIds") String ActIds) throws Exception;

	/**
	 * 修改站外活动
	 * 
	 * @param pluginAct
	 * @return
	 */
	@SQL("update t_plugin_act set title = :1.title,icon = :1.icon,startTime = :1.startTime,endTime = :1.endTime," + "accessUrl=:1.accessUrl, updateTime = NOW() where id = :1.id")
	public int upOutAct(PluginAct pluginAct);

	/**
	 * 获取活动信息总数（推荐类）
	 * 
	 * @param condition
	 *            用户推荐，插件对应推荐
	 */
	@SQL("select count(*) from t_plugin_act a,t_user_plugin b" + " where a.userPluginId = b.id  and a.dataStatus = '0' and a.type = '0' and a.actOpen = '0' ##(:condition)")
	public List<PluginActCenterVo> findActVosCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 获取活动信息（推荐类）
	 * 
	 * @param condition
	 *            用户推荐，插件对应推荐
	 */
	@SQL("select a.countOpen,a.accessUrl,a.type,a.createTime,a.id as actId,a.title as actTitle,a.joinNum as actJoinNum,a.browseNum as actBrowseNum,b.pluginId,b.userId,"
			+ " (case  when a.icon is null or a.icon =''  then (SELECT d.icon from t_plugin d WHERE d.id = b.pluginId) else a.icon end) as actIcon,"
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = b.userId) as headimgUrl,"
			+ " (SELECT (case when c.introduce is null or a.icon ='' then '还没有简介' else  c.introduce end) from t_user c WHERE c.userId = b.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = b.userId) as nickName," + " (SELECT d.name from t_plugin d WHERE d.id = b.pluginId) as pluginName,"
			+ " (SELECT d.actAccessUrl from t_plugin d WHERE d.id = b.pluginId) as actAccessUrl, "
			+ " (SELECT e.id from t_channel e WHERE e.userId = a.userId and e.auditState = '1' LIMIT 0,1) as channelId " + " from t_plugin_act a,t_user_plugin b"
			+ " where a.userPluginId = b.id  and a.dataStatus = '0'  and a.type = '0' and a.actOpen = '0' ##(:condition)")
	public List<PluginActCenterVo> findActVos(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查找演示id号
	 * 
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	@SQL("select b.pluginId, c.showActId from t_plugin_act a, t_user_plugin b, t_plugin c " + "where a.id = :1 and a.userPluginId = b.id and b.pluginId = c.id")
	public PluginShowActInfoVo findShowActId(Integer activityId) throws Exception;

	/**
	 * 活动中心总数
	 * 
	 * @param condition
	 *            查询条件
	 */
	@SQL("SELECT count(*) from(" + " select a.createTime,a.id as actId,a.title as actTitle,a.joinNum as actJoinNum,a.browseNum as actBrowseNum,a.pluginId,a.userId,a.agentId"
			+ " (case  when a.icon is null or a.icon =''  then (SELECT d.icon from t_plugin d WHERE d.id = a.pluginId) else a.icon end) as actIcon,"
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = a.userId) as headimgUrl," + " (SELECT c.introduce from t_user c WHERE c.userId = a.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = a.userId) as nickName," + " (SELECT d.name from t_plugin d WHERE d.id = a.pluginId) as pluginName,"
			+ " (SELECT d.actAccessUrl from t_plugin d WHERE d.id = a.pluginId) as actAccessUrl" + " from t_plugin_act a" + " where a.dataStatus = '0'  and a.type = '0' ##(:condition)" + " UNION"
			+ " select a.createTime,a.id as actId,a.title as actTitle,a.joinNum as actJoinNum,a.browseNum as actBrowseNum,a.pluginId,a.userId,a.icon,a.agentId"
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = a.userId) as headimgUrl," + " (SELECT c.introduce from t_user c WHERE c.userId = a.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = a.userId) as nickName," + " '' as pluginName," + " '' as  actAccessUrl" + " from t_plugin_act a"
			+ " where a.dataStatus = '0' and a.type = '1' ##(:zwCondition)" + " ) f ")
	public int getPluginActCenterCount(@SQLParam("condition") String condition, @SQLParam("zwCondition") String zwCondition) throws Exception;

	/**
	 * 未发布到活动中心的活动
	 * 
	 * @param condition
	 *            查询条件
	 * @return List<UserPluginVo>
	 */
	@SQL("select a.* from t_plugin_act a,t_user_plugin b" + " where a.userPluginId = b.id  and a.dataStatus = '0'  ##(:condition)")
	public List<PluginActVo> findNotOpenActes(@SQLParam("condition") String condition) throws Exception;

	/****************************** 代理商版本 ******************************************/

	/**
	 * 查找活动列表
	 * 
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.countOpen,a.accessUrl,a.type,a.createTime,a.id as actId,a.title as actTitle,a.joinNum as actJoinNum,a.browseNum as actBrowseNum,a.pluginId,a.userId,a.agentId, "
			+ " CASE WHEN a.icon is null then (SELECT d.icon from t_plugin d WHERE d.id = a.pluginId) else a.icon end as actIcon, "
			+ " (SELECT c.headimgUrl from t_user c WHERE c.userId = a.userId) as headimgUrl," + " (SELECT c.introduce from t_user c WHERE c.userId = a.userId) as introduce,"
			+ " (SELECT c.nickName from t_user c WHERE c.userId = a.userId) as nickName,"
			+ " (SELECT d.name from t_plugin d WHERE d.id = a.pluginId) as pluginName,(SELECT e.id from t_channel e WHERE e.userId = a.userId and e.auditState='1' LIMIT 0,1) as channelId,"
			+ " (SELECT d.actAccessUrl from t_plugin d WHERE d.id = a.pluginId) as actAccessUrl" + " FROM t_plugin_act a,t_user_plugin b"
			+ " WHERE a.userPluginId = b.id and a.dataStatus='0' ##(:condition) LIMIT :2,:3")
	public List<ActRecVO> findActList(@SQLParam("condition") String condition, int start, int count) throws Exception;

	/**
	 * 查找我的活动列表
	 * 
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id AS userPluginId,a.overdueTime, b.id AS actId, b.title AS actTitle, b.browseNum,b.joinNum, b.startTime, b.endTime,a.pluginId, "
			+ " CASE WHEN b.icon is null THEN c.icon ELSE b.icon END AS actIcon, " + " c.icon AS pluginIcon,c.actAccessUrl,c.actAddUrl,c.actEditUrl,c.name AS pluginName,c.description AS pluginDesc "
			+ " FROM (t_user_plugin a LEFT JOIN t_plugin_act b on a.id = b.userPluginId) LEFT JOIN t_plugin c on a.pluginId = c.id " + " WHERE a.userId = :1 AND a.agentId = :2  ##(:condition) ")
	public List<MyActVO> findMyActList(Integer userId, Integer agentId, @SQLParam("condition") String condition) throws Exception;

	/**
	 * 计算活动数量
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT COUNT(*) FROM t_user_plugin a LEFT JOIN t_plugin_act b on a.id = b.userPluginId " + " WHERE a.userId = :1 AND a.agentId = :2 ##(:condition) ")
	public Integer getMyActCount(Integer userId, Integer agentId, @SQLParam("condition") String condition) throws Exception;
}
