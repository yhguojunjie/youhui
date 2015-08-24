<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="constant.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="${path}/css/common.css"/>
    <link rel="stylesheet" href="${path}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/css/style.css"/>
</head>
<body class="reg">
   <div class="top-wrapper">
     <div class="top-info">
        <div class="top-left">
            <ul>
               <li><a href="${basePath}login">亲，请登录</a><a href="#">注册</a></li>
               <li class="spacer"></li>
               <li data-toggle="arrowdown" id="arrow2" class="msg-info">
                <a href="#">我的优惠</a>
                <span class="down-icon"></span>
                <div data-toggle="hidden-box" id="nav-box2" class="msg-box">
                    <div class="userinfo">
                        <div class="u-pic">

                        </div>
                        <div class="u-name u-login">
                            <a href="${basePath}login" class="link-login">你好，请登录</a>
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
</div>
<div class="reg-content">
    <div class="box-reg">
        <div class="con-1">
            <form action="${basePath}register/doRegister" id="form_reg" name="form2" method="post" >
                <div class="form-item">
                    <label for="reg_mail" class="label">
                        <b class="require">*</b>常用邮箱：
                    </label>
                    <input verify="true" type="text" name="email" id="email" class="txt auto_input" value=""/>
                    <label class="tip_message"><span class="tip warn"><i></i>请填写您常用的邮箱作为登录账号</span></label>
                </div>
                <div class="form-item">
                    <label for="reg_password" class="label">
                        <b class="require">*</b>设置密码：
                    </label>
                    <input verify="true" type="password" name="password" id="password" class="txt"/>
                    <label class="tip_message"><span class="tip warn"><i></i>6-20位字符，建议由字母，数字和符号两种以上组合</span></label>
                </div>
                <div class="form-item">
                    <label for="reg_password_again" class="label"><b class="require">*</b>确认密码：</label>
                    <input verify="true" type="password" name="repass" id="repass" class="txt" tabindex="3" />
                </div>
                <div class="form-item">
                    <label for="reg_check_code" class="label">
                        <b class="require">*</b>验证码：
                    </label>
                    <input type="text" id="reg_check_code" name="checkcode" class="txt txt-1" verify="true" />
                    <img id="reg_checkcode" onclick="reloadImage();" src="${path}/RandomValidateCodeServlet" title="点击刷新验证码" alt ="点击刷新验证码"/>
                    看不清？
                    <a id="check-code-change" class="check-code-change" onclick="reloadImage();">换一张</a>
                    <p class="tiptext"></p>              
                </div>
                <div class="form-item">
                    <p class="text-term">
                        <label for="CheckTerm">
                            <input type="checkbox" checked="checked" id="CheckTerm" class="checked"/>我已阅读并同意
                            <a href="#">《帮you惠用户注册协议》</a>
                        </label>
                        <span class="tiptext tiptext-error"><i></i>您还未同意返还网的服务条款</span>
                    </p>
                    <input  type="submit"  onclick="verify_submit();" class="btn-regist" value="立即注册">
                </div>
            </form>
        </div>
        <div class="con-2">
            <h3>已有帮you惠帐号？</h3>
            <div class="btn-outer"><a href="${basePath}login" class="btn btn-2">登&nbsp;录</a></div>
            <p>使用合作网站账号登录帮you惠</p>
            <ul>
                <li><a href="#" class="f14"><i class="ico-login-otherway i-login-otherway-qq"></i>QQ</a></li>
                <li><a href="#"><i class="ico-login-otherway i-login-otherway-tb"></i>淘宝</a></li>
                <li><a href="#"><i class="ico-login-otherway i-login-otherway-sina"></i>新浪微博</a></li>
                <li><a href="#"><i class="ico-login-otherway i-login-otherway-weixin"></i>微信</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- 底部 开始 -->
		<%@ include file="footer.jsp" %>
<!-- 底部 结束 -->
<script>
//更换验证码
	function reloadImage(){
		 $("#reg_checkcode").attr("disable","true");
		 $("#reg_checkcode").attr("src",'<%=path%>/RandomValidateCodeServlet?ts='+new Date().getTime());
		 $("#reg_checkcode").attr("disable","false");
	} 
	//var flag=true;
	//提交表单前端验证
	function verify_submit(){
		var verify = 0;
		$('input[type="text"],input[type="password"]').each(function(){
			$(this).blur();
			if($(this).attr("verify") == "false"){
				verify += 1;
			}
		});
		//alert(verify);
		if(verify > 0){
			return;
		}else{
			//alert("33---");
			 document.form2.submit();
		}
	
	}
</script>
</body>
</html>