<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="page"%>
<%@ include file="../constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css" />
<link type="text/css" rel="stylesheet" href="${adminCssPath}/runman.css?v=1">
<script type="text/javascript" src="${commonJsPath}/zeroclipboard/ZeroClipboard.js"></script>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/runman.js"></script>
<script type="text/javascript" src="${commonJsPath}/common.js"></script>
<title>排行榜</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 runman">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<c:choose>
					<c:when test="${userPluginId != null && userPluginId != 0}">
						<a href="${path}/pluginadmin/runman/add/${userPluginId}">活动设置</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/pluginadmin/runman/edit/${activityId}">活动设置</a>
					</c:otherwise>
				</c:choose>
				<a href="${path}/pluginadmin/runman/record/${activityId}">中奖名单</a>
				<a class="active" href="#">排行榜</a>
			</div>
			<div class="cont mark">
				<h4 class="tr">
					<button id="copyid" class="btn_copy" value="${basePath}/cj/runman/rank/${activityId}" onclick="init('copyid')">双击复制兑换领奖手机页面地址</button>
					<script type="text/javascript">
					 function exportRank(){
						 var start = null;
						 var end = null;
						 if($('#start').val().length != 0 || $('#end').val().length != 0){
							 start = parseInt($('#start').val());
							 end = parseInt($('#end').val());
							 if( isNaN( start )  || isNaN( end )){
								 alert("请输入数字");
								 return;
							 }
							 if(start > end){
								 alert("起始不能大于截止位置");
								 return;
							 }
						 }
						 window.location.href="${path}/pluginadmin/runman/exportRank/${activityId}?start="+start+"&end="+end;
					 }
					</script>
					<i>请输入导出条数：从</i>
					<input type="text" placeholder="起始" id="start"/><i>到</i>
					<input type="text" placeholder="截止" id="end"/><i>条</i>
					<a onclick="javascript:exportRank();">导出Excel</a>
				</h4>
				<form action="" id="recordListForm" name="recordListForm" method="get">
					<input type="hidden" name="currentPage"/>
					<input type="hidden" name="count"/>
				<table class="rank">
					<tr>
						<th>排名</th>
						<th>最高分</th>
						<th>用户信息</th>
						<th>操作</th>
					</tr>
					<c:forEach var="list" items="${recordVos.pageResult}" varStatus="s">
						<tr>
							<td>${(s.count + count*(currentPage-1))}</td>
							<td><p>${list.bestScore}</p></td>
							<td><c:if test="${list.username != null && !empty(list.username)}">${list.username}<br/></c:if>
							${list.tel}<br/>
							<c:if test="${list.mailAddress != null && !empty(list.mailAddress)}">${list.mailAddress}<br/></c:if>
							<c:if test="${list.qq != null && !empty(list.qq)}">${list.qq}<br/></c:if>
							<c:if test="${list.wechatId != null && !empty(list.wechatId)}">${list.wechatId}<br/></c:if>
							<c:if test="${list.otherInfo != null && !empty(list.otherInfo)}">${list.otherInfo}<br/></c:if>
							</td>
							<td>
								<a class="btn_modify">修改最高分</a>
							</td>
							<input type="hidden" value="${list.id}"/>
						</tr>
					</c:forEach>
				</table>
				</form>
				<input type="hidden" id="activityId" value="${activityId}">
			</div>
			<!-- 分页 开始 -->
			<div class="tr pages">
				<p:page page="recordVos" formname="recordListForm" />
			</div>
			<!-- 分页 结束 -->
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<%@ include file="../../pc/contactSupport.jsp" %>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="../../pc/footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	function init(str){
		var clip = new ZeroClipboard.Client();
		clip.setHandCursor(true);
		clip.setText($('#'+str).val());
		clip.addEventListener("mouseUp");
		clip.glue(str);
		return false;
	}
</script>

