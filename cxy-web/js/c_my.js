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
	g.token = Utils.getQueryString("token");
	//验证登录状态
	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		//未登录
		location.replace("login.html");
	}
	else{
		getUserInfo();
	}

	$("#updatebtn").bind("click",updateUserInfo);

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
		//性别1男2女
		//condi.sex = 1;
		var sexRadio = $("#inlineRadio2")[0].checked;
		if(sexRadio){
			//condi.sex = 2;
		}
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
		condi.birthDay = $("#birthday").val();
		//行业
		condi.trade = $("#profession").val();
		//现居住地
		condi.address = $("#address").val();
		//星座
		//condi.constellation = $("#constellation").val();
		//血型
		//condi.bloodgroup = $("#bloodgroup").val();

		sendUpdateUserInfoHttp(condi);
	}
	//修改个人资料
	function setUserInfoHtml(data){
		var obj = data || {};
		var nikeName = obj.cnname || "";
		var validName = obj.realName || "";
		var eName = obj.ename || "";
		var sex = obj.sex || 1;
		var message = obj.intro || "";
		var email = obj.email || "";
		var phone = obj.mobile || "";
		var qq = obj.qqnum || "";
		var weixin = obj.weixinnum || "";
		var birthday = obj.birthDay || "";
		var profession = obj.trade || "";
		var address = obj.address || "";
		var constellation = obj.constellation || "";
		var bloodgroup = obj.bloodType || "";

		g.username = obj.username;

		//昵称
		$("#nikename").val(nikeName);
		//真实姓名
		$("#validname").val(validName);
		//英文名
		$("#ename").val(eName);
		//性别1男2女
		if(sex !== 1){
			$("#inlineRadio2")[0].checked = true;
		}
		//个人简介
		$("#message").val(message);
		//电子邮箱
		$("#emailtext").val(email);
		//手机号
		$("#phonetext").val(phone);
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
		$("#constellation").val(constellation);
		//血型
		$("#bloodgroup").val(bloodgroup);
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
				var status = data.status || "";
				if(status == "OK"){
					setUserInfoHtml(data.result);
				}
				else{
					alert("获取个人信息错误");
				}
			},
			error:function(data){
			}
		});
	}

	//更新个人资料请求
	function sendUpdateUserInfoHttp(obj){
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
				var status = data.status || "";
				if(status == "OK"){
				}
				else{
					alert("验证码获取失败");
				}
			},
			error:function(data){
			}
		});
	}

});