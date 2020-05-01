var getEmpInfo = function() {

	var inputDep = $('#js-search-dep').val();
	var inputId = $('#js-search-id').val();
	var inputName = $('#js-search-name').val();

	var requestQuery = {
		inputDep : inputDep,
		inputId : inputId,
		inputName : inputName,
	};
	console.log(requestQuery);
	// サーバーにデータ送信
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/syainsearch/EmployeeSearchServlet',
		data : requestQuery,
		success : function(json) {
			console.log('返却値', json);
			for(var i=0;i<json.length;i++){
				var result = '<tr>'+'<th>社員ID'+'</th>'+'<td>'+json[i].empId+'</td>'+'<th>名前'+'</th>'+'<td>'+json[i].empName+'<td>'+'<th>年齢'+'</th>'+
				'<td>'+json[i].empAge+'</td>'+'<th>性別</th>'+'<td>'+json[i].empSex+'</td>'
				+'<th>住所</th>'+'<td>'+json[i].empAdress+'</td>'+'<th>部署ID</th>'+'<td>'+json[i].empDepId+'</td>';
				$('#js-search-result').append(result);
			}


			alert('データベースへの検索が完了しました');
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

	$('#js-search-button').click(getEmpInfo);

});