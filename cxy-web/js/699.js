
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	if(g.loginStatus && g.userprofile !== ""){
		var obj = JSON.parse(g.userprofile);
		var name = obj.realName || "";
		var mobile = obj.mobile || "";
		$("#name").val(name);
		$("#phone").val(mobile);

		$("#name2").val(name);
		$("#phone2").val(mobile);

		getImgCode();
		getImgCode2();
	}


	$("#phone").bind("blur",getImgCode);
	$("#imgcodebtn").bind("click",getImgCode);
	$("#getcodebtn").bind("click",getValidCode);
	$("#buybtn").bind("click",buyBtnUp);

	$("#phone2").bind("blur",getImgCode2);
	$("#imgcodebtn2").bind("click",getImgCode2);
	$("#getcodebtn2").bind("click",getValidCode2);
	$("#buybtn2").bind("click",buyBtnUp2);

	$("#provId").bind("change",getProvCity);
	$("#provId2").bind("change",getProvCity2);

	getAppointCategory();
	getProv();
	function getProvCity(){
		var id = $(this).val();
		getCity(id,1);
	}
	function getProvCity2(){
		var id = $(this).val();
		getCity(id,2);
	}
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
					changeSelectHtml("typeid2",data.result || []);
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
	function getProv(){
		var url = Base.cityUrl + "/PROV";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getProv",data);
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml("provId",data.result || []);
					changeSelectHtml("provId2",data.result || []);
					var id = data.result[0].id;
					getCity(id,0);
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
	function getCity(id,b){
		var url = Base.subareasUrl + "/" + id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getCity",data);
				var status = data.status || "";
				if(status == "OK"){
					switch(b){
						case 0:
							changeSelectHtml("cityId",data.result || []);
							changeSelectHtml("cityId2",data.result || []);
						break;
						case 1:
							changeSelectHtml("cityId",data.result || []);
						break;
						case 2:
							changeSelectHtml("cityId2",data.result || []);
						break;
					}
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
			console.log(phone);
			g.imgCodeId = phone;
			$("#imgcodebtn").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId);
		}
	}

	function getImgCode2(evt){
		var phone = $("#phone2").val() || "";
		if(phone !== ""){
			console.log(phone);
			g.imgCodeId = phone;
			$("#imgcodebtn2").attr("src",Base.imgCodeUrl + "?id=" + g.imgCodeId);
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
					Utils.alert("输入图形验证码");
					$("#inputImgCode3").focus();
				}
			}
			else{
				Utils.alert("手机输入不合法");
				$("#phone").focus();
			}
		}
		else{
			Utils.alert("请输入手机号");
			$("#phone").focus();
		}
	}

	function getValidCode2(evt){
		var ele = evt.currentTarget;
		//$(ele).removeClass("curr");
		//if(!this.moved){}
		var p = $("#phone2").val() || "";
		var imgCode = $("#inputImgCode32").val() || "";
		if(p !== ""){
			var reg = /^1[3,5,7,8]\d{9}$/g;
			if(reg.test(p)){
				if(imgCode !== ""){
					g.phone = p;
					if(!g.sendCode){
						sendGetCodeHttp2(imgCode);
					}
				}
				else{
					Utils.alert("输入图形验证码");
					$("#inputImgCode32").focus();
				}
			}
			else{
				Utils.alert("手机输入不合法");
				$("#phone2").focus();
			}
		}
		else{
			Utils.alert("请输入手机号");
			$("#phone2").focus();
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

	function resetGetValidCode2(){
		g.sendTime = g.sendTime - 1;
		if(g.sendTime > 0){
			$("#getcodebtn2").html(g.sendTime + "秒后重新发送");
			setTimeout(function(){
				resetGetValidCode2();
			},1000);
		}
		else{
			$("#getcodebtn2").html("重新发送");
			g.sendTime = 60;
			g.sendCode2 = false;

			//重新获取图形验证码,1分钟有效
			getImgCode2();
			$("#inputImgCode32").val("");
			$("#inputImgCode32").focus();
		}
	}

	function buyBtnUp(){
		if(g.loginStatus){
			var condi = {};
			/*
			token:用户凭据
			typeid:预约类别（字典类型名称：appoint）
			areaid:地区id
			address:手动填写地址
			mobile:电话号码
			*/
			condi.token = g.token;
			condi.typeid = $("#typeid").val() || "";
			condi.name = $("#name").val() || "";
			condi.mobile = $("#phone").val() || "";
			condi.captcha = $("#inputImgCode3").val() || "";
			condi.msgcode = $("#msgcode").val() || "";

			if(condi.name !== ""){
				if(condi.mobile !== ""){
					var reg = /^1[3,5,7,8]\d{9}$/g;
					if(reg.test(condi.mobile)){
						if(condi.captcha !== ""){
							if(condi.msgcode !== ""){
								sendAppointHttp(condi);
							}
							else{
								Utils.alert("输入短信验证码");
								$("#msgcode").focus();
							}
						}
						else{
							Utils.alert("输入图形验证码");
							$("#inputImgCode3").focus();
						}
					}
					else{
						Utils.alert("手机输入不合法");
						$("#phone").focus();
					}
				}
				else{
					Utils.alert("请输入手机号");
					$("#phone").focus();
				}
			}
			else{
				Utils.alert("请输入姓名");
				$("#name").focus();
			}
		}
		else{
			location.href = "login.html";
		}
	}

	function buyBtnUp2(){
		if(g.loginStatus){
			var condi = {};
			/*
			token:用户凭据
			typeid:预约类别（字典类型名称：appoint）
			areaid:地区id
			address:手动填写地址
			mobile:电话号码
			*/
			condi.token = g.token;
			condi.typeid = $("#typeid2").val() || "";
			condi.name = $("#name2").val() || "";
			condi.mobile = $("#phone2").val() || "";
			condi.captcha = $("#inputImgCode32").val() || "";
			condi.msgcode = $("#msgcode2").val() || "";

			if(condi.name !== ""){
				if(condi.mobile !== ""){
					var reg = /^1[3,5,7,8]\d{9}$/g;
					if(reg.test(condi.mobile)){
						if(condi.captcha !== ""){
							if(condi.msgcode !== ""){
								sendAppointHttp2(condi);
							}
							else{
								Utils.alert("输入短信验证码");
								$("#msgcode2").focus();
							}
						}
						else{
							Utils.alert("输入图形验证码");
							$("#inputImgCode32").focus();
						}
					}
					else{
						Utils.alert("手机输入不合法");
						$("#phone2").focus();
					}
				}
				else{
					Utils.alert("请输入手机号");
					$("#phone2").focus();
				}
			}
			else{
				Utils.alert("请输入姓名");
				$("#name2").focus();
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

	function sendGetCodeHttp2(imgCode){
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
					g.sendCode2 = true;
					$("#getcodebtn2").html("60秒后重新发送");
					setTimeout(function(){
						resetGetValidCode2();
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
		var url = Base.getCodeUrl;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(sendAppointHttp,data);
				var status = data.status || "";
				if(status == "OK"){
				}
				else{
					alert("预约失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendAppointHttp2(condi){
		var url = Base.getCodeUrl;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(sendAppointHttp,data);
				var status = data.status || "";
				if(status == "OK"){
				}
				else{
					alert("预约失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});













