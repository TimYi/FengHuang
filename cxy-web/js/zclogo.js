
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


	getMeterias();

	function getMeterias(){
		sendMeteriasHttp();
	}

	function sendMeteriasHttp(code){
		var url = Base.serverUrl + "/api/materials";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendMeteriasHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeMeteriasHtml(data.result);
				}
				else{
					var msg = data.error || "";
					Utils.alert("获取主材品牌错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changeMeteriasHtml(data){
		var obj = data || [];

		var pb = [];
		for(var i = 0, len = obj.length; i < len; i++){
			var logoName = obj[i].name || "";
			if(logoName !== ""){
				pb.push(logoName);
			}
		}
		var logo = [];
		var phtml = [];
		logo.push('<div class="container">');
		logo.push('<div class="center wow fadeInDown">');
		logo.push('<h2>名牌主辅材<span style="font-size:24px"> FAMOUS MATERIAL</span></h2>');
		logo.push('<p class="lead">' + pb.join('、') + '等' + pb.length + ' 大一线主材品牌,为您提供品质保障、极致体验</p>');
		logo.push('</div>');
		logo.push('<div class="pricing-area text-center wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">');
		logo.push('<div class="row">');

		phtml.push('<div class="container">');
		phtml.push('<div class="wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">');
		phtml.push('<div class="tab_container">');
		phtml.push('<div id="tab1" class="tab_content" style="display: block; ">');
		phtml.push('<div class="carousel-inner">');
		phtml.push('<div class="item active">');
		phtml.push('<table class="table u_ct_15">');
		phtml.push('<tr style="border-top:2px solid #fff">');
		phtml.push('<th width=100>品牌</th>');
		phtml.push('<th width=180>产品</th>');
		phtml.push('<th>主辅材料</th>');
		phtml.push('</tr>');

		for(var j = 0, len = obj.length; j < len; j++){
			var logoUrl = obj[j].logo || "";
			if(logoUrl !== ""){
				logoUrl = logoUrl.url;
			}
			logo.push('<div class="col-sm-6 col-md-3 wow fadeInDown">');
			logo.push('<ul>');
			logo.push('<li style="background:#fff">');
			logo.push('<img src="' + logoUrl + '" />');
			logo.push('</li>');
			logo.push('</ul>');
			logo.push('</div>');
			if(j > 0 && ((j + 1) % 4 == 0)){
				logo.push('<div style="clear:both;height:20px"></div>');
			}

			var logoName = obj[j].name || "";
			var products = obj[j].products || [];
			for(var k = 0,klen = products.length; k < klen; k++){
				var pname = products[k].name || "";

				phtml.push('<tr style="border-top:2px solid #ccc">');
				if(k == 0){
					phtml.push('<td style="vertical-align: middle;" rowspan=' + klen + '>' + logoName + '</td>');
				}
				phtml.push('<td style="vertical-align: middle;">' + pname + '</td>');
				phtml.push('<td>');

				var meterias = products[k].materials || [];
				for(var n = 0,nlen = meterias.length; n < nlen; n++){
					var margin = n == 0 ? "margin-bottom:0;width:100px;" : "margin-bottom:0;width:100px;margin-left:-10px";
					var mpic = meterias[n].pic || "";
					if(mpic !== ""){
						mpic = mpic.url;
					}
					phtml.push('<img src="' + mpic + '" style="' + margin + '">');
				}
				phtml.push('</td>');
				phtml.push('</tr>');
			}
		}
		logo.push('</div>');
		logo.push('</div><!--/pricing-area-->');
		logo.push('</div><!--/.container-->');
		//logo.push('</section><!--/#feature-->');

		phtml.push('</table>');
		phtml.push('</div>');
		phtml.push('</div>');
		phtml.push('</div>');
		phtml.push('</div>');
		phtml.push('</div>');
		phtml.push('</div>');

		$("#featurelogo").html(logo.join(''));
		//$("#featurepro").html(phtml.join(''));
		//$("#featurelogo").css("visibility","visible");
		//$("#featurepro").css("visibility","visible");
	}

});



