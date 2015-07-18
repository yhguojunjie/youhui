<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>交易记录</title>
</head>
<body>
	<div class="space_20"></div>
	<!-- 交易记录 开始 -->
	<div class="deal_record">
		<div class="norl_title">
			<h3><i class="gold"></i>代币：<em>￥${repreCoin}</em></h3>
			<!-- <a class="paycash">赚代币</a> -->
		</div>
		<c:if test="${userTrageLogVos.totalCount le 0 }">
			<div class="if_none">
				<p>您还没有交易记录!</p>
			</div>
		</c:if>
		<ul id="userTrangLogList">
			<c:if test="${userTrageLogVos.totalCount gt 0}">
				<c:forEach items="${userTrageLogVos.pageResult}" var="userTrageLogVo">
					<li>
						<span class="fr"><fmt:formatDate value="${userTrageLogVo.createTime}" pattern="yyyy/MM/dd HH:mm" /></em>
						</span>
						${userTrageLogVo.description}
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="get_more">努力加载中...</div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	<div class="space_30"></div>
	
	<!-- 交易记录 结束 -->
	<div class="space_20"></div>
	<div class="space_20"></div>
	<!-- 赚代币弹出对话框 开始 -->
	<div class="earn_box">
		<dl>
			<dt>代币可代替现金在淘插件平台上购买插件和服务</dt>
			<dd class="space_10">可通过以下方式获得代币：</dd>
			<dd>1、分享首页到微信朋友圈，10个代币。<a class="go_share">去分享</a></dd>
			<dd>2、个人主页插友数突破10，20个代币。<a class="go_invite">邀好友</a></dd>
			<dd class="tc space_15"><a class="btn_cancel">知道了</a></dd>
		</dl>
		<div class="bg"></div>
	</div>
	<!-- 赚代币弹出对话框 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">
	var fileAccessPath = '${fileAccessPath}';
	var basePath = '${basePath}';
	var currentPage = '1';
	
	//加载更多
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
					  url : "${path }/wx/userTradeLog/ajaxList?currentPage="+currentPage,
					  dataType: "json",
					  success: function(data){
						if(data != null && data !=undefined){
							  var html = '';
									for (var i = 0; i < data.pageResult.length; i++) {
										var  str = data.pageResult[i].createTime.toString();
					   					str =  str.replace(/-/g,"/");
					   					var createTime = new Date(str);
					   					createTime = createTime.Format("yyyy/MM/dd hh:mm");  
					   					var html = '';
					   					html +='<li>';
					   					html +='<span class="fr">'+createTime+'</em>';
					   					html +='</span>';
					   					html +='data.pageResult[i].description';
					   					html +='</li>';
								}
								$('#userTrangLogList').append(html); 
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
// 	    appId: '${shareArg.appId}',
// 	    timestamp: '${shareArg.timestamp}',
// 	    nonceStr: '${shareArg.nonceStr}',
// 	    signature: '${shareArg.signature}',
	    jsApiList: [
	        'wx.hideOptionMenu()'
	    ]
	});
	wx.ready(function () {
		wx.hideOptionMenu();
	});
</script>		


