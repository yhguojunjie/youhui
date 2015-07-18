<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="resource.jsp"%>
<title>${plugin.name}</title>
</head>
<body>
	<!-- 头部 开始 -->
	<%@ include file="header.jsp" %>	
	<!-- 头部 结束 -->
	<!-- 内容 开始 -->
	<div class="w1190 plugin_detail">
		<!-- 左侧内容 开始 -->
		<div class="fl w840">
			<div class="main">
				<div class="parameter">
					<dl>
						<dt>
							<img src="${plugin.icon}" />
							<h1>${plugin.name}</h1>
							<p>${plugin.description}</p>
						</dt>
						<dd class="body">
							<p><i class="ico_publh"></i>发布时间：<span><fmt:formatDate value="${plugin.publishTime}" pattern="yyyy/MM/dd  HH:mm" /></span></p>
							<p><em>已有${plugin.tryoutNum}人试用，${plugin.buyNum}人购买，${pluginActVoCount}个正在进行的活动</em></p>
							<div class="btns">
							<%-- 	<a href="${cjpcShowServer}${plugin.actAccessUrl}/${plugin.showActId}" class="btn_pc">演示</a> --%>
								<a href="${cjpcShowServer}${plugin.actAccessUrl}/${plugin.showActId}" class="btn_pc">演示</a>
								<a href="javascript:void(0);" onclick="tryout('${plugin.id}');" class="btn_try">免费试用</a>
								<c:choose>
									<c:when test="${plugin.salePrice != null &&  plugin.salePrice != 0.0 }">
										<c:set var="priceNoDot" value="${fn:substringBefore(plugin.salePrice, '.')}" />
										<a href="javascript:void(0);" onclick="tobuy('${plugin.id}');" class="btn_direct">￥${priceNoDot}/月</a>
									</c:when>
									<c:when test="${plugin.salePrice != null &&  plugin.salePrice == 0.0 }">
										<c:set var="priceNoDot" value="${fn:substringBefore(plugin.price, '.')}" />
										<a href="javascript:void(0);" onclick="buyfal();" class="btn_direct">价格面议</a>
									</c:when>
								</c:choose>
							</div>
						</dd>
					</dl>	
					<div class="clear"></div>
				</div>
				<h3 class="no_fold"><strong>模版截图</strong></h3>
				<div class="imgs">
					<ul>
						<c:forEach items="${picVos}" var="picVo">
							<li><img src="${picVo.url}" /></li>
						</c:forEach>
					</ul>
				</div>
				<div class="space_30"></div>
				<h3 class="no_fold"><strong>使用说明</strong></h3><!--id="direc_of_use"-->
				<div class="text">
					${plugin.guide}
					
					<div id="moreSee"  ><p style="font-size:12px;color:#e0848f;">免责申明：凡有本平台开发者未经模板真实作者许可，在平台上传或侵犯其作品权益的，本平台对开发者行为不负任何责任。但本平台会在收到真实作者书面投诉并核实投诉人身份后，进行处理。</p></div>
					<div class="space_30"></div>
					<div class="see_more"><a>查看更多</a></div>
				</div>
				<c:if test="${plugin.videoUrl != null}">
				<!-- 
					<h3 class="no_fold"><strong>教程视频</strong></h3>
					<div align="center">
						<embed src="${plugin.videoUrl}" width="480" height="400" allowFullScreen="true"></embed>
					</div>
					 -->
				</c:if>
				<div class="space_10"></div>
				<h3 class="fold_across">
					<strong>正在进行的活动（${pluginActVoCount}）</strong>
					<em class="prev">&lt;</em>
					<em class="next">&gt;</em>
				</h3>
				<div class="ativ_center">
					<ul class="main">
						<c:forEach items="${pluginActVos}" var="pluginActVo">
						    <li>
								<a href="${cjpcShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}" class="imgbox">
									<img class="ativ_img" src="${pluginActVo.actIcon}">
									<span class="code_img"><img src="${url_getQcode}${cjShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}"></span>
								</a>
								<h3 >
									<a href="${cjpcShowServer}${pluginActVo.actAccessUrl}/${pluginActVo.actId}">${pluginActVo.actTitle}</a>
									<p class="tr">
										<span>浏览量：<em>${pluginActVo.actBrowseNum}</em></span>
									</p>
								</h3>
								<div class="card">
									<a href="${url_otherPage}/${pluginActVo.userId}" class="avatar"><img src="${pluginActVo.headimgUrl}"></a>
									<h4>${pluginActVo.nickName}
									<c:if test="${pluginActVo.channelId != null }">
						              <em>渠道</em>
					                </c:if>
									</h4>
									<p>${pluginActVo.introduce}</p>
									<div class="clear"></div>
								</div>
								<div class="describe">使用模板：<a href="${basePath}pc/plugin/detail/${pluginActVo.pluginId}">《${pluginActVo.pluginName}》</a> </div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w320 side">
			<div class="devoler">
				<div class="title">模版开发者</div>
				<div class="body">
					<a href="${url_devOtherPage}/${dev.userId}"  class="avatar"><img src="${dev.headimgUrl}" /></a>
					<h4><a href="${url_devOtherPage}/${dev.userId}" >@${dev.nickName}</a></h4>
<!-- 					<span class="suc_gold"><i class="ico_suc"></i>成交：<strong>250</strong>笔</span> -->
					<div class="clear space_10"></div>
					<p>${dev.introduce}</p>
				</div>
				<h5><a href="${url_devOtherPage}/${dev.userId}"  class="fr">更多>></a>其他插件（${otherPluginVoCount}）</h5>
				<ul class="plugin_list" id="other_plugin_list">
					 <c:forEach items="${otherPluginVos}" var="otherPluginVo">
						<li>
							<a href="${url_pluginDetail}/${otherPluginVo.id}"  class="imgbox"><img src="${otherPluginVo.icon}" /></a>
							<h3><a href="${url_pluginDetail}/${otherPluginVo.id}" >${otherPluginVo.name}</a></h3>
							<p>已有${otherPluginVo.buyNum}人购买</p>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="norl_title"><a href="${url_pluginList }" class="fr" id="tjPlugin_change">更多>></a>模版推荐（${tjPluginVoCount}）</div>
			<ul class="plugin_list" id = "tj_plugin_list">
				<c:forEach items="${tjPluginVos}" var="tjPluginVo">
					<li>
					<a href="${url_pluginDetail}/${tjPluginVo.id}"  class="imgbox"><img src="${tjPluginVo.icon}" /></a>
					<h3><a href="${url_pluginDetail}/${tjPluginVo.id}" >${tjPluginVo.name}</a></h3>
					<p>已有${tjPluginVo.buyNum}人购买</p>
					</li>
				</c:forEach>
			</ul>
			<div class="clear space_20"></div>
			<!--  <a href="${url_pluginList }" class="see_all">查看全部</a> -->
		</div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
	</div>
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp" %>
	<!-- 底部 结束 -->
	
	<!-- 支付弹出对话框 开始 -->
	<div class="pay_box" id="paysuc">
		<form action="${path }/pay/pay/pf" method="post" name="payForm"
			id="payForm">
			<h3>
				<strong>购买活动</strong> <span class="close"> <!-- 关闭登录窗口按钮 -->╳
				</span>
			</h3>
			<div class="clear space_20"></div>
			<div class="row">
				有效期
				<select name="buyMon" onchange="useMoney(this)">
					<option value="1">1个月</option>
					<option value="2">2个月</option>
					<option value="3">3个月</option>
					<option value="4">4个月</option>
					<option value="5">5个月</option>
					<option value="6">6个月</option>
					<option value="7">7个月</option>
					<option value="8">8个月</option>
					<option value="9">9个月</option>
					<option value="10">10个月</option>
					<option value="11">11个月</option>
					<option value="12">12个月</option>
				</select>
			</div>
			<h4>消费：<span id="useMoney">￥${priceNoDot}</span></h4>
			<div class="row">
				<label><input type="checkbox" name="isRepreCoin" id="isRepreCoin" />使用代币：￥<span id="haveMoney">0</span></label> 
				<a href="javascript:alert('敬请期待!')" class="paycash">赚代币</a>
			</div>
			<div class="clear space_20"></div>
			<div class="row">
				<a class="btn_cancel">取消</a> <a onclick="topay();" class="btn_pay">支付</a>
			</div>
			<div class="clear"></div>
			<div id="pluginIdInputHid">
				<input type="hidden" id="productId" name="productId" value="${plugin.id }"/>
				<input type="hidden" id="charge" name="charge" value="${priceNoDot}"/>
				<input type="hidden" id="productType" name="productType" value="1"/>
			</div>
		</form>
		<div class="bg"></div>
	</div>
	
	<div class="pay_box" id="payfal">
		<div class="cont">
			<h3>
				<strong>购买活动</strong><span onclick="closepayfal()" class="close">╳</span>
			</h3>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<p>暂不支持线上购买</p>
			<p>请联系客服</p>
			<div class="space_10"></div>
			<div class="space_5"></div>
			<div class="btns">
				<a onclick="closepayfal()" class="btn_cancel">知道了</a>
			</div>
		</div>
		<div class="bg"></div>
	</div>
	
	<!-- 支付弹出对话框 结束 -->
	<!-- 新手提示页 开始-->
	<div class="guide" style="display: none;">
		<div class="cont">
			<img class='detail1' src="${path }/images/pc/detailguide1.png" />
			<img class='detail2' src="${path }/images/pc/detailguide2.png" />
			<img class='detail3' src="${path }/images/pc/detailguide3.png" />
		</div>
		<div class="bg"></div>
	</div>	
	<!-- 新手提示页 结束-->
</body>
</html>
<script type="text/javascript">

	//购买提示
	function buyfal(){
		$("#payfal").css("display","block");
	}
	
	function useMoney(obj){
		var m = $(obj).val();
		var pid = '${plugin.id}';
		var agid = '${plugin.agentId}';
	/* 	console.log("m="+m);
		console.log("pid="+pid);
		console.log("agid="+agid); */
		$.ajax({
			type: "POST",
			url : "${path }/pc/plugin/calculatePrice",
			data:{mon:m,pid:pid,agid:agid},
			dataType: "json",
			success: function(data){
 				/* console.log("data.afprice="+data.afprice); */
				$('#useMoney').html(data.afprice);
				$('#charge').val(data.afprice);
			}
		});
	}
	//购买
	function tobuy(pluginId){
		$.ajax({
			type: "POST",
			url : "${path }/pc/plugin/tobuy",
			data:{pluginId:pluginId},
			dataType: "json",
			success: function(data){
				if(data.status == "2"){
					$('.login_box').fadeIn(50);
					$('.login_box .main').fadeIn(200);
					window.location.href = "${basePath}login";
				}else if(data.status == "3" || data.status == "1"){
					$("#paysuc").css("display","block");
					$('#haveMoney').html(data.repreCoin);
					$('.pay_box .close,.pay_box .btn_cancel').click(function(){
						$('.pay_box').fadeOut(50);
					});
					
				}else{
					popupPrompt("系统繁忙，请稍后再试！");	  
				}
			}
			  
		}); 
	}
    
	function tryout(pluginId){
    	console.log("pluginId="+pluginId);
		$.ajax({
				type: "POST",
				url : "${basePath }pc/plugin/tryout",
				data:{pluginId:pluginId},
				dataType: "json",
				success: function(data){
					if(data.status == "2"){
						window.location.href = "${basePath}login";
					}else if(data.status == "3"){
						popupPrompt("非常抱歉,一个账号只能试用1次",toMyPlugin);
					}else if(data.status == "4"){
						popupPrompt("您已购买此模板",toMyPlugin);
					}else if(data.status == "5"){
						popupPrompt("非常抱歉,一个账号只能试用1次",toMyPlugin);
					}else if(data.status == "1"){
						window.location.href = '${url_myact}';
					}else{
						popupPrompt("系统繁忙，请稍后再试！");
					}
				}
		}); 
			
	}

	function freeUse(pluginId){
		$.ajax({
			type: "POST",
			url : "${path }/pc/plugin/freeUse",
			data:{pluginId:pluginId},
			dataType: "json",
			success: function(data){
				if(data.status == "2"){
					window.location.href = "${basePath}login";
				}else if(data.status == "3"){
					popupPrompt("免费模板只能使用一次",toMyPlugin);
				}else if(data.status == "1"){
					if(data.addUrl != null || data.addUrl != '' || data.addUrl != undefined){
						window.location.href = '${basePath}'+data.addUrl;
						
					}else{
						window.location.href = '${url_myact}';
					}
				}else{
					popupPrompt("系统繁忙，请稍后再试！");
				}
			}
		}); 
	}

	function topay(){ 
		$("#payForm").submit();
	}
	
	function toMyPlugin(){
		window.location.href = "<%=basePath %>pc/my/actList";		
	}
	
	//推荐插件轮换
	$('#tjPlugin_change').click(function(){
		$.ajax({
			  type: "GET",
			  url : "${path }/pc/plugin/tjPlugins",
			  data:{pluginId:${pluginId4query}},
			  dataType: "json",
			  success: function(data){
					$('#tj_plugin_list').empty();
					var html = "";
				    for (var i = 0; i < data.length; i++) {
				    	html += '<li>';
				    	html += '<a href="${url_pluginDetail}/'+data[i].id+'" class="imgbox"><img src="'+data[i].icon+'" /></a>';
				    	html += '<h3><a href="${url_pluginDetail}/'+data[i].id+'">'+data[i].name+'</a></h3>';
				    	html += '<p>已有'+data[i].buyNum+'人购买</p>';
				    	html += '</li>';
				    }
				    $('#tj_plugin_list').html(html);
			  }
	  }); 
	});

    //统一弹框
	function popupPrompt(str,fucName){
		var str  =  '<div class="prompt_box">\
					<div class="cont">\
					<h3><strong>提示</strong><span class="close">╳</span></h3>\
					<div class="msgs">\
						<p>'+ str +'</p>\
					</div>\
					<div class="btns">\
						<a class="btn_done">知道了</a>\
					</div>\
					</div>\
					<div class="bg"></div>\
					</div>';
		$('body').append(str);
		$('.prompt_box .close,.prompt_box .btn_cancel,.prompt_box .btn_done').click(function(){
			if( $(this).attr("class") == "btn_done" ){
				var count = 0;
				if(fucName){
					fucName();
				}
				$(this).parents('.prompt_box').remove();
			}else{
				$(this).parents('.prompt_box').remove();
			}
		});
	}
	//关闭支付失败
	function closepayfal(){
		$('#payfal').hide();
	}

	//新手引导提示
/* 	$(function(){
		if(window.localStorage){
			var storage = window.localStorage;
			if(storage.getItem("pluginDetailGuide") == null){
				showPageTip(1);
			}else{
				var guide = storage.getItem("pluginDetailGuide")
				showPageTip(guide);
			}
		}

	});	 */
	function showPageTip(guide){
		if(guide <= 1){
			$('.guide').attr("style","display:block;");
		}
		$('.guide').click(function(){
			guide += 1;
			if(guide <=3 && guide > 0){
				$('.guide .cont img').fadeOut(500);
				$('.guide .cont img.detail' + guide).fadeIn(500);
				if(guide == 3){
					$("body,html").animate({scrollTop:1000},300);
				}
			}else if(guide > 3){
				$('.guide').fadeOut(500);
			}
		});
		if(window.localStorage){
			window.localStorage.setItem("pluginDetailGuide", 2);
		}
	}
	
	//大图展示
	$('.plugin_detail .imgs li').click(function(){
		var imgsrc = [];
		var i = $(this).index();
		$(this).parent().find('img').each(function(){
			imgsrc.push($(this).attr('src'));
		});
		$('body').append('<div class="bigimg_boxlist">\
							<span class="close">╳</span>\
							<span class="prev">&lt;</span>\
							<span class="next">&gt;</span>\
							<img src="'+imgsrc[i]+'" />\
						</div>');
		$('.bigimg_boxlist img').load(function(){
			$(this).attr("style","margin-top:-" + ($(this).height()/2-5) +"px;margin-left:-" + ($(this).width()/2-5) +"px;");
		});
		console.log(i);
		$('.bigimg_boxlist .prev').on("click",function(){
			if(i > 0)i--;
			$(this).siblings("img").attr("src",imgsrc[i]);
			console.log(i);
		});
		$('.bigimg_boxlist .next').on("click",function(){
			if(i < imgsrc.length-1)i++;
			$(this).siblings("img").attr("src",imgsrc[i]);
			console.log(i);
		});

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
	
	$(document).on("click",".bigimg_boxlist .close",function(){
		$(this).parents('.bigimg_boxlist').remove();
	});
	
</script>


