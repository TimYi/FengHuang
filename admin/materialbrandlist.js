var dataModel;
var bind = false;//数据绑定标识
var rePage = true;
/*
分页功能变量定义
*/
var total ;//总数据条数
var curPage = 1;//当前页码,初始为1	
/*
*定义参数
*/
var param;
function onload(){
	initParam();
	getDatas();	
}
function initParam(){
	param={
		size : pSize,
		page : curPage
	};
}
function getDatas4page(page){
	
	rePage = false;
	$('.pageList').jqPaginator('option', {
    	currentPage: page
	});
	curPage = page;
	param.page = page;
	getDatas();
}
function getDatas(){
	getData(MATERIAL_BRAND,param,afterGetDatas);
}
function afterGetDatas(data){	
	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}
function bindData(data){
	total = data.totalCount;
	var results = data.result;
	for(var i in results){
		results[i].selected = false;
	}
	if(!bind){
		dataModel = ko.mapping.fromJS(data);	
	}else{
		ko.mapping.fromJS(data, dataModel);
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(MATERIAL_BRAND)+'/'+item.id();
			deleteReq(url,function(dataObj){
				
					friendlyTip(dataObj);
			    	if(dataObj.status === 'OK'){
			    	  	dataModel.result.remove(item);
			    	}
				});
		}
	}
	dataModel.removeSelected = function(){
				
		if(ConfDelAll(0)){
			//todo 删除
			alert(filterSelected(dataModel.result()).length);
		}
	}
	dataModel.modify = function(item){
		window.location.href='materialbrandedit.htm?id='+item.id();
	}
	dataModel.up = function(item){
		var brands = dataModel.result();
		var index = brands.indexOf(item);
		if(index == 0){
			alert("已经是第一个了，无法上移！");
		}
		var temp = brands[index].ordernum();
		brands[index].ordernum(brands[index-1].ordernum());		
		brands[index-1].ordernum(temp);		
		brands.sort(function(left,right){			
			var result = left.ordernum() == right.ordernum() ? 0:(
					left.ordernum() < right.ordernum() ? -1:1
				);
			return result;
		});
		dataModel.result(brands);
	}
	dataModel.down = function(item){
		var brands = dataModel.result();
		var index = brands.indexOf(item);
		if(index == brands.length-1){
			alert("已经是最后一个了，无法下移！");
		}
		var temp = brands[index].ordernum();
		brands[index].ordernum(brands[index+1].ordernum());	
		brands[index+1].ordernum(temp);	
		brands.sort(function(left,right){
			var result = left.ordernum() == right.ordernum() ? 0:(
					left.ordernum() < right.ordernum() ? -1:1
				);			
			return result;
		});
		dataModel.result(brands);
	}		
	if(!bind){
		bind = true;
		ko.applyBindings(dataModel);
	}
	if(rePage){
		//生成分页
		genPaginator(total,pSize,param.page,handlePageChange)
	}
}
function handlePageChange (num, type) {
    if(type == 'change'){
    	getDatas4page(num);
    }
}
function add(){
		window.location.href="materialbrandadd.htm";
}
function reorder(){
		/*var idStr = '';
		var brands = dataModel.result();
		for(var i in brands){
			idStr += brands[i].id();
			idStr += ',';
		}
		idStr = idStr.substring(0,idStr.length-1);
		var param ={ids : idStr};*/
		var param ='{';
		var results = dataModel.result();
		for(var i in results){
			param += '"'+ results[i].id()+'":'+results[i].ordernum()+',';		
		}
		param = param.substring(0,param.length-1);
		param += "}";
		//提交
		var url = genUrl(MATERIAL_BRAND)+'/order';
		postByJsonString(url,param,function(data){
			friendlyTip(data);
			window.location.href='materialbrandlist.htm?';
		});
	}