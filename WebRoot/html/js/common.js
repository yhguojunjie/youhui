/**
 * 判断是否是数字
 * @param num
 * @returns
 */
function isNum(num){
	var regExp = new RegExp('^[0-9]*[1-9][0-9]*$');  
	return regExp.test(num);
}

/**
 * 判断浏览器
 */
var ua = navigator.userAgent.toLowerCase();  
function check(r){  
  return r.test(ua);  
}  
/** 
 * return IE,IE6,IE7,IE8,IE9,Chrome,Firefox,Opera,WebKit,Safari,Others 
*/  
function getBrowserName(){  
  var browserName;  
  var isOpera = check(/opera/);  
  var isChrome = check(/chrome/);  
  var isFirefox = check(/firefox/);  
  var isWebKit = check(/webkit/);  
  var isSafari = !isChrome && check(/safari/);  
  var isIE = !isOpera && check(/msie/);  
  var isIE7 = isIE && check(/msie 7/);  
  var isIE8 = isIE && check(/msie 8/);  
  if(isIE8)  
  {  
    browserName = "IE8";    
  }else if(isIE7)  
  {  
    browserName = "IE7";  
  }else if(isIE)  
  {  
    browserName = "IE";  
  }else if(isChrome)  
  {  
    browserName = "Chrome";  
  }else if(isFirefox)  
  {  
    browserName = "Firefox";  
  }else if(isOpera)  
  {  
    browserName = "Opera";  
  }else if(isWebKit)  
  {  
    browserName = "WebKit";  
  }else if(isSafari)  
  {  
    browserName = "Safari";  
  }else  
  {  
    browserName = "Others";  
  }  
  return browserName;  
}  

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 *
 * @param fmt
 * @returns
 */
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//产生签权字符串
function getAuthStr(uid,pid){
	var key = "tchajiantchajiantchajian";
	var str = "";
  	if(uid != null && uid != ""){
  		str += uid;
  	}
  	if(pid != null && pid != ""){
  		str += pid;
  	}
  	str += key;
  	var authStr = "";
  	if(str != null && str != ""){
  		authStr = hex_md5(str);
  	}
  	return authStr;
 }

