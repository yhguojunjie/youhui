<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%><title>活动中心</title>
</head>
<body>
	<!-- 活动中心 开始 -->
	<div class="ativ_center">
		<!-- 活动专题多图滚动 开始 -->
<%-- 		<div id="scrollBox" class="scrollBox">
			<a class="careme" href="http://mp.weixin.qq.com/s?__biz=MzA4MzA1MjY2MQ==&mid=216270537&idx=1&sn=4c55a0dab799fac9a2f72688687d94d1#rd&ADUIN=565009015&ADSESSION=1418173420&ADTAG=CLIENT.QQ.5329_.0&ADPUBNO=26349"><img src="${basePath}images/wx/lacode.png" /></a>
			<div class="bd">
				<ul>
					<li>
						<a href="${wxurl_pluginDetail}/4"><img _src="${basePath}images/wx/wechatbanner0.jpg" /></a>
					</li>
				</ul>
				<ul>
					<li>
						<a href="${wxurl_pluginDetail}/5"><img _src="${basePath}images/wx/wechatbanner1.jpg"/></a>
					</li>
				</ul>
			</div>
			<div class="hd">
				<span class="prev">&lt;</span>
				<ul></ul>
				<span class="next">&gt;</span>
			</div>
		</div> --%>
		<!-- 活动专题多图滚动 结束 -->
		<!-- <div class="space_20"></div> -->
		<div class="space_20"></div>
		<!-- 活动列表 开始 -->
		<div class="tab_title" id="index_tab">
			<a id='pluginPublish' value = '2' class="active"><em>按发布</em></a>
			<a id='pluginBrowsenum' value = '1' ><em>按热度</em></a>
			<a href="${path }/wx/other/toClassicCase" ><em>经典案例</em></a>
		</div>
		<div class="ativ_list">
			<ul id="pluginWxList">
				<c:if test="${paginationPluginActCentActCent.totalCount le 0 }">
					<li>目前还没有活动!</li>
				</c:if>
				<c:if test="${paginationPluginActCent.totalCount gt 0}">
					<c:forEach items="${paginationPluginActCent.pageResult}" var="pluginWxVo">
						<li>
							<c:if test="${pluginWxVo.type == '0' }">
								<a href="${cjShowServer }${pluginWxVo.actAccessUrl}/${pluginWxVo.actId}" class="ativ_title">
								<img src="${fileAccessPath}${pluginWxVo.actIcon}" />
							</c:if>
							<c:if test="${pluginWxVo.type == '1' }">
								<a href="${pluginWxVo.accessUrl }" class="ativ_title">
								<c:choose>
									<c:when test="${pluginWxVo.actIcon != null && pluginWxVo.actIcon != ''}">
										<img src="${fileAccessPath}${pluginWxVo.actIcon}" />
									</c:when>
									<c:otherwise>
										<img src="${path}/images/wx/outact.gif" />
									</c:otherwise>
							</c:choose>
							</c:if>
					
								
								<h3>${pluginWxVo.actTitle}</h3>
								<p class="tr">
									<c:if test="${pluginWxVo.type == '0' }">
										<span class="fl">浏览量：<em>${pluginWxVo.actBrowseNum}</em></span>
									</c:if>
								</p>

							</a>
							<a href="${url_otherUserPage}/${pluginWxVo.userId}" class="ativ_intro">
								<c:choose>
									<c:when test="${pluginWxVo.headimgUrl != null && pluginWxVo.headimgUrl != ''}">
										<img src="${pluginWxVo.headimgUrl}" />
									</c:when>
									<c:otherwise>
										<img src="${path}/images/avatar.jpg" />
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${pluginWxVo.nickName != null && pluginWxVo.nickName != ''}">
										<h4>${pluginWxVo.nickName}</h4>
									</c:when>
									<c:otherwise>
										<h4>淘插件用户</h4>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${pluginWxVo.introduce != null && pluginWxVo.introduce != ''}">
										<p>${pluginWxVo.introduce}</p>
									</c:when>
									<c:otherwise>
										<p>还没有简介</p>
									</c:otherwise>
								</c:choose>
							</a>
							<c:if test="${pluginWxVo.type == '0' }">
								<a href="${wxurl_pluginDetail}/${pluginWxVo.pluginId}" class="ativ_plugin">使用插件：<em>《${pluginWxVo.pluginName}》</em></a>
							</c:if>
							<c:if test="${pluginWxVo.type == '1' }">
								<a class="ativ_plugin">站外活动</a>
							</c:if>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<div class="get_more">努力加载中...</div>
		<!-- 活动列表 开始 -->
	</div>
	<!-- 活动中心 结束 -->
	<div class="space_20"></div>
	<!-- 赚代币弹出对话框 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">
var fileAccessPath = '${fileAccessPath}';
var basePath = '${basePath}';
var cjShowServer = '${cjShowServer}';
var url_otherUserPage = '${url_otherUserPage}';
var wxurl_pluginDetail = '${wxurl_pluginDetail}';
var currentPage = '1';
var orderFlag = '2';

$(document).ready(function(){
	/**
	* 查询插件信息
	* orderFlag 2表示发布时间publishTime，1.表示浏览量pluginBrowsenum
	* count 第几页（加载更多）
	*/
	$('#pluginBrowsenum,#pluginPublish').click(function() {
		currentPage = '1';
		orderFlag = $(this).attr("value");
		setStates(orderFlag);
		getPageDatas(1); 
	})
});

//排序方式(选中状态)
function setStates(orderFlag){
	if(orderFlag=='1'){
		$('#pluginBrowsenum').attr("class","active");
		$('#pluginPublish').attr("class"," ");
	}else{
		$('#pluginBrowsenum').attr("class","");
		$('#pluginPublish').attr("class","active");
	}
}

//加载数据
//isRemove 是否删除之前查询的数据[0不删除，1删除]
function getPageDatas(isRemove){
	$.ajax({
		  type: "GET",
		  url : "${path }/wx/pluginAct/pluginActCenterAjaxList?currentPage="+currentPage+"&orderFlag="+orderFlag,
		  dataType: "json",
		  success: function(data){
			if(data != null && data !=undefined){
				  var html = '';
				  if('1' == isRemove){
					  $('#pluginWxList').empty();
				  }
				  if(data.pageResult.length != 0){
						for (var i = 0; i < data.pageResult.length; i++) {
							var pluginDetailPath = wxurl_pluginDetail + "/"+data.pageResult[i].pluginId; 
							var acticon = fileAccessPath+data.pageResult[i].actIcon; 
							var actUrl = '';
							if(data.pageResult[i].type == '0'){
								actUrl = cjShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId;
							}
							if(data.pageResult[i].type == '1'){
								actUrl = data.pageResult[i].accessUrl;
								if(data.pageResult[i].actIcon == null || data.pageResult[i].actIcon ==''){
									acticon = basePath + "images/wx/outact.gif";
								}
							}
							
							var userUrl =url_otherUserPage+"/"+data.pageResult[i].userId;
							var introduceStr =data.pageResult[i].introduce;
							if(introduceStr == null || introduceStr == ''){
								introduceStr = '还没有简介';
							}
							var headimgUrl = data.pageResult[i].headimgUrl;
							if(headimgUrl == null || headimgUrl == '' || headimgUrl == undefined){
								headimgUrl =  basePath + "images/avatar.jpg";
							}
							var nickName = data.pageResult[i].nickName;
							if(nickName == null || nickName == '' || nickName == undefined){
								nickName = "淘插件用户";
							}
							
							html += '<li>';
							html += '<a href="'+actUrl+'" class="ativ_title">';
							html += '<img src="'+acticon+'" />';
							html += '<h3>'+data.pageResult[i].actTitle+'</h3>';
							html += '<p class="tr">';
							if(data.pageResult[i].type == '0'){
								html += '<span class="fl">浏览量：<em>'+data.pageResult[i].actBrowseNum+'</em></span>';
							}
							html += '</p>';
							html += '</a>';
							html += '<a href="'+userUrl+'" class="ativ_intro">';
							html += '<img src="'+data.pageResult[i].headimgUrl+'" />';
							html += '<h4>'+data.pageResult[i].nickName+'</h4>';
							html += '<p>'+introduceStr+'</p>';
							html += '</a>';
							if(data.pageResult[i].type == '0'){
								html += '<a href="'+pluginDetailPath+'" class="ativ_plugin">使用插件：<em>《'+data.pageResult[i].pluginName+'》</em></a>';
							}
							if(data.pageResult[i].type == '1'){
								html += '<a class="ativ_plugin">站外活动</a>';
							}
							html += '</li>';
						}
					}else{
						html += '<li>目前还没有插件!</li>';
					}
					html += '</ul>';
					$('#pluginWxList').append(html); 
			  }
		  }
	}); 
}

//加载更多活动
var winHeight=$(window).height(),
maxpage=${maxpage};
$(window).scroll(function(){
	var wst=$(window).scrollTop();
	var bodyHeight=$(document).height();
	if(wst+winHeight>bodyHeight-10){
		currentPage++;
		if(currentPage <= maxpage){
			ajaxtip("努力加载中...");
			$.ajax({
				  type: "GET",
				  url : "${path }/wx/pluginAct/pluginActCenterAjaxList?currentPage="+currentPage+"&orderFlag="+orderFlag,
				  dataType: "json",
				  success: function(data){
					if(data != null && data !=undefined){
						  var html = '';
								for (var i = 0; i < data.pageResult.length; i++) {
									var pluginDetailPath = wxurl_pluginDetail + "/"+data.pageResult[i].id; 
									var acticon = fileAccessPath+data.pageResult[i].actIcon; 
									
									var actUrl = '';
									if(data.pageResult[i].type == '0'){
										actUrl = cjShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId;
									}
									if(data.pageResult[i].type == '1'){
										actUrl = data.pageResult[i].accessUrl;
									}
									
									var userUrl =url_otherUserPage+data.pageResult[i].userId;
									var introduceStr =data.pageResult[i].introduce;
									if(introduceStr == null || introduceStr == ''){
										introduceStr = '还没有简介';
									}
									
									html += '<li>';
									html += '<a href="'+actUrl+'" class="ativ_title">';
									html += '<img src="'+acticon+'" />';
									html += '<h3>'+data.pageResult[i].actTitle+'</h3>';
									html += '<p class="tr">';
									if(data.pageResult[i].type == '0'){
										html += '<span class="fl">浏览量：<em>'+data.pageResult[i].actBrowseNum+'</em></span>';
									}
									html += '</p>';
									html += '</a>';
									html += '<a href="'+userUrl+'" class="ativ_intro">';
									html += '<img src="'+data.pageResult[i].headimgUrl+'" />';
									html += '<h4>'+data.pageResult[i].nickName+'</h4>';
									html += '<p>'+introduceStr+'</p>';
									html += '</a>';
									if(data.pageResult[i].type == '0'){
										html += '<a href="'+pluginDetailPath+'" class="ativ_plugin">使用插件：<em>《'+data.pageResult[i].pluginName+'》</em></a>';
									}
									if(data.pageResult[i].type == '1'){
										html += '<a class="ativ_plugin">站外活动</a>';
									}
									html += '</li>';
							}
							$('#pluginWxList').append(html); 
						}
				},
				error: function(xhr, type){
					ajaxtip("加载失败，请稍候重试！");
				}
			});
	}else{
		ajaxtip("已加载全部")
	}
  }
});

function ajaxtip(txt){
	$(".get_more").html(txt);
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
		    title: "微信有奖活动中心",
		    desc: "好玩的、有奖的，都在这里!",
		  	link: '${shareArg.shareUrl}',
		    imgUrl: "${basePath}images/giftbox.png"
		  };
	    wx.onMenuShareTimeline(shareData);
	    wx.onMenuShareAppMessage(shareData);
	});
/* 	wx.error(function (res) {
	  alert(res.errMsg);
	}); */
</script>		


