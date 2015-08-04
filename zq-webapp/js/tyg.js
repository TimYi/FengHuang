$(function(){

	getMuseumsList();

	function getMuseumsList(){
		var url = Base.serverUrl + '/api/museums';
		var condi = {};
		condi.page = 1;
		condi.size = 100;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			async: false,
			success: function(data){
				console.log(data);
				var status = data.status || "";
				if(status == "OK"){
					changeMeseumsHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取数据错误:" + msg);
				}
				//g.httpTip.hide();
			},
			error:function(data){
				//g.httpTip.hide();
			}
		});
	}

	function changeMeseumsHtml(data){
		var obj = data.result || '';
	}

})