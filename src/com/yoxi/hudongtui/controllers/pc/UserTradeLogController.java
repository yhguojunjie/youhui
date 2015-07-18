package com.yoxi.hudongtui.controllers.pc;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.user.IUserTradeLogService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.vo.plugin.UserTradeLogVo;

@LoginRequired
public class UserTradeLogController {
	
	@Autowired
	private IUserTradeLogService userTradeLogService;

	/**
	 * 查询交易记录列表信息
	 * @param xFlag 预留条件
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/list")
	public String getUserPlugins(Invocation inv, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage) throws Exception {
		// 1.用户登录信息
		User user = SessionUtil.getUser(inv.getRequest());
		String userId = String.valueOf(user.getUserId());
//		String userId = "1000000012";
		// 2.交易记录
		Pagination<UserTradeLogVo> userTragePagination = userTradeLogService.findUserTradeLogs4Page(userId, xFlag, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("userTrageLogVos", userTragePagination);
		return "userTradeLog";
	}

}
