<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>插件详情</title>
	<%@ include file="resource.jsp"%>
</head>
<body>
	<div class="space_20"></div>
	<!-- 详情内容 开始 -->
	<div class="plugin_detail">
		<div class="card">
			<img class="avatar" src="${fileAccessPath }${plugin.icon}" />
			<div class="wrod">
				<h3><strong>${plugin.name}</strong></h3>
				<p class="indent">${plugin.description}</p>
			</div>
			<div class="clear space_5"></div>
			<p class="topborder"><i class="ico_publh"></i>发布时间：<fmt:formatDate value="${plugin.publishTime}" pattern="yyyy/MM/dd  HH:mm" /></p>
			<p><em class="fr">已有${plugin.buyNum}人购买</em><i class="ico_clock"></i>使用有效期：${plugin.valid}个月</p>
			<div class="btns">
				<a href="${cjShowServer}${plugin.actAccessUrl}/${plugin.showActId}" class="btn_demo">演示</a>
				<c:if test="${plugin.price != null &&  plugin.price != 0.0 }">
						<a href="javascript:void(0);" onclick="tryout('${plugin.id}');" class="btn_try ">免费试用</a>
						<a href="javascript:void(0);" onclick="tobuy('${plugin.id}');" class="btn_direct ">￥${plugin.price}</a>
				</c:if>
				<c:if test="${plugin.price != null &&  plugin.price == 0.0 }">
						<a href="javascript:void(0);" onclick="freeUse('${plugin.id}');" class="btn_direct">免费</a>
				</c:if>
			</div>
		</div>
		<div class="space_20"></div>
		<div class="use_explain">
			<div class="norl_title">
				<h3>使用说明</h3>
			</div>
			<div class="text">
				<div class="words">${plugin.guide}</div >
				<a class="fold">展开</a>
			</div>
		</div>
	</div>
	<!-- 详情内容 结束 -->

	<div class="space_20"></div>
	<div class="activity_recomd">
		<div class="norl_title">
			<h3>正在进行的活动（${pluginActVoCount}）</h3>
			<!-- <a href="#">换一批</a> -->
		</div>
		<ul>
			<c:forEach items="${pluginActVos}" var="pluginActVo">
				<li>
					<c:if test="${pluginActVo.type == '0'}">
						<section onclick="javascript:self.location='${cjShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.id}'">
					</c:if>
					<c:if test="${pluginActVo.type == '1'}">
						<section onclick="javascript:self.location='${pluginActVo.accessUrl}'">
					</c:if>
						<a class="imgbox"><img src="${fileAccessPath }${pluginActVo.icon}" /></a>
						<a class="title">${pluginActVo.title}</a>
						<div class="clear"></div>
					</section>
					<a href="${url_otherUserPage }/${pluginActVo.userId}" class="enterprise">${pluginActVo.nickName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="space_20"></div>
	<div class="developer_card">
		<ul>
			<li>
				<dl onclick="javascript:self.location='${url_devOtherPage}/${dev.userId}'">
					<dt>
						<a class="avatar"><img src="${dev.headimgUrl}"></a>
						<h3><a>@${dev.nickName}</a></h3>
						<p>${dev.introduce}</p>
						<div class="clear"></div>
					</dt>
<!-- 					<dd>
						<span class="fr"><i class="ico_gold"></i>收入：<strong>￥1545.6</strong></span>
						<span><i class="ico_suc"></i>成交：<strong>14</strong> 笔</span>
					</dd> -->
					<dd>
<!-- 						<a class="fr">（圣诞送优惠）</a> -->
						<span>开发插件：<strong>4</strong></span>
					</dd>
				</dl>
			</li>
		</ul>
	</div>
	<div class="space_20"></div>
	<div class="plugin_list">
		<%@ include file="plugintj.jsp" %>
	</div>
	<div class="space_10"></div>
	
	<%@ include file="footer.jsp"%>
	
	<%@ include file="menu.jsp"%>
	
	<div class="space_10"></div>
	<!-- 去PC端口弹出对话框 开始 -->
	<div class="gopc_box">
		<dl>
			<dt><i class="ico_pc"></i></dt>
			<dd class="space_10">请移步电脑，打开浏览器进行配置</dd>
			<dd><em>http://www.tchajian.com</em></dd>
			<dd>( "淘" 首字母：t，"插件" 全拼：chajian )</dd>
		</dl>
		<p class="source">淘插件<br />营销推广插件超市</p>
		<div class="bg"></div>
	</div>
	<!-- 去PC端口弹出对话框 结束 -->
</body>
</html>
<script type="text/javascript">
	//去PC
	$(document).ready(function(){
		$('.gopcbtn').click(function(){
			$('.gopc_box').attr("style","display:block;");
		});
		$('.gopc_box').click(function(){
			$(this).attr("style","display:none;");
		});
	});
	
	//购买插件后提醒
	function popupPrompt(str1,funName){
		var str = '<div class="buy_end">\
						<div class="cont">\
							<h3>\
								<strong>提示</strong>\
								<span class="close">╳</span>\
							</h3>\
							<p>\
								<span>'+str1+'</span>\
							</p>\
							<a class="btn_cancel">知道了</a>\
						</div>\
						<div class="bg"></div>\
					</div>';
		$('body').append(str);
		$('.btn_cancel').click(function(){
			$(this).parents('.buy_end').remove();
			if(funName){
				funName();
			}
		});
		$('.close').click(function(){
			$(this).parents('.buy_end').remove();
		});
	}

	var uid = '${uid}';
	var openid = '${openid}';
	
	function tryout(pluginId){
		/* alert("uid="+uid);
		alert("openid="+openid); */
		$.ajax({
				type: "POST",
				url : "${path }/wx/plugin/tryout",
				data:{pluginId:pluginId,uid:uid},
				dataType: "json",
				success: function(data){
					if(data.status == "2"){
						popupPrompt("系统繁忙，请稍后再试！");
					}else if(data.status == "3"){
						popupPrompt("非常抱歉,一个账号只能试用1次",toMyPlugin);
					}else if(data.status == "4"){
						popupPrompt("您已购买此插件",toMyPlugin);
					}else if(data.status == "5"){
						popupPrompt("非常抱歉,一个账号只能试用1次",toMyPlugin);
					}else if(data.status == "1"){
						if(data.addUrl != null || data.addUrl != '' || data.addUrl != undefined){
							window.location.href = '${basePath}wx/userPlugin/redircWxUserPluginUrl?scope=1';
							
						}else{
							window.location.href = '${basePath}wx/userPlugin/redircWxUserPluginUrl?scope=1';
						}  
					}else{
						popupPrompt("系统繁忙，请稍后再试！");
					}
				}
		}); 
			
	}

	function tobuy(pluginId){
	/* 	alert("uid="+uid);
		alert("openid="+openid); */
		$.ajax({
			type: "POST",
			url : "${path }/wx/plugin/tobuy",
			data:{pluginId:pluginId,uid:uid},
			dataType: "json",
			success: function(data){
				if(data.status == "2"){
					popupPrompt("系统繁忙，请稍后再试！");
				}else if(data.status == "3" || data.status == "1"){
					//去支付选择页
					window.location.href='${basePath}wx/other/payType?uid='+uid+'&plgid='+pluginId+'&openid='+openid;
					
				}else if(data.status == "4"){
					popupPrompt("您已购买此插件",toMyPlugin);	  
				}else{
					popupPrompt("系统繁忙，请稍后再试！");	  
				}
			}
			  
		}); 
		
	}

	function freeUse(pluginId){
		/* alert("uid="+uid);
		alert("openid="+openid); */
		$.ajax({
			type: "POST",
			url : "${path }/wx/plugin/freeUse",
			data:{pluginId:pluginId,uid:uid},
			dataType: "json",
			success: function(data){
				if(data.status == "2"){
					$('.login_box').fadeIn(50);
					$('.login_box .main').fadeIn(200);
				}else if(data.status == "3"){
					popupPrompt("您已购买此插件 ",toMyPlugin);
				}else if(data.status == "1"){
					if(data.addUrl != null || data.addUrl != '' || data.addUrl != undefined){
						window.location.href = '${basePath}wx/userPlugin/redircWxUserPluginUrl?scope=1';
						
					}else{
						window.location.href = '${basePath}wx/userPlugin/redircWxUserPluginUrl?scope=1';
					}
				}else{
					popupPrompt("系统繁忙，请稍后再试！");
				}
			}
		}); 
	}

	function toMyPlugin(){
		window.location.href = "<%=path %>/wx/userPlugin/redircWxUserPluginUrl?scope=1";		
	}

	
	//微信分享
	wx.config({
	    appId: '${shareArg.appId}',
	    timestamp: '${shareArg.timestamp}',
	    nonceStr: '${shareArg.nonceStr}',
	    signature: '${shareArg.signature}',
	    jsApiList: [
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage'
	    ]
	});
	wx.ready(function () {
		  var shareData = {
		    title: "${plugin.name}",
		    desc: "${plugin.description}",
		  	link: '${shareArg.shareUrl}',
		    imgUrl: "${fileAccessPath }${plugin.icon}"
		  };
	    wx.onMenuShareTimeline(shareData);
	    wx.onMenuShareAppMessage(shareData);
	});
/* 	wx.error(function (res) {
	  alert(res.errMsg);
	}); */
</script>

