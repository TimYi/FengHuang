/**
 * file:我的评论
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
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});

	getMyComment();

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
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=40%>评论内容</th>');
			html.push('<th width=100>栏目</th>');
			html.push('<th width=100>回复 / 转发</th>');
			html.push('<th width=100>评论时间</th>');
			//html.push('<th width=80>操作</th>');
			html.push('</tr>');

			for(var i = 0,len = obj.length; i < len; i++){
				var msg = obj[i].content || "";
				var column = obj[i].column || "";
				var replyNumber =  obj[i].replyNumber || 0;
				var createTime =  obj[i].createTime || "";
				var id = obj[i].id || "";
				html.push('<tr>');
				html.push('<td >' + msg + '</td>');
				html.push('<td >' + column + '</td>');
				html.push('<td >' + replyNumber + '</td>');
				html.push('<td >' + createTime + '</td>');
				//html.push('<td><a href="c_commet_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '" >查看</a></td>');
				//html.push('<td><a href="javascript:void(0);" >查看</a></td>');
				html.push('</tr>');
			}
			html.push('</table>');

			$("#commenttable").html(html.join(''));

			var totalpages = data.totalPages - 0;
			g.totalPage = totalpages;
			changePageHtml(totalpages);
		}
	}

	function changePageHtml(totalpages){
		var html = [];
		if((totalpages - g.currentPage) < 5){
			html.push('<ul class="pagination pagination-lg ">');
			html.push('<li class="f_page"><a href="#"><i class="fa fa-step-backward"></i></a></li>');
			html.push('<li class="p_page"><a href="#"><i class="fa fa-caret-left" style="font-size:1.5em"></i></a></li>');

			if(totalpages > 5){
				for(var i = totalpages - 5; i <= totalpages; i++){
					if(i == g.currentPage){
						html.push('<li class="active"><a href="#">' + i + '</a></li>');
					}
					else{
						html.push('<li ><a href="#">' + i + '</a></li>');
					}
				}
			}
			else{
				for(var i = 1; i < totalpages + 1; i++){
					if(i == g.currentPage){
						html.push('<li class="active"><a href="#">' + i + '</a></li>');
					}
					else{
						html.push('<li ><a href="#">' + i + '</a></li>');
					}
				}
			}
			html.push('<li class="n_page"><a href="#"><i class="fa fa-caret-right" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="l_page"><a href="#"><i class="fa fa-step-forward"></i></a></li>');
			html.push('</ul>');

		}
		else{
			html.push('<ul class="pagination pagination-lg ">');
			html.push('<li class="f_page"><a href="#"><i class="fa fa-step-backward"></i></a></li>');
			html.push('<li class="p_page"><a href="#"><i class="fa fa-caret-left" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="active"><a href="#">' + g.currentPage + '</a></li>');
			html.push('<li><a href="#">' + (g.currentPage + 1) + '</a></li>');
			html.push('<li><a href="#">' + (g.currentPage + 2) + '</a></li>');
			html.push('<li><a href="#">...</a></li>');
			html.push('<li><a href="#">' + (totalpages - 1) + '</a></li>');
			html.push('<li><a href="#">' + (totalpages) + '</a></li>');
			html.push('<li class="n_page"><a href="#"><i class="fa fa-caret-right" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="l_page"><a href="#"><i class="fa fa-step-forward"></i></a></li>');
			html.push('</ul>');
		}

		$("#commentpage").show();
		$("#commentpage").html(html.join(''));
		$("#commentpage > ul > li").bind("click",pageClick);
	}

	function pageClick(evt){
		var index = $(this).index();
		var text = $(this).text() - 0 || "";
		if(text !== ""){
			if(g.currentPage === text){
				alert("当前是第" + text + "页");
			}
			else{
				g.currentPage = text;
			}
		}
		else{
			var cn = $(this)[0].className;
			switch(cn){
				case "f_page":
					//第一页
					if(g.currentPage == 1){
						alert("当前是第一页");
					}
					else{
						g.currentPage = 1;
					}
				break;
				case "p_page":
					//前一页
					if(g.currentPage > 1){
						g.currentPage--;
					}
					else{
						alert("当前是第一页");
					}
				break;
				case "n_page":
					//后一页
					g.currentPage++;
				break;
				case "l_page":
					//最后一页
					g.currentPage = g.totalPage;
				break;
			}
		}

		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		sendGetMyCommentHttp(condi);
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
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});