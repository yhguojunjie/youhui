package com.yoxi.hudongtui.service.plugin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.plugin.PluginDAO;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.plugin.PluginPicVo;

@Service
public class PluginServiceImpl implements IPluginService {
	@Autowired
	private PluginDAO pluginDao;

	/**
	 * 查询插件分页对象
	 * 
	 * @param typeCon
	 *            插件类型条件
	 * @param condition
	 *            首页排序字段
	 * @param currPage
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	@Override
	public Pagination<PluginDetailVO> findPlugins4Page(String typeCon, String oderByCon, int currPage, int pageSize) throws Exception {
		// 根据台排序字段组织语句
		String condition = typeCon + oderByCon + " ";
		// 获取插件总数
		int totalCount = pluginDao.getPluginCount(condition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0)
			startRow = 0;
		List<PluginDetailVO> pluginVos = pluginDao.findPlugins(condition, startRow, pageSize);
		return new Pagination<PluginDetailVO>(totalCount, pageSize, currPage, pluginVos);
	}

	/**
	 * 根据插件ID查询插件图片明细信息
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return List<PluginPic>
	 */
	@Override
	public List<PluginPicVo> findPluginePics(Integer pluginId) throws Exception {
		Plugin plugin = pluginDao.findById(pluginId);
		List<PluginPicVo> pluginPics = pluginDao.findPluginePics(pluginId);
		if (!ValidateUtil.isEmpty(pluginPics)) {
			for (PluginPicVo pluginPicVo : pluginPics) {
				pluginPicVo.setShowUrl(plugin.getShowUrl());
			}
		}
		return pluginPics;
	}

	/**
	 * 获取插件个数
	 * 
	 * @param userId
	 *            用户id条件
	 * @throws Exception
	 */
	public int getPluginCount(String userId) throws Exception {
		String condition = " and a.userId = " + userId;
		return pluginDao.getPluginCount(condition);
	}

	/**
	 * 获取推荐插件
	 * 
	 * @param tjCondition
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PluginDetailVO> findTjPlugines(String tjCondition) throws Exception {
		if (ValidateUtil.isEmpty(tjCondition)) {
			tjCondition = "";
		}
		return pluginDao.findTjPlugines(tjCondition);
	}

	@Override
	public Plugin getById(Integer pluginId) {
		return pluginDao.findById(pluginId);
	}

	@Override
	public PluginDetailVO getAgentPlugin(Integer pluginId, Integer agentId) throws Exception {
		return pluginDao.getAgentPlugin(pluginId, agentId);
	}

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getOderFlag(String orderCon, String xFlag) throws Exception {
		// orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		String orderConStr = "publishTime";
		if ("2".equals(orderCon)) {
			orderConStr = "buyNum";
		} else if ("3".equals(orderCon)) {
			orderConStr = "price";
		}
		// xFlag 0表示降序，1表示升序
		String sxFlag = " DESC ";
		// 0表示降序，1表示升序
		if ("1".equals(xFlag)) {
			sxFlag = "  ASC ";
		}
		return "ORDER BY a." + orderConStr + sxFlag + " ";
	}

	@Override
	public String getAgentOrderFlag(String orderCon, String xFlag) throws Exception {
		// orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		String orderConStr = " a.publishTime";
		if ("2".equals(orderCon)) {
			orderConStr = " a.buyNum";
		} else if ("3".equals(orderCon)) {
			orderConStr = "b.salePrice";
		}
		// xFlag 0表示降序，1表示升序
		String sxFlag = " DESC ";
		// 0表示降序，1表示升序
		if ("1".equals(xFlag)) {
			sxFlag = "  ASC ";
		}
		return "ORDER BY " + orderConStr + sxFlag + " ";
	}

	/**
	 * 组装类型条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getTypeCon(String typeCon) throws Exception {
		String typeCondition = " WHERE a.status = '1' ";
		// 0表示全部，1.表示即买即用
		if ("1".equals(typeCon)) {
			typeCondition = " WHERE a.type = '1' AND  a.status = '1' ";
		}
		return typeCondition;
	}

	/**
	 * 获取插件
	 * 
	 * @param condition
	 *            条件
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PluginDetailVO> findPluginesByCondition(String condition) throws Exception {
		return pluginDao.findPluginesByCondition(condition);
	}

	/**
	 * 获取开发者插件
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	@Override
	public List<PluginDetailVO> finDevUserPlugins(String userId, int count) throws Exception {
		String condition = " and a.userId = " + userId;
		return pluginDao.finDevUserPlugins(condition, count);
	}

	/**
	 * 获取开发者插件分页
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	@Override
	public Pagination<PluginDetailVO> finDevUserPlugin4Page(String userId, int currPage, int pageSize) throws Exception {
		String condition = " where 1=1 and a.userId = " + userId;
		// 获取用户插件总数
		int totalCount = pluginDao.getPluginCount(condition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0)
			startRow = 0;
		List<PluginDetailVO> PluginVos = pluginDao.finDevUserPlugin4Page(condition, startRow, pageSize);
		return new Pagination<PluginDetailVO>(totalCount, pageSize, currPage, PluginVos);
	}

	@Override
	public List<PluginDetailVO> getAgentPluginList(Integer agentId, String condition, int start, int count) throws Exception {
		return pluginDao.getAgentPluginList(agentId, condition, start, count);
	}

}
