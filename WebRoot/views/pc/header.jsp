<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.model.user.User" %>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<%@ page language="java" import="com.yoxi.hudongtui.utils.common.ConvertUtil" %>
 <link rel="stylesheet" href="${path}/css/common.css"/>
    <link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/css/style.css"/>
	<div id="header">
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
                <div class="scroll-list">
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
                </div>
                <div class="search-wrapper-scroll">
                    <div class="search-box-scroll">
                        <input class="search-in-scroll" type="text" placeholder="想要的商品吧~" />
                        <input type="button" class="search-but-scroll" value="搜索">
                    </div>
                </div>
            </div>
        </div>
    </div>
	</div>
<script type="text/javascript">
	var header_url = self.location + '';
	var basePath = '${basePath}';
	$(document).ready(function(){
		if(header_url.indexOf("/act") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(1).attr("class","active");
		}else if(header_url.indexOf("/plugin/pluginList") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(2).attr("class","active");
		}else if(header_url.indexOf("/channel/index") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(3).attr("class","active");
		}else if(header_url.indexOf("/other/question") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(4).attr("class","active");
		}else if(header_url.indexOf("/login") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(5).attr("class","active");
		}else if(header_url.indexOf("/register/pc") != -1){
			$('.nav a').attr("class","");
			$('.nav a').eq(6).attr("class","active");
		}else{
			$('.nav a').attr("class","");
			$('.nav a').eq(0).attr("class","active");
		}
	});


</script>
