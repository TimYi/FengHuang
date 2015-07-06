$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.offLineStore.get("token",false) || "";
	g.liveId = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 2;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	//验证登录状态
	g.loginStatus = Utils.getUserInfo();

	getLiveDetails();
	getLiveDayDetails();

	function getLiveDetails(){
		var condi = {};
		condi.id = g.liveId;

		sendGetLiveDetailsHttp(condi);
	}
	function getLiveDayDetails(){
		var condi = {};
		condi.id = g.liveId;
		condi.page = g.currentPage;
		condi.size = g.paseSize;
		sendGetLiveDayDetailsHttp(condi);
	}

	function sendGetLiveDetailsHttp(condi){
		var url = Base.live + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetLiveDetailsHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeLiveDetailHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取装修直播详情错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendGetLiveDayDetailsHttp(condi){
		var url = Base.live + "/" + condi.id + "/details";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetLiveDayDetailsHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeLiveDayHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取装修直播详情错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeLiveDetailHtml(data){
		var obj = data || {};
		var id = obj.id;
		var name = obj.name || "";
		var village = obj.village || "";
		var area = obj.area || "";
		var house = obj.house || "";
		var startDate = obj.startDate || "";

		var titleStr = village + " / " + area + '㎡ / ' + house;
		$("#liveTitle").html(titleStr);
		$("#liveDistrict").html(village);
		$("#liveStart").html(startDate);
	}

	function changeLiveDayHtml(data){
		var obj = data || {};
		var lives = obj.result || [];
		var html = [];
		for(var i = 0,len = lives.length; i < len; i++){
			var d = lives[i];
			var day = d.day || "";
			var date = d.date || "";
			var title = d.title || "";
			var tips = d.tips || "";
			date = date.substring(0,10);
			var year = "";
			var month = "";
			var myday = "";
			if(date.length >= 10){
				var arr = date.split("-");
				year = arr[0];
				month = arr[1] - 0;
				myday = arr[2];
			}
			html.push('<div class="cd-timeline-block">');
            html.push('<div class="cd-timeline-img">'+day+'</div>');
            html.push('<div class="cd-timeline-content">');
            html.push('<h2>'+ title +'</h2>');
            html.push('<p>温馨提示：'+ tips +'</p>');
            html.push('<p class="tit">施工人员</p>');
            html.push('<p class="t-wrap">');
            var workers = d.workers || [];
			var typeid = {"404040e64dfbe06b014dfbe07c7c0001":"综合工","404040e64dfbe06b014dfbe07caa0002":"水工","404040e64dfbe06b014dfbe07cca0003":"电工"};
			for(var j = 0; j < workers.length; j++){
				var name = workers[j].name || "";
				var tid = workers[j].type || "";
				if(tid !== ""){
					type = typeid[tid.id] || "";
				}
				html.push(type + '：' + name + '  ');
			}
			html.push('</p>');
			html.push('<p class="tit">施工图片</p>')
			html.push('<ul data-am-widget="gallery" class="live-pic am-gallery am-avg-sm-2 am-gallery-default" data-am-gallery="{ pureview: true }">');
			var pics = d.pics || [];
			for(var k = 0; k < pics.length; k++){
				var path = pics[k].url || "http://s.amazeui.org/media/i/demos/bing-1.jpg";
				html.push('<li><div class="am-gallery-item">');
				html.push('<img src="'+ path +'" /></div></li>');
			}
            html.push('</ul>');
            html.push('<p class="tit">微信互动</p>');
			html.push('<ul data-am-widget="gallery" class="live-pic am-gallery am-avg-sm-2 am-gallery-default" data-am-gallery="{ pureview: true }">');
            var interactPics = d.interactPics || [];
			for(var n = 0; n < interactPics.length; n++){
				var path = interactPics[n].url || "http://s.amazeui.org/media/i/demos/bing-1.jpg";
				html.push('<li><div class="am-gallery-item">');
				html.push('<img src="'+ path +'" /></div></li>');
			}
            html.push('</ul>');
            html.push('<span class="cd-date">'+ year+'-'+month+'-'+myday +'</span>');
            html.push('</div>');
		}

		$("#cd-timeline").html(html.join(''));
	}

});