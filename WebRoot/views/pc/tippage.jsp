<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<%
response.setHeader( "Pragma", "no-cache" ); 
response.addHeader( "Cache-Control", "must-revalidate" ); 
response.addHeader( "Cache-Control", "no-cache" ); 
response.addHeader( "Cache-Control", "no-store" ); 
response.setDateHeader("Expires", 0); 
%>
<%
request.setAttribute("frontTitle","信息提示");
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="resource.jsp"%>
	<title>提交申请成功！</title>	
</head>
<body class="reg">
	<!-- 头部 开始 -->
	 <%@ include file="header.jsp" %> 
	<!-- 头部 结束 -->
	
	<!-- 提醒 开始 -->
    <div class="regSuccess">
		<div class="doneskip">
			<h2>
				<img src="${path }/images/pc/registerdone.jpg" />
				${redirect_msg}
			</h2>
			<c:if test="${redirect_autoRedirect}">
				<p><em>10</em>秒后自动跳转，如果你的浏览器无法跳转，<a href="${redirect_url}">请点击此处跳转</a>。</p>
			</c:if>
			
		</div>
	 </div>
	<!-- 提醒 结束 -->
	
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>

<c:if test="${redirect_autoRedirect}">
<script type="text/javascript">
//自动跳转
	$(function(){
		var count = 10;
		var countdown = setInterval(function(){
			$('.doneskip p em').text(count);
			count--;
			if(count == 0){
				location.href="${redirect_url}";
				clearInterval(countdown);
			}
		},1000);
	}); 
</script>
</c:if>
</html>
