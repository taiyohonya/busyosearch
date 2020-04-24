// AjaxでJSONを取得する
function executeAjax() {
	'use strict';

	// console.dir(requestQuery);
	//var requestQuery = {busyoId : $('#table_data').load()};
	$.ajax({
		type : 'GET',
		url : '/syainsearch/BusyoServlet',
		dataType : 'json',
		//data : requestQuery, // urlの?以降
		success : function(json) {
			console.log(json)
		}
	});

}

$(document).ready(function() {
	'use strict';

	// 初期表示用
	executeAjax();
	$('#table_data').ready('load', executeAjax);

});