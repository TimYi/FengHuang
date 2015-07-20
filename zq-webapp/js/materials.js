$(function(){

	getMeterials();

	function getMeterials(){
		var url = Base.serverUrl + '/api/materials';
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			async: false,
			success: function(data){
				console.log("getMeterials",data);
				var status = data.status || "";
				if(status == "OK"){
					//changeNavigationHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取主材数据错误:" + msg);
				}
				//g.httpTip.hide();
			},
			error:function(data){
				//g.httpTip.hide();
			}
		});
	}
})