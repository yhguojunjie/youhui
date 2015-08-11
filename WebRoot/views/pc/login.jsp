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
    <link rel="stylesheet" href="${path}/css/owlCarousel/owl.carousel.css" >
    <link rel="stylesheet" href="${path}/css/owlCarousel/owl.theme.css" >
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
       <div id="owl-demo" class="owl-carousel">
            <a class="item"><img src="${path}/img/logo/logo1.png" alt=""></a>
            <a class="item"><img src="${path}/img/logo/logo2.png" alt=""></a>
            <a class="item"><img src="${path}/img/logo/logo3.png" alt=""></a>
            <a class="item"><img src="${path}/img/logo/logo4.png" alt=""></a>
            <a class="item"><img src="${path}/img/logo/logo5.png" alt=""></a>
        </div>
        <div class="login-form">
            <div class="mt">
                <h1>帮you惠会员</h1>
                <a href="${basePath}register/pc" target="_blank"><b></b>免费注册</a>
            </div>
            <div class="msg-wrap">
                <div class="msg-warn"><b></b>公共场所不建议记住密码，以防账号丢失</div>
                <div class="msg-error" style="/* display: none; */"><b></b></div>
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
  <!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
<!-- 底部 结束 -->
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/img-show.js"></script>
    <script src="${path}/js/owlCarousel/owl.carousel.js"></script>
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
				insertNotice($("#butt"),"用户名或者密码错误  ");
			}
		}
     }); 
    
    
    
  // document.form1.submit();
	
}



</script>
</html>