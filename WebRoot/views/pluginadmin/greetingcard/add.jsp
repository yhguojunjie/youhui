<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/bootstrap.min.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/bootstrap/daterangepicker.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/basic.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/global.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/layout.css?v=2" />
<link type="text/css" rel="stylesheet" href="${adminCssPath}/greetingcard.css?v=2" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css?v=2"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js?v=2"></script>
<script type="text/javascript" src="${adminJsPath}/param.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js?v=2"></script>
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
	KindEditor.ready(function(K) {
		    var editor = K.editor({
		    	formatUploadUrl:false,
		        allowFileManager: false,
		        uploadJson: "<%=path%>/common/toServer",
		        filePostName:"Filedata",
		        extraFileUploadParams:{uploadType:"mp3"},
		    });
		    $('#bgmusic_obj').live("click",function(e) {
		        editor.loadPlugin('image',function() {
		            editor.plugin.imageDialog({
		                showRemote : false,
		                clickFn: function(url, filename, width, height, border, align) {
		                	$(e.target).val(url);
		                    $('#bgmusic_name').text(filename);
		                    $('#bgmusic').attr("src", "${resUrl}/"+url);
		                    editor.hideDialog();
		                }
		            });
		        });
		    });
		});
</script>
<title>模版配置</title>
 <script type="text/javascript">  
function onClick(){
	if(!verify_submit()){
		return false;
	}
}
</script>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 greetingcard">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a class="active">活动设置</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/greetingcard/add_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_name" type="text" name="name" maxlength="30" value="蠢萌贺卡DIY" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">活动图标：</strong>
							<div class="fr">
								<div class="preview"><img class="w80 h80" src="${adminImgPath}/greetingcard/shareImg.jpg"/></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">商家LOGO：</strong>
							<div class="fr">
								<div class="preview logoview"><img class="w80 h80" src="${adminImgPath}/greetingcard/greetcard_plugin.jpg" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="logoUrl"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传LOGO</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">链接地址：</strong>
							<div class="fr">
								<p>
									<input class="w420" type="text" name="shareLink" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd"/>
									<b>不填写则不跳转</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">活动时间：</strong>
							<div class="fr">
								<input type="text" name="activityTime" class="daterangepick w320 verify_time" value="" />
								<b>请勿超过模版有效期限</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row" id="attention_limit" style="margin-bottom:0px;">
							<strong class="fl">关注门槛：</strong>
							<div class="fr">
								<p>
									<label><input type="radio" checked="checked" name="bUrlType" value="0"/>参与活动</label>&nbsp;&nbsp;
									<label><input type="radio"  name="bUrlType" value="1"/>无</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row" id="followDiv">
							<div class="space_10"></div>
								<strong class="fl">引导关注链接：</strong>
								<div class="fr">
									<p>
										<input name="followUrl" class="verify_contact1 w420" type="text" value="" /><br />
										<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“贺卡”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
									</p>
								</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="row">
						<div class="fr">
							<p>
								<input type="submit" value="提 交"/>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<input type="hidden" name="userPluginId" value="${userPluginId}"/>
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
		$('.verify_name').blur(function(){
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
	});

	function verify_submit(){
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
					<strong class="fl">引导关注链接：</strong>\
						<div class="fr">\
							<p>\
								<input name="followUrl" class="verify_contact1 w420" type="text" value="" /><br />\
								<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“贺卡”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>


