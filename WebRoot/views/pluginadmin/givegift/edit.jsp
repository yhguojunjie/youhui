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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/christmas.css?v=2"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${adminJsPath}/christmas.js"></script>
<script type="text/javascript" src="${adminJsPath}/param.js"></script>
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
	function onClick(){
		if(!verify_submit()){
			return false;
		}

		$('.christmas .screen').each(function(){
	        addscreen_json($(this));
	    });

		var iteminfo = $('.cont_config').serializeObject();
		$('#setContent').val(iteminfo);
	}
</script>
<title>模版设置</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 christmas">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a class="active">活动设置</a>
				<a href="${path}/pluginadmin/givegift/record/${giftconf.activityId}">中奖名单</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/givegift/edit_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_title" type="text" maxlength="30" name="title" value="${giftconf.title}" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">活动图标：</strong>
							<div class="fr">
								<div class="preview">
								<c:choose>
									<c:when test="${giftconf.shareImgUrl != null && !empty(giftconf.shareImgUrl)}">
										<img class="w80 h80" src="${resUrl}/${giftconf.shareImgUrl}" />
									</c:when>
									<c:otherwise>
										<img class="w80 h80" src="${adminImgPath}/givegift/avatar.gif" />
									</c:otherwise>
									</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value="${giftconf.shareImgUrl}"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">商家LOGO：</strong>
							<div class="fr">
								<div class="preview logoview" style="width:250px;height:60px;">
									<c:choose>
									<c:when test="${giftconf.advertImgUrl != null && !empty(giftconf.advertImgUrl)}">
										<img style="width:250px;height:60px;" src="${resUrl}/${giftconf.advertImgUrl}" />
									</c:when>
									<c:otherwise>
										<img style="width:250px;height:60px;" src="${adminImgPath}/givegift/logo.png" />
									</c:otherwise>
									</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="advertImgUrl" value="${giftconf.advertImgUrl}"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传LOGO</span>
								</div>
								<b>建议尺寸：600*240（或等比），大小不超过50K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">链接地址：</strong>
							<div class="fr">
								<p>
									<input class="w420 verify_url" type="text" name="advertUrl" value="${giftconf.advertUrl}" />
									<b>不填写则不跳转</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_shareTitle" type="text" placeholder="标题" name="shareTitle" maxlength="30" value="${giftconf.shareTitle}"/>
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea class="w300 verify_shareDescription" maxlength="36" placeholder="简介" name="shareDescription">${giftconf.shareDescription}</textarea>
									<b>发送给朋友会显示简介，分享朋友圈不会显示简介</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">活动时间：</strong>
							<div class="fr">
								<input type="text" name="activityTime" class="daterangepick w320 verify_time" value="<fmt:formatDate value='${giftconf.startTime}' pattern='yyyy-MM-dd HH:mm:ss' /> - <fmt:formatDate value='${giftconf.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />" />
								<b>请勿超过模版有效期限</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row"  id="attention_limit" style="margin-bottom:0px;">
							<strong class="fl w220">关注门槛：</strong>
							<div class="fr w560">
								<p>
									<label><input type="radio" name="bUrlType" value="0" <c:if test="${giftconf.bUrlType == '0'}">checked</c:if>/>参与活动</label>&nbsp;&nbsp;
									<label><input type="radio"  name="bUrlType" value="1" <c:if test="${giftconf.bUrlType == null || giftconf.bUrlType == '1'}">checked</c:if>/>无</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<c:choose>
							<c:when test="${giftconf.bUrlType == '0'}">
							<div class="row" id="followDiv">
								<div class="space_10"></div>
									<strong class="fl w220">引导关注链接：</strong>
									<div class="fr w560">
										<p>
											<input name="followUrl" class="verify_contact1 w420" type="text" value="${giftconf.followUrl}" /><br />
											<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“心愿”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
										</p>
									</div>
								<div class="clear"></div>
							</div>
							</c:when>
							<c:otherwise>
								<div class="row" id="followDiv"></div>
							</c:otherwise>
						</c:choose>
					</div>
					<h3><strong>礼物设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<b style="margin-left:50px">只用于微信好友之间互赠礼物的展示，不真实赠送，建议设置与品牌相关的产品</b>
						<!-- 礼物一 开始 -->
						<c:forEach var="list" items="${contentlist}" varStatus="s">
						<div class="screen">
							<h4 class="screen_title"><strong>礼物${s.count}</strong><c:if test="${s.count>1}"><em class="close">╳</em></c:if></h4>
							<div class="tab_cont">
								<div class="style_image">
									<div class="row">
										<strong class="fl space_10">礼物图片：</strong>
										<div class="fr">
											<div class="preview giftview">
												<c:choose>
												<c:when test="${list.picUrl != null && !empty(list.picUrl)}">
													<img src="${resUrl}/${list.picUrl}" />
												</c:when>
												<c:when test="${s.count < 5}">
													<img src="${adminImgPath}/givegift/${s.count}.gif" />
												</c:when>
												<c:otherwise>
													<img src="${commonadminImgPath}/gift.png" />
												</c:otherwise>
												</c:choose>
											</div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" value="${list.picUrl}"/>
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">礼物标题：</strong>
										<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" value="${list.title}"/>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">描述文案：</strong>
										<div class="fr">
											<textarea class="w300 verify_intro" placeholder="简介">${list.desc}</textarea>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						</c:forEach>
						<!-- 礼物一 结束 -->
						<div class="add_screen">新增更多礼物</div>
					</div>
					<input type="hidden" name="setContent" id="setContent" value="${giftconf.setContent}"/>
					<h3><strong>优惠券设置</strong><span class="fold">展开</span></h3>
					<div class="scene_config">
						<b style="margin-left:50px;">首次成功转发赠送</b>
						<div class="space_30"></div>
						<div class="row">
							<strong class="fl">优惠券名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_couponName" type="text" maxlength="30" name="couponName" value="${giftconf.couponName}"/>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">使用规则：</strong>
							<div class="fr">
								<textarea class="w300 verify_rule" name="rule">${giftconf.rule}</textarea>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">是否需到店兑奖：</strong>
							<div class="fr w560">
								<p>
									<label><input class="act_ticket" type="radio" name="bExchange" value="0" <c:if test="${giftconf.bExchange == '0'}">checked</c:if>/>是</label>&nbsp;&nbsp;
									<label><input type="radio" name="bExchange" value="1" <c:if test="${giftconf.bExchange == null || giftconf.bExchange == '1'}">checked</c:if>/>否</label>
									<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
								<strong class="fl w220">背景图：</strong>
								<div class="fr w560">
									<div class="preview">
										<c:choose>
											<c:when test="${giftconf.bgUrl != null && giftconf.bgUrl.length() !=0}">
												<img class="w160 h240" src="${resUrl}/${giftconf.bgUrl}" />
											</c:when>
											<c:otherwise>  
												<img class="w160 h240" src="${adminImgPath}/givegift/bg.jpg" />
											</c:otherwise>
										</c:choose>
									</div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" name="bgUrl" value="${giftconf.bgUrl}"/>
										<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
									</div>
									<b>建议尺寸：640*1120（或等比），大小不超过50K。</b>
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
					<input type="hidden" name="id" value="${giftconf.id}"/>
					<input type="hidden" name="activityId" value="${giftconf.activityId}"/>
					<input type="hidden" name="createTime" value="<fmt:formatDate value='${giftconf.createTime}' pattern='yyyy-MM-dd HH:mm:ss' />"/>
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
		$('.verify_title').blur(function(){
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
		$('.verify_gifttitle').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入礼物标题");
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
				insertNotice($(this),"请输入分享内容");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$(document).on("blur",'.verify_intro',function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入简介");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"");
				$(this).attr("verify","true");
				return true;
			}
		});
		$('.verify_couponName').blur(function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入优惠券名称");
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
				insertNotice($(this),"请输入使用规则");
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
		$('.verify_url').blur(function(){
			if($(this).val()){
				if(!verify($(this).val(),'url')){
				insertNotice($(this),"请输入正确的URL格式");
				$(this).attr("verify","false");
				return false;
			}else{
				insertNotice($(this),"不填写则不跳转");
				$(this).attr("verify","true");
				return true;
			}
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
			$('.christmas .main .cont .cont_config,.christmas .main .cont .scene_config').attr("style","display:block;");
			$('.christmas .main .cont h3 span').text("收起");
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
								<input name="followUrl" class="verify_contact1 w420" type="text" value="${giftconf.followUrl}" /><br />\
								<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“心愿”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>




