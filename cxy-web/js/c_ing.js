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

	getLiveList();

	$("#houseselect").bind("change",changeLive);

	function getLiveList(){
		var condi = {};
		condi.token = g.token;
		sendGetLiveListHttp(condi);
	}

	function sendGetLiveListHttp(condi){
		g.httpTip.show();
		var url = Base.userlivesUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetLiveListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml("houseselect",data.result);
				}
				else{
					var msg = data.error || "";
					alert("直播数据错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeSelectHtml(domid,data){
		var option = [];
		var fid = "";
		for(var i = 0,len = data.length; i < len; i++){
			var id = data[i].id || "";
			if(i == 0){
				fid = id;
			}
			var name = data[i].house || "";
			var village = data[i].village || "";
			name = village + "-" + name;
			option.push('<option value="' + id + '"' + ( i == 0 ? "selected" : "") + '>' + name + '</option>');
		}
		$("#" + domid).html(option.join(''));

		getIngList(fid);
	}

	function changeLive(){
		var id = $(this).val();
		getIngList(id);
	}

	function getIngList(id){
		var condi = {};
		condi.token = g.token;
		condi.id = id;
		sendGetIngListHttp(condi);
	}

	function sendGetIngListHttp(condi){
		g.httpTip.show();
		var url = Base.userliveUrl + "/" + condi.id;
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
					changeIngListHtml(data.result);
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
		data = data || {};
		var lid = data.id;
		var obj = data.lives || [];

		g.listdata = obj;

		var html = [];
		html.push('<table class="table u_ct">');
		html.push('<tr class="u_th">');
		html.push('<th width=100>工期</th>');
		html.push('<th width=150>日期</th>');
		html.push('<th width=120>施工内容</th>');
		//html.push('<th width=80>操作</th>');
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
			//html.push('<td ><a href="my_live_details.html?id=' + lid + '">查看</a></td>');
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
		$("#messagepage").show();
		$("#messagepage").html(html.join(''));
		$("#messagepage > ul > li").bind("click",pageClick);
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
			sendGetMyMessageHttp(condi);
		}
		else{
			Utils.alert("当前是最后一页");
		}
	}




});