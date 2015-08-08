
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.packageId = Utils.getQueryString("id");
	g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	g.reserveStatus = false;
	console.log(g.userprofile);
	if(!g.loginStatus){
		alert('请先登录！');
		location.href='login.html';
	}else if(!g.userprofile){
		g.reserveStatus = false;
		alert("个人资料不完善,无法预约");
		location.href = "u_info.html?token=" + g.token + "&p=1";
	}else if(g.loginStatus && g.userprofile !== ""){
		var obj = JSON.parse(g.userprofile);
		console.log(obj);
		var name = obj.realName || "";
		var mobile = obj.mobile || "";
		if(name.length == 0 || mobile.length == 0){
			g.reserveStatus = false;
			alert("个人资料不完善,无法预约");
			location.href = "u_info.html?token=" + g.token + "&p=1";
		}else if(name.length != 0 && mobile.length != 0){
			g.reserveStatus = true;
		}
		$("#name").val(name);
		$("#phone").val(mobile);

		getImgCode();
	}


	$("#phone").bind("blur",getImgCode);
	$("#imgcodebtn").bind("click",getImgCode);
	$("#getcodebtn").bind("click",getValidCode);
	$("#buybtn").bind("click",buyBtnUp);

	getAppointCategory();
	getCityLevel();
	//获取字典
	function getAppointCategory(){
		var url = Base.categoryUrl + "/appoint";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getAppointCategory",data);
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml("typeid",data.result || []);
				}
				else{
					Utils.alert("预约类别获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function getCityLevel(){
		var url = Base.cityUrl + "/CITY";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				var status = data.status || "";
				console.log(data);
				if(status == "OK"){
					changeSelectHtml("cityId",data.result || []);
				}
				else{
					Utils.alert("城市获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeSelectHtml(domid,data){
		var option = [];
		for(var i = 0,len = data.length; i < len; i++){
			var id = data[i].id || "";
			var name = data[i].name || "";
			option.push('<option value="' + id + '"' + ( i == 0 ? "selected" : "") + '>' + name + '</option>');
		}
		$("#" + domid).html(option.join(''));
	}

	//获取图形验证码
	function getImgCode(evt){
		var phone = $("#phone").val() || "";
		if(phone !== ""){
			g.imgCodeId = phone;
			$("#imgcodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId);
		}
	}

	//获取验证码
	function getValidCode(evt){
		var ele = evt.currentTarget;
		//$(ele).removeClass("curr");
		//if(!this.moved){}
		var p = $("#phone").val() || "";
		var imgCode = $("#inputImgCode3").val() || "";
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
					alert("输入图形验证码");
					$("#inputImgCode3").focus();
				}
			}
			else{
				alert("手机输入不合法");
				$("#phone").focus();
			}
		}
		else{
			alert("请输入手机号");
			$("#phone").focus();
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

	function buyBtnUp(){
		if(g.loginStatus){
			var text = $("#buybtn div").text();
			if(text == "您已成功预约"){
				return;
			}
			if(!g.reserveStatus){
				//没有添加真实姓名,引导去填写
				alert("个人资料不完善,无法预约");
				location.href = "u_info.html?token=" + g.token + "&p=1";
				return;
			}

			var condi = {};
			/*
			token:用户凭据
			typeid:预约类别（字典类型名称：appoint）
			areaid:地区id
			address:手动填写地址
			mobile:电话号码
			*/
			condi.token = g.token;
			condi.decoratePackageId = g.packageId ||"";
			condi.cityId = $("#cityId").val() || "";
			condi.mobile = $("#phone").val() || "";
			condi.realName = $("#name").val() || "";
			condi.captcha = $("#inputImgCode3").val() || "";
			condi.validater = $("#msgcode").val() || "";
			if(condi.name !== ""){
				if(condi.mobile !== ""){
					var reg = /^1[3,5,7,8]\d{9}$/g;
					if(reg.test(condi.mobile)){
						if(condi.captcha !== ""){
							if(condi.msgcode !== ""){
								sendAppointHttp(condi);
							}
							else{
								alert("输入短信验证码");
								$("#msgcode").focus();
							}
						}
						else{
							alert("输入图形验证码");
							$("#inputImgCode3").focus();
						}
					}
					else{
						alert("手机输入不合法");
						$("#phone").focus();
					}
				}
				else{
					Utils.alert("请输入手机号");
					$("#phone").focus();
				}
			}
			else{
				alert("请输入姓名");
				$("#name").focus();
			}
		}
		else{
			location.href = "login.html";
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

	function sendAppointHttp(condi){
		var url = Base.packageAppointUrl;
		g.httpTip.show();
		console.log(condi);
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendAppointHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("预约成功");
					$("#buybtn div").html("您已成功预约");
					alert("您已成功预约");
					if(g.packageId == '699'){
						location.href="jztc.html?pt=1";
					}else{
						location.href="jztc.html";
					}
				}
				else{
					Utils.alert("预约失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

});