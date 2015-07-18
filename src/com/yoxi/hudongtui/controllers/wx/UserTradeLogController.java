package com.yoxi.hudongtui.controllers.wx;

import java.net.URLEncoder;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.service.user.IUserTradeLogService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.plugin.UserTradeLogVo;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

public class UserTradeLogController {
	@Autowired
	private IUserTradeLogService userTradeLogService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWxUserBusService wxUserBusService;
	
	/**
	 * 跳转到微信授权
	 * @throws Exception 
	 */
	@Get("/redircWxTradeLogUrl")
	public void redircWxUrl(Invocation inv,@Param("scope") String scope) throws Exception{
		if(ValidateUtil.isEmpty(scope)){
			scope = "1";
		}
		String oauthUrl = MpConstants.getOauthAccessBase();
		if(scope != null){
			if("2".equals(scope)){
				oauthUrl = MpConstants.getOauthAccessUserinfo();
			}
		}
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath")+"/wx/userTradeLog/list/"+String.valueOf(scope),"UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 查询交易记录列表信息
	 * @param xFlag 预留条件
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/list/{scope}")
	public String getUserPlugins(Invocation inv, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage,@Param("scope") final int scope) throws Exception {
		// 1.用户登录信息
		MpThirdVO thirdVO =  wxUserBusService.mpLogin(inv.getRequest(),scope, null);
		String userId = String.valueOf(thirdVO.getUserId());
		// 2.交易记录
		Pagination<UserTradeLogVo> userTragePagination = userTradeLogService.findUserTradeLogs4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("userTrageLogVos", userTragePagination);
		inv.getRequest().setAttribute("maxpage",userTragePagination.getTotalPage());
		// 3.用户代币
		User user = userService.findByUserId(Integer.valueOf(userId));
		inv.getRequest().setAttribute("repreCoin",user.getRepreCoin());
		return "userTradeLog";
	}
	
	/**
	 * 查询交易记录列表信息
	 * @param xFlag 预留条件
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxList")
	public String getUserAjaxPlugins(Invocation inv, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage) throws Exception {
		// 1.用户登录信息
		String userId = "13";
		// 2.交易记录
		Pagination<UserTradeLogVo> userTragePagination = userTradeLogService.findUserTradeLogs4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("userTrageLogVos", userTragePagination);
		return "userTradeLog";
	}

}
