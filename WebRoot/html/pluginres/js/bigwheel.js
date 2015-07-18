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
		if($(this).prev().find('h4 strong').text().substring(0,1)*1 > 1){
			$(this).before('<div class="screen">\
								<h4 class="screen_title"><strong>'+ ($(this).prev().find('h4 strong').text().substring(0,1)*1 + 1) +'等奖</strong><em class="close">╳</em></h4>\
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
							<div class="row">\
								<strong class="fl space_15">奖品图片：</strong>\
								<div class="fr">\
									<div class="preview"><img class="w80 h80" src="images/giftbox.png" /></div>\
									<div class="upload_file">\
										<input type="text" />\
										<span><em class="ico_image"></em>选择图片</span>\
									</div>\
									<b>建议尺寸：宽200像素，高200像素或等比图片，大小不超过50K。</b>\
								</div>\
								<div class="clear"></div>\
							</div>');
		}else{
			$(this).text('已达到奖品等级设置上限');
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
	$(document).on("click",".bigwheel .mark .btn_green,.bigwheel .mark .btn_gray,.bigwheel .mark .btn_modify",function(){
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
		/*if($(this).attr("class") == "btn_done" ){
			if($('.prompt_box select').val() == "未兑换"){
				obj.attr("class","btn_green").text('未兑换');
			}else{
				obj.attr("class","btn_gray").text('已兑换');
			}
			$(this).parents('.prompt_box').remove();
		}else{
			$(this).parents('.prompt_box').remove();
		}*/
		if($(this).attr("class") == "btn_done"){
			if($('.prompt_box .state').text() == "未兑换"){
				obj.attr("class","btn_green").text('未兑换');
			}else if($('.prompt_box .state').text() == "已兑换"){
				obj.attr("class","btn_gray").text('已兑换');
			}else if($('.prompt_box input').val()){
				obj.parents('tr').find('td').eq(1).text($('.prompt_box input').val());
			}
			$(this).parents('.prompt_box').remove();
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
			actrule_msgbox += '• 每人每天最多可抽奖' + $('.act_count').val() + '次。\n';
		}else{
			actrule_msgbox += '• 每人每天最多可抽奖1次。\n';
		}
		actrule_msgbox += '• 分享到朋友圈可额外获得1次抽奖机会。\n'
		if($('.cant_join').prop("checked") == true){
			actrule_msgbox += '• 如果已中奖，则不可继续参加。';
		}
		$(this).text(actrule_msgbox);
	});

});


//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".bigwheel .mark h4.tr .bg_green",function(){
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
				'num'       : $('.bigwheel .mark table tr').eq(1).find('td').eq(0).text()*1 + 1,
				'phone_num' : $('#phone_num').val(),
				'gift_num'  : $('#gift_num').val(),
				'gift_name' : $('#gift_name').val(),
				'user_name' : '金唯，福建省厦门市湖里区万达广场，',
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
			$('.bigwheel .mark table tr').eq(0).after(falsemark);
			$(this).parents('.add_falsemark').remove();
		}else{
			$(this).parents('.add_falsemark').remove();
		}
	});
}