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
			$(this).before('<div class="screen"><h4 class="screen_title"><strong>画面'+ ($('.screen').index() + 2) +'</strong><em class="close">╳</em></h4><div class="cont_style"><strong class="fl">样式：</strong><div class="fr"><p><select class="w200"><option value="0" selected="selected">纯图片式</option><option value="1">带按钮图片式</option><option value="2">带视频链接式</option></select></p></div><div class="clear"></div></div><div class="tab_cont"><div class="style_image hide" id="show"><div class="row"><strong class="fl space_10">内容：</strong><div class="fr"><div class="preview"><img class="imghead" src="images/default_bg.jpg" /></div><div class="upload_file"><input type="file" onchange="previewImage(this)" /><span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span></div><em>建议尺寸：宽640像素，高960像素或等比图片</em></div><div class="clear"></div></div></div><div class="style_button hide"><div class="row"><strong class="fl space_10">内容：</strong><div class="fr"><div class="preview"><img class="imghead" src="images/default_bg.jpg" /></div><div class="upload_file"><input type="file" onchange="previewImage(this)" /><span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span></div><em>建议尺寸：宽640像素，高960像素或等比图片</em></div><div class="clear"></div></div><div class="row"><strong class="fl space_10">按钮：</strong><div class="fr"><div class="preview"><img class="imghead btn" src="images/default_btn.png" /></div><div class="upload_file"><input type="file" /><input type="file" onchange="previewImage(this)" /><span><em class="ico_button"><!-- 图标 --></em>上传按钮图片</span></div></div><div class="clear"></div></div><div class="row"><strong class="fl">按钮位置：</strong><div class="fr"><p><select class="w200"><option>靠上</option></select></p></div><div class="clear"></div></div><div class="row"><strong class="fl">图文外链类型：</strong><div class="fr"><p><select class="w200"><option value="0" selected="selected">链接</option><option value="1">电话</option></select></p></div><div class="clear"></div></div><div class="row"><strong class="fl">页面URL：</strong><div class="fr"><p><input class="w320" type="text" /><em>必填，请您填写正确的URL格式。</em></p></div><div class="clear"></div></div></div><div class="style_video hide"><div class="row"><strong class="fl space_10">内容：</strong><div class="fr"><div class="preview"><img class="imghead" src="images/default_bg.jpg" /></div><div class="upload_file"><input type="file" onchange="previewImage(this)" /><span><em class="ico_image"><!-- 图标 --></em>点击上传图片</span></div><em>建议尺寸：宽640像素，高960像素或等比图片</em></div><div class="clear"></div></div><div class="row"><strong class="fl">按钮样式：</strong><div class="fr"><p><select class="w200"><option>绿色特效播放按钮</option></select></p></div><div class="clear"></div></div><div class="row"><strong class="fl">视频链接地址：</strong><div class="fr"><p><input class="w420" type="text" /><em>暂时只支持MP4、ogv、webm格式，视频链接格式为http://您的域名/视频文件名.mp4(或ogv/webm)</em></p></div><div class="clear"></div></div></div></div></div>');
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
	$('#bgmusic_obj').change(function(){
		var music_str = $(this).val();
		var lastIndex = music_str.lastIndexOf('\\');
		if (lastIndex > -1) {
			music_str = music_str.substring(lastIndex + 1 , music_str.length);
			$('#bgmusic_name').text(music_str);
		}
	});
	
});


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


