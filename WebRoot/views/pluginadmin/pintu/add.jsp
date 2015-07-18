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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/pintu.css?v=2"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css?v=2"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js?v=2"></script>
<script type="text/javascript" src="${adminJsPath}/pintu.js?v=3"></script>
<script type="text/javascript" src="${adminJsPath}/param.js?v=2"></script>
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

		var pictures = '';
		$('.imgs_config .pinimg').each(function(){
			var val = $(this).find('input').val();
			if(val.length != 0){
				pictures += val + ',';
			}
		});
		$('#pictures').val(pictures);

		$('.pintu .screen').each(function(){
	        addscreen_json($(this));
	    });
	    
		var iteminfo = $('.cont_config').serializeObject();
		$('#awards').val(iteminfo);
	}
</script>
<title>拼图</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 pintu">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a href="#" class="active">活动设置</a>
				<a href="#">中奖名单</a>
				<a href="#">排行榜</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/pintu/add_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input name="title" class="verify_name w300" type="text" maxlength="30" value="拼图闯关赢好礼" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_15">活动图标：</strong>
							<div class="fr">
								<div class="preview"><img class="w80 h80" src="${adminImgPath}/pintu/logo.png" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value="" />
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
									<label class="active"><input type="radio" checked="checked" name="bAdvert" value="0"/>开启</label>
									<label><input type="radio" name="bAdvert" value="1"/>关闭</label>
									<b>展示在排行榜页</b>
								</p>
								<div class="ad_upcont space_10">
									<div class="preview"><img class="w320 h50" src="${adminImgPath}/ad.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" name="advertImgUrl" value="" />
										<span><em class="ico_image"><!-- 图标 --></em>上传广告图</span>
									</div>
									<b>建议尺寸：640*100（或等比），大小不超过50K。</b>
									<p>
										<input name="advertUrl" class="w420" type="text" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd" />
									</p>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input name="shareTitle" class="verify_title w300" type="text" maxlength="25" placeholder="标题" value="要在n秒内完成拼图，闯关成功有礼，你行么？" />
									<b>n为商家设置的时间</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea name="shareDescription" class="verify_intro w300" maxlength="36" placeholder="简介">悄悄告诉你，没有想象中那么简单哦~不信就来试试！</textarea>
									<b>发送给朋友、分享朋友圈，均会显示标题；发送给朋友会显示简介，分享朋友圈不会显示简介</b>
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
					</div>
					<h3><strong>图库</strong><span class="fold">展开</span></h3>
					<div class="imgs_config">
						<!-- 图片1 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片1</strong></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpxylUrdsGAYHLsAADLGhiS6Vw786.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpxylUrdsGAYHLsAADLGhiS6Vw786.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片1 结束 -->
						<!-- 图片2 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片2</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpmpFUrdvyAf5rGAADYFOV8Gek674.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpmpFUrdvyAf5rGAADYFOV8Gek674.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片2 结束 -->
						<!-- 图片3 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片3</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpxylUrd06AVtb5AAB3D4QIT0I406.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpxylUrd06AVtb5AAB3D4QIT0I406.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片3 结束 -->
						<!-- 图片4 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片4</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpmpFUrd4iAIOGJAAClDS-eEIc701.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpmpFUrd4iAIOGJAAClDS-eEIc701.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片4 结束 -->
						<!-- 图片5 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片5</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpxylUrd7eAGgJWAACMNpCMD4I835.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpxylUrd7eAGgJWAACMNpCMD4I835.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片5 结束 -->
						<!-- 图片6 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片6</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpmpFUreDCAKodBAAByGHU3U24906.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpmpFUreDCAKodBAAByGHU3U24906.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片6 结束 -->
						<!-- 图片7 开始 -->
						<div class="pinimg">
							<h4 class="pinimg_title"><strong>图片7</strong><em class="close">╳</em></h4>
							<div class="row">
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${resUrl}group1/M00/00/01/cEpxylUreFqAUgJ3AABGN9cwDKE327.jpg" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="group1/M00/00/01/cEpxylUreFqAUgJ3AABGN9cwDKE327.jpg"/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：100*100（或等比），大小不超过50K。</b>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!-- 图片7 结束 -->
						<div class="add_pinimg">新增更多拼图</div>
					</div>
					<input type="hidden" name="pictures" id="pictures" value=""/>
					<h3><strong>奖项设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<div class="row">
							<strong class="fl w220">是否显示奖品的"实际数量"：</strong>
							<div class="fr w560">
								<p>
									<label class="active"><input type="radio" checked="checked" name="bShowNum" value="0"/>是</label>&nbsp;&nbsp;
									<label><input type="radio" name="bShowNum" value="1"/>否</label>
									<b>于活动排行榜页面显示奖品数量</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<!-- 一等奖 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>1等奖</strong></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="CK中性香水" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="1" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${adminImgPath}/giftbox_img1.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="" />
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），大小不超过30K。</b>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl w220">是否需到店兑奖：</strong>
								<div class="fr w560">
									<p>
										<label><input class="act_ticket" type="radio" name="1" value="0"/>是</label>&nbsp;&nbsp;
										<label><input type="radio" checked="checked" name="1" value="1" />否</label>
										<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 一等奖 结束 -->
						<!-- 二等奖 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>2等奖</strong><em class="close">╳</em></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="暖风机" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="2" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${adminImgPath}/giftbox_img2.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="" />
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），大小不超过30K。</b>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl w220">是否需到店兑奖：</strong>
								<div class="fr w560">
									<p>
										<label><input class="act_ticket" type="radio" name="2" value="0"/>是</label>&nbsp;&nbsp;
										<label><input type="radio" checked="checked" name="2" value="1" />否</label>
										<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 二等奖 结束 -->
						<!-- 三等奖 开始 -->
						<div class="screen">
							<h4 class="screen_title"><strong>3等奖</strong><em class="close">╳</em></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="加湿器" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="3" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80" src="${adminImgPath}/giftbox_img3.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value="" />
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），大小不超过30K。</b>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl w220">是否需到店兑奖：</strong>
								<div class="fr w560">
									<p>
										<label><input class="act_ticket" type="radio" name="3" value="0"/>是</label>&nbsp;&nbsp;
										<label><input type="radio" checked="checked" name="3" value="1" />否</label>
										<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 三等奖 结束 -->
						<div class="add_screen">新增更多奖项</div>
					</div>
					<input type="hidden" name="awards" id="awards" value=""/>
					<h3><strong>活动设置</strong><span class="fold">展开</span></h3>
					<div class="act_config">
						<div class="row"  id="attention_limit" style="margin-bottom:0px;">
							<strong class="fl w220">关注门槛：</strong>
							<div class="fr w560">
								<p>
									<label><input type="radio" checked="checked" name="bUrlType" value="0"/>查看排行榜</label>&nbsp;&nbsp;
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
										<b>参与用户要查看排行榜页(自己的名次、领奖)，会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“拼图”，公众号将回复排行榜页链接地址（在PC端活动配置排行榜栏目获取)。</b>
									</p>
								</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">拼图关数：</strong>
							<div class="fr w560">
								<p>
									<input name="levelTimes" class="verify_num w80 checkpoint" type="text" value="3" />
									<b>不能大于图库图片数量，每次游戏从图库中随机抽取</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">游戏倒计时：</strong>
							<div class="fr w560">
								<p>
									<input name="countTime" class="verify_num w80" type="text" value="60" />
									<b>秒</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">每人每天最多游戏次数：</strong>
							<div class="fr w560">
								<p>
									<input name="playTimes" class="verify_num w80 game_count" type="text" value="3" />
									<b></b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">活动规则：</strong>
							<div class="fr w560">
								<p class="actrule_msgbox">
									<textarea name="rule" class="verify_rule w430 h120">• 活动时间：2014/10/29 10:27 至 2014/11/29 10:27。
• 成功发送给好友即可额外获得2次游戏机会。
• 闯关成功保存成绩参与排名，关注公众号查看排行榜兑奖
• 活动结束后按排行榜名次依次发奖，赶快刷榜吧！
(模版演示，纯属体验，不予兑奖！)</textarea>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">兑奖说明：</strong>
							<div class="fr w560">
								<p>
									<input name="exExplain" class="verify_contact w420" type="text" value="请填写以下：(模版演示，纯属体验，不予兑奖！)" maxlength="50" />
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
									<label class="need_usermsg"><input checked="checked" value="mail," type="checkbox" />邮寄地址</label>
									<label class="need_usermsg"><input value="qq," type="checkbox" />QQ</label>
									<label class="need_usermsg"><input value="wechat," type="checkbox" />微信号</label>
									<label class="need_usermsg">
										<input class="need_other" value="other:" type="checkbox" />其他</label>
										<label class="need_usermsg"><input type="text" class="other w80" />
									</label>
									<input type="hidden" name="userinfo" id="userinfo" value="" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<h3><strong>自定义风格</strong><span class="fold">展开</span></h3>
					<div class="custom_config">
						<div class="row">
							<strong class="fl w220">封面：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/pintu/cover.jpg"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="coverUrl" value="" >
									<span><em class="ico_image"><!-- 图标 --></em>选择封面</span>
								</div>
								<b>建议尺寸：640*1120（或等比），大小不超过50K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">背景图：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/pintu/bg.jpg"></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="bgUrl" value="" >
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
		$(document).on("blur",".checkpoint",function(){
			if($(this).val() > $('.imgs_config .pinimg').length){
				insertNotice($(this),"关数过多");
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
		$('textarea').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		if(verify > 0){
			$('.pintu .main .cont .imgs_config,.pintu .main .cont .cont_config,.pintu .main .cont .act_config,.pintu .main .cont .custom_config').attr("style","display:block;");	
			$('.pintu .main .cont h3 span').text("收起");
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
								<b>参与用户要查看排行榜页(自己的名次、领奖)，会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“拼图”，公众号将回复排行榜页链接地址（在PC端活动配置排行榜栏目获取)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>