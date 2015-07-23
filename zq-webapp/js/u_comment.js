$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 99999999;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});

	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
	}else{
		getMyComment();
	}

	//获取我评论
	function getMyComment(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;

		sendGetMyCommentHttp(condi);
	}

	//修改我的评论列表
	function changeCommentListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];

			for(var i = 0,len = obj.length; i < len; i++){
				var msg = obj[i].content || "";
				var column = obj[i].column || "";
				var content = obj[i].content || "";
				var createTime =  obj[i].createTime || "";
				var sourceUrl = obj[i].url || '';
				var mUrl = sourceUrl.replace('ifhzj.com','ifhzj.com/mobile');
				var mUrl = mUrl.replace('live_details','live_detail');
				var id = obj[i].id || "";
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="'+ mUrl +'"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ content  +'</li>');
                html.push('<li class="usmall">'+ column+ ' / ' + createTime + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="'+ mUrl +'">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}
			$("#myComment").html(html.join(''));
		}
	}

	//获取我的评论
	function sendGetMyCommentHttp(condi){
		var url = Base.commentsUrl;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyCommentHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeCommentListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的评论错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});