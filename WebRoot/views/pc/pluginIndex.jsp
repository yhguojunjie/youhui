<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>模版库</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp"%>
	<!-- 头部 结束 -->
	
	<!-- 内容 开始 -->
	<div class="w1190 index">
		<!-- 左侧内容 开始 -->
		<div class="fl w860 main">
			<div class="tab_title">
				<div class="fl options">
					<!--插件类型(1 即插即用型，2用户定制)  -->
					<span id="plugin_type_all" value="0" class="active">全部</span> 
					<!-- <span id="plugin_type_1" value="1" class="">即买即用</span> -->
				</div>
				<div class="fr sorts">
					<span id="pluginPrice" value="3">
						<em class="down_arrow">
						<!-- up_arrow：上箭头；down_arrow下箭头。 -->
						</em>价格
					</span> 
					<span id="pluginBuynum" value="2">
						<em class="down_arrow"> 
						<!-- up_arrow：上箭头；down_arrow下箭头。 -->
						</em>销量
					</span> 
					<span id="pluginPublish" value="1" class="active">
						<em class="down_arrow"> 
						<!-- up_arrow：上箭头；down_arrow下箭头。 -->
						</em>发布
					</span>
				</div>
				<div class="clear"></div>
			</div>
			<div id="pluginList" class="cont">
				<ul class="demo_list">
			<%-- 		<c:forEach items="${pluginRecList }" var="pluginRec">
						<li onclick="javascript:self.location='${url_pluginDetail}/${pluginRec.id }'">
							<div class="rehot">推荐</div>
							<a href="#" class="img"><img src="${pluginRec.icon }" /></a>
							<h3>
								<a href="#"><strong>${pluginRec.name }</strong></a>
								<a href="#" class="author">@${pluginRec.nickName }</a>
								<span><fmt:formatDate value="${pluginRec.publishTime}" pattern="yyyy/MM/dd HH:mm"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<em>已有${pluginRec.buyNum }人购买</em></span>
							</h3>
							<div class="intro">
								<p>
									<em class="quot_left"></em>
									<span>${pluginRec.description }</span>
									<em class="quot_right"></em>
								</p>
								<a href="javascript:void(0);" class="btn_try">免费试用</a>
								<a class="btn_direct">
									<c:choose>
										<c:when test="${pluginRec.salePrice != '0.0' }">
											￥${pluginRec.salePrice  }
										</c:when>
										<c:otherwise>
											价格面议
										</c:otherwise>
									</c:choose>
								</a>
							</div>
							<div class="clear"></div>
						</li>
					</c:forEach> --%>
					
					
				</ul>
			</div>
		<div class="clear"></div>	
		<div class="space_20"></div>	
		<div class="get_more"><span>努力加载中...</span></div>
		
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w300 side" id="pluginLib_plgprev">
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->

	<!-- 底部 开始 -->
	<%@ include file="footer.jsp"%>
	<!-- 底部 结束 -->

</body>
</html>
<script type="text/javascript">
     gjj='0';
</script>
<script type="text/javascript">

	var url_pluginDetail = '${url_pluginDetail}/';
	var fileAccessPath = '${fileAccessPath}';
	var basePath = '${basePath}';
	var typeFlag = '0';
	var orderFlag = '1';
	var start = 0;
	//排序状态
	var buyNum_Flag = '0';
	var price_Flag = '0';
	var publishTime_Flag=0;
	
	if('${publishTime_Flag}' != null && '${publishTime_Flag}' != undefined && '${publishTime_Flag}' != ''){
		publishTime_Flag = '${publishTime_Flag}';
	}
	if('${price_Flag}'!= null && '${price_Flag}'!= undefined && '${price_Flag}' != ''){
		price_Flag = '${price_Flag}';
	}
	if('${buyNum_Flag}'!= null && '${buyNum_Flag}'!= undefined && '${price_Flag}' != ''){
		buyNum_Flag = '${buyNum_Flag}';
	}
	
	if('${orderFlag}' != null && '${orderFlag}'!= undefined && '${orderFlag}' != ''){
		orderFlag = '${orderFlag}';
	}
	
	//初始化列表
	$(document).ready(function(){
		getPageChannelDatas();
		if('${orderFlag}'=='3'){
			$('#pluginPrice').attr("class","active");
			$('#pluginBuynum').attr("class"," ");
			$('#pluginPublish').attr("class"," ");
			if('${price_Flag}' == '1'){
				$('#pluginPrice').find('em').attr("class","up_arrow");
			}else{
				$('#pluginPrice').find('em').attr("class","down_arrow");
			}
		}else if('${orderFlag}'=='2'){
			$('#pluginPrice').attr("class","");
			$('#pluginBuynum').attr("class","active");
			$('#pluginPublish').attr("class"," ");
			if('${buyNum_Flag}' == '1'){
				$('#pluginBuynum').find('em').attr("class","up_arrow");
			}else{
				$('#pluginBuynum').find('em').attr("class","down_arrow");
			}
		}else{
			$('#pluginPrice').attr("class","");
			$('#pluginBuynum').attr("class","");
			$('#pluginPublish').attr("class","active");
			if('${publishTime_Flag}' == '1'){
				$('#pluginPublish').find('em').attr("class","up_arrow");
			}else{
				$('#pluginPublish').find('em').attr("class","down_arrow");
			}
		}
		//模板预告
		$("#pluginLib_plgprev").load("${basePath}pc/recommend/getPluginPreRecList");
		
		/**
		 *通过插件排序方式获取插件
		 *orderFlag orderFlag 1表示发布时间publishTime，2.表示销量buyNum,3.表示价格price
		 *xFlag 0表示降序，1表示升序
		 */
		$('#pluginPrice,#pluginBuynum,#pluginPublish').click(function() {
				//获取排序字段
				orderFlag = $(this).attr("value");
				if(orderFlag == '1'){
					if(publishTime_Flag== '0'){
						publishTime_Flag = '1';
						buyNum_Flag = '0';
						price_Flag = '0';
					}else{
						publishTime_Flag = '0';
						buyNum_Flag = '0';
						price_Flag = '0';
					}
				}
				
				if(orderFlag == '2'){
					if(buyNum_Flag== '0'){
						buyNum_Flag = '1';
						publishTime_Flag = '0';
						price_Flag = '0';
					}else{
						buyNum_Flag = '0';
						publishTime_Flag = '0';
						price_Flag = '0';
					}
				}
				
				if(orderFlag == '3'){
					if(price_Flag  == '0'){
						price_Flag = '1';
						publishTime_Flag = '0';
						buyNum_Flag = '0';
					}else{
						price_Flag = '0';
						publishTime_Flag = '0';
						price_Flag = '0';
					}
				}
		
			window.location.href = "${basePath }pc/plugin/pluginList?typeFlag="+typeFlag+"&orderFlag="+orderFlag+
			"&publishTime_Flag="+publishTime_Flag+"&buyNum_Flag="+buyNum_Flag+"&price_Flag="+price_Flag;
		});
		
	});
	
	
	function getPageChannelDatas() {
		  $.ajax({
			  type: "GET",
			  url : "${basePath }pc/plugin/ajaxList?&typeFlag="+typeFlag+"&orderFlag="+orderFlag+
					"&publishTime_Flag="+publishTime_Flag+"&buyNum_Flag="+buyNum_Flag+"&price_Flag="+price_Flag+"&start="+start,
			  dataType: "json",
			  success: function(data){
		          if (data != null && data != undefined && data.length != 0) {
		        	    var html="";
						for (var i = 0; i < data.length; i++){
							var pluginDetailPath = url_pluginDetail + data[i].id; 
			      			var publishTime;
			     			if(data[i].publishTime != null && data[i].publishTime != undefined
									&& data[i].publishTime != ''){
			     				 publishTime = data[i].publishTime.toString().trim();
			     				 publishTime =  publishTime.replace(/(\d{4})-(\d{2})-(\d{2})T(.*)?\.(.*)/, "$1/$2/$3 $4");
			     				 publishTime = publishTime.replace(".0","").substring(0,16);
							}
			     			
							html+='<li onclick="javascript:self.location=\''+pluginDetailPath+'\'">\
								 <a href="#" class="img"><img src="'+data[i].icon+'" /></a>\
								 <h3>\
									<a href="#"><strong>'+data[i].name+'</strong></a>\
									<a href="#" class="author">@'+data[i].nickName+'</a>\
									<span>'+publishTime+'&nbsp;&nbsp;&nbsp;&nbsp;<em>已有'+data[i].buyNum+'人购买</em></span>\
								</h3>\
								<div class="intro">\
									<p>\
										<em class="quot_left"></em>\
										<span>'+data[i].description+'</span>\
										<em class="quot_right"></em>\
									</p>\
									<a href="#" class="btn_try">免费试用</a>';
									if(data[i].salePrice != 0.0){
										html+='<a class="btn_direct">￥'+data[i].salePrice+'.0</a>'; 
									}else{
										html+='<a class="btn_direct">价格面议</a>'; 
									}
									
									html+='</div>\
								     <div class="clear"></div>\
									</li>';
						}
						ajaxload=false;
						start = start + data.length;
						$(".demo_list").append(html);
		          }else{
			      		ajaxload=true;
						ajaxtip("已经到底了哦！");
		          }
		      }
		  });
		}
	
	function scroll3(){
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
								getPageChannelDatas();
								iisstop=0; //解除锁定
							}
						}
					}
		});
	}
	
    var ajaxload = false;
	var iisstop=0;
	winHeight = $(window).height();
	scroll3();
	function ff(){
		ajaxload = true;
	}
	
	function ajaxtip(txt){
		$(".get_more span").html(txt);
	}
	
	$(function(){
		$('.users_btn').hover(function(){
			$('.users_btns').attr("style","display:block;");
		},function(){
			$('.users_btns').attr("style","display:none;");
		});
	
		$(".gotop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		$(window).scroll(function(){
			if($(window).scrollTop() > 10){
				$('.gotop').attr("style","display:block;");
			}else{
				$('.gotop').attr("style","display:none;");
			}
		});
	});
</script>
