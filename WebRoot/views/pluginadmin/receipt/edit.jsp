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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/receipt.css"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js"></script>
<script type="text/javascript" src="${adminJsPath}/receipt.js"></script>
<script type="text/javascript" src="${adminJsPath}/param.js"></script>
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
					$('.need_usermsg input[type="text"]').attr("style","display:inline-block;");
				}else{
					$('.need_usermsg input[type="text"]').attr("style","display:none;");
					$('.need_usermsg input[type="text"]').val('');
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
			}
		});
		if($('.need_usermsg input[type="text"]').val().length != 0){
			str += $('.need_usermsg input[type="text"]').val();
		}else{
			str=str.replace("other:","");
		}
		$('#userinfo').val(str);

		$('.receipt .screen').each(function(){
	        addscreen_json($(this));
	    });
	    
		var iteminfo = $('.cont_config').serializeObject();
		$('#awards').val(iteminfo);
	}
</script>
<title>配置页面</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 receipt">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a href="#" class="active">活动设置</a>
				<a href="${path}/pluginadmin/receipt/record/${conf.activityId}">中奖名单</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/receipt/edit_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input name="name" class="verify_name w300" type="text" maxlength="30" value="${conf.name}" />
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
										<img class="w80 h80" src="${adminImgPath}/receipt/logo.png" />
									</c:otherwise>
									</c:choose>
								</div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value="${conf.shareImgUrl}"/>
									<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">插入广告：</strong>
							<div class="fr">
								<p class="tab_ad">
									<label class="active"><input type="radio" name="bAdvert" value="0" <c:if test="${conf.bAdvert == '0'}">checked</c:if>/>开启</label>
									<label><input type="radio" name="bAdvert" value="1" <c:if test="${conf.bAdvert == '1'}">checked</c:if>/>关闭</label>
								</p>
								<div class="ad_upcont space_10">
									<div class="preview">
									<c:choose>
										<c:when test="${conf.advertImgUrl != null && conf.advertImgUrl.length() !=0}">
											<img class="w320 h70 imghead" src="${resUrl}/${conf.advertImgUrl}" />
										</c:when>
										<c:otherwise>  
											<img class="w320 h70 imghead" src="${adminImgPath}/ad.png" />
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
									<input name="shareTitle" class="verify_title w300" type="text" maxlength="200" value="${conf.shareTitle}" />
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea name="shareDescription" class="verify_intro w300" maxlength="36" placeholder="简介">${conf.shareDescription}</textarea>
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
					<h3><strong>奖项设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<c:forEach var="list" items="${awardslist}" varStatus="s">
						<div class="screen" data-id="${s.count}">
							<h4 class="screen_title"><strong>${s.count}等奖</strong><c:if test="${s.count>1}"><em class="close">╳</em></c:if></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50"  value="${list.prizeName}"/>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="${list.realNum}"/>
										<b>奖品发完后，不会再有用户得奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">中奖概率：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80 probability" type="text" value="${list.probability}" />
										<b>0~100，用户获得该奖品的概率，独立设置，与其他奖品不相关</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl w220">是否需到店兑奖：</strong>
								<div class="fr w560">
									<p>
										<label><input class="act_ticket" type="radio" name="${s.count}" value="0" <c:if test="${list.exchangeState == '0'}">checked</c:if>/>是</label>&nbsp;&nbsp;
										<label><input type="radio" name="${s.count}" value="1" <c:if test="${list.exchangeState == null || list.exchangeState == '1'}">checked</c:if>/>否</label>
										<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						</c:forEach>
						<div class="add_screen">新增更多奖品等级</div>
					</div>
					<input type="hidden" name="awards" id="awards" value=""/>
					<h3><strong>活动设置</strong><span class="fold">展开</span></h3>
					<div class="act_config">
						<div class="row">
							<strong class="fl w220">商家名称：</strong>
							<div class="fr w560">
								<p>
									<input name="sellerName" class="verify_seller w300" type="text" maxlength="30" value="${conf.sellerName}">
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row"  id="attention_limit" style="margin-bottom:0px;">
							<strong class="fl w220">关注门槛：</strong>
							<div class="fr w560">
								<p>
									<label><input type="radio" name="bUrlType" value="0" <c:if test="${conf.bUrlType == '0'}">checked</c:if>/>参与活动</label>&nbsp;&nbsp;
									<label><input type="radio"  name="bUrlType" value="1" <c:if test="${conf.bUrlType == null || conf.bUrlType == '1'}">checked</c:if>/>无</label>
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
											<b>参与用户要查看领奖页，会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“小鸟”，公众号将回复排行榜页链接地址（在PC端活动配置排行榜栏目获取)。</b>
										</p>
									</div>
								<div class="clear"></div>
							</div>
							</c:when>
							<c:otherwise>
								<div class="row" id="followDiv"></div>
							</c:otherwise>
						</c:choose>
						<div id="get_password" class="row" style="margin-bottom:10px;">
							<strong class="fl w270">是否需输入领取密码：</strong>
							<div class="fr w510">
								<p>
									<label><input type="radio" name="bInputPass" value="0" <c:if test="${conf.bInputPass == '0'}">checked</c:if>/>是</label>&nbsp;&nbsp;
									<label><input class="cant_join" type="radio" name="bInputPass" value="1" <c:if test="${conf.bInputPass == '1'}">checked</c:if>/>否</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div id="admin_password" class="row" <c:choose><c:when test="${conf.bInputPass == '0'}">style="display:block;"</c:when><c:otherwise>style="display:none;"</c:otherwise></c:choose>>
							<strong class="fl w270">管理员密码：</strong>
							<div class="fr w510">
								<p>
									<input id="password" name="password" class=" w300" type="text" maxlength="30" value="${conf.password}">
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div id="play_time" class="row" <c:choose><c:when test="${conf.bInputPass == '1'}">style="display:block;"</c:when><c:otherwise>style="display:none;"</c:otherwise></c:choose>>
							<strong class="fl w270">每人每天最多游戏次数：</strong>
							<div class="fr w510">
								<p>
									<input name="playTimes" class="act_count verify_num w80" type="text" value="${conf.playTimes}" />
									<b>大于1，游戏玩第二次时强制要求分享</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w270" style="width:280px;">已中奖的用户，是否可以继续获奖：</strong>
							<div class="fr w510" style="width:500px;">
								<p>
									<label><input type="radio" name="bCanPlay" value="0" <c:if test="${conf.bCanPlay == '0'}">checked</c:if>/>是</label>&nbsp;&nbsp;
									<label><input class="cant_join" type="radio" name="bCanPlay" value="1" <c:if test="${conf.bCanPlay == '1'}">checked</c:if>/>否</label>
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
							<strong class="fl w220">兑奖说明：</strong>
							<div class="fr w560">
								<p>
									<input name="exExplain" class="verify_contact w420" type="text" value="${conf.exExplain}" maxlength="50" />
									<b>用户兑奖时显示</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">用户信息：</strong>
							<div class="fr w560">
								<p>
									<label><input type="checkbox" checked="checked" disabled="disabled" />手机号</label>
									<label><input type="checkbox" checked="checked" disabled="disabled" />姓名</label>
									<label class="need_usermsg"><input value="mail," type="checkbox" <c:if test="${conf.userinfo.contains('mail')}">checked</c:if>/>邮寄地址</label>
									<label class="need_usermsg"><input value="qq," type="checkbox" <c:if test="${conf.userinfo.contains('qq')}">checked</c:if>/>QQ</label>
									<label class="need_usermsg"><input value="wechat," type="checkbox" <c:if test="${conf.userinfo.contains('wechat')}">checked</c:if>/>微信号</label>
									<label class="need_usermsg"><input class="need_other" value="other:" type="checkbox" <c:if test="${conf.userinfo.contains('other')}">checked</c:if>/>其他</label>
									<label class="need_usermsg"><input type="text" class="other w80" value="${conf.userinfo.split('other:')[1]}" <c:if test="${conf.userinfo.contains('other')}">style="display:inline-block;"</c:if>/></label>
									<input type="hidden" name="userinfo" id="userinfo" value="" />
								</p>
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
		$(document).on("blur",".verify_adpass",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请设置管理员密码");
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
		$('.verify_contact').blur(function(){
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
		$(document).on("blur",".verify_seller",function(){
			if(!verify($(this).val(),'')){
				insertNotice($(this),"请输入商家名称");
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

	function verify_submit(){
		var verify = 0;
		$('input[type="text"]').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		if($('#get_password input[type="radio"]:checked').val() == '0' && $('#password').val().length == 0){
				insertNotice($('#password'),"请设置管理员密码");
				verify += 1;
		}
		$('textarea').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		if(verify > 0){
			$('.receipt .main .cont .cont_config,.receipt .main .cont .act_config').attr("style","display:block;");
			$('.receipt .main .cont h3 span').text("收起");
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
								<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“刮发票”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});

	$('#get_password input[type="radio"]').change(function(){
		if($(this).val() == 0){
			$('#admin_password').css("display","block");
			$('#play_time').css("display","none");
		}else{
			$('#admin_password').css("display","none");
			$('#play_time').css("display","block");
		}
	});
});
</script>