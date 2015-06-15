/**
 * file:我的收藏
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
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	getMyFav();

	//获取我的收藏
	function getMyFav(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;

		sendGetMyFavHttp(condi);
	}

	function deleteFavItem(){
		var id = this.id;
		var index = id.split("_")[1];
		var obj = g.listdata[index];
		var mid = obj.id;
		var condi = {};
		condi.token = g.token;
		condi.id = mid;
		console.log(condi);

		sendDeleteListInfoHttp(condi);
	}

	//修改我的收藏列表
	function changeFavListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=50%>收藏内容</th>');
			html.push('<th width=90>收藏栏目</th>');
			html.push('<th width=120>收藏时间</th>');
			html.push('<th width=80>操作</th>');
			html.push('</tr>');

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var name = obj[i].name || "";
				var column = obj[i].column || "";
				var time = obj[i].createTime || "";
				html.push('<tr>');
				html.push('<td >' + name + '</td>');
				html.push('<td >' + column + '</td>');
				html.push('<td >' + time + '</td>');
				html.push('<td><a href="c_fav_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '" >查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="delete_' + i + '" href="javascript:void(0);" class="delete" >删除</a></td>');
				//html.push('<td><a href="#">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">删除</a></td>');
				html.push('</tr>');
			}
			html.push('</table>');

			$("#favtable").html(html.join(''));
			$("#favtable .delete").bind("click",deleteFavItem);

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

		$("#favpage").show();
		$("#favpage").html(html.join(''));
		$("#favpage > ul > li").bind("click",pageClick);
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
		sendGetMyFavHttp(condi);
	}


	//获取我的收藏
	function sendGetMyFavHttp(condi){
		var url = Base.collectsUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyFavHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeFavListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的收藏列表错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.collectUrl + "/" + condi.id;
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
					Utils.alert("删除收藏成功");
					getMyFav();
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的收藏错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
});