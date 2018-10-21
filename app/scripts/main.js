(function() {

	'use strict';
	$("img[data-original]").lazyload({
		threshold : -10
	});

	$("dl.dl-horizontal").on('click', function(){
		$('dl.dl-horizontal.focus').removeClass('focus');
		$(this).addClass('focus');
	})
	$('h2, h3').on('click', function(){
		$('dl.dl-horizontal.focus').removeClass('focus');
	});
})();