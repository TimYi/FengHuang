/**
 * file:我的留言
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
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});


	$("#addbtn").bind("click",addHome);
	$("#updatebtn").bind("click",updateHome);

	getHomeList();

	function addHome(){
		var condi = {};
		condi.token = g.token;
		condi.cityId = $("#city").val();
		condi.districtName = $("#districtName").val();
		condi.houseTypeId = $("#houseType").val();
		condi.decorateType = $("#decorateType").val();
		condi.area = $("#area").val();
		condi.decorateBudget = $("#decorateBudget").val();
		condi.launchDate = $("#launchDate").val();
		condi.decorateDate = $("#decorateDate").val();

		sendAddHomeHttp(condi);
	}

	function getHomeList(){
		var condi = {};
		condi.token = g.token;
		sendGetHomeListHttp(condi);
	}

	function operateBtn(evt){
		var cname = this.className;
		var index = this.id.split("_")[1];
		var obj = g.listdata[index];
		if(cname === "update"){
			var city = obj.city;
			var districtName = obj.districtName;
			var houseType = obj.houseType;
			var decorateType = obj.decorateType;
			var area = obj.area;
			var decorateBudget = obj.decorateBudget;
			var launchDate = obj.launchDate;
			var decorateDate = obj.decorateDate;

			$("#city").val(city);
			$("#districtName").val(districtName);
			$("#houseType").val(houseType);
			$("#decorateType").val(decorateType);
			$("#area").val(area);
			$("#decorateBudget").val(decorateBudget);
			$("#launchDate").val(launchDate);
			$("#decorateDate").val(decorateDate);

			$("#updatebtn").show();
			$("#addbtn").hide();
		}
		else{
			//删除
			var id = obj.id;
			var condi = {};
			condi.token = g.token;
			condi.id = id;
			console.log(condi);
			sendDeleteHomeHttp(condi);
		}
	}

	function updateHome(){
		var condi = {};
		condi.token = g.token;
		condi.cityId = $("#city").val();
		condi.districtName = $("#districtName").val();
		condi.houseTypeId = $("#houseType").val();
		condi.decorateType = $("#decorateType").val();
		condi.area = $("#area").val();
		condi.decorateBudget = $("#decorateBudget").val();
		condi.launchDate = $("#launchDate").val();
		condi.decorateDate = $("#decorateDate").val();

		sendUpdateHomeHttp(condi);
	}



	function sendAddHomeHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					//changeMessageListHtml(data.result);
				}
				else{
					var msg = data.error || "";
					alert("添加房屋信息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendGetHomeListHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeHomeListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("添加房屋信息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendDeleteHomeHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeHomeListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("删除房屋信息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendUpdateHomeHttp(condi){
		g.httpTip.show();
		var url = Base.houses;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeListHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeHomeListHtml(data);
				}
				else{
					var msg = data.error || "";
					alert("更新房屋信息错误:" + msg);
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function changeHomeListHtml(data){
		var obj = data.result || [];
		if(obj.length > 0){
			g.listdata = obj;
			var html = [];

			html.push('<table class="table u_ct">');
			html.push('<tr class="u_th">');
			html.push('<th>#</th>');
			html.push('<th>小区名称</th>');
			html.push('<th>面积㎡</th>');
			html.push('<th>户型</th>');
			html.push('<th width=100>操作</th>');
			html.push('</tr>');

			for(var i = 0,len = obj.length; i < len; i++){
				var districtName = obj[i].districtName || "";
				var area = obj[i].area || "";
				var houseType = obj[i].houseType || "";
				var id = obj[i].id || "";
				html.push('<tr>');
				html.push('<td >' + (i + 1) + '</td>');
				html.push('<td >' + districtName + '</td>');
				html.push('<td >' + area + '</td>');
				html.push('<td >' + houseType + '</td>');
				html.push('<td><a id="update_' + i + '" href="javascript:void(0);" class="update" >修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a id="delete_' + i + '"href="javascript:void(0);" class="delete" >删除</a></td>');
				html.push('</tr>');
			}
			html.push('</table>');

			$("#hometable").html(html.join(''));
			$("#hometable  a").bind("click",operateBtn);
			//var totalpages = data.totalPages - 0;
			//g.totalPage = totalpages;
			//changePageHtml(totalpages);
		}
	}


});















