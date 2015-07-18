<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../constant.jsp"%>

	<div class="norl_title">
		<!-- <div class="fr"><a href="#" class='more_btn'>更多>></a></div> -->
		<h3>经典案例</h3>
	</div>
	<div class="classic_case">
		<div class="classic_caselist">
			<ul>
			  <c:forEach items="${actList }" var="actclassic">
				<li><a href="${url_classic }" class="caseimg"><img
						src="${actclassic.actClassicPicUrl }" /></a></li>
			  </c:forEach>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="turn">
			<a class="turn_prev">&lt;</a> <a class="turn_next">&gt;</a>
		</div>
	</div>
    <script>
    $(function(){
		// 边侧轮播
		$('.turn_next').click(function(){
			go_next('classic_caselist');
		});
		$('.turn_prev').click(function(){
			go_prev('classic_caselist');
		});
		var turning = setInterval(function(){
			$(".turn_next").click();
		},3000);
		$('.classic_case').hover(function(){
			clearInterval(turning);
		},function(){
			turning = setInterval(function(){$(".turn_next").click();},3000);
		});
		
		

		// 浮动菜单
		$(".gotop").click(function(){
			$("body,html").animate({scrollTop:0},300);
		});
		$(window).scroll(function(){
			if($(window).scrollTop() > 400){
				$('.float_menu').attr("style","display:block;");
			}else{
				$('.float_menu').attr("style","display:none;");
			}
		});
	});
    
    
    </script>