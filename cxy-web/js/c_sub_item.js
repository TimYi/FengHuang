/**
 * file:我的预约详情
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
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.id = Utils.getQueryString("id") ;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});

	$("#backbtn").bind("click",back);
	$("#deletesubbtn").bind("click",deleteItem);

	getListInfo();

	function back(){
		location.href = "c_sub.html?token=" + g.token + "&p=6";
	}

	function deleteItem(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		//sendDeleteListInfoHttp(condi);
	}

	function getListInfo(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		sendGetListInfoHttp(condi);
	}

	function sendGetListInfoHttp(condi){
		var url = Base.serverUrl + "/api/user/museumAppoint/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeListInfoHtml(data.result);
				}
				else{
					var msg = data.errorDescription || "";
					alert("获取我的预约详情错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.messageUrl + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					//changeListInfoHtml(data.result);
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的预约错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}



	function changeListInfoHtml(data){
		var obj = data || {};

		var arr = {"WAITING":"等待客服确认","PROCESSING":"处理中","FINISH":"已到店","CANCEL":"取消"};
		var code = obj.code || "";
		var user = obj.user || "";
		var type = obj.museum.name || "";
		var realName = obj.realName || "";
		var mobile = obj.mobile || "";
		var status = obj.status || "";
		if(status == ""){
			status = status[arr] || "";
		}
		var message = obj.message || "";
		var appointTime = obj.appointTime || ""
		appointTime = appointTime.substring(0,10);

		var html = [];
		html.push('<h4>' + type + '</h4>');
		//html.push('<span style="color:#999;font-size:13px">状态：<!--客服人员已确认--></span>');
		//html.push('<span style="color:#999;margin-left:20px;font-size:13px">时间：' + createTime + '</span>');
		html.push('<hr/>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		//html.push('<li>预约类型：' + type + '</li>');
		html.push('<li>预约编号：' + code + '</li>');
		html.push('<li>预约用户：' + user + '</li>');
		html.push('<li>真实姓名：' + realName + '</li>');
		html.push('<li>留言：' + message + '</li>');
		//html.push('<li>所在城市：' + city + '</li>');
		//html.push('<li>详细地址：<!--海淀区上地七街--></li>');
		html.push('</ul>');
		html.push('</div>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		html.push('<li>预约时间：' + appointTime + '</li>');
		html.push('<li>预约状态：' + status + '</li>');
		//html.push('<li>有效期限：<!--2015-06-30 00:00:00--></li>');
		html.push('<li>手机号码：'  + mobile + '</li>');
		html.push('</ul>');
		html.push('</div>');

		$("#infodiv").html(html.join(''));
		$("#infodiv").show();
	}
});















