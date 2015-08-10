/**
 * file:QQ登录
 * author:chenxy
 * date:2015-06-05
*/
//页面初始化
(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.isBind = true;
	g.token = Utils.getQueryString("token") || "";
debugger
	//验证登录状态
	var loginStatus = Utils.getUserInfo();
	if(!loginStatus && g.token != "" ){
		getUserInfo();
	}


	//获取个人资料
	function getUserInfo(){
		var token = g.token;
		sendGetUserInfoHttp(token);
	}

	//获取个人资料请求
	function sendGetUserInfoHttp(token){
		g.httpTip.show();
		var url = Base.profileUrl;
		var condi = {};
		condi.token = token;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			async: false,
			global:false,
			success: function(data){
				debugger
				console.log("qq_sendGetUserInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					Utils.offLineStore.set("userinfo",JSON.stringify(data.result.user),false);
					if(autoLogin){
						//保存自动登录数据
					}
					//location.href = "center.html";
					Utils.offLineStore.set("token",g.token,false);
				}
				else{
					Utils.offLineStore.remove("userinfo",false);
					Utils.offLineStore.remove("login_userprofile",false);
					var msg = data.error || "";
					alert("获取个人信息错误:" + msg);
					location.href = "login.html";
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
})();