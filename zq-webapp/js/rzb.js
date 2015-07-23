
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
	//标识是否抢购成功
	g.hasbuy = false;
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
	}

	getPackageMeterias();

	function getPackageMeterias(){
		var id = Utils.getQueryString("packageId") || "";
		if(id !== ""){
			var condi = {};
			condi.id = id;
			sendgetPackageMeteriasHttp(condi);
		}else{
			Utils.alert("没有获取到套餐id");
		}
	}

	function sendgetPackageMeteriasHttp(condi){
		var url = Base.serverUrl + "/api/product/package/" + condi.id + "/materials";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendgetPackageMeteriasHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeMeterialsHtml(data.result);
				}
				else{
					alert("获取软装包详情错误");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeMeterialsHtml(data){
		var obj = data || [];
		var html = [];

		for(var key in obj){
			var name = key || '';
			var products = obj[key] || '';
			html.push('<tr>');
			html.push('<td>'+ name +'</td>');
			html.push('<td>');
			for(var i = 0,len=products.length;i<len;i++){
				var img = products[i].pic || "";
				if(img){
					var imgUrl = img.url;
				}
				html.push('<span><img src="'+ imgUrl +'"></span>');
			}
			html.push('</td></tr>');
		}
		$('#mTable tbody').html(html.join(''));
	}

});












