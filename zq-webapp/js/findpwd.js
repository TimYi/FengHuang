
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.httpTip = new Utils.httpTip({});
	g.pwdvalidater = "";
	g.userName = "";

	$("#inputPhone3").bind("blur",getImgCode);
	$("#imgcodebtn").bind("click",getImgCode);
	$("#getcodebtn").bind("click",getValidCode);
	$("#nextbtn").bind("click",nextBtnUp);
	$("#resetbtn").bind("click",resetUserInfo);

	$("#sendbtn2").bind("click",enterChangePwd);
	$("#resetbtn2").bind("click",resetPwdInfo);

	//获取图形验证码
	function getImgCode(evt){
		var phone = $("#inputPhone3").val() || "";
		if(phone !== ""){
			g.imgCodeId = phone;
			//$("#imgcodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId);
			$("#imgcodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId + "&t=" + (new Date() - 0));
		}
	}

	//获取验证码
	function getValidCode(evt){
		var ele = evt.currentTarget;
		//$(ele).removeClass("curr");
		//if(!this.moved){}
		var p = $("#inputPhone3").val() || "";
		var imgCode = $("#inputImgCode3").val() || "";
		var username = $("#inputEmail3").val() || "";
		if(username !== ""){
			if(p !== ""){
				var reg = /^1[3,5,7,8]\d{9}$/g;
				if(reg.test(p)){
					if(imgCode !== ""){
						g.phone = p;
						if(!g.sendCode){
							sendGetCodeHttp(imgCode);
						}
					}
					else{
						Utils.alert("输入图形验证码");
						$("#inputImgCode3").focus();
					}
				}
				else{
					Utils.alert("手机输入不合法");
				}
			}
			else{
				$("#inputPhone3").focus();
			}
		}
		else{
			Utils.alert("输入注册用户名");
			$("#inputEmail3").focus();
		}
	}

	//重新获取验证码
	function resetGetValidCode(){
		g.sendTime = g.sendTime - 1;
		if(g.sendTime > 0){
			$("#getcodebtn").html(g.sendTime + "秒后重新发送");
			setTimeout(function(){
				resetGetValidCode();
			},1000);
		}
		else{
			$("#getcodebtn").html("重新发送");
			g.sendTime = 60;
			g.sendCode = false;

			//重新获取图形验证码,1分钟有效
			getImgCode();
			$("#inputImgCode3").val("");
			$("#inputImgCode3").focus();
		}
	}

	//重置信息
	function resetUserInfo(evt){
		$("#inputEmail3").val("");
		$("#inputPhone3").val("");
		$("#inputImgCode3").val("");
		$("#inputCode3").val("");
	}
	function resetPwdInfo(){
		$("#inputPassword3").val("");
		$("#inputPassword4").val("");
	}

	//下一步
	function nextBtnUp(evt){
		var userName = $("#inputEmail3").val() || "";
		var phone = $("#inputPhone3").val() || "";
		var imgCode = $("#inputImgCode3").val() || "";
		var validCode = $("#inputCode3").val() || "";

		var regEMail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var regPhone = /^1[3,5,7,8]\d{9}$/;
		var regFont = /^([\u4E00-\u9FA5|\w\-])+$/;
		if(regEMail.test(userName) || regPhone.test(userName) || regFont.test(userName)){
			if(userName !== "" && phone !== "" && imgCode !== "" && validCode !== ""){
				sendForgetPassWordHttp(userName,phone,imgCode,validCode);
			}
			else{
				Utils.alert("用户信息未填");
			}
		}
		else{
			Utils.alert("用户名输入错误,请输入邮箱或者手机号");
			$("#inputEmail3").focus();


			//$("#pwdfirstdiv").hide();
			//$("#pwdseconddiv").show();
		}

	}

	//请求验证码
	function sendGetCodeHttp(imgCode){
		var url = Base.getCodeUrl;
		var condi = {};
		condi.mobile = g.phone;
		condi.captcha = imgCode;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				var status = data.status || "";
				if(status == "OK"){
					g.sendCode = true;
					$("#getcodebtn").html("60秒后重新发送");
					setTimeout(function(){
						resetGetValidCode();
					},1000);
				}
				else{
					alert("验证码获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	//申请忘记密码
	function sendForgetPassWordHttp(userName,phone,imgCode,validCode){
		var url = Base.serverUrl + "/api/forgetPassword";
		var condi = {};
		condi.username = userName;
		condi.telephone = phone;
		//condi.captcha = imgCode;
		condi.validater = validCode;
		g.httpTip.show();
		$.ajax({
			url:url,
			type:"POST",
			data:condi,
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				g.httpTip.hide();
				console.log("sendForgetPassWordHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					var pwdvalidater = data.result || "";
					g.pwdvalidater = pwdvalidater;
					g.userName = userName;
					$("#pwdfirstdiv").hide();
					$("#pwdseconddiv").show();
				}
				else{
					var msg = data.error + "," + data.errorDescription;
					Utils.alert(msg);

					resetUserInfo();
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	//重置密码
	function enterChangePwd(){
		var inputPassword3 = $("#inputPassword3").val() || "";
		var inputPassword4 = $("#inputPassword4").val() || "";

		if(inputPassword3 !== "" && inputPassword4 !== ""){
			if(inputPassword3 == inputPassword4){
				sendChangeForgetPassWordHttp(inputPassword4);
			}
			else{
				Utils.alert("两次密码不一致");
				 $("#inputPassword4").val("");
				 $("#inputPassword4").focus();
			}
		}
		else{
			if(inputPassword3 === ""){
				 $("#inputPassword3").focus();
			}
			else{
				 $("#inputPassword4").focus();
			}
		}
	}

	//申请忘记密码
	function sendChangeForgetPassWordHttp(password){
		var url = Base.serverUrl + "/api/changeForgotPassword";
		var condi = {};
		condi.username = g.userName;
		condi.validater = g.pwdvalidater;
		condi.password = password;
		g.httpTip.show();
		$.ajax({
			url:url,
			type:"POST",
			data:condi,
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				g.httpTip.hide();
				console.log("sendChangeForgetPassWordHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("密码重置成功");
					location.href = "login.html";
				}
				else{
					var msg = data.error + "," + data.errorDescription;
					Utils.alert(msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});