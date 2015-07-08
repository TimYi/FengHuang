/**
 * file:安全设置
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
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;



	$("#updatepwdbtn").bind("click",updateUserPwd);
	$("#updatepwdcodeimg").bind("click",getImgCode);


	setTimeout(function(){
		getImgCode();
	},2000);
	//换一组图片
	function getImgCode(evt){
		var userName = g.username || "";
		if(userName !== ""){
			//$("#updatepwdcodeimg").attr("src",Base.imgCodeUrl + "?id=" + userName);
			$("#updatepwdcodeimg").attr("src",Base.imgCodeUrl + "?id=" + userName + "&t=" + (new Date() - 0));
			clearTimeout(g.tout);
			g.tout = setTimeout(function(){
				getImgCode();
			},60000);
		}
		else{
			setTimeout(function(){
				getImgCode();
			},100);
			g.username = Base.userName;
		}
	}

	//更新个人密码
	function updateUserPwd(){
		var condi = {};
		condi.token = g.token;
		condi.username = g.username;
		condi.oldPassword = $("#oldpwd").val() || "";
		condi.newPassword = $("#newpwd").val() || "";
		condi.confirmPassword = $("#newpwd2").val() || "";
		condi.captcha = $("#pwdimgcode").val() || "";

		if(condi.oldPassword !== "" && condi.newPassword !== "" && condi.confirmPassword !== "" && condi.captcha !== ""){
			sendUpdateUserPwdHttp(condi);
		}
		else{
			Utils.alert("信息输入错误");
		}
	}

	//获取个人资料请求
	function sendUpdateUserPwdHttp(condi){
		var url = Base.changePasswordUrl;
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
					Utils.alert("密码修改成功");
					$("#oldpwd").val("");
					$("#newpwd").val("");
					$("#newpwd2").val("");
					$("#pwdimgcode").val("");
				}
				else{
					var msg = data.error || "";
					alert("修改登录密码错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}
});