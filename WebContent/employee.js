// AjaxでJSONを取得する
function executeAjax() {
	'use strict';

	$.ajax({
		type : 'GET',
		url : '/syainsearch/EmployeeServlet',
		dataType : 'json',
		// data : requestQuery, // urlの?以降
		success : function(json) {
			console.log(json);
			if (json.result === "ok") {
				for (var i = 0; i < json.data.length; i++) {
					var data = '<tr>' + '<td>' + json.data[i].empId + '</td>'
							+ '<td>' + json.data[i].empName + '</td>' + '<td>'
							+ '<button class="emp_edit" value="'
							+ json.data[i].empId + '">編集 ' + '</button>'
							+ '</td>' + '<td>'
							+ '<button class="emp_delete" value="'
							+ json.data[i].empId + '">削除' + '</button>'
							+ '</td>';
					$('#table_data').append(data);
					console.log(json.data[i].empId);
				}
			} else {
				alert('権限がありません。またはログインが必要です。');
				$('#table_data').append("");
				var url = 'http://localhost:8080/syainsearch/loginEmp.html';
				location.href = url;

			}
			$('.emp_delete').click(deleteEmp);
			$('.emp_edit').click(editEmp);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}

	});
}

// 新しい部署登録
var addEmp = function() {
	var url = 'http://localhost:8080/syainsearch/editEmployee.html';
	location.href = url;

}

var deleteEmp = function() {

	var removeEmpId = document.activeElement.value;
	console.log(removeEmpId);
	var requestQuery = {
		empRemove : removeEmpId
	};
	console.log(requestQuery);

	$.ajax({
		type : 'POST',
		data : requestQuery,
		dataType : 'json',
		url : '/syainsearch/EmpRemoveServlet',
		success : function(json) {

			if (json.result == "ok") {
				console.log('返却値', json);

				window.location.reload();
				alert('削除しました');
			} else {
				alert('権限がありません。またはログインが必要です。');
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

var editEmp = function() {

	var empId = document.activeElement.value;

	var url = 'http://localhost:8080/syainsearch/editEditEmp.html?q=' + empId;
	location.href = url;

}

var searchEmp = function() {

	var url = 'http://localhost:8080/syainsearch/searchEmployee.html';
	location.href = url;

}

var move = function() {
	var url = 'http://localhost:8080/syainsearch/busyo.html'
	location.href = url;
}

$(document).ready(function() {
	'use strict';

	// 初期表示用
	executeAjax();
	$('#table_data').ready('load', executeAjax);

	$('#js-add-input').click(addEmp);

	$('#js-add-search').click(searchEmp);

	$('#js-move').click(move);

});
