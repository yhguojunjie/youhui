package com.yoxi.hudongtui.service.plugin.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.plugin.PluginActDAO;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.content.ActRecVO;
import com.yoxi.hudongtui.vo.plugin.MyActVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;

@Service
public class PluginActServiceImpl implements IPluginActService {
	@Autowired
	private PluginActDAO pluginActDao;
	@Autowired
	private UserPluginDAO userPluginDAO;

	/**
	 * 查询用户插件分页对象
	 * 
	 * @param param
	 *            用户ID
	 * @param param
	 *            条件
	 * @param start
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActVo> findPluginActes4Page(String userId, String param, int currPage, int pageSize) throws Exception {
		// 条件
		String condition = getOderFlag(userId, param);
		String zwCondition = getZwOderFlag(userId, param);
		// 获取用户插件总数
		int totalCount = pluginActDao.getUserPluginActCount(condition, zwCondition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0)
			startRow = 0;
		List<PluginActVo> pluginActVos = pluginActDao.findUserPluginActs(condition, zwCondition, startRow, pageSize);
		// 判断活动执行情况[0.全部，1.未开始，2.正在进行,3.已结束,4.插件以过期]
		Date dt = new Date();
		for (PluginActVo pluginActVo : pluginActVos) {
			Date startTime = pluginActVo.getStartTime();
			Date endTime = pluginActVo.getEndTime();
			Date overdueTime = pluginActVo.getOverdueTime();
			String type = pluginActVo.getType();
			// 插件过期判断
			if ("0".equals(type)) {
				if (overdueTime != null && dt.after(overdueTime)) {
					pluginActVo.setActiveFlag("4");
				} else {
					// 活动过期判断
					if (startTime != null && dt.before(startTime)) {
						pluginActVo.setActiveFlag("1");
					} else if (endTime != null && dt.after(endTime)) {
						pluginActVo.setActiveFlag("3");
					} else {
						pluginActVo.setActiveFlag("2");
					}
				}

			} else {
				if (startTime != null && dt.before(startTime)) {
					pluginActVo.setActiveFlag("1");
				} else if (endTime != null && dt.after(endTime)) {
					pluginActVo.setActiveFlag("3");
				} else {
					pluginActVo.setActiveFlag("2");
				}
			}

			// 没有活动图标处理，取插件图片做为活动图标
			if (StringUtils.isNullBlank(pluginActVo.getIcon())) {
				pluginActVo.setIcon(pluginActVo.getPluginIcon());
			}

		}
		return new Pagination<PluginActVo>(totalCount, pageSize, currPage, pluginActVos);
	}

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception {
		// String condition =
		// " and EXISTS ( select 1 from t_user_plugin k where k.id = a.userPluginId and k.userId ="
		// + userId + ")";
		String condition = " and a.userId =" + userId;
		// 判断活动执行情况[0.全部，1.未开始，2.正在进行,3.已结束]
		if ("1".equals(param)) {
			condition += " and now() <= b.overdueTime ";
			condition += " and now() < a.startTime ";
		} else if ("2".equals(param)) {
			condition += " and now() <= b.overdueTime ";
			condition += " and now() >= a.startTime and now() <= a.endTime ";
		} else if ("3".equals(param)) {
			condition += " and (now() > b.overdueTime ";
			condition += " or now() > a.endTime) ";
		} else if ("4".equals(param)) {
			condition += " and (now() <= b.overdueTime ";
			condition += " or now() <= a.endTime) ";
		}
		return condition;
	}

	/**
	 * 组装站外排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getZwOderFlag(String userId, String param) throws Exception {
		String condition = " and a.userId =" + userId;
		// 判断活动执行情况[0.全部，1.未开始，2.正在进行,3.已结束]
		if ("1".equals(param)) {
			condition += " and now() < a.startTime ";
		} else if ("2".equals(param)) {
			condition += " and now() >= a.startTime and now() <= a.endTime ";
		} else if ("3".equals(param)) {
			condition += " and now() > a.endTime ";
		} else if ("4".equals(param)) {
			condition += " and now() <= a.endTime ";
		}
		return condition;
	}

	/**
	 * 活动中心列表信息
	 * 
	 * @param param
	 *            条件
	 * @param start
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActCenterVo> findPluginActCenterVos(String param, int currPage, int pageSize) throws Exception {
		// // 条件
		// String condition = getCondition(param);
		// String zwCondition = getZwOderFlag(param);
		// String oderByCondition = getOderByFlag(param);
		// // 获取活动总数
		// int totalCount = pluginActDao.getPluginActCenterCount(condition,
		// zwCondition);
		// // 计算开始行
		// int startRow = (currPage - 1) * pageSize;
		// if (startRow < 0) startRow = 0;
		// List<PluginActCenterVo> pluginActCenterVos =
		// pluginActDao.findPluginActCenterVos(condition, zwCondition,
		// oderByCondition, startRow, pageSize);
		// return new Pagination<PluginActCenterVo>(totalCount, pageSize,
		// currPage, pluginActCenterVos);
		return null;
	}

	/**
	 * 活动中心列表信息(pc)
	 * 
	 * @param orderFlag
	 *            [1.热度，2.发布时间]
	 * @param start
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<PluginActCenterVo> findPcPluginActCenterVos(String orderFlag, int currPage, int pageSize) throws Exception {

		/*
		 * // 条件 String condition = getCondition(orderFlag); String zwCondition
		 * = getZwOderFlag(orderFlag); String oderByCondition =
		 * getOderByFlag(orderFlag); // 获取活动总数 int totalCount =
		 * pluginActDao.getPluginActCenterCount(condition, zwCondition); //
		 * 计算开始行 int startRow = (currPage - 1) * pageSize; if (startRow < 0)
		 * startRow = 0; List<PluginActCenterVo> pluginActCenterVos =
		 * pluginActDao.findPluginActCenterVos(condition, zwCondition,
		 * oderByCondition, startRow, pageSize); for(PluginActCenterVo pvo :
		 * pluginActCenterVos){ // 设置头像 if (pvo.getHeadimgUrl() != null) { if
		 * (pvo.getHeadimgUrl().startsWith("group")) {
		 * pvo.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") +
		 * pvo.getHeadimgUrl()); } } } return new
		 * Pagination<PluginActCenterVo>(totalCount, pageSize, currPage,
		 * pluginActCenterVos);
		 */

		return null;
	}

	@Override
	public List<ActRecVO> findActList(Integer agentId, String condition, int start, int count) throws Exception {
		// 封装条件
		/*
		 * String zncondition = " and a.agentId = "+agentId+
		 * " AND NOW() <= b.overdueTime and NOW() <= a.endTime and NOW() >= a.startTime AND a.browseNum >= "
		 * +Globals.Max_SIZE+" AND a.actOpen = '0' "; // String zwCondition =
		 * " and a.agentId = "+agentId+
		 * " AND a.actOpen = '0' and NOW() <= a.endTime AND NOW() >= a.startTime "
		 * ; String oderByCondition = ""; if ("1".equals(orderFlag)) {
		 * oderByCondition += " ORDER BY f.actBrowseNum DESC "; } else {
		 * oderByCondition += " ORDER BY f.createTime DESC "; }
		 */
		List<ActRecVO> actRecVOs = pluginActDao.findActList(condition, start, count);
		return actRecVOs;
	}

	/**
	 * 站内活动过滤条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getCondition(String param) throws Exception {
		String condition = " and now() <= b.overdueTime and now() <= a.endTime and now() >= a.startTime and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.actOpen = '0' ";
		return condition;
	}

	/**
	 * 站外活动过滤条件
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getZwOderFlag(String param) throws Exception {
		String condition = " and a.actOpen = '0' and now() <= a.endTime and now() >= a.startTime";
		return condition;
	}

	/**
	 * 站内与站外活动联合查询后排序条件
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getOderByFlag(String param) throws Exception {
		String condition = " ";
		// [1.热度，2.发布时间]
		if ("1".equals(param)) {
			condition += " ORDER BY f.actBrowseNum DESC ";
		} else {
			condition += " ORDER BY f.createTime DESC ";
		}
		return condition;
	}

	/**
	 * 查询用户插件活动总数
	 * 
	 * @param param
	 *            用户ID
	 * @param param
	 *            条件
	 */
	public int getUserPluginActCount(String userId, String param) throws Exception {
		String condition = getOderFlag(userId, param);
		String zwCondition = getZwOderFlag(userId, param);
		return pluginActDao.getUserPluginActCount(condition, zwCondition);
	}

	/**
	 * 获取推荐插件活动
	 * 
	 * @param userId
	 *            推荐条件
	 * @return
	 * @throws Exception
	 */
	public List<PluginActVo> findTjPluginActes(String userId) throws Exception {
		String tjCondition = "and now() <= b.overdueTime and now() <= a.endTime and  now() >= a.startTime and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.userId !=" + userId;
		List<PluginActVo> pluginActList = pluginActDao.findTjPluginActes(tjCondition);
		// 没有活动图标处理，取插件图片做为活动图标
		if (CollectionUtils.isNotEmpty(pluginActList)) {
			for (PluginActVo pluginActVo : pluginActList) {
				if (StringUtils.isNullBlank(pluginActVo.getIcon())) {
					pluginActVo.setIcon(pluginActVo.getPluginIcon());
				}
			}
		}
		return pluginActList;
	}

	/**
	 * 删除插件活动
	 * 
	 * @param removeIds
	 *            插件ID
	 * @throws Exception
	 */
	public void removePluginAct(String removeIds) throws Exception {
		if (!ValidateUtil.isEmpty(removeIds)) {
			removeIds = removeIds.substring(0, removeIds.length() - 1);
			// 删除活动
			pluginActDao.removePluginAct("(" + removeIds + ")");
			// 获取活动
			String[] idArr = removeIds.split(",");
			if (!ValidateUtil.isEmpty(idArr)) {
				for (String id : idArr) {
					int _id = Integer.valueOf(id);
					PluginAct pluginAct = pluginActDao.getPluginActById(_id);
					// 获取活动对应的用户插件
					UserPlugin userPlugin = userPluginDAO.getUserPluginById(pluginAct.getUserPluginId());
					// 更新活动数
					if (userPlugin.getActNum() > 0) {
						userPlugin.setActNum(userPlugin.getActNum() - 1);
					}
					userPluginDAO.update(userPlugin);
				}
			}
		}
	}

	/**
	 * 获取活动
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	public List<PluginActVo> getPluginActes(String userId, int count) throws Exception {
		String condition = " and a.userId = " + userId;
		List<PluginActVo> pluginActList = pluginActDao.getPluginActes(condition, condition, count);
		// 没有活动图标处理，取插件图片做为活动图标
		if (CollectionUtils.isNotEmpty(pluginActList)) {
			for (PluginActVo pluginActVo : pluginActList) {
				if (StringUtils.isNullBlank(pluginActVo.getIcon())) {
					pluginActVo.setIcon(pluginActVo.getPluginIcon());
				}
			}
		}
		return pluginActList;
	}

	/**
	 * 获取其他用户活动
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @throws Exception
	 */
	public List<PluginActVo> getOtherUserPluginActes(String userId, int count) throws Exception {
		String condition = " and now() <= b.overdueTime or now() <= a.endTime and a.startTime and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and b.userId != " + userId;
		String zwCondition = " and a.userId =" + userId;
		List<PluginActVo> pluginActList = pluginActDao.getPluginActes(condition, zwCondition, count);
		// 没有活动图标处理，取插件图片做为活动图标
		if (CollectionUtils.isNotEmpty(pluginActList)) {
			for (PluginActVo pluginActVo : pluginActList) {
				if (StringUtils.isNullBlank(pluginActVo.getIcon())) {
					pluginActVo.setIcon(pluginActVo.getPluginIcon());
				}
			}
		}
		return pluginActList;
	}

	/**
	 * 获取活动个数
	 * 
	 * @param userId
	 *            用户id条件
	 * @throws Exception
	 */
	public int getPluginActeCount(String userId) throws Exception {
		String condition = " and a.userId = " + userId;
		return pluginActDao.getPluginActeCount(condition);
	}

	/**
	 * 获取正在进行的活动
	 * 
	 * @param condition
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<PluginActVo> findIngPluginActes(Integer pluginId) throws Exception {
		List<PluginActVo> pluginActList = pluginActDao.findIngPluginActes(pluginId);
		// 没有活动图标处理，取插件图片做为活动图标
		if (CollectionUtils.isNotEmpty(pluginActList)) {
			for (PluginActVo pluginActVo : pluginActList) {
				if (StringUtils.isNullBlank(pluginActVo.getIcon())) {
					pluginActVo.setIcon(pluginActVo.getPluginIcon());
				}
			}
		}
		return pluginActList;
	}

	/**
	 * 获取活动信息（推荐类）
	 * 
	 * @param condition
	 *            用户推荐，插件对应推荐
	 * @throws Exception
	 */
	public List<PluginActCenterVo> findActVos(String condition) throws Exception {
		List<PluginActCenterVo> pluginActList = pluginActDao.findActVos(condition);
		for (PluginActCenterVo pvo : pluginActList) {
			// 设置头像
			if (pvo.getHeadimgUrl() != null) {
				if (pvo.getHeadimgUrl().startsWith("group")) {
					pvo.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") + pvo.getHeadimgUrl());
				}
			}
		}
		return pluginActList;
	}

	/**
	 * 活动中心获取该用户未公开的活动
	 * 
	 * @param userId
	 * @return List<PluginActVo>
	 */
	public List<PluginActVo> findNotOpenActes(int userId) throws Exception {
		String condition = " and now() <= b.overdueTime and  now() <= a.endTime and now() >= a.startTime and a.actOpen = '1' and b.userId =" + userId;
		List<PluginActVo> noOpenPluginActVos = pluginActDao.findNotOpenActes(condition);
		return noOpenPluginActVos;
	}

	/**
	 * 设置活动公开状态
	 * 
	 * @return
	 */
	public void editActToOpen(String actOpenFlag, String countOpenFlag, String ActIds) throws Exception {
		pluginActDao.editActToOpen(actOpenFlag, countOpenFlag, ActIds);
	}

	/**
	 * 添加活动
	 * 
	 * @param pluginAct
	 */
	public void addPluginAct(PluginAct pluginAct) {
		pluginActDao.add(pluginAct);
	}

	@Override
	public int upOutAct(PluginAct pluginAct) {
		return pluginActDao.upOutAct(pluginAct);
	}

	@Override
	public PluginAct getById(Integer id) throws Exception {
		return pluginActDao.getPluginActById(id);
	}

	@Override
	public int upByStr(Integer id, String upstr) {
		return pluginActDao.upByStr(id, upstr);
	}

	/******************************* 代理商版本 *************************************/
	@Override
	public List<MyActVO> findMyActList(Integer userId, Integer agentId, String condition) throws Exception {
		return pluginActDao.findMyActList(userId, agentId, condition);
	}

	@Override
	public Integer getMyActCount(Integer userId, Integer agentId, String condition) throws Exception {
		return pluginActDao.getMyActCount(userId, agentId, condition);
	}

	@Override
	public Pagination<MyActVO> findMyActList4Page(Integer userId, Integer agentId, int currentPage, String condition) throws Exception {
		// 条件
		String orderCon = " ORDER BY a.createTime DESC ";
		// 获取用户插件总数
		int totalCount = pluginActDao.getMyActCount(userId, agentId, condition);
		// 计算开始行
		int startRow = (currentPage - 1) * Globals.PC_MY_ACTLIST_NUM;
		if (startRow < 0)
			startRow = 0;
		String afcondition = condition + orderCon + " LIMIT " + startRow + ", " + Globals.PC_MY_ACTLIST_NUM;
		List<MyActVO> myActList = pluginActDao.findMyActList(userId, agentId, afcondition);
		// 判断剩余有效期
		Date dt = new Date();
		for (MyActVO act : myActList) {
			if (dt.after(act.getOverdueTime())) { // 设置模板状态为已过期
				act.setPluginState("已过期");
			} else {
				act.setPluginState(" ");
			}
			if(act.getStartTime() != null && act.getEndTime() != null){
				if(dt.before(act.getStartTime()) && dt.before(act.getEndTime())){//设置活动状态为未开始
					act.setActState("未开始");
				}else if(dt.after(act.getStartTime()) && dt.before(act.getEndTime())){//进行中
					act.setActState("进行中");
				}else if(dt.after(act.getEndTime()) && dt.after(act.getStartTime())){//已结束
					act.setActState("已结束");
				}else{
					act.setActState(" ");
				}
			}

		}
		return new Pagination<MyActVO>(totalCount, Globals.PC_MY_ACTLIST_NUM, currentPage, myActList);
	}
}
