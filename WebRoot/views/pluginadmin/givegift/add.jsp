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
				<a href="${path}/pluginadmin/givegift/record/0?upid=${userPluginId}">中奖名单</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/givegift/add_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_title" type="text" maxlength="30" name="title" value="魔力星愿店" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_10">活动图标：</strong>
							<div class="fr">
								<div class="preview"><img class="w80 h80" src="${adminImgPath}/givegift/avatar.gif"/></div>
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
								<div class="preview logoview" style="width:250px;height:60px;"><img style="width:250px;height:60px;" src="${adminImgPath}/givegift/logo.png" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="advertImgUrl"/>
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
									<input class="w420 verify_url" type="text" name="advertUrl" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd"/>
									<b>不填写则不跳转</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_shareTitle" type="text" value="为你挑选了1个神秘圣诞礼物" placeholder="标题" name="shareTitle" maxlength="30" />
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea class="w300 verify_shareDescription" maxlength="36" placeholder="简介" name="shareDescription">快来“魔力星愿店”，为你的好友挑选一款圣诞礼物，真的有魔力哦~</textarea>
									<b>发送给朋友会显示简介，分享朋友圈不会显示简介</b>
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
						<div class="row"  id="attention_limit" style="margin-bottom:0px;">
							<strong class="fl w220">关注门槛：</strong>
							<div class="fr w560">
								<p>
									<label><input type="radio" checked="checked" name="bUrlType" value="0"/>参与活动</label>&nbsp;&nbsp;
									<label><input type="radio"  name="bUrlType" value="1"/>无</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row" id="followDiv">
							<div class="space_10"></div>
								<strong class="fl w220">引导关注链接：</strong>
								<div class="fr w560">
									<p>
										<input name="followUrl" class="verify_contact1 w420" type="text" value="" /><br />
										<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“心愿”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
									</p>
								</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>礼物设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<b style="margin-left:50px">只用于微信好友之间互赠礼物的展示，不真实赠送，建议设置与品牌相关的产品</b>
						<!-- 礼物一 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>礼物1</strong></h4>
							<div class="tab_cont">
								<div class="style_image">
									<div class="row">
										<strong class="fl space_10">礼物图片：</strong>
										<div class="fr">
											<div class="preview giftview"><img src="${adminImgPath}/givegift/1.gif" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">礼物标题：</strong>
										<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" value="蔓越莓蛋糕(送密友)" />
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">描述文案：</strong>
										<div class="fr">
											<textarea class="w300 verify_intro" placeholder="简介">两把小勺一块蛋糕，与你一同回味那些夹杂于舌尖上的叨叨时光。</textarea>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 礼物一 结束 -->
						<!-- 礼物二 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>礼物2</strong></h4>
							<div class="tab_cont">
								<div class="style_image">
									<div class="row">
										<strong class="fl space_10">礼物图片：</strong>
										<div class="fr">
											<div class="preview giftview"><img src="${adminImgPath}/givegift/2.gif" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">礼物标题：</strong>
										<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" value="太妃榛果拿铁(送同事)" />
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">描述文案：</strong>
										<div class="fr">
											<textarea class="w300 verify_intro" placeholder="简介">热气散发馥郁深厚，温暖一起奋斗的寒夜，漫溢相知相伴的回忆。</textarea>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 礼物二 结束 -->
						<!-- 礼物三 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>礼物3</strong></h4>
							<div class="tab_cont">
								<div class="style_image">
									<div class="row">
										<strong class="fl space_10">礼物图片：</strong>
										<div class="fr">
											<div class="preview giftview"><img src="${adminImgPath}/givegift/3.gif" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">礼物标题：</strong>
										<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" value="咖啡储存罐(送恋人)" />
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">描述文案：</strong>
										<div class="fr">
											<textarea class="w300 verify_intro" placeholder="简介">原木密封盖，愿你我的爱如咖啡香气永远封存在这时间存储罐里。</textarea>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 礼物三 结束 -->
						<!-- 礼物四 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>礼物4</strong></h4>
							<div class="tab_cont">
								<div class="style_image">
									<div class="row">
										<strong class="fl space_10">礼物图片：</strong>
										<div class="fr">
											<div class="preview giftview"><img src="${adminImgPath}/givegift/4.gif" /></div>
											<div class="upload_file">
												<input type="text" class="fileuploadclass" />
												<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>
											</div>
											<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">礼物标题：</strong>
										<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" value="圣诞综合咖啡豆(送长辈)" />
										</div>
										<div class="clear"></div>
									</div>
									<div class="row">
										<strong class="fl">描述文案：</strong>
										<div class="fr">
											<textarea class="w300 verify_intro" placeholder="简介">咖啡豆历经烘焙才能成就百香浸润，犹如您对我的教导深邃甘醇。</textarea>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 礼物四 结束 -->
						<div class="add_screen">新增更多礼物</div>
					</div>
					<input type="hidden" name="setContent" id="setContent" value=""/>
					<h3><strong>优惠券设置</strong><span class="fold">展开</span></h3>
					<div class="scene_config">
						<b style="margin-left:50px;">首次成功转发赠送</b>
						<div class="space_30"></div>
						<div class="row">
							<strong class="fl">优惠券名称：</strong>
							<div class="fr">
								<p>
									<input class="w300 verify_couponName" type="text" maxlength="30" name="couponName" value="星巴克10元抵用券"/>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">使用规则：</strong>
							<div class="fr">
								<textarea class="w430 h120 verify_rule" name="rule">活动期间（2014/12/1-2014/12/25），携本页面到店购买商品，消费满50元即立减10元（模版演示，纯属体验，不予兑奖！）</textarea>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">是否需到店兑奖：</strong>
							<div class="fr w560">
								<p>
									<label><input class="act_ticket" type="radio" name="bExchange" value="0"/>是</label>&nbsp;&nbsp;
									<label><input type="radio" checked="checked" name="bExchange" value="1" />否</label>
									<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">背景图：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/givegift/bg.jpg" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="bgUrl"/>
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
								<input name="followUrl" class="verify_contact1 w420" type="text" value="" /><br />\
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


