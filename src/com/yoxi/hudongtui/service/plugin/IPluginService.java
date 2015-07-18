package com.yoxi.hudongtui.service.plugin;

import java.util.List;

import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.plugin.PluginPicVo;

public interface IPluginService {

	/**
	 * 查询插件分页对象
	 * 
	 * @param typeCon
	 *            插件类型条件
	 * @param condition
	 *            首页排序字段
	 * @param start
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginDetailVO> findPlugins4Page(String typeCon, String oderByCon, int currPage, int pageSize) throws Exception;

	/**
	 * 获取插件个数
	 * 
	 * @param userId
	 *            用户id条件
	 * @throws Exception
	 */
	public int getPluginCount(String userId) throws Exception;

	/**
	 * 根据插件ID查询插件图片明细信息
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return List<PluginPic>
	 */
	public List<PluginPicVo> findPluginePics(Integer pluginId) throws Exception;

	/**
	 * 获取推荐插件
	 * 
	 * @param tjCondition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	public List<PluginDetailVO> findTjPlugines(String tjCondition) throws Exception;

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String orderCon, String xFlag) throws Exception;

	/**
	 * 代理商查询排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getAgentOrderFlag(String orderCon, String xFlag) throws Exception;

	/**
	 * 按id查找
	 * 
	 * @param pluginId
	 * @return
	 */
	public Plugin getById(Integer pluginId);

	/**
	 * 获取插件
	 * 
	 * @param condition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	public List<PluginDetailVO> findPluginesByCondition(String condition) throws Exception;

	/**
	 * 获取开发者插件
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	public List<PluginDetailVO> finDevUserPlugins(String userId, int count) throws Exception;

	/**
	 * 获取开发者插件分页
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	public Pagination<PluginDetailVO> finDevUserPlugin4Page(String userId, int currPage, int pageSize) throws Exception;

	/**
	 * 组装类型条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getTypeCon(String typeCon) throws Exception;

	/**
	 * 获取代理商插件信息
	 * 
	 * @param pluginId
	 *            插件id
	 * @param agentId
	 *            代理商id
	 * @return
	 * @throws Exception
	 */
	public PluginDetailVO getAgentPlugin(Integer pluginId, Integer agentId) throws Exception;

	/**
	 * 获取代理商插件列表
	 * 
	 * @param agentId
	 * @param condition
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<PluginDetailVO> getAgentPluginList(Integer agentId, String condition, int start, int count) throws Exception;

}
