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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/takepic.css" />
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${adminJsPath}/param.js"></script>
<script type="text/javascript" src="${adminJsPath}/takepic.js"></script>
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
	<div class="w1190 takepic">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a href="#" class="active">活动设置</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/takepic/add_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input class="verify_name w300" type="text" name="name" maxlength="30" value="拍大白" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_15">活动图标：</strong>
							<div class="fr">
								<div class="preview"><img class="w80 h80" src="${adminImgPath}/takepic/logo.png" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl"/>
									<span><em class="ico_image"><!-- 图标 --></em>上传图片</span>
								</div>
								<b>建议尺寸：100*100，大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<!-- <div class="row">
							<strong class="fl">插入广告：</strong>
							<div class="fr">
								<p class="tab_ad">
									<label class="active"><input type="radio" checked="checked" name="bAdvert" value="0" />开启</label>
									<label><input type="radio" name="bAdvert" value="1"/>关闭</label>
								</p>
								<div class="ad_upcont space_10">
									<div class="preview"><img class="w320" src="${adminImgPath}/ad.gif" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" name="advertImgUrl"/>
										<span><em class="ico_image"></em>上传广告图</span>
									</div>
									<b>建议尺寸：640*100（或等比），大小不超过50K。</b>
									<p>
										<input class="w420" type="text" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd" name="advertUrl"/>
									</p>
								</div>
							</div>
							<div class="clear"></div>
						</div> -->
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input class="verify_title w300" type="text" maxlength="25" placeholder="标题" value="我在拍大白，赢大奖得了n分，快来PK吧！" name="shareTitle"/>
									<b>发送给朋友、分享朋友圈，均会显示标题（n代表分数）</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea class="verify_intro w300" maxlength="36" placeholder="简介" name="shareDescription">万元好礼送不停！</textarea>
									<b>发送给朋友会显示简介，分享朋友圈不会显示简介</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">活动时间：</strong>
							<div class="fr">
								<div class="control-group">
									<div class="controls">
										<div class="fr">
											<input type="text" name="activityTime" class="daterangepick w320 verify_time" value="" />
											<b>请勿超过模版有效期限</b>
										</div>
									</div>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>活动设置</strong><span class="fold">展开</span></h3>
					<div class="act_config">
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
										<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“拍大白”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
									</p>
								</div>
							<div class="clear"></div>
						</div>
						<!-- <div class="row">
							<strong class="fl w270">每人每天最多游戏次数：</strong>
							<div class="fr w510">
								<p>
									<input class="act_count verify_num w80" type="text" value="1" name="playTimes">
									<b>大于0，游戏玩第二次时强制要求分享</b>
								</p>
							</div>
							<div class="clear"></div>
						</div> -->
						<div class="row">
							<strong class="fl">活动详情链接：</strong>
							<div class="fr">
								<p>
									<input class="verify_contact2 w420" type="text" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd" name="actUrl">
									<b></b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>自定义风格</strong><span class="fold">展开</span></h3>
					<div class="custom_config">
						<div class="row">
							<strong class="fl w220">首页宣传图：</strong>
							<div class="fr w560">
								<div class="preview"><img style="background:#eee;" class="w320 h200" src="${adminImgPath}/takepic/img.png"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="coverUrl" >
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：640*400（或等比），大小不超过50K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">普通得分图：</strong>
							<div class="fr w560">
								<div class="preview"><img style="background:#eee;" class="w120 h120" src="${adminImgPath}/takepic/phone-0.png"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="normalUrl" >
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：200*200（或等比）,PNG格式，大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">双倍得分图：</strong>
							<div class="fr w560">
								<div class="preview"><img style="background:#eee;" class="w120 h120" src="${adminImgPath}/takepic/phone-1.png"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="doubleUrl" >
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：200*200（或等比）,PNG格式，大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">背景：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/takepic/bg.jpg"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="bgUrl" >
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：640*1200（或等比），大小不超过80K。</b>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="row">
						<div class="fr w560">
							<p>
								<input type="submit" value="提 交" />
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
		$(document).on("blur",".verify_contact2",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入活动详情链接");
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
			$('.takepic .main .cont .cont_config,.takepic .main .cont .act_config,.takepic .main .cont .custom_config').attr("style","display:block;");	
			$('.takepic .main .cont h3 span').text("收起");
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
								<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“拍大白”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>