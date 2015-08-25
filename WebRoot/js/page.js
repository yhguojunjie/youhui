$(function(){
	$('#owl-top').owlCarousel({
		navigation: true,
		navigationText: ["<",">"],
		items:2,
		pagination:false,
		autoPlay:true
	});

	//brand-wrapper
	$('#owl-brand').owlCarousel({
		navigation: true,
		navigationText: ["<",">"],
		items:10,
		pagination:false
	});

	//今日品牌
	$('.nav-list li').click(function() {
		console.log('22222222');
		$(this).addClass('current').siblings().removeClass('current');
	});
});