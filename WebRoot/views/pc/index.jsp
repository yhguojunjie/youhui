<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="resource.jsp"%>
	<title>${agentInfoConst.websiteTitle }</title>
    <meta content="${agentInfoConst.websiteKeyword }" name="Keywords" />
    <meta content="${agentInfoConst.websiteDesc }" name="Description" />
	<script type="text/javascript" src="${path }/js/pc/pagination.js"></script>
</head>
<body>
	<!-- 头部 开始 -->
		<%@ include file="header.jsp"%>
	<!-- 头部 结束 -->
	<!-- 焦点图 开始 -->
		<%@ include file="banner.jsp"%>
	<!-- 焦点图 结束 -->
	<!-- 内容 开始 -->
	<!-- 第二屏 开始 -->
	<div class="w1190">
		<!-- 左侧内容 开始  活动推荐-->
		<div class="fl w890">
			<div class="ativ_center for_index">
				<div class="main">
					<div class="norl_title">
						<!-- <div class="fr"><a href="#" class='more_btn'>更多>></a></div> -->
						<h3>活动推荐</h3>
					</div>
					<div id="rec_act"></div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始  经典案例-->
		<div class="fr w300 side" id="rec_case"></div>
		<!-- 右侧内容 结束 -->
		<div class="clear"></div>
			<a href="${url_act}" class="see_all">查看全部</a>
		<div class="space_20"></div>
	</div>
	<!-- 第二屏 结束 -->
	<div class="clear"></div>
	<!-- 第三屏 开始 -->
	<div class="w1190">
		<!-- 左侧内容 开始  模板推荐-->
			<div class="fl w890">
				<div class="index">
					<div class="main">
						<div class="norl_title">
							<!-- <div class="fr"><a href="#" class='more_btn'>更多>></a></div> -->
							<h3>模版推荐</h3>
						</div>
						<div class="cont w860" id="rec_plugin"></div>
					</div>
				</div>
			</div>	
		<!-- 左侧内容 结束 -->
		<!-- 右侧内容 开始 -->
		<div class="fr w300 side" id="rec_pluginprev"></div>
		<!-- 右侧内容 结束 -->
		<div class="clear space_20"></div>
		<a href="${url_pluginList}" class="see_all">查看全部</a>
	</div>
	<!-- 第三屏 结束 -->
	<div class="clear"></div>
	<!-- 渠道推荐 开始 -->
	<div class="w1190"  id="rec_channel"></div>
	<!-- 渠道推荐 结束 -->
	<!-- 内容 结束 -->
	<!-- 底部 开始 -->
	<%@ include file="footer.jsp"%>
	<!-- 底部 结束 -->
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		//活动推荐处理
		$("#rec_act").load("${basePath}pc/recommend/getActRecList");
		//经典案例
		$("#rec_case").load("${basePath}pc/recommend/getClassicRecList");
		//模板推荐处理
		$("#rec_plugin").load("${basePath}pc/recommend/getPluginRecList");
		//模板预告
		$("#rec_pluginprev").load("${basePath}pc/recommend/getPluginPreRecList");
		//渠道推荐
		$("#rec_channel").load("${basePath}pc/recommend/getChannelRecList");
	});
	
	// banner右侧二维码显隐
	$(function(){
		$('.banner .side .code_btn').click(function(){
			$('.banner .side .code').fadeIn();
		});
		$('.banner .side .code .close').click(function(){
			$('.banner .side .code').fadeOut();
		});
		$('.banner .side').mouseleave(function(){
			var delay = setInterval(function(){
				$('.banner .side .code').fadeOut();
				clearInterval(delay);
			},2000);
		});
	});

</script>

<script type="text/javascript">

	// 边侧轮播
	function go_next(className){
		var list = $('.'+className).find('ul');
		list.animate({marginLeft:'-300px'},300,function(){
			list.find('li:first').appendTo(list);
			list.css("margin-left","0px");
		});
	}
	function go_prev(className){
		var list = $('.'+className).find('ul');
		list.css("margin-left","-300px").find('li:last').prependTo(list);
		list.animate({marginLeft:'0px'},300);
	}

	$(function(){
		// 边侧轮播
		$('.turn_next').click(function(){
			go_next('classic_caselist');
		});
		$('.turn_prev').click(function(){
			go_prev('classic_caselist');
		});
		var turning = setInterval(function(){
			$(".turn_next").click();
		},3000);
		$('.classic_case').hover(function(){
			clearInterval(turning);
		},function(){
			turning = setInterval(function(){$(".turn_next").click();},3000);
		});
		
	});


	/* --------------------------------- 焦点图 开始 --------------------------------- */
	function getStyle(obj,name){
		if(obj.currentStyle){
			return obj.currentStyle[name];
		}else{
			return getComputedStyle(obj,false)[name];
		}
	}

	function getByClass(oParent,nClass){
		var eLe = oParent.getElementsByTagName('*');
		var aRrent  = [];
		for(var i=0; i<eLe.length; i++){
			if(eLe[i].className == nClass){
				aRrent.push(eLe[i]);
			}
		}
		return aRrent;
	}

	function startMove(obj,att,add){
		clearInterval(obj.timer)
		obj.timer = setInterval(function(){
			var cutt = 0 ;
			if(att=='opacity'){
				cutt = Math.round(parseFloat(getStyle(obj,att)));
			}else{
				cutt = Math.round(parseInt(getStyle(obj,att)));
			}
			var speed = (add-cutt)/4;
			speed = speed>0?Math.ceil(speed):Math.floor(speed);
			if(cutt==add){
				clearInterval(obj.timer);
			}else{
				if(att=='opacity'){
					obj.style.opacity = (cutt+speed)/100;
					obj.style.filter = 'alpha(opacity:'+(cutt+speed)+')';
				}else{
					obj.style[att] = cutt+speed+'px';
				}
			}
		},30);
	}

	window.onload = function(){
		var oDiv = document.getElementById('banner');
		var oPre = getByClass(oDiv,'prev')[0];
		var oNext = getByClass(oDiv,'next')[0];
		var oUlBig = getByClass(oDiv,'banner_cont')[0];
		var aBigLi = oUlBig.getElementsByTagName('li');
		var oDivSmall = getByClass(oDiv,'focus')[0];
		var aLiSmall = oDivSmall.getElementsByTagName('li');
		
		function tab(){
			for(var i=0; i<aLiSmall.length; i++){
				aLiSmall[i].className = '';
			}
			aLiSmall[now].className = 'active';
			startMove(oUlBig,'left',-(now*aBigLi[0].offsetWidth));
		}

		var now = 0;
		for(var i=0; i<aLiSmall.length; i++){
			aLiSmall[i].index = i;
			aLiSmall[i].onmouseover = function(){
				now = this.index;
				tab();
			}
		}

		oPre.onclick = function(){
			now--;
			if(now ==-1){
				now = aBigLi.length;
			}
			tab();
		}

		oNext.onclick = function(){
			now++;
			if(now ==aBigLi.length){
				now = 0;
			}
			tab();
		}

		var timer = setInterval(oNext.onclick,3000); //滚动间隔时间设置
		oDiv.onmouseover = function(){
			clearInterval(timer);
		}
		oDiv.onmouseout = function(){
			timer = setInterval(oNext.onclick,3000); //滚动间隔时间设置
		}
	}
	/* --------------------------------- 焦点图 结束 --------------------------------- */
</script>
