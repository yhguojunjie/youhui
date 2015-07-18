package com.yoxi.hudongtui.controllers.pc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.wx.OpenConstanst;
import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.user.IThirdService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;
import com.yoxi.hudongtui.vo.wx.BindWxdVO;

/**
 * 
 * 第三方账号相关
 *
 * @author wql
 *
 * @Date 2015年3月11日
 *
 */
@GlobalRequired
public class ThirdController {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IThirdService thirdService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAgentBusService agentBusService;
	@Autowired
	private IAgentInfoService agentInfoService;


	/**
	 * 跳转到微信网站登录扫二维码页面
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("toWetChatBind/{uid}")
	public String toWetChatBind(Invocation inv, @Param("uid") String uid )throws Exception{
		String appId = ReadProperties.getPara("wx_open_appid");
		String redirect_uri = URLEncoder.encode(ReadProperties.getPara("wx_open_authordomain")+"/pc/third/bindwx/"+uid,"UTF-8");
		inv.getRequest().setAttribute("appId",appId);
		inv.getRequest().setAttribute("redirect_uri",redirect_uri);
		return "wechatBind";
	}
	
	/**
	 * 绑定微信账号跳转
	 * @return
	 * @throws Exception
	 */
	@Get("toBindwx/{userId}")
	public String toBindwx(Invocation inv,@Param("userId") String userId)throws Exception{
		try {
			String redircUrl = URLEncoder.encode(ReadProperties.getPara("wx_open_authordomain")+"/pc/third/bindwx/"+userId,"UTF-8");
			String wechatUrl = OpenConstanst.getWebOauthaccessSnsapiLogin()
					.replace("APPID", ReadProperties.getPara("wx_open_appid"))
					.replace("REDIRECT_URI", redircUrl).replace("STATE", "huidongtui12099988");
			
			inv.getResponse().sendRedirect(wechatUrl);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 绑定微信账号
	 * @param inv
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@Get("bindwx/{userId}")
	public String bindwx(Invocation inv, @Param("userId") Integer userId)throws Exception{
		BindWxdVO bindWxVO = thirdService.bindwx(inv.getRequest(), userId);
		User user = SessionUtil.getUser(inv.getRequest());
		if(user == null){
			user = userService.findByUserId(userId);
			SessionUtil.setSessionUserAttr(inv.getRequest(), user);
		}
		Integer agentId = user.getAgentId();
		String domain = agentInfoService.getDomainById(agentId);
		String lastUrl = domain+"/pc/my/actList";
		if(bindWxVO.getBinded() == true){
			WebApplicationUtils.redirectMsg("该微信号已经绑定过用户！", "",  WebApplicationUtils.getBasePath()+"/pc/third/toWetChatBind/"+userId, true);
		}else{
			inv.getResponse().sendRedirect(lastUrl);
		}
		return null;
	}
	
}
