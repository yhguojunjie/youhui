<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.InputStream" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ReadProperties" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
	request.setAttribute("path",path);
	request.setAttribute("basePath",basePath);
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "-1"); 
	
	//文件服务器地址
//	String fileAccessPath = "http://121.40.19.134:29099/";
	String fileAccessPath = ReadProperties.getPara("fileAccessPath");
	request.setAttribute("fileAccessPath", fileAccessPath);
	//插件服务器地址--生成活动二维码的时用
//	String cjShowServer = "http://test.tchajian.com/";
	String cjShowServer = ReadProperties.getPara("cjPath")+"/";
	request.setAttribute("cjShowServer", cjShowServer);
	//插件服务器地址--用户点击生成访问活动地址的时使用
//	String cjpcShowServer = "http://test.tchajian.com/";
	String cjpcShowServer = ReadProperties.getPara("cjpcPath")+"/";;
	request.setAttribute("cjpcShowServer", cjpcShowServer);
	
	/**菜单相关**/
	request.setAttribute("wxurl_pluginDetail",basePath+"wx/plugin/redircToDetail/1");
	//个主页  例：/wx/user/mypage
	request.setAttribute("url_mypage",path+"/wx/user/redircMypageUrl/1");
	//他的主页   例：  /wx/user/otherUserPage/{loginId}
	request.setAttribute("url_otherUserPage",path+"/wx/user/redircOtherPage/1");	
	//开发者主页  例：   /wx/user/devUserPage/{loginId}
	request.setAttribute("url_devUserPage",path+"/wx/user/redircDevUserPage/1");
	//开发者ta主页  例：   /wx/user/devUserPage/{loginId}
 	 request.setAttribute("url_devOtherPage",path+"/wx/user/redircDevOtherPageUser/1");
	//二维码生成地址  例：/common/getQcode?url=qcodeurl
	request.setAttribute("url_getQcode",path+"/common/getQcode?url=");
	//编辑个人资料  例：/wx/user/userEdit
	request.setAttribute("url_userEdit",path+"/wx/user/redircToEditUrl/1");
	//我的插件
	request.setAttribute("url_userPlugin",path+"/wx/userPlugin/redircWxUserPluginUrl");
	//我的活动
	request.setAttribute("url_pluginAct",path+"/wx/pluginAct/redircWxPluginActUrl");
	//活动中心
	request.setAttribute("url_pluginActCenter",basePath+"wx/pluginAct/pluginActCenterList");
	//我的交易记录
	request.setAttribute("url_userTradeLog",path+"/wx/userTradeLog/redircWxTradeLogUrl");
	
%>	