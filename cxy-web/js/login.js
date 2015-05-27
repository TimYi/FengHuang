/**
 * file:登录
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
		$("#loginbtn").onbind("click",this.loginBtnUp,this);

	},
	pageLoad:function(){
		console.log("pageLoad");
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

	loginBtnUp:function(evt){
		var phone = $("#userName").val() || "";
		var pwd = $("#userPwd").val() || "";
		if(phone !== ""){
			var reg = /^1[3,5,7,8]\d{9}$/g;
			if(reg.test(phone)){
				//this.phone = phone;
				phone = "18612444099";
				pwd = "123456";
				this.sendLoginHttp(phone,pwd);
			}
			else{
				alert("手机输入不合法");
			}
		}
	},

	forgetBtnUp:function(evt){
		var ele = evt.currentTarget;
		$(ele).removeClass("curr");
		if(!this.moved){
			console.log("忘记密码");
		}
	},


	sendLoginHttp:function(phone,pwd){
		var url = Base.loginUrl;
		var condi = {};
		condi.username = phone;
		condi.password = pwd;
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
					location.href = "index.html";
				}
				else{
					alert("登录失败");
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