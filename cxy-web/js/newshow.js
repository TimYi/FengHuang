/**
 * file:案例直播
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
	g.id = Utils.getQueryString("id") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.packageName = "";
	g.packageStype = "";
	g.tags = "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();


	getArtical();

	function getArtical(){
		var code = "";
		var title = "帮助中心";
		switch(g.id){
			case 22:
				code = "服务指南";
				title = "帮助中心";
			break;
			case 23:
				code = "支付方式";
				title = "帮助中心";
			break;
			case 24:
				code = "常见问题";
				title = "帮助中心";
			break;
			case 25:
				code = "售后政策";
				title = "服务支持";
			break;
			case 26:
				code = "自助服务";
				title = "服务支持";
			break;
			case 27:
				code = "相关下载";
				title = "服务支持";
			break;
			case 28:
				code = "会员俱乐部";
				title = "凤凰之家";
			break;
			case 29:
				code = "会员活动";
				title = "凤凰之家";
			break;
			case 30:
				code = "VIP服务";
				title = "凤凰之家";
			break;
			case 31:
				code = "联系我们";
				title = "关于我们";
			break;
			case 32:
				code = "了解我们";
				title = "关于我们";
			break;
			case 33:
				code = "加入我们";
				title = "关于我们";
			break;
		}
		$("#newstitle").html("首页 > 文章 > " + title + " > 正文");
		sendArticalHttp(code);
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
					changeArticalHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取服务指南错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changeArticalHtml(data){
		var obj = data.result || {};
		var title = obj.title || "";
		var content = obj.content || "";
		var html = [];
		html.push('<ul>');
		html.push('<li>');
		html.push('<h3>' + title + '</h3>');
		html.push('<p>');
		html.push(content);
		html.push('</p>');
		html.push('</li>');
		html.push('</ul>');

		$("#articaldiv").html(html.join(''));
	}

});



