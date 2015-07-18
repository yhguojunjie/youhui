//配置内容切换
$(document).ready(function(){
	//展开收起
	$('.fold').click(function(){
		if($(this).text() == "收起"){
			$(this).text("展开");
			$(this).parent().next().attr("style","display:none;");
		}else{
			$(this).text("收起");
			$(this).parent().next().attr("style","display:block;");
		}
	});

	//插入广告
	$('.tab_ad label').click(function(){
		$(this).parent().find('label').attr("class"," ");
		$(this).attr("class","active");
		if($('.tab_ad .active').index() == 1){
			$(this).parent().parent().find('.ad_upcont').attr("style","display:none;");
		}else{
			$(this).parent().parent().find('.ad_upcont').attr("style","display:block;");
		}
	});

	//兑换奖品
	$(document).on("click",".bottle .mark .btn_green,.bottle .mark .btn_gray",function(){
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
		var type = 1;
		if($(this).attr("class") == "btn_gray"){
			notice = '是否更改为“<span class="state">未兑换</span>”状态';
		}
		popupPrompt($(this),notice);
	});

});
function popupPrompt(obj,notice){
	var str  =  '<div class="prompt_box">\
					<div class="cont">\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="msgs">\
							<p>'+ notice +'</p>\
						</div>\
						<div class="btns">\
							<a class="btn_cancel">取 消</a>\
							<a class="btn_done">确 定</a>\
						</div>\
					</div>\
					<div class="bg"></div>\
				</div>';
	$('body').append(str);
	$('.prompt_box .close,.prompt_box .btn_cancel,.prompt_box .btn_done').click(function(){
		if($(this).attr("class") == "btn_done"){
			popbox = $(this);
			$.ajax({
				  type: "POST",
				  url : path+"/pluginadmin/bottle/changeOpStatus",
				  data:{id:obj.parents('tr').find('input').val()},
				  dataType: "json",
				  success: function(data){
		 				if(data.status == "0"){
		 					if(data.exchangeTime.length == 0){
		 						popbox.parents('.prompt_box').remove();
			 					obj.parents('tr').find('td').eq(4).find('p').text(data.exchangeTime);
								obj.attr("class","btn_green").text('未兑换');
		 					}else{
		 						popbox.parents('.prompt_box').remove();
			 					obj.parents('tr').find('td').eq(4).find('p').text(data.exchangeTime);
								obj.attr("class","btn_gray").text('已兑换');
		 					}
		 				}else{
		 					alert("操作失败");
		 				}
				  }
		    }); 
		}else{
			$(this).parents('.prompt_box').remove();
		}
	});

}


