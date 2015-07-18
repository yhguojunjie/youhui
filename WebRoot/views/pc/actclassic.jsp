<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>经典案例</title>
</head>
<body>

	<body>
		<!--[if lte IE 8]>
		<div class="ie_version">系统检测到您所使用的浏览器版本较低，推荐使用<em>Firefox</em>或<em>Chrome</em>浏览器打开，否则将无法体验完整产品功能。</div>
	<![endif]-->
		<!-- 头部 开始 -->
		<%@ include file="header.jsp"%>
		<!-- 头部 结束 -->
		<!-- 内容 开始 -->
		<div class="w1190">
			<div class="tab_title">
				<div class="fl options">
					<a href="${url_act }">全部</a> 
					<a href="${url_classic }" class="active">经典案例</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="classic">
				
				<ul class="classic_list">
					<c:forEach items="${actList }" var="act">
					  <li>
						<dl>
							<dt>
								<img src="${act.bannerLogo }" />
								<h4>${act.bannerName }</h4>
								<div class="clear space_7"></div>
								<div class="line"></div>
								<p>
									活动名称：<span>${act.actTitle }</span>
								</p>
								<p>活动时间：<fmt:formatDate value="${act.startTime}" pattern="yyyy/MM/dd "/> - <fmt:formatDate value="${act.endTime}" pattern="yyyy/MM/dd "/></p>
								<p >
									<em >浏览量：<span>${act.browseNum }</span></em>
								</p>
								<div class="line"></div>
								<p>
									使用模板：<a href="${url_pluginDetail}/${act.pluginId }">《${act.pluginName }》</a>
								</p>
							</dt>
							<dd>
								<div class="imgs">
								  <c:if test="${act.actClassicPicList != null }">
								  	<c:forEach items="${act.actClassicPicList}" var="actPic">
								  		<a class="img" >
								  			<img src="${actPic.url }" />
								  		</a>
								  	</c:forEach>
								  </c:if>
								</div>
								<c:if test="${(act.actClassicPicList != null) && (act.actClassicPicList.size() != 1)}">
							
								<div class="turn">
								  <a class="turn_next" onclick="go_next(this)">&gt;</a>
								  <a class="turn_prev" onclick="go_prev(this)">&lt;</a>
							    </div> 
							    
							  
								</c:if>
						 
							</dd>
							</dl>
						</li>
					
					</c:forEach>
				</ul>
				<div class="clear"></div>
				<div class="get_more">
					<span>努力加载中...</span>
				</div>
			</div>
		</div>
		<!-- 内容 结束 -->
		<!-- 底部 开始 -->
		<%@ include file="footer.jsp"%>
		<!-- 底部 结束 -->
	</body>

	<script type="text/javascript">
		var url_pluginDetail = '${url_pluginDetail}';
		var url_otherUserPage = '${url_otherUserPage}';
		var fileAccessPath = '${fileAccessPath}';
		var cjShowServer = '${cjShowServer}';
		var url_getQcode = '${url_getQcode}';
		var basePath = '${basePath}';
		var cjpcShowServer = '${cjpcShowServer}';
		
		function scroll2(){
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
		//加载更多经典案例
		var start = 8;
		var ajaxload = false;
		var iisstop=0;
		winHeight = $(window).height();
		scroll2();
		function ff(){
			ajaxload = true;
		}
		function ajax(){
			$.ajax({
				type : "GET",
				url : "${basePath}pc/act/ajaxClassicList",
				dataType : "json",
				data: {"start":start},
				success:function(data) {
					console.log("data.length="+data.length);
					if(data.length != 0){
						var html = "";
						for (var i = 0; i < data.length; i++) {
			     			var startTime;
			     			if(data[i].startTime != null && data[i].startTime != undefined
									&& data[i].startTime != ''){
								var  str1 = data[i].startTime.toString();
			   					str1 =  str1.replace(/-/g,"/");
			   					startTime = new Date(str1);
			   					startTime = startTime.Format("yyyy/MM/dd"); 
							}
			     			
			     			var endTime;
			     			if(data[i].endTime != null && data[i].endTime != undefined
									&& data[i].endTime != ''){
								var  str1 = data[i].endTime.toString();
			   					str1 =  str1.replace(/-/g,"/");
			   					endTime = new Date(str1);
			   					endTime = endTime.Format("yyyy/MM/dd"); 
							}
			     			var cjpjShowPath = cjpcShowServer+data[i].actAccessUrl+'/'+data[i].actId;
							html += '<li>\
									 <dl>\
									 <dt>\
									 <img src="'+data[i].bannerLogo+'" />\
									 <h4>'+data[i].bannerName+'</h4>\
									 <div class="clear space_7"></div>\
									 <div class="line"></div>\
									 <p>活动名称：<span>'+data[i].actTitle+'</span></p>\
									 <p>活动时间：'+startTime+' - '+endTime+'</p>\
									 <p >\
									 <em >浏览量：<span>'+data[i].browseNum+'</span></em>\
									 </p>\
									 <div class="line"></div>\
									 <p>使用模版：<a href="'+url_pluginDetail+'/'+data[i].pluginId+'">《'+data[i].pluginName+'》</a></p>\
									 </dt>\
									 <dd>';
									 if(data[i].actClassicPicList!=null){
									 if(data[i].actClassicPicList.length != null){
										 html += '<div class="imgs">';
										   for(var j = 0; j < data[i].actClassicPicList.length; j++){
											   html += '<a class="img"><img src="'+data[i].actClassicPicList[j].url+'" /></a>';
										   }
										 html += '</div>';
										
											
									 }
									 }
									 if((data[i].actClassicPicList.length != null)&&(data[i].actClassicPicList.length != 1)){
										 html +='<div class="turn">';
										 html +='<a class="turn_next" onclick="go_next(this)">&gt;</a>';
										 html +='<a class="turn_prev" onclick="go_prev(this)">&lt;</a>';
										 html +='</div>'; 
									 }
									 html += '</dd>\
											 </dl>\
											 </li>';
									
								     
						}

						$(".classic_list").append(html);
						$('.classic li').each(function() {
							 if ($(this).index() % 4 == 0) {
								$(this).css("margin-left","0px");
							  }
						 });
						ajaxload = false;
						start = start + data.length;
					}else{
						ajaxload = true;
						ajaxtip("已经到底了哦！");
					}

				}
		});
		}
		$(window).scroll(
			function() {
					var wst = $(window).scrollTop();
					var bodyHeight = $(document).height();
					if (!ajaxload) {
						if (wst + winHeight > bodyHeight - 20) {
							ajaxtip("努力加载中...");
				
					}
				}
		});
		
		function ajaxtip(txt) {
			$(".get_more span").html(txt);
		}
		
		
		$(function() {
			$('.classic li').each(function() {
				if ($(this).index() % 4 == 0) {
					$(this).css("margin-left", "0px");
				}
			});
		});

		function go_next(obj) {
			var $obj = $(obj).parent().parent().find('.imgs');
			$obj.animate({
				marginLeft : '-280px'
			}, 300, function() {
				$obj.find('.img:first').appendTo($obj);
				$obj.css("margin-left", "0px");
			});
		}
		function go_prev(obj) {
			var $obj = $(obj).parent().parent().find('.imgs');
			$obj.css("margin-left", "-280px").find('.img:last').prependTo($obj);
			$obj.animate({
				marginLeft : '0px'
			}, 300);
		}

		$(".gotop").click(function() {
			$("body,html").animate({
				scrollTop : 0
			}, 300);
		});
		$(window).scroll(function() {
			if ($(window).scrollTop() > 10) {
				$('.gotop').attr("style", "display:block;");
			} else {
				$('.gotop').attr("style", "display:none;");
			}
		});
		
	</script>
</html>