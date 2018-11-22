(function() {

	'use strict';
	$("img[data-original]").lazyload({
		threshold : -10
	});

	$("dl").on('click', function(){
		$('dl.focus').removeClass('focus');
		$(this).addClass('focus');
	})
	$('h2, h3').on('click', function(){
		$('dl.focus').removeClass('focus');
	});
})();