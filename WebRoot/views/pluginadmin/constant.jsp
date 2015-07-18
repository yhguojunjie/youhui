<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.InputStream" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ReadProperties" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;


//配置页面的样式
String adminCssPath = path+"/pluginres/css";
request.setAttribute("adminCssPath", adminCssPath);

String adminImgPath = path+"/pluginres/images";
request.setAttribute("adminImgPath", adminImgPath);

String adminJsPath = path+"/pluginres/js";
request.setAttribute("adminJsPath", adminJsPath);

//公共样式
String commonCssPath = path+"/css";
request.setAttribute("commonCssPath", commonCssPath);

String commonImgPath = path+"/images";
request.setAttribute("commonImgPath", commonImgPath);

String commonJsPath = path+"/js";
request.setAttribute("commonJsPath", commonJsPath);

//图片资源
//String resUrl = "http://115.28.169.210:29099/";
String resUrl = ReadProperties.getPara("fileAccessPath");
request.setAttribute("resUrl", resUrl);
request.setAttribute("path",path);
request.setAttribute("basePath",basePath);

//模板库
request.setAttribute("url_pluginList", basePath + "/pc/plugin/pluginList");
//活动圈
request.setAttribute("url_act", basePath + "/pc/act");
//经典活动
request.setAttribute("url_classic", basePath + "/pc/act/classic");
//我的活动
request.setAttribute("url_myact", basePath + "/pc/my/actList");
//渠道主页
request.setAttribute("url_channel", basePath + "/pc/channel/index");
//帮助中心
request.setAttribute("url_question", basePath + "/pc/other/question");



%>
<script>
var commonImgPath = "${commonImgPath}";
var adminImgPath = "${adminImgPath}";
var path = "${path}";
var resUrl = "${resUrl}";
</script>
