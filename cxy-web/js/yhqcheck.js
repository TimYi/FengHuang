

$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendCode2 = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.userprofile = Utils.offLineStore.get("login_userprofile",false) || "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();
	g.reserveStatus = false;
	if(g.loginStatus && g.userprofile !== ""){
		var obj = JSON.parse(g.userprofile);
		var name = obj.realName || "";
		var mobile = obj.mobile || "";
		if(name !== "" && mobile !== ""){
			//允许预约
			g.reserveStatus = true;
		}
		else{
			g.reserveStatus = false;
		}
		//$("#name").val(name);
		//$("#phone").val(mobile);

		//$("#name2").val(name);
		//$("#phone2").val(mobile);

		//getImgCode();
		//getImgCode2();
	}


	getScramble();


	function getScramble(){
		var url = Base.scrambleUrl;
		var condi = {};
		condi.token = g.token;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getScramble",data);
				var status = data.status || "";
				if(status == "OK"){
					changeCouponDetailsHtml(data);
				}
				else{
					Utils.alert("优惠券抢购失败");
					changeCouponErrorHtml(data);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function changeCouponErrorHtml(data){
		var html = [];
		html.push('<div style="margin:50px 0 0 50px;padding:10px;text-align:center;vertical-align:middle;border:4px solid #ff0000;-moz-border-radius:50%;-webkit-border-radius:50%;border-radius:50%; height:100px;width:100px">');
		html.push('<i class="fa fa-close" style="color:#ff0000;font-size:70px"></i>');
		html.push('</div>');
		$("#statusdiv").show();
		$("#statusdiv").html(html.join(''));


		var msg = data.errorDescription;
		var d = [];
		d.push('<li style="font-weight:800;font-size:20px">抢购优惠券失败</li>');
		d.push('<li class="u_li">原因：<b >' + msg + '</b></li>');
		$("#coupondetail").html(d.join(''));
		$("#coupondetail").show();
	}
	function changeCouponDetailsHtml(data){
		var obj = data.result || {};
		var name = obj.name || "";
		var couponsMoney = obj.couponsMoney || "";
		var id = obj.id || "";
		var expireTime = obj.expireTime || "";
		var html = [];
		if(!g.orderResult){
			html.push('<li style="font-weight:800;font-size:20px">您已成功抢购一张家装抵用券</li>');
		}
		else{
			html.push('<li style="font-weight:800;font-size:20px;color:red;">抢购优惠券失败</li>');
		}

		html.push('<li class="u_li">名称：<b style="color:#83bd39">' + name + '</b></li>');
		html.push('<li class="u_li">面值：<b style="color:#83bd39">' + couponsMoney + '元</b></li>');
		html.push('<li class="u_li">编号：<b style="color:#83bd39">' + id + '</b></li>');
		html.push('<li class="u_li">提示：请于' + expireTime + ' 前使用，过期作废。</li>');
		$("#statusdiv").show();
		$("#coupondetail").html(html.join(''));
		$("#coupondetail").show();
	}
});









