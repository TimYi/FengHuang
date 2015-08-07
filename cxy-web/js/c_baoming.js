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

	g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	if(g.loginStatus && g.userprofile !== ""){
		var obj = JSON.parse(g.userprofile);
		var name = obj.realName || "";
		var mobile = obj.mobile || "";
		$("#inputEmail3bm").val(name);
		$("#inputPhone3bm").val(mobile);
	}

	sendArticalHttp("报名活动官方说明");

	//马上报名
	$("#bmsendbtn").bind("click",baoMingBtnUp);
	$("#bmresetbtn").bind("click",baoMingResetBtnUp);
	$("#bmquerybtn").bind("click",baoMingQueryBtnUp);
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
					//$('#baoming').modal('hide');
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

	function baoMingQueryBtnUp(){
		var telephone = $("#inputPhone3q").val() || "";
		if(telephone !== ""){
			var reg = /^1[3,5,7,8]\d{9}$/g;
			if(reg.test(telephone)){
				var condi = {};
				condi.telephone = telephone;
				sendBaoMingQueryHttp(condi);
			}
			else{
				Utils.alert("查询手机号码输入不合法!");
				$("#inputPhone3q").focus();
			}
		}
		else{
			Utils.alert("请输入查询手机号码!");
			$("#inputPhone3q").focus();
		}
	}

	function sendBaoMingQueryHttp(condi){
		var url = Base.serverUrl + "/api/signup/query";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendBaoMingQueryHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeBaoMingListHtml(data);
				}
				else{
					var msg = data.errorDescription || "";
					alert("报名查询失败:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeBaoMingListHtml(data){
		var obj = data.result || [];
		var html = [];
		html.push('<table class="table u_ct_15">');
		html.push('<tr style="border-top:2px solid #fff">');
		html.push('<th>#</th>');
		html.push('<th>选手一姓名</th>');
		html.push('<th>选手一电话</th>');
		html.push('<th>选手二姓名</th>');
		html.push('<th>选手二电话</th>');
		html.push('<th>选手关系</th>');
		html.push('<th>报名时间</th>');
		html.push('</tr>');

		if(obj.length == 0){
			html.push('<tr style="border-top:1px solid #ccc;border-bottom:2px solid #ccc;">');
			html.push('<td colspan=5>没有报名信息!</td>');
			html.push('</tr>');
		}
		for(var i = 0,len = obj.length; i < len; i++){
			var name = obj[i].name || "";
			var tel = obj[i].telephone || "";
			var name2 = obj[i].name2 || "";
			var tel2 = obj[i].telephone2 || "";
			var relation = obj[i].relation || "";
			var time = obj[i].createTime || "";
			if(time !== ""){
				time = time.substring(0,10);
			}

			if(i == (len - 1)){
				html.push('<tr style="border-top:2px solid #ccc;border-bottom:2px solid #ccc">');
			}
			else{
				html.push('<tr style="border-top:2px solid #ccc">');
			}
			html.push('<td>' + (i + 1) + '</td>');
			html.push('<td>' + name + '</td>');
			html.push('<td>' + tel + '</td>');
			html.push('<td>' + name2 + '</td>');
			html.push('<td>' + tel2 + '</td>');
			html.push('<td>' + relation + '</td>');
			html.push('<td>' + time + '</td>');
			html.push('</tr>');
		}
		html.push('</table>');

		$("#baominglist").html(html.join(''));
		$("#baominglist").show();
	}
});