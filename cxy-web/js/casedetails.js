/**
 * file:案例馆
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
	g.caseId = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 10;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	getCaseDetails();

	function getCaseDetails(){
		var condi = {};
		condi.id = g.caseId;

		sendGetCaseDetailsHttp(condi);
	}

	function sendGetCaseDetailsHttp(condi){
		var url = Base.casedetails + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetCaseDetailsHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					//changeCaseListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取装修案例列表错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}


	function changeCaseListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i] || {};
				var id = d.id || "";
				var mainPic = d.mainPic || "images/case/case_1.jpg";
				var packageName = d.packageName || "";
				var stype = d.stype || "";
				var space = d.space || "";
				var houseType = d.houseType || "";
				var area = d.area || "";
				var description = d.description || "";

				html.push('<div class="portfolio-item col-xs-12 col-sm-4 col-md-3">');
				html.push('<div class="recent-work-wrap">');
				html.push('<img class="img-responsive" src="' + mainPic + '" alt="">');
				html.push('<div class="overlay">');
				html.push('<div class="recent-work-inner">');
				html.push('<h3><a href="javascript:void(0);">' + packageName + '/' + stype + '/' + space + '</a></h3>');
				html.push('<p>' + houseType + '/' + area + '/' + description + '</p>');
				html.push('<a href="case_details.html?id=' + id + '"  style="color:white"><i class="fa fa-eye"></i> 案例详情</a>');
				html.push('</div></div></div></div>');
			}

			$("#caselist").html(html.join(''));
			//$("#favtable .delete").bind("click",deleteFavItem);

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

		$("#casepage").show();
		$("#casepage").html(html.join(''));
		$("#casepage > ul > li").bind("click",pageClick);
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
		//condi.token = g.token;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.tags = g.tags;
		sendGetCaseListHttp(condi);
	}
});



