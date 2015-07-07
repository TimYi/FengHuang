/**
 * file:我的评论详情
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
	g.id = Utils.getQueryString("id");
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.httpTip = new Utils.httpTip({});

	$("#backbtn").bind("click",back);
	//$("#deletebtn").bind("click",deleteItem);

	getListInfo();

	function back(){
		history.go(-1);
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
		var url = Base.order + "/" + condi.id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
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
					Utils.alert("获取我的订单信息错误:" + msg);
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
			headers:{"fhzj_auth_token":condi.token},
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetListInfoHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					Utils.alert("删除留言成功");
					back();
				}
				else{
					var msg = data.error || "";
					Utils.alert("删除我的留言错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeListInfoHtml(obj){
		var html = [];
		var name = obj.name || "";
		var code = obj.code || "";
		var payTime = obj.payTime || "";
		var status = obj.status || "";
		status = status == "Procrssing" ? "定金已支付":"定金未支付";
		html.push('<h4>' + name + '</h4>');
		html.push('<span style="color:#999;font-size:13px">状态：' + status + '</span>');
		html.push('<span style="color:#999;margin-left:20px;font-size:13px">时间:' + payTime + '</span>');
		html.push('<hr/>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		html.push('<li>套餐类型：' + name + '</li>');
		html.push('<li>订单编号：' + code + '</li>');
		//html.push('<li>备注信息：</li>');
		html.push('</ul>');
		html.push('</div>');

		html.push('<div class="col-md-6">');
		html.push('<ul class="sub_li">');
		//html.push('<li>有效期限：</li>');
		html.push('<li>下单时间：' + payTime+ '</li>');
		html.push('</ul>');
		html.push('</div>');

		$("#orderinfo").html(html.join(''));
		$("#orderinfo").show();
	}

});








