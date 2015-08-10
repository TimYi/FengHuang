
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.orderId = Utils.getQueryString("orderId") || "";
	g.orderResult = Utils.getQueryString("result") || "";
	g.payprice = 0;
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
	if(g.orderResult == "false"){
		//支付失败
		$('.pay-ok').hide();
		$('.pay-error').show();
		$('#payAgain').attr('href','paycheck.html?id='+g.orderId);
	}

});








