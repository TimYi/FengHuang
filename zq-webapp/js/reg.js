/**
 * file:注册
 * author:chenxy
 * date:2015-05-23
*/
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.httpTip = new Utils.httpTip({});

	$("#username").bind("blur",getImgCode);
	$("#regcodebtn").bind("click",getImgCode);
	$("#getcodebtn").bind("click",getValidCode);
	$("#sendcodebtn").bind("click",resetRegInfo);
	$("#sendbtn").bind("click",regBtnUp);



	//获取图形验证码
	function getImgCode(evt){
		var phone = $("#username").val() || "";
		if(phone !== ""){
			//console.log(phone);
			g.imgCodeId = phone;
			$("#regcodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId);
		}
	}

	//获取验证码
	function getValidCode(evt){
		var ele = evt.currentTarget;
		//$(ele).removeClass("curr");
		//if(!this.moved){}
		var p = $("#mobile").val() || "";
		var imgCode = $("#img-validate").val() || "";
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
					console.log("输入图形验证码");
					$("#img-validate").focus();
				}
			}
			else{
				alert("手机输入不合法");
			}
		}
		else{
			console.log("没填手机号");
			$("#mobile").focus();
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

			//重新获取短信验证码,1分钟有效
			getValidCode();
			$("#validate").val("");
			$("#validate").focus();
		}
	}

	//重置信息
	function resetRegInfo(evt){
		//$("#inputEmail3").val("");
		$("#password").val("");
		$("#mobile").val("");
		$("#img-validate").val("");
		$("#validate").val("");
	}

	//注册
	function regBtnUp(evt){
		var userName = $("#username").val() || "";
		var usePwd = $("#password").val() || "";
		var phone = $("#mobile").val() || "";
		var imgCode = $("#img-validate").val() || "";
		var validCode = $("#validate").val() || "";

		//var regEMail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var regPhone = /^1[3,5,7,8]\d{9}$/;
		var regFont = /^([\u4E00-\u9FA5|\w\-])+$/;
		if(regPhone.test(phone) || regFont.test(userName)){
			if(userName !== "" && usePwd !== "" && phone !== "" && imgCode !== "" && validCode !== ""){
				sendRegHttp(userName,usePwd,validCode);
				//http://101.200.229.135:8080/api/regist?username=ytm&password=123456&mobile=18612444099&validater=3967
			}
			else{
				alert("账户信息未填写完全");
			}
		}
		else{
			alert("用户名输入错误,请输入邮箱或者手机号");
			//$("#username").focus();
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

	//注册
	function sendRegHttp(userName,usePwd,validCode){
		var url = Base.regUrl;
		var condi = {};
		condi.username = userName;
		condi.password = usePwd;
		condi.mobile = g.phone;
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
				console.log(data);
				var status = data.status || "";
				if(status == "OK"){
					var token = data.result || "";
					//保存token
					Utils.offLineStore.set("token",token,false);
					//保存用户数据
					Utils.offLineStore.set("userinfo",JSON.tostringify(condi),false);
					location.href = "center.html";
				}
				else{
					var msg = data.error;
					alert(msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});