package com.yoxi.hudongtui.controllers.pc;

import java.net.URLEncoder;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.content.IChannelService;
import com.yoxi.hudongtui.service.plugin.IGiftService;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.FastDFSUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.user.ChannelVO;
import com.yoxi.hudongtui.vo.user.UserVO;

/**
 * 
 * 用户相关
 * 
 * @author wql 2014-11-24
 * 
 */
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IGiftService giftService;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IChannelService channelService;

	private Log log = LogFactory.getLog(getClass());

	/**
	 * 跳转到我的主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@LoginRequired
	@Get("mypage")
	public String toMyPage(Invocation inv) throws Exception {
		// 用户信息
		User user = SessionUtil.getUser(inv.getRequest());
		// 代理商信息
		AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());

		if (user != null) {
			String userId = String.valueOf(user.getUserId());
			String role = user.getRole();
			// boolean isExist = roleVos.contains("高级代理商");
			if (role.equals("1")) {
				// String userId = "1000000012";
				// 1.获取我的活动
				List<PluginActVo> myPluginActVos = pluginActService.getPluginActes(userId, Globals.PAGE_SHOW_NUMBER);
				inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
				// 2.获取其他商家活动
				// List<PluginActVo> otherPluginActVos =
				// pluginActService.getOtherUserPluginActes(userId, 4);
				String condition = "and now() <= b.overdueTime  ";
				condition += " and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.userId !=" + userId + " and a.agentId =" + agentInfoVO.getId() + " ORDER BY a.browseNum DESC LIMIT 0,4";
				List<PluginActCenterVo> otherPluginActVos = pluginActService.findActVos(condition);
				inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);
				// 3.名下渠道

				int start = 0;
				int count = Globals.PAGE_SHOW_NUMBER;
				List<ChannelVO> channelVOList = channelService.findMyChannelRecList(Integer.parseInt(userId), start, count);
				inv.getRequest().setAttribute("channelVOList", channelVOList);

				// 获取渠道总数
				int myChannelCount = channelService.getChannelCount(userId);
				inv.getRequest().setAttribute("myChannelCount", myChannelCount);
				// 5.获取活动总数
				int myPluginActCount = pluginActService.getPluginActeCount(userId);
				inv.getRequest().setAttribute("myPluginActCount", myPluginActCount);

				// 7.生成个人主页二维码
				String qrcodeUrl = URLEncoder.encode(WebApplicationUtils.getBasePath() + "/pc/user/qcotherpage/" + user.getUserId(), "UTF-8");
				inv.addModel("qrcodeUrl", qrcodeUrl);
				return "mypage";
			} else {
				// 1.获取我开发的插件
				List<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugins(userId, Globals.PAGE_SHOW_NUMBER);
				inv.getRequest().setAttribute("devPluginVos", devPluginVos);
				String condition = " and a.userId = " + userId;
				List<PluginDetailVO> _devPluginVos = pluginService.findPluginesByCondition(condition);
				inv.getRequest().setAttribute("devCountNum", _devPluginVos.size());
				// 2.获取其他开发者
				List<UserVO> otherDevUsers = userService.findOtherDevUsers(userId, 4);
				inv.getRequest().setAttribute("otherDevUsers", otherDevUsers);

				return "devUserPage";
			}
		} else {
			return "error";
		}

	}

	/**
	 * 跳转到主页的活动
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@LoginRequired
	@Get("pageActivity")
	public String topageActivity(Invocation inv, @Param("loginId") Integer loginId) throws Exception {
		if (SessionUtil.getUser(inv.getRequest()) != null && SessionUtil.getUser(inv.getRequest()).getUserId() == loginId) {// 如果访问者是自己并且登录，跳到自己人个主页
			// 用户信息
			User user = SessionUtil.getUser(inv.getRequest());
			String userId = String.valueOf(user.getUserId());
			inv.getRequest().setAttribute("userId", userId);
			// List<RoleVO> roleVos = userService.findUserRole(userId);
			String role = user.getRole();
			// boolean isExist = roleVos.contains("高级代理商");
			if (role.equals("1")) {
				// String userId = "1000000012";
				// 获取我的活动
				List<PluginActVo> myPluginActVos = pluginActService.getPluginActes(userId, Globals.PAGE_SHOW_NUMBER);
				inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
				// 获取活动总数
				int myPluginActCount = pluginActService.getPluginActeCount(userId);
				inv.getRequest().setAttribute("myPluginActCount", myPluginActCount);
				return "busmainpage/activity";
			} else {
				// String userId = "1000000012";
				// 获取开发者的模版列表信息
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<PluginDetailVO> myPluginDetails = pluginService.finDevUserPlugins(userId, count);
				inv.getRequest().setAttribute("myPluginDetails", myPluginDetails);
				// 获取开发者的模版总数
				int myPluginCount = pluginService.getPluginCount(userId);
				inv.getRequest().setAttribute("myPluginCount", myPluginCount);
				return "devmainpage/myDevelope";
			}

		} else {
			// 用户信息
			String userId = String.valueOf(loginId);
			User loginUser = userService.findByUserId(loginId);
			inv.getRequest().setAttribute("loginUser", loginUser);
			inv.getRequest().setAttribute("userId", userId);
			String role = loginUser.getRole();
			// boolean isExist = roleVos.contains("高级代理商");
			if (role.equals("1")) {
				// 获取他人的活动
				List<PluginActVo> myPluginActVos = pluginActService.getPluginActes(userId, Globals.PAGE_SHOW_NUMBER);
				inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
				// 获取活动总数
				int myPluginActCount = pluginActService.getPluginActeCount(userId);
				inv.getRequest().setAttribute("myPluginActCount", myPluginActCount);
				return "otherbusmainpage/activity";
			} else {
				// String userId = "1000000012";
				// 获取开发者的模版列表信息
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<PluginDetailVO> myPluginDetails = pluginService.finDevUserPlugins(userId, count);
				inv.getRequest().setAttribute("myPluginDetails", myPluginDetails);
				// 获取开发者的模版总数
				int myPluginCount = pluginService.getPluginCount(userId);
				inv.getRequest().setAttribute("myPluginCount", myPluginCount);
				return "otherdevmainpage/heDevelope";
			}

		}
	}

	/**
	 * 跳转到主页的渠道
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@LoginRequired
	@Get("pageChannel")
	public String topageChannel(Invocation inv, @Param("loginId") Integer loginId) throws Exception {
		if (SessionUtil.getUser(inv.getRequest()) != null && SessionUtil.getUser(inv.getRequest()).getUserId() == loginId) {// 如果访问者是自己并且登录，跳到自己人个主页
			// 用户信息
			User user = SessionUtil.getUser(inv.getRequest());
			String userId = String.valueOf(user.getUserId());
			inv.getRequest().setAttribute("userId", userId);
			// List<RoleVO> roleVos = userService.findUserRole(userId);
			String role = user.getRole();
			// boolean isExist = roleVos.contains("高级代理商");
			if (role.equals("1")) {
				// 名下渠道
				int start = 0;
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<ChannelVO> channelVOList = channelService.findMyChannelRecList(Integer.parseInt(userId), start, count);
				inv.getRequest().setAttribute("channelVOList", channelVOList);

				// 获取渠道总数
				int myChannelCount = channelService.getChannelCount(userId);
				inv.getRequest().setAttribute("myChannelCount", myChannelCount);

				return "busmainpage/channel";
			} else {
				// 其他开发者
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<UserVO> userList = userService.findOtherDevUsers(userId, count);
				inv.getRequest().setAttribute("userList", userList);
				return "devmainpage/otherDeveloper";
			}

		} else {
			// 用户信息
			String userId = String.valueOf(loginId);
			User loginUser = userService.findByUserId(loginId);
			inv.getRequest().setAttribute("loginUser", loginUser);
			inv.getRequest().setAttribute("userId", userId);
			String role = loginUser.getRole();
			// boolean isExist = roleVos.contains("高级代理商");
			if (role.equals("1")) {
				// 名下渠道
				int start = 0;
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<ChannelVO> channelVOList = channelService.findMyChannelRecList(Integer.parseInt(userId), start, count);
				inv.getRequest().setAttribute("channelVOList", channelVOList);

				// 获取渠道总数
				int myChannelCount = channelService.getChannelCount(userId);
				inv.getRequest().setAttribute("myChannelCount", myChannelCount);
				return "otherbusmainpage/channel";
			} else {
				// 其他开发者
				int count = Globals.PC_INDEX_CHANNELREC_NUM;
				List<UserVO> userList = userService.findOtherDevUsers(userId, count);
				inv.getRequest().setAttribute("userList", userList);
				return "otherdevmainpage/otherDeveloper";
			}

		}

	}

	/**
	 * 跳转到主页的其他商家活动
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@LoginRequired
	@Get("pageOtherActivity")
	public String topageOtherActivity(Invocation inv, @Param("loginId") Integer loginId) throws Exception {
		if (SessionUtil.getUser(inv.getRequest()) != null && SessionUtil.getUser(inv.getRequest()).getUserId() == loginId) {// 如果访问者是自己并且登录，跳到自己人个主页
			// 用户信息
			User user = SessionUtil.getUser(inv.getRequest());
			String userId = String.valueOf(user.getUserId());
			inv.getRequest().setAttribute("userId", userId);
			// String userId = "1000000012";
			// 获取其他商家活动
			// List<PluginActVo> otherPluginActVos =
			// pluginActService.getOtherUserPluginActes(userId, 4);
			String condition = "and now() <= b.overdueTime and now() <= a.endTime and now() >= a.startTime ";
			condition += " and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.userId !=" + userId + " ORDER BY a.browseNum DESC LIMIT 0,4";
			List<PluginActCenterVo> otherPluginActVos = pluginActService.findActVos(condition);
			inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);

			return "busmainpage/otherBusiness";
		} else {
			// 用户信息
			String userId = String.valueOf(loginId);
			User loginUser = userService.findByUserId(loginId);
			inv.getRequest().setAttribute("loginUser", loginUser);
			inv.getRequest().setAttribute("userId", userId);
			// 获取其他商家活动
			// List<PluginActVo> otherPluginActVos =
			// pluginActService.getOtherUserPluginActes(userId, 4);
			String condition = "and now() <= b.overdueTime " + "and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.userId !=" + userId + " ORDER BY a.browseNum DESC LIMIT 0,4";
			List<PluginActCenterVo> otherPluginActVos = pluginActService.findActVos(condition);
			inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);

			return "otherbusmainpage/otherBusiness";
		}

	}

	/**
	 * 跳转到他人主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@Get("otherUserPage/{loginId}")
	public String getOtherUserPage(Invocation inv, @Param("loginId") Integer loginId) throws Exception {

		if (SessionUtil.getUser(inv.getRequest()) != null && SessionUtil.getUser(inv.getRequest()).getUserId() == loginId) {// 如果访问者是自己并且登录，跳到自己人个主页
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/user/mypage");
		} else {
			// 用户信息
			String userId = String.valueOf(loginId);
			User loginUser = userService.findByUserId(loginId);
			inv.getRequest().setAttribute("loginUser", loginUser);
			// 代理商信息
			AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
			// 1.获取我的活动
			List<PluginActVo> myPluginActVos = pluginActService.getPluginActes(userId, Globals.PAGE_SHOW_NUMBER);
			inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
			// 2.获取其他商家活动
			String condition = " and now() <= b.overdueTime " + "and a.browseNum >= " + Globals.ACT_BROWSERNUM_LIMIT + " and a.userId !=" + userId + " and a.agentId =" + agentInfoVO.getId()
					+ " ORDER BY a.browseNum DESC LIMIT 0,4";
			List<PluginActCenterVo> otherPluginActVos = pluginActService.findActVos(condition);
			inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);
			// 3.名下渠道

			int start = 0;
			int count = Globals.PAGE_SHOW_NUMBER;
			List<ChannelVO> channelVOList = channelService.findMyChannelRecList(Integer.parseInt(userId), start, count);
			inv.getRequest().setAttribute("channelVOList", channelVOList);

			// 获取渠道总数
			int myChannelCount = channelService.getChannelCount(userId);
			inv.getRequest().setAttribute("myChannelCount", myChannelCount);
			// 5.获取活动总数
			int myPluginActCount = pluginActService.getPluginActeCount(userId);
			inv.getRequest().setAttribute("myPluginActCount", myPluginActCount);

			// 7.生成个人主页二维码
			String qrcodeUrl = URLEncoder.encode(WebApplicationUtils.getBasePath() + "/pc/user/qcotherpage/" + loginUser.getUserId(), "UTF-8");
			inv.addModel("qrcodeUrl", qrcodeUrl);
			return "otherUserPage";
		}

		return "error";
	}

	/**
	 * 跳转到开发者主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@LoginRequired
	@Get("devUserPage/{loginId}")
	public String getDevUserPage(Invocation inv, @Param("loginId") String loginId) throws Exception {

		// 用户信息
		String userId = String.valueOf(loginId);
		User loginUser = userService.findByUserId(Integer.valueOf(userId));
		inv.getRequest().setAttribute("loginUser", loginUser);
		// 1.获取我开发的插件
		List<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugins(userId, Globals.PAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("devPluginVos", devPluginVos);
		String condition = " and a.userId = " + userId;
		List<PluginDetailVO> _devPluginVos = pluginService.findPluginesByCondition(condition);
		inv.getRequest().setAttribute("devCountNum", _devPluginVos.size());
		// 2.获取其他开发者
		List<UserVO> otherDevUsers = userService.findOtherDevUsers(userId, 4);
		inv.getRequest().setAttribute("otherDevUsers", otherDevUsers);

		return "devUserPage";
	}

	/**
	 * 跳转到其它开发者主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@Get("devOtherPage/{loginId}")
	public String getDevOtherPage(Invocation inv, @Param("loginId") Integer loginId) throws Exception {

		if (SessionUtil.getUser(inv.getRequest()) != null && SessionUtil.getUser(inv.getRequest()).getUserId() == loginId) {// 如果访问者是自己并且登录，跳到自己人个主页
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/user/devUserPage/" + String.valueOf(loginId));
		} else {
			// 用户信息
			String userId = String.valueOf(loginId);
			User loginUser = userService.findByUserId(loginId);
			inv.getRequest().setAttribute("loginUser", loginUser);
			// 1.获取我开发的插件
			List<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugins(userId, Globals.PAGE_SHOW_NUMBER);
			inv.getRequest().setAttribute("devPluginVos", devPluginVos);
			String condition = " and a.userId = " + userId;
			List<PluginDetailVO> _devPluginVos = pluginService.findPluginesByCondition(condition);
			inv.getRequest().setAttribute("devCountNum", _devPluginVos.size());
			// 2.获取其他开发者
			List<UserVO> otherDevUsers = userService.findOtherDevUsers(userId, 4);
			inv.getRequest().setAttribute("otherDevUsers", otherDevUsers);
			return "devOtherPage";
		}
		return "error";
	}

	/**
	 * 获得更多插件（開發者頁面）
	 * 
	 * @param inv
	 * @param loginId
	 * @param frendCount
	 * @return
	 * @throws Exception
	 */
	@Get("getMorePlugins")
	public String getMorePlugins(Invocation inv, @Param("loginId") String loginId, @Param("pluginCount") int pluginCount) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		List<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugins(loginId, pluginCount);
		String devPluginVoJson = JsonUtils.toJson(devPluginVos);
		return "@json:" + devPluginVoJson;
	}

	/**
	 * 获得更多活动
	 * 
	 * @param inv
	 * @param loginId
	 * @param frendCount
	 * @return
	 * @throws Exception
	 */
	@Get("getMoreActes")
	public String getMoreActes(Invocation inv, @Param("loginId") String loginId, @Param("actCount") int actCount) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		List<PluginActVo> myPluginActVos = pluginActService.getPluginActes(loginId, actCount);
		String pluginActVoJson = JsonUtils.toJson(myPluginActVos);
		return "@json:" + pluginActVoJson;
	}

	/**
	 * 获得渠道信息
	 * 
	 * @param inv
	 * @param loginId
	 * @param channelCount
	 * @return
	 * @throws Exception
	 */
	@Get("getMoreChannes")
	public String getMoreChannes(Invocation inv, @Param("loginId") String loginId, @Param("channelCount") int channelCount) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		int start = 0;
		List<ChannelVO> channelVOs = channelService.findMyChannelRecList(Integer.parseInt(loginId), start, channelCount);
		String channelVOsJson = JsonUtils.toJson(channelVOs);
		return "@json:" + channelVOsJson;
	}

	/**
	 * 处理二维码扫描访问他人页
	 * 
	 * @param inv
	 * @return
	 */
	@Get("qcOtherPage/{uid}")
	public String qcOtherPage(Invocation inv, @Param("uid") Integer uid) {

		// 请求来自微信处理跳转到微信版本他人页
		if (WebApplicationUtils.isBrowserMicrMes(inv.getRequest())) {
			// TODO
			String otherPage = ReadProperties.getPara("httpPath") + "/";
			String oauthUrl = MpConstants.getOauthAccessBase().replace("REDIRECT_URI", "");
		} else {// 跳到移动版

		}

		return null;
	}

	/**
	 * 账号设置跳转
	 * 
	 * @param inv
	 * @return
	 */
	@LoginRequired
	@Get("userEdit")
	// @Token(needSaveToken = true)
	public String userEdit(Invocation inv) throws Exception {
		return "userEdit";
	}

	/**
	 * 账号设置保存
	 * 
	 * @param inv
	 * @return
	 */
	@LoginRequired
	@Post("userUpdate")
	// @Token(needRemoveToken = true)
	public String userUpdate(Invocation inv, User user, @Param("headimgbase64") String headimgbase64) throws Exception {
		User suser = SessionUtil.getUser(inv.getRequest());
		// 处理上传图片
		if (!StringUtils.isNullBlank(headimgbase64)) {
			String url = FastDFSUtils.getFastDfsPath(headimgbase64, "jpg");
			user.setHeadimgUrl(url);
		}
		user.setUserId(suser.getUserId());

		Integer upFlag = userService.updataUserSet(user);

		if (upFlag == 1) {
			User dbUser = userService.findByUserId(suser.getUserId());
			SessionUtil.setSessionUserAttr(inv.getRequest(), dbUser);
		}
		inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/user/userEdit");
		return null;
	}

}
