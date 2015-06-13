/**
 * file:我的预约详情
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
	g.token = Utils.getQueryString("token");
	g.page = Utils.getQueryString("p") - 0;
	g.id = Utils.getQueryString("id") ;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});

	$("#backbtn").bind("click",back);
	$("#deletebtn").bind("click",deleteItem);

	getListInfo();

	function back(){
		location.href = "c_sub.html?token=" + g.token + "&p=6";
	}

	function deleteItem(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		sendDeleteListInfoHttp(condi);
	}

	function getListInfo(){
		var condi = {};
		condi.token = g.token;
		condi.id = g.id;

		sendGetListInfoHttp(condi);
	}

	function sendGetListInfoHttp(condi){
		var url = Base.appointUrl + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeListInfoHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("获取我的预约详情错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendDeleteListInfoHttp(condi){
		var url = Base.messageUrl + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					//changeListInfoHtml(data.result);
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的预约错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeListInfoHtml(data){
		var obj = data.result || [];
		var html = [];
		html.push('<h4><!--499家装套餐--></h4>');
		html.push('<span style="color:#999;font-size:13px">状态：<!--客服人员已确认--></span>');
		html.push('<span style="color:#999;margin-left:20px;font-size:13px">时间：<!--2015-06-02 10:00--></span>');
		html.push('<hr/>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		html.push('<li>套餐类型：<!--499家装套餐--></li>');
		html.push('<li>订单编号：<!--TC0129300123--></li>');
		html.push('<li>所在城市：<!--北京--></li>');
		html.push('<li>详细地址：<!--海淀区上地七街--></li>');
		html.push('</ul>');
		html.push('</div>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		html.push('<li>预约时间：<!--2015-06-10 13:25:00--></li>');
		html.push('<li>有效期限：<!--2015-06-30 00:00:00--></li>');
		html.push('<li>联系电话：<!--13698988888--></li>');
		html.push('</ul>');
		html.push('</div>');

		$("#infodiv").html(html.join(''));
	}
});















