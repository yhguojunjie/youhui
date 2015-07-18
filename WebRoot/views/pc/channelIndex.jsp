<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
	<title>渠道</title>
</head>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp" %>
	<!-- 头部 结束 -->
	<script>
	</script>
		<!-- 登录 开始 -->
	<div class="w1190">
		<div class="main">
			<div class="tab_title" style="padding:0 12px 0 8px;">
				<div class="fl options">
					<a class="active">全部</a>
				</div>
				<div class="fr sorts">
					<span id="pluginPrice" value="3"><em class="down_arrow">
					<!-- up_arrow：上箭头；down_arrow下箭头。 -->
					</em>按均价</span> <span id="pluginBuynum" value="2"><em class="down_arrow"> 
					<!-- up_arrow：上箭头；down_arrow下箭头。 -->
					</em>按粉丝数</span> <span id="pluginPublish" value="1" class="active"><em class="down_arrow"> 
					<!-- up_arrow：上箭头；down_arrow下箭头。 -->
					</em>按发布</span>
				</div>
				<div class="clear"></div>
			</div>
			<ul class="channel_list">
			<!-- <div class="w1190" id="channelRecL">
			</div> -->
				<% //if(request.getSession().getAttribute(Globals.SESSION_USER) == null){ %>
				
				<% //}else{ %>
				<li class="service_box">
					<div class="space_30"></div>
					<div class="space_5"></div>
					<a href="${basePath}pc/channel/beChannel" class="btn publish_activity">成为渠道</a>
				</li>
				<%// }
				%>
				<div class="w1190"  id="channel">
				</div>
			</ul>
			<div id="ff" style="display:none"></div>
			<div class="clear space_15"></div>
			<div class="get_more"><span>努力加载中...</span></div>
		</div>
		<div class="clear space_10"></div>
	</div>
	<!-- 登录 结束 -->
	<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	//渠道列表
	//排序状态
	var publishTime_Flag = '0';
	var buyNum_Flag = '0';
	var price_Flag = '0';
	//查询参数
	var typeFlag = '0';
	var orderFlag = '1';
	var fileAccessPath = '${fileAccessPath}';
	var url_otherPage = '${url_otherPage }/';
	
	function getPageChannelDatas(currentPage) {
	  $.ajax({
	      type: "GET",
	      url: "${basePath}pc/channel/ajaxList?currentPage=" + currentPage + "&typeFlag=" + typeFlag + "&orderFlag=" + orderFlag + "&publishTime_Flag=" + publishTime_Flag + "&buyNum_Flag=" + buyNum_Flag + "&price_Flag=" + price_Flag + "&pageCount=20",
	      dataType: "json",
	      success: function(data) {
	          if (data != null && data != undefined) {
	              $('#channel').empty();
	              var html = '';
	              html += '<ul id="channelUi">';
	              if (data.length != 0) {
	                  for (var i = 0; i < data.length; i++) {
	                	  var userId=data[i].userId;
	                     // html += '<li > <a href="'+url_otherPage+userId+'">';
	                      //	html += '<li  onclick="javascript:window.open(\''+pluginDetailPath+'\')">';
	                      //	html += '<input type="hidden"  name="pluginId" value="'+data.pageResult[i].id+'"> ';
	                      html += '<li >';
	                      html += '<img src="' + data[i].logo + '" />';
	                      html += '<h3>';
	                      html += '<strong>' + data[i].name + '</strong>';
	                      if(data[i].type==1){
	                    	  html += '<i class="wechat_ico">订</i>'; 
	                      }
                          if(data[i].type==2){
                        	  html += '<i class="wechat_ico">服</i>';
	                      }
						  if(data[i].type==3){
							  html += '<i class="wechat_ico">个</i>';
						  }
						  if(data[i].type==4){
							  html += '<i class="sina_ico"></i>'; 						  
						  }
						  if(data[i].type==5){
							  html += '<i class="web_ico"></i>'; 						  
						  }
						  if(data[i].type==6){
							  html += '<i class="app_ico"></i>'; 						  
						  }
	                      html += '</h3>';
	                      html += '<span  style="display:block;">' + data[i].introduce + '</span>';
	                      html += '<div class="space_5 clear"></div>';
	                      if(data[i].fansNum!=null){						  
                          html += '<p>粉丝数：';
                          html += '<em class="quot_left">'+data[i].fansNum+'万</em>';
                          html += '</p>';
	                      }
	                      html += '<p>价位：';
	                      html += '<em class="quot_right">' + data[i].price + '元/条左右</em>';
	                      html += '</p>';
	                      html += '</li>';
	                      //html += '</a></li>';
	                  }
	              } else {
	                  //html += '<li>目前还没有渠道!</li>';
	              }
	              html += '</ul>';
	              $('#channel').html(html);
	          }
	      }
	  });
	}

	//初始化渠道列表
	$(function() {
	  getPageChannelDatas(1);
	  /**
		 *通过插件排序方式获取插件
		 *orderFlag orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		 *xFlag  	0表示降序，1表示升序
		 */
	  $('#pluginPrice,#pluginBuynum,#pluginPublish').click(function() {
	      //获取排序字段
	      orderFlag = $(this).attr("value");
	      /* alert('orderFlag----->'+orderFlag+'-->'
						+'buyNum_Flag----->'+buyNum_Flag+'-->'
						+'price_Flag----->'+price_Flag+'-->'
						+'publishTime_Flag----->'+publishTime_Flag); */
	      //获取（orderby）
	      if (publishTime_Flag == '0') {
	          publishTime_Flag = '1';
	      } else {
	          publishTime_Flag = '0';
	      }
	      if (price_Flag == '0') {
	          price_Flag = '1';
	      } else {
	          price_Flag = '0';
	      }
	      if (buyNum_Flag == '0') {
	          buyNum_Flag = '1';
	      } else {
	          buyNum_Flag = '0';
	      }
	     
	     //滚动条重置处理
	     $(".hhjj").empty();
	     $(".hhjj").remove();
	 	  start = 20;
		  count = 20;
		  ajaxload = false; 
		  
	      //排序方式(选中状态)
	      if (orderFlag == '3') {
	          $('#pluginPrice').attr("class", "active");
	          $('#pluginBuynum').attr("class", " ");
	          $('#pluginPublish').attr("class", " ");
	          if (price_Flag == '1') {
	              $('#pluginPrice').find('em').attr("class", "up_arrow");
	          } else {
	              $('#pluginPrice').find('em').attr("class", "down_arrow");
	          }
	      } else if (orderFlag == '2') {
	          $('#pluginPrice').attr("class", "");
	          $('#pluginBuynum').attr("class", "active");
	          $('#pluginPublish').attr("class", " ");
	          if (buyNum_Flag == '1') {
	              $('#pluginBuynum').find('em').attr("class", "up_arrow");
	          } else {
	              $('#pluginBuynum').find('em').attr("class", "down_arrow");
	          }
	      } else {
	          $('#pluginPrice').attr("class", "");
	          $('#pluginBuynum').attr("class", "");
	          $('#pluginPublish').attr("class", "active");
	          if (publishTime_Flag == '1') {
	              $('#pluginPublish').find('em').attr("class", "up_arrow");
	          } else {
	              $('#pluginPublish').find('em').attr("class", "down_arrow");
	          }
	      }
	      ajaxtip("努力加载中...");
	      getPageChannelDatas(1);
	  });
	});
	
	function scroll(){
		$(window).scroll(
				function() {
					var wst = $(window).scrollTop();
					var bodyHeight = $(document).height();
					if (!ajaxload) {
						console.log("start="+start);
						if (wst + winHeight > bodyHeight - 20) {
							ajaxtip("努力加载中...");
							console.log("inajax");
							if (iisstop==0) {
								iisstop=1; //锁定
								ff();
								ajax();
							iisstop=0; //解除锁定
							}
							}
							}
						});
	}
	
	//回到顶部
	$(function() {
	  //渠道推荐
	 // $("#channelRecL").load("${basePath}pc/channel/getChannelRecList");
	  //渠道列表（4个）
	  //$("#channel").load("${basePath}pc/channel/getChannelList");
	  $(".gotop").click(function() {
	      $("body,html").animate({
	          scrollTop: 0
	      },
	      300);
	  });
	  $(window).scroll(function() {
	      if ($(window).scrollTop() > 10) {
	          $('.gotop').attr("style", "display:block;");
	      } else {
	          $('.gotop').attr("style", "display:none;");
	      }
	  });
	});
	
	//加载更多渠道
	var start = 20;
	var count = 20;
	var ajaxload = false; 
	var iisstop=0;
	winHeight = $(window).height();
	
	scroll();
	function ff(){
		ajaxload = true;
	}
	function ajax(){
		$.ajax({
			type: "GET",
            url: "${basePath}pc/channel/ajaxList?currentPage=" + count + "&typeFlag=" + typeFlag + "&orderFlag=" + orderFlag + "&publishTime_Flag=" + publishTime_Flag + "&buyNum_Flag=" + buyNum_Flag + "&price_Flag=" + price_Flag + "&pageCount=4"+ "&start="+start,
            dataType: "json",
			success : function(data) {
				console.log("data.length="+data.length);
				if(data.length != 0){
					var html = "";
					 for (var i = 0; i < data.length; i++) {
						  var userId=data[i].userId;
                    	  var temp = '';
                    	  $('ul>li').each(function(){
                    		  //alert("777---");
                                if(temp.indexOf($(this).html())!=-1)
                                	//alert("777---");
                                    $(this).remove();
                                temp += $(this).html();
                            })
                          //html += '<li class="hhjj" > <a href="'+url_otherPage+userId+'">';
                          html += '<li class="hhjj" >';
                          html += '<img src="'+data[i].logo + '" />';
                          html += '<h3>';
                          html += '<strong>' + data[i].name + '</strong>';
                          if(data[i].type==1){
	                    	  html += '<i class="wechat_ico">订</i>'; 
	                      }
                          if(data[i].type==2){
                        	  html += '<i class="wechat_ico">服</i>';
	                      }
						  if(data[i].type==3){
							  html += '<i class="wechat_ico">个</i>';
						  }
						  if(data[i].type==4){
							  html += '<i class="sina_ico"></i>'; 						  
						  }
						  if(data[i].type==5){
							  html += '<i class="web_ico"></i>'; 						  
						  }
						  if(data[i].type==6){
							  html += '<i class="app_ico"></i>'; 						  
						  }
                          html += '</h3>';
                          html += '<span  style="display:block;word-wrap:break-word;">' + data[i].introduce + '</span>';
                          html += '<div class="space_5 clear"></div>';
                          if(data[i].fansNum!=null){
                       	  html += '<p>粉丝数：';
                          html += '<em class="quot_left">'+data[i].fansNum+'万</em>';
                          html += '</p>';
                          }
                          html += '<p>价位：';
                          html += '<em class="quot_right">' + data[i].price + '元/条左右</em>';
                          html += '</p>';
                          //html += '</a></li>';
                          html += '</li>';
                      }
					$(".channel_list").append(html);
					ajaxload = false;
					start = start + data.length;
				}else{
					ajaxload = true;
					ajaxtip("已经到底了哦！");
				}
			}
			 });
	}
function ajaxtip(txt) {
  $(".get_more span").html(txt);
}
</script>


