var login = function() {
	var inputEmpId = $('#js-input-empId').val();
	var inputpass = $('#js-input-pass').val();

	var requestQuery = {
		userId : inputEmpId,
		pass : inputpass
	};
	console.log(requestQuery);
	// サーバーにデータ送信
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/syainsearch/LoginEmpServlet',
		data : requestQuery,
		success : function(json) {
			console.log('返却値', json);
			if (json.result == "ok") {
				alert('ログイン成功');
				var url = 'http://localhost:8080/syainsearch/employee.html'
				location.href = url;
			} else {
				alert('ログイン失敗');
			}

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

	$('#js-login-button').click(login);

});