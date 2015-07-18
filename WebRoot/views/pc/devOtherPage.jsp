<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>${loginUser.nickName}个人主页</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 homepage">
		<!-- 企业名片 开始 -->
		<div class="visitcard">
			<a  class="avatar"><img src="${loginUser.headimgUrl}" /></a>
			<div class="fl">
				<h3>
					<a  class="fl">@${loginUser.nickName}</a>
				</h3>
				<p><em class="city"><i class="ico_area"></i>${loginUser.province}&nbsp;${loginUser.city}</em></p>
				<p class="indent">${loginUser.introduce}</p>
				<!-- 有联系资料: a标签类名为pinkbtn; -->
				<!-- 无联系资料: a标签类名为greybtn; -->
				<!-- 不能联系我: a标签则无类名; -->
				<!--<div class="contact_btn"><a class="greenbtn">联系我</a></div>-->
<!-- 				<p class="suc_gold"> -->
<!-- 					<span><i class="ico_suc"></i>成交：<em>14</em> 笔</span> -->
<!-- 					<b>|</b> -->
<!-- 					<span><i class="ico_gold"></i>收入：<em>￥1545.6</em></span> -->
<!-- 				</p> -->
			</div>
			<div class="fr">
				<%-- <div class="code_share">
					<img src="${url_getQcode }${qrcodeUrl}" />
				</div> --%>
				<!-- Baidu Share BEGIN -->
			<!-- 	<div class="bdsharebuttonbox" style="float:right;margin:0 -6px 0 0;">
					<a href="#" class="bds_more" data-cmd="more"></a>
					<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
					<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
					<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
					<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
					<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
				</div>
				<script>
					window._bd_share_config={
								"common":{
									"bdSnsKey":{},
									"bdText":"",
									"bdMini":"2",
									"bdMiniList":false,
									"bdPic":"",
									"bdStyle":"1",
									"bdSize":"16"
								},
								 "share":{}
					};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
							 -->
					
				<!-- Baidu Share END -->
<!-- 				<span class="sharehead">分享到：</span> -->
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- 企业名片 结束 -->
		<!-- 左侧内容 开始 -->
		<div class="fl w840 main">
			<div class="norl_title">他开发的（${devCountNum}）</div>
			<c:if test="${devCountNum le 0 }">
					<div class="if_none">
					<p>TA还没有任何插件，去<a href="${basePath}">首页帮TA挑选</a></p>
					</div>
			</c:if>	
			<div class="cont">
				<ul id="developer">
					<c:if test="${devCountNum gt 0}">
					<c:forEach items="${devPluginVos}" var="devPluginVo">
						<li>
							<div class="fl">
								<a href="${url_pluginDetail }/${devPluginVo.id}" class="imgbox"><img src="${devPluginVo.icon}" /></a>
								<h3><a href="${url_pluginDetail }/${devPluginVo.id}" >${devPluginVo.name}</a></h3>
								<p>
									<em class="quot_left"><!-- 前引号 --></em>
									<span>${devPluginVo.description}</span>
									<em class="quot_right"><!-- 后引号 --></em>
								</p>
								<div class="clear"></div>
							</div>
							<div class="fr">
								<img class="code" onclick="showBigimg(this);" src="${url_getQcode }${cjShowServer }${devPluginVo.actAccessUrl}/${devPluginVo.showActId}" />
							</div>
							<div class="clear"></div>
						</li>
					</c:forEach>
					</c:if>
					<c:if test="${devCountNum gt PAGE_SHOW_NUMBER}">
						<div class="seemore"><a onclick="morePlugins();" id="seemore_avt">查看更多</a></div>
					</c:if>
					</ul>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		
		<!-- 右侧内容 结束 -->
		<div class="clear space_30"></div>
		<!-- 其他开发者 开始 -->
		<div class="norl_title">其他开发者</div>
		<div class="developer_card">
			<ul>
				<c:forEach items="${otherDevUsers}" var="otherDevUser">
				<li style="height:auto;">
					<dl>
						<dt>
							<a target="_blank" href="${url_devOtherPage }/${otherDevUser.userId}" class="avatar"><img src="${otherDevUser.headimgUrl}" /></a>
							<h3><a target="_blank" href="${url_devOtherPage }/${otherDevUser.userId}">@${otherDevUser.nickName}</a></h3>
							<p>${otherDevUser.introduce}</p>
						</dt>
<!-- 						<dd>
							<span class="fr"><i class="ico_gold"></i>收入：<strong>￥1545.6</strong></span>
							<span><i class="ico_suc"></i>成交：<strong>14</strong>笔</span>
						</dd> -->
						<dd>
							<span>开发插件：<strong>${otherDevUser.countNum}</strong></span>
						</dd>
					</dl>
				</li>
				</c:forEach>
				
			</ul>
			<div class="clear"></div>
		</div>
		<!-- 其他开发者 结束 -->
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	var pluginCount = 10;
	var loginId = ${loginUser.userId};
	var cjShowServer = '${cjShowServer}';
	var fileAccessPath = '${fileAccessPath}';
	var url_getQcode = '${url_getQcode }';
	var url_otherPage = '${url_otherUserPage}';
	var url_pluginDetail = '${url_pluginDetail}';
	var _devCountNum = ${devCountNum};
	 
	 /**
	  * 查看更多插件
	 */
	function morePlugins(){
	 		$.ajax({
	 			  type: "GET",
	 			  url : "${path }/pc/user/getMorePlugins",
	 			  data:{pluginCount:pluginCount,loginId:loginId},
	 			  dataType: "json",
	 			  success: function(data){
	 				  pluginCount += 5;
	 				  $('#developer').empty();
	 					var html = '';
	 				    for (var i = 0; i < data.length; i++){
	 				    	html += '<li>';
		 				    	html += '<div class="fl">';
		 				    	html += '<a href="'+url_pluginDetail+'/'+data[i].id+'" class="imgbox"><img src="'+data[i].icon+'" /></a>';
		 				    	html += '<h3><a  href="'+url_pluginDetail+'/'+data[i].id+'">'+data[i].name+'</a></h3>';
		 				    	html += '<p>';
		 				    	html += '<em class="quot_left"><!-- 前引号 --></em>';
		 				    	html += '<span>'+data[i].description+'</span>';
		 				    	html += '<em class="quot_right"><!-- 后引号 --></em>';
		 				    	html += '</p>';
		 				    	html += '<div class="clear"></div>';
		 				    	html += '</div>';
		 				    	html += '<div class="fr">';
		 				    	html += '<img class="code" src="'+url_getQcode+cjShowServer+data[i].actAccessUrl+'/'+data[i].showActId+'" />';
		 				    	html += '</div>';
		 				    	html += '<div class="clear"></div>';
	 				    	html += '</li>';
	 				    }
	 				    if(_devCountNum > data.length){
					    	html += '<div class=\"seemore\"><a onclick="morePlugins();" id=\"seemore_avt\">查看更多</a></div>';
					    }
	 				    $('#developer').html(html);
	 			  }
	 	  }); 
	}
 
	//显示大图(code)
	function showBigimg(obj){
		$('body').append('<div onclick="hideBigimg(this);" class="bigimg_box">\
						<img src="'+$(obj).attr("src")+'" />\
					</div>');
		$('.bigimg_box img').load(function(){
			$(this).attr("style","margin-top:-" + ($(this).height()/2-5) +"px;margin-left:-" + ($(this).width()/2-5) +"px;");
		});
	}
	function hideBigimg(obj){
		$(obj).remove();
	}
</script>
