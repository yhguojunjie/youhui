<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

	<div class="norl_title">
			<h3>插件推荐</h3>
			<a  id="tjPlugin_change">换一批</a>
	</div>
	<ul id="plugintj_ul">
<%-- 		<c:forEach items="${tjPluginVos}" var="tjPluginVo">
				<li>
					<section onclick="javascript:self.location='${wxurl_pluginDetail}/${tjPluginVo.id}'">
						<a class="imgbox"><img src="${fileAccessPath }${tjPluginVo.icon}"></a>
						<h3><a>${tjPluginVo.name}</a></h3>
						<p>已有${tjPluginVo.buyNum}人购买</p>
						<div class="clear"></div>
					</section>
				</li>
		  </c:forEach> --%>
	</ul>
	
	
<script type="text/javascript">

//初始化加载 
$(document).ready(function(){
	$.ajax({
		type: "GET",
		url : "${path }/wx/plugin/tjPlugins",
		data:{pluginId:'${plugin.id}',tjNum:2},
		dataType: "json",
		success: function(data){
			if(data != null && data != undefined
					&& data.length != 0){
				$('#plugintj_ul').empty();
				var html = makePlugintjList(data);
				$('#plugintj_ul').append(html);
			}
		}
	}); 
	
});

//推荐插件轮换
$('#tjPlugin_change').click(function(){
	$.ajax({
			type: "GET",
			url : "${path }/wx/plugin/tjPlugins",
			data:{pluginId:'${plugin.id}',tjNum:2},
			dataType: "json",
			success: function(data){
				if(data != null && data != undefined
						&& data.length != 0){
					$('#plugintj_ul').empty();
					var html = makePlugintjList(data);
					$('#plugintj_ul').html(html);
				}

		}
	}); 
});
	
//构造 list
function makePlugintjList(data){
	var html = "";
	for (var i = 0; i < data.length; i++) {
	    html += '<li>';
	    html += '<section onclick="javascript:self.location=\'${wxurl_pluginDetail}'+'/'+data[i].id+'\'">';
	    html += '<a class="imgbox"><img src="${fileAccessPath }'+data[i].icon+'"></a>';
	    html += '<h3><a>'+data[i].name+'</a></h3>';
	    html += '<p>已有'+data[i].buyNum+'人购买</p>';
	    html += '<div class="clear"></div>';
	    html += '</section>';
	    html += '</li>';
	}
	
	return html;
}

</script>