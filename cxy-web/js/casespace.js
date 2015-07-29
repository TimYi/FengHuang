
//页面初始化
$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false);
	g.page = Utils.getQueryString("p") - 0;
	g.packageId =  Utils.getQueryString("packageId") || "";
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.packageName = "";
	g.packageStype = "";
	g.tags = "";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();


	getPackage();

	function getPackage(){
		if(g.packageId !== ""){
			sendPackageHttp();
		}
		else{
			Utils.alert("没有获取到套餐ID");
		}
	}

	function sendPackageHttp(code){
		var url = Base.serverUrl + "/api/product/package/" + g.packageId;
		//var url = "http://www.ifhzj.com" + "/api/product/package/" + g.packageId;
		g.httpTip.show();
		var condi = {};
		condi.id =  g.packageId;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendPackageHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changePackageHtml(data.result);
				}
				else{
					var msg = data.errorDescription || "";
					Utils.alert("获取套餐详情错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changePackageHtml(data){
		var obj = data || [];


		var title = [];
		var phtml = [];

		phtml.push('<div class="container">');

		phtml.push('<div class="center wow fadeInDown">');
		phtml.push('<h2>' + g.packageId + '套餐空间配置<span style="font-size:24px"> SPACE ALLOCATION</span></h2>');
		phtml.push('<p class="lead">资深设计师，为您提供一对一免费设计服务，打造最完美的装修方案</p>');
		phtml.push('</div>');

		phtml.push('<ul class="portfolio-filter text-center" id="myTab">');
		var spaces = obj.spaces || [];
		for(var i = 0,len = spaces.length; i < len; i++){
			var css = i == 0 ? "active" : "";
			var name = spaces[i].name || ""
			phtml.push('<li><a class="btn btn-default ' + css + '" href="#pack_' + i + '" >' + name + '</a></li>');
		}
		phtml.push('</ul>');

		phtml.push('<div class="tab-content">');
		for(var j = 0,jlen = spaces.length; j < jlen; j++){
			var css = j == 0 ? "active" : "";
			phtml.push('<div class="tab-pane ' + css + '" id="pack_' + j + '" >');

			var pic1 = spaces[j].pic1 || "";
			var pic2 = spaces[j].pic2 || "";
			var pic3 = spaces[j].pic3 || "";
			var imgs = [];
			if(pic1 !== ""){
				imgs.push(pic1.url);
			}
			if(pic2 !== ""){
				imgs.push(pic2.url);
			}
			if(pic3 !== ""){
				imgs.push(pic3.url);
			}

			phtml.push('<div class="row">');
			for(var k = 0,klen = imgs.length; k < klen; k++){
				phtml.push('<div class="col-xs-9 col-sm-4 col-md-4">');
				phtml.push('<div class="recent-work-wrap">');
				phtml.push('<img class="img-responsive" src="' + imgs[k] + '" alt="" style="width:347px;height:263px;">');
				phtml.push('</div>');
				phtml.push('</div>');
			}
			phtml.push('</div>');

			phtml.push('<div class="carousel-inner mt20">');
			phtml.push('<div class="item ' + css + '">');

			phtml.push('<table class="table u_ct_15">');
			phtml.push('<tr style="border-top:2px solid #fff">');
			phtml.push('<th>#</th>');
			phtml.push('<th width="150">客厅包含项目</th>');
			phtml.push('<th>品牌</th>');
			phtml.push('<th>数量</th>');
			phtml.push('<th>详细说明</th>');
			phtml.push('</tr>');

			var items = spaces[j].items || [];
			for(var n = 0,nlen = items.length; n < nlen; n++){
				var name = items[n].name || "";
				var brand = items[n].brand || "";
				var number = items[n].number || "";
				var description = items[n].description || "";
				if(n == 0){
					phtml.push('<tr style="border-top:2px solid #ccc">');
				}else{
					phtml.push('<tr style="border-top:1px solid #ccc">');
				}
				phtml.push('<td>' + (n + 1) + '</td>');
				phtml.push('<td>' + name + '</td>');
				phtml.push('<td>' + brand + '</td>');
				phtml.push('<td>' + number + '</td>');
				phtml.push('<td>' + description + '</td>');
				phtml.push('</tr>');
			}
			phtml.push('</table>');

			phtml.push('</div>');
			phtml.push('</div>');

			phtml.push('</div>');
		}

		phtml.push('</div>');

		phtml.push('</div>');

		//$("#recent-works").html(logo.join(''));
		$("#recent-works").html(phtml.join(''));
		//$("#featurelogo").css("visibility","visible");
		//$("#featurepro").css("visibility","visible");



		$(function(){
			$('#myTab a:first').tab('show');//初始化显示哪个tab

			$('#myTab a').click(function (e) {
				e.preventDefault();//阻止a链接的跳转行为
				$(this).tab('show');//显示当前选中的链接及关联的content

				$("#recent-works .item.active").removeClass("active");
				var href = $(this).attr("href");
				$(href + " .item").addClass("active");
			});
		});
	}

});



