/*

	For TinyLi 2014-11-17
	GlobalStyle v1.0

	Mature

*/

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
	//删除场景
	$(document).on("click",".screen h4 .close",function(){
		$(this).parents('.screen').remove();
		var i = 1;
		$('.screen_title').each(function(){
			$(this).find('strong').text(i +"等奖");
			i++;
			$('.add_screen').text('新增更多奖品等级');
		});
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
	
});

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".mature .mark .btn_green,.mature .mark .btn_gray,.mature .mark .btn_modify",function(){
		/*var notice = '是否更改为<select><option>已兑换</option><option>未兑换</option></select>状态';
		if($(this).attr("class") == "btn_gray"){
			notice = '是否更改为<select><option>未兑换</option><option>已兑换</option></select>状态';
		}*/
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
		if($(this).attr("class") == "btn_gray"){
			notice = '是否更改为“<span class="state">未兑换</span>”状态';
		}
		if($(this).attr("class") == "btn_modify"){
			notice = '最高分：<input type="text" />';
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
				  url : path+"/pluginadmin/mature/changeOpStatus",
				  data:{id:obj.parents('tr').find('input').val()},
				  dataType: "json",
				  success: function(data){
		 				if(data.status == "0"){
		 					if(data.exchangeTime.length == 0){
		 						popbox.parents('.prompt_box').remove();
			 					obj.parents('tr').find('td').eq(5).find('p').text(data.exchangeTime);
								obj.attr("class","btn_green").text('未兑换');
		 					}else{
		 						popbox.parents('.prompt_box').remove();
			 					obj.parents('tr').find('td').eq(5).find('p').text(data.exchangeTime);
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


$(document).ready(function(){
	$('.actrule_msgbox textarea'). focus(function(){
		var actrule_msgbox = '';
		if($('.daterangepick').val()){
			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 到 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
		}
		if($('.act_count').val()){
			actrule_msgbox += '• 每次兑换至少需要' + $('.act_count').val() + '个红包。\n';
		}else{
			actrule_msgbox += '• 每次兑换至少需要10个红包。\n';
		}
		actrule_msgbox += '• 每成功浇一次水，树上就会结出一个红包。\n';
		actrule_msgbox += '• 每位好友每天可帮忙浇一次水，偷摘红包一个。\n';
		actrule_msgbox += '• 红包越多，新年越旺，奖励越多！\n';
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n';
		$(this).text(actrule_msgbox);
	});

});


//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".mature .mark h4.tr .bg_green",function(){
		popupFalsemark();
	});
});
function popupFalsemark(fucName){
	var str  =  '<div class="add_falsemark">\
					<form>\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="space_10"></div>\
						<div class="row">\
							<strong>手机号：</strong>\
							<div class="fr">\
								<input id="phone_num" type="text" />\
								<br /><b>（页面上会将中间四位数字自动转为*）</b>\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="row">\
							<strong>奖项：</strong>\
							<div class="fr">\
								<select id="gift_num"><option>礼盒1</option></select>\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="row">\
							<strong>奖品名称：</strong>\
							<div class="fr">\
								<input id="gift_name" type="text" disabled="disabled" value="iPhone 6" />\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="space_10"></div>\
						<div class="btns">\
							<a class="btn_cancel">取 消</a>\
							<a class="btn_done">确 定</a>\
						</div>\
					</form>\
					<div class="bg"></div>\
				</div>';
	$('body').append(str);
	$('.add_falsemark .close,.add_falsemark .btn_cancel,.add_falsemark .btn_done').click(function(){
		if( $(this).attr("class") == "btn_done" ){
			if(fucName){
				fucName();
			}
			$(this).parents('.prompt_box').remove();
			var jsondata = {
				'num'       : $('.mature .mark table tr').eq(1).find('td').eq(0).text()*1 + 1,
				'phone_num' : $('#phone_num').val(),
				'gift_num'  : $('#gift_num').val(),
				'gift_name' : $('#gift_name').val(),
				'user_name' : '',
				'win_time'  : '14/10/27 18:22',
				'cash_time' : '14/10/27 18:22'
			};
			var falsemark = '<tr>\
								<td>'+ jsondata.num +'</td>\
								<td>'+ jsondata.gift_name +'</td>\
								<td><span>'+ jsondata.phone_num +'</span></td>\
								<td><strong>'+ jsondata.user_name +'<br />'+ jsondata.phone_num +'</strong></td>\
								<td>\
									<p>'+ jsondata.win_time +'</p>\
								</td>\
								<td>\
									<p>'+ jsondata.cash_time +'</p>\
								</td>\
								<td>\
									<p><a class="btn_gray">已兑换</a></p>\
								</td>\
							</tr>';
			$('.mature .mark table tr').eq(0).after(falsemark);
			$(this).parents('.add_falsemark').remove();
		}else{
			$(this).parents('.add_falsemark').remove();
		}
	});
}