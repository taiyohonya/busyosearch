var returnPage = function() {
	var url = 'http://localhost:8080/syainsearch/busyo.html';
	location.href = url;
}

$(document).ready(function() {
	'use strict';

	// 初期表示用

	$('#js-return-page').click(returnPage);

});