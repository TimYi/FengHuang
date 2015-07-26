/**
 * file:我的预约
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


	getMySub();

	//获取我的预约
	function getMySub(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = "";

		sendGetMySubHttp(condi);
	}

	function changeSubListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=20%>预约编号</th>');
			html.push('<th width=15%>预约类型</th>');
			html.push('<th width=15%>真实姓名</th>');
			html.push('<th width=15% >预约状态</th>');
			html.push('<th width=20%>预约时间</th>');
			html.push('<th width=100>操作</th>');
			html.push('</tr>');

			var arr ={"WAITING":"等待客服确认","PROCESSING":"处理中","FINISH":"已到店","CANCEL":"取消"};
			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i];
				var id = d.id || "";
				var num = d.code || "";
				var type = d.museum.name || "";
				var realName = d.realName || "";
				var status = d.status || "";
				if(status !== ""){
					status = arr[status] || "";
				}
				var createTime = d.createTime || "";
				createTime.substring(0,10);
				html.push('<tr>');
				html.push('<td >' + num + '</td>');
				html.push('<td >' + type + '</td>');
				html.push('<td >' + realName + '</td>');
				html.push('<td >' + status + '</td>');
				html.push('<td >' + createTime + '</td>');
				//html.push('<td><a href="c_sub_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '" >查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0);">删除</a></td>');
				html.push('<td><a href="c_sub_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '" >查看</a></td>');
				html.push('</tr>');
			}
			html.push('</table>');

			$("#subtable").html(html.join(''));

			var totalpages = data.totalPages - 0;
			g.totalPage = totalpages;
			changePageHtml(totalpages);
		}
	}

	function changePageHtml(totalpages){
		var html = [];
		if((totalpages - g.currentPage) < 5){
			html.push('<ul class="pagination pagination-lg ">');
			html.push('<li class="f_page"><a javascript:void(0);><i class="fa fa-step-backward"></i></a></li>');
			html.push('<li class="p_page"><a javascript:void(0);><i class="fa fa-caret-left" style="font-size:1.5em"></i></a></li>');

			if(totalpages > 5){
				for(var i = totalpages - 5; i <= totalpages; i++){
					if(i == g.currentPage){
						html.push('<li class="active"><a javascript:void(0);>' + i + '</a></li>');
					}
					else{
						html.push('<li ><a javascript:void(0);>' + i + '</a></li>');
					}
				}
			}
			else{
				for(var i = 1; i < totalpages + 1; i++){
					if(i == g.currentPage){
						html.push('<li class="active"><a javascript:void(0);>' + i + '</a></li>');
					}
					else{
						html.push('<li ><a javascript:void(0);>' + i + '</a></li>');
					}
				}
			}
			html.push('<li class="n_page"><a javascript:void(0);><i class="fa fa-caret-right" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="l_page"><a javascript:void(0);><i class="fa fa-step-forward"></i></a></li>');
			html.push('</ul>');

		}
		else{
			html.push('<ul class="pagination pagination-lg ">');
			html.push('<li class="f_page"><a javascript:void(0);><i class="fa fa-step-backward"></i></a></li>');
			html.push('<li class="p_page"><a javascript:void(0);><i class="fa fa-caret-left" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="active"><a javascript:void(0);>' + g.currentPage + '</a></li>');
			html.push('<li><a javascript:void(0);>' + (g.currentPage + 1) + '</a></li>');
			html.push('<li><a javascript:void(0);>' + (g.currentPage + 2) + '</a></li>');
			html.push('<li><a javascript:void(0);>...</a></li>');
			html.push('<li><a javascript:void(0);>' + (totalpages - 1) + '</a></li>');
			html.push('<li><a javascript:void(0);>' + (totalpages) + '</a></li>');
			html.push('<li class="n_page"><a javascript:void(0);><i class="fa fa-caret-right" style="font-size:1.5em"></i></a></li>');
			html.push('<li class="l_page"><a javascript:void(0);><i class="fa fa-step-forward"></i></a></li>');
			html.push('</ul>');
		}

		$("#subpage").show();
		$("#subpage").html(html.join(''));
		$("#subpage > ul > li").bind("click",pageClick);
	}

	function pageClick(evt){
		var index = $(this).index();
		var text = $(this).text() - 0 || "";
		if(text !== ""){
			if(g.currentPage === text){
				Utils.alert("当前是第" + text + "页");
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
						Utils.alert("当前是第一页");
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
						Utils.alert("当前是第一页");
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
		if(g.currentPage <= g.totalPage){
			var condi = {};
			condi.token = g.token;
			condi.page = g.currentPage;
			condi.size = g.paseSize;
			sendGetMySubHttp(condi);
		}
		else{
			Utils.alert("当前是最后一页");
		}
	}


	function sendGetMySubHttp(condi){
		var url = Base.serverUrl + "user/museumAppoints";
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyMuseumHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeSubListHtml(data.result);
				}
				else{
					var msg = data.errorDescription || "";
					alert("获取我的预约列表错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}
});