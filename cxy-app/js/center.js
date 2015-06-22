/**
 * file:个人中心
 * author:chenxy
 * date:2015-05-23
*/
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.token = Utils.offLineStore.get("token",false);
	g.httpTip = new Utils.httpTip({});

	//验证登录状态
	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}
	else{
		getUserInfo();
		//sendMyInfoCountsHttp();
	}

	$("#loginoutbtn").bind("click",loginOut);

	//安全退出
	function loginOut(){
		Utils.offLineStore.remove("userinfo",false);
		location.replace("login.html");
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
			global:false,
			success: function(data){
				console.log("sendGetUserInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					setUserInfoHtml(data.result || {});
				}
				else{
					var msg = data.error || "";
					Utils.alert("获取个人信息错误:" + msg);
					location.replace("login.html");
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function setUserInfoHtml(data){
		var obj = data.user || {};
		var avatar = obj.avatar || "images/client2.png";
		var realName = obj.realName || "";
		var username = obj.username || "";

		var html = [];
		html.push('<img src="' + avatar + '" style="width:30px;height:30px;-moz-border-radius: 50px;-webkit-border-radius: 50px;border-radius:50px;border:2px solid #eee">');
		html.push('<span style="margin-left:10px;color:#000;font-size:14px">' + realName + ' <span style="color:#666;font-size:13px">(' + username + ' )</span></span>');

		$("#userinfo").html(html.join(''));
	}
});