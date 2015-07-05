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

	getMyMessage();

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

	function deleteMessageItem(){
		var id = this.id;
		var index = id.split("_")[1];
		var obj = g.listdata[index];
		var mid = obj.id;
		var condi = {};
		condi.token = g.token;
		condi.id = mid;
		console.log(condi);
		sendDeleteListInfoHttp(condi);
	}

	//修改我的留言列表
	function changeMessageListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			for(var i = 0,len = obj.length; i < len; i++){
				var msg = obj[i].content || "";
				var name = obj[i].sender || "系统管理员";
				var time = obj[i].createTime || "2015-06-02 10:00";
				var id = obj[i].id || "";
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_message_item.html?id='+id+'&token='+g.token+'&p=4"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ msg +'</li>');
                html.push('<li class="usmall">'+ name +' / '+ time + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_message_item.html?id='+id+'&token='+g.token+'&p=4">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}

			$("#myMessage").html(html.join(''));
			//$("#messagetable .delete").bind("click",deleteMessageItem);
		}
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
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeMessageListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的留言错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.messageUrl + "/" + condi.id;
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
					Utils.alert("删除留言成功");
					getMyMessage();
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的留言错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});