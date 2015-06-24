/**
 * file:房屋信息
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
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});


	$("#addbtn").bind("click",addHome);
	$("#updatebtn").bind("click",updateHome);

	getHomeList();
	function getHomeList(){
		var condi = {};
		condi.token = g.token;
		sendGetHomeListHttp(condi);
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
					alert("获取房屋信息错误:" + msg);
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

			for(var i = 0,len = obj.length; i < len; i++){
				var d = obj[i];
				var id = d.id || "";
				var districtName = d.districtName || "";
				var area = d.area || "";
				var houseType = d.houseType || "";

				html.push('<ul class="am-avg-sm-2" style="padding:5px 0 5px 0;box-shadow: inset 0 -1px 1px -1px #aaa;background:#fff;">');
				html.push('<li style="padding:0;text-align:left;width:90%">');
				html.push('<div class="am-dropdown" data-am-dropdown style="width:100%;height:50px;float:left">');
				html.push('<a href="u_house_item.html?id=' + id + '">');
				html.push('<div style="line-height:50px;padding-left:20px;">');
				html.push('<ul class="uhouse">');
				html.push('<li class="ubig">' + districtName + '</li>');
				html.push('<li class="usmall">' + area + '㎡ / ' + houseType + '</li>');
				html.push('</ul>');
				html.push('</div>');
				html.push('</a>');
				html.push('</div>');
				html.push('</li>');
				html.push('<li style="padding:0;text-align:right;width:10%">');
				html.push('<div class="am-dropdown" data-am-dropdown style="height:50px">');
				html.push('<a href="u_house_item.html?id=' + id + '">');
				html.push('<div style="line-height:50px;padding-right:10px">');
				html.push('<i class="am-icon-angle-right" style="color:#ccc;padding-left:10px;font-size:20px"></i>');
				html.push('</div>');
				html.push('</a>');
				html.push('</div>');
				html.push('</li>');
				html.push('</ul>');
			}

			$("#houselist").html(html.join(''));
			//$("#houselist  a").bind("click",operateBtn);
			//var totalpages = data.totalPages - 0;
			//g.totalPage = totalpages;
			//changePageHtml(totalpages);
		}
	}








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

		console.log(condi);
		sendAddHomeHttp(condi);
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




});















