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
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	getHomeList();
	getIngList();

	function getHomeList(){
		var condi = {};
		condi.token = g.token;
		sendGetHomeListHttp(condi);
	}

	function sendGetHomeListHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml("houseselect",data.result);
				}
				else{
					var msg = data.error || "";
					alert("房屋信息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeSelectHtml(domid,data){
		var option = [];
		for(var i = 0,len = data.length; i < len; i++){
			var id = data[i].id || "";
			var name = data[i].districtName || "";
			option.push('<option value="' + id + '"' + ( i == 0 ? "selected" : "") + '>' + name + '</option>');
		}
		$("#" + domid).html(option.join(''));
	}


	function getIngList(){
		var condi = {};
		condi.token = g.token;
		sendGetIngListHttp(condi);
	}

	function sendGetIngListHttp(condi){
		g.httpTip.show();
		var url = Base.lives;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetIngListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeIngListHtml(data.result[0].lives);
				}
				else{
					var msg = data.error || "";
					alert("获取我的家装进度:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeIngListHtml(data){
		var obj = data || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=100>工期</th>');
			html.push('<th width=150>日期</th>');
			html.push('<th>施工内容</th>');
			html.push('<th width=80>操作</th>');
			html.push('</tr>');

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var day = obj[i].day || "";
				var date = obj[i].date || "";
				var title = obj[i].title || "";
				//var time = obj[i].createTime || "2015-06-02 10:00";

				html.push('<tr>');
				html.push('<td >' + day + '</td>');
				html.push('<td >' + date + '</td>');
				html.push('<td >' + title + '</td>');
				html.push('<td ><a href="#">查看</a></td>');
				//html.push('<td><a href="c_message_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '" >查看</a></td>');
				html.push('</tr>');
			}
			html.push('</table>');

			$("#ingtable").html(html.join(''));
			//$("#ingtable .delete").bind("click",deleteMessageItem);

			//var totalpages = data.totalPages - 0;
			//g.totalPage = totalpages;
			//changePageHtml(totalpages);
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
		$("#messagepage").show();
		$("#messagepage").html(html.join(''));
		$("#messagepage > ul > li").bind("click",pageClick);
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
		sendGetMyMessageHttp(condi);
	}




});