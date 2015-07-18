<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/lrz.pc.min.js"></script>
	<title>注册</title>
</head>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 注册 开始 -->
	<div class="w1190 register">
		<form class="cont" action="${path }/register/doRegister" onsubmit="return verify_submit()" method="post">
			<h2>注册</h2>
			<div class="row">
				<span class="fl">账号</span>
				<div class="fr">
					<input class="verify_mail" type="text" name="account" placeholder="邮箱" verify="false" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row">
				<span class="fl">设置密码</span>
				<div class="fr">
					<input class="verify_pass1" type="password" name="password" maxlength="16" verify="false" />
					<i>*</i><b>长度为6~16位字符</b><em></em>
				</div>
				<div class="clear"></div>
			</div>
			 <input type="hidden" name="token" value="${token}"> 
			<div class="row">
				<span class="fl">确认密码</span>
				<div class="fr">
					<input class="verify_pass2" type="password" maxlength="16" verify="false" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row">
				<span class="fl">品牌名称</span>
				<div class="fr">
					<input class="verify_name" name="nickName" maxLength=100 type="text" />
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">品牌LOGO</span>
				<div class="fr">
					<input id="preview" name="headimgbase64" type="hidden" verify="false" />
					<img class="preview" src="${path}/images/registerlogo.png" />
					<div class="uploadimg"><input class="verify_logo" onchange="imgToBase64(this)" type="file" verify="true" />上传图片</div>
					<i>*</i><b>推荐150X150或等比尺寸</b>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_10"></div>
			<div class="row">
				<span class="fl">验证码</span>
				<div class="fr">
					<input class="verify_vcode" style="width:80px;" type="text" name="identifyCode" verify="true"  />
					<img class="vcode" src="${path}/RandomValidateCodeServlet" />
					<div class="change_vcode" onclick="reloadImage();">换一张</div>
					<i>*</i><em></em>
				</div>
				<div class="clear"></div>
			</div>
			<div class="row">
				<span class="fl">&nbsp;</span>
				<div class="fr">
					<label>
						<input class="verify_protocol" type="checkbox" verify="false" />我已经阅读并同意<a href="${path }/register/agreeMent">《注册协议》</a>
					</label>
				</div>
				<div class="clear"></div>
			</div>
			<div class="space_20"></div>
			<div class="row">
				<span class="fl">&nbsp;</span>
				<div class="fr">
					<input type="submit" value="注 册" />&nbsp;&nbsp;
					<b>已有帐号<a href="${basePath }login">马上登录</a></b>
				</div>
				<div class="clear space_20"></div>
			</div>
		</form>
	</div>
	<!-- 注册 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	
</body>
</html>
<script type="text/javascript">
	//图片上传预览
	function imgToBase64(file){
		if (file.files && file.files[0]){
			var reader = new FileReader();
			reader.onload = function(evt){
				$(file).parent().prev().attr("src",evt.target.result);
				$('#preview').attr("value",evt.target.result);
			}
			reader.readAsDataURL(file.files[0]);
		}
	}

	//屏蔽复制密码
	$(function(){
		$("input:password").bind("copy cut paste",function(e){
			return false;
		});
	});


	//验证用户名
	$(function(){
		$('.verify_mail').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'email','')){
					insertNotice($(this),"请输入正确的邮箱格式");
					$(this).attr("verify","false");
					return false;
				}else{
					///////////////////lll....
					var state = checkAccount($(this).val());
					if(state){//帐号已被注册
						insertNotice($(this),'该账号已被注册<a href="http://wpa.qq.com/msgrd?v=3&uin=${agentInfoConst.serviceqq }&site=qq&menu=yes">点击申诉</a>');
						$(this).attr("verify","false");
						return false;
					}else{
						insertNotice($(this),'<img src="${path}/images/pc/done_ico.png">');
						$(this).attr("verify","true");
						return true;
					}
				}
			}else{
				insertNotice($(this),"请输入内容");
				$(this).attr("verify","false");
				return false;
			}
		});
	});

	
	//检查账号是否注册过
	
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
	
	//验证密码//检查验证码是否正解
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

	//验证品牌标志是否上传
/* 	$(function(){
		$('.verify_logo').change(function(){
			insertNotice($(this).parent(),'<img src="${path}/images/pc/done_ico.png">');
			$(this).attr("verify","true");
			return true;
		});
	}); */

	//验证品牌名称
	$(function(){
		$('.verify_name').blur(function(){
			if($(this).val()){
				insertNotice($(this),'<img src="${path}/images/pc/done_ico.png">');
				$(this).attr("verify","true");
				return true;
			}else{
				insertNotice($(this),"请输入品牌名称");
				$(this).attr("verify","false");
				return false;
			}
		});
	});
	
	//检查品牌logo
	$(function(){
		$('#preview').blur(function(){
			if($(this).val()){
				insertNotice($(this),'<img src="${path}/images/pc/done_ico.png">');
				$(this).attr("verify","true");
				return true;
			}else{
				insertNotice($(this),"请选择品牌LOGO");
				$(this).attr("verify","false");
				return false;
			}
		});
	}); 

	//验证验证码
	$(function(){
		$('.verify_vcode').blur(function(){
			if($(this).val()){
				$(this).attr("verify","true");
				//return true;
				////mmlllll
				//alert("11==="+$(this).val());
				var code=$(this).val();
				$.ajax({
				type: "POST",
				url : "${path }/common/checkIdentifyCodeU",
				data:{randomCode:code},
				dataType: "json",
				success: function(data){
					//alert(data.state);
					if(data.state == true){
						//checkState = true;
						insertNotice($(".verify_vcode"),'<img src="${path}/images/pc/done_ico.png">');
						$(this).attr("verify","true");
						return true;
					}else{
						insertNotice($(".verify_vcode"),"验证码有误");
						$(this).attr("verify","false");
						return false;
					}
				}
		}); 		
			}else{
				insertNotice($(this),"请输入验证码");
				$(this).attr("verify","false");
				return false;
			}
		});
	});

	//验证是否同意协议
	$(function(){
		$('.verify_protocol').change(function(){
			if($(this).prop("checked") == true){
				$(this).attr("verify","true");
				return true;
			}else{
				$(this).attr("verify","false");
				return false;
			}
		});
	});

	var flag=true;
	//提交表单前端验证
	function verify_submit(){
		var verify = 0;
		$('input[type="text"],input[type="password"],.verify_protocol,input[id="preview"]').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		if(verify > 0){
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
	
	//更换验证码
	function reloadImage(){
		 $(".vcode").attr("disable","true");
		 $(".vcode").attr("src",'<%=path%>/RandomValidateCodeServlet?ts='+new Date().getTime());
		 $(".vcode").attr("disable","false");
	} 
</script>



