/*

	For TinyLi 2014-11-17
	GlobalStyle v1.0

	Micro Scene

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
	//新增场景
	$('.add_screen').click(function(){
		if($(this).prev().find('h4 strong').text().substring($(this).prev().find('h4 strong').text().length-1)*1 > 0){
			$(this).before('<div class="screen">\
								<h4 class="screen_title"><strong>礼盒'+ ($(this).prev().find('h4 strong').text().substring($(this).prev().find('h4 strong').text().length-1)*1 + 1) +'</strong><em class="close">╳</em></h4>\
								<div class="row">\
									<strong class="fl">奖品名称：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_prizename w300" type="text" maxlength="50" placeholder="iPhone 6">\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">显示数量：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_num w80" type="text" placeholder="3">\
											<b>只用于前端展示</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">实际数量：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_num w80" type="text" placeholder="0">\
											<b>发完即止</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">中奖概率：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_num w80 probability" type="text" placeholder="0">\
											<b>0~100，用户获得该奖品的概率，数字越小越慢发完该奖品</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
							</div>');
		}else{
			$(this).text('已达到礼盒数目上限');
		}
	});
	//删除场景
	$(document).on("click",".screen h4 .close",function(){
		$(this).parents('.screen').remove();
		var i = 1;
		$('.screen_title').each(function(){
			$(this).find('strong').text("礼盒"+ i);
			i++;
			$('.add_screen').text('新增更多礼盒');
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
	$(document).on("click",".giftbox .mark .btn_green",function(){
		popupPrompt($(this));
	});
});
function popupPrompt(obj,fucName){
	var str  =  '<div class="prompt_box">\
					<div class="cont">\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="msgs">\
							<p>状态不可恢复，确定兑换？</p>\
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
		if( $(this).attr("class") == "btn_done" ){
			var count = 0;
			if(fucName){
				fucName();
			}
			$(this).parents('.prompt_box').remove();
			obj.attr("class","btn_gray").text('已兑换');
		}else{
			$(this).parents('.prompt_box').remove();
		}
	});
}


//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".giftbox .mark h4.tr .bg_green",function(){
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
								<input id="gift_name" type="text" value="iPhone 6" />\
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
				'num'       : $('.giftbox .mark table tr').eq(1).find('td').eq(0).text()*1 + 1,
				'phone_num' : $('#phone_num').val(),
				'gift_num'  : $('#gift_num').val(),
				'gift_name' : $('#gift_name').val(),
				'user_name' : '缺省人名',
				'win_time'  : '14/10/27 18:22',
				'cash_time' : '14/10/27 18:22'
			};
			var falsemark = '<tr>\
								<td>'+ jsondata.num +'</td>\
								<td>'+ jsondata.gift_name +'</td>\
								<td><span>'+ jsondata.phone_num +'</span></td>\
								<td>'+ jsondata.user_name +'<br />'+ jsondata.phone_num +'</td>\
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
			$('.giftbox .mark table tr').eq(0).after(falsemark);
			$(this).parents('.add_falsemark').remove();
		}else{
			$(this).parents('.add_falsemark').remove();
		}
	});
}
