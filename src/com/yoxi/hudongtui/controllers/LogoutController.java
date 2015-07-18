package com.yoxi.hudongtui.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.rest.Get;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 
 * 退出登录
 * 
 * @author wql
 * 
 *         2014-11-14
 * 
 */
public class LogoutController {

	@Get("")
	public String logout(Invocation inv) throws Exception {
		// HttpServletResponse response = inv.getResponse();
		SessionUtil.destroy(inv.getRequest(), Globals.SESSION_USER);
		// CookieUtil.clearCookie(response);
		inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath() + "/pc/plugin/list");
		return null;
	}
}
