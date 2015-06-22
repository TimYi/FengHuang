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
	g.httpTip = new Utils.httpTip({});

	$("#countbtn").bind("click",countBtnUp);

	function countBtnUp(){
		var money = $("#doc-vld-name-2").val() - 0 || 0;
		var time = $("#doc-select-1")[0].selectedIndex - 0 || 0;
		if(money !== 0 && time !== 0){
			if(money <= 200000){
				location.href = "s_loan_r.html?m=" + money + "&tn=" + time;
			}
			else{
				Utils.alert("最多可贷20W");
			}
		}
		else{
			if(money === 0){
				Utils.alert("请输入贷款金额和");
			}
			else{
				Utils.alert("请选择贷款周期");
			}
		}

	}
});