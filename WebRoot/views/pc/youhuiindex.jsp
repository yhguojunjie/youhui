<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ConvertUtil" %>
<%@ include file="constant.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>帮you惠，优惠网！我喜欢</title>
    <link rel="stylesheet" href="${path}/css/common.css"/>
    <link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/css/style.css"/>

</head>
<body>
    <div class="top-wrapper">
        <div class="top-info">
            <div class="top-left">
            <ul>
            
            				<% if(request.getSession().getAttribute(Globals.SESSION_USER) == null){ %>
					 <li><a href="${basePath}login">亲，请登录</a><a href="${basePath}register/pc">注册</a></li>
             
				<% }else{ 
					User userHeader = (User)request.getSession().getAttribute(Globals.SESSION_USER); 
					if(userHeader.getHeadimgUrl() != null){
						userHeader.setHeadimgUrl(ConvertUtil.procImgPath(userHeader.getHeadimgUrl()));
					}
				%>
				
				 <li><a href="#"><%=userHeader.getEmail() %></a><a href="${basePath}logout">(退出)</a></li>
             <% } %>
			   <li class="spacer"></li>
               <li data-toggle="arrowdown" id="arrow2" class="msg-info">
                    <a href="#">我的优惠</a>
                    <span class="down-icon"></span>
                    <div data-toggle="hidden-box" id="nav-box2" class="msg-box">
						<div class="userinfo">
							<div class="u-pic">
								
							</div>
							<div class="u-name u-login">			
								<a href="#" class="link-login">你好，请登录</a>
							</div>
							<div class="u-extra">
								<a href="#">优惠券</a><span class="line-a">|</span><a href="#">消息</a>
							</div>
						</div>
						<div class="otherlist">
							<div class="fore1">
								<div class="item">
									<a href="#" clstag="" target="_blank">待处理订单</a>
								</div>
								<div class="item">
									<a href="#" clstag="" target="_blank">咨询回复<span id="num-consultation"></span></a>
								</div>
								<div class="item">
									<a href="#" clstag="" target="_blank">降价商品<span id="num-reduction"></span></a>
								</div>
								<div class="item">
									<a href="#" clstag="" target="_blank">返修退换货</a>
								</div>
							</div>
							<div class="fore2">
								<div class="item">
									<a href="#" clstag="" target="_blank">我的关注</a>
								</div>
								<div class="item">
									<a href="#" clstag="" target="_blank">我的京豆</a>
								</div>
								<div class="item">
									<a href="#" clstag="" target="_blank">我的理财</a>
								</div>
								<div class="item baitiao hide" style="display:block">
									<a href="#" clstag="jr|keycount|njdhome|wdbaitiao" target="_blank">我的白条</a>
								</div>
							</div>
						</div>
                </div>
                </li>
               <li class="spacer"></li>
               <li><a href="#">我的订单</a></li>
            </ul>        
            </div>
            <div class="top-right">
            <ul>
                <li data-toggle="arrowdown" id="arrow3" class="user-name">
                    <a href="#">我的优惠</a>
                    <span class="down-icon"></span>
                    <div data-toggle="hidden-box" id="nav-box3" class="my-taobao-box">
                    <ul>
                        <li>已购宝贝</li>
                        <li>我的优惠</li>
                    </ul>
                </div>
                </li>
                <li class="spacer"></li>
                <li data-toggle="arrowdown" id="arrow4" class="user-name">
                    <i class="fa fa-shopping-cart fa-orange"></i>
                    <a href="#">优惠车</a>
                    <span class="down-icon"></span>
                    <div data-toggle="hidden-box" id="nav-box4" class="shopping-box">
                    <span>您购物车里还没有任何宝贝。</span><a class="check-shopp" href="#">查看我的购物车</a>
                </div>
                </li>
                <li class="spacer"></li>
                <li data-toggle="arrowdown" id="arrow5" class="user-name">
                    <i class="fa fa-star fa-gray"></i>
                    <a href="#">收藏夹</a>
                    <span class="down-icon"></span>
                    <div data-toggle="hidden-box" id="nav-box5" class="get-box">
                    <ul>
                        <li>收藏的宝贝</li>
                        <li>收藏的店铺</li>
                    </ul>
                </div>
                </li>
                <li class="spacer"></li>
                <li href="#">商品分类</li>                   
             </ul>
            </div>
        </div>
        <div class="scroll-search">
            <div class="search-litter">
                <img class="scroll-logo" src="${path}/img/logo2.png" />
                <div class="search-wrapper-scroll">
                    <div class="search-box-scroll">
                        <input class="search-in-scroll" type="text" placeholder="想要的商品吧~" />
                        <input type="button" class="search-but-scroll" value="搜索">
                    </div>
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
            <h3>手机优惠</h3>
            <img src="${path}/img/code.png" />
            <div class="close-code"><i class="fa fa-times"></i></div>
        </div>
    </div>
    <div class="typeList">
       <div class="line">
       </div>
    <div class="content-top">
       <div class="sidebar">
           <h3>优惠品分类</h3>
         <div class="sidebar-info">
            <ul class="side-li">
            	<c:forEach items="${goodsclassificatList}" var="goodsclassificat" varStatus="sta" >
		
		<c:if test="${sta.index==0}"> 
		  <li class="s_1 visited" id="${goodsclassificat.id }">${goodsclassificat.name }</li>
		</c:if> 
		<c:if test="${sta.index!=0}"> 
		 <li class="s_${sta.index+1}" id="${goodsclassificat.id }">${goodsclassificat.name }</li>
		
		</c:if>
		        </c:forEach>
               
              <!--   <li class="s_2">鞋包配饰</li>
                <li class="s_3">运动户外</li>
                <li class="s_4">珠宝手表</li>
                <li class="s_5">手机数码</li>
                <li class="s_6">家电办公</li>
                <li class="s_7">护肤彩妆</li>
                <li class="s_8">母婴用品</li>
                <li class="s_9">家纺居家</li>
                <li class="s_10">家具建材</li>
                <li class="s_11">美食特产</li>
                <li class="s_12">日用百货</li>
                <li class="s_13">汽车摩托</li>
                <li class="s_14">文化娱乐</li> -->
            </ul>
        </div>
    </div> 
    <div class="right-con">
        <div class="nav">
         <a class="spe" href="#">超高优惠</a>
            <a class="spe" href="#">限量特价抢购</a>
            <!-- <a class="spe" href="#">限时抢购</a> -->
            <!-- <a class="spe" href="#">多买多送</a> -->
            <a class="spe" href="#">推荐商品</a>          
            <span class="line-a">|</span>
            <a  href="#">商城优惠</a>
            <a  href="#">宝贝优惠</a>
            <!-- <a href="#">特卖场</a> -->
            <a href="#">9.9包邮</a>
            <a href="#">19.9包邮</a>
        <!--<a href="#">50元封顶</a>  --> 
             <a href="#">优惠券</a>
            <a href="#">本地优惠(O2O)</a>
            <a href="#">商家活动</a>    
        </div>
        <div class="content">
            <div class="con" id="webShop">
                <%--  <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                 <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>
                <a href="#" class="cooper-logo">
                    <img src="${path}/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
                </a>        
                <div class="con-list">
                    <h2>女装</h2>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
	                <a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a><a href="#">贝诗</a>
                </div>  --%>
            </div> 
        </div>
    </div> 
    </div>
</div> 
<div class="main">
    <div class="main-left">
        <div class="main-title">
            <h1>超高优惠</h1>
            <span class="show-list">
                    <ul>
                        <li>服装内衣</li>
                        <li>鞋包配饰</li>
                        <li>运动户外</li>
                        <li>珠宝手表</li>
                        <li>手机数码</li>
                        <li>家电办公</li>
                        <li>护肤彩妆</li>
                        <li>母婴用品</li>
                        <li>家纺居家</li>
                        <li>家具建材</li>
                        <li>美食特产</li>
                        <li>日用百货</li>
                        <li>汽车摩托</li>
                        <li>文化娱乐</li>
                    </ul>
            </span>
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>
        <div class="product-box">
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
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <!-- <span class="money">定金¥1,000.00</span> -->
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" href="#"><img src="${path}/img/cooperlogo/maibaobao.gif" />荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
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
        </div>

    </div>
    <div class="main-right">
        <div class="time-go">
            <div class="time-cell">
                <h1>今日限购特价商品<span class="now-news">更新<span class="">3</span></span></h1>
                <div class="inner-show">
                    <ul>
                        <li>
                            <a href="#"><img src="${path}/img/home.jpg"></a>
                            <div class="show-right">
                                <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                                <p class="desc">
                                    <a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a>
                                </p>
                                <p class="consumer">已有<i>2309</i>人购买</p>
                            </div>
                        </li>
                        <li>
                            <a href="#"><img src="${path}/img/home.jpg"></a>
                            <div class="show-right">
                                <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                                <p class="desc">
                                    <a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a>
                                </p>
                                <p class="consumer">已有<i>2309</i>人购买</p>
                            </div>
                        </li>
                        <li>
                            <a href="#"><img src="${path}/img/home.jpg"></a>
                            <div class="show-right">
                                <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                                <p class="desc">
                                    <a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a>
                                </p>
                                <p class="consumer">已有<i>2309</i>人购买</p>
                            </div>
                        </li>
                        <li>
                            <a href="#"><img src="${path}/img/home.jpg"></a>
                            <div class="show-right">
                                <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                                <p class="desc">
                                    <a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a>
                                </p>
                                <p class="consumer">已有<i>2309</i>人购买</p>
                            </div>
                        </li>
                        <li>
                            <a href="#"><img src="${path}/img/home.jpg"></a>
                            <div class="show-right">
                                <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                                <p class="desc">
                                    <a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a>
                                </p>
                                <p class="consumer">已有<i>2309</i>人购买</p>
                            </div>
                        </li>
                    </ul>
                    <h3><a href="$">更多店铺上新</a><i class="fa fa-angle-right"></i></h3>
                </div>
            </div>
            <div class="circle-new">新</div>
            <div class="time-circle"></div>
            <div class="time-circle bottom-circle"></div>
        </div>
        <div class="fix-right-sub">
            <div class="gogo-choose">
                <h1>今日推荐商品</h1>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="${path}/img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub3.jpg"></a></li>
                        </ul>
                        <div class="currentNum-sub">
                            <span class="imgNum2 mark-color"></span>
                            <span class="imgNum2"></span>
                            <span class="imgNum2"></span>
                        </div>
                        <div class="control2 to-left2"><i class="fa fa-angle-left"></i></div>
                        <div class="control2 to-right2"><i class="fa fa-angle-right"></i></div>
                    </div>
                    <div class="sub-right">
                        <div class="nowPrice">优惠价：<i>￥1020</i></div>
                        <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                        <p class="consumer">已有<i>2309</i>人购买</p>
                    </div>
                </div>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="${path}/img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub3.jpg"></a></li>
                        </ul>
                        <div class="currentNum-sub">
                            <span class="imgNum2 mark-color"></span>
                            <span class="imgNum2"></span>
                            <span class="imgNum2"></span>
                        </div>
                        <div class="control2 to-left2"><i class="fa fa-angle-left"></i></div>
                        <div class="control2 to-right2"><i class="fa fa-angle-right"></i></div>
                    </div>
                    <div class="sub-right">
                        <div class="nowPrice">优惠价：<i>￥1020</i></div>
                        <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                        <p class="consumer">已有<i>2309</i>人购买</p>
                    </div>
                </div>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="${path}/img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub3.jpg"></a></li>
                        </ul>
                        <div class="currentNum-sub">
                            <span class="imgNum2 mark-color"></span>
                            <span class="imgNum2"></span>
                            <span class="imgNum2"></span>
                        </div>
                        <div class="control2 to-left2"><i class="fa fa-angle-left"></i></div>
                        <div class="control2 to-right2"><i class="fa fa-angle-right"></i></div>
                    </div>
                    <div class="sub-right">
                        <div class="nowPrice">优惠价：<i>￥1020</i></div>
                        <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                        <p class="consumer">已有<i>2309</i>人购买</p>
                    </div>
                </div>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="${path}/img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="${path}/img/sub3.jpg"></a></li>
                        </ul>
                        <div class="currentNum-sub">
                            <span class="imgNum2 mark-color"></span>
                            <span class="imgNum2"></span>
                            <span class="imgNum2"></span>
                        </div>
                        <div class="control2 to-left2"><i class="fa fa-angle-left"></i></div>
                        <div class="control2 to-right2"><i class="fa fa-angle-right"></i></div>
                    </div>
                    <div class="sub-right">
                        <div class="nowPrice">优惠价：<i>￥1020</i></div>
                        <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                        <p class="consumer">已有<i>2309</i>人购买</p>
                    </div>
                </div>
            </div>
        </div>
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
        <h1><img src="${path}/img/help3.png"><span class="help-text">优惠特色</span></h1>
        <a class="help-a" href="#">优惠指数</a>
        <a class="help-a" href="#">淘公仔</a>
        <a class="help-a" href="#">手机优惠</a>
        <a class="help-a" href="#">旺信</a>
    </div>
</div>
<div class="footer">
    <div class="footer-right">
        <div class="footer-nav">
            <a href="#">阿里巴巴集团</a>|
            <a href="#">阿里巴巴国际站</a>|
            <a href="#">阿里巴巴中国站</a>|
            <a href="#">全球速卖通</a>|
            <a href="#">优惠网</a>|
            <a href="#">天猫</a>|
            <a href="#">聚划算</a>|
            <a href="#">一淘</a>|
            <a href="#">阿里妈妈</a>|
            <a href="#">阿里云计算</a>|
            <a href="#">云OS</a>|
            <a href="#">万网</a>|
            <a href="#">支付宝</a>|
            <a href="#">来往</a>
        </div>
        <div class="some-info">
            <img src="${path}/img/some.png" />
        </div>
        <div class="about-tao">
            <span class="gary-text">&copy; 2014 Taobao.com 版权所有</span>
            <a href="#">关于优惠</a>
            <a href="#">合作伙伴</a>
            <a href="#">营销中心</a>
            <a href="#">廉正举报</a>
            <a href="#">联系客服</a>
            <a href="#">开放平台</a>
            <a href="#">诚征英才</a>
            <a href="#">联系我们</a>
            <a href="#">网站地图</a>
            <a href="#">法律声明</a>
        </div>
    </div>
</div>
<div class="backtoTop" id="backToTop1">
    <div id="backToTop-up" class="up-back"><i class="fa fa-angle-up"></i></div>
    <div id="backToTop-down" class="down-back"><i class="fa fa-angle-down"></i></div>
</div>
<script type="text/javascript" src="${path}/js/jquery.js" ></script>
<script type="text/javascript" src="${path}/js/img-show.js"></script>
<script type="text/javascript" src="${path}/js/validate/automail.js" ></script>
<script type="text/javascript" src="${path}/js/main.js"></script>
<script >

   function f(oldArray){
	var newArray=new Array(); //目标数组

	var m=oldArray.length;
	for(var i=0;i<m;i++){
	var flag=true;
	var n=newArray.length;
	for(var j=0;j<n;j++)
	if(newArray[j] == oldArray[i])
	flag=false;
	if(flag)
	newArray[n]=oldArray[i];
	}
	return newArray;
	} 


$(function() {
	var fileAccessPath='${fileAccessPath}';
	 $.ajax({
			type: "GET",
     // url: "${basePath}pc/youHui/ajaxList?id="+idd,
    		 url: "${basePath}pc/youHui/ajaxList?id=17",
            dataType: "json",
			success : function(data) {
				var secondnameStr='';
				for (var j = 0; j < data.length; j++) {
					 secondnameStr=secondnameStr+";"+data[j].secondname;
				}
				arr=secondnameStr.split(";");
				var newarr=f(arr);
				
				console.log("data.length="+data.length);
				if(data.length != 0){
					$("#webShop").html("");
					var html = "";
					for(var z=1;z<newarr.length;z++){
					
						 for (var i = 0; i < data.length; i++) {
							 if(data[i].secondname==newarr[z]&&z==1){
						 html +='<a href="'+data[i].shophref+'" class="cooper-logo">';
						 html +='<img src="'+fileAccessPath+data[i].shopimge + '" alt="App Store">' + data[i].discountrate +'</a>';
							 }
						 }

						 html +='<div class="con-list">';
						 
						 if(z!=1){
						 html +='<h2>'+newarr[z]+'</h2>';
						 }						 
							 for (var i = 0; i < data.length; i++) {
								 if(data[i].secondname==newarr[z]&&z!=1){	 
						 html +='<a href="'+data[i].shophref+'">' + data[i].shopname +'</a>';
						 
							 }
						 }
						 html +='</div>';   
					}
					$("#webShop").append(html);
					
				}else{
				
				}
			}
			 }); 
	
	
	
	
	
	
	
$('.sidebar-info .side-li li').click(function(event) {
	var id=$(this).attr("id");
/*     $(this).addClass('visited').siblings().removeClass('visited');
    $(this).parents('.content-top').find('.right-con .content').addClass('visited');
      */
     var arr = new Array();
     
      $.ajax({
			type: "GET",
         url: "${basePath}pc/youHui/ajaxList?id="+id,
         dataType: "json",
			success : function(data) {
				var secondnameStr='';
				for (var j = 0; j < data.length; j++) {
					 secondnameStr=secondnameStr+";"+data[j].secondname;
				}
				arr=secondnameStr.split(";");
				var newarr=f(arr);
				
				console.log("data.length="+data.length);
				if(data.length != 0){
					$("#webShop").html("");
					var html = "";
					for(var z=1;z<newarr.length;z++){
					
						 for (var i = 0; i < data.length; i++) {
							 if(data[i].secondname==newarr[z]&&z==1){
						 html +='<a href="'+data[i].shophref+'" class="cooper-logo">';
						 html +='<img src="'+fileAccessPath+data[i].shopimge + '" alt="App Store">' + data[i].discountrate +'</a>';
							 }
						 }

						 html +='<div class="con-list">';
						 
						 if(z!=1){
						 html +='<h2>'+newarr[z]+'</h2>';
						 }						 
							 for (var i = 0; i < data.length; i++) {
								 if(data[i].secondname==newarr[z]&&z!=1){	 
						 html +='<a href="'+data[i].shophref+'">' + data[i].shopname +'</a>';
						 
							 }
						 }
						 html +='</div>';   
					}
					$("#webShop").append(html);
					
				}else{
				
				}
			}
			 }); 
     
     
     
     /*
     var index=$(this).index();
     $('.right-con .con').removeClass('dsb').eq(index).addClass('dsb'); */
 });

});
</script>
</body>
</html>
