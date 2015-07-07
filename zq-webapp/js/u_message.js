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
		getMyMessage();
	}

	//获取我的留言
	function getMyMessage(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;

		sendGetMyMessageHttp(condi);
	}

	//获取我的留言
	function sendGetMyMessageHttp(condi){
		g.httpTip.show();
		var url = Base.messagesUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeMessageListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的留言错误:" + msg);
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

	function changeMessageListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;
			var html = [];

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var title = obj[i].title;
				var content = obj[i].content || "";
				var readed = obj[i].readed || "";
				var createTime = obj[i].createTime || "";

				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_message_item.html?id='+id+'&token='+g.token+'&p=2"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ title +'</li>');
                html.push('<li class="usmall">'+ content +' / '+ createTime+'</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_message_item.html?id='+id+'&token='+g.token+'&p=2">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}

			$("#myMessage").html(html.join(''));
		}
	}

});