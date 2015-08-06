
//页面初始化
$(function(){
	var g = {};
	g.codeId = "";
	g.tout = null;
	g.httpTip = new Utils.httpTip({});

	$("#username").bind("blur",getImgCode);
	$("#logincodebtn").bind("click",getImgCode);
	//$("#regbtn").bind("click",openRegPage);
	$("#validate").bind("keydown",codeKeyDown);

	$("#loginbtn").bind("click",loginBtnUp);

	setTimeout(function(){
		getImgCode();
	},2000);
	//换一组图片
	function getImgCode(evt){
		var userName = $("#username").val() || "";
		if(userName !== ""){
			g.codeId = userName;
			console.log(g.codeId);
			$("#logincodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.codeId);

			clearTimeout(g.tout);
			g.tout = setTimeout(function(){
				getImgCode();
			},60000);
		}
	}

	function codeKeyDown(evt){
		evt = evt || event;
		if(evt.keyCode == 13){
			$("#loginbtn").trigger("click");
		}
	}

	//打开注册用户页面
	function openRegPage(evt){
		window.open("reg.html");
	}

	function loginBtnUp(evt){
		var userName = $("#username").val() || "";
		var pwd = $("#password").val() || "";
		var code = $("#validate").val() || "";
		if(userName !== ""){
			if(pwd !== ""){
				if(code !== ""){
					//var autoLogin = $("#autologin")[0].checked;
					var autoLogin = false;
					sendLoginHttp(userName,pwd,code,autoLogin);
				}
				else{
					$("#validate").focus();
				}
			}
			else{
				$("#password").focus();
			}
		}
		else{
			$("#username").focus();
		}
	}

	function sendLoginHttp(phone,pwd,code,autoLogin){
		var url = Base.loginUrl;
		var condi = {};
		condi.username = phone;
		condi.password = pwd;
		condi.captcha = code;

		g.httpTip.show();

		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendLoginHttp",data);
				//保存数据
				//Utils.offLineStore.set("userinfo_login",data);
				var status = data.status || "";
				if(status == "OK"){
					//保存用户数据
					Utils.offLineStore.set("userinfo",JSON.stringify(data.result.profile),false);
					if(autoLogin){
						//保存自动登录数据
					}
					var token = data.result.token || "";
					Utils.offLineStore.set("token",token,false);
					//history.go(-1);
					location.href = "hyzx.html?token=" + token + "&p=0";
				}
				else{
					var msg = data.errorDescription || "";
					alert("登录失败:" + msg);
					getImgCode();
				}

				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});