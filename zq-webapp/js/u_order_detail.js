$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.orderId = Utils.getQueryString("id");
	g.httpTip = new Utils.httpTip({});

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getOrderDetail();
	}

	$("#enterbackbtn").bind('click',enterBackBtnUp);

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
		var payTime = obj.payTime || '';
		var username = obj.user.realName;
		var mobile = obj.user.mobile;
		var zfobj = {"WAITING":"未支付","PAYED":"进行中","PROCESSING":"进行中","CANCEL":"已取消","COMPLETE":"已完成","DRAWBACKING":"退款中","DRAWBACKED":"已退款"};
		var status = obj.status || "";
		var statusText = "";
		if(status !== ""){
			status = status.toUpperCase();
			statusText = zfobj[status] || "";
		}
		$("#orderName,#orderTitle").text(name);
		$("#orderCode").text(code);
		$("#orderStatus").text(statusText);

		if(status == "WAITING"){
			$("#payTime").text("尚未支付");
			var $pay = $('<a class="btn" href="paycheck.html?id='+ g.orderId +'">去支付</a>');
			var $cancel = $('<a class="btn" href="javascript:orderCancel(\'' + g.orderId + '\');">取消订单</a>');
			$('#orderDetail').append($pay);
			$('#orderDetail').append($cancel);
		}else if(status == "PAYED" || status == "PROCESSING"){
			//申请退款
			var $return = $('<a class="btn" href="javascript:orderDrawback(\'' + g.orderId + '\');" data-am-modal="{target: \'#my-prompt\'}">申请退款</a>');
			$('#orderDetail').append($return);
		}
		if(payTime){
			$("#payTime").text(payTime);
		}else{
			$("#payTime").text("尚未支付");
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

	function orderCancel(id){
		var url = Base.serverUrl + "/api/user/order/" + id + "/cancel";
		var condi = {};
		condi.token = g.token;
		condi.id = id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("orderCalcel",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("取消订单成功");
					setTimeout(function(){
						location.href="u_order.html?token="+g.token;
					},500);
				}
				else{
					var msg = data.error;
					Utils.alert("取消订单错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function orderDrawback(id){
		//~ var modal = $('#exampleModal');
		//~ modal.addClass("in");
		//~ modal.attr("aria-hidden",false);
		//~ modal.find('.modal-title').text('退款理由');
		//~ modal.find('.modal-body input').val("");
		//~ modal.show();
		g.backorderid = id;
	}
	function enterBackBtnUp(){
		var url = Base.serverUrl + "/api/user/order/" + g.backorderid + "/drawback";
		var condi = {};
		condi.token = g.token;
		condi.id = g.backorderid;
		condi.reason = $("#message-text").val() || "";

		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("enterBackBtnUp",data);
				var status = data.status || "";
				if(status == "OK"){
					//Utils.alert("订单申请退款成功");
					alert("订单申请退款成功:" + data.result);
					setTimeout(function(){
						location.href="u_order.html?token="+g.token;
					},500);
				}
				else{
					var msg = data.error;
					Utils.alert("订单申请退款错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	window.orderCancel = orderCancel;
	window.orderDrawback = orderDrawback;
});