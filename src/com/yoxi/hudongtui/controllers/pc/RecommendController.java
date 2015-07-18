package com.yoxi.hudongtui.controllers.pc;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.content.AgentContSwitch;
import com.yoxi.hudongtui.service.content.IActClassicService;
import com.yoxi.hudongtui.service.content.IActRecService;
import com.yoxi.hudongtui.service.content.IAgentContSwitchService;
import com.yoxi.hudongtui.service.content.IChannelService;
import com.yoxi.hudongtui.service.content.IPluginPrevService;
import com.yoxi.hudongtui.service.content.IPluginRecService;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.content.ActClassicVO;
import com.yoxi.hudongtui.vo.content.ActRecVO;
import com.yoxi.hudongtui.vo.content.PluginPrevVO;
import com.yoxi.hudongtui.vo.content.PluginRecVO;
import com.yoxi.hudongtui.vo.user.ChannelVO;

/**
 * 
 * 推荐相关
 * 
 * @author wql
 * 
 * @Date 2015年3月20日
 * 
 */
@GlobalRequired
public class RecommendController {

	@Autowired
	private IActRecService actRecService;
	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IActClassicService actClassicService;
	@Autowired
	private IPluginRecService pluginRecService;
	@Autowired
	private IPluginPrevService pluginPrevService;
	@Autowired
	private IChannelService channelService;

	@Autowired
	private IAgentContSwitchService agentContSwitchService;

	/**
	 * 活动推荐
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("getActRecList")
	public String getActRecList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		try {
			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			List<ActRecVO> actList = null;
			if (agentInfoVO != null) {
				String condition = " AND NOW() <= overdueTime AND b.actOpen = '0' AND b.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " ORDER BY b.browseNum DESC ";
				actList = actRecService.findActList(agentInfoVO.getId(), condition, 0, Globals.PC_INDEX_ACTREC_NUM);
			}
			inv.getRequest().setAttribute("actList", actList);
			return "recommend/act";
		} catch (Exception e) {
			e.printStackTrace();
			return "tipsmall";
		}
	}

	/**
	 * 经典案例推荐
	 * 
	 * @param inv
	 * @param start
	 * @param count
	 * @return
	 */
	@Get("getClassicRecList")
	public String getCaseRecList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		// 代理商信息
		AgentInfoVO getAgentInfo = SessionUtil.getAgentInfo(inv.getRequest());
		AgentContSwitch agentContSwitch = agentContSwitchService.getById(getAgentInfo.getId());
		if (agentContSwitch != null) {
			String caseConType = agentContSwitch.getCaseConType();
			if (caseConType.equals("0")) {
				try {
					AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());

					List<ActClassicVO> actList = null;
					if (agentInfoVO != null) {
						Integer agentId = agentInfoVO.getId();
						if (count == 0) {
							count = Globals.PC_INDEX_CLASSIC_NUM;
						}
						actList = actClassicService.findRecList(agentId, "", start, count);

					}
					inv.getRequest().setAttribute("actList", actList);
					return "recommend/actclassic";
					// return "@json:" + JsonUtils.toJson(actList);
				} catch (Exception e) {
					e.printStackTrace();
					return "tipsmall";
				}
			} else {
				try {
					AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());

					List<ActClassicVO> actList = null;
					if (agentInfoVO != null) {
						Integer agentId = agentInfoVO.getId();
						if (count == 0) {
							count = Globals.PC_INDEX_CLASSIC_NUM;
						}
						String zncondition = " where f.agentId = " + agentId + " ";
						actList = actClassicService.findAgentRecList(agentId, zncondition, start, count);

					}
					inv.getRequest().setAttribute("actList", actList);
					return "recommend/actclassic";
					// return "@json:" + JsonUtils.toJson(actList);
				} catch (Exception e) {
					e.printStackTrace();
					return "tipsmall";
				}

			}
		} else {
			try {
				AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());

				List<ActClassicVO> actList = null;
				if (agentInfoVO != null) {
					Integer agentId = agentInfoVO.getId();
					if (count == 0) {
						count = Globals.PC_INDEX_CLASSIC_NUM;
					}
					actList = actClassicService.findRecList(agentId, "", start, count);

				}
				inv.getRequest().setAttribute("actList", actList);
				return "recommend/actclassic";
				// return "@json:" + JsonUtils.toJson(actList);
			} catch (Exception e) {
				e.printStackTrace();
				return "tipsmall";
			}
		}

	}

	/**
	 * 模板推荐
	 * 
	 * @param inv
	 * @param start
	 * @param count
	 * @return
	 */
	@Get("getPluginRecList")
	public String getPluginRecList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		try {
			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			List<PluginRecVO> pluginList = null;
			if (agentInfoVO != null) {
				Integer agentId = agentInfoVO.getId();
				if (count == 0) {
					count = Globals.PC_INDEX_PLUGINREC_NUM;
				}
				String condition = " AND a.agentId = " + agentId + " ORDER BY b.publishTime DESC ";
				pluginList = pluginRecService.findPluginList(agentId, condition, start, count);

			}
			inv.getRequest().setAttribute("pluginList", pluginList);
			return "recommend/pluginrec";
			// return "@json:" + JsonUtils.toJson(actList);
		} catch (Exception e) {
			e.printStackTrace();
			return "tipsmall";
		}
	}

	/**
	 * 模板预告
	 * 
	 * @param inv
	 * @param start
	 * @param count
	 * @return
	 */
	@Get("getPluginPreRecList")
	public String getPluginPreRecList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		try {
			if (count == 0) {
				count = Globals.PC_INDEX_PLUGINPRV_NUM;
			}
			List<PluginPrevVO> pluginPrevList = pluginPrevService.findPluginPrevList(start, count);
			inv.getRequest().setAttribute("pluginPrevList", pluginPrevList);
			return "recommend/pluginprev";
			// return "@json:" + JsonUtils.toJson(actList);
		} catch (Exception e) {
			e.printStackTrace();
			return "tipsmall";
		}
	}

	/**
	 * 渠道推荐
	 * 
	 * @param inv
	 * @param start
	 * @param count
	 * @return
	 */
	@Get("getChannelRecList")
	public String getChannelRecList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());

		AgentContSwitch agentContSwitch = agentContSwitchService.getById(ageInfo.getId());

		if (agentContSwitch != null) {
			String channelConType = agentContSwitch.getChannelConType();
			if (channelConType.equals("0")) {
				try {
					AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
					List<ChannelVO> channelVOList = null;
					if (agentInfoVO != null) {
						Integer agentId = agentInfoVO.getId();
						if (count == 0) {
							count = Globals.PC_INDEX_CHANNELREC_NUM4;
						}
						String zncondition = "  a.auditState='1' order by a.createTime DESC,a.seq ASC ";
						// String zncondition =
						// " where a.auditState=1 and a.agentId=" +
						// String.valueOf(agentId) +
						// " order by a.createTime DESC ";
						channelVOList = channelService.findChannelRecList(zncondition, agentId, start, count);
					}
					inv.getRequest().setAttribute("channelVOList", channelVOList);
					return "recommend/channel";
				} catch (Exception e) {
					e.printStackTrace();
					return "tipsmall";
				}
			} else {
				try {
					AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
					List<ChannelVO> channelVOList = null;
					if (agentInfoVO != null) {
						Integer agentId = agentInfoVO.getId();
						if (count == 0) {
							count = Globals.PC_INDEX_CHANNELREC_NUM4;
						}
						String zncondition = " c.hideState='0' AND a.auditState='1' AND c.agentId = " + agentId + " order by a.createTime DESC ";
						// String zncondition =
						// " where a.auditState=1 and a.agentId=" +
						// String.valueOf(agentId) +
						// " order by a.createTime DESC ";
						channelVOList = channelService.findAgentChannelRecList(zncondition, agentId, start, count);
					}
					inv.getRequest().setAttribute("channelVOList", channelVOList);
					return "recommend/channel";
				} catch (Exception e) {
					e.printStackTrace();
					return "tipsmall";
				}
			}
		} else {
			try {
				AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
				List<ChannelVO> channelVOList = null;
				if (agentInfoVO != null) {
					Integer agentId = agentInfoVO.getId();
					if (count == 0) {
						count = Globals.PC_INDEX_CHANNELREC_NUM4;
					}
					String zncondition = "  a.auditState='1' order by a.createTime DESC ";
					// String zncondition =
					// " where a.auditState=1 and a.agentId=" +
					// String.valueOf(agentId) +
					// " order by a.createTime DESC ";
					channelVOList = channelService.findChannelRecList(zncondition, agentId, start, count);
				}
				inv.getRequest().setAttribute("channelVOList", channelVOList);
				return "recommend/channel";
			} catch (Exception e) {
				e.printStackTrace();
				return "tipsmall";
			}
		}
	}
}
