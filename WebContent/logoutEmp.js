var logout = function() {
	// サーバーにデータ送信
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/syainsearch/LoginEmpServlet',
		success : function(json) {
			console.log('返却値', json);

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}
	})
}

$(document).ready(function() {
	'use strict';

	// 初期表示用

	$('#js-logout-button').click(logout);

});