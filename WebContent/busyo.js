// AjaxでJSONを取得する
function executeAjax() {
	'use strict';

	//console.dir(requestQuery);
}

var requestQuery = {
		busyoId : $('#table').val()
	};

$(document).ready(function() {
	'use strict';
	$.ajax({
		type : 'GET',
		url : '/syainsearch/BusyoServlet',
		dataType : 'json',
		data : requestQuery, // urlの?以降
		success : function(json) {
			console.log(json)
		}
	});

	// 初期表示用
	executeAjax();

});