package com.yoxi.hudongtui.controllers;

import java.lang.annotation.Annotation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.Base64;
import com.yoxi.hudongtui.utils.common.CookieUtil;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;

/**
 * 全局拦截器
 * 
 * @author wql
 *
 */
public class GlobalRequiredInterceptor extends ControllerInterceptorAdapter {
	
	private Log log = LogFactory.getLog(getClass());
	
	public GlobalRequiredInterceptor() {
	    // 设置优先级，优先级越高的拦截器，before方法越先执行
	    this.setPriority(1200);
	}
	
	@Override
    public Class<? extends Annotation> getRequiredAnnotationClass() {
        return GlobalRequired.class;
    }
    @Override
    public Object before(Invocation inv) throws Exception {
    	
    	HttpServletRequest request = inv.getRequest();
    	
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		/*****************代理商归属处理*******************/
	/*	if(SessionUtil.getAgentInfo(request) == null){
			IAgentBusService agentBusService = (IAgentBusService) webApplicationContext.getBean("agentBusService");
			Integer agentId = agentBusService.getAgentIdForUser(request);
//			AgentInfoVO agentInfoVO = agentBusService.getMcacheAgentInfo(agentId);
			AgentInfoVO agentInfoVO = agentBusService.getAgentInfoVO(agentId);
			SessionUtil.setAgentInfo(request, agentInfoVO);
		}*/
		
		//登录信息处理
/*		if(SessionUtil.getUser(request) != null){
			IAgentBusService agentBusService = (IAgentBusService) webApplicationContext.getBean("agentBusService");
			AgentInfoVO agentInfoVO = agentBusService.getAgentInfoVO(SessionUtil.getUser(request).getUserId());
			if(agentInfoVO != null){
				String myDomain = agentInfoVO.getMydomain();
				if(myDomain != null){
					if(!nowNomain.equals(myDomain)){
						SessionUtil.destroy(request,Globals.SESSION_USER);
					}
				}
			}

		}*/
		
		/*************下次自动登录处理************/
//		HttpSession session = request.getSession(true);
//		User user = SessionUtil.getUser(request);
//		String url = request.getRequestURI().toString();
//		// 判断用户是否登录，一般是都没登录的
//		if (user == null) {
//			Cookie[] cookies = request.getCookies();// 取cookie值，这里还有其他网站的
//			if (cookies != null) {
//				String cookieValue = null;
//				// 下面是找到本项目的cookie
//				for (int i = 0; i < cookies.length; i++) {
//					if (Globals.COOKIENAME.equals(cookies[i].getName())) {
//						cookieValue = cookies[i].getValue();
//						break;
//					}
//				}
//				// 如果cookieValue为空 说明用户上次没有选择“记住下次登录”执行其他
//				if (cookieValue == null) {
//					if (url.contains("login")) {
//						response.sendRedirect(request.getContextPath()
//								+ "/login");
//						return false;
//					}
//				} else {
//					// 先得到的CookieValue进行Base64解码
//					String cookieValueAfterDecode = new String(
//							Base64.decode(cookieValue), "utf-8");
//					// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
//					String cookieValues[] = cookieValueAfterDecode.split(":");
//					if (cookieValues.length != 3) {
//						response.sendRedirect(request.getContextPath()
//								+ "/login");
//						return false;
//					}
//					// 判断是否在有效期内,过期就删除Cookie
//					long validTimeInCookie = new Long(cookieValues[1]);
//					if (validTimeInCookie < System.currentTimeMillis()) {
//						// 删除Cookie
//						CookieUtil.clearCookie(response);
//						response.sendRedirect(request.getContextPath()
//								+ "/login");
//						return false;
//					}
//					// 取出cookie中的用户名,并到数据库中检查这个用户名,
//					String account = cookieValues[0];
//					String getStr = " account LIKE '" + account + "' ";
//					User temp;
//					try {
//						IUserService userService = (IUserService) webApplicationContext
//								.getBean("userService");
//
//						temp = userService.getByStr(getStr);
//						// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+
//						// webSiteKey进行MD5加密。与前面设置的进行比较，看是否是同一个用户
//						if (temp != null) {
//							String md5ValueInCookie = cookieValues[2];
//							String md5ValueFromUser = CookieUtil.getMD5(temp
//									.getAccount()
//									+ ":"
//									+ temp.getPassword()
//									+ ":"
//									+ validTimeInCookie
//									+ ":"
//									+ Globals.COOKIENAME);
//							// 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
//							if (md5ValueFromUser.equals(md5ValueInCookie)) {
//								session.setAttribute(Globals.SESSION_USER, temp);
//								response.sendRedirect(WebApplicationUtils.getBasePath());
//								return false;
//							}
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//			}
//		} else {
//			if (url.contains("login")) {
//				System.out.println("--" + request.getContextPath());
//				response.sendRedirect(request.getContextPath() + "/");
//				return false;
//			}
//		}
    	
        return true;
    }
}
