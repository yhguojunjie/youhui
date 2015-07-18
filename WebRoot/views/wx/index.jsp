<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>淘插件</title>
	<%@ include file="resource.jsp"%>
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
					<a href="http://wx.tchajian.com/page/cj_tree.html"><img _src="${path }/images/wx/wechatbanner0.jpg" src="${path }/images/wx/wechatbanner0.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="http://wx.tchajian.com/page/cj_wish.html"><img _src="${path }/images/wx/wechatbanner1.jpg" src="${path }/images/wx/wechatbanner1.jpg" /></a>
				</li>
			</ul>
			<ul>
				<li>
					<a href="http://wx.tchajian.com/page/cj_runman.html"><img _src="${path }/images/wx/wechatbanner2.jpg" src="${path }/images/wx/wechatbanner2.jpg" /></a>
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
		<a class="active"><em>按发布</em></a>
		<a><em>按销量</em></a>
		<a><em>按价格</em></a>
	</div>
	<div class="plugin_list">
		<ul class="plugin_list_cont">
			<li>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_greetcard.html'">
					<a class="imgbox"><img src="${path }/images/wx/greetcard.jpg" /></a>
					<h3>新年DIY送贺卡</h3>
					<p class="tr"><a>@村长</a><span>已有11人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/29 10:33</span>
					</div>
				</section>
				<div class="btns tr absolute">
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥108</a>
				</div>
			</li>
			<li>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_runman.html'">
					<a class="imgbox"><img src="${path }/images/wx/runman_logo.png" /></a>
					<h3>奔跑吧英雄</h3>
					<p class="tr"><a>@村长</a><span>已有5人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/21 21:22</span>
					</div>
				</section>
				<div class="btns tr absolute">
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥200</a>
				</div>
			</li>
			<li>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_tree.html'">
					<a class="imgbox"><img src="${path }/images/wx/ico_newy.png" /></a>
					<h3>发财树摇红包</h3>
					<p class="tr"><a>@村长</a><span>已有10人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/10 20:24</span>
					</div>
				</section>
				<div class="btns tr absolute">
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥288</a>
				</div>
			</li>
			<li>
				<div class="btns tr absolute">
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥258</a>
				</div>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_wish.html'">
					<a class="imgbox"><img src="${path }/images/wx/staravatar.jpg" /></a>
					<h3>圣诞魔力心愿店</h3>
					<p class="tr"><a>@彬 ^_^)Y</a><span>已有14人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/10 17:42</span>
					</div>
				</section>
			</li>
			<li>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_giftbox.html'">
					<a class="imgbox"><img src="${path }/images/wx/giftbox.png" /></a>
					<h3>拆礼盒</h3>
					<p class="tr"><a>@彬 ^_^)Y</a><span>已有9人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/09 18:00</span>
					</div>
				</section>
				<div class="btns tr absolute">
					<a class="btn_try">试用</a>
					<a class="btn_direct">￥238</a>
				</div>
			</li>
			<li>
				<section onclick="javascript:self.location='http://wx.tchajian.com/page/cj_microscene.html'">
					<a class="imgbox"><img src="${path }/images/wx/microscene.jpg" /></a>
					<h3>微场景</h3>
					<p class="tr"><a>@彬 ^_^)Y</a><span>已有10人购买</span></p>
					<div class="clear"></div>
					<div class="btns tr">
						<span>2014/12/03 17:42</span>
					</div>
				</section>
				<div class="btns tr absolute">
					<a class="btn_direct">免费</a>
				</div>
			</li>


		</ul>
	</div>
<!-- 	<div class="get_more">努力加载中...</div> -->
	<!-- 插件列表 结束 -->
	<%@ include file="footer.jsp"%>
	
	<%@ include file="menu.jsp"%>
	
</body>
</html>
<!--  <script type="text/javascript">
	
/* 	
	function initList(){
		ajaxtip("努力加载中...");
		$.ajax({
			  type: "GET",
			  url : "${path }/pc/plugin/ajaxList?currentPage="+currentPage+"&typeCon="+typeCon+"&orderCon="+orderCon,
			  dataType: "json",
			  success: function(data){
				if(data != null && data != undefined){
					var html = makeList(data);
					$(".plugin_list_cont").append(html);
					ajaxload=false;
				}
				  
			  },error: function(xhr, type){
					ajaxload = false;
					ajaxtip("加载失败，请稍候重试！");
			  }
		});
		
	} */

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
						var html = makeList(data)
						$(".plugin_list_cont").append(html);
						ajaxload=false;
					},error: function(xhr, type){
						ajaxload = false;
						ajaxtip("加载失败，请稍候重试！");
					}
				});
			}
		}
	});
	
	//构造list
	function makeList(data){
		var html="";
		for(var i =0;i<10;i++){
			html+='<li>\
					    <section onclick="javascript:self.location=\'#url\'">\
					    <a class="imgbox"><img src="images/app3.png" /></a>\
						<h3>圣诞送优惠</h3>\
						<p class="tr"><a>@bin潮灏</a><span>已有35人购买</span></p>\
						<div class="clear"></div>\
						<div class="btns tr">\
						<span>14/10/12 21:26</span>\
						</div>\
						</section>\
						<div class="btns tr absolute">\
						<a herf="#" class="btn_try">试用</a>\
						<a class="btn_direct">￥100</a>\
						</div>\
						</li>';
		}
		
		return html;
	}
	
	
	function ajaxtip(txt){
		$(".seemore").html(txt);
	}
</script>-->


