package com.yoxi.hudongtui.utils.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.InvocationUtils;

/**
 * 
 * WEB工具类
 * 
 */
public class WebApplicationUtils {

	/**
	 * 
	 * 获取当前Invocation
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public static Invocation getInv() {
		Invocation inv = InvocationUtils.getCurrentThreadInvocation();
		return inv;
	}

	/**
	 * 
	 * 获取session
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public static HttpSession getSession() {
		Invocation inv = getInv();
		return inv.getRequest().getSession();
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public static String getContextPath() {
		// Invocation inv = getInv();
		// String contextPath =
		// inv.getServletContext().getContextPath().substring(1);
		String contextPath = getInv().getRequest().getContextPath();
		return contextPath;
	}

	/**
	 * 
	 * @return
	 */
	public static String getBasePath() {
		HttpServletRequest request = getInv().getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName();
		int port = request.getServerPort();
		if (port != 80) {
			basePath += ":" + request.getServerPort();
		}
		if (!"".equals(path) && path != null) {
			basePath += path;
		}
		return basePath;
	}

	/**
	 * 转向信息提示页面.
	 * 
	 * @param string
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */

	/**
	 * 
	 * @param msg
	 *            提示信息
	 * @param urlTile
	 *            跳转URL提示文字
	 * @param url
	 *            跳转URL
	 * @param autoRedirect
	 *            是否自己跳转
	 */
	public static void redirectMsg(final String msg, final String urlTile,
			final String url, final boolean autoRedirect) {
		HttpSession session = getSession();
		session.setAttribute("redirect_msg", msg);
		session.setAttribute("redirect_urlTile", urlTile);
		session.setAttribute("redirect_url", url);
		// session.setAttribute("redirect_success",success);
		session.setAttribute("redirect_autoRedirect", autoRedirect);
		String basePath = getBasePath();
		try {
			getInv().getResponse().sendRedirect(
					basePath + "/views/pc/tippage.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if(isAjaxRequest()){
		// String _msg = "{success:"+success+",msg:''}";
		// render("application/json", formatJson(_msg,305,""));
		// }else{
		// if(isPostMothed()){
		// String _msg = "<html><body><textarea>" + formatJson(msg,305,"") +
		// "</textarea></body></html>";
		// renderHtml(_msg);
		// }else{
		// try {
		// String basePath = getBasePath();
		// getResponse().sendRedirect(basePath + "/message.xhtml");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}

	/**
	 * 转向信息提示页面.
	 * 
	 * @param string
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */

	/**
	 * 
	 * @param msg
	 *            提示信息
	 * @param urlTile
	 *            跳转URL提示文字
	 * @param url
	 *            跳转URL
	 * @param autoRedirect
	 *            是否自己跳转
	 */
	public static void sendRedirectMsg(final String msg, final String urlTile,
			final String url, final boolean autoRedirect) {
		HttpSession session = getSession();
		session.setAttribute("redirect_msg", msg);
		session.setAttribute("redirect_urlTile", urlTile);
		session.setAttribute("redirect_url", url);
		// session.setAttribute("redirect_success",success);
		session.setAttribute("redirect_autoRedirect", autoRedirect);
		String basePath = getBasePath();
		try {
			getInv().getResponse().sendRedirect(
					basePath + "/views/pc/tippage.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if(isAjaxRequest()){
		// String _msg = "{success:"+success+",msg:''}";
		// render("application/json", formatJson(_msg,305,""));
		// }else{
		// if(isPostMothed()){
		// String _msg = "<html><body><textarea>" + formatJson(msg,305,"") +
		// "</textarea></body></html>";
		// renderHtml(_msg);
		// }else{
		// try {
		// String basePath = getBasePath();
		// getResponse().sendRedirect(basePath + "/message.xhtml");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}

	/**
	 * 
	 * @param msg
	 *            提示信息
	 * @param urlTile
	 *            跳转URL提示文字
	 * @param url
	 *            跳转URL
	 * @param autoRedirect
	 *            是否自己跳转
	 */
	public static void channelMsg(String msg, String urlTile, String url,
			boolean autoRedirect) {
		HttpSession session = getSession();
		session.setAttribute("redirect_msg", msg);
		session.setAttribute("redirect_urlTile", urlTile);
		session.setAttribute("redirect_url", url);
		// session.setAttribute("redirect_success",success);
		session.setAttribute("redirect_autoRedirect", autoRedirect);
		String basePath = getBasePath();
		try {
			getInv().getResponse().sendRedirect(
					basePath + "/views/pc/tippage.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if(isAjaxRequest()){
		// String _msg = "{success:"+success+",msg:''}";
		// render("application/json", formatJson(_msg,305,""));
		// }else{
		// if(isPostMothed()){
		// String _msg = "<html><body><textarea>" + formatJson(msg,305,"") +
		// "</textarea></body></html>";
		// renderHtml(_msg);
		// }else{
		// try {
		// String basePath = getBasePath();
		// getResponse().sendRedirect(basePath + "/message.xhtml");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}

	/**
	 * 
	 * @param msg
	 *            提示信息
	 * @param urlTile
	 *            跳转URL提示文字
	 * @param url
	 *            跳转URL
	 * @param autoRedirect
	 *            是否自己跳转
	 */
	public static void findPwdIndex(String msg, String urlTile, String url,
			boolean autoRedirect) {
		HttpSession session = getSession();
		session.setAttribute("redirect_msg", msg);
		session.setAttribute("redirect_urlTile", urlTile);
		session.setAttribute("redirect_url", url);
		// session.setAttribute("redirect_success",success);
		session.setAttribute("redirect_autoRedirect", autoRedirect);
		String basePath = getBasePath();
		try {
			getInv().getResponse().sendRedirect(
					basePath + "/views/pc/findPwd.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if(isAjaxRequest()){
		// String _msg = "{success:"+success+",msg:''}";
		// render("application/json", formatJson(_msg,305,""));
		// }else{
		// if(isPostMothed()){
		// String _msg = "<html><body><textarea>" + formatJson(msg,305,"") +
		// "</textarea></body></html>";
		// renderHtml(_msg);
		// }else{
		// try {
		// String basePath = getBasePath();
		// getResponse().sendRedirect(basePath + "/message.xhtml");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}

	/**
	 * 判断浏览器是否为微信浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isBrowserMicrMes(HttpServletRequest request) {

		boolean flag = false;
		try {
			if (request.getHeader("USER-AGENT").indexOf("MicroMessenger") > 0) {
				flag = true;
			}
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	/**
	 * 判断是否是手机浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isBrowserMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "ipad", "android", "phone",
				"mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series",
				"webos", "sony", "blackberry", "dopod", "nokia", "samsung",
				"palmsource", "xda", "pieplus", "meizu", "midp", "cldc",
				"motorola", "foma", "docomo", "up.browser", "up.link",
				"blazer", "helio", "hosin", "huawei", "novarra", "coolpad",
				"webos", "techfaith", "palmsource", "alcatel", "amoi",
				"ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom",
				"bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird",
				"zte-", "longcos", "pantech", "gionee", "portalmmm",
				"jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
				"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi",
				"audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell",
				"cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq",
				"java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g",
				"lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-",
				"moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana",
				"pant", "phil", "play", "port", "prox", "qwap", "sage", "sams",
				"sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-",
				"siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli",
				"tim-", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa",
				"wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase()
						.indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}

	/**
	 * 获取地址栏Url，带参数的
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {

		String url = request.getScheme() + "://";

		url += request.getHeader("host");

		url += request.getRequestURI();

		if (request.getQueryString() != null) {
			url += "?" + request.getQueryString();
		}
		return url;
	}

	public static void main(String[] args) {
		String[] mobileAgents = { "iphone", "ipad", "android", "phone",
				"mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series",
				"webos", "sony", "blackberry", "dopod", "nokia", "samsung",
				"palmsource", "xda", "pieplus", "meizu", "midp", "cldc",
				"motorola", "foma", "docomo", "up.browser", "up.link",
				"blazer", "helio", "hosin", "huawei", "novarra", "coolpad",
				"webos", "techfaith", "palmsource", "alcatel", "amoi",
				"ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom",
				"bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird",
				"zte-", "longcos", "pantech", "gionee", "portalmmm",
				"jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
				"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi",
				"audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell",
				"cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq",
				"java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g",
				"lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-",
				"moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana",
				"pant", "phil", "play", "port", "prox", "qwap", "sage", "sams",
				"sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-",
				"siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli",
				"tim-", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa",
				"wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };

		String url = "Mozilla/5.0 (iPad; CPU OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D167 Safari/9537.53";

		for (String mobileAgent : mobileAgents) {
			if (url.toLowerCase().indexOf(mobileAgent) >= 0) {
				break;
			}
		}

	}

}
