<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>导购网</title>
    <link rel="stylesheet" href="${path }/css/reset.css"/>
    <link rel="stylesheet" href="${path }/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path }/css/style.css"/>
</head>
<body>
    <!--scroll-head-->
    <div class="scroll-head"></div>
    <!--top-->
    <div class="top-wrapper">
        <div class="top-info">
            <div class="top-left">
                <div data-toggle="arrowdown" id="arrow1" class="user-name">
                    <a href="#">亲，请登录</a>
                </div>
                <div data-toggle="arrowdown" id="arrow2" class="msg-info">
                    <i class="fa fa-envelope fa-gray"></i>
                    <a href="#">消息</a>
                    <span class="down-icon"></span>
                </div>
                <a class="a-float-left" href="#">手机导购</a>
                <div data-toggle="hidden-box" id="nav-box2" class="msg-box">
                    <h1>未读新消息<a href="#" class="fa fa-pencil-square-o pencil"></a></h1>
                    <div class="read-info">
                        <h2><span class="orange">&nbsp;&nbsp;|</span>&nbsp;你的书架&nbsp;<span style="font-weight: lighter">收到了<span class="orange">1</span>本书</span><span class="fa fa-times close-msg"></span></h2>
                        <img src="img/book-1.png" />
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
            <!--top-right-->
            <div class="top-right">
                <div data-toggle="arrowdown" id="arrow3" class="user-name">
                    <a href="#">我的导购</a>
                    <span class="down-icon"></span>
                </div>
                <div data-toggle="arrowdown" id="arrow4" class="user-name">
                    <i class="fa fa-shopping-cart fa-orange"></i>
                    <a href="#">导购车</a>
                    <span class="down-icon"></span>
                </div>
                <div data-toggle="arrowdown" id="arrow5" class="user-name">
                    <i class="fa fa-star fa-gray"></i>
                    <a href="#">收藏夹</a>
                    <span class="down-icon"></span>
                </div>
                <a class="a-float-left" href="#">商品分类</a>
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
        <div class="scroll-search">
            <div class="search-litter">
                <img class="scroll-logo" src="${path }/img/logo2.png" />
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
                    <!-- <li>本地生活</li>
                    <li>虚拟服务</li> -->
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

<!--top-main-->
<div class="top-main">
    <img src="${path }/img/logo.png" />
    <div class="search-wrapper">
        <div class="search-box">
            <input class="search-in" type="text" placeholder="输入你想要的商品吧~">
            <input type="button" class="search-but" value="搜索">
        </div>
    </div>
    <!--two-code-->
    <div class="two-code">
        <h3>手机导购</h3>
        <img src="${path }/img/code.png" />
        <div class="close-code"><i class="fa fa-times"></i></div>
    </div>
</div>
<!--content-top-->
<div class="content-top">
    <!--sidebar-->
    <div class="sidebar">
        <h3>导购商品分类</h3>
        <!--sidebar-info-->
        <div class="sidebar-info">
            <ul class="side-li">
                <li class="s_1"><h3>服装内衣</h3></li>
                <li class="s_2"><h3>鞋包配饰</h3></li>
                <li class="s_3"><h3>运动户外</h3></li>
                <li class="s_4"><h3>珠宝手表</h3></li>
                <li class="s_5"><h3>手机数码</h3></li>
                <li class="s_6"><h3>家电办公</h3></li>
                <li class="s_7"><h3>护肤彩妆</h3>v</li>
                <li class="s_8"><h3>母婴用品</h3></li>
                <li class="s_9"><h3>家纺居家</h3></li>
                <li class="s_10"><h3>家具建材</h3></li>
                <li class="s_11"><h3>美食特产</h3></li>
                <li class="s_12"><h3>日用百货</h3></li>
                <li class="s_13"><h3>汽车摩托</h3></li>
                <li class="s_14"><h3>文化娱乐</h3></li>
                <li class="s_15"><h3>本地生活</h3></li>
                <li class="s_16"><h3>虚拟服务</h3></li>
            </ul>
        </div>
    </div>
    <!--right-con-->
    <div class="right-con">
        <div class="nav">
            <a id="spe-a1" href="#">天猫</a>
            <a id="spe-a2" href="#">聚划算</a>
            <a id="spe-a3" href="#">二手</a>
            <span class="line-a">|</span>
            <a href="#">拍卖</a>
            <a href="#">一淘</a>
            <a href="#">电器城</a>
            <a href="#">Hitao粉妆</a>
            <a href="#">旅行</a>
            <a href="#">云手机</a>
            <a href="#">特色中国</a>
            <img style="cursor: pointer" src="${path }/img/ad.gif" />
            <span class="keep-a" href="#"><a href="#">消费者保障</a></span>
        </div>
        <!--show-box-->
        <div class="show-box">
            <!--content-->
            <div class="content">
                <ul class="imgBox">
                    <li><a href="#"><img src="${path }/img/1.jpg"></a></li>
                    <li><a href="#"><img src="${path }/img/2.jpg"></a></li>
                    <li><a href="#"><img src="${path }/img/3.jpg"></a></li>
                    <li><a href="#"><img src="${path }/img/4.jpg"></a></li>
                    <li><a href="#"><img src="${path }/img/5.jpg"></a></li>
                </ul>
                <div class="currentNum">
                    <span class="imgNum mark-color"></span>
                    <span class="imgNum"></span>
                    <span class="imgNum"></span>
                    <span class="imgNum"></span>
                    <span class="imgNum"></span>
                </div>
                <div class="control to-left"><i class="fa fa-angle-left"></i></div>
                <div class="control to-right"><i class="fa fa-angle-right"></i></div>
            </div>
            <a style="float: left" href="#"><img src="${path }/img/6.6.jpg" /></a>
            <a style="float: left" href="#"><img src="${path }/img/6.6.jpg" /></a>
        </div>
    </div>
</div>
<div class="cooperPartner">
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/appstore.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/dangdang.png" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/guomei.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/jingdong.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/meituan.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/nuomi.png" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/suning.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/taobao.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/tianmao.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/xiecheng.png" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/yamaxun.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/yihaodian.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/yilong.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/yixun.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/dongfang.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/jumei.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/ali.png" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/womai.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/maibaobao.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="cooper-logo">
        <img src="${path }/img/cooperlogo/lashouwang.gif" alt="App Store">优惠30%
    </a>
    <a href="#" class="arrow-next">更多商家</a>
</div>
<!--main-->
<div class="main">
    <div class="main-left">
        <div class="main-title">
            <h1>万能的导购<span class="show-title">下面有30个特色市场等你来逛哦！</span></h1>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="${path }/img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
        <div class="product-box">
            <div class="box">
                <a target="_blank" href="#">
                    <img src="img/show1.png">
                </a>
                <span class="box_activity">
                    <i class="fa fa-clock-o"></i>
                    距活动结束<span>00:04:56:15</span>
                </span>
                <div class="box_hover">
                    <span class="sale">已售0份</span>
                    <span class="money">定金¥1,000.00</span>
                </div>
            </div>
            <div class="box_list">
                <h3><a target="_blank" title="" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></h3>
                <div class="list_logo"><img src="${path }/img/cooperlogo/maibaobao.gif" /></div>
                <div class="box_listl">
                    <div class="pb_Price">
                        ￥<span>2400.00</span>
                        <p>当前价</p>
                    </div>
                </div>
                <div class="box_listr">
                    <a href="#" class="" target="_blank">立即购买</a>
                    <p>每增加1份再优惠0.1折</p>
                </div>
            </div>
        </div>
    </div>
    <div class="main-right">
        <div class="time-go">
            <div class="time-cell">
                <h1>今日上新<span class="now-news">更新<span class="">3</span></span></h1>
                <div class="inner-show">
                    <ul>
                        <li>
                           <a href="#">
                            <img src="${path }/img/home.jpg">
                        </a>
                        <div class="show-right">
                            <div class="nowPrice"><i>￥1020</i><s>￥1088</s></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                    </li>
                    <li>
                           <a href="#">
                            <img src="${path }/img/home.jpg">
                        </a>
                        <div class="show-right">
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                    </li>
                    <li>
                           <a href="#">
                            <img src="${path }/img/home.jpg">
                        </a>
                        <div class="show-right">
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                    </li>
                    <li>
                           <a href="#">
                            <img src="img/home.jpg">
                        </a>
                        <div class="show-right">
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                    </li>
                    <li>
                           <a href="#">
                            <img src="img/home.jpg">
                        </a>
                        <div class="show-right">
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
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
        <!--fix-right-->
        <div class="fix-right-sub">
            <!--gogo-choose-->
            <div class="gogo-choose">
                <h1>大家都选</h1>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="img/sub3.jpg"></a></li>
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
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                </div>
               <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="img/sub3.jpg"></a></li>
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
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                </div>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="img/sub3.jpg"></a></li>
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
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                </div>
                <div class="sub-show">
                    <div class="content-sub">
                        <ul class="imgBox2">
                            <li><a href="#"><img src="img/sub1.jpg"></a></li>
                            <li><a href="#"><img src="img/sub2.jpg"></a></li>
                            <li><a href="#"><img src="img/sub3.jpg"></a></li>
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
                            <div class="nowPrice">导购价：<i>￥1020</i></div>
                            <p class="desc"><a target="_blank" href="#">荣耀 6 Plus (PE-TL10) 白色 移动联通双4G手机 双卡双待双通</a></p>
                            <p class="consumer">已有<i>2309</i>人购买</p>
                        </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!--main-bottom-->
<div class="main-bottom">
    <div class="sub-bottom-nav">
        <h1>热卖单品</h1>
        <div class="sub-nav-info">
         <a href="#">s5皮套</a>|
         <a href="#">Bose</a>|
         <a href="#">春秋</a>|
         <a href="#">定制</a>|
         <a href="#">高腰裤</a>|
         <a href="#">打底裙</a>|
         <a href="#">文胸</a>|
         <a href="#">春装</a>|
         <a href="#">男鞋</a>|
         <a href="#">iphone</a>|
         <a href="#">婚纱</a>|
         <a href="#">男牛仔</a>|
         <a href="#">女T恤</a>|
         <a href="#">针织衫</a>|
         <a href="#">客厅灯</a>|
         <a href="#">巧克力</a>|
         <a href="#">更多</a>
     </div>
 </div>
 <div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
<div class="single-sale">
    <a href="#">
        <img src="img/sing1.jpg" />
        <h3>褶皱立领雪纺衫</h3>
    </a>
    <p><span class="orange">￥&nbsp;</span><span class="orange">129.00</span><span class="send-free">包邮</span></p>
    <p class="gary-text"><span>￥&nbsp;</span><span><del>399.00</del></span>&nbsp;|&nbsp;<span>月消29件</span></p>
</div>
</div>
<!--ad-->
<div class="ad">
    <img src="img/ad.jpg" />
    <img src="img/ad1.jpg" />
</div>
<!--help-->
<div class="help">
    <div class="help-info">
        <h1><img src="${path }/img/help4.png"><span class="help-text">消费者保障</span></h1>
        <a class="help-a" href="#">保障范围</a>
        <a class="help-a" href="#">退货退款流程</a>
        <a class="help-a" href="#">服务中心</a>
        <a class="help-a" href="#">更多特色服务</a>
    </div>
    <div class="help-info">
        <h1><img src="${path }/img/help1.png"><span class="help-text">新手上路</span></h1>
        <a class="help-a" href="#">新手专区</a>
        <a class="help-a"v href="#">消费警示</a>
        <a class="help-a" href="#">交易安全</a>
        <a class="help-a margin-r" href="#">24小时在线帮助</a>
    </div>
    <div class="help-info">
        <h1><img src="${path }/img/help2.png"><span class="help-text">付款方式</span></h1>
        <a class="help-a-litter" href="#">支付宝快捷支付</a>
        <a class="help-a-litter" href="#">支付宝卡（现金）付款</a>
        <a class="help-a-litter" href="#">支付宝余额付款</a>
        <a class="help-a" href="#">货到付款</a>
    </div>
    <div class="help-info">
        <h1><img src="${path }/img/help3.png"><span class="help-text">导购特色</span></h1>
        <a class="help-a" href="#">导购指数</a>
        <a class="help-a" href="#">淘公仔</a>
        <a class="help-a" href="#">手机导购</a>
        <a class="help-a" href="#">旺信</a>
    </div>
</div>
<!--footer-->
<div class="footer">
    <div class="footer-right">
        <div class="footer-nav">
            <a href="#">阿里巴巴集团</a>|
            <a href="#">阿里巴巴国际站</a>|
            <a href="#">阿里巴巴中国站</a>|
            <a href="#">全球速卖通</a>|
            <a href="#">导购网</a>|
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
        <div class="about-tao">
            <a href="#">关于导购</a>
            <a href="#">合作伙伴</a>
            <a href="#">营销中心</a>
            <a href="#">廉正举报</a>
            <a href="#">联系客服</a>
            <a href="#">开放平台</a>
            <a href="#">诚征英才</a>
            <a href="#">联系我们</a>
            <a href="#">网站地图</a>
            <a href="#">法律声明</a>
            <span class="gary-text">&copy; 2014 Taobao.com 版权所有</span>
        </div>
        <p class="gary-text"><span>网络文化经营许可证：文网文[2010]040号</span>|<span>增值电信业务经营许可证：浙B2-20080224-1</span>|<span>信息网络传播视听节目许可证：1109364号</span></p>
        <div class="some-info">
            <img src="${path }/img/some.png" />
        </div>
    </div>
    <div class="tao-man">
        <img src="${path }/img/tao-man.png" />
    </div>
</div>
<!--backtoTop1-->
<div class="backtoTop" id="backToTop1">
    <div id="backToTop-up" class="up-back"><i class="fa fa-angle-up"></i></div>
    <div id="backToTop-down" class="down-back"><i class="fa fa-angle-down"></i></div>
</div>
</div>
<script src="${path }/js/jquery_1.9.js"></script>
<script src="${path }/js/main.js"></script>
<script src="${path }/js/img-show.js"></script>
</body>
</html>
