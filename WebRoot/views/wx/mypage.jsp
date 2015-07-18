<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>个人主页</title>
</head>
<body>
	<div class="space_20"></div>
	<!-- 个人主页 开始 -->
	<div class="user_center">
		<div class="card" style="border-bottom:2px solid #63c7bb;">
			<a class="avatar"><img src="${loginUser.headimgUrl}" /></a>
			<h3>
				<a class="title">${loginUser.nickName}</a><i class="btn_edit"></i>
				<span class="contact_btn"><a class="btn_pink">联系我</a></span>
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
	<%--	<div class="friend">
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
				<div class="cover_layout"></div>
			</div>
	 		<div class="friend_list">
				<div class="body">
					<c:if test="${frendes le 0 }">
						<p>您还没有粉丝</p>
					</c:if>
					<c:if test="${frendes gt 0}">
					<ul class="friend_list_cont">
						<c:forEach items="${userFriends.pageResult}" var="userFriend">
							<li>
								<i class="ico_bedelete" id="${userFriend.userId}"></i>
								<a href="${url_otherUserPage}/${userFriend.userId}" class="avatar"><img src="${userFriend.headimgUrl}" /></a>
							</li>
						</c:forEach>
					</ul>
					</c:if>
				</div>
			</div>
		</div> --%>
		<div class="my_activitys">
			<h2>我的活动（${myPluginActCount}）</h2>
			<c:if test="${myPluginActCount le 0 }">
				<div class="if_none"><p>还没有活动，挑选<a href="${basePath }" style="color:#63c7bb;">插件</a></p></div>
			</c:if>
			<ul id="pluginActWxList">
				<c:if test="${myPluginActCount gt 0}">
					<c:forEach items="${myPluginActVos.pageResult}" var="myPluginActVo">
						<li>
							<c:if test="${myPluginActVo.type == '0' }">
								<section onclick="javascript:self.location='${cjShowServer }${myPluginActVo.actAccessUrl}/${myPluginActVo.id}'">
								<a class="imgbox"><img src="${fileAccessPath }${myPluginActVo.icon}"></a>
							</c:if>
							<c:if test="${myPluginActVo.type == '1' }">
								<section onclick="javascript:self.location='${myPluginActVo.accessUrl}'">
								<c:choose>
									<c:when test="${myPluginActVo.icon !=null && myPluginActVo.icon !='' }">
										<a class="imgbox"><img src="${fileAccessPath }${myPluginActVo.icon}"></a>
									</c:when>
									<c:otherwise>
										<img class="imgbox" src="${path}/images/wx/outact.gif" />
									</c:otherwise>
								</c:choose>
							</c:if>
								
								<a class="title">${myPluginActVo.title}</a>
								<div class="clear"></div>
							</section>
							<i class="ico_bedelete" id="${myPluginActVo.id}"></i>
						</li>
					</c:forEach>
				</c:if>
			</ul>
			<c:if test="${myPluginActCount gt WXPAGE_SHOW_NUMBER}">
				<div class="seemore" id="seemore_plugin_div">查看更多</a></div>
			</c:if>
			<div class="cover_layout"></div>
		</div>
	</div>
	<!-- 个人主页 结束 -->
	<div class="space_20"></div>
	<!-- 其他商家活动 开始 -->
	<div class="activity_recomd">
		<div class="norl_title">
			<h3>其他商家活动</h3>
		</div>
		<ul>
			<c:forEach items="${otherPluginActVos}" var="otherPluginActVo">
				<li>
					<c:if test="${otherPluginActVo.type == '0' }">
						<section onclick="javascript:self.location='${cjShowServer }${otherPluginActVo.actAccessUrl}/${otherPluginActVo.id}'">
						<a class="imgbox"><img src="${fileAccessPath }${otherPluginActVo.icon}" /></a>
					</c:if>
					<c:if test="${otherPluginActVo.type == '1' }">
						<section onclick="javascript:self.location='${otherPluginActVo.accessUrl}'">
						<c:choose>
							<c:when test="${otherPluginActVo.icon !=null && otherPluginActVo.icon !='' }">
								<a class="imgbox"><img src="${fileAccessPath }${otherPluginActVo.icon}" /></a>
							</c:when>
							<c:otherwise>
								<img class="imgbox" src="${path}/images/wx/outact.gif" />
							</c:otherwise>
						</c:choose>						
					</c:if>
						<a class="title">${otherPluginActVo.title}</a>
						<div class="clear"></div>
					</section>
					<a href="${url_otherUserPage}/${otherPluginActVo.userId}" class="enterprise">${otherPluginActVo.nickName}</a>
				</li>
			</c:forEach>
		</ul>
		<div class="cover_layout"></div>
	</div>
	<div class="space_30"></div>
	<!-- 其他商家活动 结束 -->
	
	<!-- 浮动按钮 开始 -->
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="float_btns hide" id="show">
		<a class="btn_edit">编&nbsp;&nbsp;辑</a>
		<!-- <a class="btn_invit">邀好友&nbsp;&nbsp;抢礼包</a> -->
	</div>
	<div class="float_btns hide">
		<a class="btn_cancel">取&nbsp;消</a>
		<a class="btn_done">完&nbsp;成</a>
	</div>
	<!-- 浮动按钮 结束 -->
	<!-- 被抢走提示框 开始 -->
	<div class="nogift_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<p>
				<img src="images/avatar.jpg">
				<span>嘿嘿，被我抢走了！</span>
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
			随机赠送给粉丝
			</p>
			<p>
				<span>邀请微信好友成为你的“粉丝”，来抢礼包</span>
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
									<%-- <a target="_blank"
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
	<!-- 联系我失败提示 开始 -->
	<div class="contact_faibox">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<p>
				<span>你还没有填写任何联系方式</span>
			</p>
			<p>
				<span>快去“<a href="#">帐号设置</a>”，补全资料吧~</span>
			</p>
			<div class="space_20"></div>
			<div class="tc">
				<a class="btn_cancel">取消</a>
				<a class="btn_done">确定</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 联系我失败提示 结束 -->
	<!-- 删除好友提示 开始 -->
	<div class="delfriend_box">
		<div class="cont">
			<h3>
				<strong>提示</strong>
				<span class="close"><!-- 关闭窗口按钮 -->╳</span>
			</h3>
			<p>
				<span>不可恢复，确定删除？</span>
			</p>
			<div class="tc">
				<a class="btn_cancel">取消</a>
				<a class="btn_done">确定</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	<!-- 删除好友提示 结束 -->
	<!-- 分享&邀请好弹出对话框 开始 -->
	<div class="share">
		<i class="aorrw"></i>
		<div class="clear"></div>
		<p>点击右上角</p>
		<p>【发送给朋友】或【分享到朋友圈】</p>
		<p class="notice">首次将插件库分享到朋友圈<br />即可获得10个代币</p>
		<p class="source">淘插件<span>营销推广插件超市</span></p>
	</div>
	<!-- 分享&邀请好弹出对话框 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>

<script type="text/javascript">
var fileAccessPath = '${fileAccessPath}';
var basePath = '${basePath}';
var cjShowServer = '${cjShowServer}';
var maxpage = ${maxpage};
var loginId = ${loginUser.userId};
var currentPage = '2';

/**
 * 查看更多
*/
$('#seemore_plugin_div').click(function(){
	$.ajax({
		  type: "GET",
		  url : "${path }/wx/user/getMoreActes",
		  data:{loginId:loginId,currentPage:currentPage},
		  dataType: "json",
		  success: function(data){
			  if(currentPage <= maxpage){
				currentPage++;
				var html = '';
			    for (var i = 0; i < data.pageResult.length; i++){
					var pluginActPath = "#"; 
					var pluginActicon = fileAccessPath+data.pageResult[i].icon;
					if(data.pageResult[i].type == '0'){
						pluginActPath = cjShowServer+data.pageResult[i].actAccessUrl+'/'+data.pageResult[i].actId;
					}
					if(data.pageResult[i].type == '1'){
						pluginActPath = data.pageResult[i].accessUrl;
						if(data.pageResult[i].icon == null || data.pageResult[i].icon == ''){
							pluginActicon = basePath+"images/wx/outact.gif";
						}
					}
			    	html += '<li>';
			    	html += '<section onclick="javascript:self.location=\''+pluginActPath+'\';event.stopPropagation();">';
					html += '<a class="imgbox"><img src="'+pluginActicon+'" /></a>';
					html += '<a class="title">'+data.pageResult[i].title+'</a>';
					html += '<div class="clear"></div>';
					html += '</section>';
					html += '<i class="ico_bedelete"></i>';
					html += '</li>';
			    }
			    $('#pluginActWxList').append(html);
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

$(document).ready(function(){
	//开始编辑
	$('.float_btns .btn_edit').click(function(){
		$('.my_activitys ul').attr("class","can_del");
		$('.friend_list_cont').attr("class","friend_list_cont can_del");
		$('.user_center .card h3 .btn_edit').attr("style","display:block;");
		$(this).parent().attr("id"," ");
		$(this).parent().next().attr("id","show");
		$('.active_layout,.cover_layout').attr("style","display:block;");
	});
	var frRemoveIds = actRemoveIds = "";
	//删除好友
	$('.friend_list li .ico_bedelete').click(function(){
		frRemoveIds += $(this).attr("id") + ',';
		$(this).attr("class","ico_delete");
		$(this).parents('li').attr("style","display:none;");
	});

	//删除活动
	$('.my_activitys li .ico_bedelete').click(function(){
		actRemoveIds += $(this).attr("id") + ',';
		$(this).attr("class","ico_delete");
		$(this).parents('li').attr("style","display:none;");
	});

	//确认删除
	$('.float_btns .btn_done').click(function(){
		$('.my_activitys ul').attr("class"," ");
		$('.friend_list_cont').attr("class","friend_list_cont");
		$(this).parent().attr("id"," ");
		$(this).parent().prev().attr("id","show");
		$('.friend_list li .ico_delete').each(function(){
			$(this).parents('li').remove();
		});
		$('.my_activitys li .ico_delete').each(function(){
			$(this).parents('li').remove();
		});
		removePluginActFr(frRemoveIds,actRemoveIds);
		frRemoveIds = actRemoveIds = "";
		$('.user_center .card h3 .btn_edit').attr("style","display:none;");
		$('.active_layout,.cover_layout').attr("style","display:none;");
	});
	
	//取消删除
	$('.float_btns .btn_cancel').click(function(){
		$('.my_activitys ul').attr("class"," ");
		$('.friend_list_cont').attr("class","friend_list_cont");
		$(this).parent().attr("id"," ");
		$(this).parent().prev().attr("id","show");
		$('.friend_list li .ico_delete').each(function(){
			$(this).parents('li').attr("style","");
		});
		$('.my_activitys li .ico_delete').each(function(){
			$(this).parents('li').attr("style","");
		});
		$('.user_center .card h3 .btn_edit').attr("style","display:none;");
		$('.active_layout,.cover_layout').attr("style","display:none;");
	});

})

/**
 * 删除活动,删除好友
 */
function removePluginActFr(frRemoveIds,actRemoveIds){
	$.ajax({
		  type: "POST",
		  url : "${path }/wx/user/removeActFr",
		  data:{frRemoveId:frRemoveIds,actRemoveId:actRemoveIds,loginId:loginId},
		  dataType: "json",
		  success: function(data){
		  }
  }); 
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
		    imgUrl:"${loginUser.headimgUrl}",
		    link: '${shareArg.shareUrl}',
			desc:"${loginUser.introduce}",
			title:"${loginUser.nickName}：我举办的活动，欢迎你来参加！"		    
		  };
	    wx.onMenuShareTimeline(shareData);
	    wx.onMenuShareAppMessage(shareData);
	});
/* 	wx.error(function (res) {
	  alert(res.errMsg);
	}); */
	
</script>