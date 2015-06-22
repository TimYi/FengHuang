/**
 * file:贷款计算器
 * author:chenxy
 * date:2015-05-23
*/

//页面初始化
$(function(){
	var g = {};
	g.codeId = "";
	g.tout = null;
	g.money = Utils.getQueryString("m") - 0;
	g.time = Utils.getQueryString("tn") - 0;
	g.httpTip = new Utils.httpTip({});

	countMoney();

	function countMoney(){
		var rate = [0,0.028634,0.050463,0.095027,0.140796,0.187768,0.285291,0.387527,0.494383];
		var month = [0,3,6,12,18,24,36,48,60];
		var money = g.money || 0;
		var time = g.time;
		if(money > 0){
			var MonthReturn = (money + (money * rate[time])) / month[time];
			var Amount =  month[time] * MonthReturn;

			$("#monthmoney").html(MonthReturn.toFixed(2));
			var html = [];
			$("#money").html(money);
			$("#time").html(month[time]);
			$("#allmoney").html(Amount.toFixed(2));
			$("#monthmoney").html(MonthReturn.toFixed(2));
		}
		else{
			Utils.alert("请输入贷款金额");
		}

	}
});