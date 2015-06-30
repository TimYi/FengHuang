/**
 * file:直播详情
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
	g.liveId = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 2;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	//验证登录状态
	g.loginStatus = Utils.getUserInfo();

	$("#issuebtn").bind("click",issueComment);

	getLiveDetails();
	getLiveComment();

	function getLiveDetails(){
		var condi = {};
		condi.id = g.liveId;

		sendGetLiveDetailsHttp(condi);
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


	function changeLiveDetailHtml(data){
		var obj = data || {};
		var id = obj.id;
		var name = obj.name || "";
		var village = obj.village || "";
		var area = obj.area || "";
		var house = obj.house || "";
		var startDate = obj.startDate || "";

		var title = [];
		title.push('<h2>'  + name + '</h2>');
		title.push('<p class="lead">'+ village +'／' + area + '㎡／' + house + '&nbsp;&nbsp;&nbsp;&nbsp;开工时间：' + startDate.substring(0,10) + '</p>');
		$("#centertitle").html(title.join(''));


		var lives = obj.lives || [];
		var html = [];
		for(var i = 0,len = lives.length; i < len; i++){
			var d = lives[i];
			var day = d.day || "";
			var date = d.date || "";
			var title = d.title || "";
			var tips = d.tips || "";
			date = date.substring(0,10);
			if(data.length >= 10){
				var year = data.spilt("_");
			}
			title.push('<div class="blog-item">');
			title.push('<div class="row">');
			title.push('<div class="col-xs-12 col-sm-2 text-center" style="width:13%;padding:0 15px 0 15px">');
			title.push('<div class="entry-meta">');
			title.push('<span id="publish_date_1">第 ' + day + ' 天</span>');
			title.push('<span class="u_days">');
			title.push('<span style="font-size:70px;line-height:70px">' + year[2] + '</span><br>');
			title.push('<span style="font-size:15px;line-height:40px;font-weight:normal;">' +  year[0] + '年' +  year[1] + '月</span>');//2015年 六月
			title.push('</span>');
			title.push('</div>');
			title.push('</div>');

			title.push('<div class="col-xs-12 col-sm-10 blog-content">');
			title.push('<!--a href="#"><img class="img-responsive img-blog" src="images/blog/blog1.jpg" width="100%" alt="" /></a-->');
			title.push('<h2>' + title + '</h2>');
			title.push('<h3>温馨提示：' + tips + '</h3>');

			title.push('<a class="btn btn-primary readmore" href="blog-item.html">施工人员</a>');
			title.push('<div style="clear:both;padding:10px 0"></div>');
			var workers = d.workers || [];
			var typeid = {"404040e64dfbe06b014dfbe07c7c0001":"综合工","404040e64dfbe06b014dfbe07caa0002":"水工","404040e64dfbe06b014dfbe07cca0003":"电工"};
			for(var j = 0; j < workers.length; j++){
				var name = workers[j].name || "";
				var tid = workers[j].type || "";
				if(tid !== ""){
					type = typeid[tid.id] || "";
				}
				title.push('<span>' + type + '：<b>' + name + '</b>');
				//木工：<b>周进</b>　　水电工：<b>张昊、谭双合</b></span>
			}
			title.push('<div style="clear:both;padding:10px 0"></div>');
			title.push('<a class="btn btn-primary readmore" href="blog-item.html">施工图片</a>');
			title.push('<div style="clear:both;padding:10px 0"></div>');
			title.push('<div class="row">');

			var pics = d.pics || [];
			for(var k = 0; k < pics.length; k++){
				var path = pics[k].url || "images/team/team_9.jpg";
				title.push('<div class="col-xs-12 col-sm-4 col-md-3">');
				title.push('<div class="recent-work-wrap">');
				title.push('<img class="img-responsive" src="" alt="' + path + '">');
				title.push('</div>');
				title.push('</div>');
			}
			//~ <div class="col-xs-12 col-sm-4 col-md-3">
			//~ <div class="recent-work-wrap">
			//~ <img class="img-responsive" src="images/team/team_7.jpg" alt="">
			//~ </div>
			//~ </div>
			title.push('</div><!--/.row-->');

			title.push('<div style="clear:both;padding:10px 0"></div>');
			title.push('<a class="btn btn-primary readmore" href="blog-item.html">微信互动</a>');
			title.push('<div style="clear:both;padding:10px 0"></div>');
			title.push('<div class="row">');
			//~ <div class="col-xs-12 col-sm-4 col-md-3">
			//~ <div class="recent-work-wrap">
			//~ <img class="img-responsive" src="images/team/team_10.jpg" alt="">
			//~ </div>
			//~ </div>
			//~ <div class="col-xs-12 col-sm-4 col-md-3">
			//~ <div class="recent-work-wrap">
			//~ <img class="img-responsive" src="images/team/team_3.jpg" alt="">
			//~ </div>
			//~ </div>
			//~ </div><!--/.row-->
			title.push('<hr/>');
			title.push('<span>更新于 1分钟 之前</span>');
			title.push('</div>');
			title.push('</div>');
			title.push('</div>');
		}

		$("#livedetail").html(html.join(''));
		$("#livedetail").show();
	}

	function getLiveComment(){
		var condi = {};
		condi.sourceid = g.liveId;
		condi.currentPage = 1;
		condi.paseSize = 5;
		sendGetLiveCommentHttp(condi);
	}

	function sendGetLiveCommentHttp(condi){
		var url = Base.comments;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetLiveCommentHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeCommentsHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取直播评论错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeCommentsHtml(data){
		var obj = data.result || [];

		var totalCount = data.totalCount;
		var html = [];
		html.push('<h1 id="comments_title">' + totalCount + ' 条评论</h1>');
		for(var i = 0; i < obj.length; i++){
			var column = obj[i].column || "";
			var createTime = obj[i].createTime || "";
			var content = obj[i].content || "";
			var avatar = obj[i].user.avatar || "images/blog/avatar3.png";
			var realName = obj[i].user.realName || "";
			html.push('<div class="media comment_section">');
			html.push('<div class="pull-left post_comments">');
			html.push('<a href="#"><img src="' + avatar + '" class="img-circle" alt="" /></a>');
			html.push('</div>');
			html.push('<div class="media-body post_reply_comments">');
			html.push('<h3>' + realName + '</h3>');
			html.push('<h4>' + createTime + '</h4>');
			html.push('<p>' + content+ '</p>');
			//html.push('<a href="#">回复</a>');
			html.push('</div>');
			html.push('</div>');
		}

		if(html.length > 1){
			$("#commentslist").html(html.join(''));
			$("#commentslist").show();
		}
	}

	function issueComment(){
		/*
		column:栏目标题
		type:资源类型，为枚举值：ARTICAL,PACKAGE,CASE;
		token:用户凭据
		replyid:如果是回复某条评论，传被评论的id，否则不传
		url:当前地址url，可选
		content:回复的内容
		*/
		var condi = {};
		condi.sourceid = g.liveId;
		condi.column = "栏目标题";
		condi.type = "CASE";
		condi.token = g.token;
		condi.replyid = "";
		condi.url = location.href;
		condi.content = $("#message").val() || "";

		if(g.loginStatus){
			if(condi.content !==""){
				sendAddLiveCommentHttp(condi);
			}
			else{
				Utils.alert("输入评论内容");
				$("#message").focus();
			}
		}
		else{
			alert("请先登录");
			location.href = "login.html";
		}
	}

	function sendAddLiveCommentHttp(condi){
		var url = Base.commentUrl;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendAddLiveCommentHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					alert("评论发表成功");
					$("#message").val("");
					getLiveComment();
				}
				else{
					var msg = data.error || "";
					alert("添加案例评论错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

});











