/**
 * file:首页
 * author:chenxy
 * date:2015-05-23
*/


var PageManager = function (obj){
	this.init.apply(this,arguments);
};

PageManager.prototype = {
	constructor:PageManager,
	phone:"",
	init: function(){
		$(window).onbind("load",this.pageLoad,this);
		this.bindEvent();
	},
	bindEvent:function(){
	},
	pageLoad:function(){
		this.spanTipShow();
	},
	pageBack:function(evt){
		history.go(-1);
	},
	pageMove:function(evt){
		this.moved = true;
	},

	/**
	 * 隐藏dom 卸载资源
	*/
	pageHide:function(){
	},


	btnDown:function(evt){
		//按钮按下通用高亮效果
		this.moved = false;
		var ele = evt.currentTarget;
		$(ele).addClass("curr");
	},

	spanTipShow:function(){
		var t = this;
		$("#spantip").animate({"opacity":0},2000,"linear",function(){
			$("#spantip").html('稳中求进，精益求精，每个案例都是一个凤凰筑家的"样板间"');
			$("#spantip").css({"opacity":1});
			t.spanTipShow();
		});
	},




	getValidCode:function(evt){
		var ele = evt.currentTarget;
		$(ele).removeClass("curr");
		//if(!this.moved){}
		var phone = $("#emailAndPhone").val() || "";
		if(phone !== ""){
			var reg = /^1[3,5,7,8]\d{9}$/g;
			if(reg.test(phone)){
				this.phone = phone;
				this.sendGetCodeHttp();
			}
			else{
				alert("手机输入不合法");
			}
		}
		else{
			$("#emailAndPhone").focus();
		}

	},

	regBtnUp:function(evt){
		debugger
		var phone = $("#emailAndPhone").val() || "";
		var userName = $("#userName").val() || "";
		var usePwd = $("#usePwd").val() || "";
		var autoCode = $("#autoCode").val() || "";

		if(phone != "" && userName != "" && usePwd != "" && autoCode != ""){
			this.sendRegHttp(phone,userName,usePwd,autoCode);
		}
		else{
			alert("账户信息未填");
		}
	},

	//请求验证码
	sendGetCodeHttp:function(){
		var url = Base.getCodeUrl;
		var condi = {};
		condi.mobile = this.phone;
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
	},

	sendRegHttp:function(phone,userName,usePwd,autoCode){
		var url = Base.regUrl;
		var condi = {};
		condi.mobile = phone;
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
};

//页面初始化
$(function(){
	var page = new PageManager({});
});