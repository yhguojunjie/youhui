/*

	For TinyLi 2014-11-24
	GlobalStyle v1.0

	zanrenqi

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
	
	//场景内容切换
	$(document).on("change",".cont_style select",function(){
		$(this).parents('.screen').find('.tab_cont .hide').attr("id"," ");
		$(this).parents('.screen').find('.tab_cont .hide').eq($(this).val()).attr("id","show");
	});
	//图文外链类型内容切换
	$(document).on("change",".tab_cont .style_button select",function(){
		if($(this).val() == 0){
			$(this).parents('.row').next().find('strong').text("页面URL：");
			$(this).parents('.row').next().find('em').text("必填，请您填写正确的URL格式。");
		}else{
			$(this).parents('.row').next().find('strong').text("电话号码：");
			$(this).parents('.row').next().find('em').text("必填，请您填写正确电话号码格式。");
		}
	});
	//音乐开关
	$('.tab_music label').click(function(){
		$(this).parent().find('label').attr("class"," ");
		$(this).attr("class","active");
		if($('.tab_music .active').index() == 1){
			$(this).parent().parent().find('.music_upcont').attr("style","display:none;");
		}else{
			$(this).parent().parent().find('.music_upcont').attr("style","display:block;");
		}
	});
	//音乐上传预览
	$('#bgmusic_obj').change(function(){
		var music_str = $(this).val();
		var lastIndex = music_str.lastIndexOf('\\');
		if (lastIndex > -1) {
			music_str = music_str.substring(lastIndex + 1 , music_str.length);
			$('#bgmusic_name').text(music_str);
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
		actrule_msgbox += '• 每个好友只能帮忙一次。\n';
		actrule_msgbox += '• 奖品数量有限，先到先得。\n';
		actrule_msgbox += '(模版演示，纯属体验，不予兑奖！)\n'
		$(this).text(actrule_msgbox);
	});

});

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".zanrenqi .mark .btn_green,.zanrenqi .mark .btn_gray,.zanrenqi .mark .btn_modify",function(){
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
		var popbox = $(this);
		$.ajax({
			  type: "POST",
			  url : path+"/pluginadmin/zanrenqi/changeOpStatus",
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

//新增虚假记录
$(document).ready(function(){
	$(document).on("click",".zanrenqi .mark h4 a.bg_green",function(){
		$.ajax({
		  type: "POST",
		  url : path+"/pluginadmin/zanrenqi/getPrizeJson",
		  data:{activityId:$('#activityId').val()},
		  dataType: "json",
		  success: function(data){
			popupFalsemark(data);
		  }
		}); 
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
			  url : path+"/pluginadmin/zanrenqi/addRecord",
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
    var prizeType,prizeName,showNum,realNum,getNum,desc,picUrl,exchangeState;

    prizeType = obj.attr("data-id");
    screen1['prizeType'] = prizeType;

    prizeName = obj.find('input').eq(0).val();
    screen1['prizeName'] = prizeName;

    showNum = obj.find('input').eq(1).val();
    screen1['showNum'] = showNum;

    realNum = obj.find('input').eq(2).val();
    screen1['realNum'] = realNum;

    getNum = obj.find('input').eq(3).val();
    screen1['getNum'] = getNum;

    desc = obj.find('textarea').eq(0).val();
    screen1['desc'] = desc;

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