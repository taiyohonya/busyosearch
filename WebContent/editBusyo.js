// 新しい部署登録
var editBusyo = function() {
	var editBusyoName = $('#js-add-editName').val();

	var requestQuery = {
		editName : editBusyoName
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

			alert('データベースへの登録が完了しました')
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
	executeAjax();

	$('#js-add-input').click(addBusyo);

});