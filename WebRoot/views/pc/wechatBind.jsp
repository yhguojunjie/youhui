<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
	<title>微信登录</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 login">
		<div class="bind_wechat">
			<h2>绑定微信账号</h2>
			<p>
				用微信扫一扫下面二维码<br />
				完成登录<br />
				可实时查看活动数据
			</p>
			<div id="wechatCode" ></div>
			<a href="${url_myact }" class="btn_bind">先跳过</a>
			<div class="clear"></div>
		</div>
	</div>
	<script type="text/javascript">
				var appId = '${appId}';
				var redircURI = '${redirect_uri}';
				var obj = new WxLogin({
		            id:"wechatCode", //第三方页面显示二维码的容器id
		            appid: appId, //应用唯一标识，在微信开放平台提交应用审核通过后获得
		            scope: "snsapi_login", //应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
		            redirect_uri: redircURI, //重定向地址，需要进行UrlEncode
		            state: "", //用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
		            style: "black", //提供"black"、"white"可选，默认为黑色文字描述。详见文档底部FAQ
		            href: "" //自定义样式链接，第三方可根据实际需求覆盖默认样式。详见文档底部FAQ
		          });
	</script>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>


