/**
 * file:登录
 * author:chenxy
 * date:2015-05-23
*/

//页面初始化
$(function(){
	var g = {};
	g.codeId = "";
	g.tout = null;

	$("#inputEmail3").bind("blur",getImgCode);
	$("#loginbtn").bind("click",loginBtnUp);
	$("#regbtn").bind("click",openRegPage);
	$("#inputCode3").bind("keydown",codeKeyDown);

	setTimeout(function(){
		getImgCode();
	},2000);
	//换一组图片
	function getImgCode(evt){
		var userName = $("#inputEmail3").val() || "";
		if(userName !== ""){
			g.codeId = userName;
			console.log(g.codeId);
			$("#updatecodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.codeId);

			clearTimeout(g.tout);
			g.tout = setTimeout(function(){
				getImgCode();
			},60000);
		}
	}

	function codeKeyDown(evt){
		evt = evt || event;
		if(evt.keyCode == 13){
			//
			$("#loginbtn").trigger("click");
		}
	}

	//打开注册用户页面
	function openRegPage(evt){
		window.open("reg.html");
	}

	function loginBtnUp(evt){
		var userName = $("#inputEmail3").val() || "";
		var pwd = $("#password").val() || "";
		var code = $("#inputCode3").val() || "";
		if(userName !== ""){
			if(pwd !== ""){
				if(code !== ""){
					//var autoLogin = $("#autologin")[0].checked;
					var autoLogin = false;
					sendLoginHttp(userName,pwd,code,autoLogin);
				}
				else{
					$("#inputCode3").focus();
				}
			}
			else{
				$("#password").focus();
			}
		}
		else{
			$("#inputEmail3").focus();
		}
	}

	function sendLoginHttp(phone,pwd,code,autoLogin){
		var url = Base.loginUrl;
		var condi = {};
		condi.username = phone;
		condi.password = pwd;
		condi.captcha = code;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				//保存数据
				//Utils.offLineStore.set("userinfo_login",data);
				var status = data.status || "";
				if(status == "OK"){
					//保存用户数据
					Utils.offLineStore.set("userinfo",JSON.stringify(condi),false);
					if(autoLogin){
						//保存自动登录数据
					}
					//location.href = "center.html";
					var token = data.result || "";
					Utils.offLineStore.set("token",token,false);
					location.href = "c_my.html";
				}
				else{
					var msg = data.error || "";
					alert("登录失败:" + msg);
				}
			},
			error:function(data){
			}
		});
	}
});