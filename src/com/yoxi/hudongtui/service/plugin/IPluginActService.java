package com.yoxi.hudongtui.service.plugin;

import java.util.List;

import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.content.ActRecVO;
import com.yoxi.hudongtui.vo.plugin.MyActVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.plugin.UserPluginVo;

public interface IPluginActService {

	/**
	 * 查询用户插件分页对象
	 * @param param 用户ID
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActVo> findPluginActes4Page(String userId, String param, int currPage, int pageSize) throws Exception;

	/**
	 * 组装排序条件
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception;

	/**
	 * 获取推荐插件活动
	 * @param userId 推荐条件
	 * @return
	 * @throws Exception
	 */
	public List<PluginActVo> findTjPluginActes(String userId) throws Exception;

	/**
	 * 查询用户插件活动总数
	 * @param param 用户ID
	 * @param param 条件
	 */
	public int getUserPluginActCount(String userId, String param) throws Exception;

	/**
	 * 删除插件活动
	 * @param removeIds 插件ID
	 * @throws Exception
	 */
	public void removePluginAct(@SQLParam("removeIds") String removeIds) throws Exception;

	/**
	 * 获取活动
	 * @param userId 用户id条件
	 * @param count 查询的个数
	 * @throws Exception
	 */
	public List<PluginActVo> getPluginActes(String userId, int count) throws Exception;

	/**
	 * 获取其他用户活动
	 * @param userId 用户id条件
	 * @param count 查询的个数
	 * @throws Exception
	 */
	public List<PluginActVo> getOtherUserPluginActes(String userId, int count) throws Exception;

	/**
	 * 获取活动
	 * @param userId 用户id条件
	 * @throws Exception
	 */
	public int getPluginActeCount(String userId) throws Exception;

	/**
	 * 获取正在进行的活动
	 * @param condition
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<PluginActVo> findIngPluginActes(Integer pluginId) throws Exception;

	/**
	 * 按id查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PluginAct getById(Integer id) throws Exception;

	/**
	 * 按拼凑的字符串更新
	 * @param id
	 * @param upstr
	 * @return
	 */
	public int upByStr(Integer id, String upstr);

	/**
	 * 活动中心列表信息
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActCenterVo> findPluginActCenterVos(String param, int currPage, int pageSize) throws Exception;

	/**
	 * 活动中心列表信息(pc)
	 * @param orderFlag [1.热度，2.发布时间]
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActCenterVo> findPcPluginActCenterVos(String orderFlag, int currPage, int pageSize) throws Exception;

	/**
	 * 活动中心获取该用户未公开的活动
	 * @param userId
	 * @return List<PluginActVo>
	 */
	public List<PluginActVo> findNotOpenActes(int userId) throws Exception;

	/**
	 * （活动中心）发布活动到活动中心
	 * @param actOpenFlag TODO
	 * @param countOpenFlag TODO
	 * @param inv
	 * @param userId
	 * @return
	 */
	public void editActToOpen(String actOpenFlag, String countOpenFlag, String ActIds) throws Exception;
	
	/**
	 * 添加活动
	 * @param pluginAct
	 */
	public void addPluginAct(PluginAct pluginAct)throws Exception;
	
	/**
	 * 获取活动信息（推荐类）
	 * @param condition 用户推荐，插件对应推荐
	 * @throws Exception 
	 */
	public List<PluginActCenterVo> findActVos(String condition) throws Exception;
	
	/**
	 * 更新站外活动
	 * @param pluginAct
	 * @return
	 */
	public int upOutAct(PluginAct pluginAct);
	
	/****************************代理商版本********************************************/
	
	/**
	 * 查询活动列表
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ActRecVO> findActList(Integer agentId,String condition, int start,int count)throws Exception;
	
	/**
	 * 查询我的活动列表
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<MyActVO> findMyActList(Integer userId,Integer agentId,String condition)throws Exception;
	
	/**
	 * 分页查询我的活动列表
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Pagination<MyActVO> findMyActList4Page(Integer userId,Integer agentId,int currentPage,String condition)throws Exception;
	
	/**
	 * 计算我的活动数量
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getMyActCount(Integer userId,Integer agentId,String condition)throws Exception;

}
