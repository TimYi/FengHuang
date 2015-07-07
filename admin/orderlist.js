var dataModel;
var bind = false;//数据绑定标识
var rePage = true;
/*
分页功能变量定义
*/
var total;//总数据条数
var curPage = 1;//当前页码,初始为1	
var param;
var StatusObj = function(name,value){
		this.name = name;
		this.value = value;
	}
function onload(){
	//构造状态下拉列表		
	statusDic = [
		new StatusObj('WAITING','WAITING'),
		new StatusObj('PAYED','PAYED'),
		new StatusObj('PROCESSING','PROCESSING'),
		new StatusObj('COMPLETE','COMPLETE'),
		new StatusObj('CANCEL','CANCEL')
	];
	initParam();
	getData(ORDER_ORDER,param,afterGetDatas);
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
	getData(MENU_ARTICLE,param,afterGetDatas);
}
function afterGetDatas(data){
	
	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}

function bindData(data){
	total = data.totalCount;//用于分页	
	var results = data.result;
	for(var i in results){
		results[i].selected = false;
	}
	if(!bind){
		dataModel = ko.mapping.fromJS(data);
		dataModel.selectStatus = ko.observable();	
	}else{
		ko.mapping.fromJS(data, dataModel);
	}
		
	dataModel.remove = function(item){
		
		if(ConfDel(0)){
			
			var url = genUrl(ORDER_ORDER)+'/'+item.id();
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
		
		window.location.href='orderedit.htm?id='+item.id();
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
function filter(){
	var status = $('#selectStatus').val();
	if(status == ''){
		alert('请先选择筛选条件！');
		return;
	}
	var url = ORDER_ORDER+'/status/'+status;	
	initParam();
	getData(url,param,afterGetDatas);
}
function getAll(){
	initParam();
	getData(ORDER_ORDER,param,afterGetDatas);
}

/*function add(){
		window.location.href="articleadd.htm";
}*/