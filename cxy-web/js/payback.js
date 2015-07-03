
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.orderId = Utils.getQueryString("id") || "";
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	g.reserveStatus = false;
	if(!g.loginStatus){
		location.replace("index.html");
	}

	$("#backbtn").bind("click",back);

	getOrderDetail();

	function back(){
		location.replace("index.html");
	}

	function getOrderDetail(){
		//~ token:用户凭据
		//~ id：订单id，
		var condi = {};
		condi.token = g.token;
		condi.id = g.orderId;
		if(g.loginStatus && g.orderId !== ""){
			sendGetOrderDetailsHttp(condi);
		}
	}
	function sendGetOrderDetailsHttp(condi){
		var url = Base.order + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetOrderDetailsHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeOrderDetailsHtml(data.result);
				}
				else{
					var msg = data.error || "";
					Utils.alert("获取订单详情错误" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function changeOrderDetailsHtml(data){
		//~ id:id
		//~ price:售出的实际价格
		//~ status:处理状态
		//~ count:数量
		//~ name:商品名称
		//~ type:订单的商品类型
		//~ mainPic:主图
		//~ shopid:店铺id
		//~ shop:店铺名称
		//~ good:具体的商品信息，目前这个就是购买的套餐的信息
		var obj = data || {};
		var price = obj.price || "";
		var orderId = g.orderId;
		var html = [];
		html.push('<li style="font-weight:800;font-size:20px">您已支付成功</li>');
		html.push('<li style="font-size:16px">交易时间：2015年6月29日 13:30:25</li>');
		html.push('<li class="u_li">金额：' + price + '元</li>');
		html.push('<li class="u_li">流水号：<b style="color:#83bd39">' + orderId + '</b></li>');
		html.push('<li class="u_li">');
		html.push('<br/>本页面可作为付款凭证。');
		html.push('</li>');
		$("#orderdetail").html(html.join(''));
		$("#orderdetail").show();
	}


});









