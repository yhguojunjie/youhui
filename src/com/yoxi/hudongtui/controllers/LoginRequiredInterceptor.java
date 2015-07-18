package com.yoxi.hudongtui.controllers;

import java.lang.annotation.Annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

/**
 * 登录拦截器
 * 
 * @author wql
 * 
 * 2014-12-04
 *
 */
public class LoginRequiredInterceptor extends ControllerInterceptorAdapter {

	
	public LoginRequiredInterceptor() {
	    // 设置优先级，优先级越高的拦截器，before方法越先执行
	    this.setPriority(1000);
	}
	
    // 覆盖这个方法，表示只有标注@LoginRequired的控制器或方法才会被此拦截器拦截
    @Override
    public Class<? extends Annotation> getRequiredAnnotationClass() {
        return LoginRequired.class;
    }

    @Override
    public Object before(Invocation inv) throws Exception {
        HttpServletRequest request = inv.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(Globals.SESSION_USER) == null) {
            // 如果没有登录，重定向到登录页面
        	return "r:"+WebApplicationUtils.getBasePath()+"/login";
        }
        	return true;
        
    }
}
