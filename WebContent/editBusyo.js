// 新しい部署登録
var editBusyo = function() {
	var editBusyoName = $('#js-add-editName').val();
	var originName = localStorage.getItem('nameOrigin');

	var requestQuery = {
		editName : editBusyoName,
		originName : originName
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

var cancel = function() {
	var url = 'http://localhost:8080/syainsearch/busyo.html';
	location.href = url;
}

$(document).ready(function() {
	//'use strict';

	// 初期表示用
	//executeAjax();

	$('#js-add-edit').click(editBusyo);
	$('#js-add-cancel').click(cancel);

});