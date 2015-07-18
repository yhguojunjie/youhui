<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ConvertUtil" %>

	<div class="w1190 head" id="header">
		<div class="logo">
			<h1>
			 <% AgentInfoVO agentInfoHeader = (AgentInfoVO)request.getSession().getAttribute(Globals.SESSION_ANGENTINFO);
				%>
				<a href="${basePath}" title="${agentInfoConst.logoDesc }">
					<img alt="${agentInfoConst.logoDesc }" src="${agentInfoConst.logo }" />
				</a>
			</h1>
			<div class="slogan"></div>
		</div>
		<div class="nav">
			<ul>
				<li><a href="${basePath}" class="active"><em class="icon_home"></em>首页</a></li>
				<li><a href="${url_act }"><em class="icon_ativ"></em>活动圈</a></li>
				<li><a href="${url_pluginList }"><em class="icon_plugin"></em>模版库</a></li>
				<li><a href="${url_channel }"><em class="icon_channel"></em>发布渠道</a></li>
				<li><a href="${url_question }"><em class="icon_help"></em>帮助中心</a></li>
				<% if(request.getSession().getAttribute(Globals.SESSION_USER) == null){ %>
					<li><a href="${basePath}login">登录</a><strong>|</strong><a href="${basePath}register/pc">注册</a></li>
				<% }else{ 
					User userHeader = (User)request.getSession().getAttribute(Globals.SESSION_USER); 
					if(userHeader.getHeadimgUrl() != null){
						userHeader.setHeadimgUrl(ConvertUtil.procImgPath(userHeader.getHeadimgUrl()));
					}
				%>
				<li class="users_btn"><a href="${url_myact }"><img src="<%=userHeader.getHeadimgUrl() %>" /><%=userHeader.getNickName() %></a>（<a href="${basePath}logout">退出</a>）</li>
				<% } %>
			</ul>
		</div>
	</div>
<script type="text/javascript">
	var header_url = self.location + '';
	var basePath = '${basePath}';
	$(document).ready(function(){
		if(header_url.indexOf("/act") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(1).attr("class","active");
		}else if(header_url.indexOf("/plugin/pluginList") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(2).attr("class","active");
		}else if(header_url.indexOf("/channel/index") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(3).attr("class","active");
		}else if(header_url.indexOf("/other/question") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(4).attr("class","active");
		}else if(header_url.indexOf("/login") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(5).attr("class","active");
		}else if(header_url.indexOf("/register/pc") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(6).attr("class","active");
		}else{
			$('.nav a').attr("class","");
			$('.nav a').eq(0).attr("class","active");
		}
	});


</script>
