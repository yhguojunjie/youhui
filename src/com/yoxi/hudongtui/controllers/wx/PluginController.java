package com.yoxi.hudongtui.controllers.wx;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.ErrorEnum;
import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.constants.wx.MpConstants;
import com.yoxi.hudongtui.exception.ServiceException;
import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.wx.WxUserInfo;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IPluginBusService;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.service.wx.IWxJsAPIService;
import com.yoxi.hudongtui.service.wx.IWxUserBusService;
import com.yoxi.hudongtui.service.wx.IWxUserInfoService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.StringUtils;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.utils.wx.CommonUtils;
import com.yoxi.hudongtui.vo.plugin.PluginActVo;
import com.yoxi.hudongtui.vo.plugin.PluginDetailVO;
import com.yoxi.hudongtui.vo.wx.MpThirdVO;

/**
 * 
 * 微信版-插件相关
 * 
 * @author wanql
 * 
 * 2014-12-09
 *
 */
public class PluginController {

	@Autowired
	private IPluginService pluginService;
	@Autowired
	private IPluginBusService pluginBusService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IWxUserBusService wxUserBusService;
	@Autowired
	private IWxUserInfoService wxUserInfoService;
	@Autowired
	private IWxJsAPIService wxJsAPIService;
	
	/**
	 * 跳到插件库
	 * @return
	 * @throws Exception
	 */
	@Get("list")
	public String index(Invocation inv )throws Exception{
		return "index";
	}
	/**
	 * 进入微信版首页
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginList")
	public String getPluginlist(Invocation inv)throws Exception{
		//默认值
		String typeConStr = pluginService.getTypeCon("0");
		String orderStrCon = pluginService.getOderFlag("1", "0");
		Pagination<PluginDetailVO> paginationPlugin = pluginService.findPlugins4Page(typeConStr, orderStrCon, 1, Globals.WXPAGE_SHOW_NUMBER);
		inv.getRequest().setAttribute("paginationPlugin", paginationPlugin);
		inv.getRequest().setAttribute("maxpage",paginationPlugin.getTotalPage());
		//ReadProperties.getPara("wx_httpPath")+"/"
		inv.getRequest().setAttribute("shareArg", wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()),
				CommonUtils.randomUrl()+"/wx/plugin/pluginList"));
		return "pluginList";
	}
	
	/**
	 * 进入微信版首页(ajax请求,count递增)
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("/pluginAjaxList")
	public String getPluginAjaxlist(Invocation inv,@Param("typeFlag") String typeFlag, @Param("orderFlag") String orderFlag, 
		@Param("count") final int count) throws Exception {
		String typeConStr = pluginService.getTypeCon("0");
		String orderStrCon = pluginService.getOderFlag(orderFlag, "0");
		Pagination<PluginDetailVO> paginationPlugin = pluginService.findPlugins4Page(typeConStr, orderStrCon, count, Globals.WXPAGE_SHOW_NUMBER);
		return "@json:" + JsonUtils.toJson(paginationPlugin);
	}
	
	
	/**
	 * 详情页--微信授权
	 * @throws Exception
	 */
	@Get("/redircToDetail/{scope}/{pluginId}")
	public void redircToDetail(Invocation inv, @Param("scope") String scope,
			@Param("pluginId") String pluginId) throws Exception {
		String oauthUrl = MpConstants.getOauthAccessBase();
		String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_httpPath") + "/wx/plugin/detail/"+scope+"/"+pluginId, "UTF-8");
		oauthUrl = oauthUrl.replace("REDIRECT_URI", redircUrl);
		inv.getResponse().sendRedirect(oauthUrl);
	}
	
	/**
	 * 详情
	 * @param inv
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Get("detail/{scope}/{pluginId}")
	public String detail(Invocation inv,@Param("scope") Integer scope,
				@Param("pluginId") Integer pluginId)throws Exception{
		
		MpThirdVO thirdVO = wxUserBusService.mpLogin(inv.getRequest(), scope, null);
		
		WxUserInfo wxUserInfo = wxUserInfoService.findByKey("mpOpenId", thirdVO.getOpenId());
		
		if(thirdVO != null && wxUserInfo != null){
			inv.getRequest().setAttribute("uid", wxUserInfo.getUserId());
			inv.getRequest().setAttribute("openid",thirdVO.getOpenId());
		}else{
			throw new ServiceException(ErrorEnum.WX_GETOPENID_FAIL.getCode(),ErrorEnum.WX_GETOPENID_FAIL.getDesc());
		}
		
//		inv.getRequest().setAttribute("uid","3" );
//		inv.getRequest().setAttribute("openid","oEqHMt9c8-Ie3X3j1ppbXTWVVEow");
		
		//插件信息
		Plugin plugin = pluginService.getById(pluginId);
		
		inv.getRequest().setAttribute("plugin", plugin);
		
		//该插件正在进行的活动
		List<PluginActVo> pluginActVos = pluginActService.findIngPluginActes(pluginId);
		int actNum = 3;
		if (3 > pluginActVos.size()) {
			actNum = pluginActVos.size();
		}
		inv.getRequest().setAttribute("pluginActVos", pluginActVos.subList(0, actNum));
		inv.getRequest().setAttribute("pluginActVoCount", pluginActVos.size());
		
		//推荐插件
		String tjCondition = "WHERE a.status = '1' ORDER BY a.publishTime DESC";
		List<PluginDetailVO> tjPluginVos = pluginService.findTjPlugines(tjCondition);
		Collections.shuffle(tjPluginVos);
		inv.getRequest().setAttribute("tjPluginVoCount", tjPluginVos.size());
		int pluginNum = 5;
		if (5 > tjPluginVos.size()) {
			pluginNum = tjPluginVos.size();
		}
		inv.getRequest().setAttribute("tjPluginVos", tjPluginVos.subList(0, pluginNum));
		//开发者信息
		User dev = userService.findByUserId(plugin.getUserId());
		inv.getRequest().setAttribute("dev", dev);
		inv.getRequest().setAttribute("shareArg", wxJsAPIService.getShareSignPackage(WebApplicationUtils.getRequestUrl(inv.getRequest()),
				CommonUtils.randomUrl()+"/wx/plugin/redircToDetail/1/"+String.valueOf(pluginId)));
		return "detail";
	}
	
	/**
	 * 插件详情页面推荐插件
	 * @param inv
	 * @param plugingId
	 * @return
	 * @throws Exception
	 */
	@Get("/tjPlugins")
	public String getTjPlugins(Invocation inv, @Param("pluginId") String pluginId,
					@Param("tjNum") Integer tjNum) throws Exception {
		// 推荐插件
		String tjCondition = "WHERE a.status = '1' ";
		if(!StringUtils.isNullBlank(pluginId)){
			tjCondition += " and a.id != "+pluginId;
		}
		tjCondition += " ORDER BY a.publishTime DESC LIMIT 0,3";
		List<PluginDetailVO> tjPluginVos = pluginService.findTjPlugines(tjCondition);
		Collections.shuffle(tjPluginVos);
		String tjPluginVosJson = JsonUtils.toJson(tjPluginVos.subList(0, tjNum));
		return "@json:" + tjPluginVosJson;
	}
	
	
	/**
	 * 试用插件
	 * @param inv
	 * @param plugingId 插件Id
	 * @return json 1试用添加成功.2用户未登录.3 已购买,已试用. 4已购买,未试用. 5未购买,已试用
	 * @throws Exception
	 */
	@Post("tryout")
	public String tryout(Invocation inv, @Param("pluginId") Integer plugingId,
			@Param("uid") Integer uid) throws Exception {
		Map<String,String> dataMap = pluginBusService.tryOut(new Plugin(plugingId),uid);
		return "@json:" + JsonUtils.toJson(dataMap);
	}

	/**
	 * 购买插件条件判断
	 * @param inv
	 * @param plugingId 插件Id
	 * @return status取值 1进入正常购买流程,2 用户未登录, 3 已购买，插件过期，重新购买,可以进入购买流程
	 *         ,4已购买，未过期，可正常使用 repreCoin 当前用户账号拥有的代币数
	 * @throws Exception
	 */
	@Post("tobuy")
	public String tobuy(Invocation inv, @Param("pluginId") Integer plugingId,
			@Param("uid") Integer uid) throws Exception {
		Map<String, String> dataMap = pluginBusService.prepareBuy(new Plugin(plugingId), uid);
		return "@json:" + JsonUtils.toJson(dataMap);
	}
	
	/**
	 * 免费使用
	 * @param inv
	 * @param plugingId 插件id
	 * @return 1 成功处理 跳到我的淘插件.2用户未登录.3已免费使用过，如果过期则自己续期. 0系统错误
	 * @throws Exception
	 */
	@Post("freeUse")
	public String freeUse(Invocation inv, @Param("pluginId") Integer plugingId,
			@Param("uid") Integer uid) throws Exception {
		Map<String, String> dataMap = pluginBusService.freeUser(new Plugin(plugingId), uid);
		return "@json:" + JsonUtils.toJson(dataMap);
	}
}
