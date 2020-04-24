/**
 *
 */
$.ajax({
	type : "GET",
	url : "/BusyoServlet",
	datatype : "json",
	success : function(json){
		console.log(json);
	}
})