<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>

<!DOCTYPE html>
<html>
<head>
<title>用户资料编辑</title>
	<%@ include file="resource.jsp"%>
	<script type="text/javascript" src="${path }/js/area.js"></script>
</head>
<body>
	<div class="space_20"></div>
	<!-- 资料编辑 开始 -->
	<div class="usermsg_edit">
		<form action="${path }/wx/user/edit"  method="POST" onsubmit="return verify_submit()">
			<input type="hidden" name="userId" value="${user.userId }">
			<div class="box">
				<h3>基础资料</h3>
				<div class="row">
					<div class="avatar_upload">
						<c:choose>
							<c:when test="${user.headimgUrl != null || user.headimgUrl !=''}">
								<div class="preview"><img name="headimgbase64"  class="imghead" src="${user.headimgUrl }" /></div>
							</c:when>
							<c:otherwise>
								<div class="preview"><img name="headimgbase64"  class="imghead" src="${path }/images/wx/avatar.png" /></div>
							</c:otherwise>
						</c:choose>	
						<input class="hiddensrc" type="hidden" name="headimgbase64"  value="" />
						<input type="file" onchange="previewImages(this)" />
					
					</div>
					
					<% 
					    User user = (User)request.getAttribute("user");
						if(user.getHeadimgUrl() != null){
							if(user.getHeadimgUrl().contains("group")){
								String picUrl = user.getHeadimgUrl();
								picUrl =  picUrl.substring(picUrl.indexOf("group"), picUrl.length());%>
								<input type="hidden" name="headimgUrl"  value="<%=picUrl %>" />
							<%}else{%>
								<input type="hidden" name="headimgUrl"  value="${user.headimgUrl }" />
							<%}
						}
					%>
					
					<span>点击头像修改</span>
					<div class="clear"></div>
				</div>
				<div class="row">
					<strong>昵称：</strong>
					<div class="fl">
						<input type="text" name="nickName" class="verify_name" value="${user.nickName }"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<strong>地区：</strong>
					<div class="fl">
						<select id="prov" name="province" onchange="provChange()">
							<c:choose>
								<c:when test="${user.province != null && user.province !=''}">
									<option>${user.province}</option>
								</c:when>
								<c:otherwise>
									<option>请选择</option>
									</c:otherwise>		
							</c:choose> 
						</select>
						<div class="space_5"></div>
						<div class="space_10"></div>
						<select id="city" name="city">
							<c:choose>
								<c:when test="${user.city != null && user.city !=''}">
									<option >${user.city}</option>
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
					<strong>简介：</strong>
					<div class="fl">
						<textarea name="introduce">${user.introduce }</textarea>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="space_20"></div>
			<div class="box">
				<h3>联系方式</h3>
				<div class="row">
					<strong>手机：</strong>
					<div class="fl">
						<input type="text" name="mobile" class="verify_phone" value="${user.mobile}" />
						<label><input name="mobileOpen" type="checkbox"/>不公开</label>
						
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<strong>Q Q：</strong>
					<div class="fl">
						<input type="text" name="qqAccount" class="verify_qq" value="${user.qqAccount }"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<strong>微信：</strong>
					<div class="fl">
						<input type="text" name="weixinAccount" class="verify_wechat" value="${user.weixinAccount }"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="row">
					<strong>邮箱：</strong>
					<div class="fl">
						<input type="text" name="email" class="verify_mail" value="${user.email }"/>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="space_30"></div>
			<div class="space_30"></div>
			<div class="space_20"></div>
			<div class="btn_submit"><input type="submit" value="确定" /></div>
		</form>
	</div>
	<!-- 资料编辑 结束 -->
	<div class="space_20"></div>
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">
	
	/**
	 * 更新用户
	 */
	function userUpdate(){
		$('#userForm').submit();
	}
	
	$(document).ready(function(){
		loadProv();
		if('${user.mobileOpen}' == '0'){
			$('input[type="checkbox"]').attr("checked","chedked");
		}
		
	});
		
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
	//提交表单前端验证
	function verify_submit(){
		if($('.verify_name').blur().attr("verify") != "false" && $('.verify_phone').blur().attr("verify") != "false" && $('.verify_qq').blur().attr("verify") != "false" && $('.verify_wechat').blur().attr("verify") != "false" && $('.verify_mail').blur().attr("verify") != "false"){
			return true;
		}else{
			return false;
		}
	}
	
	
	function getByClass(oParent,nClass){
		var eLe = oParent.getElementsByTagName('*');
		var aRrent  = [];
		for(var i=0; i<eLe.length; i++){
			if(eLe[i].className == nClass){
				aRrent.push(eLe[i]);
			}
		}
		return aRrent;
	}
	function previewImages(file){
		var MAXWIDTH = 62;
		var MAXHEIGHT = 62;
		var div = getByClass(file.parentNode.parentNode,'preview')[0];
		if (file.files && file.files[0]){
			div.innerHTML = '<img class="imghead">';
			var img = getByClass(file.parentNode.parentNode,'imghead')[0];
			img.onload = function(){
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				img.style.marginLeft = rect.left+'px';
				img.style.marginTop = rect.top+'px';
			}
			var reader = new FileReader();
			reader.onload = function(evt){
				img.src = evt.target.result;
				$('.hiddensrc').val(evt.target.result);
			}
			reader.readAsDataURL(file.files[0]);
		}else{
			var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img class="imghead">';
			var img = getByClass(file.parentNode.parentNode,'imghead')[0];
			var rect = clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
			status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
			div.innerHTML = "<div class='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
		}
	}
	function clacImgZoomParam(maxWidth,maxHeight,width,height){
		var param = {top:0, left:0, width:width, height:height};
		if( width>maxWidth || height>maxHeight){
			rateWidth = width/maxWidth;
			rateHeight = height/maxHeight;
			if( rateWidth > rateHeight){
				param.width = maxWidth;
				param.height = Math.round(height/rateWidth);
			}else{
				param.width = Math.round(width/rateHeight);
				param.height = maxHeight;
			}
		}
		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
	
	//微信分享
	wx.config({
// 	    appId: '${shareArg.appId}',
// 	    timestamp: '${shareArg.timestamp}',
// 	    nonceStr: '${shareArg.nonceStr}',
// 	    signature: '${shareArg.signature}',
	    jsApiList: [
	        'wx.hideOptionMenu()'
	    ]
	});
	wx.ready(function () {
		wx.hideOptionMenu();
	});
</script>

