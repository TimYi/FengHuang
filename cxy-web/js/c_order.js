/**
 * file:我的订单
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

	getMyOrder();

	//获取我的订单
	function getMyOrder(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = "";

		sendGetMyOrderHttp(condi);
	}

	//修改我的订单列表
	function changeOrderListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=25%>订单编号</th>');
			html.push('<th width=25%>订单类型</th>');
			html.push('<th>订单时间</th>');
			html.push('<th>支付状态</th>');
			html.push('<th width=150>操作</th>');
			html.push('</tr>');

			var zfobj = {"WAITING":"未支付","PAYED":"进行中","PROCESSING":"进行中","CANCEL":"已取消","COMPLETE":"已完成"};
			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id|| "";
				var name = obj[i].code || "";
				var type = obj[i].name || "";
				var time =  obj[i].payTime || "";
				var status = obj[i].status || "";
				var statusText = "";
				if(status !== ""){
					status = status.toUpperCase();
					statusText = zfobj[status] || "";
				}
				var cid = obj[i].good.id || "";

				html.push('<tr>');
				html.push('<td >' + name + '</td>');
				html.push('<td >' + type + '</td>');
				html.push('<td >' + time + '</td>');
				if(status == "WAITING"){
					html.push('<td ><a href="../orderback_paysel.html?id=' + id + '" >支付</a></td>');
				}
				else{
					html.push('<td >' + statusText + '</td>');
				}
				if(status == "WAITING" || status == "PAYED" || status == "PROCESSING"){
					html.push('<td><a href="c_order_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="delete_' + i + '" href="javascript:orderCancel(\'' + id + '\');" class="delete" >取消</a></td>');
				}
				else if(status == "CANCEL"){
					html.push('<td><a href="c_order_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="delete_' + i + '" href="javascript:Utils.alert(\'订单已取消\');" class="delete" >取消</a></td>');
				}
				else{
					html.push('<td><a href="c_order_item.html?id=' + id + '&token=' + g.token + '&p=' + g.page + '">查看</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="delete_' + i + '" href="javascript:Utils.alert(\'订单已完成\');" class="delete" >取消</a></td>');
				}
				html.push('</tr>');
			}
			html.push('</table>');

			$("#ordertable").html(html.join(''));

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
			sendGetMyOrderHttp(condi);
		}
		else{
			Utils.alert("当前是最后一页");
		}
	}


	function sendGetMyOrderHttp(condi){
		var url = Base.orderUrl;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyOrderHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeOrderListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的订单错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}


	function miaoSha(id){
		var url = Base.scramble;
		var condi = {};
		condi.token = g.token;
		condi.id = id;
		condi.caseId = "";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("miaoSha",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("抢购成功");
					var orderId = data.result.id;
					location.href = "orderback_paysel.html?id=" + orderId;
				}
				else{
					Utils.alert("抢购失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function orderCancel(id){
		var url = Base.serverUrl + "/api/user/order/" + id + "/cancel";
		var condi = {};
		condi.token = g.token;
		condi.id = id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("orderCalcel",data);
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("取消订单成功");
					setTimeout(function(){
						getMyOrder();
					},500);
				}
				else{
					var msg = data.error;
					Utils.alert("取消订单错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	window.miaoSha = miaoSha;
	window.orderCancel = orderCancel;
});