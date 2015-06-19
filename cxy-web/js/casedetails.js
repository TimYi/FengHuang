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
		/*
		套餐名称   packageName 499套餐                           string
		风格      stype           欧式                      string
		空间      space           书房、厨房                 string
		户型      houseType       二居室                       string
		价格      price           51888                       double
		面积      area            88.85                           double
		描述      description     三居80㎡简装499套餐简欧案例                  string
		案例编号    code            AL10230405（手动输入）                    string
		TAG     tags            公寓 卧室 简欧（like匹配）                string
		图片        mainPic                 图片{id,url}
		*/
		var obj = data || {};
		var id = g.caseId;
		var houseType = obj.houseType || "";
		var area = obj.area || "";
		var packageName = obj.packageName || "";
		var stype = obj.stype || "";
		var mainPic = obj.mainPic || "http://www.talmd.cn/upload/upfiles/part/201401/113890851569826.jpg_870.jpg";
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
		$("#casetitle").html(title);

		var html = [];
		html.push('<div  id="pic" class="col-md-8">');
		html.push('<img src="' + mainPic + '">');
		html.push('</div>');
		html.push('<div class="case-right col-md-4" style="height:540px">');
		html.push('<ul>');
		html.push('<li style="font-size:24px;">' + title + '</li>');
		html.push('<li style="color:#999;padding-top:20px">案例编号：' + code + '</li>');
		html.push('<li>' + stype + '</li>');
		html.push('<li style="color:#999">定制价</li>');
		html.push('<li style="color:#b9090e;font-size:24px;font-weight:800;">￥' + price + '<span style="font-size:16px;">.<span style="text-decoration:underline">00</span> 元</span></li>');
		html.push('<hr>');
		html.push('<li><span style="color:#999">TAG：</span>' + tagstr + '</li>');
		html.push('<li>分享到：</li>');
		html.push('<hr>');
		html.push('<li>');
		html.push('<div style="float:left;height:45px;width:130px;background:orange;-moz-border-radius:7px;-webkit-border-radius:7px;border-radius:7px;">');
		html.push('<a href="miaosha.html?id=' + id + '">');
		html.push('<div style="text-align:center;line-height:45px;font-size:16px;color:#000;">定制套餐</div>');
		html.push('</a>');
		html.push('</div>');
		html.push('<div style="margin:0 0 20px 20px;float:left;height:45px;width:130px;background:#b9090e;-moz-border-radius:7px;-webkit-border-radius:7px;border-radius:7px;">');
		html.push('<a href="#">');
		html.push('<div style="text-align:center;line-height:45px;font-size:16px;color:white;">收藏</div>');
		html.push('</a>');
		html.push('</div>');
		html.push('<div style="clear:both"></div>');
		html.push('</li>');
		html.push('<li style="color:#999">定制说明</li>');
		html.push('<li>了解更多</li>');
		html.push('</ul>');
		html.push('</div>');

		$("#casedetails").html(html.join(''));
	}

	function getCaseComment(){
		var condi = {};
		condi.sourceid = g.caseId;

		//sendGetCaseCommentHttp(condi);
	}

	function sendGetCaseCommentHttp(condi){
		var url = Base.commentsUrl;
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
					//changeCaseDetailHtml(data.result);
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

	function issueComment(){

		$("#name")
	}

});











