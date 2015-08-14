$('span[id^="cnzz_stat_icon"]').hide();
if (CurrentUser.UserName != undefined) {
	$(".iframe_tips").show();
	$("#site_userinfo").html("您好，<a id='formailafter' href='http://my.fanhuan.com/my/'>" + CurrentUser.UserName + "</a><span class='ver-line'>|</span><a class='gotomail' href='http://my.fanhuan.com/my/mail'><i class='ico-site i-site-mail'></i><font id = 'NewMessageCount'>(0)</font></a><span class='ver-line'>|</span><a class='toplogout' href='javascript:LogOut();'>退出</a> ")
} else {
	var index = 1;
	if ($.cookie("user_name") == null) {
		$("#site_userinfo").html("<a href = 'javascript:Login()' id = 'login_link'>请登录</a><span class='ver-line'>|</span><a href = 'http://passport.fanhuan.com/reg/' target='_blank' class='top_reg'>免费注册</a><span class='ver-line'>|</span><a href=\"javascript:LoginFrom('qq')\" class='topqq_login'><i class='ico-site i-site-qq'></i>QQ登录</a><span class='ver-line'>|</span><a href=\"javascript:LoginFrom('taobao')\" class='toptb_login'><i class='ico-site i-site-tb'></i>淘宝登录</a><span class='ver-line'>|</span><a href= \"javascript:LoginFrom('sina')\" class='topwb_login'><i class='ico-site i-site-weibo-sina'></i>微博登录</a><span class='ver-line'>|</span><a href= \"javascript:LoginFrom('wechat')\" class='topwb_login'><i class='ico-site i-site-wx'></i>微信登录</a>");
		$("#login_link").attr("href", "javascript:Login('index')")
	} else {
		var oCookieUserName = $.cookie("user_name");
		if (oCookieUserName.length > 20) {
			oCookieUserName = oCookieUserName.substring(0, 15) + "..."
		}
		$("#site_userinfo").html("您好，<span class='user-name f-ellipsis' title=" + oCookieUserName + ">" + oCookieUserName + "</span>&nbsp;&nbsp;<a href = 'javascript:Login()' id = 'login_link'>请登录</a><span class='ver-line'>|</span><a href = 'http://passport.fanhuan.com/reg/' target='_blank' class='top_reg'>免费注册</a><span class='ver-line'>|</span><a href=\"javascript:LoginFrom('qq')\" class='topqq_login'><i class='ico-site i-site-qq'></i>QQ登录</a><span class='ver-line'>|</span><a href=\"javascript:LoginFrom('taobao')\" class='toptb_login'><i class='ico-site i-site-tb'></i>淘宝登录</a><span class='ver-line'>|</span><a href= \"javascript:LoginFrom('sina')\" class='topwb_login'><i class='ico-site i-site-weibo-sina'></i>微博登录</a><span class='ver-line'>|</span><a href= \"javascript:LoginFrom('wechat')\" class='topwb_login'><i class='ico-site i-site-wx'></i>微信登录</a>");
		$("#login_link").attr("href", "javascript:Login('index')")
	}
}
$(".act-login").live("click", function() {
	if (CurrentUser.UserId == undefined) {
		Login();
		return false
	}
});

function request(c) {
	var a = location.href;
	var e = a.substring(a.indexOf("?") + 1, a.length).split("&");
	var d = {};
	for (i = 0; j = e[i]; i++) {
		d[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length)
	}
	var b = d[c.toLowerCase()];
	if (typeof(b) == "undefined") {
		return ""
	} else {
		return b
	}
}
if ($(".notice_inner li").length > 1) {
	var mtime = null;
	$(function() {
		clearInterval(mtime);
		$("#nav_notice").hover(function() {
			$(".notice_inner").stop(true, true);
			clearInterval(mtime);
			$(this).addClass("nav_notice_hover");
			$("#nav_notice .i-site-arrow").css("backgroundPosition", "-64px 0px")
		}, function() {
			clearInterval(mtime);
			$(this).removeClass("nav_notice_hover");
			$("#nav_notice .i-site-arrow").css("backgroundPosition", "-64px -5px");
			mtime = setInterval(scrollDown, 3000)
		});
		mtime = setInterval(scrollDown, 3000)
	});

	function scrollDown() {
		$(".notice_inner").stop(true, true).animate({
			top: "-19px"
		}, 600, function() {
			$(".notice_inner").css("top", "6px");
			$(".notice_inner li").first().insertAfter($(".notice_inner li").last())
		})
	}
}
$("#shopper_lists li").hover(function() {
	_this = $(this);
	clearInterval(_this.timer);
	_this.timer = setTimeout(function() {
		a(_this)
	}, 300);

	function a(b) {
		$(".subcategory_box").hide();
		b.addClass("current").siblings().removeClass("current");
		b.find(".subcategory_box").show()
	}
}, function() {
	clearInterval(_this.timer);
	_this.timer = setTimeout(function() {
		$(".subcategory_box").hide();
		$("#shopper_lists li").removeClass("current")
	}, 300)
});
$("#mallLogoList").find("li").live("mouseover", function() {
	$(this).addClass("hover").siblings().removeClass("hover")
}).live("mouseout", function() {
	$("#mallLogoList").find("li").removeClass("hover")
});
var regPhoneNum = /^(\+86)?1[34578]\d{9}$/;

function regPhone(a) {
	return regPhoneNum.test(a)
}
var _defaultText = ["输入商城名查看返还，例如：凡客", "输入关键字搜宝贝、商城、优惠券", "输入宝贝名称/宝贝网址查看返还，例如：李宁", "请输入团购商品或团购名称哦", "输入商城名找优惠券，例如：凡客", "请输入团购商品或团购名称哦", "输入淘宝/天猫商品名称"];
var regRow = /[,|.|，|。|\/|、|~|!|！|？|?|@|\||+|-|*|.|;|'|；|"|“|:|：|#|$|￥|%|……|^|-|*|(|)|（|）|_|——|=|\s]/g;
$("#search .search-select").html($("#search_item li.current").html());
var timeSearch = null;
$("#search .search-select,#search .search-square").mouseover(function() {
	timeSearch = setTimeout(function() {
		$("#search .search-select").hide();
		$("#search .search-square").addClass("search-square-down");
		$("#search_item").show()
	}, 150)
}).mouseout(function() {
	clearTimeout(timeSearch)
});
$("#searchInputImage").hover(function() {
	$(this).toggleClass("hover")
});
$("#search_item").hover(function() {
	$("#search_item li").mouseover(function() {
		$(this).addClass("hover").siblings().removeClass("hover")
	}).click(function() {
		var b = $("#search_item li.current").attr("id");
		$(this).addClass("current").siblings().removeClass("current");
		$("#search .search-select").html($("#search_item li.current").html());
		if ($(this).html() != $("#search_item li:first-child").html()) {
			$(this).insertBefore($("#search_item li").first());
			$("#search .search-select, #search .search-square").show();
			$("#search .search-square").removeClass("search-square-down");
			$("#search_item").hide()
		}
		var c = 1;
		var d = $(this).attr("id");
		switch (d) {
		case "hs_tb":
		default:
			c = 1;
			break;
		case "hs_mall":
			c = 0;
			break;
		case "hs_tg":
			c = 3;
			break;
		case "hs_yhq":
			c = 4;
			break
		}
		var a = 1;
		switch (b) {
		case "hs_tb":
		default:
			a = 1;
			break;
		case "hs_mall":
			a = 0;
			break;
		case "hs_tg":
			a = 3;
			break;
		case "hs_yhq":
			a = 4;
			break
		}
		if ($("#SearchInputText").val() == "" || $("#SearchInputText").val() == _defaultText[a]) {
			$("#SearchInputText").attr("value", _defaultText[c])
		} else {
			HeadMallSuggest($.trim($("#SearchInputText").val()));
			$("#J_show").hide();
			$("#J_show .sts").hide()
		}
	})
}, function() {
	$(this).hide();
	$("#search .search-select,#search .search-square").show();
	$("#search .search-square").removeClass("search-square-down")
});
$("#SearchInputText").focus(function() {
	var d = $("#search_input .sts"),
		f = $("#J_show");
	d.show();
	var c = $("#SearchInputText").val();
	var e = /http:\/\/.+/gi;
	var b = e.test(c);
	if (!b) {
		f.show();
		if (d.length > 0) {
			$("#J_show").show()
		}
	}
	if ($("#search_item").is(":visible")) {
		$("#search_item").hide()
	}
	$("#search .search-select,#search .search-square").show();
	$("#search .search-square").removeClass("search-square-down");
	var a = $("#search_item li.current").attr("id");
	var g = a == "hs_mall" ? 0 : a == "hs_tb" ? 1 : a == "hs_tg" ? 3 : 4;
	if ($("#homeNavId").length == 1) {
		g = 6
	}
	$(this).css("color", "#333");
	if ($.trim($(this).val()) == _defaultText[g]) {
		$(this).val("");
		$(this).next().hide();
		if (g == 6) {
			$(this).css({
				"margin-left": 17
			})
		}
	}
}).blur(function() {
	var a = $("#search_item li.current").attr("id");
	var b = a == "hs_mall" ? 0 : a == "hs_tb" ? 1 : a == "hs_tm" ? 2 : a == "hs_tg" ? 3 : 4;
	if ($("#homeNavId").length == 1) {
		b = 6
	}
	if ($.trim($(this).val()) == "") {
		$(this).val(_defaultText[b]);
		$("#SearchInputText").css({
			color: "#9C9C9C"
		});
		if (b == 6) {
			$("#SearchInputText").css({
				"margin-left": 32
			})
		}
		$("#SearchInputText").next().show()
	}
	setTimeout(function() {
		$("#J_show").hide();
		$("#Text_Ts_Box .sts").css("display", "none")
	}, 200);
	if ($(this).attr("value") == "") {
		$(this).css("color", "#9C9C9C");
		$(this).attr("value", _defaultText[0])
	}
});
$("#search_input .sts").live("mouseover", function() {
	$("#search_input .sts").removeClass("hover");
	$(this).addClass("hover")
}).live("mouseout", function() {
	$(this).removeClass("hover")
}).live("click", function() {
	var c = $("#search_item li.current").attr("id");
	switch (c) {
	case "hs_tb":
	default:
		c = 1;
		break;
	case "hs_mall":
		c = 0;
		break;
	case "hs_tg":
		c = 3;
		break;
	case "hs_yhq":
		c = 4;
		break
	}
	if (c == 1) {
		var a = $(this).attr("url");
		for (var b = 0, d = $(".show > .sts").length; b < d; b++) {
			if (a == $(".show > .sts").eq(b).attr("url")) {
				var e = $(".show > .sts").eq(b).attr("data-type");
				if (e == "mall" && a.indexOf("www.fanhuan.com") > -1) {
					$.cookie("home_cur_catename", null, {
						path: "/",
						expires: -1,
						domain: _domain
					});
					break
				}
			}
		}
		window.open($(this).attr("url"))
	} else {
		window.open($(this).attr("url"))
	}
});

function SearchMall(f) {
	var d = $.trim($("#SearchInputText").attr("value"));
	var g = 1;
	var c = $("#search_item li.current").attr("id") || $("#homeNavId").val();
	switch (c) {
	case "hs_tb":
	default:
		g = 1;
		break;
	case "hs_tm":
		g = 1;
		break;
	case "hs_mall":
		g = 0;
		break;
	case "hs_tg":
		g = 3;
		break;
	case "hs_yhq":
		g = 4;
		break
	}
	if ($("#homeNavId").length == 1) {
		g = 6
	}
	if ($("#SearchInputText").attr("value") == _defaultText[g] || $("#SearchInputText").attr("value") == "") {
		alert("请输入关键字。");
		return false
	}
	if (g == 0) {} else {
		if (g == 3) {
			var e = getValue($("#SearchInputText"));
			var m = /^[a-zA-z]+:\/\/[^\s]*$/;
			if (m.test(e)) {
				$("#head_form").attr("action", "http://taobao.fanhuan.com/search/item?url=" + encodeURIComponent(e));
				return true
			}
			$("#head_form").attr("action", "http://tuan.fanhuan.com/?kw=" + encodeURIComponent(d));
			return true
		} else {
			if (g == 4) {
				var e = getValue($("#SearchInputText"));
				var m = /^[a-zA-z]+:\/\/[^\s]*$/;
				if (m.test(e)) {
					$("#head_form").attr("action", "http://taobao.fanhuan.com/search/item?url=" + encodeURIComponent(e));
					return true
				}
				if ($("#Text_Ts_Box .show .sts").length) {
					if ($("#Text_Ts_Box .show .sts").hasClass("hover")) {
						$("#head_form").attr("action", $("#Text_Ts_Box .show .hover").eq(0).attr("url"))
					} else {
						$("#head_form").attr("action", $("#Text_Ts_Box .show .sts").eq(0).attr("url"))
					}
				} else {
					alert("抱歉，搜索不到任何优惠券");
					return false
				}
				return true
			} else {
				if (g == 1 || g == 6) {
					if (!searchItem()) {
						return false
					}
					var h = $("#head_form").attr("action");
					for (var a = 0, b = $(".show > .sts").length; a < b; a++) {
						if (h == $(".show > .sts").eq(a).attr("url")) {
							var k = $(".show > .sts").eq(a).attr("data-type");
							if (k == "mall" && h.indexOf("www.fanhuan.com") > -1) {
								$.cookie("home_cur_catename", null, {
									path: "/",
									expires: -1,
									domain: _domain
								});
								return true
							}
						}
					}
				}
			}
		}
	}
}
function HeadMallSuggest(e) {
	var s = $("#search_item li.current").attr("id");
	$("#J_show").html("").show();
	if ($("#homeNavId").length == 1) {
		s = $("#homeNavId").val()
	}
	if (s == "hs_mall") {} else {
		if (s == "hs_tg") {} else {
			if (s == "hs_tb") {
				if (e.toLowerCase().replace(regRow, "") != "") {
					if (testKeyWord(e)) {
						$("#J_show").html("");
						var b = "";
						var r = "";
						var u = "";
						var p = "";
						var l = 0;
						var d = 0;
						var t = /^[a-zA-z]+:\/\/[^\s]*$/;
						var a = false;
						var h = $("#SearchInputText").val();
						var k = /http:\/\/.+/gi;
						var q = k.test(h);
						for (var o in AllMall) {
							for (var n in AllMall[o]) {
								var c = AllMall[o][n];
								if (c.SS.toLowerCase().indexOf(e.toLowerCase().replace(regRow, "")) >= 0 && l < 5) {
									var m = "";
									if (c.IC == "淘宝网") {
										m = "http://taobao.fanhuan.com/"
									} else {
										if (c.IC == "tmall") {
											m = "http://taobao.fanhuan.com/qijian/"
										} else {
											if (c.IC == "paipai") {
												m = "http://paipai.fanhuan.com/"
											} else {
												if (c.IC == "tgfh") {
													m = "http://tuan.fanhuan.com/"
												} else {
													if (q) {
														var g = $("#SearchInputText").val();
														m = "http://go.fanhuan.com/redirect/?s=" + c.IC + "&p=" + encodeURIComponent(g)
													} else {
														m = "http://www.fanhuan.com/" + c.IC + ".html"
													}
												}
											}
										}
									}
									if (l == 0) {
										b += '<div class="sts sts-tit sts-add" data-type="mall" url="' + m + '" ><span class="left">搜“<strong>' + formatKeyword(e) + '</strong>”相关商城<em>&gt;&gt;</em></span><span class="right"></span><div class="clear"></div></div>'
									}
									try {
										if (c.LM.toLowerCase() == "tb") {
											a = true
										} else {
											a = false
										}
									} catch (f) {}
									if (a) {
										imgSrcDefault = "http://i.fanhuan.com/images/ico_mall/淘宝网.png"
									} else {
										imgSrcDefault = "http://i.fanhuan.com/images/ico_mall/ico-default.png"
									}
									imgSrc = "http://i.fanhuan.com/images/ico_mall/" + c.IC + ".png";
									b += '<div class="sts sts-add" data-type="mall" url="' + m + '"><span class="left"><img src="' + imgSrc + '" onerror="this.src=(\'' + imgSrcDefault + "')\"  />" + c.T + '</span><span class="right">返还' + c.FH + '</span><div class="clear"></div></div>';
									l++
								}
							}
						}
						if (b == "") {
							Head_TB_Suggest(e)
						} else {
							$("#J_show").append(b);
							if (q) {
								$("#J_show").hide()
							}
						}
						p = '<div class="sts sts-tb sts-tit sts-add" url="http://taobao.fanhuan.com/tbs/?keyword=' + encodeURIComponent(formatKeyword(e)) + '"><span class="left">搜“<strong>' + charAtEllipsis(formatKeyword(e), 10, 8) + '</strong>”相关淘宝/天猫宝贝<em>&gt;&gt;</em></span><span class="right"></span><div class="clear"></div></div>';
						if (q) {
							$("#J_show").hide()
						}
						$("#J_show").append(p);
						$("#J_show").append(r);
						$("#J_show .sts:first").addClass("sts-first").siblings().removeClass("sts-first");
						$("#J_show .sts:last").addClass("sts-last").siblings().removeClass("sts-last")
					}
				}
			}
		}
	}
}
function charAtEllipsis(c, b, a) {
	if (c.length >= b) {
		c = c.substring(0, a) + "..."
	}
	return c
}
function Head_TB_Suggest(a) {
	CreateJScript("tb_suggest", "http://suggest.taobao.com/sug?code=utf-8&callback=ShowTBSuggest&q=" + a)
}
function ShowTBSuggest(d) {
	var c = d.result;
	var e = "";
	var a = 0;
	if (c.length == 0) {
		return ""
	}
	for (var b in c) {
		if (a < 5) {
			var f = "http://taobao.fanhuan.com/tbs/?keyword=" + encodeURIComponent(formatKeyword(c[b][0]));
			e += '<div class="sts sts-tb sts-add sts-tb-first" url="' + f + '"><span class="left" style="width:90%">' + formatKeyword(c[b][0]) + '</span><span class="right"></span><div class="clear"></div></div>'
		}
		a++
	}
	if (status) {
		$("#J_show").hide()
	}
	if (status) {
		$("#J_show").hide()
	}
	$("#J_show").append(e);
	$("#J_show .sts-tb-first").insertBefore($("#J_show .sts-tit").eq(0));
	$("#J_show .sts:first").addClass("sts-first").siblings().removeClass("sts-first");
	$("#J_show .sts:last").addClass("sts-last").siblings().removeClass("sts-last")
}
function keyWord(b) {
	var c = /([^.]+)/gi;
	var a = b.match(c);
	if (a[1] == "vip") {
		a[1] = "vipshop"
	}
	return a[1]
}
function searchItem() {
	var d = getValue($("#SearchInputText"));
	var e = /http:\/\/.+/gi;
	var b = e.test(d);
	if (b && d.indexOf("taobao") == -1 && d.indexOf("tmall") == -1) {
		var d = keyWord(d)
	}
	var a = /^[a-zA-z]+:\/\/[^\s]*$/;
	var c = formatKeyword(d);
	if (a.test(d)) {
		$("#head_form").attr("action", "http://taobao.fanhuan.com/search/item?url=" + encodeURIComponent(d));
		return true
	}
	if (containsIllegalKeywords(c)) {
		$("#Text_Ts_Box .sts").remove();
		alert("很抱歉，您不能搜索此关键字。请尝试检索其他商品。");
		$("#SearchInputText").val("");
		return false
	} else {
		if (d.replace(regRow, "") == "") {
			return false
		} else {
			if ($("#Text_Ts_Box .show .sts").hasClass("hover")) {
				if ($("#Text_Ts_Box .show .hover").hasClass("sts-tb")) {
					if ($("#Text_Ts_Box .show .hover").hasClass("sts-tit")) {
						$("#head_form").attr("action", ("http://taobao.fanhuan.com/tbs/?keyword=" + encodeURIComponent(d)))
					} else {
						$("#head_form").attr("action", ("http://taobao.fanhuan.com/tbs/?keyword=" + encodeURIComponent($("#Text_Ts_Box .show .hover").find(".left").text())))
					}
				} else {
					$("#head_form").attr("action", $("#Text_Ts_Box .show .hover").eq(0).attr("url"));
					$("#Text_Ts_Box .show .hover").removeClass("hover")
				}
			} else {
				if (($("#Text_Ts_Box .show .sts-add").length > 1) && ($("#Text_Ts_Box .show .sts-add[data-type=mall]").length > 0)) {
					$("#head_form").attr("action", $("#Text_Ts_Box .show .sts-add").eq(0).attr("url"))
				} else {
					$("#head_form").attr("action", ("http://taobao.fanhuan.com/tbs/?keyword=" + encodeURIComponent(d)))
				}
			}
			return true
		}
	}
}
function testKeyWord(a) {
	var c = /^[a-zA-z]+:\/\/[^\s]*$/;
	var b = formatKeyword(a);
	if (containsIllegalKeywords(b)) {
		$("#Text_Ts_Box .sts").remove();
		return false
	} else {
		return true
	}
}
var ILLEGAL_KEYWORDS = ["网赚", "网络赚钱", "百家乐", "电子狗", "黑客", "赌具"];

function containsIllegalKeywords(a) {
	for (var b = 0; b < ILLEGAL_KEYWORDS.length; b++) {
		if (a.indexOf(ILLEGAL_KEYWORDS[b]) != -1) {
			return true
		}
	}
}
function formatKeyword(a) {
	if (!a) {
		return ""
	}
	var b = [/\//g, /\*/g, /\?/g, /</g, />/g, /%/g, /\:/g, /\|/g, /\?/g, '"'],
		d = b.length;
	for (var c = 0; c < d; c++) {
		a = a.replace(b[c], "")
	}
	return a
}
function getValue(a) {
	if (a.jquery) {
		return trim(a.val())
	} else {
		if (typeof a.value !== "undefined") {
			return trim(a.value)
		} else {
			return null
		}
	}
}
function trim(a) {
	if (a == null) {
		return ""
	}
	if (typeof String.prototype.trim !== "function") {
		return a.toString().replace(/^\s+/, "").replace(/\s+$/, "")
	}
	return a.toString().trim()
}
var _timeOutId = 0;
var _productKeyWord = "";
var _headKeyWord = "";
var _homeKeyWord = "";
var _interval = 200;
$("#SearchInputText").bind("keyup focus", function(k) {
	var n = $("#search_input .sts");
	var l = [];
	for (var d = 0; d < n.length; d++) {
		if (n.eq(d).is(":visible")) {
			l.push(d)
		}
	}
	var m = l.length;
	var b = $(this).attr("value").toLowerCase();
	var c = $(this).attr("id");
	var f = k || window.event;
	var h = f.keyCode;
	if (h == 13) {}
	if (h == 38 || h == 40) {
		if (c != "SearchInputText") {
			return
		}
		if (n.is(":visible")) {
			var g = "";
			if (h == 38) {
				if (n.index(n.filter(".hover").eq(0)) >= 0) {
					for (var a = 0; a < l.length; a++) {
						if (l[a] == n.index(n.filter(".hover").eq(0))) {
							g = a
						}
					}
				} else {
					g = 0
				}
				if (g == m || g == 0) {
					g = m
				}
				g--
			} else {
				if (n.index(n.filter(".hover").eq(0)) >= 0) {
					for (var a = 0; a < l.length; a++) {
						if (l[a] == n.index(n.filter(".hover").eq(0))) {
							g = a
						}
					}
				} else {
					g = -1
				}
				if (g >= m - 1) {
					g = -1
				}
				g++
			}
			n.eq(l[g]).addClass("hover").siblings().removeClass("hover")
		} else {
			_timeOutId = setTimeout(function() {
				HeadMallSuggest($.trim(b));
				_headKeyWord = b
			}, _interval)
		}
		return
	}
	if (b == "") {
		clearTimeout(_timeOutId);
		switch (c) {
		case "SearchInputText":
			_headKeyWord = "";
			$("#Text_Ts_Box .show").hide();
			$("#Text_Ts_Box .sts").css("display", "none");
			break;
		case "Search_Index":
			_homeKeyWord = "";
			$("#sidebar_ul li .active").eq(0).click();
			break;
		case "Search_Index_New":
			_productKeyWord = "";
			$("#sidebar_show_ul li .active").eq(0).click();
			break
		}
		return
	}
	switch (c) {
	case "SearchInputText":
		if (_headKeyWord != b) {
			clearTimeout(_timeOutId);
			_timeOutId = setTimeout(function() {
				HeadMallSuggest($.trim(b));
				_headKeyWord = b
			}, _interval)
		}
		break;
	case "Search_Index":
		if (_homeKeyWord != b) {
			clearTimeout(_timeOutId);
			_timeOutId = setTimeout(function() {
				HomeMallSuggest($.trim(b));
				_homeKeyWord = b
			}, _interval)
		}
		break
	}
	var e = /http:\/\/.+/gi;
	var o = e.test(b);
	if (o) {
		var b = keyWord(b)
	}
});
var _path = location.pathname.toLowerCase();

function GetTbUrl(a, c) {
	var b = "http://taobao.fanhuan.com/";
	id = GetItemId(a);
	if (id == "") {
		b += "s/" + FormatKeyWord(a) + (c ? "?mall_item=true" : "")
	} else {
		b += "convert/" + id
	}
	return b
}
function FormatKeyWord(a) {
	var b = [/\//g, /\*/g, /\?/g, /</g, />/g, /%/g, /\:/g, /\|/g, /\?/g, '"'];
	for (var c = 0; c < b.length; c++) {
		a = a.replace(b[c], "")
	}
	return encodeURIComponent(a)
}
function GetItemId(a) {
	if (a.indexOf("http://detail.tmall.com/item.htm") != 0 && a.indexOf("http://item.taobao.com/item.htm") != 0) {
		return ""
	}
	var c = a.split("?")[1];
	if (c == undefined) {
		return ""
	}
	ps = c.split("&");
	for (var b = 0; b < ps.length; b++) {
		var e = ps[b].split("=");
		if (e[0] == "id") {
			var d = e[1];
			if (d == undefined) {
				return ""
			}
			if (/^[0-9]+$/.test(d)) {
				return d
			}
		}
	}
	return ""
}
function TbLogin() {
	$.cookie("TargetUrl", top.location, {
		path: "/",
		domain: _domain
	});
	window.top.location.href = "http://passport.fanhuan.com/ajax/taobao"
}
function LoginFrom(a) {
	if (location.host == "passport.fanhuan.com") {
		window.top.location.href = "http://passport.fanhuan.com/ajax/" + a + "/"
	} else {
		top.location.href = "http://passport.fanhuan.com/ajax/" + a + "/?lp=" + encodeURIComponent(location.href)
	}
}
function Login(b) {
	var c = encodeURIComponent("登录后记得签到赚积分哦！");
	var a = encodeURIComponent("请先登录");
	$("#TB_window").hide().html("");
	if (b) {
		var d = tb_show("", "http://passport.fanhuan.com/loginbox/?keepThis=true&tip=" + c + "&TB_iframe=true&height=284&width=611", "")
	} else {
		tb_show("", "http://passport.fanhuan.com/loginbox/?keepThis=true&tip=" + a + "&TB_iframe=true&height=284&width=611", "")
	}
	$("#TB_window").css({
		border: "6px solid #b4b4b4",
		"border-color": "rgba(0,0,0,0.15)",
		background: "none",
		width: "640px",
		height: "308px",
		overflow: "hidden"
	});
	$("#TB_title").css({
		background: "#fff",
		border: "0 none",
		height: 0
	}).next("iframe").css({
		margin: 0,
		height: "324px"
	}).attr({
		scrolling: "no",
		frameborder: 0
	});
	$("#TB_ajaxWindowTitle").css({
		"float": "none",
		padding: 0,
		margin: 0
	});
	$("#TB_closeAjaxWindow").css({
		"float": "none",
		padding: 0,
		margin: 0
	});
	$("#TB_closeAjaxWindow_a").css({
		padding: 0,
		margin: 0
	});
	$("#TB_closeWindowButton").html("×").addClass("pop-close")
}
function LoginV2(c) {
	var b = {
		conticeMsg: "登录后记得签到赚积分哦！",
		lp: null
	};
	if (c) {
		b = $.extend(b, c)
	}
	$("#TB_window").hide().html("");
	var a = "http://passport.fanhuan.com/loginbox/?keepThis=true";
	if (b.conticeMsg) {
		a += "&tip=" + b.conticeMsg
	}
	if (b.lp) {
		a += "&lp=" + b.lp
	}
	a += "&TB_iframe=true&height=284&width=611";
	tb_show("", a, "");
	$("#TB_window").css({
		border: "6px solid #b4b4b4",
		"border-color": "rgba(0,0,0,0.15)",
		background: "none",
		width: "640px",
		height: "308px",
		overflow: "hidden"
	});
	$("#TB_title").css({
		background: "#fff",
		border: "0 none",
		height: 0
	}).next("iframe").css({
		margin: 0,
		height: "324px"
	}).attr({
		scrolling: "no",
		frameborder: 0
	});
	$("#TB_ajaxWindowTitle").css({
		"float": "none",
		padding: 0,
		margin: 0
	});
	$("#TB_closeAjaxWindow").css({
		"float": "none",
		padding: 0,
		margin: 0
	});
	$("#TB_closeAjaxWindow_a").css({
		padding: 0,
		margin: 0
	});
	$("#TB_closeWindowButton").html("×").addClass("pop-close")
}
function LogOut() {
	$.cookie("userDetial", "", {
		path: "/",
		expires: -1,
		domain: _domain
	});
	$.cookie("A9D5EMD96D5E5G", "", {
		path: "/",
		expires: -1,
		domain: _domain
	});
	if (_path.indexOf("/ajax/") != -1) {
		location = "/"
	} else {
		location.reload()
	}
}
function ShowPageNum(h, b, g, k, l, e, n) {
	var o = h % g == 0 ? h / g : Math.ceil(h / g);
	if (o < 2) {
		return ""
	}
	if (b > o) {
		b == o
	}
	var m = "";
	m += "<span class='page_area'>";
	if (e) {
		if (b == 1) {
			m += "<span class='page_start'><i></i></span>"
		} else {
			m += "<a class='page_prev' href = \"" + GetLink(l, k, b - 1) + '"><i></i></a>'
		}
	}
	var d = 5;
	var a = 3;
	if (typeof n == "number") {
		d = n;
		if (n % 2 == 1) {
			a = (d - 1) / 2
		} else {
			a = d / 2
		}
	}
	if (o <= d) {
		for (var f = 1; f <= o; f++) {
			if (f == b) {
				m += "<span class='page_cur'>" + b + "</span>"
			} else {
				m += '<a href = "' + GetLink(l, k, f) + '">' + f + "</a>"
			}
		}
	} else {
		var c = b + a;
		if (b <= a) {
			c = d
		} else {
			if (b > o - a) {
				c = o
			}
		}
		if (e) {
			if (c > d) {
				m += '<a href = "' + GetLink(l, k, 1) + '">1</a>';
				if (c > d + 1) {
					m += "<span class='page_ellipsis'>...</span>"
				}
			}
		}
		for (var f = c - d + 1; f < c + 1; f++) {
			if (f == b) {
				m += "<span class='page_cur'>" + b + "</span>"
			} else {
				m += '<a href = "' + GetLink(l, k, f) + '">' + f + "</a>"
			}
		}
		if (e) {
			if (c < o) {
				if (c < o - 1) {
					m += "<span class='page_ellipsis'>...</span>"
				}
				m += '<a href = "' + GetLink(l, k, o) + '">' + o + "</a>"
			}
		}
	}
	if (e) {
		if (b != o) {
			m += "<a class='page_next' href = \"" + GetLink(l, k, b + 1) + '"><i></i></a>'
		} else {
			m += "<span class='page_end'><i></i></span>"
		}
	}
	m += "</span>";
	return m
}
function GetLink(b, a, d) {
	var c = (b == undefined || b == "") ? a + d : "javascript:" + b + "('" + a + d + "')";
	return c
}
function CreateJScript(b, a) {
	if (document.getElementById(b) != null) {
		DeleteJScript(b)
	}
	var c = document.createElement("script");
	c.setAttribute("src", a);
	c.setAttribute("id", b);
	c.setAttribute("type", "text/javascript");
	document.getElementsByTagName("head")[0].appendChild(c);
	c = null
}
function DeleteJScript(a) {
	var c = document.getElementById(a);
	if (c != null) {
		var b = c.parentNode;
		b.removeChild(c);
		c = null
	}
}
function addFavorite(c) {
	CloseNLRAF(true);
	var a = null;
	if (c == "childreTop") {
		var a = "http://www.fanhuan.com"
	} else {
		if (c == "welcomefavorite") {
			var a = "http://www.fanhuan.com?from=welcomefavorite"
		} else {
			var a = location.href + (c == true ? "?from=topfavorite" : "")
		}
	}
	if ($.browser.msie) {
		try {
			window.external.addFavorite(a, "返还网-省钱，从返还网开始。")
		} catch (b) {
			alert("请按键盘 CTRL键 + D 收藏返还网")
		}
	} else {
		if ($.browser.mozilla) {
			try {
				window.sidebar.addPanel("返还网-网购，从返还网开始。", a, "")
			} catch (b) {
				alert("请按键盘 CTRL键 + D 收藏返还网")
			}
		} else {
			alert("请按键盘 CTRL键 + D 收藏返还网")
		}
	}
	return false
}
function CloseNLRAF(a) {
	if (a) {
		$.cookie("NLRAF", "true", {
			path: "/",
			expires: 30,
			domain: _domain
		})
	} else {
		$.cookie("NLRAF", "true", {
			path: "/",
			domain: _domain
		})
	}
	$("#afp").slideUp()
}
function SetHome(c) {
	var a = window.location.href + (c == undefined ? "" : c);
	try {
		document.body.style.behavior = "url(#default#homepage)";
		document.body.setHomePage(a)
	} catch (d) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")
			} catch (d) {
				alert("当前浏览器禁止此操作。您可点击浏览器上方的 工具，选项 手动将\n" + a + " 设为您的主页");
				return false
			}
			var b = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
			b.setCharPref("browser.startup.homepage", a)
		}
	}
	return false
}
$("a").click(function() {
	$(this).blur()
});
if (_path.indexOf("/my/") > -1 || (location.hostname == "quan.fanhuan.com" && _path.indexOf("/couponmanage") > -1)) {
	$("#nav li").eq(1).children("a").addClass("active")
} else {
	if (location.hostname == "taobao.fanhuan.com") {
		$("#nav li").eq(2).children("a").addClass("active")
	} else {
		if (location.hostname == "gou.fanhuan.com") {
			$("#nav li").eq(3).children("a").addClass("active")
		} else {
			if (location.href == "http://jiujiu.fanhuan.com/") {
				$("#nav li").eq(4).children("a").addClass("active")
			} else {
				if (location.hostname == "quan.fanhuan.com") {
					$("#nav li").eq(5).children("a").addClass("active")
				} else {
					if (location.hostname == "baoliao.fanhuan.com") {
						$("#nav li").eq(6).children("a").addClass("active")
					} else {
						if (location.href == "http://www.fanhuan.com/app") {
							$("#nav li").eq(7).children("a").addClass("active")
						} else {
							if (_path == "/") {
								$("#nav li").eq(0).children("a").addClass("active")
							}
						}
					}
				}
			}
		}
	}
}
$("#J_navApp").click(function() {
	location.href = "http://www.fanhuan.com/app"
});
$(".nav-a").mouseover(function() {
	$(this).next(".nav-dropdown").show();
	$(this).find(".i-site-arrow").css("background-position", "-64px 0px")
}).mouseout(function() {
	$(this).next(".nav-dropdown").mouseover(function() {
		$(this).show();
		$(this).prev(".nav-a").find(".i-site-arrow").css("background-position", "-64px 0px");
		if (location.hostname != "taobao.fanhuan.com") {
			$(this).prev(".nav-a").addClass("active")
		}
	}).mouseout(function() {
		$(this).hide();
		if (location.hostname != "taobao.fanhuan.com") {
			$(this).prev(".nav-a").removeClass("active")
		}
		$(this).prev(".nav-a").find(".i-site-arrow").css("background-position", "-64px -5px")
	});
	$(this).next(".nav-dropdown").hide();
	if (location.hostname != "taobao.fanhuan.com") {
		$(this).find(".i-site-arrow").css("background-position", "-64px -5px")
	}
});
$("#nav li.first_menu").hover(function() {
	$(this).addClass("nav_hover")
}, function() {
	$(this).removeClass("nav_hover")
});
$("#menu li a,#sidebar_ul li a").hover(function() {
	$(this).addClass("hover")
}, function() {
	$(this).removeClass("hover")
});
$("#menu li.first a").hover(function() {
	$(this).addClass("hover1")
}, function() {
	$(this).removeClass("hover1")
});
$("#menu li .active").unbind();
LogSeo(document.referrer);

function LogSeo(b) {
	if (b.indexOf("http://www.baidu.com/s") == -1) {
		return
	}
	if ($.cookie("advertisement") != null) {
		return
	}
	var e = GetQueryParameter(b);
	if (e == null) {
		return
	}
	var a = e.wd || e.word;
	if (a == null) {
		return
	}
	var c = e.ie || "gb2312";
	var d = "http://tj.fanhuan.com/seo/?se=baidu&k=" + encodeURIComponent(a) + "&e=" + c + "&s=" + encodeURIComponent(b.split("?")[1]);
	CreateJScript("forseo", d)
}
function LogSeoCallback(a) {
	$.cookie("advertisement", a, {
		path: "/",
		domain: _domain
	})
}
function GetQueryParameter(a) {
	if (a == null) {
		return null
	}
	var d = a.split("?");
	if (d.length != 2) {
		return null
	}
	if (d[1].length == "") {
		return null
	}
	var e = d[1].split("&");
	var c = new Object();
	for (var b = 0; b < e.length; b++) {
		nameValue = e[b].split("=");
		if (nameValue.length != 2) {
			continue
		}
		if (nameValue[0] == "" || nameValue[1] == "") {
			continue
		}
		c[nameValue[0]] = nameValue[1]
	}
	return c
}
function Search_form() {
	var a = $.trim($("#Search_Index").attr("value").toLowerCase());
	if (a) {
		var b = $("#shoplogo_ul").find("a").eq(0).attr("href");
		if (b != undefined) {
			$("#coop_form").attr("action", b);
			return true
		} else {
			return false
		}
	} else {
		return false
	}
}
var SuspendSearch = function(b) {
		var a = '<div id="pop_bg" style=""></div><div id="q_message"><p class="q_con"><img src="http://taobao.fanhuan.com/content/image/qin_bg.png" alt="亲爱的用户：你的搜索将直接跳转到淘宝网的搜索结果页面，从该搜索结果中挑选宝贝并购买，不会影响购物返还。除虚拟商品外，所有商品均可享受现金返还。" /></p><p class="q_tips">该搜索结果页面因淘宝接口变化，暂时看不到返还比例。但您可以通过点击<a href="http://taobao.fanhuan.com/">taobao.fanhuan.com</a>的搜索框顶部的“搜宝贝返还”来搜索该店铺的返还比例。</p><p class="solign_active"><label for="q_remind"><input type="checkbox" name="remind" id="q_remind" />下次不再提醒</label><a class="q_btn" target = "_blank" href=' + b + ' onclick = "return POP_close();">我知道了，跳转吧</a></p></div>';
		$("#wrapper").after(a)
	};

function POP_close() {
	if ($("#q_message :input[name=remind]")[0].checked) {
		$.cookie("q_message", "close", {
			path: "/",
			expires: 30,
			domain: _domain
		})
	} else {
		$.cookie("q_message", "close", {
			path: "/",
			domain: _domain
		})
	}
	$("#q_message,#pop_bg").remove();
	return true
}
function POP_SearchTB(a) {
	if ($.cookie("q_message")) {
		window.open(a)
	} else {
		SuspendSearch(a)
	}
}
var curDay = new Date().getDay();
var curHours = new Date().getHours();
var curMinu = new Date().getMinutes();
var online = false;
var onphone = false;
if (curHours <= 8 || curHours >= 23) {
	online = true;
	if (curHours == 8 && curMinu >= 30) {
		online = false
	}
}
if (curHours <= 8 || curHours >= 18) {
	onphone = true;
	if (curHours == 8 && curMinu >= 30) {
		onphone = false
	}
}
if (curDay == 0 || curDay == 6) {
	onphone = true
}
if (online) {
	$(".top_online .i-site-online").removeClass("i-site-online").addClass("i-site-offline");
	$(".online_work_time .online_servers").removeClass("on").addClass("off");
	$(".online_work_time .online_servers").html('<i class="c-tb">客服离线</i>：<a class="btn btn-4-lx" href = "javascript:viod(0);"><span>点击留言</span></a></div>');
	$("#footer").append('<div class="show-title-tips-1"><div class="show-title-nr" style="padding:9px 10px;font:12px SimSum;"><p>工作时间</p><p>周一至周日<span style="font:14px Tahoma;vertical-align:-1px;margin-left:5px;">8:30-23:00</span></p><b class="wai-1">◆</b><b class="nei-1">◆</b></div></div>');
	$(".online_servers .btn-4-lx").click(function() {
		window.open("http://b.qq.com/webc.htm?new=0&sid=800009200&eid=2188z8p8p8p8p808K8p8p&o=www.fanhuan.com&q=7", "_blank", "height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no");
		return false
	});
	$(".online_servers a").hover(function() {
		$(".show-title-tips-1").show()
	}, function() {
		$(".show-title-tips-1").hide()
	});
	$(".no_answer_tips .qq-kf").css({
		"background-position": "-117px -180px"
	})
}
if (onphone) {
	$(".phone_400 .i-site-tel").removeClass("i-site-tel").addClass("i-site-notel");
	$(".online_work_time .call_servers").removeClass("on").addClass("off")
}
function LayoutPop(d, a) {
	var b = $(window).height();
	var e = $(window).width();
	var c;
	if (d) {
		c = d
	} else {
		c = $(".pop").eq(0)
	}
	if (c.height() < b) {
		c.css({
			"margin-top": 0
		});
		if (a) {
			c.stop(true).animate({
				top: (b - c.height()) * 3 / 8 + "px"
			}, a)
		} else {
			c.css({
				"margin-top": 0,
				top: (b - c.height()) * 3 / 8 + "px"
			})
		}
	} else {
		c.css({
			top: 0,
			"margin-top": 0
		})
	}
}
var showDistance = 5;
var toTopHtml = '<div id="J_bottom_box" class="bottom-box">';
toTopHtml += '<div id="goTop"><a href="javascript:void(0)" title="返回顶部"></a></div></div>';
$("body").append(toTopHtml);
if ($(".inquire-into").is(":visible")) {
	$("#goTop").css({
		bottom: "260px"
	})
} else {
	$("#goTop").css({
		bottom: "20px"
	})
}
$(window).scroll(function() {
	if ($(window).scrollTop() >= showDistance) {
		$("#J_bottom_box").fadeIn("slow")
	} else {
		$("#J_bottom_box").fadeOut("slow")
	}
});
$("#goTop a").click(function(a) {
	a.preventDefault();
	$("html").animate({
		scrollTop: 0
	}, "fast");
	$("body").animate({
		scrollTop: 0
	}, "fast")
});
$(function() {
	$("#mbAPP").mouseover(function() {
		$(".nav_app_ewm").show()
	});
	$("#mbAPP").mouseleave(function() {
		$(".nav_app_ewm").hide()
	})
});

function MixOperation(a) {
	if (a == null) {
		return
	}
	if (a.unread_msg > 0 && $("#NewMessageCount").length > 0) {
		$("#NewMessageCount").text("(" + a.unread_msg + ")").parents("a").addClass("newmessage")
	} else {
		$("#NewMessageCount").text("(" + 0 + ")").parents("a").removeClass("newmessage")
	}
	if (a.email_postfix.toLowerCase().indexOf("yahoo.") == 0 && CurrentUser.UserName != undefined) {
		if ($.cookie("yahoo_jump") != CurrentUser.UserName) {
			ShowYahooNotice()
		}
		$.cookie("yahoo_jump", CurrentUser.UserName, {
			path: "/",
			domain: _domain
		})
	}
	SetServerLink(a.server_link)
}
function ShowYahooNotice() {
	location.href = "http://my.fanhuan.com/my/changeemail"
}
function SetServerLink(a) {
	if (a == "") {
		return
	}
	$(".online_servers, .i-site-online, .i-site-offline, #help_service").removeAttr("onclick").attr("href", a).attr("target", "_blank")
}(function() {
	var a = "http://my.fanhuan.com/ajax/GetUserInfo?callback=MixOperation";
	if (CurrentUser.UserId) {
		CreateJScript("newmessage", a + "&_=" + Math.random())
	} else {
		CreateJScript("", a)
	}
})();
$(".navfirst").live("click", function() {
	$.cookie("product_cur_catename", "", {
		path: "/",
		expires: -1,
		domain: _domain
	})
});
var btns = $(".J_login_btn");
btns.on("click", function() {
	if (!CurrentUser.UserId) {
		Login();
		return false
	}
});
$(function() {
	var f, d, h, b, g, k, c, a, e;
	if (CurrentUser.UserId) {
		$.getJSON("http://www.fanhuan.com/ajax/GetNowDateTime/?callback=?", {}, function(l) {
			d = l.Date.split("-");
			h = d[0];
			b = d[1];
			g = l.Date;
			$("#J_date").val(g)
		});
		$.getJSON("http://www.fanhuan.com/ajax/GetSignStatus/?callback=?&s=" + Math.random(10) + "", {}, function(l) {
			f = l.Code
		})
	}(function() {
		var q = {
			showDateSign: function(J, D, C) {
				var G = this;
				var J = document.getElementById(J);
				var F = 0;
				var C = Number(C);
				if (C <= 12 && C >= 1) {
					if (C <= 7) {
						if (C == 2) {
							if ((D % 4 == 0 && D % 100 != 0) || D % 400 == 0) {
								F = 29
							} else {
								F = 28
							}
						} else {
							if (C % 2 == 0) {
								F = 30
							} else {
								F = 31
							}
						}
					} else {
						if (C % 2 == 0) {
							F = 31
						} else {
							F = 30
						}
					}
				} else {
					alert("操作错误！");
					return
				}
				var M = 0;
				var L = new Date();
				L.setYear(D);
				L.setMonth(C - 1);
				L.setDate(1);
				M = L.getDay();
				if (M == 0) {
					M = 7
				}
				C = C < 10 ? "0" + C : C;
				var N = '<div class="tit clearfix">';
				N += '<div class="action">';
				N += '<span id="nowDate">' + D + "-" + C + "</span>";
				N += '<b class="square-l" id="prevMonth"></b><b class="square-r" id="nextMonth"></b>';
				N += "</div>";
				N += "</div>";
				N += '<div class="con">';
				N += '<table cellpadding="0" cellspacing="0" border="0">';
				N += '<thead><tr><th width="35">一</th><th width="35">二</th><th width="35">三</th><th width="35">四</th><th width="35">五</th><th width="35">六</th><th>日</th></tr></thead>';
				N += '<tbody id="J_days">';
				for (var I = 0; I < 6; I++) {
					N += '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td class="last"></td></tr>'
				}
				N += "</tbody>";
				N += "</table>";
				N += "</div>";
				if (J) {
					J.innerHTML = N;
					var E = J.getElementsByTagName("td");
					for (var I = 1; I <= F; I++) {
						E[M - 1].innerHTML = I;
						M++
					}
					var K = J.getElementsByTagName("tr");
					if (K[K.length - 1].getElementsByTagName("td")[0].innerHTML == "") {
						K[K.length - 1].style.display = "none"
					}
					var H = document.getElementById("prevMonth");
					var O = document.getElementById("nextMonth");
					H.onclick = function() {
						G.actionDate(1);
						var P = C - 1;
						$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&year=" + D + "&month=" + P + "&s=" + Math.random(10) + "", {}, function(Q) {
							var S = Q.Days;
							var R = $("#J_days").find("td");
							for (var U = 0, T = S.length; U < T; U++) {
								for (var W = 0, T = 42; W < T; W++) {
									var V = R[W].innerHTML;
									if (S[U] && V) {
										if (S[U] == V) {
											$(R[W]).addClass("yet")
										}
									}
								}
							}
						})
					};
					O.onclick = function() {
						G.actionDate(0);
						var P = +C + 1;
						$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&year=" + D + "&month=" + P + "&s=" + Math.random(10) + "", {}, function(Q) {
							var S = Q.Days;
							var R = $("#J_days").find("td");
							for (var U = 0, T = S.length; U < T; U++) {
								for (var W = 0, T = 42; W < T; W++) {
									if (R[W].innerHTML) {
										var V = R[W].innerHTML;
										if (S[U] && V) {
											if (S[U] == V) {
												$(R[W]).addClass("yet")
											}
										}
									}
								}
							}
						})
					}
				}
			},
			actionDate: function(C) {
				var E = document.getElementById("nowDate").innerHTML.split("-");
				var D = parseInt(E[0]);
				var F = Number(E[1]);
				if (C == 1) {
					if (F == 1) {
						this.showDateSign("signDate", D - 1, 12);
						t(D - 1, 12)
					} else {
						this.showDateSign("signDate", D, F - 1);
						t(D, F - 1)
					}
				} else {
					if (F == 12) {
						this.showDateSign("signDate", D + 1, 1);
						t(D + 1, 1)
					} else {
						this.showDateSign("signDate", D, F + 1);
						t(D, F + 1)
					}
				}
			},
			showDateColor: function(O, F, I) {
				var O = document.getElementById(O);
				if (O) {
					var E = O.getElementsByTagName("td");
					var J = document.getElementById("nowDate").innerHTML.split("-");
					var D = Number(J[0]);
					var C = Number(J[1]);
					var K = [];
					var P = I.split("-");
					var H = Number(P[0]);
					var Q = Number(P[1]);
					var G = Number(P[2]);
					for (var L = 0; L < E.length; L++) {
						if (E[L].innerHTML != "") {
							K.push(L)
						}
					}
					if (D == H && C == Q) {
						if (E[K[G - 1]].className != "") {
							E[K[G - 1]].className += " cur"
						} else {
							E[K[G - 1]].className = "cur"
						}
					}
					for (var L = 0, N = K.length; L <= N; L++) {
						for (var M = 0; M < F.length; M++) {
							if (L == F[M]) {
								if (E[K[L - 1]].className != "") {
									E[K[L - 1]].className += " yet"
								} else {
									E[K[L - 1]].className = "yet"
								}
							}
						}
					}
				}
			}
		};
		var n = timeSignRecode_load_out = null;
		var s, z, l;
		var v = true;
		var p = false;
		$("#J_sign_btn").live("mouseover", function() {
			if (!p) {
				clearTimeout(n);
				$("#J_nav_sign .nav_sign_tip").show()
			}
		}).live("mousemove", function() {
			if (!p) {
				clearTimeout(n);
				$("#J_nav_sign .nav_sign_tip").show()
			}
		}).live("mouseout", function() {
			n = setTimeout(function() {
				$("#J_nav_sign .nav_sign_tip").hide()
			}, 300)
		}).bind("click", function() {
			$.getJSON("http://www.fanhuan.com/ajax/SignIn/?callback=?&s=" + Math.random(10) + "", {}, function(C) {
				var D = C.Code;
				$("#J_signCode").val(D)
			});
			m();
			return false
		});

		function r(C) {}
		$("#J_nav_sign .nav_sign_tip").live("mouseover", function(C) {
			clearTimeout(n)
		}).live("mouseout", function() {
			n = setTimeout(function() {
				$("#J_nav_sign .nav_sign_tip").hide()
			}, 300)
		});
		$("#J_nav_sign .sign-count a").live("click", function() {
			$("#J_nav_sign .nav_sign_tip").hide()
		});
		var o = $("#J_nav_sign .nav_sign_tip");
		if (CurrentUser.UserId == undefined) {
			o.append('<div class="no-login">每天最多可赚<strong>3</strong>积分<br /><a href="javascript:Login()">登录</a>后才能签到！</div>')
		} else {
			if (CurrentUser.UserId && p == false) {
				var A = setInterval(function() {
					if (f) {
						clearInterval(A);
						if (f == 300) {
							$("#J_sign_btn").unbind("click");
							p = false;
							var E = $("#J_sign_btn");
							E.attr("class", "btn ico-yes");
							E.find("em").html("今日已签到").css("color", "#ff4400");
							$("#J_sign_btn").one("mouseover", function() {
								$("#J_sign_box").html("努力加载中...");
								var G = new Date().getFullYear(),
									F = new Date().getMonth() + 1;
								$.getJSON("http://www.fanhuan.com/ajax/GetSignInDateAdData/?callback=?&s=" + Math.random(10) + "", {}, function(H) {
									$("#J_day").val(H.Day);
									$("#J_week").val(H.WeekCN);
									$("#J_yi").val(H.Yi);
									$("#J_yi_url").val(H.YiUrl);
									$("#J_ji").val(H.Ji);
									var J = H.DateTime.split("-");
									var I = e;
									$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&s=" + Math.random(10) + "&action=all&year=" + h + "&month=" + b + "", {}, function(K) {
										if (K.Code == 100) {
											k = K.Days;
											c = K.SuccessionNum;
											a = K.TodayAddNum;
											e = K.TomorrowAddNum;
											$("#J_nav_sign .nav_sign_tip").find(".no-login").remove();
											var M = $("#J_day").val(),
												L = $("#J_week").val(),
												P = $("#J_yi").val(),
												O = $("#J_yi_url").val(),
												Q = $("#J_ji").val();
											var N = '<div class="sign-recode clearfix">';
											N += '<div class="tips"><span class="success-tip"><span class="ico-success"></span>签到成功！</span>连续签到<em class="f-bold">' + c + '</em>天(<em class="s-red"><em class="f-bold">+' + a + '</em> 积分</em>)，明天签到可得<em class="s-orange"><em class="f-bold">' + e + "</em>积分</em></div>";
											N += '<div id="signDate"></div>';
											N += '<div class="sign-info">';
											N += '<a href="http://www.fanhuan.com/app" class="C-Apptz"></a>';
											N += "</div></div></div>";
											$("#J_nav_sign").append('<div class="nav_sign_pop" style="display:none;">签到成功！<strong>积分<b>+' + s + "</b></strong></div>");
											$("#J_sign_box").html(N);
											$("#J_nav_sign .nav_sign_tip").css({
												width: "435px"
											});
											p = false;
											n = setTimeout(function() {});
											u(CurrentUser.UserId);
											setTimeout(function() {
												var T = k;
												var S = $("#J_days").find("td");
												for (var V = 0, U = T.length; V < U; V++) {
													for (var R = 0, U = 42; R < U; R++) {
														if ($(S[R]).html()) {
															var W = S[R].innerHTML;
															if (T[V] && W) {
																if (T[V] == W) {
																	$(S[R]).addClass("yet")
																}
															}
														}
													}
												}
											}, 500)
										}
									})
								})
							})
						} else {
							var D = new Date().getFullYear(),
								C = new Date().getMonth() + 1;
							$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&action=all&year=" + D + "&month=" + C + "&s=" + Math.random(10) + "", {}, function(F) {
								var G = F.SuccessionNum;
								o.append('<div class="no-login" style="width:150px;">您已连续签到<strong>' + G + "</strong>天<br />连续签到每天可赚<strong>3</strong>积分！</div>")
							})
						}
					}
				}, 100);
				u(CurrentUser.UserId)
			}
		}
		function u(D) {
			var C = setInterval(function() {
				var E = $("#J_date").val();
				if (E) {
					clearInterval(C);
					B({
						Error: null,
						Result: {
							IsTodaySign: false,
							ContinueSign: 0,
							TotalSignGold: 0,
							CurSignGold: 0,
							NextSignGold: 0,
							SignDate: null,
							CurDay: E
						}
					})
				}
			}, 100);
			timeSignRecode_load_out = setTimeout(function() {
				v = false;
				$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign .nav_sign_tip").find(".sign-fail").remove()
			}, 1000)
		}
		function B(I) {
			if (I.Error != null) {
				if (v) {
					clearTimeout(timeSignRecode_load_out)
				} else {
					$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
					$("#J_nav_sign .nav_sign_tip").find(".sign-fail").remove();
					$("#J_nav_sign .nav_sign_tip").append('<div class="sign-fail">载入失败，<a href="javascript:void(0)" onclick="GetSignInfo(' + CurrentUser.UserId + ');">刷新！</a></div>');
					v = true
				}
				return
			}
			var E, C, D, K, G, M;
			z = I.Result.IsTodaySign;
			G = I.Result.ContinueSign;
			K = I.Result.TotalSignGold;
			s = I.Result.CurSignGold;
			M = I.Result.NextSignGold;
			l = I.Result.CurDay;
			var L = l.split("-");
			var F = Number(L[0]);
			var O = Number(L[1]);
			var J = Number(L[2]);
			var N = I.Result.SignDate;
			if (N == null || N.length <= 0) {
				E = F;
				C = O;
				D = []
			} else {
				E = N[0].Date.substr(0, 4);
				C = N[0].Date.substr(5, 2);
				var P = [];
				for (var H = 0; H < N.length; H++) {
					P.push(N[H].Date.substr(8, 2))
				}
				D = P
			}
			if (z) {
				$("#J_nav_sign .nav_sign_tip").find(".sign-info h3").remove();
				$("#J_nav_sign .nav_sign_tip").find(".sign-info .sign-count").remove();
				$("#J_nav_sign .nav_sign_action").html('<a class="ico-jrqd i-jrqd-btn2" id="J_sign_btn" href="javascript:void(0)"><span>连签' + G + "天</span></a>");
				$("#J_nav_sign .nav_sign_tip").find(".sign-info .tip").before('<p class="sign-success"><i class="ico-jrqd i-jrqd-success"></i>今天+' + s + "</p><h3>明天签到可得<strong>" + M + '</strong>积分</h3><p class="sign-count">累计签到积分：<a href="http://my.fanhuan.com/my/redbag/" target="_blank">' + K + "</a></p>");
				$("#J_sign_btn").unbind("click")
			} else {}
			q.showDateSign("signDate", E, C);
			q.showDateColor("signDate", D, l);
			if (v) {
				clearTimeout(timeSignRecode_load_out)
			} else {
				clearTimeout(timeSignRecode_load_out);
				$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign .nav_sign_tip").find(".sign-recode").show();
				v = true
			}
		}
		function m() {
			if (CurrentUser.UserId == undefined) {
				Login()
			} else {
				if (!z) {
					timeSignRecode_load_out = setTimeout(function() {
						v = false;
						$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
						$("#J_nav_sign .nav_sign_tip").find(".sign-fail").remove()
					}, 1000);
					setTimeout(function() {
						var E = $("#J_signCode").val();
						if (E == 201) {
							$("#J_nav_sign").find(".nav_sign_tip").hide();
							var F = "";
							F += '<div class="pop pop-bindphone" style="display:block;">';
							F += '<div class="inner"><a href="javascript:void(0)" class="pop-close">×</a>';
							F += "<p>为了您的账户安全，请先<strong>绑定手机</strong>再来签到哦~</p>";
							F += '<div class="btn-outer"><a href="http://my.fanhuan.com/my/bindphone" class="btn btn-1" target="_blank"><span>去绑定</span></a></div>';
							F += "</div></div>";
							$("body").append(F);
							$("body").append('<div id="TB_overlay" class="TB_overlayBG" style="opacity:0.6; filter:alpha(opacity=60);"></div>');
							$(".pop .pop-close").live("click", function() {
								$("#TB_overlay").remove();
								$(".pop").remove()
							});
							$(".pop .btn").live("click", function() {
								$("#TB_overlay").remove();
								$(".pop").remove()
							});
							if (v) {
								clearTimeout(timeSignRecode_load_out)
							} else {
								$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
								$("#J_nav_sign .nav_sign_tip").find(".sign-recode").show();
								v = true
							}
						} else {
							if (E == 202) {
								$("#J_nav_sign").find(".nav_sign_tip").hide();
								var F = "";
								F += '<div class="pop pop-bindphone" style="display:block;">';
								F += '<div class="inner"><a href="javascript:void(0)" class="pop-close">×</a>';
								F += "<p>您至少需要获得一笔购物返还，才能享有“<strong>有奖签到</strong>”特权哦~</p>";
								F += '<div class="btn-outer"><a href="http://www.fanhuan.com/" class="btn btn-1" target="_blank"><span>去购物，拿返还</span></a></div>';
								F += "</div></div>";
								$("body").append(F);
								$("body").append('<div id="TB_overlay" class="TB_overlayBG" style="opacity:0.6; filter:alpha(opacity=60);"></div>');
								$(".pop .pop-close").live("click", function() {
									$("#TB_overlay").remove();
									$(".pop").remove()
								});
								$(".pop .btn").live("click", function() {
									$("#TB_overlay").remove();
									$(".pop").remove()
								});
								if (v) {
									clearTimeout(timeSignRecode_load_out)
								} else {
									$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
									$("#J_nav_sign .nav_sign_tip").find(".sign-recode").show();
									v = true
								}
							} else {
								if (E == 100 || E == 300) {
									p = true;
									var G = $("#J_sign_btn");
									G.attr("class", "btn ico-yes");
									G.find("em").html("今日已签到").css("color", "#ff4400");
									var C = new Date().getFullYear(),
										D = new Date().getMonth() + 1;
									$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&action=all&year=" + h + "&month=" + b + "", {}, function(H) {
										var J = H.Days;
										$("#J_nav_sign").find(".no-login").remove();
										var I = '<div class="sign-recode clearfix">';
										I += '<div class="tips"><span class="success-tip"><span class="ico-success"></span>签到成功！</span>连续签到<em class="f-bold">' + H.SuccessionNum + '</em>天(<em class="s-red"><em class="f-bold">+' + H.TodayAddNum + '</em> 积分</em>)，明天签到可得<em class="s-orange"><em class="f-bold">' + H.TomorrowAddNum + "</em>积分</em></div>";
										$.getJSON("http://www.fanhuan.com/ajax/GetSignInDateAdData/?callback=?&s=" + Math.random(10) + "", {}, function(K) {
											var M = K.Day,
												L = K.WeekCN,
												O = K.Yi,
												N = K.YiUrl,
												P = K.Ji;
											I += '<div id="signDate"></div>';
											I += '<div class="sign-info">';
											I += '<a href="http://www.fanhuan.com/app" class="C-Apptz"></a>';
											I += "</div></div></div>"
										});
										$("#J_nav_sign").append('<div class="nav_sign_pop" style="display:none;">签到成功！<strong>积分<b>+' + s + "</b></strong></div>");
										setTimeout(function() {
											$("#J_sign_box").html(I);
											$("#J_nav_sign .nav_sign_tip").css({
												width: "435px"
											}).slideDown("slow", function() {
												p = false;
												n = setTimeout(function() {
													$("#J_nav_sign .nav_sign_tip").hide()
												}, 2000)
											});
											u(CurrentUser.UserId)
										}, 500);
										setTimeout(function() {
											var P = new Date().getFullYear(),
												K = new Date().getMonth() + 1;
											var N = $("#J_days").find("td");
											for (var L = 0, M = J.length; L < M; L++) {
												for (var Q = 0, M = 42; Q < M; Q++) {
													var O = N[Q].innerHTML;
													if (J[L] && O) {
														if (J[L] == O) {
															$(N[Q]).addClass("yet")
														}
													}
												}
											}
										}, 1500);
										return false
									})
								} else {
									if (E == 300) {
										var G = $("#J_sign_btn");
										G.addClass("ico-yes");
										G.find("em").html("今日已签到").css("color", "#ff4400");
										clearTimeout(timeSignRecode_load_out);
										u(CurrentUser.UserId);
										$("#J_sign_btn").unbind("click");
										return false
									}
								}
							}
						}
					}, 500)
				}
				return false
			}
		}
		function x(C) {}
		function t(D, C) {
			if (CurrentUser.UserId == undefined) {
				Login()
			} else {
				timeSignRecode_load_out = setTimeout(function() {
					v = false;
					$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove()
				}, 1000)
			}
		}
		function w(C) {
			if (C.Error != null) {
				if (v) {
					clearTimeout(timeSignRecode_load_out)
				} else {
					$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
					$("#J_nav_sign .nav_sign_tip").append('<div class="sign-fail">获取日历失败，<a href="javascript:void(0)" onclick="GetSignCalender();">请重试！</a></div>');
					v = true
				}
				return
			}
			var I, F, D;
			var E = C.Result.List;
			if (E == null || E.length <= 0) {
				I = new Date().getFullYear();
				F = new Date().getMonth() + 1;
				D = "[]"
			} else {
				I = E[0].Date.substr(0, 4);
				F = E[0].Date.substr(5, 2);
				var G = [];
				for (var H = 0; H < E.length; H++) {
					G.push(E[H].Date.substr(8, 2))
				}
				D = G
			}
			q.showDateColor("signDate", D, l);
			if (v) {
				clearTimeout(timeSignRecode_load_out)
			} else {
				$("#J_nav_sign .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign .nav_sign_tip").find(".sign-recode").show();
				v = true
			}
		}
		function y(C, D, E) {
			$.ajax({
				type: "get",
				async: true,
				url: C,
				dataType: "jsonp",
				jsonp: D,
				jsonpCallback: E,
				error: function() {}
			})
		}
	})();
	(function() {
		var q = {
			showDateSign: function(J, D, C) {
				var G = this;
				var J = document.getElementById(J);
				var F = 0;
				var C = Number(C);
				if (C <= 12 && C >= 1) {
					if (C <= 7) {
						if (C == 2) {
							if ((D % 4 == 0 && D % 100 != 0) || D % 400 == 0) {
								F = 29
							} else {
								F = 28
							}
						} else {
							if (C % 2 == 0) {
								F = 30
							} else {
								F = 31
							}
						}
					} else {
						if (C % 2 == 0) {
							F = 31
						} else {
							F = 30
						}
					}
				} else {
					alert("操作错误！");
					return
				}
				var M = 0;
				var L = new Date();
				L.setYear(D);
				L.setMonth(C - 1);
				L.setDate(1);
				M = L.getDay();
				if (M == 0) {
					M = 7
				}
				C = C < 10 ? "0" + C : C;
				var N = '<div class="tit clearfix">';
				N += '<div class="action">';
				N += '<span id="nowDate1">' + D + "-" + C + "</span>";
				N += '<b class="square-l" id="prevMonth1"></b><b class="square-r" id="nextMonth1"></b>';
				N += "</div>";
				N += "</div>";
				N += '<div class="con">';
				N += '<table cellpadding="0" cellspacing="0" border="0">';
				N += '<thead><tr><th width="35">一</th><th width="35">二</th><th width="35">三</th><th width="35">四</th><th width="35">五</th><th width="35">六</th><th>日</th></tr></thead>';
				N += '<tbody id="J_days1">';
				for (var I = 0; I < 6; I++) {
					N += '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td class="last"></td></tr>'
				}
				N += "</tbody>";
				N += "</table>";
				N += "</div>";
				if (J) {
					J.innerHTML = N;
					var E = J.getElementsByTagName("td");
					for (var I = 1; I <= F; I++) {
						E[M - 1].innerHTML = I;
						M++
					}
					var K = J.getElementsByTagName("tr");
					if (K[K.length - 1].getElementsByTagName("td")[0].innerHTML == "") {
						K[K.length - 1].style.display = "none"
					}
					var H = document.getElementById("prevMonth1");
					var O = document.getElementById("nextMonth1");
					$(H).live("click", function() {
						G.actionDate(1);
						var P = C - 1;
						$.ajax({
							type: "get",
							async: true,
							url: "http://www.fanhuan.com/ajax/GetSignData/?year=" + D + "&month=" + P + "&s=" + Math.random(10) + "",
							dataType: "jsonp",
							jsonp: "callback",
							jsonpCallback: "callback",
							success: function(Q) {
								var S = Q.Days;
								var R = $("#J_days1").find("td");
								for (var U = 0, T = S.length; U < T; U++) {
									for (var W = 0, T = 42; W < T; W++) {
										var V = R[W].innerHTML;
										if (S[U] && V) {
											if (S[U] == V) {
												$(R[W]).addClass("yet")
											}
										}
									}
								}
							},
							error: function() {}
						})
					});
					$(O).live("click", function() {
						G.actionDate(0);
						var P = +C + 1;
						$.ajax({
							type: "get",
							async: true,
							url: "http://www.fanhuan.com/ajax/GetSignData/?year=" + D + "&month=" + P + "&s=" + Math.random(10) + "",
							dataType: "jsonp",
							jsonp: "callback",
							jsonpCallback: "callback",
							success: function(Q) {
								var S = Q.Days;
								var R = $("#J_days1").find("td");
								for (var U = 0, T = S.length; U < T; U++) {
									for (var W = 0, T = 42; W < T; W++) {
										var V = R[W].innerHTML;
										if (S[U] && V) {
											if (S[U] == V) {
												$(R[W]).addClass("yet")
											}
										}
									}
								}
							},
							error: function() {}
						})
					})
				}
			},
			actionDate: function(C) {
				var E = document.getElementById("nowDate1").innerHTML.split("-");
				var D = parseInt(E[0]);
				var F = Number(E[1]);
				if (C == 1) {
					if (F == 1) {
						this.showDateSign("signDate1", D - 1, 12);
						t(D - 1, 12)
					} else {
						this.showDateSign("signDate1", D, F - 1);
						t(D, F - 1)
					}
				} else {
					if (F == 12) {
						this.showDateSign("signDate1", D + 1, 1);
						t(D + 1, 1)
					} else {
						this.showDateSign("signDate1", D, F + 1);
						t(D, F + 1)
					}
				}
			},
			showDateColor: function(O, F, I) {
				var O = document.getElementById(O);
				if (O) {
					var E = O.getElementsByTagName("td");
					var J = document.getElementById("nowDate1").innerHTML.split("-");
					var D = Number(J[0]);
					var C = Number(J[1]);
					var K = [];
					var P = I.split("-");
					var H = Number(P[0]);
					var Q = Number(P[1]);
					var G = Number(P[2]);
					for (var L = 0; L < E.length; L++) {
						if (E[L].innerHTML != "") {
							K.push(L)
						}
					}
					if (D == H && C == Q) {
						if (E[K[G - 1]].className != "") {
							E[K[G - 1]].className += " cur"
						} else {
							E[K[G - 1]].className = "cur"
						}
					}
					for (var L = 0, N = K.length; L <= N; L++) {
						for (var M = 0; M < F.length; M++) {
							if (L == F[M]) {
								if (E[K[L - 1]].className != "") {
									E[K[L - 1]].className += " yet"
								} else {
									E[K[L - 1]].className = "yet"
								}
							}
						}
					}
				}
			}
		};
		var n = timeSignRecode_load_out = null;
		var s, z, l;
		var v = true;
		var p = false;
		$("#J_sign_btn1").live("mouseover", function() {
			if (!p) {
				clearTimeout(n);
				$("#J_nav_sign1 .nav_sign_tip").show()
			}
		}).live("mousemove", function() {
			if (!p) {
				clearTimeout(n);
				$("#J_nav_sign1 .nav_sign_tip").show()
			}
		}).live("mouseout", function() {
			n = setTimeout(function() {
				$("#J_nav_sign1 .nav_sign_tip").hide()
			}, 300)
		}).bind("click", function() {
			$.ajax({
				type: "get",
				async: true,
				url: "http://www.fanhuan.com/ajax/SignIn/?s=" + Math.random(10) + "",
				dataType: "jsonp",
				jsonp: "callback",
				jsonpCallback: "callback",
				success: function(C) {
					var D = C.Code;
					$("#J_signCode").val(D)
				},
				error: function() {}
			});
			m();
			return false
		});

		function r(C) {}
		$("#J_nav_sign1 .nav_sign_tip").live("mouseover", function(C) {
			clearTimeout(n)
		}).live("mouseout", function() {
			n = setTimeout(function() {
				$("#J_nav_sign1 .nav_sign_tip").hide()
			}, 300)
		});
		$("#J_nav_sign1 .sign-count a").live("click", function() {
			$("#J_nav_sign1 .nav_sign_tip").hide()
		});
		var o = $("#J_nav_sign1 .nav_sign_tip");
		if (CurrentUser.UserId == undefined) {
			$("#J_sign_btn1").show();
			o.append('<div class="no-login">每天最多可赚<strong>3</strong>积分<br /><a href="javascript:Login()">登录</a>后才能签到！</div>')
		} else {
			if (CurrentUser.UserId && p == false) {
				var A = setInterval(function() {
					if (f) {
						clearInterval(A);
						if (f == 300) {
							$("#J_sign_btn1").unbind("click");
							p = true;
							var E = $("#J_sign_btn1");
							E.attr("class", "btn ico-yes");
							E.find("em").html("今日已签到").css("color", "#ff4400");
							E.one("mouseover", function() {
								$(this).next(".nav_sign_tip").css({
									top: -195,
									width: 100,
									height: 230,
									display: "block"
								});
								$(this).next(".nav_sign_tip").find(".i-jrqd-arrow").css({
									top: 215
								});
								$("#J_sign_box1").html("努力加载中...");
								var G = new Date().getFullYear(),
									F = new Date().getMonth() + 1;
								$.getJSON("http://www.fanhuan.com/ajax/GetSignInDateAdData/?callback=?&s=" + Math.random(10) + "", {}, function(H) {
									$("#J_day").val(H.Day);
									$("#J_week").val(H.WeekCN);
									$("#J_yi").val(H.Yi);
									$("#J_yi_url").val(H.YiUrl);
									$("#J_ji").val(H.Ji);
									var I = H.DateTime.split("-")
								});
								$.ajax({
									type: "get",
									async: true,
									url: "http://www.fanhuan.com/ajax/GetSignData/?action=all&year=" + G + "&month=" + F + "&s=" + Math.random(10) + "",
									dataType: "jsonp",
									jsonp: "callback",
									jsonpCallback: "callback",
									success: function(H) {
										k = H.Days;
										c = H.SuccessionNum;
										a = H.TodayAddNum;
										e = H.TomorrowAddNum;
										$("#J_nav_sign1 .nav_sign_tip").find(".no-login").remove();
										var J = $("#J_day").val(),
											I = $("#J_week").val(),
											M = $("#J_yi").val(),
											L = $("#J_yi_url").val(),
											N = $("#J_ji").val();
										var K = '<div class="sign-recode clearfix">';
										K += '<div class="tips"><span class="success-tip"><span class="ico-success"></span>签到成功！</span>连续签到<em class="f-bold">' + H.SuccessionNum + '</em>天(<em class="s-red"><em class="f-bold">+' + H.TodayAddNum + '</em> 积分</em>)，明天签到可得<em class="s-orange"><em class="f-bold">' + H.TomorrowAddNum + "</em>积分</em></div>";
										K += '<div id="signDate1"></div>';
										K += '<div class="sign-info">';
										K += '<a href="http://www.fanhuan.com/app" class="C-Apptz"></a>';
										K += "</div></div></div>";
										$("#J_nav_sign1").append('<div class="nav_sign_pop" style="display:none;">签到成功！<strong>积分<b>+' + s + "</b></strong></div>");
										$("#J_sign_box1").html(K);
										$("#J_nav_sign1 .nav_sign_tip").css({
											width: "435px"
										});
										p = false;
										u(CurrentUser.UserId);
										setTimeout(function() {
											var Q = k;
											var P = $("#J_days1").find("td");
											for (var S = 0, R = Q.length; S < R; S++) {
												for (var O = 0, R = 42; O < R; O++) {
													if ($(P[O]).html()) {
														var T = P[O].innerHTML;
														if (Q[S] && T) {
															if (Q[S] == T) {
																$(P[O]).addClass("yet")
															}
														}
													}
												}
											}
										}, 500)
									},
									error: function() {}
								})
							})
						} else {
							var D = new Date().getFullYear(),
								C = new Date().getMonth() + 1;
							$.ajax({
								type: "get",
								async: true,
								url: "http://www.fanhuan.com/ajax/GetSignData/?action=all&year=" + D + "&month=" + C + "&s=" + Math.random(10) + "",
								dataType: "jsonp",
								jsonp: "callback",
								jsonpCallback: "callback",
								success: function(F) {
									var G = F.SuccessionNum;
									o.append('<div class="no-login" style="width:150px;">您已连续签到<strong>' + G + "</strong>天<br />连续签到每天可赚<strong>3</strong>积分！</div>")
								},
								error: function() {}
							})
						}
					}
				}, 100);
				u(CurrentUser.UserId)
			}
		}
		function u(D) {
			var C = setInterval(function() {
				var E = $("#J_date").val();
				if (E) {
					clearInterval(C);
					B({
						Error: null,
						Result: {
							IsTodaySign: false,
							ContinueSign: 0,
							TotalSignGold: 0,
							CurSignGold: 0,
							NextSignGold: 0,
							SignDate: null,
							CurDay: E
						}
					})
				}
			}, 100);
			timeSignRecode_load_out = setTimeout(function() {
				v = false;
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-fail").remove()
			}, 1000)
		}
		function B(I) {
			if (I.Error != null) {
				if (v) {
					clearTimeout(timeSignRecode_load_out)
				} else {
					$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
					$("#J_nav_sign1 .nav_sign_tip").find(".sign-fail").remove();
					$("#J_nav_sign1 .nav_sign_tip").append('<div class="sign-fail">载入失败，<a href="javascript:void(0)" onclick="GetSignInfo(' + CurrentUser.UserId + ');">刷新！</a></div>');
					v = true
				}
				return
			}
			var E, C, D, K, G, M;
			z = I.Result.IsTodaySign;
			G = I.Result.ContinueSign;
			K = I.Result.TotalSignGold;
			s = I.Result.CurSignGold;
			M = I.Result.NextSignGold;
			l = I.Result.CurDay;
			var L = l.split("-");
			var F = Number(L[0]);
			var O = Number(L[1]);
			var J = Number(L[2]);
			var N = I.Result.SignDate;
			if (N == null || N.length <= 0) {
				E = F;
				C = O;
				D = []
			} else {
				E = N[0].Date.substr(0, 4);
				C = N[0].Date.substr(5, 2);
				var P = [];
				for (var H = 0; H < N.length; H++) {
					P.push(N[H].Date.substr(8, 2))
				}
				D = P
			}
			if (z) {
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-info h3").remove();
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-info .sign-count").remove();
				$("#J_nav_sign1 .nav_sign_action").html('<a class="ico-jrqd i-jrqd-btn2" id="J_sign_btn1" href="javascript:void(0)"><span>连签' + G + "天</span></a>");
				$("#J_sign_btn1").show();
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-info .tip").before('<p class="sign-success"><i class="ico-jrqd i-jrqd-success"></i>今天+' + s + "</p><h3>明天签到可得<strong>" + M + '</strong>积分</h3><p class="sign-count">累计签到积分：<a href="http://my.fanhuan.com/my/redbag/" target="_blank">' + K + "</a></p>");
				$("#J_sign_btn1").unbind("click")
			} else {
				$("#J_sign_btn1").show()
			}
			q.showDateSign("signDate1", E, C);
			q.showDateColor("signDate1", D, l);
			if (v) {
				clearTimeout(timeSignRecode_load_out)
			} else {
				clearTimeout(timeSignRecode_load_out);
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-recode").show();
				v = true
			}
		}
		function m() {
			if (CurrentUser.UserId == undefined) {
				Login()
			} else {
				if (!z) {
					timeSignRecode_load_out = setTimeout(function() {
						v = false;
						$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
						$("#J_nav_sign1 .nav_sign_tip").find(".sign-fail").remove()
					}, 1000);
					setTimeout(function() {
						var E = $("#J_signCode").val();
						if (E == 201) {
							$("#J_nav_sign1").find(".nav_sign_tip").hide();
							var F = "";
							F += '<div class="pop pop-bindphone" style="display:block;">';
							F += '<div class="inner"><a href="javascript:void(0)" class="pop-close">×</a>';
							F += "<p>为了您的账户安全，请先<strong>绑定手机</strong>再来签到哦~</p>";
							F += '<div class="btn-outer"><a href="http://my.fanhuan.com/my/bindphone" class="btn btn-1" target="_blank"><span>去绑定</span></a></div>';
							F += "</div></div>";
							$("body").append(F);
							$("body").append('<div id="TB_overlay" class="TB_overlayBG" style="opacity:0.6; filter:alpha(opacity=60);"></div>');
							$(".pop .pop-close").live("click", function() {
								$("#TB_overlay").remove();
								$(".pop").remove()
							});
							$(".pop .btn").live("click", function() {
								$("#TB_overlay").remove();
								$(".pop").remove()
							});
							if (v) {
								clearTimeout(timeSignRecode_load_out)
							} else {
								$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
								$("#J_nav_sign1 .nav_sign_tip").find(".sign-recode").show();
								v = true
							}
						} else {
							if (E == 202) {
								$("#J_nav_sign1").find(".nav_sign_tip").hide();
								var F = "";
								F += '<div class="pop pop-bindphone" style="display:block;">';
								F += '<div class="inner"><a href="javascript:void(0)" class="pop-close">×</a>';
								F += "<p>您至少需要获得一笔购物返还，才能享有“<strong>有奖签到</strong>”特权哦~</p>";
								F += '<div class="btn-outer"><a href="http://www.fanhuan.com/" class="btn btn-1" target="_blank"><span>去购物，拿返还</span></a></div>';
								F += "</div></div>";
								$("body").append(F);
								$("body").append('<div id="TB_overlay" class="TB_overlayBG" style="opacity:0.6; filter:alpha(opacity=60);"></div>');
								$(".pop .pop-close").live("click", function() {
									$("#TB_overlay").remove();
									$(".pop").remove()
								});
								$(".pop .btn").live("click", function() {
									$("#TB_overlay").remove();
									$(".pop").remove()
								});
								if (v) {
									clearTimeout(timeSignRecode_load_out)
								} else {
									$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
									$("#J_nav_sign1 .nav_sign_tip").find(".sign-recode").show();
									v = true
								}
							} else {
								if (E == 100 || E == 300) {
									p = true;
									var G = $("#J_sign_btn1");
									G.attr("class", "btn ico-yes");
									G.find("em").html("今日已签到").css("color", "#ff4400");
									G.next(".nav_sign_tip").css({
										top: -195
									});
									G.next(".nav_sign_tip").find(".i-jrqd-arrow").css({
										top: 215
									});
									var C = new Date().getFullYear(),
										D = new Date().getMonth() + 1;
									$.getJSON("http://www.fanhuan.com/ajax/GetSignData/?callback=?&action=all&year=" + h + "&month=" + b + "", {}, function(H) {
										var J = H.Days;
										$("#J_nav_sign1 .nav_sign_tip").find(".no-login").remove();
										var I = '<div class="sign-recode clearfix">';
										I += '<div class="tips"><span class="success-tip"><span class="ico-success"></span>签到成功！</span>连续签到<em class="f-bold">' + H.SuccessionNum + '</em>天(<em class="s-red"><em class="f-bold">+' + H.TodayAddNum + '</em> 积分</em>)，明天签到可得<em class="s-orange"><em class="f-bold">' + H.TomorrowAddNum + "</em>积分</em></div>";
										$.getJSON("http://www.fanhuan.com/ajax/GetSignInDateAdData/?callback=?&s=" + Math.random(10) + "", {}, function(K) {
											var M = K.Day,
												L = K.WeekCN,
												O = K.Yi,
												N = K.YiUrl,
												P = K.Ji;
											I += '<div id="signDate1"></div>';
											I += '<div class="sign-info">';
											I += '<a href="http://www.fanhuan.com/app" class="C-Apptz"></a>';
											I += "</div></div></div>"
										});
										$("#J_nav_sign1").append('<div class="nav_sign_pop" style="display:none;">签到成功！<strong>积分<b>+' + s + "</b></strong></div>");
										setTimeout(function() {
											$("#J_sign_box1").html(I);
											$("#J_nav_sign1 .nav_sign_tip").css({
												width: "435px"
											}).slideDown("slow", function() {
												p = false;
												n = setTimeout(function() {
													$("#J_nav_sign1 .nav_sign_tip").hide()
												}, 2000)
											});
											u(CurrentUser.UserId)
										}, 500);
										setTimeout(function() {
											var P = new Date().getFullYear(),
												K = new Date().getMonth() + 1;
											var N = $("#J_days1").find("td");
											for (var L = 0, M = J.length; L < M; L++) {
												for (var Q = 0, M = 42; Q < M; Q++) {
													if (N[Q].innerHTML) {
														var O = N[Q].innerHTML;
														if (J[L] && O) {
															if (J[L] == O) {
																$(N[Q]).addClass("yet")
															}
														}
													}
												}
											}
										}, 1500);
										return false
									})
								} else {
									if (E == 300) {
										var G = $("#J_sign_btn1");
										G.addClass("ico-yes");
										G.find("em").html("今日已签到").css("color", "#ff4400");
										clearTimeout(timeSignRecode_load_out);
										u(CurrentUser.UserId);
										$("#J_sign_btn1").unbind("click");
										return false
									}
								}
							}
						}
					}, 500)
				}
				return false
			}
		}
		function x(C) {}
		function t(D, C) {
			if (CurrentUser.UserId == undefined) {
				Login()
			} else {
				timeSignRecode_load_out = setTimeout(function() {
					v = false;
					$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove()
				}, 1000)
			}
		}
		function w(C) {
			if (C.Error != null) {
				if (v) {
					clearTimeout(timeSignRecode_load_out)
				} else {
					$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
					$("#J_nav_sign1 .nav_sign_tip").append('<div class="sign-fail">获取日历失败，<a href="javascript:void(0)" onclick="GetSignCalender();">请重试！</a></div>');
					v = true
				}
				return
			}
			var I, F, D;
			var E = C.Result.List;
			if (E == null || E.length <= 0) {
				I = new Date().getFullYear();
				F = new Date().getMonth() + 1;
				D = "[]"
			} else {
				I = E[0].Date.substr(0, 4);
				F = E[0].Date.substr(5, 2);
				var G = [];
				for (var H = 0; H < E.length; H++) {
					G.push(E[H].Date.substr(8, 2))
				}
				D = G
			}
			q.showDateColor("signDate1", D, l);
			if (v) {
				clearTimeout(timeSignRecode_load_out)
			} else {
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-load").remove();
				$("#J_nav_sign1 .nav_sign_tip").find(".sign-recode").show();
				v = true
			}
		}
		function y(C, D, E) {
			$.ajax({
				type: "get",
				async: true,
				url: C,
				dataType: "jsonp",
				jsonp: D,
				jsonpCallback: E,
				error: function() {}
			})
		}
	})()
});
(function() {
	var d = null;

	function c() {
		var g = null;
		var f = null;
		var h = $.cookie("userDetial");
		if (h) {
			var e = h.split("|");
			g = e[1]
		}
		d = "phonetip_" + g;
		if (g) {
			f = $.cookie(d)
		}
		if (!f && g) {
			$.getJSON("http://my.fanhuan.com/ajax/UserInfoData/?callback=?", function(k) {
				if (k.BindedPhone == 1) {
					return
				}
				a()
			})
		}
	}
	function a() {
		var e = '<div class="opacity-bg-box">';
		e += '<div class="con-phonetip ie6fixedTL">';
		e += '<ul class="clearfix">';
		e += "<li>";
		e += "<p>您正在裸奔</p>";
		e += '<p class="phonetip-mg-p">请尽快绑定手机号，保护账户安全哦！</p>';
		e += "</li>";
		e += '<li class="phonetip-mg"><a class="btn btn-1-40" href="javascript:void(0);" target="_blank" id="bind-phone" bind="1"><span>绑定手机号</span></a></li>';
		e += '<li><a class="btn btn-3-40 next-close" href="javascript:void(0);"><span>下一次</span></a></li>';
		e += "</ul>";
		e += '<a href="#" title="Close" class="pop-close">×</a>';
		e += "</div>";
		e += "</div>";
		var f = location.host;
		if (f != "go.fanhuan.com" && location.href.indexOf("www.fanhuan.com/welcome.html") <= -1) {
			$("body").append('<div id="TB_overlay" class="TB_overlayBG" style="opacity:0.4; filter:alpha(opacity=40);"></div>');
			$("body").append(e)
		}
		$(".pop-close,.next-close,#bind-phone").live("click", function() {
			$.cookie(d, "F%8i3%5fg,45.@/123,y52rtD.6#$", {
				path: "/",
				expires: 7,
				domain: _domain
			});
			$(".TB_overlayBG").hide();
			$(".opacity-bg-box").hide();
			var g = $(this).attr("bind");
			if (g && g == "1") {
				window.open("http://my.fanhuan.com/my/bindphone");
				return false
			}
		});
		b();
		$(window).resize(function() {
			b()
		})
	}
	function b() {
		$(".opacity-bg-box").css({
			left: ($(window).width() - $(".opacity-bg-box").outerWidth()) / 2,
			top: ($(window).height() - $(".opacity-bg-box").outerHeight()) / 2 + $(document).scrollTop()
		})
	}
	c()
});

function imgLoadError(a) {
	a.src = "http://i.fanhuan.com/images/zdm/zdm_loading.png?v=150129"
}
var isIE_6 = false,
	isIE_7 = false;
if ($.browser.msie) {
	if ($.browser.version == "6.0") {
		isIE_6 = true
	} else {
		if ($.browser.version == "7.0") {
			isIE_7 = true
		}
	}
}
var FH = FH || {};
FH.base = {
	imgLazyLoad: function(e) {
		var a = $(window).height() + 60;
		var b = 95;
		var f = "http://i.fanhuan.com/images/zdm/zdm_loading.png?v=150129";
		var c = "zdm_loading";
		$(window).bind("resize", function() {
			a = $(window).height() + 60
		});

		function d(k, h) {
			var g = e;
			var l = 0;
			g.find("img").each(function(r) {
				if ($(this).attr("src").indexOf(c) >= 0) {
					var q = $(this).data("url");
					var o = $(this).offset().top + h;
					if (o >= $(window).scrollTop() && $(window).scrollTop() + k >= o) {
						if ($(this).attr("src") == f) {
							$(this).attr("src", q);
							var n = $(this).data("tongji");
							if (n) {
								var p = new Date();
								var m = "";
								m += p.getFullYear() + "-" + (p.getMonth() + 1) + "-" + p.getDate() + " " + p.getHours() + ":" + p.getMinutes() + ":" + p.getSeconds();
								n.Act_time = m;
								$.getJSON("http://tracker.upin.com/ajax/log?callback=?", n, function(s) {})
							}
						}
					}
				}
			})
		}
		setTimeout(function() {
			d(a, b)
		}, 1000);
		$(window).bind("scroll", function() {
			d(a, b)
		})
	},
	countDown: function(a, c, b) {
		var c = c;
		$.getJSON("http://www.fanhuan.com/ajax/GetNowDateTime/?callback=?", {}, function(d) {
			var e = function(t, u) {
					var k = u.Time.split(":");
					if (t.num == 34 || t.num == 39) {
						switch (k[0]) {
						case "00":
							k[0] = 24;
							break;
						case "01":
							k[0] = 25;
							break;
						case "02":
							k[0] = 26;
							break;
						case "03":
							k[0] = 27;
							break;
						case "04":
							k[0] = 28;
							break;
						case "05":
							k[0] = 29;
							break;
						case "06":
							k[0] = 30;
							break;
						case "07":
							k[0] = 31;
							break;
						case "08":
							k[0] = 32;
							break;
						case "09":
							k[0] = 33;
							break
						}
					}
					if (t.num == 39) {
						switch (k[0]) {
						case "10":
							k[0] = 34;
							break;
						case "11":
							k[0] = 35;
							break;
						case "12":
							k[0] = 36;
							break;
						case "13":
							k[0] = 37;
							break;
						case "14":
							k[0] = 38;
							break
						}
					}
					var f = t.num * 3600 - (k[0] * 3600 + k[1] * 60 + +k[2]);
					var p, q, n, g, l, x, r;
					var v = f;

					function w() {
						p = Math.floor(v / 3600);
						q = Math.floor(v / 60) % 60;
						n = Math.floor(v % 60);
						p < 0 ? p = 0 : p = p;
						q < 0 ? q = 0 : q = q;
						n < 0 ? n = 0 : n = n;
						p.toString().length < 2 ? g = "0" + p.toString() : g = p;
						q.toString().length < 2 ? l = "0" + q.toString() : l = q;
						n.toString().length < 2 ? x = "0" + n.toString() : x = n;
						if (p == 0 && q == 0 && n == 0) {}
						if (b == 0) {
							r = "<em>" + g + "</em><i>:</i><em>" + l + "</em><i>:</i><em>" + x + "</em>"
						} else {
							r = "<em>" + g + "</em><i>时</i><em>" + l + "</em><i>分</i><em>" + x + "</em><i>秒</i>"
						}
						if (c) {
							c.html(r)
						}
						v = v - 1;
						if (v == 0) {
							location.reload()
						}
					}
					var w = setInterval(w, 1000)
				};
			e({
				num: a
			}, d)
		})
	}
};
$(function() {
	var a = $(".J_btn_jump");
	a.live("click", function() {
		var e = $(this).attr("status-isClick");
		if (e == 1) {} else {
			var b = $(this).data("tongji");
			var d = new Date();
			var c = "";
			c += d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
			b.Act_time = c;
			$.getJSON("http://tracker.upin.com/ajax/log?callback=?", b, function(f) {});
			$(this).attr("status-isClick", 1)
		}
	});
	$(".J_login").live("click", function(b) {
		if (!CurrentUser.UserName) {
			Login();
			b.preventDefault()
		}
	})
});
FH.report = function() {
	var b = "";
	var a = function() {
			b += '<div id="J_report_box" class="m-report f-hide"><h2 id="J_drag"><a id="J_close_layer" class="xulayer_png32 u-close-white" href="javascript:;"></a>举<i style="padding:0 15px"></i>报</h2>';
			b += '<div class="cont"><h3>请选择举报原因：</h3>';
			b += '<ul class="form-list" id="J_report_form">';
			b += '<li><input id="reason1" type="radio" name="reason" value="宝贝价格与活动价格不符" /><label for="reason1">宝贝价格与活动价格不符</label></li>';
			b += '<li><input id="reason2" type="radio" name="reason" value="宝贝已下架" /><label for="reason2">宝贝已下架</label></li>';
			b += '<li><input id="reason3" type="radio" name="reason" value="主图宝贝与实际出售宝贝不符" /><label for="reason3">主图宝贝与实际出售宝贝不符</label></li>';
			b += '<li><input id="reason4" type="radio" name="reason" value="宝贝链接打不开" /><label for="reason4">宝贝链接打不开</label></li>';
			b += '<li><input id="reason5" type="radio" name="reason" value="卖家拒绝发货" /><label for="reason5">卖家拒绝发货</label></li>';
			b += '<li><input id="reason6" type="radio" name="reason" value="" style="*position:relative;*bottom:8px"/><label for="reason6" style="*position:relative;*bottom:8px">其他原因</label><input class="text" type="text" name="text" value="" /></li>';
			b += "</ul>";
			b += '<div class="btn-box f-tc">';
			b += '<a href="JavaScript:;" class="btn btn-1" id="J_btn_report_submit" data-id=""><span style="width:80px;">提交</span></a>';
			b += "</div></div></div>";
			$("body").append(b)
		};
	var c = function() {
			var d = "";
			$(document).on("click", function(l) {
				var g = l.target;
				var f = g;
				var h = $(g).hasClass("J_btn_report");
				if (h) {
					l.preventDefault();
					if (!CurrentUser.UserName) {
						Login();
						return false
					}
					var k = location.href;
					if (k == "http://www.fanhuan.com/" || k == "http://gou.fanhuan.com/") {
						d = 1
					} else {
						if (k == "http://jiujiu.fanhuan.com/") {
							d = 2
						} else {
							d = 1
						}
					}
					seajs.use("http://js.fanhuan.com/common/js/layer.min.js?v=201502121", function() {
						var e = $(f).data("id");
						$.getJSON("http://baoliao.fanhuan.com/ajax/GetUserReportInfo?callback=?", {
							AppType: d,
							AppData: e
						}, function(m) {
							if (m.code == "1000") {
								var n = setInterval(function() {
									var o = $("#J_report_box").length;
									if (o) {
										clearInterval(n);
										$("#J_report_form").find("input:checked").removeAttr("checked");
										$("#J_report_form").find(".text").val("");
										$.layer({
											shade: [0.5, "#000", true],
											type: 1,
											area: ["auto", "auto"],
											title: false,
											move: ["#J_drag", true],
											page: {
												dom: "#J_report_box"
											}
										});
										$(".xubox_close1_0").css({
											"background-position": "0 -310px",
											top: 10,
											"background-color": "#2F97F0",
											border: "1px solid #2F97F0",
											right: 8
										});
										if (isIE_7) {
											$(".xubox_shade,.xubox_layer").css({
												position: "fixed"
											})
										}
										$("#J_btn_report_submit").data("id", $(f).data("id"))
									} else {
										a()
									}
								}, 100)
							} else {
								layer.msg("" + m.msg + "", 2, 1);
								if (isIE_7) {
									$(".xubox_shade,.xubox_layer").css({
										position: "fixed"
									})
								}
								return false
							}
						})
					})
				}
			});
			$("#J_btn_report_submit").live("click", function(m) {
				m.preventDefault();
				var f = $("#J_report_form").find("input:checked"),
					k = f.length;
				if (!k) {
					layer.msg("请选择举报理由~", 2, 1);
					if (isIE_7) {
						$(".xubox_shade,.xubox_layer").css({
							position: "fixed"
						})
					}
					return false
				}
				var h = $("#J_report_form").find(".text").val();
				var k = $("#reason6:checked").length;
				if (!h && k) {
					layer.msg("举报理由不能为空~", 2, 1);
					if (isIE_7) {
						$(".xubox_shade,.xubox_layer").css({
							position: "fixed"
						})
					}
					return false
				}
				var g = $(this).data("id"),
					n = f.val() || $("#J_report_form").find(".text").val();
				if (k) {
					id = 100
				} else {
					id = f.parent().index() + 1
				}
				var l = $.cookie("cookie_clientid");
				$.getJSON("http://baoliao.fanhuan.com/ajax/AddReportInfo?callback=?", {
					AppType: d,
					AppData: g,
					ReportContent: n,
					ReportContentID: id,
					MachineID: l
				}, function(e) {
					if (e.code == "1000") {
						LAYER.close(LAYER.getIndex("#J_report_form"));
						layer.msg("举报成功~", 2, 2);
						if (isIE_7) {
							$(".xubox_shade,.xubox_layer").css({
								position: "fixed"
							})
						}
					} else {
						layer.msg("" + e.msg + "", 2, 1);
						if (isIE_7) {
							$(".xubox_shade,.xubox_layer").css({
								position: "fixed"
							})
						}
					}
				})
			})
		};
	return {
		author: "zimo",
		version: "1.0.0",
		init: function() {
			c()
		}
	}
}();
FH.report.init();
$("#sh_sel").click(function() {
	var a = window.open();
	if (CurrentUser.UserName == undefined) {
		a.location.href = "http://www.fanhuan.com/hezuo/NewApply";
		return
	}
	$.getJSON("http://my.fanhuan.com/ajax/PhoneIsBind?callback=?", function(b) {
		a.location.href = "http://www.fanhuan.com/hezuo/applylist"
	})
});