<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css?v=2" />
<script type="text/javascript" src="${commonJsPath}/jquery.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js?v=2"></script>
<title>温馨提示</title>
</head>
<body>
	<!-- 去微信查看 开始 -->
	<div class="seein_wechat">
		<p>请使用微信扫一扫参加活动</p>
		<div class="wechat_code"><img src="${path}/common/getQcode?url=${url}" /></div>
	</div>
	<!-- 去微信查看 结束 -->
</body>
</html>
