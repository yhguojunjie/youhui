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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/microscene.css?v=2"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css?v=2"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js?v=2"></script>
<script type="text/javascript" src="${adminJsPath}/microscene.js?v=2"></script>
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
	                clickFn: function(url, title, width, height, border, align) {
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
		$('.microscene .screen').each(function(){
	        $(this).addscreen_json();
	    });
		var iteminfo = $('.cont_config').serializeObject();
		$('#content').val(iteminfo);
	}
</script>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 microscene">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<span class="active">活动设置</span>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/magazine/edit_do" method="post"  onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">微场景名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_name" type="text" name="name" value="${magazineconf.name }" maxlength="30" placeholder="微信营销互动模版平台" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">活动图标：</strong>
							<div class="fr">
								<div class="preview">
								<c:choose>
									<c:when test="${magazineconf.shareImgUrl != null && !empty(magazineconf.shareImgUrl)}">
										<img class="w80 h80" src="${resUrl}/${magazineconf.shareImgUrl}" />
									</c:when>
									<c:otherwise>
										<img class="w80 h80" src="${adminImgPath}/magazine/logo.jpg" />
									</c:otherwise>
									</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value="${magazineconf.shareImgUrl}"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_shareTitle" maxlength="30" type="text" name="shareTitle" value="${magazineconf.shareTitle}" placeholder="标题" />
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea class="w300 verify_shareDescription" maxlength="36" name="shareDescription" placeholder="简介">${magazineconf.shareDescription}</textarea>
									<b>发送给朋友会显示简介，分享朋友圈不会显示简介</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<input class="w420 verify_shareLink" type="text" name="shareLink" value="${magazineconf.shareLink}" placeholder="分享成功后自动跳转链接" />
									<b>不填写则不跳转</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">活动时间：</strong>
							<div class="fr">
								<input type="text" name="activityTime" class="daterangepick w320 verify_time" value="<fmt:formatDate value='${magazineconf.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /> - <fmt:formatDate value='${magazineconf.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>内容设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<!-- 画面一 开始 -->
						<!-- 画面一 结束 -->
						<!-- ---------------------------------------------------------------------------------- -->
						<c:forEach var="list" items="${contentlist}" varStatus="s">
						<div class="screen">
							<h4 class="screen_title"><strong>画面${s.count}</strong><em class="close">╳</em></h4>
							<div class="cont_style">
								<strong class="fl">样式：</strong>
								<div class="fr">
									<p>
										<select class="w200">
											<c:if test="${s.index == 0}">
												<option value="3" <c:if test="${list.pic_type == 3}">selected</c:if> >刮卡图片式</option>
											</c:if>
											<option value="0" <c:if test="${list.pic_type == 0}">selected</c:if> >纯图片式</option>
											<option value="1" <c:if test="${list.pic_type == 1}">selected</c:if> >带按钮图片式</option>
											<option value="2" <c:if test="${list.pic_type == 2}">selected</c:if> >带视频链接式</option>
										</select>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="tab_cont">
								<c:choose>
								<c:when test="${list.pic_type == 0}">
								<!-- -------------------------------------------------------------------------------------- -->
								<div class="style_image hide" <c:if test="${list.pic_type == 0}">id="show"</c:if>>
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_button hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass"/>
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">按钮：</strong>
										<div class="fr">
											<div class="preview"><img class="btn w150 h50" src="${adminImgPath}/magazine/default_btn.png" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span>
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮位置：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="top">靠上</option>
													<option value="middle">中间</option>
													<option value="bottom">靠下</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">图文外链类型：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="0" selected="selected">链接</option>
													<option value="1">电话</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">页面URL：</strong>
										<div class="fr">
											<p>
												<input class="w320" type="text" />
												<b>必填，请您填写正确的URL格式。</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_video hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview"><img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮样式：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="ico_video">绿色特效播放按钮</option>
													<option value="ico_video1">圆形播放按钮</option>
													<option value="ico_video2">长方形播放按钮</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">视频链接地址：</strong>
										<div class="fr">
											<p>
												<input class="w420" type="text"/>
												<b>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_card hide">
									<div class="row">
										<strong class="fl space_10">覆盖图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_cover}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_cover.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_cover}"/>
												<span><em class="ico_card"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">底图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<!-- -------------------------------------------------------------------------------------- -->
								</c:when>
								<c:when test="${list.pic_type == 1}">
								<!-- -------------------------------------------------------------------------------------- -->
								<div class="style_image hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_button hide" <c:if test="${list.pic_type == 1}">id="show"</c:if>>
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">按钮：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_btn != null && !empty(list.pic_btn)}">
													<img class="imghead" src="${resUrl}/${list.pic_btn}" />
												</c:when>
												<c:otherwise>
													<img class=" w140 h40 btn" src="${adminImgPath}/magazine/default_btn.png" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_btn}"/>
												<span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span>
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮位置：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="top" <c:if test="${list.btn_pos == 'top'}">selected</c:if>>靠上</option>
													<option value="middle" <c:if test="${list.btn_pos == 'middle'}">selected</c:if>>中间</option>
													<option value="bottom" <c:if test="${list.btn_pos == 'bottom'}">selected</c:if>>靠下</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">图文外链类型：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="0" <c:if test="${list.btn_type == 0}">selected</c:if>>链接</option>
													<option value="1" <c:if test="${list.btn_type == 1}">selected</c:if>>电话</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">页面URL：</strong>
										<div class="fr">
											<p>
												<input class="w320" type="text" value="${list.btn_url}"/>
												<b>必填，请您填写正确的URL格式。</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_video hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview"><img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮样式：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="ico_video">绿色特效播放按钮</option>
													<option value="ico_video1">圆形播放按钮</option>
													<option value="ico_video2">长方形播放按钮</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">视频链接地址：</strong>
										<div class="fr">
											<p>
												<input class="w420" type="text"/>
												<b>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_card hide">
									<div class="row">
										<strong class="fl space_10">覆盖图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_cover}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_cover.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_cover}"/>
												<span><em class="ico_card"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">底图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<!-- -------------------------------------------------------------------------------------- -->
								</c:when>
								<c:when test="${list.pic_type == 2}">
								<!-- -------------------------------------------------------------------------------------- -->
								<div class="style_image hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_button hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass"/>
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">按钮：</strong>
										<div class="fr">
											<div class="preview"><img class="w140 h40 btn" src="${adminImgPath}/magazine/default_btn.png" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span>
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮位置：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="top">靠上</option>
													<option value="middle">中间</option>
													<option value="bottom">靠下</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">图文外链类型：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="0" selected="selected">链接</option>
													<option value="1">电话</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">页面URL：</strong>
										<div class="fr">
											<p>
												<input class="w320" type="text" />
												<b>必填，请您填写正确的URL格式。</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_video hide" <c:if test="${list.pic_type == 2}">id="show"</c:if>>
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮样式：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="ico_video" <c:if test="${list.video_btn_type == 'ico_video'}">selected</c:if>>绿色特效播放按钮</option>
													<option value="ico_video1" <c:if test="${list.video_btn_type == 'ico_video1'}">selected</c:if>>圆形播放按钮</option>
													<option value="ico_video2" <c:if test="${list.video_btn_type == 'ico_video2'}">selected</c:if>>长方形播放按钮</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">视频链接地址：</strong>
										<div class="fr">
											<p>
												<input class="w420" type="text" value="${list.video_url}"/>
												<b>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_card hide">
									<div class="row">
										<strong class="fl space_10">覆盖图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_cover}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_cover.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_cover}"/>
												<span><em class="ico_card"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">底图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<!-- -------------------------------------------------------------------------------------- -->
								</c:when>
								<c:when test="${list.pic_type == 3}">
								<!-- -------------------------------------------------------------------------------------- -->
								<div class="style_image hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview"><img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass"/>
												
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_button hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview"><img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">按钮：</strong>
										<div class="fr">
											<div class="preview"><img class="w140 h40 btn" src="${adminImgPath}/magazine/default_btn.png" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span>
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮位置：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="top">靠上</option>
													<option value="middle">中间</option>
													<option value="bottom">靠下</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">图文外链类型：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="0" selected="selected">链接</option>
													<option value="1">电话</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">页面URL：</strong>
										<div class="fr">
											<p>
												<input class="w320" type="text" />
												<b>必填，请您填写正确的URL格式。</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_video hide">
									<div class="row">
										<strong class="fl space_10">内容：</strong>
										<div class="fr">
											<div class="preview"><img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">按钮样式：</strong>
										<div class="fr">
											<p>
												<select class="w200">
													<option value="ico_video">绿色特效播放按钮</option>
													<option value="ico_video1">圆形播放按钮</option>
													<option value="ico_video2">长方形播放按钮</option>
												</select>
											</p>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">视频链接地址：</strong>
										<div class="fr">
											<p>
												<input class="w420" type="text"/>
												<b>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</b>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="style_card hide" <c:if test="${list.pic_type == 3}">id="show"</c:if> >
									<div class="row">
										<strong class="fl space_10">覆盖图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_cover != null && !empty(list.pic_cover)}">
													<img class="imghead" src="${resUrl}/${list.pic_cover}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_cover.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_cover}"/>
												<span><em class="ico_card"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl space_10">底图：</strong>
										<div class="fr">
											<div class="preview">
												<c:choose>
												<c:when test="${list.pic_base != null && !empty(list.pic_base)}">
													<img class="imghead" src="${resUrl}/${list.pic_base}" />
												</c:when>
												<c:otherwise>
													<img class="imghead" src="${adminImgPath}/magazine/default_bg.jpg" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.pic_base}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<!-- -------------------------------------------------------------------------------------- -->
								</c:when>
								</c:choose>
							</div>
						<input type="hidden" value="" class="screen_json"/>
						</div>
						</c:forEach>
						<!-- ------------------------------------------------------------------------------ -->
						<div class="add_screen">新增更多画面</div>
					</div>
					<input type="hidden" name="content" id="content" value="${magazineconf.content}"/>
					<h3><strong>场景设置</strong><span class="fold">展开</span></h3>
					<div class="scene_config">
						<div class="row">
							<strong class="fl">微场景效果：</strong>
							<div class="fr">
								<p>
									<select class="w200" name="effect">
										<option value="0" <c:if test="${magazineconf.effect == 0}">selected</c:if> >上下滑动</option>
										<option value="1" <c:if test="${magazineconf.effect == 1}">selected</c:if> >左右滑动</option>
									</select>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">背景音乐：</strong>
							<div class="fr">
								<p class="tab_music">
									<label class="active"><input type="radio" name="bgMusic" value="0" <c:if test="${magazineconf.bgMusic == 0}">checked</c:if>/>开启</label>
									<label><input type="radio" name="bgMusic" value="1" <c:if test="${magazineconf.bgMusic == 1}">checked</c:if>/>关闭</label>
								</p>
								<div class="music_upcont" <c:if test="${magazineconf.bgMusic == 1}">style="display:none;"</c:if>>
									<c:choose>
										<c:when test="${magazineconf.bgMusicUrl != null && !empty(magazineconf.bgMusicUrl)}">
											<audio id="bgmusic" controls="controls" style="vertical-align:-8px;" src="${resUrl}/${magazineconf.bgMusicUrl}"><i id="bgmusic_name">default.mp3</i></audio>
										</c:when>
										<c:otherwise>
											<audio id="bgmusic" controls="controls" style="vertical-align:-8px;" src="${soundPath}/magazine/default.mp3"><i id="bgmusic_name">default.mp3</i></audio>
										</c:otherwise>
									</c:choose>
									<div class="upload_file">
										<input id="bgmusic_obj" type="text" name="bgMusicUrl" value="${magazineconf.bgMusicUrl}"/>
										<span><em class="ico_audio"><!-- 图标 --></em>点击上传音乐</span>
									</div>
									<b>为保证浏览网页的加载速度，上传音乐最大为1MB</b>

								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">音乐图标及效果：</strong>
							<div class="fr">
								<p>
									<select class="w200" name="iconEffect">
										<option value="0" <c:if test="${magazineconf.iconEffect == 0}">selected</c:if>>音乐旋转效果</option>
										<option value="1" <c:if test="${magazineconf.iconEffect == 1}">selected</c:if>>音阶效果</option>
									</select>
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
					<input type="hidden" name="id" value="${magazineconf.id}"/>
					<input type="hidden" name="activityId" value="${magazineconf.activityId}"/>
					<input type="hidden" name="createTime" value="<fmt:formatDate value='${magazineconf.createTime}' pattern='yyyy-MM-dd HH:mm:ss' />"/>
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
		$('.verify_shareTitle').blur(function(){
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
		$('.verify_shareDescription').blur(function(){
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
			$('.microscene .main .cont .cont_config,.microscene .main .cont .scene_config').attr("style","display:block;");
			$('.microscene .main .cont h3 span').text("收起");
			alert("请核查全部字段是否正确填写");
			return false;
		}else{
			return true;
		}
	}
</script>


