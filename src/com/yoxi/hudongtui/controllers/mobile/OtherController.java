package com.yoxi.hudongtui.controllers.mobile;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * 
 * 系统与其它相关的页面
 * 
 * @author wql
 * 
 *
 */
public class OtherController {
	
	/**
	 * 帮助中心
	 * @return
	 * @throws Exception
	 */
	@Get("help")
	public String help(final Invocation inv)throws Exception{
		
		return "help";
	}
	
	
	/***
	 * 联系我们
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("contactus")
	public String contactus(final Invocation inv)throws Exception{
		
		return "contactus";
	}
}
