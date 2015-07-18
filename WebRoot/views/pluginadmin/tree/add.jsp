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
<link type="text/css" rel="stylesheet" href="${adminCssPath}/bigwheel.css?v=2"/>
<link type="text/css" rel="stylesheet" href="${commonCssPath}/kindeditor/default.css?v=2"/>
<script type="text/javascript" src="${commonJsPath}/jquery.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/bootstrap.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/moment.min.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/bootstrap/daterangepicker.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/kindeditor.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/kindeditor/lang/zh_CN.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/layout.js?v=2"></script>
<script type="text/javascript" src="${commonJsPath}/pluginpublic.js?v=2"></script>
<script type="text/javascript" src="${adminJsPath}/bigwheel.js?v=2"></script>
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

		$('.bigwheel .screen').each(function(){
	        addscreen_json($(this));
	    });
	    
		var iteminfo = $('.cont_config').serializeObject();
		$('#awards').val(iteminfo);
	}
</script>
<title>圣诞树</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="../../pc/header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 bigwheel">
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="tab_title">
				<a href="#" class="active">活动设置</a>
				<a href="${path}/pluginadmin/tree/record/0?upid=${userPluginId}">中奖名单</a>
			</div>
			<div class="cont">
				<form action="${path}/pluginadmin/tree/add_do" method="post" onsubmit="return onClick();">
					<h3><strong>基础设置</strong><span class="fold">收起</span></h3>
					<div class="basic_config">
						<div class="row">
							<strong class="fl">活动名称：</strong>
							<div class="fr">
								<p>
									<input name="name" class="verify_name w300" type="text" maxlength="30" value="摇一摇，中大奖" />
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl space_15">活动图标：</strong>
							<div class="fr">
								<div class="preview"><img class="w80 h80 imghead" src="${adminImgPath}/tree/logo.gif" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="shareImgUrl" value=""/>
									<span><em class="ico_image"><!-- 图标 --></em>上传图片</span>
								</div>
								<b>建议尺寸：100*100（或等比），大小不超过30K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">插入广告：</strong>
							<div class="fr">
								<p class="tab_ad">
									<label class="active"><input type="radio" checked="checked" name="bAdvert" value="0" />开启</label>
									<label><input type="radio" name="bAdvert" value="1"/>关闭</label>
								</p>
								<div class="ad_upcont space_10">
									<div class="preview"><img class="w320 imghead" src="${adminImgPath}/ad.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" name="advertImgUrl"/>
										<span><em class="ico_image"><!-- 图标 --></em>上传广告图</span>
									</div>
									<b>建议尺寸：640*100（或等比），大小不超过50K。</b>
									<p>
										<input name="advertUrl" class="verify_url w420" type="text" value="http://mp.weixin.qq.com/s?__biz=MzA3OTU4MDY3OQ==&mid=204142510&idx=1&sn=d6f2450ecf33baebf8f44f9d72ece296#rd" />
									</p>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl">分享内容设置：</strong>
							<div class="fr">
								<p>
									<input name="shareTitle" class="verify_title w300" type="text" maxlength="25" placeholder="标题" value="快来摇一摇！中大奖哦~" />
									<b>发送给朋友、分享朋友圈，均会显示标题</b>
								</p>
								<div class="space_10"><!-- 填充10像素间距 --></div>
								<p>
									<textarea name="shareDescription" class="verify_intro w300" maxlength="36" placeholder="简介">就快掉下来了！加把劲呀~</textarea>
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
					</div>
					<h3><strong>奖项设置</strong><span class="fold">展开</span></h3>
					<div class="cont_config">
						<div class="row">
							<strong class="fl w220">是否显示奖品的"显示数量"：</strong>
							<div class="fr w560">
								<p>
									<label class="active"><input checked="checked" type="radio" name="bShowNum" value="0"/>是</label>&nbsp;&nbsp;
									<label><input type="radio" name="bShowNum" value="1"/>否</label>
									<b>奖品显示在活动界面的展示数量（仅供用户观看）</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">是否显示中奖名单：</strong>
							<div class="fr w560">
								<p>
									<label><input checked="checked" type="radio"  name="bShowList" value="0"/>是</label>&nbsp;&nbsp;
									<label><input type="radio" name="bShowList" value="1"/>否</label>
									<b>活动界面中，位于广告图下侧滚动显示用户中奖记录</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<!-- 一等奖 开始 -->
						<div class="screen" data-id="1">
							<h4 class="screen_title"><strong>1等奖</strong></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="立可拍" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">显示数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="10" />
										<b>仅用于活动界面中展示给用户看</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input onchange="unreduce(0,this);" class="verify_num w80" type="text" value="0" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">中奖概率：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80 probability" type="text" value="0" />
										<b>0~100，用户获得该奖品的概率，独立设置，与其他奖品不相关</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80 imghead" src="${adminImgPath}/tree/1.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value=""/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），PNG格式，大小不超过30K。</b>
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
						<div class="screen" data-id="2">
							<h4 class="screen_title"><strong>2等奖</strong></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="100元红包" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">显示数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="50" />
										<b>仅用于活动界面中展示给用户看</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input onchange="unreduce(0,this);" class="verify_num w80" type="text" value="0" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">中奖概率：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80 probability" type="text" value="0" />
										<b>0~100，用户获得该奖品的概率，独立设置，与其他奖品不相关</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80 imghead" src="${adminImgPath}/tree/2.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value=""/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），PNG格式，大小不超过30K。</b>
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
						<div class="screen" data-id="3">
							<h4 class="screen_title"><strong>3等奖</strong></h4>
							<div class="row">
								<strong class="fl">奖品名称：</strong>
								<div class="fr">
									<p>
										<input class="verify_prizename w300" type="text" maxlength="50" value="10元话费" />
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">显示数量：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80" type="text" value="100" />
										<b>仅用于活动界面中展示给用户看</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">实际数量：</strong>
								<div class="fr">
									<p>
										<input onchange="unreduce(10,this);" class="verify_num w80" type="text" value="10" />
										<b>奖品发完后，不会再有用户中奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl">中奖概率：</strong>
								<div class="fr">
									<p>
										<input class="verify_num w80 probability" type="text" value="10" />
										<b>0~100，用户获得该奖品的概率，独立设置，与其他奖品不相关</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl space_15">奖品图片：</strong>
								<div class="fr">
									<div class="preview"><img class="w80 h80 imghead" src="${adminImgPath}/tree/3.png" /></div>
									<div class="upload_file">
										<input type="text" class="fileuploadclass" value=""/>
										<span><em class="ico_image"></em>选择图片</span>
									</div>
									<b>建议尺寸：80*80（或等比），PNG格式，大小不超过30K。</b>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<strong class="fl w220">是否需到店兑奖：</strong>
								<div class="fr w560">
									<p>
										<label><input class="act_ticket" type="radio" name="3" value="0"/>是</label>&nbsp;&nbsp;
										<label><input type="radio" checked="checked" name="3" value="1"/>否</label>
										<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>
									</p>
								</div>
								<div class="clear"></div>
							</div>
							<input type="hidden" value="" class="screen_json"/>
						</div>
						<!-- 三等奖 结束 -->
						<div class="add_screen">新增更多奖品等级</div>
					</div>
					<input type="hidden" name="awards" id="awards" value=""/>
					<h3><strong>活动设置</strong><span class="fold">展开</span></h3>
					<div class="act_config">
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
										<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“摇树”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>
									</p>
								</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w270">每人每天最多游戏次数：</strong>
							<div class="fr w510">
								<p>
									<input name="playTimes" class="act_count verify_num w80" type="text" value="2" />
									<b>大于1，游戏玩第二次时强制要求分享</b>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w270">已中奖的用户，是否可以继续获奖：</strong>
							<div class="fr w510">
								<p>
									<label><input type="radio" name="bCanPlay" value="0"/>是</label>&nbsp;&nbsp;
									<label><input class="cant_join" type="radio" checked="checked" name="bCanPlay" value="1"/>否</label>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">活动规则：</strong>
							<div class="fr w560">
								<p class="actrule_msgbox">
									<textarea name="rule" class="verify_rule w430 h120">• 活动时间：  至  。
• 每人每天可抽奖1次。
• 分享到朋友圈可额外获得1次抽奖机会。
• 请中奖用户关注公众号，回复手机号兑奖。
(模版演示，纯属体验，不予兑奖！)</textarea>
								</p>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">兑奖说明：</strong>
							<div class="fr w560">
								<p>
									<input name="exExplain" class="verify_contact w420" type="text" value="请填写以下资料：(模版演示，纯属体验，不予兑奖！)" maxlength="200" />
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
									<label class="need_usermsg"><input value="name," type="checkbox"/>姓名</label>
									<label class="need_usermsg"><input value="mail," type="checkbox"/>邮寄地址</label>
									<label class="need_usermsg"><input value="qq," type="checkbox" checked="checked" />QQ</label>
									<label class="need_usermsg"><input value="wechat," type="checkbox" />微信号</label>
									<label class="need_usermsg"><input class="need_other" value="other:" type="checkbox" />其他</label>
									<label class="need_usermsg"><input type="text" class="other w80" /></label>
									
									<input type="hidden" name="userinfo" id="userinfo"  value="" />
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
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/tree/cover.gif" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="coverUrl" value=""/>
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：640*1120（或等比），大小不超过50K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">圣诞树：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/tree/tree.gif" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="treeUrl" value=""/>
									<span><em class="ico_image"><!-- 图标 --></em>选择背景</span>
								</div>
								<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>
							</div>
							<div class="clear"></div>
						</div>
						<div class="row">
							<strong class="fl w220">背景图：</strong>
							<div class="fr w560">
								<div class="preview"><img class="w160 h240" src="${adminImgPath}/tree/bg.jpg" /></div>
								<div class="upload_file">
									<input type="text" class="fileuploadclass" name="bgUrl" value=""/>
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
			$('.bigwheel .main .cont .cont_config,.bigwheel .main .cont .act_config,.bigwheel .main .cont .custom_config').attr("style","display:block;");
			$('.bigwheel .main .cont h3 span').text("收起");
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
								<b>用户打开好友发送或分享至朋友圈的链接，欲参与活动会先跳转该链接。建议用公众号新增图文素材，内容为活动规则，重点引导用户关注，再在公众号内设置关键词回复，如用户发送“摇树”，公众号将回复活动链接地址（在PC端我的活动列表获取，该状态下仅在被分享出去的页面生效，原活动链接地址打开的页面不生效)。</b>\
							</p>\
						</div>\
						<div class="clear"></div>');
			}else{
				$('#attention_limit').next().html('');
			}
	});
});
</script>