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
						+ json[i].busyoName + '</td>' + '<td>'
						+ '<button class="busyo_edit" value="'
						+ json[i].busyoId + '">編集 ' + '</button>' + '</td>'
						+ '<td>' + '<button class="busyo_delete" value="'
						+ json[i].busyoId + '">削除' + '</button>' + '</td>';
				$('#table_data').append(data);
				console.log(json[i].busyoId);
			}
			$('.busyo_delete').click(deleteBusyo);
			$('.busyo_edit').click(editBusyo);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}

	});
}

// 新しい部署登録
var addBusyo = function() {
	var inputBusyoCd = $('#js-add-inputCd').val();
	var inputBusyoName = $('#js-add-inputName').val();

	var requestQuery = {
		busyoCdNew : inputBusyoCd,
		busyoNameNew : inputBusyoName
	};
	console.log(requestQuery);
	// サーバーにデータ送信
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/syainsearch/BusyoServlet',
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

var deleteBusyo = function() {

	var removeBusyoId = document.activeElement.value;
	var requestQuery = {
		busyoRemove : removeBusyoId
	};
	console.log(requestQuery);

	$.ajax({
		type : 'POST',
		data : requestQuery,
		dataType : 'json',
		url : '/syainsearch/BusyoRemoveServlet',
		success : function(json) {
			console.log('返却値', json);
			alert('削除しました');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}
	})
}

var editBusyo = function() {

	var editBusyoId = document.activeElement.value;

	var url = 'http://localhost:8080/syainsearch/edit_busyo.html?busyoId='+editBusyoId;
	location.href = url;

}

$(document).ready(function() {
	'use strict';

	// 初期表示用
	executeAjax();
	$('#table_data').ready('load', executeAjax);

	$('#js-add-input').click(addBusyo);

});