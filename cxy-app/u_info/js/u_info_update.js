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
	g.userinfo = Utils.offLineStore.get("userinfo_update",false);
	g.page = Utils.getQueryString("p") - 0;
	g.httpTip = new Utils.httpTip({});
	var obj = g.userinfo || "";

	if(obj !== ""){
		obj = JSON.parse(obj);
		g.k = obj.k;
		g.v = obj.v;
		$("#" + g.k).val(g.v);
	}

	//验证登录状态
	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}
	else{
	}

	$("#clearbtn").bind("click",clearTextValue);
	$("#savebtn").bind("click",updateUserInfo);
	$("#" + g.k).bind("change",textValueChange);

	//$("#phonetext").bind("blur",getImgCode);
	//$("#updatepwdcodeimg").bind("click",getImgCode);
	//$("#sendbtn").bind("click",getPhoneCode);
	//$("#bindbtn").bind("click",bindPhone);



	function clearTextValue(){
		$("#" + g.k).val("");
		$("#clearbtn").hide();
	}

	function textValueChange(){
		var v = $(this).val() || "";
		if(v !== ""){
			$("#clearbtn").show();
		}
		else{
			$("#clearbtn").hide();
		}
	}

	function updateUserInfo(){
		var condi = {};
		condi.token = g.token;
		condi[g.k] = $("#" + g.k).val();
		console.log(condi);
		sendUpdateUserInfoHttp(condi);
	}

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
				console.log("sendUpdateUserInfoHttp",data);
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