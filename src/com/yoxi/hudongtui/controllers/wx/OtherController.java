package com.yoxi.hudongtui.controllers.wx;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.model.plugin.Plugin;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.plugin.IPluginService;
import com.yoxi.hudongtui.service.user.IUserService;

/**
 * 
 * 其它业务
 * 
 * @author wangql
 * 
 * 2014-12-15
 *
 */
public class OtherController {

	@Autowired
	private IPluginService pluginService;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 进支付方式选择页面
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("payType")
	public String toPayType(Invocation inv,@Param("uid") Integer uid,
			@Param("plgid") Integer plgid,@Param("openid") String openid)throws Exception{
		
		Integer userId = uid;
		Plugin plugin = pluginService.getById(plgid);
		User user = userService.findByUserId(userId);
		
//		Double afPluginPrice = plugin.getPromPrice() != null && !plugin.getPromPrice().equals(0.0) ? plugin.getPromPrice() : plugin.getPrice();
		
		inv.getRequest().setAttribute("plugin", plugin);
		inv.getRequest().setAttribute("user", user);
		inv.getRequest().setAttribute("openId", openid);
		
		return "payType";
	}
	
	/**
	 * 跳转到经典案例
	 * @param inv
	 * @return
	 */
	@Get("toClassicCase")
	public String toClassicCase(final Invocation inv){
	
		return "classiccase";
	}
	
}
