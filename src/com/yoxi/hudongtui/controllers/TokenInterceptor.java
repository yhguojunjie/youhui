package com.yoxi.hudongtui.controllers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

/**
 * <p>
 * 防止重复提交过滤器
 * </p>
 * 
 * @author gjj
 * @date: 2015-4-8
 * 
 */
public class TokenInterceptor extends ControllerInterceptorAdapter {
	@Override
	public Class<? extends Annotation> getRequiredAnnotationClass() {
		return Token.class;
	}

	@Override
	public Object before(Invocation inv) throws Exception {
		HttpServletRequest request = inv.getRequest();
		HttpServletResponse response = inv.getResponse();

		Method method = inv.getMethod();
		Token annotation = method.getAnnotation(Token.class);
		if (annotation != null) {
			boolean needSaveSession = annotation.needSaveToken();
			if (needSaveSession) {
				request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
			}
			boolean needRemoveSession = annotation.needRemoveToken();
			if (needRemoveSession) {
				if (isRepeatSubmit(request)) {
					return false;
				}
				request.getSession(false).removeAttribute("token");
			}
		}
		return true;

	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}
}