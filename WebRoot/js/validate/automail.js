AutoEmall();

function AutoEmall(a) {
	var b = this;
	$("input.auto_input").focus(function() {
		if ($.trim($("input.auto_input").attr("value").toLowerCase()) != "") {
			$("#auto_email").show()
		}
	}).blur(function() {
		var c = setTimeout("$('#auto_email').hide()", 250)
	}).keydown(function(c) {
		switch (c.keyCode) {
		case 38:
			c.preventDefault();
			moveSelect(-1);
			break;
		case 40:
			c.preventDefault();
			moveSelect(1);
			break;
		case 13:
			if ($("#auto_email td").filter(".current").length) {
				$("input.auto_input").val($("#auto_email td").filter(".current").eq(0).text());
				return false;
				$("#auto_email").hide()
			}
			if ($.trim($("input.auto_input").attr("value").toLowerCase()).indexOf("@") > -1) {
				$("#auto_email").hide()
			}
			break;
		case 108:
			if ($.trim($("input.auto_input").attr("value").toLowerCase()).indexOf("@") > -1) {
				$("#auto_email").hide()
			} else {
				$("#auto_email").show()
			}
			break;
		default:
			break
		}
	}).keyup(function(c) {
		if ($.trim($("input.auto_input").attr("value").toLowerCase()) != "") {
			$("#auto_email").show()
		}
		if (!(c.keyCode == 38 || c.keyCode == 40)) {
			if ($.trim($("input.auto_input").attr("value").toLowerCase()) != "") {
				buildList();
				$("#auto_email").show()
			}
		}
		if (c.keyCode == 13 || c.keyCode == 108) {
			if ($.trim($("input.auto_input").attr("value").toLowerCase()).indexOf("@") > -1) {
				$("#auto_email").hide()
			} else {
				$("#auto_email").show()
			}
		}
		if ($.trim($("input.auto_input").attr("value").toLowerCase()) == "") {
			$("#auto_email").hide()
		}
	})
}
function moveSelect(d) {
	var a = $("#auto_email td");
	var b = a.index(a.filter(".current").eq(0));
	var c = a.length;
	if (d == -1) {
		if (b == c || b == 0) {
			b = c
		}
		b--
	} else {
		if (d == 1) {
			if (b == c - 1) {
				b = 0
			} else {
				b++
			}
		}
	}
	a.eq(b).addClass("current").parent().siblings().children().removeClass("current")
}
function buildList() {
	var b = ["qq.com", "163.com", "126.com", "139.com", "sina.com", "sina.com.cn", "sohu.com", "gmail.com", "hotmail.com"];
	var d = $.trim($("input.auto_input").attr("value").toLowerCase());
	var e = "";
	var c = "";
	c += '<tr><td class="current">' + d + "</td></tr>";
	if (d.indexOf("@") > -1) {
		e = d.substring(d.indexOf("@") + 1);
		d = d.substring(0, d.indexOf("@"))
	}
	for (var a = 0; a < b.length; a++) {
		if (e != "") {
			if (b[a].indexOf(e) == 0) {
				c += "<tr><td>" + d + "@" + b[a] + "</td></tr>"
			}
		} else {
			c += "<tr><td>" + d + "@" + b[a] + "</td></tr>"
		}
	}
	$("#auto_email tbody").html(c);
	$("#auto_email td").hover(function() {
		$(this).addClass("current").parent().siblings().children().removeClass("current")
	}, function() {});
	$("#auto_email td").click(function() {
		$("input.auto_input").val($(this).text()).focus();
		var f = $("input.auto_input").get(0).createTextRange();
		f.collapse(false);
		f.select();
		$("#auto_email").hide();
		buildList()
	})
};