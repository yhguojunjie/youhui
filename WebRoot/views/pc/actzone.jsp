<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
<title>活动圈</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp"%>
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 ativ_center">
		<div class="main ativgroup_page">
			<div class="tab_title">
				<div class="fl options">
					<a href="${url_act }" class="active">全部</a> 
					<a href="${url_classic }" class="demo">经典案例</a>
				</div>
				<div class="fr sorts">
					<a href="${basePath }pc/act?ordertype=1"><span id="order_publish" >按发布</span></a>
					<a href="${basePath }pc/act?ordertype=0"><span id="order_hot" class="active" >按热度</span></a> 
					
				</div>
				<div class="clear"></div>
			</div>
			<ul class="ativpage_list">
<%-- 				<c:forEach items="${actRecList }" var="actRec">
					<li>
						<div class="rehot">推荐</div> 
						<a href="${cjpcShowServer}${actRec.actAccessUrl}/${actRec.actId}" class="imgbox"> 
							<img class="ativ_img" src="${actRec.actIcon }" /> 
							<span class="code_img">
							<img src="${url_getQcode }${cjShowServer}${actRec.actAccessUrl}/${actRec.actId}" /></span>
						</a>
						<h3>
							<a href="${actRec.pcShowActAccUrl }">${actRec.actTitle }</a>
							<p>
								<span >浏览量：<em>${actRec.actBrowseNum }</em></span>
							</p>
						</h3>
						<div class="card">
							<a href="${url_otherPage }/${actRec.userId}" class="avatar"><img src="${actRec.headimgUrl }" /></a>
							<h4>${actRec.nickName }</h4>
							<p>${actRec.introduce }</p>
							<div class="clear"></div>
						</div>
						<div class="describe">
							使用模版：<a href="${url_pluginDetail }/${actRec.pluginId}">《${actRec.pluginName }》</a>
						</div>
					</li>
				</c:forEach> --%>
				<c:forEach items="${actList }"  var="ct">
					<li>
						<a href="${cjpcShowServer}${ct.actAccessUrl}/${ct.actId}" class="imgbox"> 
							<img class="ativ_img" src="${ct.actIcon }" /> 
							<span class="code_img">
							<img src="${url_getQcode }${basePath}${ct.actAccessUrl}/${ct.actId}" /></span>
						</a>
						<h3>
							<a style="height:30px;overflow:hidden;" href="${cjpcShowServer}${ct.actAccessUrl}/${ct.actId}">${ct.actTitle }</a>
							<p>
								<span >浏览量：<em>${ct.actBrowseNum }</em></span>
							</p>
						</h3>
						<div class="card">
							<a href="${url_otherPage }/${ct.userId}" class="avatar"><img src="${ct.headimgUrl }" /></a>
							<h4>
							${ct.nickName }
							<c:if test="${ct.channelId != null }">
						      <em>渠道</em>
					        </c:if>
							</h4>
							<p>${ct.introduce }</p>
							<div class="clear"></div>
						</div>
						<div class="describe">
							使用模版：<a href="${url_pluginDetail }/${ct.pluginId}">《${ct.pluginName }》</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="get_more">
				<span>努力加载中...</span>
			</div>
		</div>
		<div class="clear space_10"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp"%>
	<!-- 底部 结束 -->
</body>

<script type="text/javascript">
	var url_pluginDetail = '${url_pluginDetail}';
	var url_otherPage = '${url_otherPage}';
	var url_getQcode = '${url_getQcode}';
	var basePath = '${basePath}';
	var cjpcShowServer = '${cjpcShowServer}';
	var cjShowServer = '${cjShowServer}';
	var ordertype = 0;
	function scroll2(){
		$(window).scroll(
				function() {
					var wst = $(window).scrollTop();
					var bodyHeight = $(document).height();
					if (!ajaxload) {
// 						console.log("start="+start);
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
	//加载更多活动
	var start = 8;
	var ajaxload = false;
	var iisstop=0;
	winHeight = $(window).height();
	scroll2();
	function ff(){
		ajaxload = true;
	}
	function ajax(){
// 		console.log("ordertype="+ordertype);
// 		console.log("start="+start);
		$.ajax({
			type: "GET",
			url : "${basePath}pc/act/ajaxActList",
			dataType : "json",
			data: {"ordertype":ordertype,"start":start},
			success : function(data) {
				console.log("data.length="+data.length);
				if(data.length != 0){
					var html = "";
					for(var i = 0; i < data.length; i++){
						var cjpjShowPath = cjpcShowServer+data[i].actAccessUrl+'/'+data[i].actId;
						html += '<li>\
							<a href="'+cjpjShowPath+'" class="imgbox">\
								<img class="ativ_img" src="'+data[i].actIcon+'" />\
								<span class="code_img"><img src="'+url_getQcode+basePath+data[i].actAccessUrl+'/'+data[i].actId+'" /></span>\
							</a>\
							<h3>\
								<a style="height:30px;overflow:hidden;" href="'+cjpjShowPath+'">'+data[i].actTitle+'</a>\
								<p >\
									<span >浏览量：<em>'+data[i].actBrowseNum+'</em></span>\
								</p>\
							</h3>\
							<div class="card">\
								<a href="'+url_otherPage+'/'+data[i].userId+'" class="avatar"><img src="'+data[i].headimgUrl+'" /></a>\
								<h4>'+data[i].nickName;
								if(data[i].channelId != null){
									html +='<em>渠道</em>';
								}
								html +='</h4><p>'+data[i].introduce+'</p>\
								<div class="clear"></div>\
							</div>\
							<div class="describe">使用模版：<a href="'+url_pluginDetail+'/'+data[i].pluginId+'">《'+data[i].pluginName+'》</a></div>\
						</li>';
					}
					$(".ativpage_list").append(html);
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
	
	
	$(document).ready(function(){
		if('${ordertype}' == '1'){
			$("#order_publish").addClass("active");
			$("#order_hot").removeClass("active");
			ordertype = '${ordertype}';
		}
		if('${ordertype}' == '0'){
			$("#order_hot").addClass("active");
			$("#order_publish").removeClass("active");
			ordertype = '${ordertype}';
		}
	});

	$(window).scroll(function() {
		if ($(window).scrollTop() > 10) {
			$('.gotop').attr("style", "display:block;");
		} else {
			$('.gotop').attr("style", "display:none;");
		}
	});

	$(function() {
		$(".ativ_center .publish_activity").click(function() {
			$('.pluginlist_box').attr("style", "display:block;");
			$('.pluginlist_box .cont').attr("style", "display:block;");
		});
		
		$('.pluginlist_box .btn_done').click(function() {
			var dataid = "";
			$('.pluginlistmask input').each(function() {
				if ($(this).prop("checked") == true) {
					dataid += $(this).attr("data-id") + ",";
				}
			});
		});
		
		//排序方式切换
		$(".ativ_center .sorts span").click(function(){
			$(this).parent().find("span").attr("class","");
			$(this).attr("class","active");
			if($(this).index() == 0){
				ordertype = 1;
			}else{
				ordertype = 0;
			}
		});
		// 回到顶部
		$(".gotop").click(function() {
			$("body,html").animate({
				scrollTop : 0
			}, 300);
		});
	});
	
	
</script>
</html>