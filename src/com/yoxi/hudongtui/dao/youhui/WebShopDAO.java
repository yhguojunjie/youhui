package com.yoxi.hudongtui.dao.youhui;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.content.WebShop;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.plugin.PluginPicVo;

/**
 * 商城DAO
 * 
 * @author guojunjie 2015-8-2
 */
@DAO
public interface WebShopDAO {
	/**
	 * 查询全部商城数据
	 * 
	 * @param condition
	 *            查询条件
	 * @return List<WebShop>
	 */
	@SQL("SELECT * FROM t_webshop a LEFT JOIN t_goods_webshop b on a.id=b.webshop_id ##(:condition)")
	public List<WebShop> findwebShops(@SQLParam("condition") String condition)
			throws Exception;

	/**
	 * 查询插件列表信息
	 * 
	 * @param condition
	 *            查询条件
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	@SQL("SELECT a.id,a.icon,a.name,a.description,a.guide,(SELECT DISTINCT b.nickName from t_user b where b.userId = a.userId) AS nickName,"
			+ " a.publishTime,a.valid,a.buyNum "
			+ " FROM t_plugin a ##(:condition) LIMIT :2,:3")
	public List<PluginDetailVO> findPlugins(
			@SQLParam("condition") String condition, int startRow, int pageSize)
			throws Exception;

	/**
	 * 根据插件ID查询插件图片明细信息
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return List<PluginPic>
	 */
	@SQL("SELECT a.* FROM t_plugin_pic a WHERE a.pluginId = :1")
	public List<PluginPicVo> findPluginePics(Integer pluginId) throws Exception;

	/**
	 * 获取推荐插件
	 * 
	 * @param tjCondition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.uploadTime,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.updateTime,a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl,(select m.nickName from t_user m where m.userId = a.userId) as nickName FROM t_plugin a ##(:tjCondition)")
	public List<PluginDetailVO> findTjPlugines(
			@SQLParam("tjCondition") String tjCondition) throws Exception;

	/**
	 * 按id查找
	 * 
	 * @param pluginId
	 * @return
	 */
	@SQL("SELECT * FROM t_plugin WHERE id = :1")
	public Plugin findById(Integer pluginId);

	/**
	 * 按拼凑字符串更新
	 * 
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_plugin set updateTime = NOW() ##(:upstr) where id = :1")
	public int upByStr(Integer pluginId, @SQLParam("upstr") String upstr);

	/**
	 * 获取插件
	 * 
	 * @param tjCondition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.uploadTime,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.updateTime,a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl FROM t_plugin a where 1=1 ##(:condition)")
	public List<PluginDetailVO> findPluginesByCondition(
			@SQLParam("condition") String condition) throws Exception;

	/**
	 * 获取开发者插件
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.uploadTime,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.updateTime,a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl FROM t_plugin a where 1=1  ##(:condition) LIMIT 0,:2")
	public List<PluginDetailVO> finDevUserPlugins(
			@SQLParam("condition") String condition, int count)
			throws Exception;

	/**
	 * 获取开发者插件分页
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.uploadTime,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.updateTime,a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl FROM t_plugin a ##(:condition) LIMIT :2,:3")
	public List<PluginDetailVO> finDevUserPlugin4Page(
			@SQLParam("condition") String condition, int startRow, int pageSize)
			throws Exception;

	/**
	 * 获取代理商插件信息
	 * 
	 * @param pluginId
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl,a.showUrl,"
			+ " (SELECT c.nickName FROM t_user c where c.userId = a.userId) AS nickName,b.salePrice,b.onlineState,b.agentId "
			+ " FROM t_plugin a LEFT JOIN t_plugin_agent b ON  a.id = b.pluginId WHERE a.id = :1 AND b.agentId = :2 ")
	public PluginDetailVO getAgentPlugin(Integer pluginId, Integer agentId)
			throws Exception;

	/**
	 * 获取代理商插件列表
	 * 
	 * @param pluginId
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.id,a.name,a.userId,a.price,a.uploadTime,a.icon,a.description,a.buyNum,a.type,a.publishTime,a.status,a.valid,"
			+ " a.tryoutNum,a.actAddUrl,a.actEditUrl,a.actAccessUrl,a.showActId,a.guide,a.videoUrl,"
			+ " (SELECT c.nickName FROM t_user c where c.userId = a.userId) AS nickName,b.salePrice AS salePrice,b.agentId "
			+ " FROM t_plugin a LEFT JOIN t_plugin_agent b ON  a.id = b.pluginId WHERE a.status = 1 AND b.onlineState = 0 AND b.agentId = :1 ##(:condition) LIMIT :3,:4")
	public List<PluginDetailVO> getAgentPluginList(Integer agentId,
			@SQLParam("condition") String condition, int start, int count)
			throws Exception;
}
