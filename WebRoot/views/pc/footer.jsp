<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yoxi.hudongtui.constants.Globals" %>
<%@ page language="java" import="com.yoxi.hudongtui.vo.agent.AgentInfoVO" %>
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script type="text/javascript">
	BizQQWPA.addCustom({aty: '1', a: '1', nameAccount: '${agentInfoConst.serviceqq }', selector: 'BizQQWPAFooter'});
</script>
	<div class="w1190 foot">
		<h2><a href="${basePath}">
			<img alt="${agentInfoConst.logoDesc }" src="${agentInfoConst.logo }" />
		</a></h2>
		<p>
			<a href="${basePath }">首页</a>
			<em>|</em>
			<a href="${url_act }">活动圈</a>
			<em>|</em>
			<a href="${url_pluginList }">模板库</a>
			<em>|</em>
			<a href="${url_channel }">发布渠道</a>
			<em>|</em>
			<a href="${url_question }">帮助中心</a>
		</p>
		<p>
			<c:if test="${agentInfoConst.servicePhone != null}">
				<span>咨询电话：${agentInfoConst.servicePhone }</span>
			</c:if>
			<c:if test="${agentInfoConst.serviceqq != null}">
				<span >QQ：${agentInfoConst.serviceqq }</span>
			</c:if>
			
			<c:if test="${agentInfoConst.serviceEmail != null}">
				<span>邮箱：${agentInfoConst.serviceEmail }</span>
			</c:if>
		</p>
		<c:if test="${agentInfoConst.address != null}">
			<p>地址：${agentInfoConst.address }</p>
		</c:if>
		<c:if test="${agentInfoConst.webRecord != null}">
			<p>${agentInfoConst.webRecord }</p>
		</c:if>
		
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