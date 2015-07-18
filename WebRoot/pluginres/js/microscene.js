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
		if(true){
			var html='';
			html+='<div class="screen">\
					<h4 class="screen_title"><strong>画面'
					+ ($('.screen').index() + 2) 
					+'</strong><em class="close">╳</em></h4>\
					<div class="cont_style">\
						<strong class="fl">样式：</strong>\
						<div class="fr">\
							<p>\
								<select class="w200">\
									<option value="0" selected="selected">纯图片式</option>\
									<option value="1">带按钮图片式</option>\
									<option value="2">带视频链接式</option>\
								</select>\
							</p>\
						</div>\
						<div class="clear"></div>\
					</div>\
					<div class="tab_cont">\
						<div class="style_image hide" id="show">\
							<div class="row">\
								<strong class="fl space_10">内容：</strong>\
								<div class="fr">\
									<div class="preview"><img class="imghead" src="'+adminImgPath+'/magazine/default_bg.jpg" /></div>\
									<div class="upload_file">\
										<input type="text" class="fileuploadclass" />\
										<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>\
									</div>\
									<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>\
								</div>\
								<div class="clear"></div>\
							</div>\
						</div>\
						<div class="style_button hide">\
							<div class="row">\
								<strong class="fl space_10">内容：</strong>\
								<div class="fr">\
									<div class="preview"><img class="imghead" src="'+adminImgPath+'/magazine/default_bg.jpg" /></div>\
									<div class="upload_file">\
										<input type="text" class="fileuploadclass" />\
										<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>\
									</div>\
									<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl space_10">按钮：</strong>\
								<div class="fr">\
									<div class="preview"><img class="w150 h50 btn" src="'+adminImgPath+'/magazine/default_btn.png" /></div>\
									<div class="upload_file">\
										<input type="text" class="fileuploadclass" />\
										<span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span>\
									</div>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">按钮位置：</strong>\
								<div class="fr">\
									<p>\
										<select class="w200">\
											<option value="top">靠上</option>\
											<option value="middle">中间</option>\
											<option value="bottom" selected="selected">靠下</option>\
										</select>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">图文外链类型：</strong>\
								<div class="fr">\
									<p>\
										<select class="w200">\
											<option value="0" selected="selected">链接</option>\
											<option value="1">电话</option>\
										</select>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">页面URL：</strong>\
								<div class="fr">\
									<p>\
										<input class="w320" type="text" value=""/>\
										<b>必填，请您填写正确的URL格式。</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
						</div>\
						<div class="style_video hide">\
							<div class="row">\
								<strong class="fl space_10">内容：</strong>\
								<div class="fr">\
									<div class="preview"><img class="imghead" src="'+adminImgPath+'/magazine/default_bg.jpg" /></div>\
									<div class="upload_file">\
										<input type="text" class="fileuploadclass" />\
										<span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span>\
									</div>\
									<b>建议尺寸：640*1120（或等比），大小不超过80K。</b>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">按钮样式：</strong>\
								<div class="fr">\
									<p>\
										<select class="w200">\
											<option value="ico_video">绿色特效播放按钮</option>\
											<option value="ico_video1">圆形播放按钮</option>\
											<option value="ico_video2">长方形播放按钮</option>\
										</select>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
							<div class="row">\
								<strong class="fl">视频链接地址：</strong>\
								<div class="fr">\
									<p>\
										<input class="w420" type="text" />\
										<b>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</b>\
									</p>\
								</div>\
								<div class="clear"></div>\
							</div>\
						</div>\
					</div>\
					<input type="hidden" value="" class="screen_json"/>\
				</div>';
			$(this).before(html);
		}else{
			$(this).text('已达到画面数目上限');
		}
	});
	//删除场景
	$(document).on("click",".screen h4 .close",function(){
		$(this).parents('.screen').remove();
		var i = 1;
		$('.screen_title').each(function(){
			$(this).find('strong').text("画面"+ i);
			i++;
			$('.add_screen').text('新增更多画面');
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
	// $('#bgmusic_obj').change(function(){
	// 	var music_str = $(this).val();
	// 	var lastIndex = music_str.lastIndexOf('\\');
	// 	if (lastIndex > -1) {
	// 		music_str = music_str.substring(lastIndex + 1 , music_str.length);
	// 		$('#bgmusic_name').text(music_str);
	// 	}
	// });
	$.fn.addscreen_json = function(){
        var screen1 = {} ;
        var pic_type,pic_cover,pic_base,pic_btn,btn_type,btn_pos,btn_url,video_btn_type,video_url;

        pic_type = $(this).find('.cont_style select').val();
        $(this).find('.cont_style select').change(function(){
            pic_type = $(this).val();
        });

        screen1['pic_type'] = pic_type;
        if(pic_type == 3){
            pic_cover = $(this).find('#show .row').eq(0).find('.fileuploadclass').val();
            pic_base = $(this).find('#show .row').eq(1).find('.fileuploadclass').val();
            screen1['pic_cover'] = pic_cover;
            screen1['pic_base'] = pic_base;
        }else if(pic_type == 0){
            pic_base = $(this).find('#show .row .fileuploadclass').val();
            screen1['pic_base'] = pic_base;
        }else if(pic_type == 1){
            pic_base = $(this).find('#show .row').eq(0).find('.fileuploadclass').val();
            pic_btn = $(this).find('#show .row').eq(1).find('.fileuploadclass').val();
            btn_pos = $(this).find('#show .row').eq(2).find('select').val();
            $(this).find('#show .row').eq(2).find('select').change(function(){
                btn_pos = $(this).val();
            });
            btn_type = $(this).find('#show .row').eq(3).find('select').val();
            $(this).find('#show .row').eq(3).find('select').change(function(){
                btn_type = $(this).val();
            });
            btn_url = $(this).find('#show .row').eq(4).find('input').val();
            screen1['pic_base'] = pic_base;
            screen1['pic_btn'] = pic_btn;
            screen1['btn_type'] = btn_type;
            screen1['btn_pos'] = btn_pos;
            screen1['btn_url'] = btn_url;

        }else{
            pic_base = $(this).find('#show .row').eq(0).find('.fileuploadclass').val();
            video_btn_type = $(this).find('#show .row').eq(1).find('select').val();
            $(this).find('#show .row').eq(1).find('select').change(function(){
                video_btn_type = $(this).val();
            });
            video_url = $(this).find('#show .row').eq(2).find('input').val();
            screen1['pic_base'] = pic_base;
            screen1['video_btn_type'] = video_btn_type;
            screen1['video_url'] = video_url;
        }

            $(this).find('.screen_json').val(JSON.stringify(screen1));
    }
});

//图片上传预览
function getByClass(oParent,nClass){
	var eLe = oParent.getElementsByTagName('*');
	var aRrent  = [];
	for(var i=0; i<eLe.length; i++){
		if(eLe[i].className == nClass){
			aRrent.push(eLe[i]);
		}
	}
	return aRrent;
}
