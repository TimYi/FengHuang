
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
		return;
	}

	if(g.orderId === ""){
		alert("没有得到订单ID");
		location.replace("index.html");
		return;
	}

	$("#playnextbtn").bind("click",getPayCondi);

	getOrderDetail();
	getMyCoupon();
	//获取我的优惠券
	function getMyCoupon(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.notUsed = true;

		sendGetMyCouponHttp(condi);
	}

	function sendGetMyCouponHttp(condi){
		var url = Base.couponsUrl ;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyCouponHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeCouponListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取我的优惠券错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}

	function changeCouponListHtml(data){
		var obj = data.result || [];
		var html = [];
		for(var i = 0,len = obj.length; i < len; i++){
			var id = obj[i].id || "";
			var name = obj[i].name || "";
			html.push('<label class="radio-inline" style="padding-right:30px">');
			html.push('<input type="radio" name="couponRadioOptions" value="' + id + '">' + name);
			html.push('</label>');
		}

		$("#couponlist").html(html.join(''));
		$("#couponlist").show();

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
		html.push('<li style="font-weight:800;font-size:20px">您的订单已抢购成功！付定金咯～</li>');
		html.push('<li style="font-size:16px">成功付款后，将有专属导购一对一为您服务！</li>');
		html.push('<li class="u_li">定金金额：' + price + '元</li>');
		html.push('<li class="u_li">订单号：<b style="color:#83bd39">' + orderId + '</b></li>');
		//html.push('<li class="u_li">备注：陈宣宇 / 136****0909 / 北京市海淀区***小区 / 3居室 / 95㎡ </li>');
		$("#orderdetail").html(html.join(''));
		$("#orderdetail").show();
	}

	function getPayCondi(){
		var url = Base.orderPlay + "/" + g.orderId + "/pay/pufa";
		var condi = {};
		condi.token = g.token;
		condi.orderId = g.orderId;
		var coupon = $("input[name='couponRadioOptions']:checked").val() || "";
		condi.couponsId = coupon;
		var check = $("input[name='inlineRadioOptions']:checked").val() || "";
		if(check === ""){
			alert("请选择支付银行");
			return;
		}

		condi.payBank = check;
		var accountType = $("input[name='inlineRadioAccountType']:checked").val() || "";
		if(accountType === ""){
			alert("请选择支付类型");
			return;
		}
		condi.accountType = accountType;//借记卡：JIEJJI,信用卡：XINYONG，前端写死。
		//~ token:用户凭据
		//~ orderId：订单id，路径参数
		//~ couponsIds:用户选择使用的优惠券id，可以是多个
		//~ payBank:支付银行，详情请见{支付过程}附表A
		//~ accountType:支付方式，详情见{支付过程}附表B
		g.httpTip.show();
		console.log("sendGetOrderDetailsHttp--condi",condi);
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetOrderDetailsHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					orderPlay(data.result);
				}
				else{
					var msg = data.error || "";
					Utils.alert("获取支付参数错误" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function orderPlay(obj){
		//https://124.74.239.32/payment/main
		//https://ebank.spdb.com.cn/payment/main
		//g.httpTip.show();
		$("#transName").val(obj.transName);
		$("#Plain").val(obj.plain);
		$("#Signature").val(obj.signature);
		$("#bankform").submit();
		//Utils.offLineStore.set("pay_info",JSON.stringify(obj),false);
		//location.href = "pay.html";
	}

});









