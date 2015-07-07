$(function(){
	var g = {};
	g.phone = "";
	g.imgCodeId = "";
	g.sendCode = false;
	g.sendTime = 60;
	g.username = Base.userName;
	g.token = Utils.getQueryString("token");
	g.houseId = Utils.getQueryString("id");
	g.page = Utils.getQueryString("p") - 0;
	g.totalPage = 1;
	g.currentPage = 1;
	g.paseSize = 20;
	g.listdata = [];
	g.httpTip = new Utils.httpTip({});
	g.updateId = "";


	var loginStatus = Utils.getUserInfo();
	if(!loginStatus){
		location.replace("login.html");
		return false;
	}else if(g.houseId){
		getHomeDetail();
		$("#updatebtn").bind("click",updateHome);
	}else{
		$("#updatebtn").bind("click",addHome);
	}


	getProv();
	getCategory("houseType","house");
	getCategory("decorateType","decorate");
	function getProvCity(){
		var id = $(this).val();
		getCity(id,1);
	}
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

	function getHomeDetail(){
		var condi ={};
		var url = Base.house + "/" + g.houseId;
		condi.token = g.token;
		condi.id=g.houseId;
		$.ajax({
			url:url,
			data:condi,
			type:"GET",
			dataType:"json",
			context:this,
			global:false,
			success:function(data){
				console.log(data);
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					var obj = data.result;
					$("#provId").val(obj.city.id);
					$("#districtName").val(obj.districtName);
					$("#houseType").val(obj.houseType.id);
					$("#decorateType").val(obj.decorateType.id);
					$("#area").val(obj.area);
					$("#decorateBudget").val(obj.decorateBudget);
					$("#launchDate").val(obj.launchDate.substr(0,10));
					$("#decorateDate").val(obj.decorateDate.substr(0,10));
				}
				else{
					var msg = data.error || "";
					alert("更新房屋信息错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
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
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					alert("添加成功");
					location.href="u_house.html?token="+g.token+"&p=2";
				}
				else{
					var msg = data.error || "";
					alert("添加房屋信息错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

	function sendUpdateHomeHttp(condi){
		g.httpTip.show();
		var url = Base.house + "/" + g.houseId;
		$.ajax({
			url:url,
			data:condi,
			type:"POST",
			dataType:"json",
			context:this,
			global:false,
			success: function(data){
				g.httpTip.hide();
				var status = data.status || "";
				if(status == "OK"){
					location.href="u_house.html?token="+g.token+"&p=2";
				}
				else{
					var msg = data.error || "";
					alert("更新房屋信息错误:" + msg);
					if(msg == "您需要登录"){
						location.href = "login.html";
					}
				}
			},
			error:function(data){
				g.httpTip.hide();
			}
		});
	}

});


