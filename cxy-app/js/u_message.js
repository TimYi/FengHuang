/**
 * file:我的留言
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
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});


	//$("#addbtn").bind("click",addHome);
	//$("#updatebtn").bind("click",updateHome);

	getMyMessage();

	//获取我的留言
	function getMyMessage(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;

		sendGetMyMessageHttp(condi);
	}

	function sendGetMyMessageHttp(condi){
		g.httpTip.show();
		var url = Base.messagesUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyMessageHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeMessageListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的留言错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function changeMessageListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;
			var html = [];

			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i];
				var id = d.id || "";
				var content = d.content || "";
				var createTime = d.createTime || "";

				html.push('<ul class="am-avg-sm-2" style="padding:5px 0 5px 0;box-shadow: inset 0 -1px 1px -1px #aaa;background:#fff;">');
				html.push('<li style="padding:0;text-align:left;width:90%">');
				html.push('<div class="am-dropdown" data-am-dropdown style="width:100%;height:50px;float:left">');
				html.push('<a href="u_message_item.html?id=' + id + '">');
				html.push('<div style="line-height:50px;padding-left:20px;">');
				html.push('<ul class="uhouse">');
				html.push('<li class="ubig">' + content + '</li>');
				html.push('<li class="usmall">' + createTime + '</li>');
				html.push('</ul>');
				html.push('</div>');
				html.push('</a>');
				html.push('</div>');
				html.push('</li>');
				html.push('<li style="padding:0;text-align:right;width:10%">');
				html.push('<div class="am-dropdown" data-am-dropdown style="height:50px">');
				html.push('<a href="u_message_item.html?id=' + id + '">');
				html.push('<div style="line-height:50px;padding-right:10px">');
				html.push('<i class="am-icon-angle-right" style="color:#ccc;padding-left:10px;font-size:20px"></i>');
				html.push('</div>');
				html.push('</a>');
				html.push('</div>');
				html.push('</li>');
				html.push('</ul>');
			}

			$("#messagelist").html(html.join(''));
			//$("#houselist  a").bind("click",operateBtn);
			//var totalpages = data.totalPages - 0;
			//g.totalPage = totalpages;
			//changePageHtml(totalpages);
		}
	}




});















