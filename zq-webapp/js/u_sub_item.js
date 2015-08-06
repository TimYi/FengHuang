$(function(){
	var g = {};
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.pid = Utils.getQueryString("id");

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}else{
		getMySubDetail();
	}

	function getMySubDetail(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.pid;
		sendGetMySubDetailHttp(condi);
	}

	function sendGetMySubDetailHttp(condi){
		var url = Base.serverUrl+'/api/user/museumAppoint/'+g.pid;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				var status = data.status || "";
				if(status == "OK"){
					changeSubDetailHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的预约详情错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
			}
		});
	}

	function changeSubDetailHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];
			var arr ={"WAITING":"等待客服确认","PROCESSING":"处理中","FINISH":"已到店","CANCEL":"取消"};
			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i];
				var id = d.id || "";
				var code = d.code || '';
				var city = d.city || '';
				var city_name = city.name ||'';
				var type = d.type.name || "";
				var createTime = d.createTime || "";
				var status = d.status;
				if(status !== ""){
					status = arr[status] || "";
				}
				createTime.substring(0,10);
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_sub_item.html?id='+id+'&token='+g.token+'&p=5"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ city_name+type +'<span style="float:right;color:#666;font-size:14px;">'+status+'</span></li>');
                html.push('<li class="usmall">'+ createTime + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_sub_item.html?id='+id+'&token='+g.token+'&p=5">');
                html.push('<a href="javascript:void(0)">')
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}

			$("#mySub").html(html.join(''));
		}
	}

});