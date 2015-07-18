package com.yoxi.hudongtui.controllers.wx;

import java.net.URLEncoder;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.plugin.IUserPluginService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.plugin.UserPluginVo;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

public class UserPluginController {
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IUserPluginService userPluginService;
	@Autowired
	private IWxUserBusService wxUserBusService;
	
	
	/**
	 * 跳转到微信授权
	 * @throws Exception 
	 */
	@Get("/redircWxUserPluginUrl")
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
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath")+"/wx/userPlugin/list/"+String.valueOf(scope),"UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 查询用户插件列表信息
	 * @param xFlag 0表示全部，1表示可使用,2表示已过期
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/list/{scope}")
	public String getUserPlugins(Invocation inv, @Param("xFlag") String xFlag,@Param("currentPage") final int currentPage,@Param("scope") final int scope)
			throws Exception {
		// 1.用户登录信息
		MpThirdVO thirdVO =  wxUserBusService.mpLogin(inv.getRequest(),scope, null);
		String userId = String.valueOf(thirdVO.getUserId());
		if (ValidateUtil.isEmpty(xFlag)) {
			xFlag = "0";
		}
		Pagination<UserPluginVo> userPluginVos = userPluginService.findUserPlugins4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("userPluginVos", userPluginVos);
		inv.getRequest().setAttribute("WXPAGE_SHOW_NUMBER", Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("userPluginCount", userPluginVos.getTotalCount());
		inv.getRequest().setAttribute("maxpage", userPluginVos.getTotalPage());
		return "userPlugin";
	}
	/**
	 * 查询用户插件列表信息(ajax请求)
	 * @param xFlag 0表示全部，1表示可使用,2表示已过期
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxList")
	public String getUserPluginAjaxList(Invocation inv, @Param("xFlag") String xFlag,@Param("currentPage") final int currentPage)
			throws Exception {
		// 1.用户登录信息
		String userId = String.valueOf("1");
		// 2.用户插件信息 【xFlag 0表示全部，1表示可使用,2表示已过期】
		if (ValidateUtil.isEmpty(xFlag)) {
			xFlag = "0";
		}
		Pagination<UserPluginVo> userPluginVos = userPluginService.findUserPlugins4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		return "@json:" + JsonUtils.toJson(userPluginVos);
	}
}
