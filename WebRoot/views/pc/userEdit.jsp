<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ include file="constant.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/area.js"></script>
	<script type="text/javascript" src="${path }/js/plupload/plupload.full.min.js"></script>
<title>帐号设置</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 index">
		<!-- 左侧内容 开始 -->
		<%@ include file="left.jsp" %>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w860 main">
			<div class="cont user_setting">
				<form action="${path }/pc/user/userUpdate"  method="post" onsubmit="return verify_submit()">
				    <input type="hidden" name="userId" value="${sessionScope.SESSION_USER.userId}">
					<h3 class="no_fold"><strong>基础资料</strong></h3>
					<div class="row">
						<strong class="fl space_10">品牌LOGO：</strong>
						<div class="fr">
							<img class="avatar_preview" src="${sessionScope.SESSION_USER.headimgUrl}" />
							<input id="browse" class="upload_avatar" type="button" value="修改头像" />
							<input class="hiddensrc" type="hidden" name="headimgbase64"  value="" />
							
							<% 
							User user = (User)request.getSession().getAttribute(Globals.SESSION_USER);
							if(user.getHeadimgUrl() != null){
									if(user.getHeadimgUrl().contains("group")){
										String picUrl = user.getHeadimgUrl();
										picUrl =  picUrl.substring(picUrl.indexOf("group"), picUrl.length());%>
										<input type="hidden" name="headimgUrl"  value="<%=picUrl %>" />
									<%}else{%>
										<input type="hidden" name="headimgUrl"  value="${sessionScope.SESSION_USER.headimgUrl }" />
									<%}
							   }
							%>
						</div>
						<div class="clear"></div>
					</div>
					<input type="hidden" name="token" value="${token}"> 
					<div class="row">
						<strong class="fl">昵称：</strong>
						<div class="fr">
							<p>
								<input maxlength="10" name="nickName" value="${sessionScope.SESSION_USER.nickName}" class="verify_name w200" type="text"/>
								<em>必填</em>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<strong class="fl">地区：</strong>
						<div class="fr">
							<select id="prov" name="province" onchange="provChange()">
								<c:choose>
									<c:when test="${sessionScope.SESSION_USER.province != null && sessionScope.SESSION_USER.province !=''}">
										<option>${sessionScope.SESSION_USER.province}</option>
									</c:when>
									<c:otherwise>
										<option>请选择</option>
									</c:otherwise>		
								</c:choose> 
							</select>
							<select id="city" name="city">
								<c:choose>
									<c:when test="${sessionScope.SESSION_USER.city != null && sessionScope.SESSION_USER.city !=''}">
											<option >${sessionScope.SESSION_USER.city}</option>
									</c:when>
									<c:otherwise>
											<option >请选择</option>
									</c:otherwise>		
								</c:choose> 
								
							</select>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<strong class="fl">简介：</strong>
						<div class="fr">
							<p>
								<textarea name="introduce"  class="verify_intro w300" maxlength="36">${sessionScope.SESSION_USER.introduce}</textarea>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<h3 class="no_fold"><strong>联系方式</strong></h3>
					<div class="row">
						<strong class="fl">电话：</strong>
						<div class="fr">
							<p>
								<input maxlength="11" name="mobile" value="${sessionScope.SESSION_USER.mobile}" class="verify_phone w200" type="text"/>&nbsp;&nbsp;
								<!-- <label><input name="mobileOpen" type="checkbox"/>不公开</label> -->
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<strong class="fl">Q Q：</strong>
						<div class="fr">
							<p>
								<input maxlength="15" name="qqAccount" value="${sessionScope.SESSION_USER.qqAccount}" class="verify_qq w200" type="text"/>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<strong class="fl">微信：</strong>
						<div class="fr">
							<p>
								<input maxlength="20" name="weixinAccount" value="${sessionScope.SESSION_USER.weixinAccount}" class="verify_wechat w200" type="text"/>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<strong class="fl">邮箱：</strong>
						<div class="fr">
							<p>
								<input name="email" value="${sessionScope.SESSION_USER.email}" class="verify_mail w200" type="text"/>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="row">
						<div class="fr">
							<p>
								<input type="submit" value="完成修改">
							</p>
						</div>
						<div class="clear"></div>
					</div>
				</form>
			</div>
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script>	
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'browse',
		url : '${path }/pc/user/userUpdate',
		flash_swf_url : '${path }/js/plupload/Moxie.swf',
		silverlight_xap_url : '${path }/js/plupload/Moxie.xap',
		filters: {
		  mime_types : [ //只允许上传图片文件
		    { title : "图片文件", extensions : "jpg,gif,png" }
		  ]
		}
	});
	uploader.init(); //初始化

	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		previewImage(files[0],function(imgsrc){
			$('.avatar_preview').attr('src',imgsrc);
			$('.hiddensrc').attr('value',imgsrc);
		})
	});

	function previewImage(file,callback){
	//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
		if(!file || !/image\//.test(file.type)) return; //确保文件是图片
		if(file.type=='image/gif'){//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
			var fr = new mOxie.FileReader();
			fr.onload = function(){
				callback(fr.result);
				fr.destroy();
				fr = null;
			}
			fr.readAsDataURL(file.getSource());
		}else{
			var preloader = new mOxie.Image();
			preloader.onload = function() {
				preloader.downsize( 300, 300 );//先压缩一下要预览的图片,宽300，高300
				var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
				callback && callback(imgsrc); //callback传入的参数为预览图片的url
				preloader.destroy();
				preloader = null;
			};
			preloader.load(file.getSource());
		}	
	}
	
	/**
	 * 更新用户
	 */
	function userUpdate(){
		$('#userForm').submit();
	}
	
	$(document).ready(function(){
		loadProv();
	//	alert('${sessionScope.SESSION_USER.mobileOpen}');
		if('${sessionScope.SESSION_USER.mobileOpen}' == '0'){
			$('input[type="checkbox"]').attr("checked","chedked");
		}
		
/* 		$('input[type="checkbox"]').click(function(){
			if($(this).prop("checked") == true ){
				alert($(this).prop("checked"));
				$(this).attr("value","0");
			}else{
				alert($(this).prop("checked"));
				$(this).attr("value","1");
			}
		}); */
	});
	
	//表单验证
	$(document).ready(function(){
		//表单字段验证
		$('.verify_name').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入名称");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_phone').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'number')){
					insertNotice($(this),"电话号码应为全数字");
					$(this).attr("verify","false");
					return false;
				}else if(!verify($(this).val(),11,'count')){
					insertNotice($(this),"请输入11位电话号码");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),"");
					$(this).attr("verify","true");
					return true;
				}
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_qq').blur(function(){
			if(!verify($(this).val(),'number')){
				insertNotice($(this),"QQ号码应为全数字");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_wechat').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'username')){
					insertNotice($(this),"请输入正确的微信号格式");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),"");
					$(this).attr("verify","true");
					return true;
				}
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_mail').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'email')){
					insertNotice($(this),"请输入正确的邮箱格式");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),"");
					$(this).attr("verify","true");
					return true;
				}
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
	});
	var flag=true;
	//提交表单前端验证
	function verify_submit(){
		if($('.verify_name').blur().attr("verify") != "false" && $('.verify_phone').blur().attr("verify") != "false" && $('.verify_qq').blur().attr("verify") != "false" && $('.verify_wechat').blur().attr("verify") != "false" && $('.verify_mail').blur().attr("verify") != "false"){
			//防止表单重复提交
			if (flag==false){
				return false;
				window.location.reload();//刷新当前页面.
				}
				flag=false;
				return true;
				window.location.reload();//刷新当前页面.
		}else{
			return false;
		}
	}
	
</script>


