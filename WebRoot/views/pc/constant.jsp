<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="p" uri="page"%>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.InputStream" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ReadProperties" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("path", path);
	request.setAttribute("basePath", basePath);
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "-1");
	//代理商信息
	AgentInfoVO agentInfoConst = (AgentInfoVO)request.getSession().getAttribute(Globals.SESSION_ANGENTINFO);
	request.setAttribute("agentInfoConst", agentInfoConst);
	//文件服务器地址
	String fileAccessPath = ReadProperties.getPara("fileAccessPath");
	request.setAttribute("fileAccessPath", fileAccessPath);
	//插件服务器地址--生成活动二维码的时用
	String cjShowServer = ReadProperties.getPara("cjPath")+"/";
	request.setAttribute("cjShowServer", cjShowServer);
	//插件服务器地址--用户点击生成访问活动地址的时使用
	String cjpcShowServer = ReadProperties.getPara("cjpcPath")+"/";;
	request.setAttribute("cjpcShowServer", cjpcShowServer);

	// 首页每页显示数量
	request.setAttribute("HOMEPAGE_SHOW_NUMBER", Globals.HOMEPAGE_SHOW_NUMBER);
	// 其他每页显示数量
	request.setAttribute("PAGE_SHOW_NUMBER", Globals.PAGE_SHOW_NUMBER);

	/*************************************菜单相关***********************************************/
	//二维码生成地址  例：/common/getQcode?url=qcodeurl
	request.setAttribute("url_getQcode", basePath + "common/getQcode?url=");
	//模板库
	request.setAttribute("url_pluginList", basePath + "pc/plugin/pluginList");
	//模板详情页 例： /pc/plugin/pluginDetail/{pluginId}
	request.setAttribute("url_pluginDetail", basePath + "pc/plugin/detail");
	//活动圈
	request.setAttribute("url_act", basePath + "pc/act");
	//经典活动
	request.setAttribute("url_classic", basePath + "pc/act/classic");
	//我的活动
	request.setAttribute("url_myact", basePath + "pc/my/actList");
	//渠道主页
	request.setAttribute("url_channel", basePath + "pc/channel/index");
	//常见问题
	request.setAttribute("url_question", basePath + "pc/other/question");
	//个主页  例：/pc/user/mypage
	request.setAttribute("url_myPage", basePath + "pc/user/mypage");
	//他人主页   例：  /pc/user/otherUserPage/{loginId}
	request.setAttribute("url_otherPage", basePath + "pc/user/otherUserPage");
	//开发者自己主页  例：   /pc/user/devUserPage/{loginId}
	request.setAttribute("url_devPage", basePath + "pc/user/devUserPage");
	//开发者主页  例：   /pc/user/devOtherPage/{loginId}
	request.setAttribute("url_devOtherPage", basePath + "pc/user/devOtherPage");
	//编辑个人资料  例：/pc/user/userEdit
	request.setAttribute("url_userEdit", basePath + "pc/user/userEdit");
	//我的订单
	request.setAttribute("url_orders", path + "/pc/my/orderList");
	//站外活动
	request.setAttribute("url_outPluginAct", basePath + "pc/pluginAct/outPluginAct");
	
%>
