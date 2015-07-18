<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>淘插件</title>
</head>
<body>
	<!-- 多图滚动 开始 -->
	<div id="scrollBox" class="scrollBox">
	<a class="careme" href="http://mp.weixin.qq.com/s?__biz=MzA4MzA1MjY2MQ==&mid=216270537&idx=1&sn=4c55a0dab799fac9a2f72688687d94d1#rd&ADUIN=565009015&ADSESSION=1418173420&ADTAG=CLIENT.QQ.5329_.0&ADPUBNO=26349">
		<img src="${path }/images/wx/lacode.png" />
	</a>
		<div class="bd">
			<ul>
				<li>
					<a href="${wxurl_pluginDetail }/5"><img _src="${path }/images/wx/wechatbanner0.jpg" src="${path }/images/wx/wechatbanner0.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="${wxurl_pluginDetail }/4"><img _src="${path }/images/wx/wechatbanner1.jpg" src="${path }/images/wx/wechatbanner1.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="${wxurl_pluginDetail }/3"><img _src="${path }/images/wx/wechatbanner2.jpg" src="${path }/images/wx/wechatbanner2.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="${wxurl_pluginDetail }/2"><img _src="${path }/images/wx/wechatbanner3.jpg" src="${path }/images/wx/wechatbanner3.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="${wxurl_pluginDetail }/1"><img _src="${path }/images/wx/wechatbanner4.jpg" src="${path }/images/wx/wechatbanner4.jpg" /></a>
				</li>
			</ul>
		</div>
		<div class="hd">
			<span class="prev">&lt;</span>
			<ul></ul>
			<span class="next">&gt;</span>
		</div>
	</div>
	<!-- 多图滚动 结束 -->
	<!-- 插件列表 开始 -->
	<div class="space_20"></div>
	<div class="space_20"></div>
	<div class="tab_title" id="index_tab">
		<a id='pluginPublish' value = '1' class="active"><em>按发布</em></a>
		<a id='pluginBuynum' value = '2' ><em>按销量</em></a>
		<a id='pluginPrice' value = '3' ><em>按价格</em></a>
	</div>
	<div class="plugin_list">
		<ul class="plugin_list_cont" id="pluginWxList">
			<c:if test="${paginationPlugin.totalCount le 0 }">
				<li>目前还没有插件!</li>
			</c:if>
			<c:if test="${paginationPlugin.totalCount gt 0}">
				<c:forEach items="${paginationPlugin.pageResult}" var="pluginWxVo">
					<li>
						<div class="btns tr absolute">
							<c:if test="${pluginWxVo.price != 0.0}">
								<a class="btn_try">免费试用</a>
								<a class="btn_direct">￥${pluginWxVo.price}</a>
							</c:if>
							
							<c:if test="${pluginWxVo.price == 0.0}">
								<a href="javascript:void(0);"  class="btn_free">免费</a>
							</c:if>
						</div>
						<section onclick="javascript:self.location='${wxurl_pluginDetail}/${pluginWxVo.id}';event.stopPropagation();">
							<a class="imgbox"><img src="${fileAccessPath}${pluginWxVo.icon}" /></a>
							<h3>${pluginWxVo.name}</h3>
							<p class="tr"><a>@${pluginWxVo.nickName}</a><span>已有${pluginWxVo.buyNum}人购买</span></p>
							<div class="clear"></div>
							<div class="btns tr">
								<span><fmt:formatDate value="${pluginWxVo.publishTime}" pattern="yyyy/MM/dd  HH:mm" /></span>
							</div>
						</section>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="get_more">努力加载中...</div>
	<!-- 插件列表 结束 -->
	<%@ include file="footer.jsp"%>
	<%@ include file="menu.jsp"%>
</body>
</html>
<script type="text/javascript">
	var fileAccessPath = '${fileAccessPath}';
	var basePath = '${basePath}';
	var detailPath = '${wxurl_pluginDetail}';
	var count = '1';
	var orderFlag = '1';
	
	$(document).ready(function(){
		/**
		* 查询插件信息
		* typeFlag 0表示全部，1.表示即买即用
		* orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		* count 第几页（加载更多）
		*/
		$('#pluginPrice,#pluginBuynum,#pluginPublish').click(function() {
			count = '1';
			orderFlag = $(this).attr("value");
			setStates(orderFlag);
			getPageDatas(1); 
		})
	});
	
	//排序方式(选中状态)
	function setStates(orderFlag){
		if(orderFlag=='3'){
			$('#pluginPrice').attr("class","active");
			$('#pluginBuynum').attr("class"," ");
			$('#pluginPublish').attr("class"," ");
		}else if(orderFlag=='2'){
			$('#pluginPrice').attr("class","");
			$('#pluginBuynum').attr("class","active");
			$('#pluginPublish').attr("class"," ");
		}else{
			$('#pluginPrice').attr("class","");
			$('#pluginBuynum').attr("class","");
			$('#pluginPublish').attr("class","active");
		}
	}
	
	//Remove 是否删除之前查询的数据[0不删除，1删除]
	function getPageDatas(isRemove){
		$.ajax({
			  type: "GET",
			  url : "${path }/wx/plugin/pluginAjaxList?count="+count+"&orderFlag="+orderFlag,
			  dataType: "json",
			  success: function(data){
				if(data != null && data !=undefined){
					  var html = '';
					  if('1' == isRemove){
						  $('#pluginWxList').empty();
					  }
					  if(data.pageResult.length != 0){
							for (var i = 0; i < data.pageResult.length; i++) {
								var  str = data.pageResult[i].publishTime.toString();
			   					str =  str.replace(/-/g,"/");
			   					var publishTime = new Date(str);
			   					publishTime = publishTime.Format("yyyy/MM/dd hh:mm");  
								var pluginDetailPath = detailPath+"/"+data.pageResult[i].id; 
								var pluginicon = fileAccessPath+data.pageResult[i].icon; 
								html += '<li>';
								html += '<div class="btns tr absolute">';
								
								if(data.pageResult[i].price != null && data.pageResult[i].price != 0.0){
									html += '<a href="javascript:void(0);"  class="btn_try">免费试用</a>';
									html += '<a href="javascript:void(0);"  class="btn_direct">￥'+data.pageResult[i].price+'</a>';
								}
								
								if(data.pageResult[i].price != null && data.pageResult[i].price == 0.0){
									html += '<a href="javascript:void(0);"  class="btn_free">免费</a>';
								}
								
								html += '</div>';
								html += '<section onclick="javascript:self.location=\''+pluginDetailPath+'\';event.stopPropagation();">';
								html += '<a class="imgbox"><img src="'+pluginicon+'" /></a>';
								html += '<h3>'+data.pageResult[i].name+'</h3>';
								html += '<p class="tr"><a>@'+data.pageResult[i].nickName+'</a><span>已有'+data.pageResult[i].buyNum+'人购买</span></p>';
								html += '<div class="clear"></div>';
								html += '<div class="btns tr">';
								html += '<span>'+publishTime+'</span>';
								html += '</div>';
								html += '</section>';
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
	
	//加载更多插件
	var winHeight=$(window).height(),
	maxpage=${maxpage};
	$(window).scroll(function(){
		var wst=$(window).scrollTop();
		var bodyHeight=$(document).height();
		if(wst+winHeight>bodyHeight-10){
			count++;
			if(count <= maxpage){
				ajaxtip("努力加载中...");
				$.ajax({
					  type: "GET",
					  url : "${path }/wx/plugin/pluginAjaxList?count="+count+"&orderFlag="+orderFlag,
					  dataType: "json",
					  success: function(data){
						if(data != null && data !=undefined){
							  var html = '';
									for (var i = 0; i < data.pageResult.length; i++) {
										var  str = data.pageResult[i].publishTime.toString();
					   					str =  str.replace(/-/g,"/");
					   					var publishTime = new Date(str);
					   					publishTime = publishTime.Format("yyyy/MM/dd hh:mm");  
										var pluginDetailPath = detailPath + "/"+data.pageResult[i].id; 
										var pluginicon = fileAccessPath+data.pageResult[i].icon; 
										html += '<li>';
										html += '<div class="btns tr absolute">';
										
										if(data.pageResult[i].price != null && data.pageResult[i].price != 0.0){
											html += '<a href="javascript:void(0);"  class="btn_try">免费试用</a>';
											html += '<a href="javascript:void(0);"  class="btn_direct">￥'+data.pageResult[i].price+'</a>';
										}
										
										if(data.pageResult[i].price != null && data.pageResult[i].price == 0.0){
											html += '<a href="javascript:void(0);"  class="btn_free">免费</a>';
										}
										
										html += '</div>';
										html += '<section onclick="javascript:self.location=\''+pluginDetailPath+'\';event.stopPropagation();">';
										html += '<a class="imgbox"><img src="'+pluginicon+'" /></a>';
										html += '<h3>'+data.pageResult[i].name+'</h3>';
										html += '<p class="tr"><a>@'+data.pageResult[i].nickName+'</a><span>已有'+data.pageResult[i].buyNum+'人购买</span></p>';
										html += '<div class="clear"></div>';
										html += '<div class="btns tr">';
										html += '<span>'+publishTime+'</span>';
										html += '</div>';
										html += '</section>';
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
		    title: "淘插件，营销推广插件超市",
		    desc: "微活动、微游戏一网打尽。“试用、购买、配置”，只需三步，如此简单！",
		  	link: '${shareArg.shareUrl}',
		    imgUrl: "${basePath}images/108px.png"
		  };
	    wx.onMenuShareTimeline(shareData);
	    wx.onMenuShareAppMessage(shareData);
	});
/* 	wx.error(function (res) {
	  alert(res.errMsg);
	}); */
	
</script>		