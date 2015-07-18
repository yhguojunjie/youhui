/**
 * 获取匹配元素 内部所有input标签的name和value 如果name和value为空则 不获取
 */


(function($){
    $.fn.elParam = function(){
        var result = {};
       $(this).find("input").each(function(){
           if($(this).attr("disabled")) return;
           var type= $(this).attr("type");
           if(type&&(type.toLocaleLowerCase()=="radio"||type.toLocaleLowerCase() == "checkbox")&&!($(this).attr("checked")))return;
           var name = $(this).attr("tag");
           var value = $(this).val();
           if(name&&value){
               result[name] =  value;
           }
       });
        return result;
    }
    
    $.fn.serializeObject = function()    
    {
    	var o = [];//定义数组
    	$(this).find(".screen .screen_json").each(function(){
    		var strjson = $(this).val();
    		o.push(eval('(' + strjson + ')'));
    	});
    	return JSON.stringify(o);
    }

})($);


