<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <%@ include file="resource.jsp"%>
    <link rel="stylesheet" href="${path}/css/common.css"/>
    <link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/css/style.css"/>
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
    <div class="login-head">
        <div class="logo">
            <a href="${basePath}">
                <img src="${path}/img/logo2.png" alt="帮you惠">
            </a>
        </div>
        <h3>欢迎登录</h3>
    </div>
    <div class="login-wrap">
        <div class="login-form">
            <div class="mt">
                <h1>帮you惠会员</h1>
                <a href="${basePath}register/pc" target="_blank"><b></b>免费注册</a>
            </div>
            <div class="mc">
                <form  id="loginForm" method="post" name="form1" action="${basePath}login/doLogin">
                    <div class="form">
                        <div class="item item-fore1">
                            <label for="loginname" class="login-label name-label"></label>
                            <input type="text" class="itxt" name="email" value="<%=name %>" id="email" placeholder="邮箱/用户名">
                        </div>
                        <div class="item item-fore2">
                            <label class="login-label pwd-label" for="nloginpwd"></label>
                            <input type="password" class="itxt" name="password" id="password" value="<%=password %>" tabindex="2" autocomplete="off" placeholder="密码">
                        </div>
                        <div class="item item-fore3">
                            <div class="safe">
                                <span>
                                 <!--    <input id="" name="" type="checkbox" class="" > -->
                                   <!--  <label>下次自动登录</label> -->
                                    <label>
					
						<!-- 
							<input type="checkbox" name="autoLogin" />下次自动登录
						 -->
						 <input type="checkbox" name="autoLogin" id="flag" value="1" <%if(flag!=null && flag.equals("1")){%> checked ; value ="1"; <%}else {%> value="0" <%;}%> />记住密码
						</label>
                                </span>
                                <span class="forget-pw-safe">
                                    <a href="${path}/login/toFindPwd" class="" target="">忘记密码?</a>
                                </span>
                            </div>
                        </div>
                        <div class="item item-fore5">
                            <div class="login-btn">
                                <a onclick="verify_submit();" id="butt" class="btn-img">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
                            </div>
                         
                        </div>
                    </div>
                </form>
                <div class="coagent">
                    <h5>使用合作网站账号登录帮you惠：</h5>
                    <ul>
                        <li><a href="#" class="f14"><i class="ico-login-otherway i-login-otherway-qq"></i>QQ</a></li>
                        <li><a href="#"><i class="ico-login-otherway i-login-otherway-tb"></i>淘宝</a></li>
                        <li><a href="#"><i class="ico-login-otherway i-login-otherway-sina"></i>新浪微博</a></li>
                        <li><a href="#"><i class="ico-login-otherway i-login-otherway-weixin"></i>微信</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="footer-right">
            <div class="footer-nav">
                <a href="#">阿里巴巴集团</a>|
                <a href="#">阿里巴巴国际站</a>|
                <a href="#">阿里巴巴中国站</a>|
                <a href="#">全球速卖通</a>|
                <a href="#">导购网</a>|
                <a href="#">天猫</a>|
                <a href="#">聚划算</a>|
                <a href="#">一淘</a>|
                <a href="#">阿里妈妈</a>|
                <a href="#">阿里云计算</a>|
                <a href="#">云OS</a>|
                <a href="#">万网</a>|
                <a href="#">支付宝</a>|
                <a href="#">来往</a>
            </div>
            <div class="about-tao">
                <span class="gary-text">Copyright©2004-2015  帮you惠byouh.com版权所有</span>
            </div>
        </div>
    </div>
    <script src="${path}/js/jquery_1.9.js"></script>
    <script src="${path}/js/img-show.js"></script>
    <script src="${path}/js/validate/jquery.validate.min.js"></script>
    <script src="${path}/js/validate/messages_cn.js"></script>
    <script src="${path}/js/main.js"></script>
</body>

<script>


$(function(){
	$('#email').blur(function(){
		if($('#email').val()!=null&&$('#email').val()!=''){
			insertNotice($("#email"),"");
		}else{
			insertNotice($("#email"),"请输入邮箱或者用户名 ");
		}
	});
	
	$('#password').blur(function(){
		if($('#password').val()!=null&&$('#password').val()!=''){
			insertNotice($("#password"),"");
		}else{
			insertNotice($("#password"),"请输入密码");
		}
	});
});






function checkAccount(account){
	var isAccountExist = false;
	$.ajax({
			type: "POST",
			url : "${path }/register/checkAccount",
			async: false,
			data:{account:account},
			dataType: "json",
			success: function(data){
				if(data.state == '0'){
					isAccountExist = true;
				}
			}
	}); 
	return isAccountExist;
}


function verify_submit(){
	var name=$("#email").val();
	var password=$("#password").val();
	var flag=$("#flag").val();
	insertNotice($("#email"),"");
	insertNotice($("#password"),"");
	insertNotice($("#butt"),"");
	if(name==""){
		insertNotice($("#email"),"请输入邮箱或者用户名 ");
		return;
	}
    if(password==""){
    	insertNotice($("#password"),"请输入密码");
    	return;
	}
   
    
    //ajax提交登入
	$.ajax({
		type: "POST",
		url : "${basePath}login/doLoginajax",
		async: false,
		data:{email:name,password:password,flag:flag},
		dataType: "json",
		success: function(data){
			console.log(data);
			if(data.state == '0'){
				location.href="${basePath}";
			}else{
				insertNotice($("#password"),"用户名或者密码错误  ");
			}
		}
     }); 
    
    
    
  // document.form1.submit();
	
}



</script>
</html>