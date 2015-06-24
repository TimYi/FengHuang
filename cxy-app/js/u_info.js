/**
 * file:个人资料
 * author:chenxy
 * date:2015-06-05
*/
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.isBind = true;
	g.token = Utils.offLineStore.get("token",false);
	g.page = Utils.getQueryString("p") - 0;
	g.httpTip = new Utils.httpTip({});
	g.data = {};
	g.updateCondi = {};
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
	$("#nikenamebtn").bind("click",changePage);
	$("#validnamebtn").bind("click",changePage);
	$("#enamebtn").bind("click",changePage);
	//$("#phonebtn").bind("click",changePage);
	$("#emailbtn").bind("click",changePage);
	$("#messagebtn").bind("click",changePage);
	$("#professionbtn").bind("click",changePage);

	$("#constellation").bind("change",changeconstellation);
	$("#bloodgroup").bind("change",changebloodgroup);
	/*
	$("#phonetext").bind("blur",getImgCode);
	$("#updatepwdcodeimg").bind("click",getImgCode);
	$("#sendbtn").bind("click",getPhoneCode);
	$("#bindbtn").bind("click",bindPhone);
	*/

	function changePage(){
		var id = this.id;
		var condi = g.updateCondi;
		var url = "";
		switch(id){
			case "nikenamebtn":
				condi.k = "cnname";
				condi.v = g.data.cnname;
				url = "u_info/u_info_nicheng.html";
			break;
			case "validnamebtn":
				condi.k = "realName";
				condi.v = g.data.realName;
				url = "u_info/u_info_name.html";
			break;
			case "enamebtn":
				condi.k = "ename";
				condi.v = g.data.ename;
				url = "u_info/u_info_ename.html";
			break;
			case "phonebtn":
				condi.k = "mobile";
				condi.v = g.data.mobile;
				url = "u_info/u_info_phone.html";
			break;
			case "emailbtn":
				condi.k = "email";
				condi.v = g.data.email;
				url = "u_info/u_info_email.html";
			break;
			case "messagebtn":
				condi.k = "intro";
				condi.v = g.data.intro;
				url = "u_info/u_info_jianjie.html";
			break;
			case "professionbtn":
				condi.k = "profession";
				condi.v = g.data.profession;
				url = "u_info/u_info_profession.html";
			break;
		}
		Utils.offLineStore.set("userinfo_update",JSON.stringify(condi),false);
		location.href = url;
	}

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

	function setUserInfoHtml(data){
		var obj = data.user || {};
		g.data = obj;
		var nikeName = obj.cnname || "";
		var validName = obj.realName || "";
		var eName = obj.ename || "无";
		var sex = obj.sex || "404040e64dd26ab5014dd26ac61f0013";
		var message = obj.intro || "";
		var email = obj.email || "";
		var phone = obj.mobile || "";
		var qq = obj.qqnum || "";
		var weixin = obj.weixinnum || "";
		var birthday = obj.birthDay || "";
		var profession = obj.trade || "";
		var address = obj.address || "";
		var constellation = obj.constellation || {};
		var bloodgroup = obj.bloodType || {};

		g.username = obj.username;
		Base.userName = obj.username;

		//获取验证码
		//getImgCode();

		//昵称
		$("#nikename").html(nikeName);
		//真实姓名
		$("#validname").html(validName);
		//英文名
		$("#ename").html(eName);
		$("#phone").html(phone);
		//电子邮箱
		$("#emailtext").html(email);

		//星座
		$("#constellation").val(constellation.id);
		//行业
		$("#profession").html(profession);
		//血型
		$("#bloodgroup").val(bloodgroup.id);

		//个人简介
		$("#message").html(message);

		/*
		//性别1男2女
		if(sex !== "404040e64dd26ab5014dd26ac61f0013"){
			if($("#inlineRadio2").length > 0){
				$("#inlineRadio2")[0].checked = true;
			}
		}
		if(phone != ""){
			//手机号
			$("#phonetext").val(phone);
			$("#bindbtn").html("解绑");
			g.isBind = false;
		}
		//QQ号
		$("#qqtext").val(qq);
		//微信
		$("#weixintext").val(weixin);
		//生日
		$("#birthday").val(birthday);

		//现居住地
		$("#address").val(address);
		*/
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
					g.updateCondi = data.result.user;
					setUserInfoHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取个人信息错误:" + msg);
					location.replace = "login.html";
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	//更新个人资料
	function changeconstellation(){
		var condi = {};
		condi.token = g.token;

		//血型
		//condi.bloodTypeId = $("#bloodgroup").val();
		//星座
		condi.constellationId = $("#constellation").val();
		console.log(condi);
		sendUpdateUserInfoHttp(condi);
	}

	function changebloodgroup(){
		var condi = {};
		condi.token = g.token;

		//血型
		condi.bloodTypeId = $("#bloodgroup").val();
		console.log(condi);
		sendUpdateUserInfoHttp(condi);
	}

	//更新个人资料请求
	function sendUpdateUserInfoHttp(obj){
		g.httpTip.show();
		var url = Base.profileUrl;
		$.ajax({
			url:url,
			data:obj,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					alert("修改个人资料成功");
				}
				else{
					alert("修改个人资料失败");
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

});