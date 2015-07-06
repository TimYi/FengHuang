$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.ptab = Utils.getQueryString("pt") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];
	g.packageName = "";
	g.packageStype = "";
	g.tags = "";
	g.status ="";
	//验证登录状态
	g.loginStatus = Utils.getUserInfo();

	getCaseList();
	getCaseTags();
	getLiveList();

	$("#liveTag .tag").bind("click",changeLiveType);

	function getCaseTags(){
		var url = Base.caseTags;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			global:false,
			success:function(data){
				var status = data.status;
				if(status == "OK"){
					changeCaseTagHtml(data.result);
				}else{
					var msg = data.error || "";
					console.log("获取装修案例标签错误:" + msg);
				}
			},
			error:function(){
				g.httpTip.hide();
			}
		});
	}

	function changeCaseTagHtml(data){
		var obj = data || [];
		if(obj.length > 0){
			g.listdata = obj;

			var html = [];
			html.push('<span class="tag active">全部</span>');
			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i] || {};
				var name = d.name || "";
				html.push('<span class="tag">'+name+'</span>');
			}
			$("#caseTag").html(html.join(''));
			$("#caseTag .tag").on("click",changeCaseType);
		}
	}

	function changeCaseType(){
		$("#caseTag").find('.tag').removeClass("active");
		$(this).addClass("active");
		var text = $(this).text();
		g.packageStype = text;
		if(text == "全部"){
			g.packageName = "";
			//$(".tag-wrap .tag").removeClass("active");
			g.tags = "";
			getCaseList();
		}else{
			g.packageName = text;
			g.tags = text;
			getCaseList();
		}
	}

	function changeLiveType(){
		$("#liveTag .tag").removeClass("active");
		$(this).addClass("active");
		var text = $(this).text();
		if(text == "最新"){
			g.packageName = "";
			g.status = "";
			getLiveList();
		}
		else{
			//g.packageName = text;
			g.status = text;
			getLiveList();
		}
	}

	function getCaseList(){
		//token:用户凭据
		//page:当前页码
		//size:每页数据量
		var condi = {};
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.tags = g.tags;
		sendGetCaseListHttp(condi);
	}

	function sendGetCaseListHttp(condi){
		var url = Base.cases;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				var status = data.status || "";
				if(status == "OK"){
					changeCaseListHtml(data.result);
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
		var html = [];
		if(obj.length > 0){
			g.listdata = obj;

			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i] || {};
				var id = d.id || "";
				var mainPic = d.mainPic || "";
				if(mainPic !== ""){
					mainPic = mainPic.url || "images/case/case_1.jpg";
				}
				var packageName = d.packageName || "";
				//var stype = d.stype || "";
				//var space = d.space || "";
				//var houseType = d.houseType || "";
				//var area = d.area || "";
				var description = d.description || "";

				html.push('<li><div class="am-gallery-item">');
				html.push('<a href="case_detail.html?id='+id+'"><img src="'+mainPic+'" alt="'+description+'" data-am-pureviewed="1"></a>');
				html.push('<h3 class="am-gallery-title" style="font-size:12px;">'+packageName+'</h3>');
				html.push('</div>');
			}
		}
		$("#caseList").html(html.join(''));

	}

	function getLiveList(){
		var condi = {};
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		condi.status = g.status;
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
				console.log(data);
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
				var mainPic = d.mainPic || "";
				if(mainPic !== ""){
					mainPic = mainPic.url || "images/case/case_1.jpg";
				}
				var name = d.name || "";
				//var stype = d.stype || "";
				//var space = d.space || "";
				//var houseType = d.houseType || "";
				//var area = d.area || "";
				//var description = d.description || "";

				html.push('<li><div class="am-gallery-item">');
				html.push('<a href="live_detail.html?id='+id+'"><img src="'+mainPic+'" alt="'+name+'" data-am-pureviewed="1"></a>');
				html.push('<h3 class="am-gallery-title" style="font-size:12px;">'+name+'</h3>');
				html.push('</div>');
			}

			$("#liveList").html(html.join(''));

		}
	}
});


