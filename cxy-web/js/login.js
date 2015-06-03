/**
 * file:登录
 * author:chenxy
 * date:2015-05-23
*/

//页面初始化
$(function(){
	var g = {};
	g.codeId = "";

	$("#updatecodebtn").bind("click",updateCodeImg);
	$("#regbtn").bind("click",openRegPage);
	$("#loginbtn").bind("click",loginBtnUp);

	//$("#loginbtn").onbind("click",this.loginBtnUp,this);

	updateCodeImg();
	//换一组图片
	function updateCodeImg(evt){
		g.codeId = new Date() - 0;
		console.log(g.codeId);
		$("#updatecodebtn").attr("src","http://101.200.229.135:8080/api/captcha?id=" + g.codeId);
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
					var autoLogin =$("#autologin")[0].checked;
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
		condi.code = code;
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
				Utils.offLineStore.set("userinfo_login",data);
				var status = data.status || "";
				if(status == "OK"){
					location.href = "center.html";
				}
				else{
					alert("登录失败");
				}
			},
			error:function(data){
			}
		});
	}
});