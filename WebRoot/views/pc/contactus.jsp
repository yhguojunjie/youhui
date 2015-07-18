<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"   pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>联系我们</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 help">
		<!-- 左侧内容 开始 -->
		<div class="fl w300 side">
			<div class="subnav">
				<ul>
					<li><a href="${basePath }pc/other/question" >常见问题</a></li>
					<li><a href="${basePath }pc/other/aboutus" >关于我们</a></li>
					<li><a href="${basePath }pc/other/contactus" class="active">联系我们</a></li>
				</ul>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w860 main">
			<div class="help_cont">
				<p>
					客服电话：${agentInfoConst.servicePhone }
				</p>
				<p>
					客服QQ：${agentInfoConst.serviceqq }
				</p>
				<p>
					粉丝QQ群：${agentInfoConst.qqGroup }
				</p>
				<p>
					邮箱：${agentInfoConst.serviceEmail }
				</p>
				<p>
					地址：${agentInfoConst.address }
				</p>		
			</div>
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp"%>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		$('.help .side .subnav').height($('.help').height());
	});
</script>
