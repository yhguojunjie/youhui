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
		if($(this).prev().find('h4 strong').text().substring(2,3)*1 < 9){
			$(this).before('<div class="screen" data-id="'+($(this).prev().find('h4 strong').text().substring(2,3)*1 + 1) +'">\
								<h4 class="screen_title"><strong>奖项'+ ($(this).prev().find('h4 strong').text().substring(2,3)*1 + 1) +'</strong><em class="close">╳</em></h4>\
								<div class="row">\
								<strong class="fl">奖品名称：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_prizename w300" type="text" maxlength="50" value="新增奖品">\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">实际数量：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_num w80" type="text" value="0">\
										<b>奖品发完后，不会再有用户得奖</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">关数：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_num w80" type="text" value="0" />\
										<b>奖品出现的关数</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl space_15">奖品图片：</strong>\
								<div class="fr">\
									<div class="preview"><img class="w80 h80" src="'+commonImgPath+'/giftbox.png" /></div>\
									<div class="upload_file">\
										<input type="text" class="fileuploadclass"/>\
										<span><em class="ico_image"></em>选择图片</span>\
									</div>\
									<b>建议尺寸：80*80（或等比），PNG格式，大小不超过30K。</b>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
									<strong class="fl w220">是否需到店兑奖：</strong>\
									<div class="fr w560">\
										<p>\
											<label><input class="act_ticket" type="radio" name="'+($(this).prev().find('h4 strong').text().substring(2,3)*1 + 1) +'" value="0"/>是</label>&nbsp;&nbsp;\
											<label><input type="radio" checked="checked" name="'+($(this).prev().find('h4 strong').text().substring(2,3)*1 + 1) +'" value="1"/>否</label>\
											<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<input type="hidden" value="" class="screen_json"/>\
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
			$(this).find('strong').text("奖项"+i);
			i++;
			$('.add_screen').text('新增更多奖项');
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

//活动规则
$(document).ready(function(){
	$('.actrule_msgbox textarea'). focus(function(){
		var actrule_msgbox = '';
		if($('.daterangepick').val()){
			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 至 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
		}
		if($('.game_count').val()){
			actrule_msgbox += '• 每人每天最多游戏' + $('.game_count').val() + '次。\n';
		}else{
			actrule_msgbox += '• 每人每天最多游戏3次。\n';
		}
		actrule_msgbox += '• 碰到奖品算获得，同一种奖品只能获得一次，数量有限，先到先得。\n';
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".bird .mark .btn_green,.bird .mark .btn_gray,.bird .mark .btn_modify",function(){
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
		var type = 1;
		if($(this).attr("class") == "btn_gray"){
			notice = '是否更改为“<span class="state">未兑换</span>”状态';
		}
		if($(this).attr("class") == "btn_modify"){
			notice = '最高分：<input type="text" />';
			type = 2;
		}
		popupPrompt($(this),notice,type);
	});

});
function popupPrompt(obj,notice,type){
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
			if(type == 1){
				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/bird/changeOpStatus",
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
				$.ajax({
					  type: "POST",
					  url : path+"/pluginadmin/bird/updateBestScore",
					  data:{id:obj.parents('tr').find('input').val(), score:popbox.parents('.prompt_box').find('input').val()},
					  dataType: "json",
					  success: function(data){
			 				if(data.status == "0"){
				 				obj.parents('tr').find('td').eq(1).find('p').text(popbox.parents('.prompt_box').find('input').val());
				 				popbox.parents('.prompt_box').remove();
			 				}else{
			 					alert("操作失败");
			 				}
					  }
			    }); 
			}
			
		}else{
			$(this).parents('.prompt_box').remove();
		}
	});

}

function addscreen_json(obj){
    var screen1 = {} ;
    var prizeType,prizeName,realNum,level,picUrl,exchangeState;

    prizeType = obj.attr("data-id");
    screen1['prizeType'] = prizeType;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    realNum = obj.find('input').eq(1).val();
    screen1['realNum'] = realNum;

    level = obj.find('input').eq(2).val();
    screen1['level'] = level;

    picUrl= obj.find('input').eq(3).val();
    screen1['picUrl'] = picUrl;

    if(obj.find('input[type="radio"]').eq(0).prop("checked")){
    	exchangeState = obj.find('input[type="radio"]').eq(0).val();
    }else{
    	exchangeState = obj.find('input[type="radio"]').eq(1).val();
    }
    screen1['exchangeState'] = exchangeState;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}

