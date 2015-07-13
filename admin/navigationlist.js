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
	getData(SYSTEM_NAVIGATION,param,afterGetDatas);
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
		var subNavs = results[i].subNavigations;
		for(var j in subNavs){
			subNavs[j].selected = false;
		}
	}
	if(!bind){
		dataModel = ko.mapping.fromJS(data);	
	}else{
		ko.mapping.fromJS(data, dataModel);
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(SYSTEM_NAVIGATION)+'/'+item.id();
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
		var url = "navigationedit.htm?id="+item.id();
		window.location.href = url;
	}
	dataModel.addSub = function(item){
		//判断item类型是否为DROPDOWN，只有是时才允许加子菜单
		if(item.type() === 'URL'){
			alert('只有DROPDOWN类型的菜单才可以添加子菜单！');
			return;
		}
		window.location.href='navigationadd.htm?superId='+item.id();
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
        	
	//alert(num+':'+type);
    if(type == 'change'){
    	getDatas4page(num);
    }            
}
function add(superId){
	var url = "navigationadd.htm";
	if(superId!=null){
		url += ('?superId='+superId);
	}
	window.location.href = url;
}
function reorder(){
		var idStr = '';
		var brands = dataModel.result();
		for(var i in brands){
			idStr += brands[i].id();
			idStr += ',';
		}
		idStr = idStr.substring(0,idStr.length-1);
		var param ={ids : idStr};
		//提交
		var url = genUrl(SYSTEM_NAVIGATION)+'/order';
		postReq(url,param,function(data){
			friendlyTip(data);
			window.location.href='navigationlist.htm?';
		});
	}