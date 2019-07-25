$(document).ready(function() {
	$('#gnb > li ').hover(function() {
		var ia = $(this).attr('class');
		var iaon = $(this).find('> a').attr('class');
		var iahref = $(this).find('> div >ul').height();
		$(this).find('>div').stop();
		if (iaon != 'on') {
			// $(this).find('> a').css({'background-position':'-181px top'});
			$(this).find('>div').animate({
				height : iahref + 'px',
				opacity : '1'
			});
		}
	}, function() {
		$(this).find('>div').stop();
		var iaon = $(this).find('> a').attr('class');
		if (iaon != 'on') {
			// $(this).find('> a').css({'background-position':'left top'});
			$(this).find('>div').animate({
				height : '0px',
				opacity : '0'
			});
		}
	});
});