package com.yoxi.hudongtui.controllers.wx;

import java.net.URLEncoder;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.plugin.IGiftService;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.service.wx.IWxJsAPIService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.utils.common.FastDFSUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.utils.wx.CommonUtils;
import com.yoxi.hudongtui.vo.plugin.GiftVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.user.UserVO;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

/**
 * 
 * 用户相关
 * 
 * @author wangql 2014-12-14
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
	private IWxUserBusService wxUserBusService;
	@Autowired
	private IWxJsAPIService wxJsAPIService;

	/**
	 * 跳转到微信授权
	 * 
	 * @throws Exception
	 */
	@Get("/redircToEditUrl/{scope}")
	public void redircToEditUrl(Invocation inv, @Param("scope") int scope) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/user/toEdit/" + String.valueOf(scope), "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 跳转到用户资料编辑页
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("/toEdit/{scope}")
	public String toEdit(Invocation inv, @Param("scope") int scope) throws Exception {
		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		Integer userId = thirdVO.getUserId();
		User user = userService.findByUserId(userId);
		inv.getRequest().setAttribute("user", user);
		return "userEdit";
	}

	/**
	 * 编辑用户资料
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Post("/edit")
	public String edit(Invocation inv, User user, @Param("headimgbase64") String headimgbase64) throws Exception {
		// 处理上传图片
		if (!StringUtils.isNullBlank(headimgbase64)) {
			String url = FastDFSUtils.getFastDfsPath(headimgbase64, "jpg");
			user.setHeadimgUrl(url);
		}
		// 处理手机公开状态 前端 input check
		if (!StringUtils.isNullBlank(user.getMobileOpen())) {
			if ("on".equals(user.getMobileOpen())) {
				user.setMobileOpen("0");// 不公开
			}
		} else {
			user.setMobileOpen("1");// 公开
		}

		userService.updataUserSet(user);

		inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircToEditUrl/1");

		return null;

	}

	/**
	 * 微信授权-我的主页
	 * 
	 * @throws Exception
	 */
	@Get("/redircMypageUrl/{scope}")
	public void redircWxUrl(Invocation inv, @Param("scope") String scope) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		if (scope != null) {
			if ("2".equals(scope)) {
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/user/mypage/" + String.valueOf(scope), "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 跳转到我的主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@Get("/mypage/{scope}")
	public String toMyPage(Invocation inv, @Param("scope") final int scope) throws Exception {
		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		if (StringUtils.isNullBlank(thirdVO.getNickName()) && StringUtils.isNullBlank(thirdVO.getHeadimgurl())) {
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircMypageUrl/2");
			return null;
		}

		String userId = String.valueOf(thirdVO.getUserId());
		User loginUser = userService.findByUserId(Integer.valueOf(userId));

		if (loginUser != null) {
			if (StringUtils.isNullBlank(loginUser.getNickName()) && !StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getHeadimgUrl()) && !StringUtils.isNullBlank(loginUser.getNickName())) {
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getNickName()) && StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
		}

		inv.getRequest().setAttribute("loginUser", loginUser);
		inv.getRequest().setAttribute("WXPAGE_SHOW_NUMBER", Globals.WXPAGE_SHOW_NUMBER);
		// 1.获取我的活动
		Pagination<PluginActVo> myPluginActVos = pluginActService.findPluginActes4Page(userId, "4", 1, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
		inv.getRequest().setAttribute("myPluginActCount", myPluginActVos.getTotalCount());
		inv.getRequest().setAttribute("maxpage", myPluginActVos.getTotalPage());
		// 2.获取其他商家活动
		List<PluginActVo> otherPluginActVos = pluginActService.getOtherUserPluginActes(userId, 3);
		inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);
		// 3.获取插友
		// Pagination<User> userFriends =
		// userService.findUserFriendes4Page(userId, 1,
		// Globals.WXPAGE_SHOW_NUMBER);
		// inv.getRequest().setAttribute("userFriends", userFriends);
		// inv.getRequest().setAttribute("frendes",
		// userFriends.getTotalCount());
		// inv.getRequest().setAttribute("maxFrPage",
		// userFriends.getTotalPage());
		// 4.获取礼品及礼品领用情况
		List<GiftVo> giftVos = giftService.findGifteInfo(userId, "");
		inv.getRequest().setAttribute("giftVos", giftVos);
		inv.getRequest().setAttribute("shareArg",
				wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()), CommonUtils.randomUrl() + "/wx/user/redircOtherPage/1/" + userId));
		return "mypage";
	}

	/**
	 * 微信授权-他人页
	 * 
	 * @throws Exception
	 */
	@Get("/redircOtherPage/{scope}/{visitoredId}")
	public void redirOtherPage(Invocation inv, @Param("scope") String scope, @Param("visitoredId") String visitoredId) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		if (scope != null) {
			if ("2".equals(scope)) {
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/user/otherUserPage/" + scope + "/" + visitoredId, "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 他人页
	 * 
	 * @param inv
	 * @return
	 * @throws
	 */
	@Get("/otherUserPage/{scope}/{visitoredId}")
	public String getOtherUserPage(Invocation inv, @Param("visitoredId") Integer visitoredId, @Param("scope") Integer scope) throws Exception {
		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		if (StringUtils.isNullBlank(thirdVO.getNickName()) && StringUtils.isNullBlank(thirdVO.getHeadimgurl())) {
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircOtherPage/2");
			return null;
		}
		Integer userId = thirdVO.getUserId();
		User loginUser = userService.findByUserId(userId);
		if (loginUser != null) {
			if (StringUtils.isNullBlank(loginUser.getNickName()) && !StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getHeadimgUrl()) && !StringUtils.isNullBlank(loginUser.getNickName())) {
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getNickName()) && StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
		}
		if (visitoredId == userId) {// 当访问者用户id与他人页用户id一样，跳到我的主页
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircMypageUrl/1");
		} else {
			User visitoredUser = userService.findByUserId(visitoredId);
			inv.getRequest().setAttribute("loginUser", visitoredUser);
			inv.getRequest().setAttribute("WXPAGE_SHOW_NUMBER", Globals.WXPAGE_SHOW_NUMBER);
			// 1.获取我的活动
			Pagination<PluginActVo> myPluginActVos = pluginActService.findPluginActes4Page(String.valueOf(visitoredId), "4", 1, Globals.WXPAGE_SHOW_NUMBER);
			inv.getRequest().setAttribute("myPluginActVos", myPluginActVos);
			inv.getRequest().setAttribute("myPluginActCount", myPluginActVos.getTotalCount());
			inv.getRequest().setAttribute("maxpage", myPluginActVos.getTotalPage());
			// 2.获取其他商家活动
			List<PluginActVo> otherPluginActVos = pluginActService.getOtherUserPluginActes(String.valueOf(visitoredId), 3);
			inv.getRequest().setAttribute("otherPluginActVos", otherPluginActVos);
			// 3.获取插友
			// Pagination<User> userFriends =
			// userService.findUserFriendes4Page(String.valueOf(visitoredId), 1,
			// Globals.WXPAGE_SHOW_NUMBER);
			// inv.getRequest().setAttribute("userFriends", userFriends);
			// inv.getRequest().setAttribute("frendes",
			// userFriends.getTotalCount());
			// inv.getRequest().setAttribute("maxFrPage",
			// userFriends.getTotalPage());
			// 4.获取礼品及礼品领用情况
			List<GiftVo> giftVos = giftService.findGifteInfo(String.valueOf(visitoredId), "");
			inv.getRequest().setAttribute("giftVos", giftVos);
			inv.getRequest().setAttribute("shareArg",
					wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()), CommonUtils.randomUrl() + "/wx/user/redircOtherPage/1/" + String.valueOf(visitoredId)));
			return "otherUserPage";
		}
		return null;
	}

	/**
	 * 微信授权-开发者主页
	 * 
	 * @throws Exception
	 */
	@Get("/redircDevUserPage/{scope}")
	public void redircDevUserPage(Invocation inv, @Param("scope") String scope) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		if (scope != null) {
			if ("2".equals(scope)) {
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/user/devUserPage/" + String.valueOf(scope), "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 开发者主页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@Get("/devUserPage/{scope}")
	public String getDevUserPage(Invocation inv, @Param("scope") Integer scope) throws Exception {

		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		if (StringUtils.isNullBlank(thirdVO.getNickName()) && StringUtils.isNullBlank(thirdVO.getHeadimgurl())) {
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircDevUserPage/2");
			return null;
		}

		String userId = String.valueOf(thirdVO.getUserId());
		User loginUser = userService.findByUserId(Integer.valueOf(userId));

		if (loginUser != null) {
			if (StringUtils.isNullBlank(loginUser.getNickName()) && !StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getHeadimgUrl()) && !StringUtils.isNullBlank(loginUser.getNickName())) {
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getNickName()) && StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
		}

		// 用户信息
		inv.getRequest().setAttribute("loginUser", loginUser);
		inv.getRequest().setAttribute("WXPAGE_SHOW_NUMBER", Globals.WXPAGE_SHOW_NUMBER);
		// 1.获取我开发的插件
		Pagination<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugin4Page(userId, 1, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("devPluginVos", devPluginVos);
		inv.getRequest().setAttribute("devCountNum", devPluginVos.getTotalCount());
		inv.getRequest().setAttribute("maxpage", devPluginVos.getTotalPage());
		// 2.获取其他开发者
		List<UserVO> otherDevUsers = userService.findOtherDevUsers(userId, 3);
		inv.getRequest().setAttribute("otherDevUsers", otherDevUsers);
		// 3.获取插友
		// Pagination<User> userFriends =
		// userService.findUserFriendes4Page(userId, 1,
		// Globals.WXPAGE_SHOW_NUMBER);
		// inv.getRequest().setAttribute("userFriends", userFriends);
		// inv.getRequest().setAttribute("frendes",
		// userFriends.getTotalCount());
		// inv.getRequest().setAttribute("maxFrPage",
		// userFriends.getTotalPage());
		// 4.获取礼品及礼品领用情况
		List<GiftVo> giftVos = giftService.findGifteInfo(userId, "");
		inv.getRequest().setAttribute("giftVos", giftVos);
		inv.getRequest().setAttribute("shareArg", wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()), CommonUtils.randomUrl() + "/wx/user/redircDevUserPage/1"));
		return "devUserPage";
	}

	/**
	 * 微信授权-开发者他人主页
	 * 
	 * @throws Exception
	 */
	@Get("/redircDevOtherPageUser/{scope}/{visitoredId}")
	public void redircDevOtherPageUser(Invocation inv, @Param("scope") String scope, @Param("visitoredId") String visitoredId) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		if (scope != null) {
			if ("2".equals(scope)) {
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/user/devOtherUserPage/" + scope + "/" + visitoredId, "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 开发者他人页页
	 * 
	 * @param inv
	 * @return
	 * @throws E
	 */
	@Get("/devOtherUserPage/{scope}/{visitoredId}")
	public String devOtherUserPage(Invocation inv, @Param("scope") Integer scope, @Param("visitoredId") Integer visitoredId) throws Exception {

		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		if (StringUtils.isNullBlank(thirdVO.getNickName()) && StringUtils.isNullBlank(thirdVO.getHeadimgurl())) {
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircDevOtherPageUser/2");
			return null;
		}
		Integer userId = thirdVO.getUserId();
		User loginUser = userService.findByUserId(userId);
		if (loginUser != null) {
			if (StringUtils.isNullBlank(loginUser.getNickName()) && !StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getHeadimgUrl()) && !StringUtils.isNullBlank(loginUser.getNickName())) {
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
			if (StringUtils.isNullBlank(loginUser.getNickName()) && StringUtils.isNullBlank(loginUser.getHeadimgUrl())) {
				loginUser.setNickName(thirdVO.getNickName());
				loginUser.setHeadimgUrl(thirdVO.getHeadimgurl());
				userService.updataUserSet(loginUser);
			}
		}

		if (visitoredId == userId) {// 当访问者用户id与他人页用户id一样，跳到开发者主页
			inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/wx/user/redircDevUserPage/1");
		} else {
			// 用户信息
			User visitoredUser = userService.findByUserId(visitoredId);
			inv.getRequest().setAttribute("loginUser", visitoredUser);
			inv.getRequest().setAttribute("WXPAGE_SHOW_NUMBER", Globals.WXPAGE_SHOW_NUMBER);
			// 1.获取我开发的插件
			Pagination<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugin4Page(String.valueOf(visitoredId), 1, Globals.WXPAGE_SHOW_NUMBER);
			inv.getRequest().setAttribute("devPluginVos", devPluginVos);
			inv.getRequest().setAttribute("devCountNum", devPluginVos.getTotalCount());
			inv.getRequest().setAttribute("maxpage", devPluginVos.getTotalPage());
			// 2.获取其他开发者
			List<UserVO> otherDevUsers = userService.findOtherDevUsers(String.valueOf(visitoredId), 3);
			inv.getRequest().setAttribute("otherDevUsers", otherDevUsers);
			// 3.获取插友
			// Pagination<User> userFriends =
			// userService.findUserFriendes4Page(String.valueOf(visitoredId), 1,
			// Globals.WXPAGE_SHOW_NUMBER);
			// inv.getRequest().setAttribute("userFriends", userFriends);
			// inv.getRequest().setAttribute("frendes",
			// userFriends.getTotalCount());
			// inv.getRequest().setAttribute("maxFrPage",
			// userFriends.getTotalPage());
			// 4.获取礼品及礼品领用情况
			List<GiftVo> giftVos = giftService.findGifteInfo(String.valueOf(visitoredId), "");
			inv.getRequest().setAttribute("giftVos", giftVos);
			inv.getRequest().setAttribute(
					"shareArg",
					wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()),
							CommonUtils.randomUrl() + "/wx/user/redircDevOtherPageUser/1/" + String.valueOf(visitoredId)));
			return "devOtherUserPage";
		}
		return null;

	}

	/**
	 * 获得更多插件（开发者页面）
	 * 
	 * @param inv
	 * @param loginId
	 * @param frendCount
	 * @return
	 * @throws Exception
	 */
	@Get("/getMorePlugins")
	public String getMorePlugins(Invocation inv, @Param("loginId") String loginId, @Param("currentPage") int currentPage) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		Pagination<PluginDetailVO> devPluginVos = pluginService.finDevUserPlugin4Page(loginId, currentPage, Globals.WXPAGE_SHOW_NUMBER);
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
	@Get("/getMoreActes")
	public String getMoreActes(Invocation inv, @Param("loginId") String loginId, @Param("currentPage") int currentPage) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		Pagination<PluginActVo> myPluginActVos = pluginActService.findPluginActes4Page(loginId, null, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		String pluginActVoJson = JsonUtils.toJson(myPluginActVos);
		return "@json:" + pluginActVoJson;
	}

	/**
	 * 获得朋友信息
	 * 
	 * @param inv
	 * @param loginId
	 * @param frendCount
	 * @return
	 * @throws Exception
	 */
	@Get("/getMoreFriendes")
	public String getMoreFriendes(Invocation inv, @Param("loginId") String loginId, @Param("currentFrPage") int currentFrPage) throws Exception {
		// 用户信息
		if (ValidateUtil.isEmpty(loginId)) {
			return null;
		}
		Pagination<User> userFriends = userService.findUserFriendes4Page(loginId, currentFrPage, Globals.WXPAGE_SHOW_NUMBER);
		String userFriendsJson = JsonUtils.toJson(userFriends);
		return "@json:" + userFriendsJson;
	}

	/**
	 * 删除好友,删除活动
	 * 
	 * @param userId
	 * @param removeIds
	 * @throws Exception
	 */
	@Post("/removeActFr")
	public void removeUserFriends(@Param("loginId") String loginId, @Param("actRemoveId") String actRemoveId, @Param("frRemoveId") String frRemoveId) throws Exception {
		// 删除活动
		if (!ValidateUtil.isEmpty(actRemoveId) && actRemoveId.contains(",")) {
			pluginActService.removePluginAct(actRemoveId);
		}
		// 删除朋友
		if (!ValidateUtil.isEmpty(frRemoveId) && frRemoveId.contains(",")) {
			userService.removeUserFriends(loginId, frRemoveId);
		}
	}

}
