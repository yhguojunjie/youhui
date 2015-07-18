<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
<title>插件库</title>
<link rel="stylesheet" href="${path }/css/wx/layout.css">
<script type="text/javascript" src="${path }/js/wx/zepto.js"></script>
<script type="text/javascript" src="${path }/js/wx/touchslide.js"></script>
<script type="text/javascript" src="${path }/js/wx/layout.js"></script>
</head>
<body>
	<!-- 多图滚动 开始 -->
	<div id="scrollBox" class="scrollBox">
		<div class="bd">
			<ul>
				<li>
					<a href="#"><img _src="${path}/images/wx/banner.jpg" src="${path}/images/wx/app1.png" /></a>
					<p>李玟亲赴红十字会赈灾</p>
				</li>
			</ul>
			<ul>
				<li>
					<a href="#"><img _src="${path}/images/wx/banner.jpg" src="${path}/images/wx/banner.jpg" /></a>
					<p>武僧一龙一拳击败日本</p>
				</li>
			</ul>
			<ul>
				<li>
					<a href="#"><img _src="${path}/images/wx/banner.jpg" src="${path}/images/wx/banner.jpg" /></a>
					<p>火箭2分险胜雷霆总分1-3</p>
				</li>
			</ul>
			<ul>
				<li>
					<a href="#"><img _src="${path}/images/wx/banner.jpg" src="${path}/images/wx/banner.jpg" /></a>
					<p>武僧一龙一拳击败日本</p>
				</li>
			</ul>
			<ul>
				<li>
					<a href="#"><img _src="${path}/images/wx/banner.jpg" src="${path}/images/wx/banner.jpg" /></a>
					<p>火箭2分险胜雷霆总分1-3</p>
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
	<div class="tab_title">
		<a href="#" class="active">按发布</a>
		<a href="#">按销量</a>
		<a href="#">按价格</a>
	</div>
	<div class="plugin_list">
		<ul class="plugin_list_cont">
			<li>
				<a href="#" class="imgbox"><img src="${path}/images/wx/app1.png" /></a>
				<h3><a href="#">圣诞送优惠</a></h3>
				<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>
				<div class="clear"></div>
				<div class="btns tr">
					<span>14/10/12 21:26</span>
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥100</a>
				</div>
			</li>
			<li>
				<a href="#" class="imgbox"><img src="${path}/images/wx/app2.png" /></a>
				<h3><a href="#">圣诞送优惠</a></h3>
				<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>
				<div class="clear"></div>
				<div class="btns tr">
					<span>14/10/12 21:26</span>
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥100</a>
				</div>
			</li>
			<li>
				<a href="#" class="imgbox"><img src="${path}/images/wx/app3.png" /></a>
				<h3><a href="#">圣诞送优惠</a></h3>
				<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>
				<div class="clear"></div>
				<div class="btns tr">
					<span>14/10/12 21:26</span>
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥100</a>
				</div>
			</li>
			<li>
				<a href="#" class="imgbox"><img src="${path}/images/wx/app4.png" /></a>
				<h3><a href="#">圣诞送优惠</a></h3>
				<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>
				<div class="clear"></div>
				<div class="btns tr">
					<span>14/10/12 21:26</span>
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥100</a>
				</div>
			</li>
			<li>
				<a href="#" class="imgbox"><img src="${path}/images/wx/app5.png" /></a>
				<h3><a href="#">圣诞送优惠</a></h3>
				<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>
				<div class="clear"></div>
				<div class="btns tr">
					<span>14/10/12 21:26</span>
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥100</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="get_more">努力加载中...</div>
	<!-- 插件列表 结束 -->
	<footer>
		<p>淘插件</p>
		<p>营销推广插件超市</p>
		<p><a href="${path }/mobile/other/help">帮助中心</a></p>
		<p>© 2014 www.tchajian.com  闽ICP备14019370号</p>
	</footer>
	<!-- 支付弹出对话框 开始 -->
	<div class="pay_box">
		<form>
			<h3>
				<strong>确认支付</strong>
				<span class="close"><!-- 关闭登录窗口按钮 -->╳</span>
			</h3>
			<h4>消费：￥100</h4>
			<div class="row">
				<label><input type="checkbox" />使用代币：￥200</label>
				<a class="paycash">赚代币</a>
			</div>
			<div class="clear space_10"></div>
			<div class="row">
				<a class="btn_cancel">取消</a>
				<input type="submit" value="支付" />
			</div>
			<div class="clear"></div>
		</form>
		<div class="bg"></div>
	</div>
	<!-- 支付弹出对话框 结束 -->
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
</body>
</html>
<script type="text/javascript">
	var startpage=0,
	count=10,
	ajaxload=false,
	winHeight=$(window).height(),
	maxpage=false;
	$(window).scroll(function(){
		var wst=$(window).scrollTop();
		var bodyHeight=$(document).height();
		if(!ajaxload||!maxpage){
			if(wst+winHeight>bodyHeight-10){
				ajaxload=true;
				startpage++;
				var startnum=startpage*count;
				ajaxtip("努力加载中...");
				$.ajax({
					url: 'xxxxxxxxxx',
					dataType: 'json',
					timeout: 300,
					success: function(data){
						var bookshtml="";
						for(var i =0;i<10;i++){
							bookshtml+='<li>\
											<a href="#" class="imgbox"><img src="${path}/images/wx/app4.png" /></a>\
											<h3><a href="#">圣诞送优惠</a></h3>\
											<p class="tr"><a href="#">@bin潮灏</a><span>已有35人购买</span></p>\
											<div class="clear"></div>\
											<div class="btns tr">\
												<span>14/10/12 21:26</span>\
												<a href="#" class="btn_try">试用</a>\
												<a href="#" class="btn_direct">￥100</a>\
											</div>\
										</li>';
						}
						$(".plugin_list_cont").append(bookshtml);
							ajaxload=false;
					},error: function(xhr, type){
						ajaxload = false;
						ajaxtip("加载失败，请稍候重试！");
					}
				});
			}
		}
	});
	function ajaxtip(txt){
		$(".get_more").html(txt);
	}
</script>
