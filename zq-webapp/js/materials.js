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
					changeMeterialsHtml(data);
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

	function changeMeterialsHtml(data){
		var obj = data.result || [];
		var logo_html = [];
		var phtml = [];
		for(var i = 0,len=obj.length;i<len;i++){
			var logosrc = obj[i].logo.url || '';
			if(logosrc != ''){
				logo_html.push('<li><img src="'+ logosrc +'"></li>');
			}

			var logoName = obj[i].name || "";
			var products = obj[i].products || [];
			for(var k = 0,klen = products.length; k < klen; k++){
				var pname = products[k].name || "";

				phtml.push('<tr>');
				if(k == 0){
					phtml.push('<td rowspan=' + klen + '>' + logoName + '</td>');
				}
				phtml.push('<td>' + pname + '</td>');
				phtml.push('<td>');

				var meterias = products[k].materials || [];
				for(var n = 0,nlen = meterias.length; n < nlen; n++){
					var mpic = meterias[n].pic || "";
					if(mpic !== ""){
						mpic = mpic.url;
					}
					phtml.push('<img src="' + mpic + '">');
				}
				phtml.push('</td>');
				phtml.push('</tr>');
			}
		}
		$('#logoWrap').html(logo_html.join(''));
		$('#mTable tbody').html(phtml.join(''));
	}
})