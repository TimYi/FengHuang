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
	g.houseId = Utils.getQueryString("id") || "";
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.data = {};
	g.httpTip = new Utils.httpTip({});


	$("#districtNamebtn").bind("click",changePage);
	$("#areabtn").bind("click",changePage);
	$("#decorateBudgetbtn").bind("click",changePage);
	//$("#districtNamebtn").bind("click",changePage);
	//$("#districtNamebtn").bind("click",changePage);
	//$("#updatebtn").bind("click",updateHome);

	getHomeDetails();
	function getHomeDetails(){
		if(g.houseId !== ""){
			var condi = {};
			condi.token = g.token;
			condi.id = g.houseId;
			sendGetHomeDetailsHttp(condi);
		}
		else{
			Utils.alert("参数错误");
		}
	}

	function sendGetHomeDetailsHttp(condi){
		g.httpTip.show();
		var url = Base.house + "/" + condi.id;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("sendGetHomeDetailsHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					changeHomeDetailsHtml(data.result);
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
	function changeHomeDetailsHtml(data){
		var obj = data || {};
		g.data = obj;

		var districtName = obj.districtName || "";
		var area = obj.area || "";
		var decorateBudget = obj.decorateBudget || 0;
		var launchDate = obj.launchDate || "";
		var decorateDate = obj.decorateDate || "";

		$("#districtName").html(districtName);
		$("#area").html(area);
		$("#decorateBudget").html(decorateBudget);
		$("#launchDate").html(launchDate.substring(0,10));
		$("#decorateDate").html(decorateDate.substring(0,10));
	}


	function changePage(){
		var id = this.id;
		var condi = g.data;
		var url = "";
		switch(id){
			case "districtNamebtn":
				condi.k = "districtName";
				condi.v = g.data.districtName;
				url = "u_house/u_house_name.html";
			break;
			case "areabtn":
				condi.k = "area";
				condi.v = g.data.area;
				url = "u_house/u_house_mianji.html";
			break;
			case "decorateBudgetbtn":
				condi.k = "decorateBudget";
				condi.v = g.data.decorateBudget;
				url = "u_house/u_house_zhuangxiuyusuan.html";
			break;
			case "phonebtn":
				condi.k = "mobile";
				condi.v = g.data.mobile;
				url = "u_info/u_info_phone.html";
			break;
			case "emailbtn":
				condi.k = "email";
				condi.v = g.data.email;
				url = "u_info/u_info_email.html";
			break;
			case "messagebtn":
				condi.k = "intro";
				condi.v = g.data.intro;
				url = "u_info/u_info_jianjie.html";
			break;
			case "professionbtn":
				condi.k = "profession";
				condi.v = g.data.profession;
				url = "u_info/u_info_profession.html";
			break;
		}
		Utils.offLineStore.set("houseinfo_update",JSON.stringify(condi),false);
		location.href = url;
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















