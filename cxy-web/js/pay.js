
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

	var condi = Utils.offLineStore.get("pay_info",false) || "";
	if(condi !== ""){
		var obj = JSON.parse(condi);
		orderPlay(obj);
	}
	function orderPlay(obj){
		//https://124.74.239.32/payment/main
		//https://ebank.spdb.com.cn/payment/main
		g.httpTip.show();
		$("#transName").val(obj.transName);
		$("#Plain").val(obj.plain);
		$("#Signature").val(obj.signature);
		$("#bankform").submit();
	}
});









