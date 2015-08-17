(function() {
    //下拉框
    var num = 0;
    $('[data-toggle=arrowdown]').hover(function() {
        var _id = $(this).attr('id');
        num = _id.substring(5, _id.length);
        $(this).find('span').removeClass('run-down').addClass('run-up');
        $('#nav-box' + num).slideDown(100);
    }, function() {
        $(this).find('span').removeClass('run-up').addClass('run-down');
        $('#nav-box' + num).hide();
    });
    $('[data-toggle=hidden-box]').hover(function() {
        var _id = $(this).attr('id');
        num = _id.substring(7, _id.length);
        $('#arrow' + num).addClass('nav-hover').find('span').removeClass('run-down').addClass('run-up');
        $(this).show();
    }, function() {
        $('#arrow' + num).removeClass('nav-hover').find('span').removeClass('run-up').addClass('run-down');
        setTimeout(function() {
            $('#arrow' + num).find('span').removeClass('run-down');
        }, 500);
        $(this).slideUp(100);
    });
    
    //关二维码
    $('.close-code').click(function() {
        $('.two-code').fadeOut(200);
    });
    
    //滚动出现固定导航
    $(window).scroll(function() {
        var scTop = $(window).scrollTop(),
        $scS = $('.scroll-search'),
        $frS = $('.fix-right-sub'),
        rW;
        rW = ($(window).width() - 1200) / 2;

        scTop >= 200 ? $scS.slideDown(200) : $scS.slideUp(200);
        if (scTop >= 2700 && scTop < 4400) {
            $frS.css({
                position: 'fixed',
                top: '-541px',
                right: rW,
                marginTop: ''
            });
        } else if (scTop >= 4400) {
            $frS.css({
                position: '',
                marginTop: 1728
            });
        } else {
            $frS.css({
                position: ''
            });
        }
    });

  //超高优惠
	$('.show-list li').click(function() {
		$(this).addClass('current').siblings().removeClass('current');
	});
    //合作伙伴
   /* $('.right-con .cooper-logo').hover(function() {
        $(this).addClass('hover');
    }, function() {
        $(this).removeClass('hover');
    });*/
 
    /* 返回顶部  */
    $(window).scroll(function() { //滚动时触发
        var top = $(document).scrollTop(),
            //获取滚动条到顶部的垂直高度
            height = $(window).height(); //获得可视浏览器的高度
            if (top > 100) {
                $("#backToTop1").show(200, function() {
                    $("#backToTop1").css({
                        top: height + top - 100
                    });
                });
            }
        });

    /*点击回到顶部*/
    $('#backToTop-up').click(function() {
        $('html, body').animate({
            scrollTop: 0
        }, 500);
    });
    
    /*点击到底部*/
    $('#backToTop-down').click(function() {
        $('html, body').animate({
            scrollTop: $(document).height()
        }, 500);
    });
})(jQuery);

//注册
$(function () {
        //光标停靠输入框右侧
        $("#form_reg").find(".label").live("click", function () {
            var input = $(this).siblings("input.txt");
            if (!input.attr("disabled")) {
                input.focus();
              /*  var rng = input.createTextRange();
                rng.collapse(false);
                rng.select();*/
            }
        });

    /*    $("#reg_checkcode").trigger("click");
        if ($("#reg_password").val() == "") {
            $("#reg_password_again").css({
                "background": "#EBEBE4"
            })
        }*/

        //判断IE 和 IE6
      /*  var isIE = false,
            isIE_6 = false,
            isIE_10 = false;
        if ($.browser.msie) {
            isIE = true;
            if ($.browser.version == "6.0") {
                isIE_6 = true;
            } else if (parseInt($.browser.version) >= 10) {
                isIE_10 = true;
            }
        }*/
        var maxIndex = 1;

        //页面刷新时，判断输入框
       /* if (!isIE) {
            $("#reg_mail").css({
                //"padding-right": "25px",
                "width": "205px"
            })
        }*/
       if ($("#reg_mail").val() != "") {
            $("#reg_mail").css({
                "color": "#666"
            });
           /* if (!isIE) {
                $("#reg_mail").siblings(".clear-btn").removeClass("clear-btn-focus").show();
            }*/
        }
        $("#form_reg").find("input[class~='txt']").bind({
            "focus": function () {
/*                maxIndex++;
*/                $(this).addClass("focus");
            },
            "blur": function () {
                $(this).removeClass("focus");
            },
            "keyup": function (e) {
                if (e.keyCode == 13) {
                    RegisterByEmail($("#reg_submit"), true);
                }
            }
        });

        //表单验证
        $("#form_reg #reg_mail,#form_reg #reg_password,#form_reg #reg_password_again,#form_reg #reg_checkcode").bind({
            "focus": function () {
                CheckInput($(this), true);
            },
            "blur": function () {
                CheckInput($(this));
            }
        });

        //“X”清空已输入
        $("#form_reg").find(".clear-btn").live("click", function () {
            $(this).hide().siblings("input").val("").focus();
        });

        //密码框是否大写
 /*       if (!isIE) {
            $("#form_reg").find("input[type='password']").bind({
                "keypress": function (event) {
                    var e = event || window.event;
                    var target = e.target || e.srcElement;
                    var keyCode = e.keyCode || e.which; //按键的keyCode
                    var isShift = e.shiftKey || (keyCode == 16) || false;   //shift键是否按住
                    if ((keyCode >= 65 && keyCode <= 90) || ((keyCode >= 97 && keyCode <= 122) && isShift)) {
                        $(this).siblings(".poptip-pass").show();
                    } else {
                        $(this).siblings(".poptip-pass").hide();
                    }
                },
                "blur": function () {
                    $(this).siblings(".poptip-pass").hide();
                },
                "keyup": function () {
                    if ($.trim($(this).val()) == "") {
                        $(this).siblings(".poptip-pass").hide();
                    }
                },
                "keydown": function () {
                    if ($.trim($(this).val()) == "") {
                        $(this).siblings(".poptip-pass").hide();
                    }
                }
            });
}*/
        //邮箱事件
        $("#reg_mail").bind({
            "focus": function () {
                if ($.trim($(this).val()) == "") {
                    $(this).val("").css({
                        "color": "#666"
                    });
                }
                if (!isIE && $.trim($(this).val()) != "") {
                    $(this).siblings(".clear-btn").addClass("clear-btn-focus").show();
                }
            },
            "blur": function () {
                var _this = $(this),
                value = $.trim($("#reg_mail").val()),
                result = CheckEmail(value);
                if (value != "") {
                    $(this).css({
                        "color": "#666"
                    });
                    if (!isIE) {
                        $(this).siblings(".clear-btn").removeClass("clear-btn-focus").show();
                    }
                } else {
                    if (!isIE) {
                        $(this).siblings(".clear-btn").removeClass("clear-btn-focus").hide();
                    }
                }
            },
            "keyup": function () {
                if (!isIE && $("#reg_mail").val() != "") {
                    $(this).siblings(".clear-btn").addClass("clear-btn-focus").show();
                } else {
                    $(this).siblings(".clear-btn").hide();
                }
            }
        });

        //密码事件
        //密码提示控制
        var oBtnPassAgain = false;
        $("#reg_password").bind({
            "keyup": function () {
                if ($(this).val().length > 0) {
                    $("#reg_password_again").removeAttr("disabled").css({
                        "background": "#fff"
                    });
                    if (oBtnPassAgain && ($("#reg_password_again").val() != "")) {
                        CheckInput($("#reg_password_again"));
                    }
                } else {
                    //oBtnPassAgain = false;
                    $("#reg_password_again").attr("disabled", "disabled").css({
                        "background": "#EBEBE4"
                    });
                    $("#reg_password_again").siblings(".tiptext").hide();
                }
            }
        });
        $("#reg_password_again").bind({
            "focus": function () {
                oBtnPassAgain = true;
            }
        });
    });

    //服务条款事件
    $("#CheckTerm").change(function () {
        if ($(this).is(":checked")) {
            $(this).parents(".text-term").find(".tiptext").hide();
        } else {
            $(this).parents(".text-term").find(".tiptext").show();
        }
    });

    //联合登录
    var aYetMail = {
        mail: [],
        result: []
    };
    var oCheckEmailBtn = false;

    //表单验证
    function CheckInput(element, oBtn) {
        var result;
        var isFocus = false;
        if (oBtn) {
            isFocus = true;
        }
        var value = $.trim(element.val());
        switch (element.attr("name")) {
            case "email":
            result = CheckEmail(value, oBtn);
            break;
            case "password":
            value = element.val();
            result = CheckPassword(value, oBtn);
            break;
            case "password_again":
            var pass_val = $("#reg_password").val();
            value = element.val();
            result = CheckPasswordAgain(pass_val, value, oBtn);
            break;
            case "checkcode":
            value = element.val();
            result = CheckCheckcode(value, oBtn);
            break;
            default:
            return;
        }
        if (result != null) {
            if (element.attr("name") == "email" && result.state == "ok" && !isFocus) {
                if (aYetMail.mail.length > 0) {
                    for (var i = 0; i < aYetMail.mail.length; i++) {
                        if (value == aYetMail.mail[i]) {
                            ShowTiptext(element, aYetMail.result[i].content, aYetMail.result[i].state);
                            if (aYetMail.result[i].state == "ok") {
                                return true;
                            }
                            return false;
                        }
                    }
                }
                //element.siblings(".tiptext").attr("class", "").addClass("tiptext tiptext-loading").html("");
                // 检测用户名和邮箱是否已存在
                $.ajax({
                    type: "GET",
                    url: "",
                    cache: false,
                    data: "identifier=" + encodeURIComponent(value),
                    success: function (response) {
                        var response = eval(response);
                        if (response.Error != null) {
                            result = {
                                content: response.Error.Msg,
                                state: "error"
                            }
                            oCheckEmailBtn = false;
                        }
                        if (response.Result == null) {
                            result = {
                                content: "",
                                state: "ok"
                            }
                            oCheckEmailBtn = true;
                        }
                        if (response.Result != null) {
                            if (response.Result.Succeed) {
                                result = {
                                    content: "该邮箱已被注册",
                                    state: "error"
                                }
                                oCheckEmailBtn = false;
                            }
                            else {
                                result = {
                                    content: "",
                                    state: "ok"
                                }
                                oCheckEmailBtn = true;
                            }
                        }
                        aYetMail.mail.push(value);
                        aYetMail.result.push(result);

                        ShowTiptext(element, result.content, result.state);
                    }
                });
} else {
    ShowTiptext(element, result.content, result.state);
    if (result.state == "ok") {
        return true;
    }
}
}
return false;
}
    // 邮箱检测
    function CheckEmail(email, oBtn) {
        if (oBtn) {
            return {
                content: "请填写您常用的邮箱作为登录帐号",
                state: "info"
            }
        }
        if (email == "") {
            return {
                content: "邮箱不能为空",
                state: "error"
            }
        }
        if (!/^[\.a-zA-Z0-9_-]+@([a-zA-Z0-9_-]+\.)+[a-zA-Z]{2,3}$/.test(email)) {
            return {
                content: "邮箱格式出错啦",
                state: "error"
            }
        }
        return {
            content: "",
            state: "ok"
        }
    }

    //密码
    function CheckPassword(value, oBtn) {
        if (oBtn) {
            return {
                content: "密码由数字、字母组成，字母区分大小写",
                state: "info"
            }
        }
        if (value == "") {
            return {
                content: "密码不能为空",
                state: "error"
            }
        }
        if ($("#reg_mail").val() == "") {
            $(".tiptext").eq(0).removeClass("tiptext-info").addClass("tiptext-error").html("<i></i>请输入您的常用邮箱");
        }
        return {
            content: "",
            state: "ok"
        }
    }

    //确认密码
    function CheckPasswordAgain(value1, value2, oBtn) {
        if (oBtn) {
            return {
                content: "再输一次密码",
                state: "info"
            }
        }
        if (value1 != value2) {
            return {
                content: "两次密码不一致",
                state: "error"
            }
        } else {
            return {
                content: "",
                state: "ok"
            }
        }
    }

    // 校验验证码
    function CheckCheckcode(value, oBtn) {
        if (oBtn) {
            return {
                content: "",
                state: "ok"
            }
        }
        var reg = /^\w+$/g;
        if (value == "") {
            return {
                content: "请输入验证码",
                state: "error"
            };
        }
        if (!reg.test(value)) {
            return {
                content: "验证码格式错误",
                state: "error"
            }
        }
        if (value.length != 4) {
            return {
                content: "验证码长度错误",
                state: "error"
            };
        }
        return {
            content: "",
            state: "ok"
        }
    }

    // 提示
    // param    state   ok,error,info
    var timeShowTip = {};
    function ShowTiptext(input, content, state) {
        var name = input.attr('name');
        clearTimeout(timeShowTip[name]);
        timeShowTip[name] = setTimeout(function () {
            input.siblings(".tiptext").attr("class", "tiptext").addClass("tiptext-" + state).html('<i></i>' + content).show();
            if (input.attr("name") == "checkcode") {
                input.siblings(".tiptext").find("i").remove();
            }
        }, 150);
    }

    // 刷新验证码
    function RefreshCheckcode(img) {
        var imgUrl = img.attr("_src") + "?" + Math.random();
        img.attr("src", imgUrl);
        img.parents(".checkcode").css("display", "");
    }

    // 邮件注册
    var _reggingClass = "regging";
    function RegisterByEmail(btn, setPassword) {
        if (btn.hasClass(_reggingClass)) {
            return false;
        }
        if ((CheckInput($("#form_reg input[name='email']")) || oCheckEmailBtn) && CheckInput($("#form_reg input[name='password']")) && CheckInput($("#form_reg input[name='password_again']")) && CheckInput($("#form_reg input[name='checkcode']"))) {
            if (!$("#CheckTerm").is(":checked")) {
                $("#CheckTerm").parents(".text-term").find(".tiptext").show();
                return false;
            }
            var email = $.trim($("#form_reg input[name='email']")[0].value),
            password = $("#form_reg input[name='password']")[0].value,
            checkcode = $("#form_reg input[name='checkcode']")[0].value,
            qqNum = "";
            var data = "email=" + encodeURIComponent(email) +
            "&setpassword=" + setPassword +
            "&password=" + encodeURIComponent(password) +
            "&checkcode=" + encodeURIComponent(checkcode) +
            "&qq=" + qqNum +
            "&regpage=" + encodeURIComponent(location.href) +
            "&callback=RegisterCallback&_=" + Math.random() + "";
            btn.addClass(_reggingClass);
            CreateJScript("register", "http://passport.fanhuan.com/ajax/RegByEmail?" + data);
        }
        return false;
    }

    // 注册回调
    function RegisterCallback(response) {
        if (response.Error != null) {
            if (response.Error.Keyword == "checkcode") {
                $("#reg_checkcode").trigger("click");
                CheckInput($("#reg_checkcode"));
                ShowTiptext($("#reg_checkcode"), response.Error.Msg, "error");
                $("#reg_checkcode").find("i").remove();
                $("#reg_check_code").siblings(".tiptext").attr("class","").addClass("tiptext tiptext-error").html(response.Error.Msg);
                alert(response.Error.Msg);
            } else if (response.Error.Keyword == "existed_email") {
                CheckInput($("#reg_mail"));
                ShowLoginBox($.trim($(":input[name=email]")[0].value), "您的邮箱已注册过返还网，请输入密码直接登录");
            } else { alert(response.Error.Msg); }
        }
        else if (response.Result.Succeed) {
            JumpAfterReg();
            return;
        }
        $("." + _reggingClass).removeClass(_reggingClass);
    }

    // 注册成功后跳转
    function JumpAfterReg() {
        $(window).unbind("resize");
        $('#FH_Taobao_css').remove();
        var lp = decodeURIComponent(request("lp"));
        $.cookie("FH_TaobaoOrTmallGuider_over", "true");
        if (/^\/(taobao)|(tianmao)\/?$/.test(location.pathname)) {
            window.top.location.href = "taobao.html";
        }
        else if (lp == "" || lp == undefined || lp.indexOf("passport.fanhuan.com") >-1 ) {
            window.top.location.href = "welcome.html";
        } else {
            $(".con-phonetip").hide();
            window.top.location.href = lp;
        }
    }