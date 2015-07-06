

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
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();



	getPackages();

	//获取字典
	function getPackages(){
		var url = Base.packagesUrl;
		var condi = {};
		condi.token = g.token;
		condi.page = 1;
		condi.size = 10;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getPackages",data);
				var status = data.status || "";
				if(status == "OK"){
					changePackageNav(data.result.result);
				}
				else{
					Utils.alert("预约类别获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changePackageNav(data){
		var html = [];
		var id1 = "";
		var id2 = "";
		var id3 = "";
		for(var i = 0,len = data.length; i < len; i++){
			var price = data[i].price - 0;
			var id = data[i].id;
			switch(price){
				case 499:
					id1 = id;
				break;
				case 699:
					id2 = id;
				break;
				case 818:
					id3 = id;
				break;
			}
		}
		html.push('<li><a href="499.html?id=' + id1 + '">499套餐</a></li>');
		html.push('<li><a href="699.html?id=' + id2 + '">699套餐</a></li>');
		html.push('<li><a href="818.html?id=' + id3 + '">818套餐</a></li>');

		$("#packages").html(html.join(''));
	}
});



