/**
 * file:报名
 * author:chenxy
 * date:2015-08-04
*/
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});

	sendArticalHttp("报名活动官方说明");

	//马上报名
	$("#bmsendbtn").bind("click",baoMingBtnUp);
	$("#bmresetbtn").bind("click",baoMingResetBtnUp);
	function baoMingBtnUp(){
		var name = $("#inputEmail3bm").val() || "";
		var phone = $("#inputPhone3bm").val() || "";
		var name2 = $("#inputEmail32bm").val() || "";
		var phone2 = $("#inputPhone32bm").val() || "";
		var relation = $("#inputrelationbm").val() || "";
		if(name !== ""){
			if(phone !== ""){
				var reg = /^1[3,5,7,8]\d{9}$/g;
				if(reg.test(phone)){
					if(name2 !== ""){
						if(phone2 !== ""){
							var reg2 = /^1[3,5,7,8]\d{9}$/g;
							if(reg2.test(phone2)){
								if(relation !== ""){
									var condi = {};
									condi.name = name;
									condi.telephone = phone;
									condi.name2 = name2;
									condi.telephone2 = phone2;
									condi.relation = relation;
									condi.token = g.token;
									sendSignupHttp(condi);
								}
								else{
									Utils.alert("请输入选手关系!");
									$("#inputrelationbm").focus();
								}
							}
							else{
								Utils.alert("选手二手机号码输入不合法!");
								$("#inputPhone32bm").focus();
							}
						}
						else{
							Utils.alert("请输入选手二手机号码!");
							$("#inputPhone32bm").focus();
						}
					}
					else{
						Utils.alert("请输入选手二姓名!");
						$("#inputEmail32bm").focus();
					}
				}
				else{
					Utils.alert("选手一手机号码输入不合法!");
					$("#inputPhone3bm").focus();
				}
			}
			else{
				Utils.alert("请输入选手一手机号码!");
				$("#inputPhone3bm").focus();
			}
		}
		else{
			Utils.alert("请输入选手一姓名!");
			$("#inputEmail3bm").focus();
		}
	}
	function baoMingResetBtnUp(){
		$("#inputEmail3bm").val("");
		$("#inputPhone3bm").val("");
		$("#inputEmail32bm").val("");
		$("#inputPhone32bm").val("");
		$("#inputrelationbm").val("");
	}

	function sendSignupHttp(condi){
		var url = Base.serverUrl + "/api/signup";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendSignupHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					alert("报名成功!");
					$('#baoming').modal('hide');
				}
				else{
					var msg = data.errorDescription || "";
					alert("报名失败:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function sendArticalHttp(code){
		var url = Base.articalUrl + "/" + code;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendArticalHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					var content = data.result.content || "";
					//changeArticalHtml(data);
					$("#gfsm").html(content);
				}
				else{
					var msg = data.error || "";
					alert("获取报名活动官方说明错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});