/**
 * file:房屋信息
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
	g.houseinfo = Utils.offLineStore.get("houseinfo_update",false);
	g.page = Utils.getQueryString("p") - 0;
	g.httpTip = new Utils.httpTip({});
	g.condiObj = g.houseinfo || "";

	if(g.condiObj !== ""){
		g.condiObj = JSON.parse(g.condiObj);
		g.k = g.condiObj.k;
		g.v = g.condiObj.v;
		$("#" + g.k).val(g.v);

		if(g.v != ""){
			$("#clearbtn").hide();
		}
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
	$("#savebtn").bind("click",updateInfo);
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

	function updateInfo(){
		var condi = g.condiObj;
		condi.token = g.token;
		condi[g.k] = $("#" + g.k).val();
		console.log(condi);
		sendUpdateInfoHttp(condi);
	}

	function sendUpdateInfoHttp(obj){
		g.httpTip.show();
		var url = Base.house + "/" + obj.id;
		$.ajax({
			url:url,
			data:obj,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendUpdateInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					alert("修改房屋资料成功");
				}
				else{
					alert("修改房屋资料失败");
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});