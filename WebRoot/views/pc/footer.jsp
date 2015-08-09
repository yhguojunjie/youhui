<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script type="text/javascript">
	BizQQWPA.addCustom({aty: '1', a: '1', nameAccount: '${agentInfoConst.serviceqq }', selector: 'BizQQWPAFooter'});
</script>
	<div class="w1190 foot">
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
		
	</div>
	<!-- 底部 结束 -->
	<!-- 浮动菜单 开始 -->
	<div class="float_menu" style="display:none;">
		<ul class="clearfix">
			<li class="sliding0">
				<a><em class="phone_ico"></em><span>${agentInfoConst.servicePhone }</span></a>
			</li>
			<li class="clear"></li>
			<li class="sliding1">
				<c:choose>
					<c:when test="${fn:startsWith(agentInfoConst.serviceqq,'400') || fn:startsWith(agentInfoConst.serviceqq,'800')}">
						<a  href="javascript:void(0);" id="BizQQWPAFooter" class="qq_btn"><em class="qq_ico"></em><span>点击QQ交谈</span></a>
					</c:when>
					<c:otherwise>
						<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${agentInfoConst.serviceqq }&site=qq&menu=yes" class="qq_btn"><em class="qq_ico"></em><span>点击QQ交谈</span></a>
					</c:otherwise>
				</c:choose>
			</li>
			<li class="clear"></li>
			<c:if test="${agentInfoConst.wxqrcode != null}">
				<li id="float_menucode">
					<a href="#" class="sliding"><img src="${agentInfoConst.wxqrcode}"></a>
				</li>
			</c:if>

			<li class="clear"></li>
			<li class="gotop">
				<a><em class="gotop_ico">返回顶部</em></a>
			</li>
		</ul>
	</div>
  
  
<script type="text/javascript">
	$(function(){
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
		// 浮动菜单
		$(".gotop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		var basePath = '${basePath}';
		var path = window.location.href;
		
		if(basePath==path){
			$(window).scroll(function(){
				if($(window).scrollTop() > 400){
					$('.float_menu').attr("style","display:block;");
				}else{
					$('.float_menu').attr("style","display:none;");
				}
			});
		}else{
			$('.float_menu').attr("style","display:block;");
			$(window).scroll(function(){
				if($(window).scrollTop() > 10){
					$('.gotop_ico').attr("style","display:block;");
				}else{
					$('.gotop_ico').attr("style","display:none;");
				}
			}); 
		}
	});
</script>