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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/guessgame.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/guessgame.js"></script>
<script type="text/javascript" src="${commonJsPath}/common.js"></script>
<title>中奖名单</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 guessgame">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<c:choose>
					<c:when test="${userPluginId != null && userPluginId != 0}">
						<a href="${path}/pluginadmin/guessgame/add/${userPluginId}">活动设置</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/pluginadmin/guessgame/edit/${activityId}">活动设置</a>
					</c:otherwise>
				</c:choose>
				<a href="${path}/pluginadmin/guessgame/gameadmin/${activityId}?upid=${userPluginId}">比赛管理</a>
				<a href="${path}/pluginadmin/guessgame/betrecord/${activityId}?upid=${userPluginId}">投注记录</a>
				<a href="#" class="active">中奖名单</a>
			</div>
			<div class="cont mark">
				<h4 class="tr">
					<script type="text/javascript">
					 function exportRecord(){
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
						 window.location.href="${path}/pluginadmin/guessgame/exportRecord/${activityId}?start="+start+"&end="+end;
					 }
					</script>
					<i>请输入导出条数：从</i>
					<input type="text" placeholder="起始" id="start"/><i>到</i>
					<input type="text" placeholder="截止" id="end"/><i>条</i>
					<a onclick="javascript:exportRecord();">导出Excel</a>
				</h4>
				<form action="" id="recordListForm" name="recordListForm" method="get">
					<input type="hidden" name="currentPage"/>
					<input type="hidden" name="count"/>
				<table>
					<tr>
						<th>序号</th>
						<th>比赛</th>
						<th>投注</th>
						<th>用户信息</th>
						<th>投注时间</th>
						<th>兑换时间</th>
						<th>操作</th>
					</tr>
					<c:forEach var="list" items="${recordVos.pageResult}" varStatus="s">
						<tr class="trc">
							<td>${recordVos.totalCount-(s.count + count*(currentPage-1))+1}</td>
							<td>${list.gameName}</td>
							<td>${list.result}</td>
							<td><c:if test="${list.username != null && !empty(list.username)}">${list.username}<br/></c:if>
							${list.tel}<br/>
							<c:if test="${list.mailAddress != null && !empty(list.mailAddress)}">${list.mailAddress}<br/></c:if>
							<c:if test="${list.qq != null && !empty(list.qq)}">${list.qq}<br/></c:if>
							<c:if test="${list.wechatId != null && !empty(list.wechatId)}">${list.wechatId}<br/></c:if>
							<c:if test="${list.otherInfo != null && !empty(list.otherInfo)}">${list.otherInfo}<br/></c:if>
							</td>
							<td>
								<p><fmt:formatDate value="${list.betTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
							</td>
							<td>
								<p><fmt:formatDate value="${list.exchangeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
							</td>
							<td>
							<!-- btn_green btn_gray -->
								<c:choose>
									<c:when test="${list.opState == null || list.opState == '0'}">
										<p><a class="btn_green">未兑换</a></p>
									</c:when>
									<c:otherwise>
										<p><a class="btn_gray">已兑换</a></p>
									</c:otherwise>
								</c:choose>
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

