/**
 * file:个人中心
 * author:chenxy
 * date:2015-05-23
*/
//页面初始化
$(function(){
	var g = {};
	g.phone = "";

	$("#updatebtn").bind("click",updateUserInfo);

	//更新个人信息
	function updateUserInfo(evt){
		var nikeName = $("#
	}

	//重置信息
	function resetRegInfo(evt){
		$("#inputEmail3").val("");
		$("#inputPassword3").val("");
		$("#inputPhone3").val("");
		$("#inputCode3").val("");
	}

	//注册
	function regBtnUp(evt){
		var phone = $("#inputPhone3").val() || "";
		var userName = $("#inputEmail3").val() || "";
		var usePwd = $("#inputPassword3").val() || "";
		var autoCode = $("#inputCode3").val() || "";

		if(phone != "" && userName != "" && usePwd != "" && autoCode != ""){
			this.sendRegHttp(phone,userName,usePwd,autoCode);
		}
		else{
			alert("账户信息未填");
		}
	}

	//请求验证码
	function sendGetCodeHttp(){
		var url = Base.getCodeUrl;
		var condi = {};
		condi.mobile = g.phone;
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
					$("#getcodebtn").html("60秒后重新发送");
				}
				else{
					alert("验证码获取失败");
				}
			},
			error:function(data){
			}
		});
	}

	//注册
	function sendRegHttp(phone,userName,usePwd,autoCode){
		var url = Base.regUrl;
		var condi = {};
		condi.mobile = g.phone;
		condi.username = userName;
		condi.password = usePwd;
		condi.validater = autoCode;

		$.ajax({
			url:url,
			type:"POST",
			data:condi,
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				var status = data.status || "";
				if(status == "OK"){
					var token = data.result || "";

					//保存token
					Utils.offLineStore.set("token",token,false);
				}
				else{
					alert("注册失败");
				}
			},
			error:function(data){
			}
		});
	}
});