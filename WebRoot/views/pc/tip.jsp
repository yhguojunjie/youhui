<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>提示</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	
	<!-- 提醒 开始 -->
	<div class="w1190 cont">
		<div class="notice_page">
			<h3>${tip }</h3>
			<c:choose>
				<c:when test="${backUrl !=null && !empty(backUrl)}">
					<p class="indent_5em">猛戳这里，<a href="${backUrl }">原路返回</a>！</p>
				</c:when>
				<c:otherwise>
					<p class="indent_5em">猛戳这里，<a href="${basePath }">原路返回</a>！</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 提醒 结束 -->
	
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	
</body>
</html>



