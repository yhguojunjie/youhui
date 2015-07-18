package com.yoxi.hudongtui.controllers.pc;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.content.AgentContSwitch;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.content.IActClassicService;
import com.yoxi.hudongtui.service.content.IActRecService;
import com.yoxi.hudongtui.service.content.IAgentContSwitchService;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.content.ActClassicVO;
import com.yoxi.hudongtui.vo.content.ActRecVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;

/**
 * 
 * 活动相关
 * 
 * @author wql
 * 
 * @Date 2015年3月26日
 * 
 */
@GlobalRequired
public class ActController {

	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IActRecService actRecService;
	@Autowired
	private IActClassicService actClassicService;
	@Autowired
	private IAgentContSwitchService agentContSwitchService;

	/**
	 * 进入活动圈首页
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("")
	public String toActZone(Invocation inv, @Param("ordertype") int ordertype) throws Exception {
		// 活动推荐
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());
		/*
		 * List<ActRecVO> actRecList =
		 * actRecService.findActList(ageInfo.getId(), " ",
		 * 0,Globals.PC_ACTZONE_RECNUM);
		 * inv.getRequest().setAttribute("actRecList", actRecList);
		 */
		// 活动列表
		String orderType = "browseNum";
		Integer start = 0;
		if (ordertype == 1) {
			orderType = "createTime";
			start = 0;
		} else {
			orderType = "browseNum";
			start = 0;
		}
		String condition = " AND a.agentId = " + ageInfo.getId() + " AND NOW() <= b.overdueTime AND a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " AND a.actOpen = '0' " + " ORDER BY a."
				+ orderType + " DESC";

		List<ActRecVO> actList = pluginActService.findActList(ageInfo.getId(), condition, start, Globals.PC_ACTZONE_NUM);
		inv.getRequest().setAttribute("actList", actList);
		inv.getRequest().setAttribute("ordertype", ordertype);
		return "actzone";
	}

	/**
	 * 活动列表获取
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("ajaxActList")
	public String ajaxActList(Invocation inv, @Param("ordertype") int ordertype, @Param("start") int start, @Param("count") int count) throws Exception {

		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());
		String orderType = "browseNum";
		if (ordertype == 1) {
			orderType = "createTime";
		} else {
			orderType = "browseNum";
		}
		String condition = " AND a.agentId = " + ageInfo.getId() + " AND NOW() <= b.overdueTime AND a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " AND a.actOpen = '0' " + " ORDER BY a."
				+ orderType + " DESC";

		List<ActRecVO> actList = pluginActService.findActList(ageInfo.getId(), condition, start, Globals.PC_ACTZONE_NUM);
		return "@json:" + JsonUtils.toJson(actList);
	}

	/**
	 * 进入经典案例
	 * 
	 * @return
	 * @throws Exceptoin
	 */
	@Get("classic")
	public String toActClassic(Invocation inv) throws Exception {
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());

		AgentContSwitch agentContSwitch = agentContSwitchService.getById(ageInfo.getId());
		if (agentContSwitch != null) {
			String caseConType = agentContSwitch.getCaseConType();
			Integer agentId = ageInfo.getId();
			if (caseConType.equals("0")) {
				List<ActClassicVO> actList = actClassicService.findList(ageInfo.getId(), "", 0, Globals.PC_ACTCLASSIC_NUM);
				inv.getRequest().setAttribute("actList", actList);
				return "actclassic";
			} else {
				String zncondition = " where f.agentId = " + agentId + " ";
				List<ActClassicVO> actList = actClassicService.findAgentList(ageInfo.getId(), zncondition, 0, Globals.PC_ACTCLASSIC_NUM);
				inv.getRequest().setAttribute("actList", actList);
				return "actclassic";
			}
		} else {
			List<ActClassicVO> actList = actClassicService.findList(ageInfo.getId(), "", 0, Globals.PC_ACTCLASSIC_NUM);
			inv.getRequest().setAttribute("actList", actList);
			return "actclassic";
		}
	}

	/**
	 * 经典案例列表获取
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("ajaxClassicList")
	public String ajaxClassicList(Invocation inv, @Param("start") int start, @Param("count") int count) throws Exception {
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());
		AgentContSwitch agentContSwitch = agentContSwitchService.getById(ageInfo.getId());
		if (agentContSwitch != null) {
			String caseConType = agentContSwitch.getCaseConType();
			Integer agentId = ageInfo.getId();
			if (caseConType.equals("0")) {
				List<ActClassicVO> actList = actClassicService.findList(ageInfo.getId(), "", start, Globals.PC_ACTCLASSIC_NUM);
				return "@json:" + JsonUtils.toJson(actList);
			} else {
				String zncondition = " where f.agentId = " + agentId + " ";
				List<ActClassicVO> actList = actClassicService.findAgentList(ageInfo.getId(), zncondition, start, Globals.PC_ACTCLASSIC_NUM);
				return "@json:" + JsonUtils.toJson(actList);
			}
		} else {
			List<ActClassicVO> actList = actClassicService.findList(ageInfo.getId(), "", start, Globals.PC_ACTCLASSIC_NUM);
			return "@json:" + JsonUtils.toJson(actList);
		}
	}

	/******************************** 淘插件版本 ************************************************/

	/**
	 * 活动中心信息(pc)
	 * 
	 * @param typeFlag
	 *            0表示全部，1.站外
	 * @param orderFlag
	 *            1热度pluginBrowsenum，2.发布pluginPublish
	 * @param currentPage
	 *            当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginActCenterList")
	public String findPluginActCenterVos(Invocation inv, @Param("typeFlag") String typeFlag, @Param("orderFlag") String orderFlag, @Param("currentPage") final int currentPage) throws Exception {
		Pagination<PluginActCenterVo> paginationPluginActCent = pluginActService.findPcPluginActCenterVos(orderFlag, currentPage, 15);
		inv.getRequest().setAttribute("paginationPluginActCent", paginationPluginActCent);
		inv.getRequest().setAttribute("maxpage", paginationPluginActCent.getTotalPage());
		return "pluginActCenter";
	}

	/**
	 * 活动中心信息(ajax请求)
	 * 
	 * @param typeFlag
	 *            0表示全部，1.站外
	 * @param orderFlag
	 *            1热度pluginBrowsenum，2.发布pluginPublish
	 * @param currentPage
	 *            当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginActCenterAjaxList")
	public String findPluginActCenterVosAjax(Invocation inv, @Param("typeFlag") String typeFlag, @Param("orderFlag") String orderFlag, @Param("currentPage") final int currentPage) throws Exception {
		Pagination<PluginActCenterVo> paginationPluginActCent = pluginActService.findPcPluginActCenterVos(orderFlag, currentPage, 15);
		return "@json:" + JsonUtils.toJson(paginationPluginActCent);
	}

	/**
	 * 查找未公开的活动
	 * 
	 * @param inv
	 * @param userId
	 * @return
	 */
	@Get("/findNotOpenActes")
	public String findNotOpenActes(Invocation inv) throws Exception {
		inv.getRequest().getSession().setAttribute(Globals.SESSION_LASTURL, WebApplicationUtils.getBasePath() + "/pc/pluginActCenter/pluginActCenterList");
		User user = SessionUtil.getUser(inv.getRequest());
		if (ValidateUtil.isNull(user)) {
			return "@json:" + "{\"flag\":\"false\"}";
		} else {
			List<PluginActVo> noOpenPluginActVos = pluginActService.findNotOpenActes(user.getUserId());
			return "@json:" + JsonUtils.toJson(noOpenPluginActVos);
		}
	}

	/**
	 * （活动中心）发布活动到活动中心
	 * 
	 * @param inv
	 * @param userId
	 * @return
	 */
	@Post("/editActToOpen")
	public void editActToOpen(Invocation inv, @Param("ActIds") String ActIds) throws Exception {
		ActIds = ActIds.substring(0, ActIds.length() - 1);
		pluginActService.editActToOpen("0", "0", "(" + ActIds + ")");
		inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/pluginActCenter/pluginActCenterList");
	}

}
