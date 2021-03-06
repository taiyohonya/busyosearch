var editAdd = function() {

	var inputEmpCd = $('#js-add-inputCd').val();
	var inputEmpName = $('#js-add-inputName').val();
	var inputEmpAge = $('#js-add-inputAge').val();
	var inputEmpSex = $('#js-add-inputSex').val();
	var inputEmpAdress = $('#js-add-inputAdress').val();
	var inputEmpDep = $('#js-add-inputDep').val();
	var inputEmpJoin = $('#js-add-inputJoinDay').val();
	var inputEmpLeave = $('#js-add-inputLeaveDay').val();

	var requestQuery = {
		inputEmpCd : inputEmpCd,
		inputEmpName : inputEmpName,
		inputEmpAge : inputEmpAge,
		inputEmpSex : inputEmpSex,
		inputEmpAdress : inputEmpAdress,
		inputEmpDep : inputEmpDep,
		inputEmpJoin : inputEmpJoin,
		inputEmpLeave : inputEmpLeave
	};
	console.log(requestQuery);
	// サーバーにデータ送信
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/syainsearch/EmployeeServlet',
		data : requestQuery,
		success : function(json) {
			if (json.result == "ok") {
				console.log('返却値', json);
				var url = 'http://localhost:8080/syainsearch/successEmp.html';
				location.href = url;

				alert('データベースへの登録が完了しました');
			} else {
				alert('権限がありません。またはログインしてください。');
				$('#table_data').append("");
				var url = 'http://localhost:8080/syainsearch/loginEmp.html';
				location.href = url;
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

	$('#js-add-edit').click(editAdd);

});