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
	g.updateId = "";

	$("#addbtn").bind("click",addHome);
	$("#updatehousebtn").bind("click",updateHome);

	getHomeList();

	//$("#provId").bind("change",getProvCity);
	//$("#provId2").bind("change",getProvCity2);

	getProv();
	getCategory("houseType","house");
	getCategory("decorateType","decorate");
	function getProvCity(){
		var id = $(this).val();
		getCity(id,1);
	}
	function getProvCity2(){
		var id = $(this).val();
		getCity(id,2);
	}
	//获取字典
	function getCategory(id,type){
		var url = Base.categoryUrl + "/" + type;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getCategory",data);
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml(id,data.result || []);
				}
				else{
					Utils.alert("获取字典数据错误");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function getProv(){
		//var url = Base.cityUrl + "/PROV";
		var url = Base.cityUrl + "/CITY";
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getProv",data);
				var status = data.status || "";
				if(status == "OK"){
					changeSelectHtml("provId",data.result || []);
					//var id = data.result[0].id;
					//getCity(id,0);
				}
				else{
					Utils.alert("城市获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function getCity(id,b){
		var url = Base.subareasUrl + "/" + id;
		g.httpTip.show();
		$.ajax({
			url:url,
			data:{},
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				console.log("getCity",data);
				var status = data.status || "";
				if(status == "OK"){
					switch(b){
						case 0:
							changeSelectHtml("cityId",data.result || []);
							changeSelectHtml("cityId2",data.result || []);
						break;
						case 1:
							changeSelectHtml("cityId",data.result || []);
						break;
						case 2:
							changeSelectHtml("cityId2",data.result || []);
						break;
					}
				}
				else{
					Utils.alert("城市获取失败");
				}
				g.httpTip.hide();
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}
	function changeSelectHtml(domid,data){
		var option = [];
		for(var i = 0,len = data.length; i < len; i++){
			var id = data[i].id || "";
			var name = data[i].name || "";
			option.push('<option value="' + id + '"' + ( i == 0 ? "selected" : "") + '>' + name + '</option>');
		}
		$("#" + domid).html(option.join(''));
	}


	function addHome(){
		var condi = {};
		condi.token = g.token;
		condi.cityId = $("#provId").val();//$("#cityId").val();
		condi.districtName = $("#districtName").val() || "";
		condi.houseTypeId = $("#houseType").val();
		condi.decorateTypeId = $("#decorateType").val();
		condi.area = $("#area").val() || "";
		condi.decorateBudget = $("#decorateBudget").val() || "";
		condi.launchDate = $("#launchDate").val() || "";
		condi.decorateDate = $("#decorateDate").val() || "";

		if(condi.districtName !== ""){
			if(condi.area !== "" ){
				if(condi.decorateBudget !== ""){
					if(condi.launchDate !== ""){
						if(condi.decorateDate !== ""){
							//console.log(condi);
							sendAddHomeHttp(condi);
						}
						else{
							Utils.alert("请输入装修时间");
							$("#decorateDate").focus();
						}
					}
					else{
						Utils.alert("请输入交房时间");
						$("#launchDate").focus();
					}
				}
				else{
					Utils.alert("请输入装修预算");
					$("#decorateBudget").focus();
				}
			}
			else{
				Utils.alert("请输入房屋面积");
				$("#area").focus();
			}
		}
		else{
			Utils.alert("请输入小区名称");
			$("#districtName").focus();
		}
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
			var city = obj.city || "";
			var districtName = obj.districtName || "";
			var houseType = obj.houseType || "";
			var decorateType = obj.decorateType || "";
			var area = obj.area;
			var decorateBudget = obj.decorateBudget || "";
			var launchDate = obj.launchDate || "";
			var decorateDate = obj.decorateDate || "";

			if(city !== ""){
				var cid = city.id;
				$("#provId").val(cid);
			}
			$("#districtName").val(districtName);
			if(houseType !== ""){
				var hid = houseType.id;
				$("#houseType").val(hid);
			}
			if(decorateType !== ""){
				var did = decorateType.id;
				$("#decorateType").val(did);
			}
			$("#area").val(area);
			$("#decorateBudget").val(decorateBudget);
			$("#launchDate").val(launchDate.substring(0,10));
			$("#decorateDate").val(decorateDate.substring(0,10));


			g.updateId = obj.id;
			$("#updatehousebtn").show();
			$("#addbtn").hide();
		}
		else{
			if (confirm("确认要删除?")) {
				//删除
				var id = obj.id;
				var condi = {};
				condi.token = g.token;
				condi.id = id;
				//console.log(condi);
				sendDeleteHomeHttp(condi);
			}
		}
	}

	function updateHome(){
		var condi = {};
		condi.token = g.token;
		condi.cityId = $("#provId").val();//$("#cityId").val();
		condi.districtName = $("#districtName").val() || "";
		condi.houseTypeId = $("#houseType").val();
		condi.decorateTypeId = $("#decorateType").val();
		condi.area = $("#area").val() || "";
		condi.decorateBudget = $("#decorateBudget").val() || "";
		condi.launchDate = $("#launchDate").val() || "";
		condi.decorateDate = $("#decorateDate").val() || "";

		if(condi.districtName !== ""){
			if(condi.area !== "" ){
				if(condi.decorateBudget !== ""){
					if(condi.launchDate !== ""){
						if(condi.decorateDate !== ""){
							//console.log(condi);
							sendUpdateHomeHttp(condi);
						}
						else{
							Utils.alert("请输入装修时间");
							$("#decorateDate").focus();
						}
					}
					else{
						Utils.alert("请输入交房时间");
						$("#launchDate").focus();
					}
				}
				else{
					Utils.alert("请输入装修预算");
					$("#decorateBudget").focus();
				}
			}
			else{
				Utils.alert("请输入房屋面积");
				$("#area").focus();
			}
		}
		else{
			Utils.alert("请输入小区名称");
			$("#districtName").focus();
		}
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
				//console.log("sendAddHomeHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					getHomeList();
					Utils.alert("添加成功");
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
				//console.log("sendGetHomeListHttp",data);
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

	function sendDeleteHomeHttp(condi){
		g.httpTip.show();
		var url = Base.house + "/" + condi.id;
		$.ajax({
			url:url,
			headers:{"fhzj_auth_token":condi.token},
			data:condi,
			type:"DELETE",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				//console.log("sendDeleteHomeHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					getHomeList();
					alert("删除成功");
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
		var url = Base.house + "/" + g.updateId;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				//console.log("sendUpdateHomeHttp",data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					getHomeList();
					Utils.alert("修改成功");
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
				var id = obj[i].id || "";
				var districtName = obj[i].districtName || "";
				var area = obj[i].area || "";
				var houseType = obj[i].houseType || "";
				if(houseType !== ""){
					houseType = houseType.name;
				}

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















