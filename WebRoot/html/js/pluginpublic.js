/*

	For TinyLi 2014-11-29
	Plug-in Public v1.0

*/


/* -------------------------------- 日历插件  开始 ------------------------------- */

// $(document).ready(function(){
// 	var $daterangepick = $(".daterangepick");
// 	if ($daterangepick.length > 0) {
// 		$daterangepick.each(function () {
// 			var $this = $(this),minid=$this.data("mindateid"),mindate=$this.data("mindate"),min=false,max=false,
// 			maxid=$this.data("maxdateid"),maxdate=$this.data("maxdate");
// 			var timef=true,formats="YYYY-MM-DD HH:mm:00",day=$this.data("day");
// 			if(day){
// 				timef=false;
// 				formats="YYYY-MM-DD";
// 			}
// 			if(mindate){
// 				if(mindate=="now"){
// 					min=new Date();
// 				}else{
// 					min=mindate;
// 				}
// 			}
// 			if(maxdate){
// 				if(maxdate=="now"){
// 					max=new Date();
// 				}else{
// 					max=maxdate;
// 				}
// 			}
// 			$this.daterangepicker({
// 				timePicker: timef,
// 				minDate:min,
// 				maxDate:max,
// 				format: formats
// 			}).on("shown",function(ev, picker){
// 				if(minid){
// 					var rarr=minid.split("-"),rid=rarr[0],index=rarr[1]-0,
// 					result=$(rid).val();
// 					if(result){
// 						picker.picker.minDate=moment(result.split("-")[index], picker.picker.format);
// 						picker.picker.updateCalendars();
// 					}
// 				}
// 				if(maxid){
// 					var rmaxarr=maxid.split("-"),rmaxid=rmaxarr[0],indexmax=rmaxarr[1]-0,
// 					resultmax=$(rmaxid).val();
// 					if(resultmax){
// 						picker.picker.maxDate=moment(resultmax.split("-")[indexmax], picker.picker.format);
// 						picker.picker.updateCalendars();
// 					}
				
// 				}
// 			});
// 			var next = $this.next("span.add-on");
// 			var prev = $this.prev("span.add-on");
// 			next.add(prev).on("click", function () {
// 				$this.trigger("click");
// 			});

// 		});
// 	}
// });

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
	var browser = getBrowserInfo();
	if(!(browser == "firefox" || browser == "chrome")){
		$('.w840 .cont').attr("style","position:relative;");
		$('.w840 .cont').append('<div class="browserinfo"><strong>请使用Chrome（谷歌）浏览器或者Firefox（火狐）浏览器打开页面。</strong><div class="browserbg"></div></div>');
	}
});

/* -------------------------------- 日历插件  结束 ------------------------------- */




























