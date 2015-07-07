/**
 * file:案例直播
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

	$("#packagename >li > a").bind("click",changePackageName);
	//$("#packagestype1 >li > a").bind("click",changePackageStype);
	//$("#packagestype2 >li > a").bind("click",changePackageStype);

	getLiveList();

	function changePackageName(){
		$("#packagename >li > a").removeClass("active");
		$(this).addClass("active");
		var text = $(this).text();
		if(text == "最新"){
			g.packageName = "";
			$("#packagestype1 >li > a").removeClass("active");
			$("#packagestype2 >li > a").removeClass("active");

			g.tags = "";
			getLiveList();
		}
		else{
			//g.packageName = text;
			g.tags = text;
			getLiveList();
		}
	}

	function changePackageStype(){
		$("#packagestype1 >li > a").removeClass("active");
		$("#packagestype2 >li > a").removeClass("active");
		$(this).addClass("active");
		var text = $(this).text();
		g.packageStype = text;
		if(g.packageName == ""){
			g.tags = g.packageStype;
		}
		else{
			g.tags = g.packageName + "&" + g.packageStype;
		}
		getCaseList();
	}

	function getLiveList(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = g.tags;
		console.log(condi);
		sendGetLiveListHttp(condi);
	}

	function sendGetLiveListHttp(condi){
		var url = Base.lives;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetLiveListHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeLiveListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取直播列表错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changeLiveListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i] || {};
				var id = d.id || "";
				var mainPic = d.mainPic.url || "images/case/case_1.jpg";
				var packageName = d.name || "";
				var village = d.village || ""
				var area = d.area || "";
				var house = d.house || "";
				var startDate = d.startDate || "";

				var description = d.description || "";

				html.push('<div class="portfolio-item col-xs-12 col-sm-4 col-md-3">');
				html.push('<div class="recent-work-wrap">');
				html.push('<img class="img-responsive" src="' + mainPic + '" alt="">');
				html.push('<div class="overlay">');
				html.push('<div class="recent-work-inner">');
				html.push('<h3><a href="javascript:void(0);">' + packageName + '</a></h3>');
				html.push('<p>'+ village + '/' + area + '/' + house + '<br />开工时间:' +startDate + '</p>');
				html.push('<a href="live_details.html?id=' + id + '"  style="color:white"><i class="fa fa-eye"></i> 直播详情</a>');
				html.push('</div></div></div></div>');
			}

			$("#livelist").html(html.join(''));
			//$("#favtable .delete").bind("click",deleteFavItem);

			var totalpages = data.totalPages - 0;
			g.totalPage = totalpages;
			changePageHtml(totalpages);
		}
		else{
			$("#livelist").html("");
			$("#livepage").hide();
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

		$("#livepage").show();
		$("#livepage").html(html.join(''));
		$("#livepage > ul > li").bind("click",pageClick);
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

		getLiveList();
	}
});



