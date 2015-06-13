/**
 * file:我的评论详情
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
	g.id = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});

	$("#backbtn").bind("click",back);
	$("#deletebtn").bind("click",deleteItem);

	getListInfo();

	function back(){
		location.href = "c_message.html?token=" + g.token + "&p=5";
	}

	function deleteItem(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		sendDeleteListInfoHttp(condi);
	}

	function getListInfo(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		sendGetListInfoHttp(condi);
	}

	function sendGetListInfoHttp(condi){
		var url = Base.messageUrl + "/" + condi.id;
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
					var msg = data.error || "";
					Utils.alert("获取我的留言错误:" + msg);
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
					Utils.alert("删除我的留言错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeListInfoHtml(obj){
		var html = [];
		var title = obj.title || "";
		var time = obj.createTime || "";
		var content = obj.content || "";
		var sender = obj.sender || "";

		html.push('<h4>' + title + '</h4>');
		html.push('<span style="color:#999;font-size:13px">' + sender + '</span>');
		html.push('<span style="color:#999;margin-left:20px;font-size:13px">' + time + '</span>');
		html.push('<hr/>');
		html.push('<p>');
		html.push(content);
		html.push('</p>');

		$("#infodiv").html(html.join(''));
	}

});








