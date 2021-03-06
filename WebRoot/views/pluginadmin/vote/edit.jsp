<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/daterangepicker.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css" />
<link type="text/css" rel="stylesheet" href="${adminCssPath}/vote.css"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/vote.js"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js"></script>
<script>
	KindEditor.ready(function(K) {
	    var editor = K.editor({
	    	formatUploadUrl:false,
	        allowFileManager: false,
	        uploadJson: "<%=path%>/common/toServer",
	        filePostName:"Filedata",
	        extraFileUploadParams:{uploadType:"image"},
	    });
	    $('.fileuploadclass').live("click",function(e) {
	        editor.loadPlugin('image',function() {
	            editor.plugin.imageDialog({
	                showRemote : false,
	                clickFn: function(url, filename, width, height, border, align) {
	                    $(e.target).parent().prev().find('img').attr("src","${resUrl}"+url);
	                    $(e.target).val(url);
	                    editor.hideDialog();
	                }
	            });
	        });
	    });
	});
	$(document).ready(function(){
		$('.need_usermsg input[type="checkbox"]').change(function(){
			if($(this).attr("class") == "need_other"){
				if($(this).prop("checked") == true){
					$(this).parent().next().attr("style","display:inline-block;");
				}else{
					$(this).parent().next().attr("style","display:none;");
					$(this).parent().next().val('');
				}
			}
		});
	});
	$(document).ready(function(){
		$('.need_usermsg2 input[type="checkbox"]').change(function(){
			if($(this).attr("class") == "need_other"){
				if($(this).prop("checked") == true){
					$(this).parent().next().attr("style","display:inline-block;");
				}else{
					$(this).parent().next().attr("style","display:none;");
					$(this).parent().next().val('');
				}
			}
		});
	});
	function onClick(){
		if(!verify_submit()){
			return false;
		}
		//用户信息抓取
		var str = '';
		$('.need_usermsg').each(function(){
			if( $(this).find('input[type="checkbox"]').prop("checked") == true){
				str += $(this).find('input[type="checkbox"]').val();
				var chkval = $(this).find('input[type="checkbox"]').val();
				if( $(this).find('input[type="checkbox"]').attr("class") == "need_other"){
					var other = $(this).find('input[type="checkbox"]').parent().next().val();
					if(other.length == 0){
						str=str.replace(chkval,"");
					}else{
						str += other+",";
					}
				}
			}
		});
		$('#publicInfo').val(str);
		var str = '';
		$('.need_usermsg2').each(function(){
			if( $(this).find('input[type="checkbox"]').prop("checked") == true){
				str += $(this).find('input[type="checkbox"]').val();
				var chkval = $(this).find('input[type="checkbox"]').val();
				if( $(this).find('input[type="checkbox"]').attr("class") == "need_other"){
					var other = $(this).find('input[type="checkbox"]').parent().next().val();
					if(other.length == 0){
						str=str.replace(chkval,"");
					}else{
						str += other+",";
					}
				}
			}
		});
		$('#hideInfo').val(str);
	}
</script>
<title>模版设置</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 vote">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a href="#" class="active">活动设置</a>
				<a href="${path}/pluginadmin/vote/rank/${conf.activityId}">排行榜</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/vote/edit_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input class="verify_name w300" type="text" maxlength="30" name="name" value="${conf.name}" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_15">活动图标：</strong>
							<div class="fr">
								<div class="preview">
								<c:choose>
									<c:when test="${conf.shareImgUrl != null && conf.shareImgUrl.length() !=0}">
										<img class="w80 h80" src="${resUrl}/${conf.shareImgUrl}" />
									</c:when>
									<c:otherwise>
										<img class="w80 h80" src="${adminImgPath}/vote/logo.png"/>
									</c:otherwise>
								</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value="${conf.shareImgUrl}"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过20K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">插入广告：</strong>
							<div class="fr">
								<p class="tab_ad">
									<label class="active"><input type="radio"  name="bAdvert" value="0" <c:if test="${conf.bAdvert == '0'}">checked</c:if>/>开启</label>
									<label><input type="radio" name="bAdvert" value="1" <c:if test="${conf.bAdvert == '1'}">checked</c:if>/>关闭</label>
								</p>
								<div class="ad_upcont space_10">
									<div class="preview">
									<c:choose>
										<c:when test="${conf.advertImgUrl != null && conf.advertImgUrl.length() !=0}">
											<img class="w320 h50 imghead" src="${resUrl}/${conf.advertImgUrl}" />
										</c:when>
										<c:otherwise>  
											<img class="w320 h50 imghead" src="${adminImgPath}/ad.gif" />
										</c:otherwise>
									</c:choose>
									</div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" name="advertImgUrl" value="${conf.advertImgUrl}"/>
										<span><em class="ico_image"><!-- 图标 --></em>上传广告图</span>
									</div>
									<b>建议尺寸：640*100（或等比），大小不超过50K。</b>
									<p>
										<input name="advertUrl" class="w420" type="text" value="${conf.advertUrl}" />
									</p>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input class="verify_title w300" type="text" maxlength="25" placeholder="标题" value="${conf.shareTitle}" name="shareTitle"/>
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea class="verify_intro w300" maxlength="36" placeholder="简介" name="shareDescription">${conf.shareDescription}</textarea>
									<b>发送给朋友会显示简介，分享朋友圈不会显示简介</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">活动时间：</strong>
							<div class="fr">
								<input type="text" name="activityTime" class="daterangepick w320 verify_time" value="<fmt:formatDate value='${conf.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /> - <fmt:formatDate value='${conf.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
								<b>请勿超过模版有效期限</b>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>活动设置</strong><span class="fold">展开</span></h3>
					<div class="act_config">
						<div class="row"  id="attention_limit" style="margin-bottom:0;">
							<strong class="fl w220">关注门槛：</strong>
							<div class="fr w560">
								<p>
									<label><input type="radio" name="bUrlType" value="0" <c:if test="${conf.bUrlType == '0'}">checked</c:if>/>给他投票</label>&nbsp;&nbsp;
									<label><input type="radio" name="bUrlType" value="1" <c:if test="${conf.bUrlType == '1'}">checked</c:if>/>参与活动</label>
									<label><input type="radio" name="bUrlType" value="2" <c:if test="${conf.bUrlType == null || conf.bUrlType == '2'}">checked</c:if>/>无</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<c:choose>
							<c:when test="${conf.bUrlType == '0'}">
							<div class="row" id="followDiv">
								<div class="space_10"></div>
									<strong class="fl w220">引导关注链接：</strong>
									<div class="fr w560">
										<p>
											<input name="followUrl" class="verify_contact1 w420" type="text" value="${conf.followUrl}" /><br />
											<b>用户欲投票会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“投票”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
										</p>
									</div>
								<div class="clear"></div>
							</div>
							</c:when>
							<c:when test="${conf.bUrlType == '1'}">
							<div class="row" id="followDiv">
								<div class="space_10"></div>
									<strong class="fl w220">引导关注链接：</strong>
									<div class="fr w560">
										<p>
											<input name="followUrl" class="verify_contact1 w420" type="text" value="${conf.followUrl}" /><br />
											<b>用户欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“投票”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
										</p>
									</div>
								<div class="clear"></div>
							</div>
							</c:when>
							<c:otherwise>
								<div class="row" id="followDiv"></div>
							</c:otherwise>
						</c:choose>
						<div class="row">
							<strong class="fl w220">参赛者上限：</strong>
							<div class="fr w560">
								<p>
									<input name="playPeople" class="verify_num w80 game_count" type="text" value="${conf.playPeople}" />
									<b>大于0</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">活动规则：</strong>
							<div class="fr w560">
								<p>
									<textarea name="rule" class="verify_rule w430 h120">${conf.rule}</textarea>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">参赛者显示信息：</strong>
							<div class="fr w560">
								<p>
									<label><input type="checkbox" checked="checked" disabled="disabled" />照片</label>
									<label><input type="checkbox" checked="checked" disabled="disabled" />标题</label>
									<label class="need_usermsg"><input value="desc," type="checkbox" <c:if test="${conf.publicInfo.contains('desc')}">checked</c:if>/>描述简介</label>
									<span class="need_usermsg">
										<label><input class="need_other" value="other1:" type="checkbox" <c:if test="${conf.publicInfo.contains('other1:')}">checked</c:if>/>其他</label>
										<input type="text" class="other w80" value="${conf.publicInfo.split('other1:')[1].split(',')[0]}" <c:if test="${conf.publicInfo.contains('other1:')}">style="display:inline-block;"</c:if>/>
									</span>
									<span class="need_usermsg">
										<label><input class="need_other" value="other2:" type="checkbox" <c:if test="${conf.publicInfo.contains('other2:')}">checked</c:if>/>其他</label>
										<input type="text" class="other w80" value="${conf.publicInfo.split('other2:')[1].split(',')[0]}" <c:if test="${conf.publicInfo.contains('other2:')}">style="display:inline-block;"</c:if>/>
									</span>
									<span class="need_usermsg">
										<label><input class="need_other" value="other3:" type="checkbox" <c:if test="${conf.publicInfo.contains('other3:')}">checked</c:if>/>其他</label>
										<input type="text" class="other w80" value="${conf.publicInfo.split('other3:')[1].split(',')[0]}" <c:if test="${conf.publicInfo.contains('other3:')}">style="display:inline-block;"</c:if>/>
									</span>
									<input type="hidden" name="publicInfo" id="publicInfo" value="" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">参赛者隐藏信息：</strong>
							<div class="fr w560">
								<p>
									<label class="need_usermsg2"><input value="tel," type="checkbox" <c:if test="${conf.hideInfo.contains('tel')}">checked</c:if>/>手机号</label>
									<label class="need_usermsg2"><input value="username," type="checkbox" <c:if test="${conf.hideInfo.contains('username')}">checked</c:if>/>姓名</label>
									<label class="need_usermsg2"><input value="mail," type="checkbox" <c:if test="${conf.hideInfo.contains('mail')}">checked</c:if>/>邮寄地址</label>
									<label class="need_usermsg2"><input value="qq," type="checkbox" <c:if test="${conf.hideInfo.contains('qq')}">checked</c:if>/>QQ</label>
									<label class="need_usermsg2"><input value="wechat," type="checkbox" <c:if test="${conf.hideInfo.contains('wechat')}">checked</c:if>/>微信号</label>
									<span class="need_usermsg2">
										<label><input class="need_other" value="other4:" type="checkbox" <c:if test="${conf.hideInfo.contains('other4:')}">checked</c:if>/>其他</label>
										<input type="text" class="other w80" value="${conf.hideInfo.split('other4:')[1].split(',')[0]}" <c:if test="${conf.hideInfo.contains('other4:')}">style="display:inline-block;"</c:if>/>
									</span>
									<input type="hidden" name="hideInfo" id="hideInfo"  value="" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>自定义设置</strong><span class="fold">展开</span></h3>
					<div class="custom_config">
						<div class="row">
							<strong class="fl w220">背景：</strong>
							<div class="fr w560">
								<div class="preview">
								<c:choose>
									<c:when test="${conf.bgUrl != null && conf.bgUrl.length() !=0}">
										<img class="w160 h240" src="${resUrl}/${conf.bgUrl}" />
									</c:when>
									<c:otherwise>  
										<img class="w160 h240" src="${adminImgPath}/vote/bg.jpg" />
									</c:otherwise>
								</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="bgUrl" value="${conf.bgUrl}">
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="row">
						<div class="fr">
							<p>
								<input type="submit" value="提 交" />
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<input type="hidden" name="id" value="${conf.id}"/>
					<input type="hidden" name="activityId" value="${conf.activityId}"/>
					<input type="hidden" name="createTime" value="<fmt:formatDate value='${conf.createTime}' pattern='yyyy-MM-dd HH:mm:ss' />"/>
				</form>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<%@ include file="../../pc/contactSupport.jsp" %>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="../../pc/footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		//表单字段验证
		$('.verify_name').eq(0).blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入活动名称");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$(document).on("blur",".verify_prizename",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入奖品名称");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$(document).on("blur",".verify_num",function(){
			if($(this).val()){
				if(!verify($(this).val(),'number')){
					insertNotice($(this),"请输入正确格式");
					$(this).attr("verify","false");
					return false;
				}else{
					insertNotice($(this),"");
					$(this).attr("verify","true");
					return true;
				}
			}else{
				insertNotice($(this),"请输入数字");
				$(this).attr("verify","false");
				return false;
			}
		});
		$('.verify_title').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入分享标题");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_intro').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入活动简介");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_relation').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入关系");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_time').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请选择时间");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_rule').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入活动规则");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$(document).on("blur",".verify_contact",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入兑奖说明");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$(document).on("blur",".verify_contact1",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入引导关注链接");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_attlink').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入内容");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_url').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'url')){
				insertNotice($(this),"请输入正确的URL格式");
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
		//提交前验证
		var verify = 0;
		$('input[type="text"]').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		$('textarea').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		
		if(verify > 0){
			$('.vote .main .cont .cont_config,.vote .main .cont .act_config,.vote .main .cont .custom_config').attr("style","display:block;");	
			$('.vote .main .cont h3 span').text("收起");
			alert("请核查全部字段是否正确填写");
			return false;
		}else{
			return true;
		}
	}


$(document).ready(function(){
	$('#attention_limit input[type="radio"]').change(function(){
			if($(this).val() == 0){
				$('#followDiv').html('<div class="space_10"></div>\
					<strong class="fl w220">引导关注链接：</strong>\
						<div class="fr w560">\
							<p>\
								<input name="followUrl" class="verify_contact1 w420" type="text" value="${conf.followUrl}" /><br />\
								<b>用户欲投票会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“投票”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else if($(this).val() == 1){
				$('#followDiv').html('<div class="space_10"></div>\
					<strong class="fl w220">引导关注链接：</strong>\
						<div class="fr w560">\
							<p>\
								<input name="followUrl" class="verify_contact1 w420" type="text" value="${conf.followUrl}" /><br />\
								<b>用户欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“投票”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>