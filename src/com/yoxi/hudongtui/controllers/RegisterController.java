package com.yoxi.hudongtui.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.FastDFSUtils;
import com.yoxi.hudongtui.utils.common.PasswordUtil;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.user.RegisterVO;

/**
 * 
 * 注册相关
 * 
 * @author wql
 * 
 * @Date 2015年3月13日
 * 
 */
public class RegisterController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAgentBusService agentBusService;
	@Autowired
	private IAgentInfoService agentInfoService;

	/**
	 * 跳转到注册页面
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("/pc")
	// @Token(needSaveToken = true)
	public String register(Invocation inv) throws Exception {
		return "pc/register";
	}

	/**
	 * 跳转到注册协议页面
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("/agreeMent")
	// @Token(needSaveToken = true)
	public String agreeMent(Invocation inv) throws Exception {
		return "pc/agreement";
	}

	/**
	 * 提交注册
	 * 
	 * @param registerVO
	 * @return
	 * @throws Exception
	 */
	@Post("doRegister")
	@GlobalRequired
	// @Token(needRemoveToken = true)
	public String doRegister(Invocation inv, HttpServletRequest request, HttpSession session, RegisterVO registerVO, @Param("headimgbase64") String headimgbase64) throws Exception {
		if (registerVO != null) {

			// 验证码处理

			if (!(registerVO.getIdentifyCode().equalsIgnoreCase(session.getAttribute("RANDOMVALIDATECODEKEY").toString()))) { // 忽略验证码大小写
				request.setAttribute("msg", "验证码不正确!");
				// inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/login/");
				return "pc/register";

			} else {
				User user = new User();
				user.setAccount(registerVO.getAccount());
				user.setEmail(registerVO.getEmail());
				user.setNickName(registerVO.getNickName());
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				// 密码处理
				if (!StringUtils.isNullBlank(registerVO.getPassword())) {
					user.setPassword(PasswordUtil.encrypt(registerVO.getAccount(), registerVO.getPassword(), PasswordUtil.getStaticSalt()));
				}
				user.setRole("1");
				// 用户归属代理商处理

				AgentInfoVO agentInfoVO = SessionUtil.getAgentInfo(inv.getRequest());
				if (agentInfoVO != null) {
					user.setAgentId(agentInfoVO.getId());
				}
				// 上传图片处理
				if (!StringUtils.isNullBlank(headimgbase64)) {
					String url = FastDFSUtils.getFastDfsPath(headimgbase64, "jpg");
					user.setHeadimgUrl(url);
				}
				Integer id = userService.save(user);
				user.setUserId(id);
				if (SessionUtil.getUser(inv.getRequest()) != null) {
					// 如果有登陆先注销
					SessionUtil.destroy(inv.getRequest(), Globals.SESSION_USER);
				}
				SessionUtil.setSessionUserAttr(request, user);
				String domian = agentInfoService.getDomainById(agentInfoVO.getId());
				WebApplicationUtils.redirectMsg("恭喜您已注册成功", "", domian + "/pc/third/toWetChatBind/" + id, true);
			}
		}
		return null;
	}

	/**
	 * 验证账号是否被注册过
	 * 
	 * @param inv
	 * @param account
	 *            账号
	 * @param type
	 *            0普通账号， 1邮箱，2手机，
	 * @return 0 账号已经被占用，1 未占用
	 * @throws Exception
	 */
	@Post("checkAccount")
	public String checkAccount(Invocation inv, @NotBlank @Param("account") String account) throws Exception {
		String getStr = " account LIKE '" + account + "' ";
		User user = userService.getByStr(getStr);
		if (user == null) {
			return "@json:" + "{\"state\":1}";
		} else {
			return "@json:" + "{\"state\":0}";
		}
	}
}
