package com.yoxi.hudongtui.controllers.pc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.content.AgentContSwitch;
import com.yoxi.hudongtui.model.content.Channel;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.content.IAgentContSwitchService;
import com.yoxi.hudongtui.service.content.IChannelService;
import com.yoxi.hudongtui.service.content.IPluginPrevService;
import com.yoxi.hudongtui.utils.common.FastDFSUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.user.ChannelVO;

/**
 * 
 * 渠道controller
 * 
 * @author gjj
 * 
 * @Date 2015年3月24日
 * 
 */
@GlobalRequired
public class ChannelController {
	@Autowired
	private IPluginPrevService pluginPrevService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IAgentContSwitchService agentContSwitchService;

	/**
	 * 渠道总列表
	 * 
	 * @param inv
	 * @return
	 */
	@Get("index")
	public String index(Invocation inv) throws Exception {
		return "channelIndex";
	}

	/**
	 * 成为渠道页面
	 * 
	 * @param inv
	 * @return
	 */
	@Get("beChannel")
	// @Token(needSaveToken = true)
	public String beChannel(Invocation inv, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(Globals.SESSION_USER);
		if (user == null) {
			// WebApplicationUtils.channelMsg("登录之后才能成为渠道！请先登录！", "",
			// WebApplicationUtils.getBasePath() + "/login/", true);
			return "login";
		} else {
			return "beChannel";
		}
	}

	/**
	 * 提交渠道
	 * 
	 * @param channelVO
	 * @return
	 * @throws Exception
	 */
	@Post("doBeChannel")
	// @Token(needRemoveToken = true)
	public String doBeChannel(Invocation inv, HttpServletRequest request, HttpSession session, ChannelVO channelVO) throws Exception {
		User user = (User) session.getAttribute(Globals.SESSION_USER);

		if (channelVO != null && user != null) {
			Channel channel = new Channel();
			if (channelVO.getType().equals("1") || channelVO.getType().equals("2") || channelVO.getType().equals("3")) {
				// 二维码上传图片处理
				if (!StringUtils.isNullBlank(channelVO.getQrcode())) {
					String qrcodeUrl = FastDFSUtils.getFastDfsPath(channelVO.getQrcode(), "jpg");
					channel.setQrcode(qrcodeUrl);
				}
			} else if (channelVO.getType().equals("4") || channelVO.getType().equals("5") || channelVO.getType().equals("6")) {
				channel.setWebsite(channelVO.getWebsite().replaceAll(",", ""));
			} else {

			}
			channel.setCreateTime(new Date());
			channel.setAuditState("0");
			channel.setType(channelVO.getType());
			channel.setName(channelVO.getName());

			if (!StringUtils.isNullBlank(channelVO.getEmail())) {
				channel.setEmail(channelVO.getEmail());
			}
			if (!StringUtils.isNullBlank(channelVO.getDownloadLink())) {
				channel.setDownloadLink(channelVO.getDownloadLink());
			}
			if (channelVO.getAttentionLink() != null) {
				channel.setAttentionLink(channelVO.getAttentionLink());
			}
			channel.setMobile(channelVO.getMobile());
			// LOGO上传图片处理
			if (!StringUtils.isNullBlank(channelVO.getLogo())) {
				String logoUrl = FastDFSUtils.getFastDfsPath(channelVO.getLogo(), "jpg");
				channel.setLogo(logoUrl);
			}
			if (channelVO.getMicroNum() != null) {
				channel.setMicroNum(channelVO.getMicroNum());
			}

			channel.setIntroduce(channelVO.getIntroduce());
			if (channelVO.getFansNum() != null) {
				channel.setFansNum(channelVO.getFansNum());
			}
			channel.setPrice(channelVO.getPrice());
			channel.setQq(channelVO.getQq());
			if (user != null) {
				channel.setUserId(user.getUserId());
			}

			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			channel.setAgentId(agentInfoVO.getId());
			Integer id = channelService.save(channel);

			WebApplicationUtils.channelMsg("已经提交申请，1个工作日内工作人员会联系你完成审核！", "", WebApplicationUtils.getBasePath() + "/pc/channel/index/", true);
		}

		return null;
	}

	/**
	 * 
	 * @param inv
	 * @param start
	 * @param typeFlag
	 * @param orderFlag
	 * @param publishTime_Flag
	 * @param buyNum_Flag
	 * @param price_Flag
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	@Get("ajaxList")
	public String getChannelAjaxList(Invocation inv, @Param("start") int start, @Param("typeFlag") String typeFlag, @Param("orderFlag") String orderFlag,
			@Param("publishTime_Flag") String publishTime_Flag, @Param("buyNum_Flag") String buyNum_Flag, @Param("price_Flag") String price_Flag, @Param("currentPage") final int currentPage,
			@Param("pageCount") int pageCount) throws Exception {
		// 1.设置参数
		String xFlag = "0";
		// typeFlag 0表示全部，1.表示即买即用
		// orderFlag 1表示发布时间publishTime，2.表示粉丝量fansNum,3.表示价格price
		if ("1".equals(orderFlag)) {
			xFlag = publishTime_Flag;
		} else if ("2".equals(orderFlag)) {
			xFlag = buyNum_Flag;
		} else if ("3".equals(orderFlag)) {
			xFlag = price_Flag;
		}
		// 0表示降序，1表示升序
		if (ValidateUtil.isEmpty(publishTime_Flag) && ValidateUtil.isEmpty(buyNum_Flag) && ValidateUtil.isEmpty(price_Flag)) {
			publishTime_Flag = "0";
			buyNum_Flag = "0";
			price_Flag = "0";
		}
		// 2.查询渠道
		// 第一版本类型为全部
		String typeConStr = channelService.getTypeCon("0");
		String orderStrCon = channelService.getOderFlag(orderFlag, xFlag);
		String orderStrCon2 = channelService.getOderFlag(orderFlag, xFlag);
		AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
		Integer agentId = agentInfoVO.getId();
		if (pageCount == 0) {
			pageCount = Globals.PC_INDEX_CHANNE_NUM;
		}
		if (start == 0) {
			start = 0;
		}
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());

		AgentContSwitch agentContSwitch = agentContSwitchService.getById(ageInfo.getId());

		if (agentContSwitch != null) {
			String channelConType = agentContSwitch.getChannelConType();
			if (channelConType.equals("0")) {
				List<ChannelVO> channelVOList = channelService.findChannelList(xFlag, typeConStr, orderStrCon2, agentId, start, pageCount);
				return "@json:" + JsonUtils.toJson(channelVOList);
			} else {
				List<ChannelVO> channelVOList = channelService.findAgentChannelList(xFlag, typeConStr, orderStrCon, agentId, start, pageCount);
				return "@json:" + JsonUtils.toJson(channelVOList);
			}
		} else {
			List<ChannelVO> channelVOList = channelService.findChannelList(xFlag, typeConStr, orderStrCon2, agentId, start, pageCount);
			return "@json:" + JsonUtils.toJson(channelVOList);
		}

	}

	/**
	 * 
	 * @param inv
	 * @param start
	 * @param typeFlag
	 * @param orderFlag
	 * @param publishTime_Flag
	 * @param buyNum_Flag
	 * @param price_Flag
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	@Get("ajaxListLoad")
	public String getChannelAjaxLoadList(Invocation inv, @Param("start") int start, @Param("typeFlag") String typeFlag, @Param("orderFlag") String orderFlag,
			@Param("publishTime_Flag") String publishTime_Flag, @Param("buyNum_Flag") String buyNum_Flag, @Param("price_Flag") String price_Flag, @Param("currentPage") final int currentPage,
			@Param("pageCount") int pageCount) throws Exception {
		// 1.设置参数
		String xFlag = "0";
		// typeFlag 0表示全部，1.表示即买即用
		// orderFlag 1表示发布时间publishTime，2.表示粉丝量fansNum,3.表示价格price
		if ("1".equals(orderFlag)) {
			xFlag = publishTime_Flag;
		} else if ("2".equals(orderFlag)) {
			xFlag = buyNum_Flag;
		} else if ("3".equals(orderFlag)) {
			xFlag = price_Flag;
		}
		// 0表示降序，1表示升序
		if (ValidateUtil.isEmpty(publishTime_Flag) && ValidateUtil.isEmpty(buyNum_Flag) && ValidateUtil.isEmpty(price_Flag)) {
			publishTime_Flag = "0";
			buyNum_Flag = "0";
			price_Flag = "0";
		}
		// 2.查询渠道
		// 第一版本类型为全部
		String typeConStr = channelService.getTypeCon("0");
		String orderStrCon = channelService.getOderFlag(orderFlag, xFlag);
		AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
		Integer agentId = agentInfoVO.getId();
		if (pageCount == 0) {
			pageCount = Globals.PC_INDEX_CHANNE_NUM;
		}
		if (start == 0) {
			start = 3;
		}
		AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());

		AgentContSwitch agentContSwitch = agentContSwitchService.getById(ageInfo.getId());

		if (agentContSwitch != null) {
			String channelState = agentContSwitch.getChannelState();
			if (channelState.equals("0")) {
				// List<ChannelVO> channelVOList
				// =channelService.findChannelList(xFlag,typeConStr,orderStrCon,agentId,
				// start, pageCount);
				Pagination<ChannelVO> channelVOList = channelService.findChannelPage(currentPage, xFlag, typeConStr, orderStrCon, agentId, start, pageCount);
				return "@json:" + JsonUtils.toJson(channelVOList);
			} else {
				// List<ChannelVO> channelVOList
				// =channelService.findChannelList(xFlag,typeConStr,orderStrCon,agentId,
				// start, pageCount);
				Pagination<ChannelVO> channelVOList = channelService.findAgentChannelPage(currentPage, xFlag, typeConStr, orderStrCon, agentId, start, pageCount);
				return "@json:" + JsonUtils.toJson(channelVOList);
			}
		} else {
			// List<ChannelVO> channelVOList
			// =channelService.findChannelList(xFlag,typeConStr,orderStrCon,agentId,
			// start, pageCount);
			Pagination<ChannelVO> channelVOList = channelService.findChannelPage(currentPage, xFlag, typeConStr, orderStrCon, agentId, start, pageCount);
			return "@json:" + JsonUtils.toJson(channelVOList);
		}

	}

	/**
	 * 渠道列表
	 * 
	 * @param inv
	 * @param start
	 * @param count
	 * @return
	 */
	@Get("getChannelList")
	public String getChannelList(Invocation inv, @Param("start") int start, @Param("count") int count) {
		try {
			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			Integer agentId = agentInfoVO.getId();
			if (count == 0) {
				count = Globals.PC_INDEX_CHANNE_NUM;
			}

			// List<ChannelVO> channelVOList
			// =channelService.findChannelList(xFlag,agentId, start, count);

			// List<ChannelVO> channelVOList
			// =channelService.findChannelList(agentId, start, count);
			// inv.getRequest().setAttribute("channelVOList", channelVOList);
			return "recommend/channelRecL";
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
	public String getChannelRecList(HttpSession session, Invocation inv, @Param("start") int start, @Param("count") int count) {
		try {
			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			Integer agentId = agentInfoVO.getId();
			if (count == 0) {
				// User user = (User)
				// session.getAttribute(Globals.SESSION_USER);
				// if (user == null) {
				// count = Globals.PC_INDEX_CHANNELREC_NUM4;
				// } else {
				count = Globals.PC_INDEX_CHANNELREC_NUM;
				// }
			}
			String zncondition = " where a.auditState=1 and a.agentId=" + String.valueOf(agentId) + " order by a.createTime DESC ";
			List<ChannelVO> channelVOList = channelService.findChannelRecList(zncondition, agentId, start, count);
			inv.getRequest().setAttribute("channelVOList", channelVOList);
			return "recommend/channelRecL";
			// return "@json:" + JsonUtils.toJson(actList);
		} catch (Exception e) {
			e.printStackTrace();
			return "tipsmall";
		}
	}
}
