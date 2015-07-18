<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>登录</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	
	<!-- 登录 开始 -->
	<div class="w1190 login">
		<div class="cont">
			<h2>登录超时，请重新登录。</h2>
			<div class="fl">
				<h3>推荐：<span>（同微信公众号数据同步）</span></h3>
				<div class="space_10"></div>
				<dl>
					<dt><a href="${basePath }login/wechat">
						<img src="${path }/images/wechat.png" />
						<span>微信登录</span>
					</a></dt>
				</dl>
			</div>
			<div class="fr">
				<h3>其他：</h3>
				<div class="space_10"></div>
				<dl>
					<dd><a href="${basePath }login/qq">
						<img src="${path }/images/tencent.png" />
						<span>QQ登录</span>
					</a></dd>
				</dl>
			<%-- 	<dl>
					<dd><a href="${path }/login">
						<img src="${path }/images/tencent.png" />
						<span>手动登录</span>
					</a></dd>
				</dl> --%>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 登录 结束 -->
	
	
	
	
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->

</body>
</html>



