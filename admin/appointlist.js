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
		//token : token,
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
	getData(APPOINT_APPOINT,param,afterGetDatas);
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
	dataModel = ko.mapping.fromJS(data);
	dataModel.add = function(){
		
		alert('add');
	}
	dataModel.remove = function(item){
		
		alert('remove'+item.id());
		if(ConfDel(0)){
			remove();
		}
	}
	dataModel.removeSelected = function(){
				
		if(ConfDelAll(0)){
			
			//todo 删除
			alert(filterSelected(dataModel.result()).length);
		}
	}
	dataModel.modify = function(item){
		
		//alert('modify'+item.id);
		window.location.href='appointedit.htm?id='+item.id();
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

function AppointViewModel(data){
	//添加checked属性，默认为false
	var results = data.result;
	for(var i in results){
		results[i].selected = false;
	}
	
	data.result = results;
	self = this;
	self.size = ko.observable(data.size);
	self.totalCount = ko.observable(data.totalCount);
	self.totalPages = ko.observable(data.totalPages);
	self.page = ko.observable(data.page);
	self.result = ko.observableArray(data.result);
}