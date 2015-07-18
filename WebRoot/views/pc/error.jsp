<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>错误页面</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	
	<div class="w1190 cont">
		<div class="error">
			<h3>不小心就出错啦。</h3>
			<p class="indent_5em">去<a href="${basePath }">首页</a>看看吧！</p>
		</div>
	</div>
	
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	
		<!-- 登录弹出对话框 开始 -->
	<div class="login_box">
		<div class="main">
			<h3>
				<strong>登录</strong>
				<span class="close"><!-- 关闭登录窗口按钮 -->╳</span>
			</h3>
			<h4>
				<strong>推荐</strong>
				<em>（同微信公众号数据同步）</em>
			</h4>
			<a href="${basePath }login/wechat" class="btn">
				<img src="${path }/images/wechat.png" />
				<span>微信登录</span>
			</a>
			<h5>其他</h5>
			<ul>
				<li><a href="${basePath }login/qq">
					<img src="${path }/images/tencent.png" />
					<span>QQ登录</span>
				</a></li>
				<li><a href="${path }/login">
					<img src="${path }images/tencent.png" />
					<span>其它登录</span>
				</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 登录弹出对话框 结束 -->
</body>
</html>



