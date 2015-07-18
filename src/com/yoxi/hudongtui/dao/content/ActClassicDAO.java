package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.vo.content.ActClassicVO;

/**
 * 
 * 经典活动
 * 
 * @author wql
 * 
 * @Date 2015年3月22日
 * 
 */
@DAO
public interface ActClassicDAO {
	/**
	 * 经典活动列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ActClassicVO>
	 */
	@SQL(" select f.* from ((SELECT a1.id,a1.updateTime,a1.seq,a1.userId,a1.actId,a1.pluginId,  " + " (SELECT c1.name FROM t_plugin c1 WHERE c1.id = a1.pluginId) AS pluginName,"
			+ " (SELECT c1.actAccessUrl FROM t_plugin c1 WHERE c1.id = a1.pluginId ) AS actAccessUrl, " + " (SELECT e1.url FROM t_act_classic_pic e1 WHERE e1.classicId = a1.id LIMIT 0,1) "
			+ " AS actClassicPicUrl, (SELECT c1.icon FROM t_plugin c1 WHERE c1.id = a1.pluginId) " + " AS actIcon, a1.bannerName  AS bannerName, a1.title  AS actTitle, a1.startTime  AS startTime,"
			+ " a1.endTime  AS endTime,  a1.browseNum  AS browseNum,a1.joinNum  AS joinNum, a1.bannerLogo " + " AS bannerLogo FROM t_act_classic a1 where a1.type=2)  union  "

			+ " (SELECT a.id,a.updateTime,a.seq,a.actId,a.userId,a.pluginId, " + " (SELECT c.name FROM t_plugin c WHERE c.id = a.pluginId) AS pluginName,"
			+ " (SELECT c.actAccessUrl FROM t_plugin c WHERE c.id = a.pluginId ) AS actAccessUrl," + " (SELECT e.url FROM t_act_classic_pic e WHERE e.classicId = a.id LIMIT 0,1) AS actClassicPicUrl,"
			+ " CASE WHEN b.icon is NULL THEN (SELECT c.icon FROM t_plugin c WHERE c.id = a.pluginId) ELSE b.icon END AS actIcon,"
			+ " CASE WHEN a.bannerName is NULL THEN (SELECT d.nickName FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerName END AS bannerName,"
			+ " CASE WHEN a.title is NULL THEN b.title ELSE a.title END AS actTitle," + " CASE WHEN a.startTime is NULL THEN b.startTime ELSE a.startTime END AS startTime,"
			+ " CASE WHEN a.endTime is NULL THEN b.endTime ELSE a.endTime END AS endTime," + " CASE WHEN a.browseNum is NULL THEN b.browseNum ELSE a.browseNum END AS browseNum,"
			+ " CASE WHEN a.joinNum is NULL THEN b.joinNum ELSE a.joinNum END AS joinNum,"
			+ " CASE WHEN a.bannerLogo is NULL THEN (SELECT d.headimgUrl FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerLogo END AS bannerLogo"
			+ " FROM t_act_classic a LEFT JOIN t_plugin_act b ON a.actId = b.id WHERE b.dataStatus = '0'))f ORDER BY f.seq ASC,f.updateTime DESC ##(:condition) LIMIT :2,:3 ")
	public List<ActClassicVO> findRecList(@SQLParam("condition") String condition, int start, int count) throws Exception;

	/**
	 * 经典活动列表信息（代理商）
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ActClassicVO>
	 */
	@SQL(" select f.* from ((SELECT aaa1.agentId,a1.id,a1.updateTime,aaa1.seq,a1.userId,a1.actId,a1.pluginId,  "
			+ " (SELECT c1.name FROM t_plugin c1 WHERE c1.id = a1.pluginId) AS pluginName,"
			+ " (SELECT c1.actAccessUrl FROM t_plugin c1 WHERE c1.id = a1.pluginId ) AS actAccessUrl, "
			+ " (SELECT e1.url FROM t_act_classic_pic e1 WHERE e1.classicId = a1.id LIMIT 0,1) "
			+ " AS actClassicPicUrl, (SELECT c1.icon FROM t_plugin c1 WHERE c1.id = a1.pluginId) "
			+ " AS actIcon, a1.bannerName  AS bannerName, a1.title  AS actTitle, a1.startTime  AS startTime,"
			+ " a1.endTime  AS endTime,  a1.browseNum  AS browseNum,a1.joinNum  AS joinNum, a1.bannerLogo "
			+ " AS bannerLogo FROM t_agent_classic aaa1 LEFT JOIN t_act_classic a1 on aaa1.classicId=a1.id where a1.type=2 and a1.auditState=1 and aaa1.hideState=0)  union  "

			+ " (SELECT aaa1.agentId,a.id,a.updateTime,aaa1.seq,a.actId,a.userId,a.pluginId, "
			+ " (SELECT c.name FROM t_plugin c WHERE c.id = a.pluginId) AS pluginName,"
			+ " (SELECT c.actAccessUrl FROM t_plugin c WHERE c.id = a.pluginId ) AS actAccessUrl,"
			+ " (SELECT e.url FROM t_act_classic_pic e WHERE e.classicId = a.id LIMIT 0,1) AS actClassicPicUrl,"
			+ " CASE WHEN b.icon is NULL THEN (SELECT c.icon FROM t_plugin c WHERE c.id = a.pluginId) ELSE b.icon END AS actIcon,"
			+ " CASE WHEN a.bannerName is NULL THEN (SELECT d.nickName FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerName END AS bannerName,"
			+ " CASE WHEN a.title is NULL THEN b.title ELSE a.title END AS actTitle,"
			+ " CASE WHEN a.startTime is NULL THEN b.startTime ELSE a.startTime END AS startTime,"
			+ " CASE WHEN a.endTime is NULL THEN b.endTime ELSE a.endTime END AS endTime,"
			+ " CASE WHEN a.browseNum is NULL THEN b.browseNum ELSE a.browseNum END AS browseNum,"
			+ " CASE WHEN a.joinNum is NULL THEN b.joinNum ELSE a.joinNum END AS joinNum,"
			+ " CASE WHEN a.bannerLogo is NULL THEN (SELECT d.headimgUrl FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerLogo END AS bannerLogo"
			+ " FROM t_agent_classic aaa1 LEFT JOIN t_act_classic a on aaa1.classicId=a.id LEFT JOIN t_plugin_act b ON a.actId = b.id WHERE b.dataStatus = '0' and a.auditState='1' and aaa1.hideState='0' ))f ##(:condition) ORDER BY f.seq ASC,f.updateTime DESC  LIMIT :2,:3 ")
	public List<ActClassicVO> findAgentRecList(@SQLParam("condition") String condition, int start, int count) throws Exception;

	/**
	 * 
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.actId, b.title,b.startTime,b.endTime,b.browseNum,b.joinNum,b.pluginId, " + " (SELECT c.name FROM t_plugin c WHERE c.id = b.pluginId) AS pluginName,"
			+ " (SELECT c.actAccessUrl FROM t_plugin c WHERE c.id = b.pluginId ) AS actAccessUrl,"
			+ " CASE WHEN b.icon is NULL THEN (SELECT c.icon FROM t_plugin c WHERE c.id = b.pluginId) ELSE b.icon END AS actIcon,"
			+ " CASE WHEN a.bannerName is NULL THEN (SELECT d.nickName FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerName END AS bannerName,"
			+ " CASE WHEN a.bannerLogo is NULL THEN (SELECT d.headimgUrl FROM t_user d WHERE d.userId = b.userId ) ELSE a.bannerLogo END AS bannerLogo"
			+ " FROM t_act_classic a LEFT JOIN t_plugin_act b ON a.actId = b.id WHERE b.dataStatus = '0' ##(:condition) LIMIT :2,:3")
	public List<ActClassicDAO> findActClassicVO(@SQLParam("condition") String condition, int start, int count) throws Exception;
}
