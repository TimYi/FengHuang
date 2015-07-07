$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getMyFav();
	}
	//获取我的收藏
	function getMyFav(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;

		sendGetMyFavHttp(condi);
	}

	function deleteFavItem(){
		var id = this.id;
		var index = id.split("_")[1];
		var obj = g.listdata[index];
		var mid = obj.id;
		var condi = {};
		condi.token = g.token;
		condi.id = mid;

		sendDeleteListInfoHttp(condi);
	}

	//修改我的收藏列表
	function changeFavListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var name = obj[i].name || "";
				var column = obj[i].column || "";
				var time = obj[i].createTime || "";
				var type = obj[i].type.toLowerCase();
				var source = obj[i].sourceid;
				var urlstr = type+"_detail.html?id="+source;
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="'+urlstr+'"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ column +'</li>');
                html.push('<li class="usmall">'+ name + ' / ' + time + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="'+urlstr+'">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}
			$("#myFav").html(html.join(''));
		}
	}

	//获取我的收藏
	function sendGetMyFavHttp(condi){
		var url = Base.collectsUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyFavHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeFavListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的收藏列表错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
			}
		});
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.collectUrl + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			headers:{"fhzj_auth_token":condi.token},
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendDeleteListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("删除收藏成功");
					getMyFav();
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的收藏错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});