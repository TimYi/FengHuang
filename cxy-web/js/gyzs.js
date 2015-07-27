
(function(){

	function sendGetTechnologiesHttp(){
		if(pageid !== ""){
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
		else{
			//alert("链接错误");
		}
	}


	function changeTechnologiesHtml(data){
		var obj = data.result || [];

		var html = [];
		html.push('<section style="border-top:1px solid #f6f6f6;">');
		html.push('<div class="container"  style="margin-bottom:-50px">');
		html.push('<div class="center wow fadeInDown">');
		html.push('<h2>电路改造工艺<span style="font-size:24px"> FREE SERVICE</span></h2>');
		html.push('<p class="lead"></p>');
		html.push('</div>');
		html.push('<div class="container">');
		html.push('<div class="row" style="margin:-20px 0 30px 0">');
		html.push('<div class="col-md-4">');
		html.push('<div class="recent-work-wrap">');
		html.push('<img class="img-responsive" src="assets/images/dl1.gif" alt="">');
		html.push('</div>');
		html.push('</div>');

		html.push('<div class="col-md-8">');
		html.push('<div class="overlay">');
		html.push('<div class="recent-work-inner">');
		html.push('<p style="font-size:16px;">');
		html.push('PVC线管线盒，暗管暗槽横平竖直，电线导管无折皱、无裂缝保证电线导管畅通。强弱电要分开，互不干扰很重要“左零右火中间零”安装面板要记牢.10A以上大功率电器，必须使用4mm的电源线设置专用供电回路，确保大功率电器稳定运行');
		html.push('</p>');
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		html.push('</section>');

		//document.write(html.join(''));
	}

	sendGetTechnologiesHttp();

})();













