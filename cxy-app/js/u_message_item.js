/**
 * file:留言信息
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
	g.token = Utils.offLineStore.get("token",false);
	g.messageId = Utils.getQueryString("id") || "";
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.data = {};
	g.httpTip = new Utils.httpTip({});


	//$("#districtNamebtn").bind("click",changePage);

	getMessageDetails();
	function getMessageDetails(){
		if(g.messageId !== ""){
			var condi = {};
			condi.token = g.token;
			condi.id = g.messageId;
			sendGetMessageDetailsHttp(condi);
		}
		else{
			Utils.alert("参数错误");
		}
	}
	function sendGetMessageDetailsHttp(condi){
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
				console.log("sendGetMessageDetailsHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeMessageDetailsHtml(data.result);
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

	function changeMessageDetailsHtml(data){
		var obj = data || {};
		var title = obj.title || "";
		var content = obj.content || "";
		var createTime = obj.createTime || "";

		var html = [];
		html.push('<dt>' + title + '</dt>');
		html.push('<dd>' + createTime + '</dd>');
		html.push('<hr/>');
		html.push('<li>' + content + '</li>');
		html.push('<hr/>');
		html.push('<a id="deletebtn" href="javascript:void(0);">');
		html.push('<div class="am-btn am-btn-secondary" style="background:#f25618;border-color:#f25618;width:100%" type="button">');
		html.push('删除');
		html.push('</div>');
		html.push('</a>');

		$("#messagedetails").html(html.join(''));
		$("#messagedetails").show();

		$("#deletebtn").bind("click",deleteMessage);
	}

	function deleteMessage(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.messageId;
		sendDeleteListInfoHttp(condi);
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.messageUrl + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			headers:{"fhzj_auth_token":condi.token},
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendDeleteListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("删除留言成功");
					//getMyMessage();
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



});















