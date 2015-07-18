package com.yoxi.hudongtui.utils.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.vo.agent.AgentInfoVO;

/**
 * web 登录session
 * 
 * @author wql
 * 
 * 2014-10-14
 *
 */
public class SessionUtil {

	public static final Map<String, HttpSession> LoginSessionMap = new HashMap<String, HttpSession>();
	
	
	/**
	 * 设置用户session
	 * @param invocation
	 * @param user
	 */
	public static void setSessionUserAttr(HttpServletRequest request,User user){
		HttpSession session = request.getSession(true);// 不存在就创建一个session
		if(user == null){
			session.removeAttribute(Globals.SESSION_USER);
		}else{
			session.setAttribute(Globals.SESSION_USER, user);
			session.setMaxInactiveInterval(Globals.SESSION_INTERVAL);
		}
	}
	
	/**
	 * 获取session中的用户信息
	 * @param inv
	 * @return
	 */
	public static User getUser(HttpServletRequest request){
		Object user = request.getSession().getAttribute(Globals.SESSION_USER);
		return user==null ? null : (User)user;
	}

	
	/**
	 * 保留用户session到Map
	 */
	public static void addLoginSession(Invocation inv){
		if(!LoginSessionMap.containsKey(inv.getRequest().getSession().getId())){
			LoginSessionMap.put(inv.getRequest().getSession().getId(),inv.getRequest().getSession());
		}
	}
	
	
	/**
	 * 添加代理商信息 session
	 * @param request
	 * @param AgentId
	 */
	public static void setAgentInfo(HttpServletRequest request,AgentInfoVO agentInfoVO){
		
		HttpSession session = request.getSession(true);// 不存在就创建一个session
		if(agentInfoVO == null){
			session.removeAttribute(Globals.SESSION_ANGENTINFO);
		}else{
			session.setAttribute(Globals.SESSION_ANGENTINFO, agentInfoVO);
			session.setMaxInactiveInterval(Globals.SESSION_ANGENTINFO_INTERVAL);
		}
	}
	
	
	/**
	 * 获取session中的代理商信息
	 * @param inv
	 * @return
	 */
	public static AgentInfoVO getAgentInfo(HttpServletRequest request){
		Object agentInfo = request.getSession().getAttribute(Globals.SESSION_ANGENTINFO);
		return agentInfo==null ? null : (AgentInfoVO)agentInfo;
	}
	
	/**
	 * 添加最近访问域名SESSION
	 * @param request
	 * @param AgentId
	 */
	public static void setLastDomain(HttpServletRequest request,String domain){
		
		HttpSession session = request.getSession(true);// 不存在就创建一个session
		if(StringUtils.isNullBlank(domain)){
			session.removeAttribute(Globals.SESSION_LASTDOMAIN);
		}else{
			session.setAttribute(Globals.SESSION_LASTDOMAIN, domain);
			session.setMaxInactiveInterval(Globals.SESSION_LASTDOMAIN_INTERVAL);
		}
	}
	
	/**
	 * 获取session中的最近访问域名
	 * @param request
	 * @return
	 */
	public static String getLastDomain(HttpServletRequest request){
		Object lastDomain = request.getSession().getAttribute(Globals.SESSION_LASTDOMAIN);
		return lastDomain==null ? null : (String)lastDomain;
	}
	
	/**
	 * 移除session信息
	 * @param inv
	 */
	public static void destroy(HttpServletRequest request,String sessionId){
		request.getSession().removeAttribute(sessionId);
		
	}
}
