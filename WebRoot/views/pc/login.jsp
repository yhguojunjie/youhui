<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<title>登录</title>
</head>
<% String flag = (String)session.getAttribute("flag")==null?"":(String)session.getAttribute("flag");
String name = "";
String password = "";
try{
Cookie[] cookies=request.getCookies();
if(cookies!=null){
for(int i=0;i<cookies.length;i++){
if(cookies[i].getName().equals("cookie_user")){
String value =  cookies[i].getValue();
if(value!=null&&!"".equals(value)){
name=cookies[i].getValue().split("-")[0];
if(cookies[i].getValue().split("-")[1]!=null && !cookies[i].getValue().split("-")[1].equals("null")){
password=cookies[i].getValue().split("-")[1];
}
}
}
request.setAttribute("name",name);
request.setAttribute("password",password);
}
}
}catch(Exception e){
e.printStackTrace();
}
%>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
		<!-- 登录 开始 -->
	<div class="w1190 login">
		<div class="cont">
			<h2>
				<span>登录<em>登录后即可免费试用所有模板，新增配置活动，体验全部付费功能</em></span>
				<!-- <strong>登录超时，请重新登录</strong> -->
			</h2>
			<form class="fl" action="${path }/login/doLogin" method="post" onsubmit="return login()" >
				<div class="row">
					<span>帐号</span>
					<input class="username" name="account" type="text" value="<%=name %>" placeholder="请输入邮箱" />
				</div>
				<div class="row">
					<span>密码</span>
					<input class="password" name="password" type="password" value="<%=password %>"/>
					<div>
						<label>
					
						<!-- 
							<input type="checkbox" name="autoLogin" />下次自动登录
						 -->
						 <input type="checkbox" name="autoLogin" id="flag" value="1" <%if(flag!=null && flag.equals("1")){%> checked ; value ="1"; <%}else {%> value="0" <%;}%> />记住密码
						</label>
						<a href="${path}/login/toFindPwd" class="forget">忘记密码</a>
					</div>
				</div>
				<%String message=(String)request.getAttribute("message");
				if(message==null){
					message="";
				}
				%>
				<div class="row " style="color:red;" >
					<%=message %>
				</div>
				   <input type="hidden" name="token" value="${token}"> 
				<div class="row ">
					<input type="submit" value="提 交" />
				</div>
			</form>
			<dl class="fr">
				<dd>
					还没有帐号？<a href="${basePath }register/pc">立即注册</a>
				</dd>
<%-- 				<dd>
					其他登录方式：
				</dd>
				<dt><a href="#">
					<img src="${path}/images/wechat.png" />
					<span>微信登录</span>
				</a></dt> --%>
			</dl>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 登录 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	var flag=true;
	function login(){
		if(!$('.username').val() || !$('.password').val()){
			alert("帐号密码不能为空！");
			return false;
		}else{
			//防止表单重复提交
			if (flag==false){
				return false;
				window.location.reload();//刷新当前页面.
				}
				flag=false;
				return true;
				window.location.reload();//刷新当前页面.
		}
	}

	//回到顶部
	$(function(){
		$(".gotop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		$(window).scroll(function(){
			if($(window).scrollTop() > 10){
				$('.gotop').attr("style","display:block;");
			}else{
				$('.gotop').attr("style","display:none;");
			}
		});
	});
</script>


