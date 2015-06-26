/**
 * file:我的优惠券
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


	getMyCoupon();

	//获取我的优惠券
	function getMyCoupon(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = "";

		sendGetMyCouponHttp(condi);
	}

	//修改我的优惠券列表
	function changeCouponListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			var html = [];
			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th width=30%>优惠券名称</th>');
			html.push('<th width=30%>优惠金额</th>');
			html.push('<th width=120>过期时间</th>');
			html.push('<th width=80>状态</th>');
			html.push('</tr>');

			for(var i = 0,len = obj.length; i < len; i++){
				var id = obj[i].id || "";
				var name = obj[i].name || "";
				var couponsMoney = obj[i].couponsMoney || 0;
				var expireTime = obj[i].expireTime || "";
				var expired = obj[i].expired || false;
				var used = obj[i].used || false;
				var str = "未使用";
				if(expired){
					str = "已过期";
				}
				if(used){
					str = "已使用";
				}
				html.push('<tr>');
				html.push('<td >' + name + '</td>');
				html.push('<td >' + couponsMoney + '</td>');
				html.push('<td >' + expireTime + '</td>');
				html.push('<td>' + str + '</td>');
				html.push('</tr>');
			}
			html.push('</table>');
			$("#coupontable").html(html.join(''));

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

		$("#couponpage").show();
		$("#couponpage").html(html.join(''));
		$("#couponpage > ul > li").bind("click",pageClick);
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
		sendGetMyCouponHttp(condi);
	}


	function sendGetMyCouponHttp(condi){
		var url = Base.couponsUrl ;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetMyCouponHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeCouponListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("获取我的优惠券错误:" + msg);
				}
			},
			error:function(data){
			}
		});
	}
});