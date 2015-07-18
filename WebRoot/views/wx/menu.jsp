<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
	<!-- 菜单 开始 -->
<%-- 		<dl class="float_menu">
			<dd><ul>
				<li class="at_cent"><a href="${url_pluginActCenter }">活动中心</a></li>
				<li class="my_ativ"><a href="${url_pluginAct }">我的活动</a></li>
				<li class="my_plug"><a href="${url_userPlugin }">我的插件</a></li>
				<li class="my_mark"><a href="${url_userTradeLog }">交易记录</a></li>
				<li class="my_home"><a href="${url_mypage }">个人主页</a></li>
			</ul></dd>
			<dt class="my_menu">菜单</dt>
		</dl>  --%>
		
	<div class="fixed_menu">
		<ul>
			<li><a href="${url_pluginActCenter }">
				<img src="${path }/images/wx/menu2.png" />
				<span>活动中心</span>
			</a></li>
			<li><a href="${url_userPlugin }">
				<img src="${path }/images/wx/menu3.png" />
				<span>我的插件</span>
			</a></li>
			<li><a href="${url_pluginAct }">
				<img src="${path }/images/wx/menu4.png" />
				<span>我的活动</span>
			</a></li>
			<li><a href="${url_mypage }">
				<img src="${path }/images/wx/menu5.png" />
				<span>个人主页</span>
			</a></li>
			<li><a href="${url_userTradeLog }">
				<img src="${path }/images/wx/menu6.png" />
				<span>交易记录</span>
			</a></li>
			<li><a href="${basePath }">
				<img src="${path }/images/wx/menu7.png" />
				<span>首页</span>
			</a></li>
		</ul>
	</div>
	<div class="fixed_menubtn"><img src="${path }/images/wx/menu1.png" /></div>
	<!-- 菜单 结束 -->
<script type="text/javascript">
//初始化加载 
$(document).ready(function(){
	$(".my_menu").toggle(function(){
		$(".float_menu").animate({height:'288px'});
	},function(){
		$(".float_menu").animate({height:'48px'});
	});
	
});
</script>