// 新しい部署登録
var editBusyo = function() {

	var parameter = location.search.substring(1, location.search.length);
	parameter = decodeURIComponent(parameter);
	parameter = parameter.split('=')[1];

	var editBusyoName = $('#js-add-editName').val();

	var requestQuery = {
		editName : editBusyoName,
		q : parameter
	};
	console.log(requestQuery);
	// サーバーにデータ送信
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/syainsearch/EditBusyoServlet',
		data : requestQuery,
		success : function(json) {
			console.log('返却値', json);
			var url = 'http://localhost:8080/syainsearch/successDep.html';
			location.href = url;

			alert('データベースへの登録が完了しました')
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}
	})

}

var cancel = function() {
	var url = 'http://localhost:8080/syainsearch/busyo.html';
	location.href = url;
}

$(document).ready(function() {
	// 'use strict';

	// 初期表示用
	// executeAjax();

	$('#js-add-edit').click(editBusyo);
	$('#js-add-cancel').click(cancel);

});