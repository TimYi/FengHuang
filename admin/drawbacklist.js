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
var status;
var statusDic;
var selectedStatus;
var Status = function(key,value){
	this.key = key;
	this.value = value;
}
function onload(){
	initParam();
	statusDic = new Array();
	statusDic.push(new Status("全部","ALL"));
	statusDic.push(new Status("等待处理","WAITING"));
	statusDic.push(new Status("批准退款","APPROVE"));
	statusDic.push(new Status("拒绝退款","DISAPPROVE"));	
	//默认显示全部
	selectedStatus = statusDic[0];
	//alert(selectedStatus.key+selectedStatus.value);
	getData(ORDER_DRAWBACK,param,afterGetDatas);
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
	if(status != 'ALL'){
		getData(ORDER_DRAWBACK+'/?status='+status,param,afterGetDatas);
	}else{
		getData(ORDER_DRAWBACK,param,afterGetDatas);
	}
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
		dataModel.selectedStatus = ko.observable(selectedStatus);	
		dataModel.statusDic = ko.observableArray(statusDic);
	}else{
		ko.mapping.fromJS(data, dataModel);
		dataModel.selectedStatus(selectedStatus);	
	}
	dataModel.detail = function(item){
		window.location.href='drawbackdeal.htm?id='+item.id();
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
	window.location.href="materialproductadd.htm";
}
function onStatusChange(){
	status = dataModel.selectedStatus().value;
	selectedStatus = dataModel.selectedStatus();
	getDatas();
}