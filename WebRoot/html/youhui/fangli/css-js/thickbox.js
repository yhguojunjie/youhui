var tb_pathToImage = "http://i.fanhuan.com/images/thickBoxLoading.gif";
$(document).ready(function() {
	tb_init("a.thickbox, area.thickbox, input.thickbox");
	imgLoader = new Image();
	imgLoader.src = tb_pathToImage
});

function tb_init(a) {
	$(a).click(function() {
		var b = this.title || this.name || null;
		var c = this.href || this.alt;
		var d = this.rel || false;
		tb_show(b, c, d);
		this.blur();
		return false
	})
}
function tb_show(g, c, i) {
	try {
		if (typeof document.body.style.maxHeight === "undefined") {
			$("body", "html").css({
				height: "100%",
				width: "100%"
			});
			$("html").css("overflow", "hidden");
			if (document.getElementById("TB_HideSelect") === null) {
				$("body").append("<iframe id='TB_HideSelect'></iframe><div id='TB_overlay'></div><div id='TB_window'></div>");
				$("#TB_overlay").click(tb_remove)
			}
		} else {
			if (document.getElementById("TB_overlay") === null) {
				$("body").append("<div id='TB_overlay'></div><div id='TB_window'></div>");
				$("#TB_overlay").click(tb_remove)
			}
		}
		if (tb_detectMacXFF()) {
			$("#TB_overlay").addClass("TB_overlayMacFFBGHack")
		} else {
			$("#TB_overlay").addClass("TB_overlayBG")
		}
		if (g === null) {
			g = ""
		}
		$("body").append("<div id='TB_load'><img src='" + imgLoader.src + "' /></div>");
		$("#TB_load").show();
		var j;
		if (c.indexOf("?") !== -1) {
			j = c.substr(0, c.indexOf("?"))
		} else {
			j = c
		}
		var h = /\.jpg$|\.jpeg$|\.png$|\.gif$|\.bmp$/;
		var a = j.toLowerCase().match(h);
		if (a == ".jpg" || a == ".jpeg" || a == ".png" || a == ".gif" || a == ".bmp") {
			TB_PrevCaption = "";
			TB_PrevURL = "";
			TB_PrevHTML = "";
			TB_NextCaption = "";
			TB_NextURL = "";
			TB_NextHTML = "";
			TB_imageCount = "";
			TB_FoundURL = false;
			if (i) {
				TB_TempArray = $("a[@rel=" + i + "]").get();
				for (TB_Counter = 0;
				((TB_Counter < TB_TempArray.length) && (TB_NextHTML === "")); TB_Counter++) {
					var b = TB_TempArray[TB_Counter].href.toLowerCase().match(h);
					if (!(TB_TempArray[TB_Counter].href == c)) {
						if (TB_FoundURL) {
							TB_NextCaption = TB_TempArray[TB_Counter].title;
							TB_NextURL = TB_TempArray[TB_Counter].href;
							TB_NextHTML = "<span id='TB_next'>&nbsp;&nbsp;<a href='#'>Next &gt;</a></span>"
						} else {
							TB_PrevCaption = TB_TempArray[TB_Counter].title;
							TB_PrevURL = TB_TempArray[TB_Counter].href;
							TB_PrevHTML = "<span id='TB_prev'>&nbsp;&nbsp;<a href='#'>&lt; Prev</a></span>"
						}
					} else {
						TB_FoundURL = true;
						TB_imageCount = "Image " + (TB_Counter + 1) + " of " + (TB_TempArray.length)
					}
				}
			}
			imgPreloader = new Image();
			imgPreloader.onload = function() {
				imgPreloader.onload = null;
				var k = tb_getPageSize();
				var m = k[0] - 150;
				var n = k[1] - 150;
				var o = imgPreloader.width;
				var l = imgPreloader.height;
				if (o > m) {
					l = l * (m / o);
					o = m;
					if (l > n) {
						o = o * (n / l);
						l = n
					}
				} else {
					if (l > n) {
						o = o * (n / l);
						l = n;
						if (o > m) {
							l = l * (m / o);
							o = m
						}
					}
				}
				TB_WIDTH = o + 30;
				TB_HEIGHT = l + 60;
				$("#TB_window").append("<a href='' id='TB_ImageOff' title='Close'><img id='TB_Image' src='" + c + "' width='" + o + "' height='" + l + "' alt='" + g + "'/></a><div id='TB_caption'>" + g + "<div id='TB_secondLine'>" + TB_imageCount + TB_PrevHTML + TB_NextHTML + "</div></div><div id='TB_closeWindow'><a href='#' id='TB_closeWindowButton' title='Close'>关闭</a></div>");
				$("#TB_closeWindowButton").click(tb_remove);
				if (!(TB_PrevHTML === "")) {
					function q() {
						if ($(document).unbind("click", q)) {
							$(document).unbind("click", q)
						}
						$("#TB_window").remove();
						$("body").append("<div id='TB_window'></div>");
						tb_show(TB_PrevCaption, TB_PrevURL, i);
						return false
					}
					$("#TB_prev").click(q)
				}
				if (!(TB_NextHTML === "")) {
					function p() {
						$("#TB_window").remove();
						$("body").append("<div id='TB_window'></div>");
						tb_show(TB_NextCaption, TB_NextURL, i);
						return false
					}
					$("#TB_next").click(p)
				}
				document.onkeydown = function(r) {
					if (r == null) {
						keycode = event.keyCode
					} else {
						keycode = r.which
					}
					if (keycode == 27) {
						tb_remove()
					} else {
						if (keycode == 190) {
							if (!(TB_NextHTML == "")) {
								document.onkeydown = "";
								p()
							}
						} else {
							if (keycode == 188) {
								if (!(TB_PrevHTML == "")) {
									document.onkeydown = "";
									q()
								}
							}
						}
					}
				};
				tb_position();
				$("#TB_load").remove();
				$("#TB_ImageOff").click(tb_remove);
				$("#TB_window").css({
					display: "block"
				})
			};
			imgPreloader.src = c
		} else {
			var f = c.replace(/^[^\?]+\??/, "");
			var d = tb_parseQuery(f);
			TB_WIDTH = (d.width * 1) + 30 || 630;
			TB_HEIGHT = (d.height * 1) + 40 || 440;
			ajaxContentW = TB_WIDTH - 30;
			ajaxContentH = TB_HEIGHT - 45;
			if (c.indexOf("TB_iframe") != -1) {
				urlNoQuery = c.split("TB_");
				if (urlNoQuery[0].length - 2 == urlNoQuery[0].indexOf("?&")) {
					urlNoQuery[0] = urlNoQuery[0].substr(0, urlNoQuery[0].length - 2)
				}
				$("#TB_iframeContent").remove();
				if (d.modal != "true") {
					$("#TB_window").append("<div id='TB_title'><div id='TB_ajaxWindowTitle'>" + g + "</div><div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton' title='Close'>关闭</a></div></div><iframe frameborder='0' hspace='0' src='" + urlNoQuery[0] + "' id='TB_iframeContent' name='TB_iframeContent" + Math.round(Math.random() * 1000) + "' onload='tb_showIframe()' style='width:" + (ajaxContentW + 29) + "px;height:" + (ajaxContentH + 17) + "px;' > </iframe>")
				} else {
					$("#TB_overlay").unbind();
					if (location.pathname == "/my/status" || location.pathname == "/my/set" || location.pathname == "/duihuan/huafei" || location.pathname == "/duihuan/qbi") {
						ajaxContentW = 372;
						ajaxContentH = 217;
						if (location.pathname == "/my/status") {
							ajaxContentW = TB_WIDTH;
							ajaxContentH = TB_HEIGHT - 45
						}
						$("#TB_window").append("<iframe frameborder='0' hspace='0' src='" + urlNoQuery[0] + "' id='TB_iframeContent' name='TB_iframeContent" + Math.round(Math.random() * 1000) + "' onload='tb_showIframe()' style='width:" + (ajaxContentW) + "px;height:" + (ajaxContentH) + "px;'> </iframe>")
					}
				}
			} else {
				if ($("#TB_window").css("display") != "block") {
					if (d.modal != "true") {
						$("#TB_window").append("<div id='TB_title'><div id='TB_ajaxWindowTitle'>" + g + "</div><div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton'>关闭</a></div></div><div id='TB_ajaxContent' style='width:" + ajaxContentW + "px;height:" + ajaxContentH + "px'></div>")
					} else {
						$("#TB_overlay").unbind();
						$("#TB_window").append("<div id='TB_ajaxContent' class='TB_modal' style='width:" + ajaxContentW + "px;height:" + ajaxContentH + "px;'></div>")
					}
				} else {
					$("#TB_ajaxContent")[0].style.width = ajaxContentW + "px";
					$("#TB_ajaxContent")[0].style.height = ajaxContentH + "px";
					$("#TB_ajaxContent")[0].scrollTop = 0;
					$("#TB_ajaxWindowTitle").html(g)
				}
			}
			$("#TB_closeWindowButton").click(tb_remove);
			if (c.indexOf("TB_inline") != -1) {
				$("#TB_ajaxContent").append($("#" + d.inlineId).children());
				$("#TB_window").unload(function() {
					$("#" + d.inlineId).append($("#TB_ajaxContent").children())
				});
				tb_position();
				$("#TB_load").remove();
				$("#TB_window").css({
					display: "block"
				})
			} else {
				if (c.indexOf("TB_iframe") != -1) {
					tb_position();
					if ($.browser.safari) {
						$("#TB_load").remove();
						$("#TB_window").css({
							display: "block"
						})
					}
				} else {
					$("#TB_ajaxContent").load(c += "&random=" + (new Date().getTime()), function() {
						tb_position();
						$("#TB_load").remove();
						tb_init("#TB_ajaxContent a.thickbox");
						$("#TB_window").css({
							display: "block"
						})
					})
				}
			}
		}
		if (!d.modal) {
			document.onkeyup = function(k) {
				if (k == null) {
					keycode = event.keyCode
				} else {
					keycode = k.which
				}
				if (keycode == 27) {
					tb_remove()
				}
			}
		}
	} catch (e) {}
}
function tb_showIframe() {
	$("#TB_load").remove();
	$("#TB_window").css({
		display: "block"
	});
	var b = document.getElementById("TB_iframeContent");
	var a = document.frames ? document.frames.TB_iframeContent.document : b.contentDocument;
	if (b != null && a != null) {
		b.height = a.body.scrollHeight
	}
}
function tb_remove() {
	$("#TB_imageOff").unbind("click");
	$("#TB_closeWindowButton").unbind("click");
	$("#TB_window").fadeOut("fast", function() {
		$("#TB_window,#TB_overlay,#TB_HideSelect").trigger("unload").unbind().remove()
	});
	$("#TB_load").remove();
	if (typeof document.body.style.maxHeight == "undefined") {
		$("body", "html").css({
			height: "auto",
			width: "auto"
		});
		$("html").css("overflow", "")
	}
	document.onkeydown = "";
	document.onkeyup = "";
	return false
}
function tb_position() {
	$("#TB_window").css({
		marginLeft: "-" + parseInt((TB_WIDTH / 2), 10) + "px",
		width: TB_WIDTH + "px"
	});
	if (location.pathname == "/my/set" || location.pathname == "/duihuan/huafei" || location.pathname == "/duihuan/qbi") {
		$("#TB_window").css({
			width: 372
		})
	}
	if (!(jQuery.browser.msie && jQuery.browser.version < 7)) {
		$("#TB_window").css({
			marginTop: "-" + parseInt((TB_HEIGHT / 2), 10) + "px"
		})
	}
}
function tb_parseQuery(e) {
	var b = {};
	if (!e) {
		return b
	}
	var a = e.split(/[;&]/);
	for (var c = 0; c < a.length; c++) {
		var g = a[c].split("=");
		if (!g || g.length != 2) {
			continue
		}
		var f = unescape(g[0]);
		var d = unescape(g[1]);
		d = d.replace(/\+/g, " ");
		b[f] = d
	}
	return b
}
function tb_getPageSize() {
	var c = document.documentElement;
	var a = window.innerWidth || self.innerWidth || (c && c.clientWidth) || document.body.clientWidth;
	var b = window.innerHeight || self.innerHeight || (c && c.clientHeight) || document.body.clientHeight;
	arrayPageSize = [a, b];
	return arrayPageSize
}
function tb_detectMacXFF() {
	var a = navigator.userAgent.toLowerCase();
	if (a.indexOf("mac") != -1 && a.indexOf("firefox") != -1) {
		return true
	}
};