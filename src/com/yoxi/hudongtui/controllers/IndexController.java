package com.yoxi.hudongtui.controllers;


import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * 
 * 首页跳转
 * 
 * @author wql
 * 
 * 2014-12-05
 *
 */
@GlobalRequired
@Path("")
public class IndexController {
	
	/**
	 * 各版本主页跳转处理
	 * @param inv
	 * @return
	 */
	@Get("")
	public void toIndex(Invocation inv)throws Exception{
		inv.getRequest().getRequestDispatcher("/pc/plugin/list").forward(inv.getRequest(), inv.getResponse());
	}

	
}
