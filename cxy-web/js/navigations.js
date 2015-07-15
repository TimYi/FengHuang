/**
 * file:案例直播
 * author:chenxy
 * date:2015-06-05
*/

(function(window){
	window._pageId = {};


	function sendGetNavigationHttp(){
		var url = Base.serverUrl + "/api/navigations";
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
				console.log("sendGetNavigationHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeNavigationHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取导航栏错误:" + msg);
				}
				//g.httpTip.hide();
			},
			error:function(data){
				//g.httpTip.hide();
			}
		});
	}


	function changeNavigationHtml(data){
		var obj = data.result || [];
		var html = [];

		html.push('<div class="container">');

		html.push('<div class="navbar-header">');
		html.push('<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">');
		html.push('<span class="sr-only">Toggle navigation</span>');
		html.push('<span class="icon-bar"></span>');
		html.push('<span class="icon-bar"></span>');
		html.push('<span class="icon-bar"></span>');
		html.push('</button>');
		var page = location.href.indexOf("/center/");
		if(page > -1){
			html.push('<a class="navbar-brand" href="../index.html"><img src="assets/images/fhzj.jpg" alt="logo"></a>');
		}
		else{
			html.push('<a class="navbar-brand" href="index.html"><img src="assets/images/fhzj.jpg" alt="logo"></a>');
		}

		html.push('</div>');

		html.push('<div class="collapse navbar-collapse navbar-right">');
		html.push('<ul id="navul" class="nav navbar-nav">');

		//class="active"

		var pageid = {};

		for(var i = 0,len = obj.length; i < len; i++){
			var sub = obj[i].subNavigations || [];
			var dropdown = sub.length > 0 ? "dropdown" : "";
			var url = obj[i].url || "";
			if(url == ""){
				url = "javascript:void(0);";
			}
			else{
				url = url.substring(1);
			}
			var title = obj[i].title || "";
			var id = obj[i].id || "";
			html.push('<li class="' + dropdown + '">');

			if(dropdown === ""){
				if(page > -1){
					html.push('<a href="../' + url + '">' + title + '</a>');
				}
				else{
					html.push('<a href="' + url + '">' + title + '</a>');
				}
				pageid[url] = id;
			}
			else{
				html.push('<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">' + title + '<i class="fa fa-angle-down"></i></a>');
				html.push('<ul class="dropdown-menu">');
			}

			for(var j = 0,jlen = sub.length; j < jlen; j++){
				var url = sub[j].url || "";
				if(url == ""){
					url = "javascript:void(0);";
				}
				else{
					url = url.substring(1);
				}
				var title = sub[j].title || "";
				var id = sub[j].id || "";
				if(page > -1){
					html.push('<li><a href="../' + url + '">' + title + '</a></li>');
				}
				else{
					html.push('<li><a href="' + url + '">' + title + '</a></li>');
				}
				pageid[url] = id;
				//<li style="padding:0 5px;margin:5px 0;border-bottom:1px solid #555"></li>
			}
			if(dropdown !== ""){
				html.push('</ul>');
			}
		}

		html.push('</ul>');
		html.push('</div>');
		html.push('</div><!--/.container-->');

		$("#navdiv").html(html.join(''));

		window._pageId = pageid;
	}

	sendGetNavigationHttp();
})(window);


