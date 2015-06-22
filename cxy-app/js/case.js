/**
 * file:案例馆
 * author:chenxy
 * date:2015-05-18
*/


//页面初始化
$(function(){

	function condiSelectChange(evt){
		var style = $("#styleselect").val();
		var setup = $("#setupselect").val();
		var size = $("#sizeselect").val();
		var color = $("#colorselect").val();

		var styleArr = {"a":1,"b":2,"c":3,"d":4,"e":5,"f":6,"g":7,"h":8};
		var setupArr = {"a":1,"b":2,"c":3,"d":4,"e":5,"f":6,"g":7,"h":8};
		var sizeArr = {"a":1,"b":2,"c":3,"d":4,"e":5,"f":6,"g":7,"h":8};
		var colorArr = {"a":1,"b":2,"c":3,"d":4,"e":5,"f":6,"g":7,"h":8};

		console.log(styleArr[style],setupArr[setup],sizeArr[size],colorArr[color]);
	}


	$("#styleselect").bind("change",condiSelectChange);
	$("#setupselect").bind("change",condiSelectChange);
	$("#sizeselect").bind("change",condiSelectChange);
	$("#colorselect").bind("change",condiSelectChange);
});