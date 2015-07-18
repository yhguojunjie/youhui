<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<title>找回密码</title>
</head>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 找回密码开始 -->
	<div class="w1190 register">
		<form class="cont" action="${path }/login/resetPassWord" onsubmit="return verifyy_submit()" method="post">
			<h2>找回密码</h2>
			<div class="row">
				<span class="fl">新密码</span>
				<div class="fr">
					<input class="verify_pass1" type="password" name="password" maxlength="16" verify="false" />
					<i>*</i><b>长度为6~16位字符</b><em></em>
				</div>
				<div class="clear"></div>
			</div>
			 <input type="hidden" name="token" value="${token}"> 
			<script >
		
           </script>
			
			<input id="preview2" name="userId" value="" type="hidden" />
			<div class="row">
				<span class="fl">确认密码</span>
				<div class="fr">
					<input class="verify_pass2" type="password" maxlength="16" verify="false" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
				<%String message=(String)request.getAttribute("message2");
				if(message==null){
					message="";
				}
				%>
			<div class="row" style="color:red;" >
					<%=message %>
			</div>
			<div class="space_20"></div>
			
			<div class="row">
				<span class="fl">&nbsp;</span>
				<div class="fr">
					<input type="submit" value="完 成" />
				</div>
				<div class="clear space_20"></div>
			</div>
		</form>
	</div>
	<!-- 找回密码 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	<!-- 浮动菜单 开始 -->
	<div class="float_menu">
		<ul>
			<li class="sliding">
				<a><em class="phone_ico"></em><span>0592-7127103</span></a>
			</li>
			<li class="clear"></li>
			<li class="sliding">
				<a href="#"><em class="qq_ico"></em><span>点击QQ交谈</span></a>
			</li>
			<li class="clear"></li>
			<li id="float_menucode">
				<a href="#" class="sliding"><img src="${path}/images/pc/float_code.jpg"></a>
			</li>
			<li class="clear"></li>
			<li class="gotop">
				<a><em class="gotop_ico">返回顶部</em></a>
			</li>
		</ul>
	</div>
	<!-- 浮动菜单 结束 -->
</body>
</html>
<script type="text/javascript">

//验证密码//检查验证码是否正解
$(function(){
	//alert(window.location);
	var strs= new Array(); //定义一数组
	strs=window.location.toString().split("="); //字符分割 
	//alert(strs[2]);
	var userId=strs[2];
	//alert(userId);
	 $("#preview2").attr("value",userId); 

	//验证密码
		$(function(){
			$('.verify_pass1').blur(function(){
				$(".verify_pass2").blur();
				if($(this).val()){
					if($(this).val().length > 5){
						insertNotice($(this),'<img src="${path}/images/pc/done_ico.png">');
						$(this).attr("verify","true");
						return true;
					}else{
						insertNotice($(this),"请输入合适的密码长度");
						$(this).attr("verify","false");
						return false;
					}
				}else{
					insertNotice($(this),"请输入密码");
					$(this).attr("verify","false");
					return false;
				}
			});
			$('.verify_pass2').blur(function(){
				if($(this).val()){
					if($(this).val().length > 5){
						if($('.verify_pass1').val() == $('.verify_pass2').val()){
							insertNotice($(this),'<img src="${path}/images/pc/done_ico.png">');
							$(this).attr("verify","true");
							return true;
						}else{
							insertNotice($(this),'两次输入密码不一致');
							$(this).attr("verify","false");
							return false;
						}
					}else{
						insertNotice($(this),"请输入合适的密码长度");
						$(this).attr("verify","false");
						return false;
					}
				}else{
					insertNotice($(this),"请输入密码");
					$(this).attr("verify","false");
					return false;
				}
			});
		});
	
	
});


function verifyy_submit(){
	var verify = 0;
	$('input[type="text"],input[type="password"],.verify_protocol').each(function(){
		$(this).blur();
		if($(this).attr("verify") == "false"){
			verify += 1;
		}
	});
	if(verify > 0){
		return false;
	}else{
		return true;
	}
}

</script>



