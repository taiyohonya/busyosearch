// AjaxでJSONを取得する
function executeAjax() {
	'use strict';


	$.ajax({
		type : 'GET',
		url : '/syainsearch/BusyoServlet',
		dataType : 'json',
		// data : requestQuery, // urlの?以降
		success : function(json) {
			console.log(json);

			for (var i = 0; i < json.length; i++) {
				var data = '<tr>' + '<td>' + (i + 1) + '</td>' + '<td>'
						+ json[i].busyoName + '</td>';
				$('#table_data').append(data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}

	});
}

//新しい部署登録
var addBusyo = function() {
	var inputBusyoName=$('#js-add-inputName').val();
	var inputBusyoCd=$('#js-add-inputCd').val();
	var requestQuery={busyoNameNew:inputBusyoName};
	console.log('requestQuery');
	//サーバーにデータ送信
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/syainsearch/BusyoServlet',
		data:requestQuery,
		success:function(json){
			console.log('返却値',json);

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
	$('#table_data').ready('load', executeAjax);

	$('#js-add-input').click(addBusyo);

});