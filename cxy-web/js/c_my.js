/**
 * file:个人资料
 * author:chenxy
 * date:2015-06-05
*/
//页面初始化
$(function(){
	if(typeof eui !== "undefined"){
		var birthday = $('#birthday');
		if(birthday.length > 0){
			eui.calendar({
				startYear: 1900,
				input:birthday[0]
			});
		}
	}

	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.isBind = true;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.httpTip = new Utils.httpTip({});

	//验证登录状态
	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}
	else{
		getUserInfo();
		sendMyInfoCountsHttp();
	}

	$("#updatebtn").bind("click",updateUserInfo);
	$("#loginoutbtn").bind("click",loginOut);
	$("#phonetext").bind("blur",getImgCode);
	$("#updatepwdcodeimg").bind("click",getImgCode);
	$("#sendbtn").bind("click",getPhoneCode);
	$("#bindbtn").bind("click",bindPhone);

	//头像
	//$("#avatarbtn").bind("click",avatarBtnUp);
	$("#avatar").bind("change",avatarBtnUp);

	//安全退出
	function loginOut(){
		Utils.offLineStore.remove("userinfo",false);
		location.href = "login.html";
	}

	//获取图形验证码
	function getImgCode(){
		var img = $("#updatepwdcodeimg");
		var tel = $("#phonetext").val();
		var reg = /^1[3,5,7,8]\d{9}$/g;
		if(img.length > 0 && reg.test(tel)){
			g.codeId = tel;
			console.log(tel);
			//$("#updatepwdcodeimg").attr("src",Base.imgCodeUrl + "?id=" + g.codeId);
			$("#updatepwdcodeimg").attr("src",Base.imgCodeUrl + "?id=" + g.codeId + "&t=" + (new Date() - 0));
			clearTimeout(g.tout);
			g.tout = setTimeout(function(){
				getImgCode();
			},60000);
		}
	}

	function getPhoneCode(){
		var p = $("#phonetext").val() || "";
		var imgCode = $("#phoneimgcode").val() || "";
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
					console.log("输入图形验证码");
					$("#phoneimgcode").focus();
				}
			}
			else{
				alert("手机输入不合法");
				$("#phonetext").focus();
			}
		}
		else{
			console.log("没填手机号");
			$("#phonetext").focus();
		}
	}

	//请求验证码
	function sendGetCodeHttp(imgCode){
		g.httpTip.show();
		var url = Base.getCodeUrl;
		var condi = {};
		condi.mobile = g.phone;
		condi.captcha = imgCode;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					g.sendCode = true;
					$("#sendbtn").html("60秒后重新发送");
					setTimeout(function(){
						resetGetValidCode();
					},1000);
				}
				else{
					alert("验证码获取失败");
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	//重新获取验证码
	function resetGetValidCode(){
		g.sendTime = g.sendTime - 1;
		if(g.sendTime > 0){
			$("#sendbtn").html(g.sendTime + "秒后重新发送");
			setTimeout(function(){
				resetGetValidCode();
			},1000);
		}
		else{
			$("#sendbtn").html("重新发送");
			g.sendTime = 60;
			g.sendCode = false;

			//重新获取图形验证码,1分钟有效
			getImgCode();
			$("#phoneimgcode").val("");
			$("#phoneimgcode").focus();
		}
	}

	//绑定手机号
	function bindPhone(){
		//token:用户凭据
		//mobile：手机号码
		//validater:短信验证码
		var p = $("#phonetext").val() || "";
		var bindcode = $("#bindcode").val() || "";
		if(g.isBind){
			if(p !== ""){
				var reg = /^1[3,5,7,8]\d{9}$/g;
				if(reg.test(p)){
					if(bindcode !== ""){
						sendBindPhoneHttp(p,bindcode);
					}
					else{
						console.log("输入验证码");
						$("#bindcode").focus();
					}
				}
				else{
					alert("手机输入不合法");
					$("#phonetext").focus();
				}
			}
			else{
				console.log("没填手机号");
				$("#phonetext").focus();
			}
		}
		else{
			alert("解绑没有接口");
		}
	}

	//获取个人资料
	function getUserInfo(){
		var token = g.token;
		sendGetUserInfoHttp(token);
	}
	//更新个人资料
	function updateUserInfo(){
		var condi = {};
		condi.token = g.token;
		condi.username = g.username;
		//昵称
		condi.cnname = $("#nikename").val();
		//真实姓名
		condi.realName = $("#validname").val();
		//英文名
		condi.ename = $("#ename").val();
		//个人简介
		condi.intro = $("#message").val();
		//电子邮箱
		condi.email = $("#emailtext").val();
		//手机号
		//condi.phone = $("#phonetext").val();
		//QQ号
		//condi.qq = $("#qqtext").val();
		//微信
		//condi.weixin = $("#weixintext").val();
		//生日
		condi.birthDay = $("#birthday").val() || "";
		//行业
		condi.trade = $("#profession").val();
		//现居住地
		condi.address = $("#address").val();



		//性别1男2女
		condi.sexId = "404040e64dd26ab5014dd26ac61f0013";
		var sexRadio = $("#inlineRadio2")[0].checked;
		if(sexRadio){
			//condi.sex = 2;
			condi.sexId = "404040e64dd26ab5014dd26ac64e0014";
		}
		//血型
		condi.bloodTypeId = $("#bloodgroup").val();
		//星座
		condi.constellationId = $("#constellation").val();
		console.log(condi);
		sendUpdateUserInfoHttp(condi);
	}
	//修改个人资料
	function setUserInfoHtml(data){
		var obj = data.user || {};
		var avatar = obj.avatar || "";
		if(avatar !== "" ){
			avatar = avatar.url || "";
		}
		var nikeName = obj.cnname || "";
		var validName = obj.realName || "";
		var eName = obj.ename || "";
		var sex = obj.sex || "404040e64dd26ab5014dd26ac61f0013";
		var message = obj.intro || "";
		var email = obj.email || "";
		var phone = obj.mobile || "";
		var qq = obj.qqnum || "";
		var weixin = obj.weixinnum || "";
		var birthday = obj.birthDay || "";
		if(birthday !== ""){
			birthday = birthday.substring(0,10);
		}
		var profession = obj.trade || "";
		var address = obj.address || "";
		var constellation = obj.constellation || {};
		var bloodgroup = obj.bloodType || {};

		g.username = obj.username;
		//Base.userName = obj.username;

		//获取验证码
		//getImgCode();
		if(avatar !== ""){
			$("#avatarbtn img").attr("src",avatar);
		}
		//昵称
		$("#nikename").val(nikeName);
		//真实姓名
		$("#validname").val(validName);
		//英文名
		$("#ename").val(eName);
		//性别1男2女
		if(sex !== "404040e64dd26ab5014dd26ac61f0013"){
			if($("#inlineRadio2").length > 0){
				$("#inlineRadio2")[0].checked = true;
			}
		}
		//个人简介
		$("#message").val(message);
		//电子邮箱
		$("#emailtext").val(email);
		if(phone != ""){
			//手机号
			$("#phonetext").val(phone);
			$("#bindbtn").html("解绑");
			g.isBind = false;
		}
		//QQ号
		$("#qqtext").val(qq);
		//微信
		$("#weixintext").val(weixin);
		//生日
		$("#birthday").val(birthday);
		//行业
		$("#profession").val(profession);
		//现居住地
		$("#address").val(address);
		//星座
		$("#constellation").val(constellation.id);
		//血型
		$("#bloodgroup").val(bloodgroup.id);


		var li = [];
		//li.push('<li>ID：' + obj.username + '</li>');
		li.push('<li>' + obj.username + '</li>');
		li.push('<li>' + phone + '</li>');
		$("#user_ul").html(li.join(''));

		var li = [];
		var loginIp = obj.loginip || "";
		var loginTime = obj.loginTime || "";
		var regIp = obj.regIp || "";
		var regTime = obj.regTime || "";
		var integra = obj.integra || 0;
		li.push('<li>登录 IP<span class="pull-right">' + loginIp + '</span></li>');
		li.push('<li>注册时间<span class="pull-right">' + regTime + '</span></li>');
		li.push('<li>登录时间<span class="pull-right">' + loginTime + '</span></li>');
		li.push('<li>用户积分<span class="pull-right">' + integra + '分</span></li>');
		$("#logintime").html(li.join(''));

		//setUserFunHtml();
		$("#loginoutbtn").show();

	}

	function setUserFunHtml(obj){
		//style="background:#b9090e;color:#fff;"
		var h = 'style="background:#b9090e;color:#fff;"';
		var comments = obj.comments || "";
		var coupons = obj.coupons || "";
		var messages = obj.messages || "";
		var appoints = obj.appoints || "";
		var collects = obj.collects || "";

		var html = [];
		html.push('<ul class="blog_category">');
		html.push('<li><a href="c_my.html?token=' + g.token + '&p=1" ' + (g.page == 1 ? h : "") + ' >个人信息 </a></li>');
		html.push('<li><a href="c_safe.html?token=' + g.token + '&p=2" ' + (g.page == 2 ? h : "") + ' >安全设置 </a></li>');
		html.push('<li><a href="c_home.html?token=' + g.token + '&p=3" ' + (g.page == 3 ? h : "") + '  >房屋信息 </a></li>');
		html.push('<li><a href="c_ing.html?token=' + g.token + '&p=4" ' + (g.page == 4 ? h : "") + '  >家装进度 </a></li>');
		html.push('<li><a href="c_message.html?token=' + g.token + '&p=5" ' + (g.page == 5 ? h : "") + '  >我的留言 <span class="badge">' + messages + '</span></a></li>');
		html.push('<li><a href="c_sub.html?token=' + g.token + '&p=6" ' + (g.page == 6 ? h : "") + '  >我的预约 <span class="badge">' + appoints + '</span></a></li>');
		html.push('<li><a href="c_order.html?token=' + g.token + '&p=7" ' + (g.page == 7 ? h : "") + '  >我的订单 <span class="badge"></span></a></li>');
		html.push('<li><a href="c_comment.html?token=' + g.token + '&p=8" ' + (g.page == 8 ? h : "") + '  >我的评论 <span class="badge">' + comments + '</span></a></li>');
		html.push('<li><a href="c_fav.html?token=' + g.token + '&p=9" ' + (g.page == 9 ? h : "") + '  >我的收藏 <span class="badge">' + collects + '</span></a></li>');
		html.push('<li><a href="c_coupon.html?token=' + g.token + '&p=10" ' + (g.page == 10 ? h : "") + '  >我的优惠券 <span class="badge">' + coupons + '</span></a></li>');
		html.push('</ul>');

		$("#userfunul").html(html.join(''));
	}


	//注册
	function regBtnUp(evt){
		var userName = $("#inputEmail3").val() || "";
		var usePwd = $("#inputPassword3").val() || "";
		var phone = $("#inputPhone3").val() || "";
		var imgCode = $("#inputImgCode3").val() || "";
		var validCode = $("#inputCode3").val() || "";

		var regEMail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var regPhone = /^1[3,5,7,8]\d{9}$/;
		var regFont = /^([\u4E00-\u9FA5|\w\-])+$/;
		if(regEMail.test(userName) || regPhone.test(userName) || regFont.test(userName)){
			if(userName !== "" && usePwd !== "" && phone !== "" && imgCode !== "" && validCode !== ""){
				sendRegHttp(userName,usePwd,validCode);
				//http://101.200.229.135:8080/api/regist?username=ytm&password=123456&mobile=18612444099&validater=3967
			}
			else{
				alert("账户信息未填");
			}
		}
		else{
			alert("用户名输入错误,请输入邮箱或者手机号");
			$("#inputEmail3").focus();
		}

	}

	//获取个人资料请求
	function sendGetUserInfoHttp(token){
		g.httpTip.show();
		var url = Base.profileUrl;
		var condi = {};
		condi.token = token;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					setUserInfoHtml(data.result);
					Utils.offLineStore.set("login_userprofile",JSON.stringify(data.result.user),false);
				}
				else{
					Utils.offLineStore.remove("userinfo",false);
					Utils.offLineStore.remove("login_userprofile",false);
					var msg = data.error || "";
					alert("获取个人信息错误:" + msg);
					location.href = "login.html";
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	//更新个人资料请求
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
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					Utils.offLineStore.set("login_userprofile",JSON.stringify(data.result),false);
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

	//绑定手机号
	function sendBindPhoneHttp(phone,code){
		g.httpTip.show();
		var url = Base.bindMobile;
		//token:用户凭据
		//mobile：手机号码
		//validater:短信验证码
		var condi = {};
		condi.token = g.token;
		condi.mobile = phone;
		condi.validater = code;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					alert("绑定手机成功");
				}
				else{
					var msg = data.error;
					alert("手机绑定错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function sendMyInfoCountsHttp(){
		g.httpTip.show();
		var url = Base.unreads;
		//token:用户凭据
		var condi = {};
		condi.token = g.token;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					setUserFunHtml(data.result);
				}
				else{
					var msg = data.error;
					Utils.alert("获取未读消息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function avatarBtnUp(){
		var avatar = $("#avatar").val() || "";
		if(avatar !== ""){
			uploadBtnUp();
		}
		/*
		var popbox = $("#popbox");
		if(popbox.length == 0){
			var url = Base.serverUrl + "/api/user/changeAvatar";
			//var url = "http://192.168.10.209:8080/fenghuangzhujia-eshop-web/";
			var token = g.token;
			var html = [];
			html.push('<div id="popbox" class="prompt_mask transparentbg" style="display: block;">');
			html.push('<div class="p_load" style="width:600px;height:200px;background:#fff;margin-left:-300px;">');
			//html.push('<form id="avatarform" submit="return false;" action="' + url + '" method="post" enctype="multipart/form-data">');
			html.push('<p>');
			html.push('<input id="avatar" type="file" name="avatar" multiple="multiple" min="1" max="99" value="选择头像" accept="image/*" />');
			//html.push('<input id="uploadbtn" type="submit" value="upload" />');
			html.push('<input id="uploadbtn" type="button" value="upload" />');
			html.push('<input id="token" type="hidden" name="token" value="' + token + '" />');
			html.push('</p>');
			//html.push('</form>');
			html.push('</div>');
			html.push('</div>');

			$("body").append(html.join(''));

			$("#uploadbtn").bind("click",uploadBtnUp);
		}
		else{
			popbox.show();
		}
		*/
	}

	function uploadBtnUp(){
		if(lastname()){
			g.httpTip.show();
			var url = Base.serverUrl + "/api/user/changeAvatar";
			var condi = {};
			condi.token = g.token;
			$.ajaxFileUpload({
				url: url, //用于文件上传的服务器端请求地址
				data:condi,
				secureuri: false, //一般设置为false
				fileElementId: 'avatar', //文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType: 'jsonp', //返回值类型 一般设置为json
				success: function (data, status)  //服务器成功响应处理函数
				{
					g.httpTip.hide();
					Utils.alert("头像上传成功");
					console.log("ajaxFileUpload",data,status);
					location.reload();


					/*
					$("#img1").attr("src", data.imgurl);
					if (typeof (data.error) != 'undefined') {
						if (data.error != '') {
							alert(data.error);
						} else {
							alert(data.msg);
						}
					}
					*/
				},
				error: function (data, status, e)//服务器响应失败处理函数
				{
					Utils.alert("头像上传失败");
					g.httpTip.hide();
					//alert(e);
				}
			});
			return false;
		}
	}

	function lastname(){
		//获取欲上传的文件路径
		var filepath = document.getElementById("avatar").value;
		//为了避免转义反斜杠出问题，这里将对其进行转换
		var re = /(\\+)/g;
		var filename=filepath.replace(re,"#");
		//对路径字符串进行剪切截取
		var one=filename.split("#");
		//获取数组中最后一个，即文件名
		var two=one[one.length-1];
		//再对文件名进行截取，以取得后缀名
		var three=two.split(".");
		//获取截取的最后一个字符串，即为后缀名
		var last=three[three.length-1];
		//添加需要判断的后缀名类型
		var tp ="jpg,gif,bmp,JPG,GIF,BMP,png";
		//返回符合条件的后缀名在字符串中的位置
		var rs=tp.indexOf(last);
		//如果返回的结果大于或等于0，说明包含允许上传的文件类型
		if(rs>=0){
			return true;
		}else{
			alert("您选择的上传文件不是有效的图片文件！");
			return false;
		}
	}
});