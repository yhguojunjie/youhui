<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>个人主页</title>
</head>
<body>
	<div class="space_20"></div>
	<!-- 开发者主页 开始 -->
	<div class="user_center">
		<div class="card" style="border-bottom:2px solid #63c7bb;">
			<a href="javascript:void(0);" class="avatar"><img src="${loginUser.headimgUrl}" /></a>
			<h3>
				<a href="javascript:void(0);" class="title">@${loginUser.nickName}</a>
				<span class="contact_btn"><a class="btn_green">联系我</a></span>
			</h3>
			<c:choose>
				<c:when test="${loginUser.introduce != null && loginUser.introduce != ''}">
					<p>${loginUser.introduce}</p>
				</c:when>
				<c:otherwise>
					<p>还没有简介</p>
				</c:otherwise>
			</c:choose>
			<div class="clear"></div>
			<a href="${url_userEdit}" class="active_layout"></a>
		</div>
<%--		<div class="friend">
			<div class="title">
				<ul class="gift">
					<c:forEach items="${giftVos}" var="giftVo">
						<c:if test="${giftVo.drawUserName != ''}">
							<li class="active">
								<input type="hidden" value = "${giftVo.id}">
								<input type="hidden" class="drawUserheadimgUrl" value = "${giftVo.drawUserheadimgUrl}">
								<input type="hidden" class="drawUserName" value = "${giftVo.drawUserName}">
								<a class="imgbox">被抢了</a>
							</li>
						</c:if>
						<c:if test="${giftVo.drawUserName == ''}">
							<li>
								<input type="hidden" id="id" name = "${giftVo.id}">
								<a class="imgbox"></a>
							</li>
						</c:if>
				    </c:forEach>
				</ul>
				<strong>粉丝（${frendes}）</strong>
			</div>
 			<div class="friend_list">
				<div class="body">
					<ul class="friend_list_cont">
						<c:if test="${frendes le 0 }">
						<li><p>您还没有粉丝</p></li>
						</c:if>
						<c:if test="${frendes gt 0}">
							<c:forEach items="${userFriends.pageResult}" var="userFriend">
								<li>
									<a target="_blank" href="${url_otherUserPage}/${userFriend.userId}" class="avatar"><img src="${userFriend.headimgUrl}" /></a>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div> 
		</div>--%>
		<div class="plugin_list">
			<h2>我开发的（${devCountNum}）</h2>
			<ul id="pluginWxList">
				<c:if test="${devCountNum le 0 }">
					<li>
					<p>TA还没有插件</a></p>
					</li>
				</c:if>
				<c:if test="${devCountNum gt 0}">
					<c:forEach items="${devPluginVos.pageResult}" var="devPluginVo">
						<li>
							<section onclick="javascript:self.location='${wxurl_pluginDetail }/${devPluginVo.id}'">
								<a class="imgbox"><img src="${fileAccessPath }${devPluginVo.icon}"></a>
								<h3><a>${devPluginVo.name}</a></h3>
								<p>${devPluginVo.description}</p>
								<div class="clear"></div>
							</section>
						</li>
					</c:forEach>
				</c:if>
			</ul>
			<c:if test="${devCountNum gt WXPAGE_SHOW_NUMBER}">
				<div class="seemore"><a id=seemore_plugin_div>查看更多</a></div>
			</c:if>
		</div>
	</div>
	<!-- 开发者主页 结束 -->
	<div class="space_20"></div>
	<!-- 其他开发者 开始 -->
	<div class="developer_card">
		<div class="norl_title">
			<h3>其他开发者</h3>
		</div>
		<ul>
			<c:forEach items="${otherDevUsers}" var="otherDevUser">
				<li>
					<dl onclick="javascript:self.location='${url_devOtherPage }/${otherDevUser.userId}'">
						<dt>
							<a class="avatar"><img src="${otherDevUser.headimgUrl}" /></a>
							<h3><a>@${otherDevUser.nickName}</a></h3>
							<p>${otherDevUser.introduce}</p>
							<div class="clear"></div>
						</dt>
<!-- 						<dd>
							<span class="fr"><i class="ico_gold"></i>收入：<strong>￥1545.6</strong></span>
							<span><i class="ico_suc"></i>成交：<strong>14</strong>笔</span>
						</dd> -->
						<dd>
							<span>开发插件：<strong>${otherDevUser.pluginNum}</strong></span>
						</dd>
					</dl>
				</li>
				</c:forEach>
		</ul>
	</div>
	<div class="space_30"></div>
	<!-- 其他开发者 结束 -->
	<!-- 浮动按钮 开始 -->
	<!--<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="float_btns hide" id="show">
		 <a class="btn_befriend">成为插友&nbsp;&nbsp;抢他礼包</a>
	</div> -->
	<!-- 浮动按钮 结束 -->
	<!-- 抢礼包提示 开始 -->
	<div class="robgift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<div class="space_5"></div>
			<div class="space_10"></div>
			<div class="text">
				<p><span>没有抢到礼包</span></p>
				<p><span>首次成为别人的插友，奖励代币：<em>￥10</em></span></p>
			</div>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<div class="tc">
				<a class="btn_done">去使用</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 抢礼包提示 结束 -->
	<!-- 被抢走提示框 开始 -->
	<div class="nogift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<p>
				<img src="${path }/images/wx/avatar.jpg">
				<span>嘿嘿，被<em>我</em>抢走了！</span>
			</p>
			<a class="btn_cancel">知道了</a>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 被抢走提示框 结束 -->
	<!-- 剩余礼包提示 开始 -->
	<div class="remaininggift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<div class="space_10"></div>
			<p>
				每个淘插件账号都有<em>3个神秘大礼包</em>
			</p>
			<p>
			随机赠送给插友
			</p>
			<p>
				<span>邀请微信好友成为你的“插友”，来抢礼包</span>
			</p>
			<div class="space_10"></div>
			<div class="tc">
				<a class="btn_cancel">知道了</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 剩余礼包提示 结束 -->
	
	<!-- 联系我成功提示 开始 -->
	<div class="contact_sucbox">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<table>
				<tr>
					<td>
						<div class="row">
							<i class="ico_phone"></i><b>手机：</b>
							<c:choose>
								<c:when
									test="${loginUser.mobile !=null && loginUser.mobile != '' 
													&& loginUser.mobileOpen == '1'}">
										${loginUser.mobile}
								</c:when>
								<c:otherwise>
								暂无
								</c:otherwise>
							</c:choose>

						</div>
						<div class="row">
							<i class="ico_qq"></i><b>Q Q：</b>
							<c:choose>
								<c:when
									test="${loginUser.qqAccount !=null && loginUser.qqAccount != ''}">
								<%-- 	<a target="_blank"
										href="http://wpa.qq.com/msgrd?v=3&uin=${loginUser.qqAccount}&site=qq&menu=yes"
										class="fr">发消息</a> --%>
									
										${loginUser.qqAccount}
									</c:when>
									<c:otherwise>
										暂无
									</c:otherwise>
							</c:choose>
						</div>
						<div class="row">
							<i class="ico_wechat"></i><b>微信：</b>
							<c:choose>
								<c:when
									test="${loginUser.weixinAccount !=null && loginUser.weixinAccount != ''}">
									${loginUser.weixinAccount}
								</c:when>
								<c:otherwise>
									暂无
								</c:otherwise>
							</c:choose>
						</div>
						<div class="row">
							<i class="ico_mail"></i><b>邮箱：</b>
							<c:choose>
								<c:when
									test="${loginUser.email !=null && loginUser.email != ''}">
									${loginUser.email}
								</c:when>
								<c:otherwise>
									暂无
								</c:otherwise>
							</c:choose>

						</div>
					</td>
				</tr>
			</table>
			<a class="btn_cancel">知道了</a>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 联系我成功提示 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>

<script type="text/javascript">
var fileAccessPath = '${fileAccessPath}';
var basePath = '${basePath}';
var maxpage = ${maxpage};
var loginId = ${loginUser.userId};
var currentPage = '2';

/**
 * 查看更多
*/
$('#seemore_plugin_div').click(function(){
	$.ajax({
		  type: "GET",
		  url : "${path }/wx/user/getMorePlugins",
		  data:{loginId:loginId,currentPage:currentPage},
		  dataType: "json",
		  success: function(data){
			  if(currentPage <= maxpage){
				currentPage++;
				var html = '';
			    for (var i = 0; i < data.pageResult.length; i++){
					var pluginDetailPath = basePath + "wx/plugin/detail/"+data.pageResult[i].id; 
					var pluginicon = fileAccessPath+data.pageResult[i].icon;
					
			    	html += '<li>';
			    	html += '<section onclick="javascript:self.location=\''+pluginDetailPath+'\';event.stopPropagation();">';
					html += '<a class="imgbox"><img src="'+pluginicon+'" /></a>';
					html += '<h3>'+data.pageResult[i].name+'</h3>';
					html += '<p>'+data.pageResult[i].description+'</p>';
					html += '<div class="clear"></div>';
					html += '</section>';
					html += '</li>';
			    }
			    $('#pluginWxList').append(html);
			  }else{
				 $('#seemore_plugin_div').remove();
			  }
		  }
  });
  if(currentPage == maxpage){
    	$('#seemore_plugin_div').remove();
  }
});

/**
 * 加载更多好友
 */
var currentFrPage = '1';
var url_otherUserPage = '${url_otherUserPage}';

var winWidth=$('.friend_list .body').width();
$('.friend_list .body').scroll(function(){
	var wst = $('.friend_list .body').scrollLeft();
	var bodyWidth = $('.friend_list .body ul').width();
	if(wst+winWidth > bodyWidth-10){
		currentFrPage++;
		$.ajax({
			 type: "GET",
		     url : "${path }/wx/user/getMoreFriendes?loginId="+loginId+"&currentFrPage="+currentFrPage,
		     dataType: "json",
			 success: function(data){
				var bookshtml="";
				//var userDetailUrl = url_otherUserPage+data.pageResult[i].userId;
				 for (var i = 0; i < data.pageResult.length; i++){
					bookshtml+='<li>';
					bookshtml+='<a target="_blank" href="#" class="avatar"><img src="'+data.pageResult[i].headimgUrl+'" /></a>';
					bookshtml+='</li>';
				}
				$(".friend_list_cont").append(bookshtml);
			}
		});
	}
});
	
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
			  imgUrl:"${loginUser.headimgUrl}",
			  link: '${shareArg.shareUrl}',
			  desc:"${loginUser.introduce}",
			  title:"${loginUser.nickName}：我开发的插件，欢迎你来使用！"    
		  };
	    wx.onMenuShareTimeline(shareData);
	    wx.onMenuShareAppMessage(shareData);
	});
/* 	wx.error(function (res) {
	  alert(res.errMsg);
	}); */
	
</script>
