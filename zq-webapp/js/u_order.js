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

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getMyOrder();
	}

	//获取我的订单
	function getMyOrder(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = "";

		sendGetMyOrderHttp(condi);
	}

	//修改我的订单列表
	function changeOrderListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id|| "";
				var name = obj[i].name || "";
				var type = obj[i].type || "";
				var time =  "2015-06-02 10:00";
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_order_item.html?id='+id+'&token='+g.token+'&p=7"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ name +'</li>');
                html.push('<li class="usmall">'+ type + ' / '+ time + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="u_order_item.html?id='+id+'&token='+g.token+'&p=7">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}

			$("#myOrder").html(html.join(''));
		}
	}

	function sendGetMyOrderHttp(condi){
		var url = Base.orderUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyOrderHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeOrderListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的订单错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
			}
		});
	}
});