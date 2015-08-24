<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ConvertUtil" %>
<%@ include file="constant.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>帮you惠，优惠网！我喜欢</title>
    <link rel="stylesheet" href="${path}/css/common.css"/>
    <link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/css/owlCarousel/owl.carousel.css" >
	<link rel="stylesheet" href="${path}/css/owlCarousel/owl.theme.css" >
    <link rel="stylesheet" href="${path}/css/style.css"/>

</head>
<body>
   <div class="top-wrapper">
		<div class="top-info">
			<div class="top-left">
				<a href="#">亲，请登录</a>
				<a href="#">注册</a>
				<a class="spacer"></a>
				<div data-toggle="arrowdown" id="arrow2" class="msg-info">
					<i class="fa fa-envelope fa-gray"></i>
					<a href="#">消息</a>
					<span class="down-icon"></span>
				</div>
				<a href="#">手机帮you惠</a>
				<div data-toggle="hidden-box" id="nav-box2" class="msg-box">
					<h1>未读新消息<a href="#" class="fa fa-pencil-square-o pencil"></a></h1>
					<div class="read-info">
						<h2>
							<span class="orange">&nbsp;&nbsp;|</span>&nbsp;你的书架&nbsp;
							<span style="font-weight: lighter">收到了</span>
							<span class="orange">1本书</span>
							<span class="fa fa-times close-msg"></span>
						</h2>
						<img src="${path}/img/book-1.png" />
						<div style="float: right">
							<p>
								全中国最穷的小伙子发财日记<br/>
								掏彩票公共账号
							</p>
							<h3><a href="#">去看看</a></h3>
						</div>
					</div>
					<div class="msg-setting">
						<a href="#" class="fa fa-cog"></a>
						<a href="#" class="fa fa-pencil-square-o"></a>
						<a style="margin-left: 70px" href="#">买家消息&nbsp;|&nbsp;</a>
						<a href="#">卖家消息</a>
					</div>
				</div>
			</div>
			<div class="top-right">
				<div data-toggle="arrowdown" id="arrow3" class="user-name">
					<a href="#">我的订单</a>
					<span class="down-icon"></span>
				</div>
				<div data-toggle="arrowdown" id="arrow4" class="user-name">
					<i class="fa fa-shopping-cart fa-orange"></i>
					<a href="#">帮you惠车</a>
					<span class="down-icon"></span>
				</div>
				<div data-toggle="arrowdown" id="arrow5" class="user-name">
					<i class="fa fa-star fa-gray"></i>
					<a href="#">收藏夹</a>
					<span class="down-icon"></span>
				</div>
				<a href="#">商品分类</a>
				<div data-toggle="hidden-box" id="nav-box3" class="my-taobao-box">
					<ul>
						<li>已购宝贝</li>
						<li>我的优惠</li>
					</ul>
				</div>
				<div data-toggle="hidden-box" id="nav-box4" class="shopping-box">
					<span>您购物车里还没有任何宝贝。</span><a class="check-shopp" href="#">查看我的购物车</a>
				</div>
				<div data-toggle="hidden-box" id="nav-box5" class="get-box">
					<ul>
						<li>收藏的宝贝</li>
						<li>收藏的店铺</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="top-main">
		<img src="${path}/img/logo.png" />
		<div class="search-wrapper">
			<div class="search-box">
				<input class="search-in" type="text" placeholder="输入你想要的商品吧~">
				<input type="button" class="search-but" value="搜索">
			</div>
		</div>
		<div class="two-code">
			<h3>手机帮you惠</h3>
			<img src="${path}/img/code.png" />
			<div class="close-code"><i class="fa fa-times"></i></div>
		</div>
	</div>
	<div class="line">
		<div class="nav">
			<ul>
				<li class="cur"><a href="${basePath}">首页</a></li>
				<li><a href="#">我的优惠</a></li>
				<li><a href="#">今日特惠</a></li>
				<li><a href="#">品牌专场</a></li>
				<li><a href="#">金牌秒杀</a></li>
			</ul>
		</div>
	</div>
	<div class="main_page">
		<div class="brand-top">
			<div id="owl-top" class="owl-carousel">
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top2.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top2.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top2.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top2.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top2.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/page/top1.jpg" alt=""></a>
			</div>
		</div>
		<div class="brand-wrapper">
			<!-- <div id="owl-brand" class="owl-carousel">
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/guwang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/kuailegou.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/aituan.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/meituan.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/mogujie.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/jingdong.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/qinqin.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/shopin.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/paipai.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/huawei.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/guomei.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/guwang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/kuailegou.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/aituan.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/meituan.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/mogujie.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/jingdong.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/qinqin.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/shopin.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/paipai.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/huawei.jpg" alt=""></a>
				<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
				<a class="item"><img src="${path}/mg/cooperlogo/guomei.jpg" alt=""></a>
			</div> -->
			<ul>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/guwang.jpg" alt=""></a>
					<a class="br" title="谷网" href="#">
						谷网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/kuailegou.jpg" alt=""></a>
					<a class="br" title="快乐购" href="#">
						快乐购
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/aituan.jpg" alt=""></a>
					<a class="br" title="爱团网" href="#">
						爱团网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
					<a class="br" title="卖包包" href="#">
						卖包包
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/meituan.jpg" alt=""></a>
					<a class="br" title="美团" href="#">
						美团
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/mogujie.jpg" alt=""></a>
					<a class="br" title="蘑菇街" href="#">
						蘑菇街
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/jingdong.jpg" alt=""></a>
					<a class="br" title="京东" href="#">
						京东
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/qinqin.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						亲亲网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/shopin.jpg" alt=""></a>
					<a class="br" title="折扣网" href="#">
						折扣网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
					<a class="br" title="卖包包" href="#">
						卖包包
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/paipai.jpg" alt=""></a>
					<a class="br" title="拍拍网" href="#">
						拍拍网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/huawei.jpg" alt=""></a>
					<a class="br" title="华为" href="#">
						华为
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/guomei.jpg" alt=""></a>
					<a class="br" title="国美电器" href="#">
						国美电器
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/guwang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						谷网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/kuailegou.jpg" alt=""></a>
					<a class="br" title="快乐购" href="#">
						快乐购
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/aituan.jpg" alt=""></a>
					<a class="br" title="爱团网" href="#">
						爱团网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
					<a class="br" title="卖包包" href="#">
						卖包包
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/meituan.jpg" alt=""></a>
					<a class="br" title="蘑菇街" href="#">
						蘑菇街
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/mogujie.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/jingdong.jpg" alt=""></a>
					<a class="br" title="京东" href="#">
						京东
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/qinqin.jpg" alt=""></a>
					<a class="br" title="亲亲网" href="#">
						亲亲网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/shopin.jpg" alt=""></a>
					<a class="br" title="折扣网" href="#">
						折扣网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/maibaobao.jpg" alt=""></a>
					<a class="br" title="卖包包" href="#">
						卖包包
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/paipai.jpg" alt=""></a>
					<a class="br" title="拍拍网" href="#">
						拍拍网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/dangdang.jpg" alt=""></a>
					<a class="br" title="当当网" href="#">
						当当网
					</a>
				</li>
				<li>
					<a class="item"><img src="${path}/img/cooperlogo/huawei.jpg" alt=""></a>
					<a class="br" title="华为" href="#">
						华为
					</a>
				</li>
			</ul>
		</div>
		<div class="main-left">
        <div class="main-title font">
            <h1>超高优惠</h1>
<!--             <div class="tab"><ul><li class="tab-item"><a href="#">单品推荐</a></li><li class="tab-item"><a href="#">品牌专场</a></li></ul></div>    
 -->        </div>
               <div class="show-list">
                    <ul class="show-listjj">
                     <!--   <li class="current"><a href="#">全部(100)</a></li>  
             
                        <li><a href="#">服装内衣(12)</a></li>
                        <li><a href="#">鞋包配饰(12)</li>
                        <li><a href="#">运动户外(12)</li>
                        <li><a href="#">珠宝手表(12)</li>
                        <li><a href="#">手机数码(12)</li>
                        <li><a href="#">家电办公(12)</li>
                        <li><a href="#">护肤彩妆(12)</li>
                        <li><a href="#">母婴用品(12)</li>
                        <li><a href="#">家纺居家(12)</li>
                        <li><a href="#">家具建材(12)</li>  -->
                        
                        
                         <li class="current" gjj="全部">全部(100)</li>
                         <c:forEach items="${goodsclassificatList}" var="goodsclassificat" varStatus="sta" >
		
		<c:if test="${goodsclassificat.id!=17&&goodsclassificat.id!=18}"> 
				 <li id="${goodsclassificat.id }"  gjj="${goodsclassificat.name }" >${goodsclassificat.name }</li>
		  
		</c:if> 
		        </c:forEach>
            <!--             <li><div>服装内衣(12)</div></li>
                        <li>鞋包配饰(12)</li>
                        <li>运动户外(12)</li>
                        <li>珠宝手表(12)</li>
                        <li>手机数码(12)</li>
                        <li>家电办公(12)</li>
                        <li>护肤彩妆(12)</li>
                        <li>母婴用品(12)</li>
                        <li>家纺居家(12)</li>
                        <li>家具建材(12)</li>  -->
                        
                        
                        
                        <!-- <li><a href="#">美食特产</a></li>
                        <li><a href="#">日用百货</a></li>
                        <li><a href="#">汽车摩托</a></li>
                        <li><a href="#">文化娱乐</a></li> -->
                    </ul>
            </div>
            <a href="#">
        </a>
       
        <div id="superhuishops"> 
            
          <!--   <div class="product-box">
            <div class="box"><div class="logo">
            <span class="bg-cgf ico-tag1">今日 </span>
            <span class="bg-cgf ico-tag2">活动</span>
            <span class="bg-cgf ico-tag3">品牌</span>
            <span class="bg-cgf ico-tag4">限量<br>100件</span></div>
            <a target="_blank" href="#"><img src="http://115.28.169.210:29099/group1/M00/00/22/cxyp0lXSAjmAf0ibAACEqMHE_O8772.png"></a>
            <span class="box_activity"><i class="fa fa-clock-o"></i>距活动结束<span>00:04:56:15</span></span>
            <div class="box_hover"><span class="sale">已售1000份</span></div></div>
            <div class="box_list"><h3>
            <a target="_blank" href="#"><img src="/hudongtui/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
            <div class="list_logo">
            <span class="price-new"><em>¥</em>58</span>
            <del class="price-old"><em>¥</em>98</del></div>
            <div class="box_listl"><span>-53.3</span>
            <div class="ico-you">购买后减67%</div></div>
            <div class="box_listr"><a href="#" class="" target="_blank">立即购买</a></div></div>  </div>
            
            
            <div class="product-box"><div class="box"><div class="logo"><span class="bg-cgf ico-tag1">今日 </span><span class="bg-cgf ico-tag2">活动</span><span class="bg-cgf ico-tag3">品牌</span><span class="bg-cgf ico-tag4">限量<br>100件</span></div><a target="_blank" href="#"><img src="http://115.28.169.210:29099/group1/M00/00/22/cxyp0lXTMQeADJQ1AACEqMHE_O8496.png"></a><span class="box_activity"><i class="fa fa-clock-o"></i>距活动结束<span>00:04:56:15</span></span><div class="box_hover"><span class="sale">已售356份</span></div></div><div class="box_list"><h3><a target="_blank" href="#"><img src="/hudongtui/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3><div class="list_logo"><span class="price-new"><em>¥</em>666</span><del class="price-old"><em>¥</em>888</del></div><div class="box_listl"><span>-53.3</span><div class="ico-you">购买后减67%</div></div><div class="box_listr"><a href="#" class="" target="_blank">立即购买</a></div></div>
            
            </div>
             -->
            
            
            	<div class="get_more"><span>努力加载中...</span></div>
            </div>
        <%-- <div class="product-box">
            <div class="box">
              <div class="logo">
	                <span class="bg-cgf ico-tag1">今日</span>
	                <span class="bg-cgf ico-tag2">活动</span>
	                <span class="bg-cgf ico-tag3">品牌</span>
	                <span class="bg-cgf ico-tag4">限量<br>100件</span>
             </div>
                <a target="_blank" href="#">
                    <img src="${path}/img/show1.png">
                </a>
              <!--   <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span> -->
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo">
                  <span class="price-new"><em>¥</em>79</span>
                  <del class="price-old"><em>¥</em>339</del>
                </div>
                <div class="box_listl">
                        <span>-53.3</span>
                        <div class="ico-you">购买后减67%</div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                </div>
            </div>
        </div> --%>
    </div>
	</div>
	<div class="help">
		<div class="help-info">
			<h1><img src="${path}/img/help4.png"><span class="help-text">消费者保障</span></h1>
			<a class="help-a" href="#">保障范围</a>
			<a class="help-a" href="#">退货退款流程</a>
			<a class="help-a" href="#">服务中心</a>
			<a class="help-a" href="#">更多特色服务</a>
		</div>
		<div class="help-info">
			<h1><img src="${path}/img/help1.png"><span class="help-text">新手上路</span></h1>
			<a class="help-a" href="#">新手专区</a>
			<a class="help-a"v href="#">消费警示</a>
			<a class="help-a" href="#">交易安全</a>
			<a class="help-a margin-r" href="#">24小时在线帮助</a>
		</div>
		<div class="help-info">
			<h1><img src="${path}/img/help2.png"><span class="help-text">付款方式</span></h1>
			<a class="help-a-litter" href="#">支付宝快捷支付</a>
			<a class="help-a-litter" href="#">支付宝卡（现金）付款</a>
			<a class="help-a-litter" href="#">支付宝余额付款</a>
			<a class="help-a" href="#">货到付款</a>
		</div>
		<div class="help-info">
			<h1><img src="${path}/img/help3.png"><span class="help-text">帮you惠特色</span></h1>
			<a class="help-a" href="#">帮you惠指数</a>
			<a class="help-a" href="#">淘公仔</a>
			<a class="help-a" href="#">手机帮you惠</a>
			<a class="help-a" href="#">旺信</a>
		</div>
	</div>
<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
<!-- 底部 结束 -->

</body>
<script>
var fileAccessPath='${fileAccessPath}';
function allsuiper(){
		 $.ajax({
				type: "GET",
	       url: "${basePath}pc/superhuiGoods/ajaxAllList?start=0",
	       dataType: "json",
				success : function(data) {
					
					
					console.log("data.length="+data.length);
					if(data.length != 0){
						$("#superhuishops").html("");
						var html = " ";
						
							 for (var i = 0; i < data.length; i++) {
								
								 console.log("data.i="+i);
								 
							 html +='<div class="product-box">';	 
							 html +='<div class="box">';
							 html +='<div class="logo">';
							 html +='<span class="bg-cgf ico-tag1">今日 </span>';
							 html +='<span class="bg-cgf ico-tag2">活动</span>';
							 html +='<span class="bg-cgf ico-tag3">品牌</span>';
							html +='<span class="bg-cgf ico-tag4">限量<br>100件</span>';
							 html +='</div>';
							 html +='<a target="_blank" href="#">';
							 html +='<img src="'+fileAccessPath+data[i].goodsicon+'">';
							 html +='</a>';
							 html +='<span class="box_activity">';
						     html +='<i class="fa fa-clock-o"></i>';
							html +='距活动结束<span>00:04:56:15</span>';
							html +='</span>';
							html +='<div class="box_hover">';
							html +='<span class="sale">已售'+data[i].buyNum+'份</span>';
					                 
							html +='</div>';
							html +='</div>';
							html +='<div class="box_list">';
							html +='<h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>';
							html +='<div class="list_logo">';
							html +='<span class="price-new"><em>¥</em>'+data[i].chuprice+'</span>';
							html +='<del class="price-old"><em>¥</em>'+data[i].normalprice+'</del>';
							html +='</div>';
							html +='<div class="box_listl">';
							 html +='<span>-53.3</span>';
							html +='<div class="ico-you">购买后减67%</div>';
							html +='</div>';
							html +='<div class="box_listr">';
							html +='<a href="#" class="" target="_blank">立即购买</a>';
							html +='</div>';
							html +='</div>';
							html +='</div>';
							 }

							 //alert(html);
								$("#superhuishops").append(html);
						
						console.log('结果'+html);
											
					}else{
					
					}
				}
				 }); 
	   
	   
	   
}



function scroll(){
	   //alert("1---");
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
								ajaxxx();
							iisstop=0; //解除锁定
							}
							}
							}
						});
	}





	//加载更多渠道
	var start = 6;
	var count = 20;
	var ajaxload = false; 
	var iisstop=0;
	winHeight = $(window).height();
	//alert("hjjhhhh"+winHeight);
	scroll();
	function ff(){
		ajaxload = true;
	}
	function ajaxxx(){
		
		var id=$(".current").attr("id");
		var name=$(".current").attr("gjj");
		//alert(id);
		//alert("XXXXXXXX"+name);
		if(name=="全部"){
			$.ajax({
				type: "GET",
	           url: "${basePath}pc/superhuiGoods/ajaxAllList?id="+id+"&start=" + start,
	           dataType: "json",
				success : function(data) {
					//console.log("data.length="+data.length);
					console.log("data.length="+data.length);
					if(data.length != 0){
						//$("#superhuishops").html("");
						var html = " ";
						
							 for (var i = 0; i < data.length; i++) {
								
								 console.log("data.i="+i);
								 
							 html +='<div class="product-box">';	 
							 html +='<div class="box">';
							 html +='<div class="logo">';
							 html +='<span class="bg-cgf ico-tag1">今日 </span>';
							 html +='<span class="bg-cgf ico-tag2">活动</span>';
							 html +='<span class="bg-cgf ico-tag3">品牌</span>';
							html +='<span class="bg-cgf ico-tag4">限量<br>100件</span>';
							 html +='</div>';
							 html +='<a target="_blank" href="#">';
							 html +='<img src="'+fileAccessPath+data[i].goodsicon+'">';
							 html +='</a>';
							 html +='<span class="box_activity">';
						     html +='<i class="fa fa-clock-o"></i>';
							html +='距活动结束<span>00:04:56:15</span>';
							html +='</span>';
							html +='<div class="box_hover">';
							html +='<span class="sale">已售'+data[i].buyNum+'份</span>';
					                 
							html +='</div>';
							html +='</div>';
							html +='<div class="box_list">';
							html +='<h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>';
							html +='<div class="list_logo">';
							html +='<span class="price-new"><em>¥</em>'+data[i].chuprice+'</span>';
							html +='<del class="price-old"><em>¥</em>'+data[i].normalprice+'</del>';
							html +='</div>';
							html +='<div class="box_listl">';
							 html +='<span>-53.3</span>';
							html +='<div class="ico-you">购买后减67%</div>';
							html +='</div>';
							html +='<div class="box_listr">';
							html +='<a href="#" class="" target="_blank">立即购买</a>';
							html +='</div>';
							html +='</div>';
							html +='</div>';
							 }

							 //alert(html);
								$("#superhuishops").append(html);
						
						console.log('结果'+html);
		
						ajaxload = false;
						start = start + 6;
					}else{
						ajaxload = true;
						ajaxtip("已经到底了哦！");
					}
				}
				 });
		}else{
			$.ajax({
				type: "GET",
	           url: "${basePath}pc/superhuiGoods/ajaxList?id="+id+"&start=" + start,
	           dataType: "json",
				success : function(data) {
					//console.log("data.length="+data.length);
					console.log("data.length="+data.length);
					if(data.length != 0){
						//$("#superhuishops").html("");
						var html = " ";
						
							 for (var i = 0; i < data.length; i++) {
								
								 console.log("data.i="+i);
								 
							 html +='<div class="product-box">';	 
							 html +='<div class="box">';
							 html +='<div class="logo">';
							 html +='<span class="bg-cgf ico-tag1">今日 </span>';
							 html +='<span class="bg-cgf ico-tag2">活动</span>';
							 html +='<span class="bg-cgf ico-tag3">品牌</span>';
							html +='<span class="bg-cgf ico-tag4">限量<br>100件</span>';
							 html +='</div>';
							 html +='<a target="_blank" href="#">';
							 html +='<img src="'+fileAccessPath+data[i].goodsicon+'">';
							 html +='</a>';
							 html +='<span class="box_activity">';
						     html +='<i class="fa fa-clock-o"></i>';
							html +='距活动结束<span>00:04:56:15</span>';
							html +='</span>';
							html +='<div class="box_hover">';
							html +='<span class="sale">已售'+data[i].buyNum+'份</span>';
					                 
							html +='</div>';
							html +='</div>';
							html +='<div class="box_list">';
							html +='<h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>';
							html +='<div class="list_logo">';
							html +='<span class="price-new"><em>¥</em>'+data[i].chuprice+'</span>';
							html +='<del class="price-old"><em>¥</em>'+data[i].normalprice+'</del>';
							html +='</div>';
							html +='<div class="box_listl">';
							 html +='<span>-53.3</span>';
							html +='<div class="ico-you">购买后减67%</div>';
							html +='</div>';
							html +='<div class="box_listr">';
							html +='<a href="#" class="" target="_blank">立即购买</a>';
							html +='</div>';
							html +='</div>';
							html +='</div>';
							 }

							 //alert(html);
								$("#superhuishops").append(html);
						
						console.log('结果'+html);
		
						ajaxload = false;
						start = start + 6;
					}else{
						ajaxload = true;
						ajaxtip("已经到底了哦！");
					}
				}
				 });	
			
			
		}
	
	}
function ajaxtip(txt) {
$(".get_more span").html(txt);
}









$(function() {
	//默认加载（全部）
 allsuiper();
	
	
	 
	// 超级优惠商品分类
	$('.show-list .show-listjj li').click(function(event) {
		//var id=$(this).attr("id");
		var id=$(this).attr("id");
		
		var name=$(this).attr("gjj");
		
		start = 6;
		ajaxload = false; 
	    iisstop=0;
		winHeight = $(window).height();
	//alert("hjj"+winHeight);
	if(name=="全部"){
		
		allsuiper();	
		scroll();
	}else{
		//alert();
	  $.ajax({
			type: "GET",
    url: "${basePath}pc/superhuiGoods/ajaxList?start=0&id="+id,
    dataType: "json",
			success : function(data) {
				$("#superhuishops").html("");
				//alert();
				console.log("data.length="+data.length);
				if(data.length != 0){
				//	$("#webShop").html("");
					var html = "";
					
						 for (var i = 0; i < data.length; i++) {
							
			
						 html +='<div class="product-box">';
						 html +='<div class="box">';
						 html +='<div class="logo">';
						 html +='<span class="bg-cgf ico-tag1">今日 </span>';
						 html +='<span class="bg-cgf ico-tag2">活动</span>';
						 html +='<span class="bg-cgf ico-tag3">品牌</span>';
						// html +='<span class="bg-cgf ico-tag4">'+限量+'<br>'+100件+'</span>';
						 html +='</div>';
						 html +='  <a target="_blank" href="#">';
						 html +='             <img src="'+fileAccessPath+data[i].goodsicon+'">';
						 html +='       </a>';
						 html +='          <span class="box_activity">';
					     html +='              <i class="fa fa-clock-o"></i>';
						html +='         距活动结束<span>00:04:56:15</span>';
						html +='           </span>';
						html +='          <div class="box_hover">';
						html +='             <span class="sale">已售0份</span>';
				                 
						html +='       </div>';
						html +='       </div>';
						html +='         <div class="box_list">';
						html +='           <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>';
						html +='         <div class="list_logo">';
						html +='          <span class="price-new"><em>¥</em>79</span>';
						html +='           <del class="price-old"><em>¥</em>339</del>';
						html +='         </div>';
						html +='        <div class="box_listl">';
						 html +='             <span>-53.3</span>';
						html +='          <div class="ico-you">购买后减67%</div>';
						html +='       </div>';
						html +='       <div class="box_listr">';
						html +='        <a href="#" class="" target="_blank">立即购买</a>';
						html +='        </div>';
						html +='       </div>';
						html +='       </div>';

						 }

					 
					
					console.log('结果'+html);
					$("#superhuishops").append(html);					
				}else{
				
				}
			}
	  
			 }); 
	
	  scroll();
	}
	
	
	 });
	

});
</script>
</html>
