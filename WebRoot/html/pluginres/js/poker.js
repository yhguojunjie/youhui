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
	//新增拼图
	$('.add_pinimg').click(function(){
		if($('.pinimg').index() < 29){
			var html='';
			html+='<div class="pinimg">\
						<h4 class="pinimg_title"><strong>图片' + ($('.pinimg').index() + 2) + '</strong><em class="close">╳</em></h4>\
						<div class="row">\
							<div class="fr">\
								<div class="preview"><img class="w80 h80" src="../../../pluginres/images/poker/poker1.jpg" /></div>\
								<div class="upload_file">\
									<input type="text" />\
									<span><em class="ico_image"></em>选择图片</span>\
								</div>\
								<b>建议尺寸：100*100（或等比），大小不超过50K。</b>\
							</div>\
							<div class="clear"></div>\
						</div>\
					</div>';
			$(this).before(html);
		}else{
			$(this).text('已达到拼图数目上限');
		}
	});
	//删除拼图
	$(document).on("click",".pinimg h4 .close",function(){
		$(this).parents('.pinimg').remove();
		var i = 1;
		$('.pinimg_title').each(function(){
			$(this).find('strong').text("图片"+ i);
			i++;
			$('.add_pinimg').text('新增更多拼图');
		});
	});
	//新增场景
	$('.add_screen').click(function(){
		if($(this).prev().find('h4 strong').text().substring(0,1)*1 < 6){
			$(this).before('<div class="screen" data-id="'+($(this).prev().find('h4 strong').text().substring(0,1)*1 + 1) +'">\
								<h4 class="screen_title"><strong>'+ ($(this).prev().find('h4 strong').text().substring(0,1)*1 + 1) +'等奖</strong><em class="close">╳</em></h4>\
								<div class="row">\
								<strong class="fl">奖品名称：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_prizename w300" type="text" maxlength="50" value="iPhone 6">\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">显示数量：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_num w80" type="text" value="3">\
										<b>只用于前端展示</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">实际数量：</strong>\
								<div class="fr">\
									<p>\
										<input class="verify_num w80" type="text" value="0">\
										<b>发完即止</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl space_15">奖品图片：</strong>\
								<div class="fr">\
									<div class="preview"><img class="w80 h80" src="../../../pluginres/images/giftbox_ico.png" /></div>\
									<div class="upload_file">\
										<input type="text" />\
										<span><em class="ico_image"></em>选择图片</span>\
									</div>\
									<b>建议尺寸：宽200像素，高200像素或等比图片，大小不超过50K。</b>\
								</div>\
								<div class="clear"></div>\
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

//活动规则
$(document).ready(function(){
	$('.actrule_msgbox textarea'). focus(function(){
		var actrule_msgbox = '';
		if($('.daterangepick').val()){
			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 至 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
		}
		if($('.suc_count').val()){
			actrule_msgbox += '• 每盘几局连胜' + $('.suc_count').val() + '获奖。\n';
		}// else{
		// 	actrule_msgbox += '• 每人每天最多游戏1次。\n';
		// }
		//actrule_msgbox += '• 分享朋友圈或发送给朋友可获得额外游戏1次。\n';
		actrule_msgbox += '(插件演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".poker .mark .btn_green,.poker .mark .btn_gray,.poker .mark .btn_modify",function(){
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
					  url : path+"/pluginadmin/poker/changeOpStatus",
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
					  url : path+"/pluginadmin/poker/updateBestScore",
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
    var prizeType,prizeName,showNum,realNum,picUrl;

    prizeType = obj.attr("data-id");
    screen1['prizeType'] = prizeType;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    showNum = obj.find('input').eq(1).val();
    screen1['showNum'] = showNum;

    realNum = obj.find('input').eq(2).val();
    screen1['realNum'] = realNum;

    picUrl= obj.find('input').eq(3).val();
    screen1['picUrl'] = picUrl;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}

