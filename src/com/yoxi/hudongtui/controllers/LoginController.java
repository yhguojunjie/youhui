package com.yoxi.hudongtui.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.constants.wx.OpenConstanst;
import com.yoxi.hudongtui.model.user.EmailValidationCode;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.thirdpart.IQQConnectService;
import com.yoxi.hudongtui.service.user.EmailValidationCodeService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.service.wx.IWxUserInfoService;
import com.yoxi.hudongtui.utils.common.MailSender;
import com.yoxi.hudongtui.utils.common.PasswordUtil;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.utils.pay.alipay.wap.sign.MD5;
import com.yoxi.hudongtui.vo.user.EmailValidationCodeExsitVO;
import com.yoxi.hudongtui.vo.user.LoginVO;
import com.yoxi.hudongtui.vo.user.ThirdVO;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

/**
 * 
 * 登录相关
 * 
 * @author wql
 * 
 *         2014-11-17
 * 
 */
@GlobalRequired
public class LoginController {

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IQQConnectService qqConnectService;
	@Autowired
	private IUserService userService;
	@Autowired
	private EmailValidationCodeService emailValidationCodeService;
	@Autowired
	private IWxUserBusService wxUserBusService;
	@Autowired
	private IAgentInfoService agentInfoService;
	@Autowired
	private IWxUserInfoService wxUserInfoService;

	/**
	 * 跳转到登陆页
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("")
	// @Token(needSaveToken = true)
	public String toLogin(Invocation inv) throws Exception {
		return "pc/login";
	}

	/**
	 * 跳转到找回密码页面
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("toFindPwd")
	// @Token(needSaveToken = true)
	public String toFindPwd(HttpServletRequest request, Invocation inv)
			throws Exception {
		return "pc/findPwd";
	}

	/**
	 * 重置密码页面
	 * 
	 * @param loginVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Post("resetPassWord")
	// @Token(needRemoveToken = true)
	public String resetPassWord(HttpServletRequest request, LoginVO loginVO)
			throws Exception {
		// String userId=request.getParameter("userId");
		String getStr = " userId LIKE '"
				+ Integer.parseInt(loginVO.getUserId()) + "' ";
		User user = userService.getByStr(getStr);
		user.setPassword(PasswordUtil.encrypt(user.getAccount(),
				loginVO.getPassword(), PasswordUtil.getStaticSalt()));
		String str = " , password ='" + user.getPassword() + "'";
		userService.updataUserSetPassWord(user.getUserId(), str);
		String msg = "密码重置成功！";
		request.setAttribute("message2", msg);
		WebApplicationUtils.sendRedirectMsg("密码重置成功", "",
				WebApplicationUtils.getBasePath() + "/login/", true);
		return null;
	}

	/**
	 * 发送邮件
	 * 
	 * @param loginVO
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unused")
	@Post("findPassWord")
	// @Token(needRemoveToken = true)
	public String findPassWord(HttpServletRequest request, LoginVO loginVO)
			throws Exception {

		String getStr = " account LIKE '" + loginVO.getAccount() + "' ";
		User user = userService.getByStr(getStr);
		EmailValidationCode emailValidationCode = new EmailValidationCode();
		EmailValidationCodeExsitVO emailValidationCodeExsitVO = emailValidationCodeService
				.findExistByuserId(String.valueOf(user.getUserId()));

		if (user != null) {
			String toMail = user.getAccount();
			// String basePath = this.getBasePath();

			String secretKey = UUID.randomUUID().toString(); // 密钥
			Timestamp outDate = new Timestamp(
					System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
			long date = outDate.getTime() / 1000 * 1000; // 忽略毫秒数
			emailValidationCode.setOutDate(outDate);
			emailValidationCode.setValidationCode(secretKey.trim());
			emailValidationCode.setUserId(user.getUserId());
			// userService.updataUserSet(user); // 保存到数据库
			if (emailValidationCodeExsitVO != null) {
				emailValidationCodeService.removeEmailValidationCode(String
						.valueOf(emailValidationCodeExsitVO.getUserId()));
			}
			emailValidationCodeService.save(emailValidationCode);
			String key = user.getNickName() + "$" + date + "$" + secretKey;
			String digitalSignature = MD5.sign(user.getNickName(), secretKey,
					"UTF-8");// 数字签名

			String emailTitle = "密码找回";
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			String resetPassHref = basePath + "login/" + "checkResetLink?sid="
					+ digitalSignature + "&userId=" + user.getUserId();
			String emailContent = "请勿回复本邮件.点击下面的链接,重设密码:" + resetPassHref
					+ "。 notice:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'";

			boolean flag = MailSender.getInstance().sentEmail(emailContent,
					emailTitle, toMail);
			if (flag) {
				request.setAttribute("message", "重置密码邮件已经发送，请登陆邮箱进行重置！");
				WebApplicationUtils.sendRedirectMsg("邮件已经发送成功", "",
						WebApplicationUtils.getBasePath() + "/login/", true);
				return null;
			} else {
				request.setAttribute("message", "操作失败，轻稍后重新尝试！");
				WebApplicationUtils
						.sendRedirectMsg("操作失败，轻稍后重新尝试！", "",
								WebApplicationUtils.getBasePath()
										+ "/login/toFindPwd/", true);
				return null;
			}

		} else {
			request.setAttribute("message2", "当前的用户名不存在！");
			WebApplicationUtils.sendRedirectMsg("当前的用户名不存在！", "",
					WebApplicationUtils.getBasePath() + "/login/toFindPwd/",
					true);
			return null;
		}

	}

	/**
	 * 点击邮件中链接处理
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Get("checkResetLink")
	// @Token(needSaveToken = true)
	public String checkResetLink(HttpServletRequest request) throws Exception {
		String sid = request.getParameter("sid");
		String userId = request.getParameter("userId");
		String getStr = " userId LIKE '" + userId + "' ";

		User user = userService.getByStr(getStr);
		// String getStr2 = " userId LIKE '" + userId + "' ";
		EmailValidationCode emailValidationCode = emailValidationCodeService
				.getByStr(getStr);

		String msg = "";
		if (sid.equals("") || user.getNickName().equals("")) {
			msg = "链接不完整,请重新生成";
			// model.addObject("msg",msg) ;
			request.setAttribute("message2", msg);
			WebApplicationUtils.sendRedirectMsg(msg, "",
					WebApplicationUtils.getBasePath() + "/login/toFindPwd/",
					true);
			return null;
		}

		if (user == null) {
			msg = "链接错误,无法找到匹配用户,请重新申请找回密码.";
			// model.addObject("msg",msg) ;
			request.setAttribute("message2", msg);
			WebApplicationUtils.sendRedirectMsg(msg, "",
					WebApplicationUtils.getBasePath() + "/login/toFindPwd/",
					true);
			return null;
		}

		String dateStr = "";
		Date date = emailValidationCode.getOutDate();
		// format的格式可以任意
		DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		// DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");
		try {
			dateStr = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Timestamp outDate = new Timestamp(System.currentTimeMillis());
		try {
			outDate = Timestamp.valueOf(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (outDate.getTime() <= System.currentTimeMillis()) { // 表示已经过期
			msg = "链接已经过期,请重新申请找回密码.";
			// model.addObject("msg",msg) ;
			request.setAttribute("message2", msg);
			WebApplicationUtils.sendRedirectMsg(msg, "",
					WebApplicationUtils.getBasePath() + "/login/toFindPwd/",
					true);
			return null;
		}

		String key = user.getAccount() + "$" + outDate.getTime() / 1000 * 1000
				+ "$" + emailValidationCode.getValidationCode(); // 数字签名
		String digitalSignature = MD5.sign(user.getNickName(),
				emailValidationCode.getValidationCode(), "UTF-8");// 数字签名
		System.out.println(key + "\t" + digitalSignature);
		if (!digitalSignature.equals(sid)) {
			msg = "链接不正确,是否已经过期了?重新申请吧";
			// model.addObject("msg",msg) ;
			request.setAttribute("message2", msg);
			WebApplicationUtils.sendRedirectMsg(msg, "",
					WebApplicationUtils.getBasePath() + "/login/toFindPwd/",
					true);
			return null;
		}
		// model.setViewName("user/reset_password"); //返回到修改密码的界面
		// return "pc/resetPwd";
		// WebApplicationUtils.sendRedirectMsg("邮件已经发送成功", "",
		// WebApplicationUtils.getBasePath() + "/login/", true);
		return "pc/resetPwd";
	}

	/**
	 * 注册用户登录
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Post("doLogin")
	// @Token(needRemoveToken = true)
	public String doLogin(HttpServletRequest request,
			HttpServletResponse response, Invocation inv, LoginVO loginVO)
			throws Exception {
		String email = request.getParameter("email");
		String getStr = " email LIKE '" + email + "' ";
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		User user = userService.getByStr(getStr);
		String basePath = WebApplicationUtils.getBasePath();
		if (user != null) {
			String decryptStr = PasswordUtil.decrypt(user.getPassword(),
					password, PasswordUtil.getStaticSalt());
			if (user.getEmail().equals(decryptStr)) {
				// 正常登录
				// 选择下次自动登录，cookie处理
				if (autoLogin != null && !"".equals(autoLogin)) {
					/*
					 * if (loginVO.getAutoLogin().equals("on")) {
					 * CookieUtil.saveCookie(user, inv.getResponse()); }
					 */
					request.getSession().setAttribute("flag", autoLogin);
					// set cookie
					if (autoLogin != null && !"".equals(autoLogin)
							&& loginVO.getAutoLogin().equals("1")) {
						Cookie cookie = new Cookie("cookie_user", email + "-"
								+ password);
						cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
						response.addCookie(cookie);
					} else {
						Cookie cookie = new Cookie("cookie_user", email + "-"
								+ null);
						cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
						response.addCookie(cookie);
					}
				}

				// Integer agentId = user.getAgentId();
				// String domain = agentInfoService.getDomainById(agentId);

				if (SessionUtil.getUser(inv.getRequest()) != null) {
					// 如果有登陆先注销
					SessionUtil.destroy(inv.getRequest(), Globals.SESSION_USER);
				}
				SessionUtil.setSessionUserAttr(inv.getRequest(), user);
				// 检查是否绑定微信号
				Integer uid = wxUserInfoService.userExist(user.getUserId());
				if (uid == null) {// 没有绑定公众号
					inv.getResponse().sendRedirect(basePath);
					return null;
				}
				// 登录后回到上次发起登录页面处理

				inv.getResponse().sendRedirect(basePath);
				return null;
			} else {
				// 密码错误
				request.setAttribute("message", "密码错误,请重新输入密码！");
				// inv.getResponse().sendRedirect("/hudongtui/login/");
				WebApplicationUtils.redirectMsg("密码错误,请重新输入密码！!", "", basePath
						+ "/login" + "", true);
				// return null;
				return "pc/login";
			}

		} else {
			// 账号不存在
			request.setAttribute("message", "账号不存在,请重新输入账号！");
			WebApplicationUtils.redirectMsg("密码错误,请重新输入密码！!", "", basePath
					+ "/login" + "", true);
			// inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/login/");
			return "pc/login";
			// /return null;
		}
		// return "";

	}

	/**
	 * 微信网站登录
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	// @Get("wechat")
	public String wxWebLogin(Invocation inv) {
		try {
			String redircUrl = URLEncoder.encode(
					ReadProperties.getPara("wx_open_authordomain")
							+ "/login/wechatRedirect", "UTF-8");
			String wechatUrl = OpenConstanst.getWebOauthaccessSnsapiLogin()
					.replace("APPID", ReadProperties.getPara("wx_open_appid"))
					.replace("REDIRECT_URI", redircUrl)
					.replace("STATE", "tchajian12099988");
			inv.getResponse().sendRedirect(wechatUrl);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 网站微信登录回调
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	// @Get("wechatRedirect")
	public String wxWebRedirc(Invocation inv, @Param("role") String role)
			throws Exception {

		ThirdVO thirdVO = wxUserBusService.webLogin(inv.getRequest(), role);
		User user = userService.findByUserId(thirdVO.getUserId());
		SessionUtil.setSessionUserAttr(inv.getRequest(), user);
		String lastUrl = WebApplicationUtils.getBasePath() + "/pc/my/actList";
		if (inv.getRequest().getSession().getAttribute(Globals.SESSION_LASTURL) != null) {
			if (!StringUtils.isNullBlank(lastUrl)) {
				lastUrl = inv.getRequest().getSession()
						.getAttribute(Globals.SESSION_LASTURL).toString();
			}
		}
		inv.getResponse().sendRedirect(lastUrl);
		return null;
	}

	/**
	 * 微信公众平台登录
	 * 
	 * @param inv
	 * @param scope
	 *            授权类型 1 base,2 userInfo
	 * @param role
	 *            角色 1商家 2开发者
	 * @return
	 * @throws Exception
	 */
	// @Get("wechatmp/{scope}")
	public String wxMpLogin(Invocation inv, @Param("scope") Integer scope,
			@Param("role") String role) throws Exception {

		String oauthUrl = MpConstants.getOauthAccessBase();
		if (scope != null) {
			if (scope == 2) {
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("httpPath")
				+ "/login/wechatmpRedirect/" + String.valueOf(scope), "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		try {
			inv.getResponse().sendRedirect(oauthUrl);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信公众平台登录 回调
	 * 
	 * @param inv
	 * @param scope
	 * @param role
	 * @return
	 * @throws Exception
	 */
	// @Get("wechatmpRedirect/{scope}")
	public String wxMpRedirc(Invocation inv, @Param("scope") Integer scope,
			@Param("role") String role) throws Exception {

		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope,
				role);
		inv.getRequest().setAttribute("userInfo", thirdVO);
		return null;
	}

	/**
	 * qq 网页登录
	 * 
	 * @param inv
	 * @throws Exception
	 */
	// @Get("/qq")
	public void qqLogin(Invocation inv) throws Exception {
		inv.getResponse().setContentType("text/html;charset=utf-8");
		try {
			inv.getResponse().sendRedirect(
					new Oauth().getAuthorizeURL(inv.getRequest()));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}

	}

	/**
	 * QQ网页登录回调
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	// @Get("/qqRedirect")
	public String qqRedirect(Invocation inv, @Param("role") String role)
			throws Exception {

		ThirdVO thirdVO = qqConnectService.login(inv.getRequest(), role);
		User user = userService.findByUserId(thirdVO.getUserId());
		SessionUtil.setSessionUserAttr(inv.getRequest(), user);
		String lastUrl = WebApplicationUtils.getBasePath() + "/pc/my/actList";
		if (inv.getRequest().getSession().getAttribute(Globals.SESSION_LASTURL) != null) {
			if (!StringUtils.isNullBlank(lastUrl)) {
				lastUrl = inv.getRequest().getSession()
						.getAttribute(Globals.SESSION_LASTURL).toString();
			}
		}
		inv.getResponse().sendRedirect(lastUrl);
		return null;
	}

	/**
	 * 重新登录
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("relogin")
	public String relogin(Invocation inv) throws Exception {
		return "a:../views/pc/login.jsp";
	}
}
