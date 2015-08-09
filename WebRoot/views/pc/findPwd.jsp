<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<title>找回密码</title>
</head>
<body class="reg">
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 找回密码开始 -->
	<div class="forgetPwd">
		<form class="cont" action="${path }/login/findPassWord" onsubmit="return verifyy_submit()" method="post" >
			<h2>找回密码</h2>
			<div class="row">
				<label>账号</label>
				<div class="email">
					<input class="verifyy_mail" name="account" type="text" placeholder="邮箱" verify="false" />
					<i>*</i><em></em>
				</div>
			</div>
			<p class="tip">友情提示：如该邮箱不是您现在使用的邮箱，请<a href="http://wpa.qq.com/msgrd?v=3&uin=${agentInfoConst.serviceqq }&site=qq&menu=yes">联系在线客服</a>进行密码重置。</p>
		<!-- 	<div class="row">
				<label>验证码</label>
				<div class="email code">
					<input class="verify_vcode" type="text" verify="false" />
					<a class="getvcode"><i>*</i>获取验证码</a>
					<em></em>
				</div>
			</div> -->
			 <input type="hidden" name="token" value="${token}"> 
			<div class="row">
				<input type="submit"  value="发送验证邮件" />
			</div>
		</form>
	</div>
	<!-- 找回密码 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">

//验证邮件
$(function(){
	$('.verifyy_mail').blur(function(){
		if($(".verifyy_mail").val()){
			if(!verify($(".verifyy_mail").val(),'email','')){
				insertNotice($(".verifyy_mail"),"请输入正确的邮箱格式");
				$(".verifyy_mail").attr("verify","false");
				return false;
			}else{
				var state = checkAccountt($(".verifyy_mail").val());
				if(state){//帐号已注册
					$(".verifyy_mail").attr("verify","true");
				    return true;
				}else{
					$(".verifyy_mail").attr("verify","false");
					insertNotice($(".verifyy_mail"),'抱歉，平台无此账号！请重新输入！');
					//$(this).attr("verify","true");
					return false;
				}
			}
			
		}else{
			insertNotice($(".verifyy_mail"),"请输入邮箱！");
			$(".verifyy_mail").attr("verify","true");
			return false;
			
		}	
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
		//alert("33");
		return false;
	}else{
		//alert("44");
		return true;
	}
}


//检查账号是否注册过

function checkAccountt(account){
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
</script>



