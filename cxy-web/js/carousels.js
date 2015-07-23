/**
 * file:案例直播
 * author:chenxy
 * date:2015-06-05
*/

(function(){
	var pageId = window._pageId || {};
	var url = location.href || "";
	var index = url.indexOf(".html");
	url = url.replace("#","");
	if(index > 16){
		url = url.substring(url.lastIndexOf("/") + 1);
	}
	else{
		//直接输入官网,定死首页
		url = "index.html";
	}
	var pageid = pageId[url] || "";
	function sendGetCarouselsHttp(){
		if(pageid !== ""){
			var url = Base.serverUrl + "/api/page/" + pageid + "/carousels";
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
					console.log("sendGetCarouselsHttp",data);
					var status = data.status || "";
					if(status == "OK"){
						changeCarouselsHtml(data);
					}
					else{
						var msg = data.error || "";
						Utils.alert("获取轮播图错误:" + msg);
					}
					//g.httpTip.hide();
				},
				error:function(data){
					//g.httpTip.hide();
				}
			});
		}
		else{
			alert("链接错误");
		}
	}


	function changeCarouselsHtml(data){
		var obj = data.result || [];
		var html = [];
		html.push('<section id="main-slider" class="no-margin">');
		html.push('<div class="carousel slide">');
		html.push('<ol class="carousel-indicators">');
		for(var i = 0, len = obj.length; i < len; i++){
			var css = i == 0 ? "active" : "";
			html.push('<li data-target="#main-slider" data-slide-to="' + i + '" class="' + css + '"></li>');
		}
		html.push('</ol>');

		html.push('<div class="carousel-inner">');

		for(var i = 0, len = obj.length; i < len; i++){
			var bgimg = obj[i].pic || "";
			if(bgimg !== ""){
				bgimg = bgimg.url || "images/tyg_bg_01.jpg";
			}
			else{
				bgimg = "images/tyg_bg_01.jpg";
			}
			var css = i == 0 ? "active" : "";
			var title = obj[i].title || "";
			var content = obj[i].content || "";
			var buttonText = obj[i].buttonText || "";
			var rightArea = obj[i].rightArea || "";

			var content = obj[i].fragment || ""
			if(content !== ""){
				content = content.templateContent || "";
				if(i > 0){
					content = content.replace("active","");
				}
			}
			html.push(content);
			/*
			html.push('<div class="item ' + css + '" style="background-image: url(' + bgimg + ')">');
			html.push('<div class="container">');
			html.push('<div class="row slide-margin">');
			html.push('<div class="col-sm-6">');
			html.push('<div class="carousel-content">');
			html.push('<h1 class="animation animated-item-1" style="font-weight: normal;text-shadow: 0 0 2px #333;">' + title + '</h1>');
			html.push('<h2 class="animation animated-item-2" style="font-weight: normal;text-shadow: 0 0 2px #333;">' + content + '</h2>');

			if(buttonText !== ""){
				html.push('<div class="animation animated-item-3" style="margin-top:20px;height:45px;width:160px;background:orange;-moz-border-radius:7px;-webkit-border-radius:7px;border-radius:7px;">')
				html.push('<a class="subbtn" href="center/reg.html" ><div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">' + buttonText + '</div></a>');
				html.push('</div>');
			}
			html.push('</div>');
			html.push('</div>');

			if(rightArea !== ""){
				html.push('<div class="col-sm-6 hidden-xs animation animated-item-4">');
				html.push('<div class="slider-img" style="margin-top:130px">');
				html.push('<a class="subbtn" href="center/reg.html" >');
				html.push('<img src="images/tyg_ticket_01.png" class="img-responsive">');
				html.push('</a>');
				html.push('</div>');
				html.push('</div>');
			}

			html.push('</div>');
			html.push('</div>');
			html.push('</div>');
			*/
		}

		html.push('</div>');
		html.push('</div>');
		html.push('<a class="prev hidden-xs" href="#main-slider" data-slide="prev">');
		html.push('<i class="fa fa-chevron-left"></i>');
		html.push('</a>');
		html.push('<a class="next hidden-xs" href="#main-slider" data-slide="next">');
		html.push('<i class="fa fa-chevron-right"></i>');
		html.push('</a>');
		html.push('</section>');
		if(html.length > 14){
			document.write(html.join(''));
		}
	}

	sendGetCarouselsHttp();
})();



$(function(){
	var loginStatus = Utils.getUserInfo();

	var subbtn = $(".subbtn");
	for(var i = 0,len = subbtn.length; i < len; i++){
		var btn = $(subbtn[i]);
		var href = btn.attr("href");
		if(href == "#TYG_XM_BTNTITLE#" || href == "#TYG_BJ_BTNTITLE#"){
			//预约体验馆
			if(loginStatus){
				btn.attr("href","subappoint.html");
			}
			else{
				btn.attr({"href":"#","data-toggle":"modal","data-target":"#exampleModal","data-whatever":"@mdo"});
			}
		}
		else if(href == "#"){
			btn.attr("href","javascript:void(0);");
		}
	}

});
