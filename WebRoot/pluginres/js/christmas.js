/*

	For TinyLi 2014-11-24
	GlobalStyle v1.0

	Christmas

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
			var html = " ";
			html += '<div class="screen">\
						<h4 class="screen_title"><strong>礼物'+ ($(this).prev().find('h4 strong').text().substring($(this).prev().find('h4 strong').text().length-1)*1 + 1) +'</strong><em class="close">╳</em></h4>\
						<div class="tab_cont">\
							<div class="style_image">\
								<div class="row">\
									<strong class="fl space_10">礼物图片：</strong>\
									<div class="fr">\
										<div class="preview giftview"><img src="'+ commonImgPath +'/gift.png" /></div>\
										<div class="upload_file">\
											<input type="text" class="fileuploadclass" />\
											<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>\
										</div>\
										<b>建议尺寸：150*125（或等比），PNG格式，大小不超过50K。</b>\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">礼物标题：</strong>\
									<div class="fr"><input class="w300 verify_gifttitle" type="text" placeholder="标题" />\
									</div>\
									<div class="clear"></div>\
								</div>\
								<div class="row">\
									<strong class="fl">描述文案：</strong>\
									<div class="fr">\
										<textarea class="verify_intro w300" placeholder="简介"></textarea>\
									</div>\
									<div class="clear"></div>\
								</div>\
							</div>\
						</div>\
						<input type="hidden" value="" class="screen_json"/>\
					</div>';
			$(this).before(html);
		}else{
			$(this).text('已达到礼物数目上限');
		}
	});
	//删除场景
	$(document).on("click",".screen h4 .close",function(){
		$(this).parents('.screen').remove();
		var i = 1;
		$('.screen_title').each(function(){
			$(this).find('strong').text("礼物"+ i);
			i++;
			$('.add_screen').text('新增更多礼物');
		});
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

//兑换奖品
$(document).ready(function(){
	$(document).on("click",".christmas .mark .btn_green,.christmas .mark .btn_gray,.christmas .mark .btn_modify",function(){
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
			  url : path+"/pluginadmin/givegift/changeOpStatus",
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


//图片上传预览
function previewImages(file){
	var MAXWIDTH = 40;
	var MAXHEIGHT = 60;
	var div = getByClass(file.parentNode.parentNode,'preview')[0];
	if (file.files && file.files[0]){
		div.innerHTML = '<img class="imghead">';
		var img = getByClass(file.parentNode.parentNode,'imghead')[0];
		img.onload = function(){
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = rect.left+'px';
			img.style.marginTop = rect.top+'px';
		}
		var reader = new FileReader();
		reader.onload = function(evt){
			img.src = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	}else{
		var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img class="imghead">';
		var img = getByClass(file.parentNode.parentNode,'imghead')[0];
		var rect = clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
		status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		div.innerHTML = "<div class='divhead' style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
	}
}
function clacImgZoomParam(maxWidth,maxHeight,width,height){
	var param = {top:0, left:0, width:width, height:height};
	if( width>maxWidth || height>maxHeight){
		rateWidth = width/maxWidth;
		rateHeight = height/maxHeight;
		if( rateWidth > rateHeight){
			param.width = maxWidth;
			param.height = Math.round(height/rateWidth);
		}else{
			param.width = Math.round(width/rateHeight);
			param.height = maxHeight;
		}
	}
	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}

function addscreen_json(obj){
    var screen1 = {} ;
    var picUrl,desc,title;

    picUrl = obj.find('.fileuploadclass').val();
    title = obj.find('input').eq(1).val();
    desc = obj.find('textarea').val();

    screen1['picUrl'] = picUrl;
    screen1['title'] = title;
    screen1['desc'] = desc;

    obj.find('.screen_json').val(JSON.stringify(screen1));
}