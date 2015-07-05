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
				html.push('<ul class="am-avg-sm-2 house-list"><li class="h-left">');
                html.push('<div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="javascript:void(0)"><div><ul class="uhouse">');
                html.push('<li class="ubig">'+ name +'</li>');
                html.push('<li class="usmall">'+ couponsMoney + ' / '+ expireTime + ' / ' + str + '</li></ul></div></a></div></li>');
                html.push('<li class="h-right"><div class="am-dropdown" data-am-dropdown>');
                html.push('<a href="javascript:void(0)">');
                html.push('<div><i class="am-icon-angle-right"></i></div></a></div></li></ul>');
			}
			
			$("#myCoupon").html(html.join(''));

			//var totalpages = data.totalPages - 0;
			//g.totalPage = totalpages;
			//changePageHtml(totalpages);
		}
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