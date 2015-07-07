$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.orderId = Utils.getQueryString("id");

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getOrderDetail();
	}


	function getOrderDetail(){
		var condi = {};
		condi.token = g.token;
		condi.page = g.orderId;

		sendGetOrderDetailHttp(condi);
	}

	function changeOrderDetailHtml(data){
		var obj = data|| [];
		var name = obj.name;
		var code = obj.code;
		var price = obj.price;
		var status = obj.status;
		var payTime = obj.payTime;
		var username = obj.user.realName;
		var mobile = obj.user.mobile;
		$("#orderName,#orderTitle").text(name);
		$("#orderCode").text(code);
		if(payTime){
			$("#payTime").text(payTime);
		}
		$("#userName").text(username);
		$("#mobile").text(mobile);
		$("#orderPrice").text(price);
	}

	function sendGetOrderDetailHttp(condi){
		var url = Base.order+'/'+g.orderId;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetOrderDetailHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeOrderDetailHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取订单详情错误:" + msg);
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