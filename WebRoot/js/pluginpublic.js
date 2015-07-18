/*

	For TinyLi 2014-11-29
	Plug-in Public v1.0

*/


/* -------------------------------- 日历插件  开始 ------------------------------- */

$(document).ready(function() {
	//daterangepicker
	$('.daterangepick').daterangepicker({
		timePicker: true,
		timePickerSeconds: true,
		timePickerIncrement: 1,
		format: "YYYY-MM-DD HH:mm:ss",
	});

	//singledatepicker
	$('.singledatepick').daterangepicker({
		timePicker: true,
		timePickerSeconds: true,
		timePickerIncrement: 1,
		singleDatePicker: true,
		format: "YYYY-MM-DD HH:mm:ss",
	});
});


/* -------------------------------- 日历插件  结束 ------------------------------- */



/* -------------------------------- 浏览器信息  开始 ------------------------------- */

function getBrowserInfo(){
	var agent = navigator.userAgent.toLowerCase();
	//IE
	if(agent.indexOf("msie") > 0){
		return "ie";
	}
	//firefox
	if(agent.indexOf("firefox") > 0){
		return "firefox";
	}
	//Chrome
	if(agent.indexOf("chrome") > 0){
		return "chrome";
	}
	//Safari
	if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0){
		return "safari";
	}
}

$(document).ready(function(){
	$('input[name="activityTime"]').attr("readonly","readonly");

	var browser = getBrowserInfo();
	if(!(browser == "firefox" || browser == "chrome")){
		$('.w840 .cont').attr("style","position:relative;");
		$('.w840 .cont').append('<div class="browserinfo"><strong>请使用Chrome（谷歌）浏览器或者Firefox（火狐）浏览器打开页面。</strong><div class="browserbg"></div></div>');
	}
});

/* -------------------------------- 浏览器信息  结束 ------------------------------- */


/* -------------------------------- 奖品数不能减  开始 ------------------------------- */

function unreduce(val,obj){//val：原奖品数，obj：this
	if($(obj).val() < val){
		alert("新奖品数不能少于原奖品数");
		$(obj).val(val);
	}
}

/* -------------------------------- 奖品数不能减  结束 ------------------------------- */


function checkEnter(e)
{
	var et=e||window.event;
	var keycode=et.charCode||et.keyCode;
	if(keycode==13){
		if(window.event)
			window.event.returnValue = false;
		else
			e.preventDefault();//for firefox
	}
}
$(document).ready(function(){
	$('textarea[name="shareDescription"]').keydown(function(e){ 
		checkEnter(e); 
	}); 
});


















