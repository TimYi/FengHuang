
(function(){

	function sendGetTechnologiesHttp(){
		var url = Base.serverUrl + "/api/technologies";
		//g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			async: false,
			success: function(data){
				console.log("sendGetTechnologiesHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeTechnologiesHtml(data);
				}
				else{
					var msg = data.errorDescription || "";
					Utils.alert("获取工艺展示错误:" + msg);
				}
				//g.httpTip.hide();
			},
			error:function(data){
				//g.httpTip.hide();
			}
		});
	}


	function changeTechnologiesHtml(data){
		var obj = data.result || [];

		var html = [];
		for(var i = 0, len = obj.length; i < len; i++){
			var name = obj[i].name || "";
			var pic = obj[i].pic || "";
			if(pic !== ""){
				pic = pic.url || "";
			}
			var description = obj[i].description || "";

			html.push('<section style="border-top:1px solid #f6f6f6;">');
			html.push('<div class="container"  style="margin-bottom:-50px">');
			html.push('<div class="center wow fadeInDown">');
			html.push('<h2>' + name + '<span style="font-size:24px"> FREE SERVICE</span></h2>');
			html.push('<p class="lead"></p>');
			html.push('</div>');
			html.push('<div class="container">');
			html.push('<div class="row" style="margin:-20px 0 30px 0">');
			html.push('<div class="col-md-4">');
			html.push('<div class="recent-work-wrap">');
			html.push('<img class="img-responsive" src="' + pic + '" alt="" style="width:350px;height:265px;">');
			html.push('</div>');
			html.push('</div>');

			html.push('<div class="col-md-8">');
			html.push('<div class="overlay">');
			html.push('<div class="recent-work-inner">');
			html.push('<p style="font-size:16px;">');
			html.push(description);
			html.push('</p>');
			html.push('</div>');
			html.push('</div>');
			html.push('</div>');
			html.push('</div>');
			html.push('</div>');
			html.push('</div>');
			html.push('</section>');
		}
		document.write(html.join(''));
	}

	sendGetTechnologiesHttp();

})();













