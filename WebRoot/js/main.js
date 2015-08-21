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
$(document).ready(function() {
    // 中文字两个字节 
    jQuery.validator.addMethod("byteRangeLength",
    function(value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || (length >= param[0] && length <= param[1]);
    },
    "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

    //字符串验证
    jQuery.validator.addMethod("stringCheck",
    function(value, element) {
        return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
    },
    "只能包括中文字、英文字母、数字和下划线");

    validator = $('#form_reg').validate({
        rules: {
            "password": {
                required: true,
                rangelength: [4, 20]
            },
            "repass": {
                required: true,
                equalTo: "#password"
            },
            "email": {
                required: true,
                email: true
            }
        },
        messages: {
            "password": {
                required: "<span class='tip warn'><i></i>密码由数字、字母组成，字母区分大小写</span>",
                rangelength: "<span class='tip err'><i></i>请输入4-20位正确的密码</span>"
            },
            "repass": {
                required: "",
                equalTo: "<span class='tip err'><i></i>请输入相同的密码</span>"
            },
            "email": {
                required: "<span class='tip warn'><i></i>请填写您常用的邮箱作为登录账号</span>",
                email: "<span class='tip err'><i></i>邮箱格式出错啦</span>"
            },

        }
    });
});


   
 