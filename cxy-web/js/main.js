jQuery(function($) {'use strict',

	//#main-slider
	//~ $(function(){
		//~ $('#main-slider.carousel').carousel({
			//~ interval: 8000
		//~ });
	//~ });


	// accordian
	$('.accordion-toggle').on('click', function(){
		$(this).closest('.panel-group').children().each(function(){
		$(this).find('>.panel-heading').removeClass('active');
		 });

	 	$(this).closest('.panel-heading').toggleClass('active');
	});

	//Initiat WOW JS
	new WOW().init();

	// portfolio filter
	$(window).load(function(){'use strict';
		var $portfolio_selectors = $('.portfolio-filter >li>a');
		var $portfolio = $('.portfolio-items');
		$portfolio.isotope({
			itemSelector : '.portfolio-item',
			layoutMode : 'fitRows'
		});

		$portfolio_selectors.on('click', function(){
			$portfolio_selectors.removeClass('active');
			$(this).addClass('active');
			var selector = $(this).attr('data-filter');
			$portfolio.isotope({ filter: selector });
			return false;
		});
	});

	// Contact form
	var form = $('#main-contact-form');
	form.submit(function(event){
		event.preventDefault();
		var form_status = $('<div class="form_status"></div>');
		$.ajax({
			url: $(this).attr('action'),

			beforeSend: function(){
				form.prepend( form_status.html('<p><i class="fa fa-spinner fa-spin"></i> Email is sending...</p>').fadeIn() );
			}
		}).done(function(data){
			form_status.html('<p class="text-success">' + data.message + '</p>').delay(3000).fadeOut();
		});
	});


	//goto top
	$('.gototop').click(function(event) {
		event.preventDefault();
		$('html, body').animate({
			scrollTop: $("body").offset().top
		}, 500);
	});

	//Pretty Photo
	$("a[rel^='prettyPhoto']").prettyPhoto({
		social_tools: false
	});
});




$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	//标识是否抢购成功
	g.hasbuy = false;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	if(g.loginStatus){
		$("#yhqbtn1div").html('<a id="yhqbtn1" href="yhqcheck.html" ><div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即领取</div></a>');
		$("#yhqbtn2div").html('<a id="yhqbtn2" href="yhqcheck.html" ><div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即领取</div></a>');
	}
	g.reserveStatus = false;
	if(g.loginStatus && g.userprofile !== ""){
		var obj = JSON.parse(g.userprofile);
		var name = obj.realName || "";
		var mobile = obj.mobile || "";
		if(name !== "" && mobile !== ""){
			//允许预约
			g.reserveStatus = true;
			//$("#subbtn").attr("href","subappoint.html");
		}
		else{
			g.reserveStatus = false;
			//$("#subbtn").attr("href","center/c_my.html?token=" + g.token + "&p=1");
		}

		$("#inputEmail3bm").val(name);
		$("#inputPhone3bm").val(mobile);
		//$("#name").val(name);
		//$("#phone").val(mobile);

		//$("#name2").val(name);
		//$("#phone2").val(mobile);

		//getImgCode();
		//getImgCode2();
	}



	getPackages();

	//$("#phone").bind("blur",getImgCode);
	//$("#imgcodebtn").bind("click",getImgCode);
	//$("#getcodebtn").bind("click",getValidCode);
	//$("#reservebtn").bind("click",reserverBtnUp);

	//$("#phone2").bind("blur",getImgCode2);
	//$("#imgcodebtn2").bind("click",getImgCode2);
	//$("#getcodebtn2").bind("click",getValidCode2);
	//$("#reservebtn2").bind("click",reserverBtnUp2);

	//$("#provId").bind("change",getProvCity);
	//$("#provId2").bind("change",getProvCity2);

	//getAppointCategory();
	//getProv();

	function getCoupon(){
		//console.log(this.id);
		var id = this.id;
		if(g.loginStatus){
			location.href = "yhqcheck.html";
		}
		else{
			location.href = "center/reg.html";
		}
	}



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
		//var ele = evt.currentTarget;
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

	function reserverBtnUp(){
		if(g.loginStatus){
			if(!g.reserveStatus){
				//没有添加真实姓名,引导去填写
				alert("个人资料不完善,无法预约");
				location.href = "c_my.html?token=" + g.token + "&p=1";
				return;
			}
			var condi = {};
			/*
			token:用户凭据
			typeId:预约类别（字典类型名称：appoint）
			cityId:城市id，通过area相关接口获取
			mobile:电话号码，暂时这个字段不需要传，会默认使用用户绑定手机号码
			realName:用户真实姓名
			validater:根据用户绑定手机号码，发送的短信验证码
			*/
			condi.token = g.token;
			condi.typeId = $("#typeid").val() || "";
			condi.cityId = $("#cityId").val() || "";
			condi.realName = $("#name").val() || "";
			condi.mobile = $("#phone").val() || "";
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
			location.href = "center/login.html";
		}
	}

	function reserverBtnUp2(){
		if(g.loginStatus){
			if(!g.reserveStatus){
				//没有添加真实姓名,引导去填写
				alert("个人资料不完善,无法预约");
				location.href = "c_my.html?token=" + g.token + "&p=1";
				return;
			}
			var condi = {};
			/*
			token:用户凭据
			typeId:预约类别（字典类型名称：appoint）
			cityId:城市id，通过area相关接口获取
			mobile:电话号码，暂时这个字段不需要传，会默认使用用户绑定手机号码
			realName:用户真实姓名
			validater:根据用户绑定手机号码，发送的短信验证码
			*/
			condi.token = g.token;
			condi.typeId = $("#typeid2").val() || "";
			condi.cityId = $("#cityId2").val() || "";
			condi.name = $("#name2").val() || "";
			condi.mobile = $("#phone2").val() || "";
			condi.captcha = $("#inputImgCode32").val() || "";
			condi.validater = $("#msgcode2").val() || "";

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
			location.href = "center/login.html";
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
		var url = Base.appointUrl;
		g.httpTip.show();
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
					Utils.alert("预约服务成功");
				}
				else{
					Utils.alert("预约服务失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendAppointHttp2(condi){
		var url = Base.appointUrl;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendAppointHttp2",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("预约服务成功");
				}
				else{
					Utils.alert("预约服务失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function getPackages(){
		var url = Base.packagesUrl;
		var condi = {};
		condi.token = g.token;
		condi.page = 1;
		condi.size = 10;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getPackages",data);
				var status = data.status || "";
				if(status == "OK"){
					changePackageList(data.result.result);
				}
				else{
					Utils.alert("套餐列表获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changePackageList(data){
		for(var i = 0,len = data.length; i < len; i++){
			var obj = data[i];
			var id = obj.id || "";
			var price = obj.price - 0 || "";
			var decorate = obj.decorate || "";
			var description = obj.description || "";
			var status = obj.status || "";
			var inStock = obj.inStock - 0 || 0;
			var saleNumber = obj.saleNumber - 0 || 0;
			var scrambleStartTime = obj.scrambleStartTime || "";
			var scrambleEndTime = obj.scrambleEndTime || "";
			var hasAppointed = obj.hasAppointed || false;
			var couldAppoint = obj.couldAppoint || false;
			var hasScrambled = obj.hasScrambled || false;


			var lifeCycle = obj.lifeCycle || "";

			var hasshow = true;

			if(!g.loginStatus){
				//没登录,去登录
				var page = "center/login.html";
				$(".buynow").html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻预约</div>');
				return;
			}

			if(lifeCycle == "APPOINT"){
				//用户没有预约,并且可以预约,让用户去预约
				if(g.reserveStatus){
					var page = "subcheck.html?id=" + id;
					$(".buynow").html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即预约</div>');
				}
				else{
					var page = "center/c_my.html?token=" + g.token + "&p=1";
					var msg = "尊敬的" + g.username + "用户，您好！预约前，请先到会员中心完善个人资料。是否前往？";
					$(".buynow").html('<div onclick="buyTip(\'' + msg + '\',\'' + page + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即预约</div>');
				}
			}
			else if(lifeCycle == "WAITING"){
				//用户已经预约,套餐抢购尚未开始,
				var msg = "尊敬的" + g.username + "用户，您好！您已经预约成功，等待最新抢购消息！";
				$(".buynow").html('<div onclick="alert(\'' + msg + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">抢购未开始</div>');
			}
			else if(lifeCycle == "SCRAMBLE"){
				//让用户抢购套餐
				$(".buynow").html('<div onclick="miaoSha(\'' + id + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
			}
			else if(lifeCycle == "FINISHED"){
				//用户处于无法预约的状态,且套餐抢购已结束
				var msg = "尊敬的" + g.username + "用户，您好！抢购已结束！\n敬请期待下次抢购！";
				$(".buynow").html('<div onclick="alert(\'' + msg + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">已结束</div>');
			}
			else if(lifeCycle == "PAY"){
				//用户没有可用预约,且不能再次预约,且已经抢购一个套餐且没有支付,让用户去支付
				var page = "center/c_order.html?token=" + g.token + "&p=7";
				//var msg = "尊敬的" + g.username + "用户，您好！本月您已成功抢购过该套餐，\\n每人每月仅限抢购一次，请下个月再试，谢谢您的参与。";
				var msg = "尊敬的" + g.username + "用户，您好！本月您已成功抢购过该套餐，\\n请尽快完成支付。";
				$(".buynow").html('<div onclick="buyTip(\'' + msg + '\',\'' + page + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
			}
			else if(lifeCycle == "COMPLETE"){
				//用户完成预约抢购支付全部内容,并且没有可以用的预约,也无法进行下一次预约
				var msg = "尊敬的" + g.username + "用户，您好！本月您已成功抢购过该套餐，\\n每人每月仅限抢购一次，请下个月再试，谢谢您的参与。";
				$(".buynow").html('<div onclick="alert(\'' + msg + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
			}

			/*
			if(status == "PREPARE"){
				$(".buynow" + price).html('<div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">未开始</div>');
			}
			else if(status == "SCRAMBLE" && inStock > saleNumber){
				//~ 先判断hasAppointed，true直接显示是否开始抢购
				//~ false->判断couldAppoint，true让用户预约。
				//~ false->判断hasScrambled，true，引导用户到订单界面
				//~ false->向用户提示reasonForCantAppoint里的内容

				//没有登录,这些属性都是false,没用
				if(!g.loginStatus){
					//没登录,去登录
					var page = "center/reg.html";
					//$(".buynow" + price).html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">立刻抢购</div>');
					$(".buynow" + price).html('<div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">立刻抢购</div>');
				}
				else{
					//判断是否可以抢购
					if(hasAppointed){
						//可以抢购
						if(g.loginStatus){
							$(".buynow" + price).html('<div onclick="miaoSha(\'' + id + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
							//html.push('<a href="javascript:miaoSha(\'' + id + '\')">');
						}
						else{
							var page = "center/reg.html";
							//$(".buynow" + price).html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">立刻抢购</div>');
							$(".buynow" + price).html('<div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">立刻抢购</div>');
						}
					}
					else{
						//不能抢购,判断couldAppoint，true让用户预约
						if(couldAppoint){
							if(g.loginStatus){
								//~ var page = "center/c_order.html?token=" + g.token + "&p=7";
								//~ $(".buynow").html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
								if(g.reserveStatus){
									var page = "subcheck.html?id=" + id;
									$(".buynow" + price).html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即预约</div>');
								}
								else{
									var page = "center/c_my.html?token=" + g.token + "&p=1";
									var msg = "尊敬的" + g.username + "用户，您好！预约前，请先到会员中心完善个人资料。是否前往？";
									$(".buynow" + price).html('<div onclick="buyTip(\'' + msg + '\',\'' + page + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立即预约</div>');
								}
							}
							else{
								var page = "center/login.html";
								//$(".buynow" + price).html('<div onclick="location.href=\'' + page + '\'" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
								$(".buynow" + price).html('<div  style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">立刻抢购</div>');
							}
						}
						else{
							////~ hasScrambled:标识是否已经抢购完并且尚未付款
							if(hasScrambled){
								var page = "center/c_order.html?token=" + g.token + "&p=7";
								var msg = "尊敬的" + g.username + "用户，您好！本月您已成功抢购过该套餐，\\n每人每月仅限抢购一次，请下个月再试，谢谢您的参与。";
								$(".buynow" + price).html('<div onclick="buyTip(\'' + msg + '\',\'' + page + '\')" style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">立刻抢购</div>');
							}
							else{
								//向用户提示reasonForCantAppoint里的内容
								var msg = obj.reasonForCantAppoint || "套餐异常";
								Utils.alert(msg);

								hasshow = false;
							}
						}
					}
				}
			}
			else{
				$(".buynow" + price).html('<div style="font-weight:800;text-align:center;line-height:45px;font-size:18px;color:#000;">已结束</div>');
			}
			*/
			//$(".buynow" + price).parent().show();

		}
	}


	function miaoSha(id){
		if(g.hasbuy){
			//直接跳订单
			location.href = "center/c_order.html?token=" + g.token + "&p=7";
			return;
		}
		var url = Base.scramble;
		var condi = {};
		condi.token = g.token;
		condi.id = id;
		condi.caseId = "";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("miaoSha",data);
				var status = data.status || "";
				if(status == "OK"){
					g.hasbuy = true;
					var d = new Date(new Date() - 0 + (7 * 24 * 60 * 60 * 1000));
					var dt = d.format("yyyy-MM-dd");
					var msg = "尊敬的" + g.username + "用户，您好，您已成功抢购" + id + "套餐，请于" + dt + "前完成定金支付方可生效，是否现在支付？";
					if(confirm(msg)){
						//Utils.alert("抢购成功");
						var orderId = data.result.id;
						location.href = "orderback_paysel.html?id=" + orderId;
					}
				}
				else{
					var msg = data.errorDescription || "";
					alert("抢购失败:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function buyTip(msg,url){
		if(confirm(msg)){
			location.href = url;
		}
	}

	window.miaoSha = miaoSha;
	window.buyTip = buyTip;
});









