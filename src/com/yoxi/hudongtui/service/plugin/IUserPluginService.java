package com.yoxi.hudongtui.service.plugin;

import java.util.List;

import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.UserPluginVo;

public interface IUserPluginService {

	/**
	 * 查询用户插件分页对象
	 * @param param 用户ID
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<UserPluginVo> findUserPlugins4Page(String userId, String param, int currPage, int pageSize) throws Exception;

	/**
	 * 查询用户插件总数
	 * @param param 用户ID
	 * @param param 条件
	 */
	public int getUserPluginCount(String userId, String param) throws Exception;

	/**
	 * 组装排序条件
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception;

	/**
	 * 查询用户插件总数
	 * @param userId 查询条件
	 */
	public List<UserPluginVo> findUserPlugins4AddAct(String userId) throws Exception;
	
	/**
	 * 按id查找
	 * @param id
	 * @return
	 */
	public UserPlugin getById(Integer id);

}
