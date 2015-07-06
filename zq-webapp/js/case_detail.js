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
	g.token = Utils.offLineStore.get("token",false);
	g.caseId = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 10;
	g.httpTip = new Utils.httpTip({});
	g.listdata = [];

	//验证登录状态
	g.loginStatus = Utils.getUserInfo();

	$("#issuebtn").bind("click",issueComment);

	getCaseDetails();
	getCaseComment();

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
					changeCaseDetailHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取装修案例详情错误:" + msg);
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeCaseDetailHtml(data){
		var obj = data || {};
		var id = g.caseId;
		var houseType = obj.houseType || "";
		var area = obj.area || "";
		var packageName = obj.packageName || "";
		var stype = obj.stype || "";
		var mainPic = obj.mainPic || "";
		if(mainPic !== ""){
			mainPic = mainPic.url || "http://www.talmd.cn/upload/upfiles/part/201401/113890851569826.jpg_870.jpg";
		}
		var code = obj.code || "";
		var price = obj.price || "";
		var tags = obj.tags || [];
		var tagstr = "";
		for(var i = 0,len = tags.length; i < len; i++){
			var n = tags[i].name || "";
			tagstr = tagstr + n + "，";
		}
		if(tagstr.length > 0){
			tagstr = tagstr.substring(0,tagstr.length - 1);
		}

		var title = houseType + area + "㎡" + packageName + stype + "案例";
		$("#mainPic").attr("src",mainPic);
		$("#caseName").html(title);
		$("#casePrice").html(price);
		$("#caseCode").html(code);
		$("#caseTags").html(tagstr);

		$("#buynow").attr("href","buy_ok.html?id=" + id);
		$("#addCollect").attr("href","javascript:addCollect('"+id+"','CASE')");
	}

	function getCaseComment(){
		var condi = {};
		condi.sourceid = g.caseId;
		condi.currentPage = 1;
		condi.paseSize = 5;
		sendGetCaseCommentHttp(condi);
	}

	function sendGetCaseCommentHttp(condi){
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
				console.log("sendGetCaseCommentHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					changeCommentsHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取装修案例评论错误:" + msg);
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
		for(var i = 0; i < obj.length; i++){
			var column = obj[i].column || "";
			var createTime = obj[i].createTime || "";
			var content = obj[i].content || "";
			var realName = obj[i].user.realName || "";
			html.push('<li><p><i class="am-icon-user"></i>');
			html.push('<b>'+ realName +'</b></p>');
            html.push('<p>'+ content +'</p>');
            html.push('<p class="time">'+ createTime +'</p></li>');
		}
		$("#commentList").html(html.join(''));
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
		condi.sourceid = g.caseId;
		condi.column = "栏目标题";
		condi.type = "CASE";
		condi.token = g.token;
		condi.replyid = "";
		condi.url = location.href;
		condi.content = $("#message").val() || "";

		if(g.loginStatus){
			if(condi.content !==""){
				sendAddCaseCommentHttp(condi);
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

	function sendAddCaseCommentHttp(condi){
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
				console.log("sendAddCaseCommentHttp",data);
				var status = data.status || "";
				if(status == "OK"){
					alert("评论发表成功");
					$("#message").val("");
					getCaseComment();
				}else{
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

	function addCollect(sourceid,type){
		var text = $("#addCollect div").text();
		if(g.loginStatus && text == "加入收藏"){
			var url = Base.collectUrl;
			var condi = {};
			condi.token = g.token;
			condi.sourceid = sourceid;
			condi.type = type;
			g.httpTip.show();
			$.ajax({
				url:url,
				data:condi,
				type:"POST",
				dataType:"json",
				context:this,
				global:false,
				success: function(data){
					console.log("addCollect",data);
					var status = data.status || "";
					if(status == "OK"){
						$("#addCollect div").html("已收藏");
					}
					else{
						var msg = data.error || "";
						alert("添加收藏错误:" + msg);
					}
					g.httpTip.hide();
				},
				error:function(data){
					g.httpTip.hide();
				}
			});
		}
		else{
			alert("请先登录");
			location.href = "login.html";
		}
	}

	window.addCollect = addCollect;
});