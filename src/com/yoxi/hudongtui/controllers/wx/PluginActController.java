package com.yoxi.hudongtui.controllers.wx;

import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.plugin.IUserPluginService;
import com.yoxi.hudongtui.service.wx.IWxJsAPIService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.utils.wx.CommonUtils;
import com.yoxi.hudongtui.vo.plugin.PluginActCenterVo;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

public class PluginActController {

	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IUserPluginService userPluginService;
	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IWxUserBusService wxUserBusService;
	@Autowired
	private IWxJsAPIService wxJsAPIService;
	
	
	/**
	 * 跳转到我的插件微信授权
	 * @throws Exception 
	 */
	@Get("/redircWxPluginActUrl")
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
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath")+"/wx/pluginAct/list/"+String.valueOf(scope),"UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}

	/**
	 * 我的插件
	 * @param xFlag 0.全部，1.未开始，2.正在进行,3.已结束
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/list/{scope}")
	public String getPluginActs(Invocation inv, @Param("xFlag") String xFlag, @Param("tjCondition") String tjCondition, @Param("currentPage") final int currentPage,@Param("scope") final int scope)
			throws Exception {
		// 1.用户登录信息
		MpThirdVO thirdVO =  wxUserBusService.mpLogin(inv.getRequest(),scope, null);
		String userId = String.valueOf(thirdVO.getUserId());
		// 2.判断活动执行情况 【xFlag 0.全部，1.未开始，2.正在进行,3.已结束】
		if (ValidateUtil.isEmpty(xFlag)) {
			xFlag = "0";
		}
		Pagination<PluginActVo> paginationPluginAct = pluginActService.findPluginActes4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("paginationPluginAct", paginationPluginAct);
		inv.getRequest().setAttribute("maxpage", paginationPluginAct.getTotalPage());
		// 3. 推荐的插件活动
		List<PluginActVo> tjPluginActVos = pluginActService.findTjPluginActes(userId);
		inv.getRequest().setAttribute("tjPluginActVos", tjPluginActVos);
//		inv.getRequest().setAttribute("shareArg", wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()),
//				WebApplicationUtils.getBasePath()+"/wx/pluginAct/redircWxPluginActUrl"));
		return "pluginAct";
	}

	/**
	 * 查询用户插件活动列表信息(ajax请求)
	 * @param xFlag 0.全部，1.未开始，2.正在进行,3.已结束
	 * @param currPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/ajaxList/{userId}")
	public String getPluginActAjaxList(Invocation inv,@Param("userId") String userId, @Param("xFlag") String xFlag, @Param("currentPage") final int currentPage) throws Exception {
		// 1.用户登录信息
//		String userId = String.valueOf("13");
		// 2.判断活动执行情况 【xFlag 0.全部，1.未开始，2.正在进行,3.已结束】
		if (ValidateUtil.isEmpty(xFlag)) {
			xFlag = "0";
		}
		Pagination<PluginActVo> paginationPluginAct = pluginActService.findPluginActes4Page(userId, xFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		return "@json:" + JsonUtils.toJson(paginationPluginAct);
	}

	/**
	 * 活动中心信息
	 * @param param [1.热度，2.发布时间]
	 * @param currentPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginActCenterList")
	public String findPluginActCenterVos(Invocation inv, @Param("orderFlag") String orderFlag, @Param("currentPage") final int currentPage) throws Exception {
		// [1.热度，2.发布时间]
		if (ValidateUtil.isEmpty(orderFlag)) {
			orderFlag = "2";
		}
		Pagination<PluginActCenterVo> paginationPluginActCent = pluginActService.findPluginActCenterVos(orderFlag, 1, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("paginationPluginActCent", paginationPluginActCent);
		inv.getRequest().setAttribute("maxpage", paginationPluginActCent.getTotalPage());
    	inv.addModel("shareArg", wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()),
    			CommonUtils.randomUrl()+"/wx/pluginAct/pluginActCenterList"));
		return "pluginActCenter";
	}

	/**
	 * 活动中心信息(ajax请求)
	 * @param param [1.热度，2.发布时间]
	 * @param currentPage 当前页
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginActCenterAjaxList")
	public String findPluginActCenterVosAjax(Invocation inv, @Param("orderFlag") String orderFlag, @Param("currentPage") final int currentPage) throws Exception {
		// [1.热度，2.发布时间]
		if (ValidateUtil.isEmpty(orderFlag)) {
			orderFlag = "2";
		}
		Pagination<PluginActCenterVo> paginationPluginActCent = pluginActService.findPluginActCenterVos(orderFlag, currentPage, Globals.WXPAGE_SHOW_NUMBER);
		return "@json:" + JsonUtils.toJson(paginationPluginActCent);
	}
}
