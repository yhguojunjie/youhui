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
		if(($(this).prev().find('h4 strong').text().substring(0,1)*1) < 6){
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
											<input class="verify_num w80" type="text" value="10"/>\
											<b>仅用于活动界面中展示给用户看</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">实际数量：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_num w80" type="text" value="10"/>\
											<b>奖品发完后，不会再有用户中奖</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">中奖概率：</strong>\
									<div class="fr">\
										<p>\
											<input class="verify_num w80 probability" type="text">\
											<b>0~100，用户获得该奖品的概率，独立设置，与其他奖品不相关 </b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl space_15">奖品图片：</strong>\
									<div class="fr">\
										<div class="preview"><img class="w80 h80 imghead" src="'+adminImgPath+'/giftbox_ico.png" /></div>\
										<div class="upload_file">\
											<input type="text" class="fileuploadclass"/>\
											<span><em class="ico_image"></em>选择图片</span>\
										</div>\
										<b>建议尺寸：80*80（或等比），PNG格式，大小不超过30K。</b>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">是否需到店兑奖：</strong>\
									<div class="fr">\
										<p>\
											<label><input class="act_ticket" type="radio" name="'+ ($(this).prev().find('h4 strong').text().substring(0,1)*1 + 1) +'" value="0"/>是</label>&nbsp;&nbsp;\
											<label><input type="radio" checked="checked" name="'+ ($(this).prev().find('h4 strong').text().substring(0,1)*1 + 1) +'" value="1"/>否</label>\
											<b>获奖用户需到店，出示中奖记录，更改兑奖状态，才完成兑奖</b>\
										</p>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<input type="hidden" value="" class="screen_json"/>\
							</div>');
		}else{
			$(this).text('已达到奖品数目上限');
		}
	});
	//删除场景
	$(document).on("click",".screen h4 .close",function(){
		$(this).parents('.screen').remove();
		var i = 1;
		$('.screen_title').each(function(){
			$(this).find('strong').text(i+ "等奖");
			i++;
			$('.add_screen').text('新增更多等级');
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
	$(document).on("click",".giftbox .mark .btn_green,.giftbox .mark .btn_gray,.giftbox .mark .btn_modify",function(){
		var notice = '是否更改为“<span class="state">已兑换</span>”状态';
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
		if( $(this).attr("class") == "btn_done" ){
			popbox = $(this);
			$.ajax({
				  type: "POST",
				  url : path+"/pluginadmin/giftbox/changeOpStatus",
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


//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".giftbox .mark h4.tr .bg_green",function(){
		$.ajax({
			  type: "POST",
			  url : path+"/pluginadmin/giftbox/getPrizeJson",
			  data:{activityId:$('#activityId').val()},
			  dataType: "json",
			  success: function(data){
				popupFalsemark(data);
			  }
		}); 
	});
});

//活动规则
$(document).ready(function(){
	$('.actrule_msgbox textarea'). focus(function(){
		var actrule_msgbox = '';
		if($('.daterangepick').val()){
			actrule_msgbox += '• 活动时间：' + $('.daterangepick').val().replace(/\ -\ /," 至 ").replace(/-/g,"/").replace(/:00/g,"") + '。\n';
		}
		if($('.giftbox_count').val()){
			actrule_msgbox += '• 每人每天最多可领取礼盒' + $('.giftbox_count').val() + '个。\n';
		}else{
			actrule_msgbox += '• 每人每天最多可领取礼盒1个。\n';
		}
		if($('.friend_count').val()){
			actrule_msgbox += '• 每个礼盒需要邀请' + $('.friend_count').val() + '个朋友帮忙拆开，才可获得里面的礼品。\n';
		}else{
			actrule_msgbox += '• 每个礼盒需要邀请3个朋友帮忙拆开，才可获得里面的礼品。\n';
		}
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});


function popupFalsemark(dataarr){
	var str  =  '<div class="add_falsemark">\
					<form>\
						<h3><strong>提示</strong><span class="close">╳</span></h3>\
						<div class="space_10"></div>\
						<div class="row">\
							<strong>手机号：</strong>\
							<div class="fr">\
								<input id="phone_num" type="text" maxlength="11"/>\
								<br /><b>（页面上会将中间四位数字自动转为*）</b>\
							</div>\
							<div class="clear"></div>\
						</div>\
						<div class="row">\
							<strong>奖项：</strong>\
							<div class="fr">\
								<select id="gift_num">';
	for (var i in dataarr){
		str += '<option>'+ dataarr[i].prizeType +'等奖</option>';
	}
	str +=  '</select></div>\
							<div class="clear"></div>\
						</div>\
						<div class="row">\
							<strong>奖品名称：</strong>\
							<div class="fr">\
								<input id="gift_name" disabled="disabled" type="text" value="'+ dataarr[0].prizeName +'" />\
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

	$(document).on("change",'#gift_num',function(){
		var index = $(this).val().substring(0,1) - 1;
		$('#gift_name').val(dataarr[index].prizeName);
	});

	$('.add_falsemark .close,.add_falsemark .btn_cancel,.add_falsemark .btn_done').click(function(){
		if( $(this).attr("class") == "btn_done" ){
			var popbox = $(this);
			$(this).parents('.prompt_box').remove();
			if($('#phone_num').val().length == 0){
				alert("请输入手机号");
				return;
			}
			var jsondata = {
				'activityId': $('#activityId').val(),
				'tel' : $('#phone_num').val(),
				'prizeType'  : $('#gift_num').val().substring(0,1),
				'prizeName' : $('#gift_name').val(),
			};
		$.ajax({
			  type: "POST",
			  url : path+"/pluginadmin/giftbox/addRecord",
			  data:jsondata,
			  dataType: "json",
			  success: function(data){
			  	if(data != 0){
			  		popbox.parents('.add_falsemark').remove();
			  		window.location.reload();
			  	}else{
			  		alert("增加失败");
			  	}
			  }
		}); 
		}else{
			$(this).parents('.add_falsemark').remove();
		}
	});
}


function addscreen_json(obj){
    var screen1 = {} ;
    var prizeType,prizeName,showNum,realNum,probability,picUrl,exchangeState;

    prizeType = obj.attr("data-id");
    screen1['prizeType'] = prizeType;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    showNum = obj.find('input').eq(1).val();
    screen1['showNum'] = showNum;

    realNum = obj.find('input').eq(2).val();
    screen1['realNum'] = realNum;

    probability= obj.find('input').eq(3).val();
    screen1['probability'] = probability;

    picUrl= obj.find('input').eq(4).val();
    screen1['picUrl'] = picUrl;

    if(obj.find('input[type="radio"]').eq(0).prop("checked")){
    	exchangeState = obj.find('input[type="radio"]').eq(0).val();
    }else{
    	exchangeState = obj.find('input[type="radio"]').eq(1).val();
    }
    screen1['exchangeState'] = exchangeState;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}