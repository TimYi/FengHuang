$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.houseId = Utils.getQueryString("id");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});
	g.updateId = "";

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getHomeList();
	}

	$("#addhouse").attr("href","u_house_item.html?token="+g.token);

	function getHomeList(){
		var condi = {};
		condi.token = g.token;
		sendGetHomeListHttp(condi);
	}

	function sendGetHomeListHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeHomeListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取房屋信息错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changeHomeListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;
			var html = [];

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var city = obj[i].city.name;
				var districtName = obj[i].districtName || "";
				var area = obj[i].area || "";
				var houseType = obj[i].houseType || "";
				if(houseType !== ""){
					houseType = houseType.name;
				}
				var decorateType = obj[i].decorateType || "";
				if(decorateType !== ""){
					decorateType = decorateType.name;
				}

				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_house_item.html?id='+id+'&token='+g.token+'&p=2"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ districtName +'</li>');
                html.push('<li class="usmall">'+ city +' / '+ area+'㎡ / '+ houseType + ' / ' + decorateType +'</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_house_item.html?id='+id+'&token='+g.token+'&p=2">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}

			$("#houselist").html(html.join(''));
		}
	}


});


