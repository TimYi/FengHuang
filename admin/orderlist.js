var dataModel;
var bind = false;//数据绑定标识
var rePage = true;
/*
分页功能变量定义
*/
var total;//总数据条数
var curPage = 1;//当前页码,初始为1	
var param;
var Obj = function(name,value){
		this.name = name;
		this.value = value;
	}
var statusDic;
var searchDic;
function onload(){
	//构造状态下拉列表		
	statusDic = [
		new Obj('WAITING','WAITING'),
		new Obj('PAYED','PAYED'),
		new Obj('PROCESSING','PROCESSING'),
		new Obj('COMPLETE','COMPLETE'),
		new Obj('CANCEL','CANCEL')
	];
	searchDic = [
		new Obj('ID','id'),
		new Obj('编码','code'),
		new Obj('姓名','realName'),
		new Obj('手机','mobile')
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
	getData(ORDER_ORDER,param,afterGetDatas);
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
		dataModel.selectedSearch = ko.observable();
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
function doSearch(){
	initParam();
	//查询格式：search_operator_param=value
	var selectedSearch = dataModel.selectedSearch();
	if(typeof selectedSearch == 'undefined'){
		alert("请选择搜索条件");
		return;
	}
	var searchParam = selectedSearch.value;
	var value = $("#keyword").val();
	var searchType;
	
	if(searchParam == 'ID'){
		param.search_like_id = value;
	}else if(searchParam == 'code'){
		param.search_like_code = value;
	}else if(searchParam == 'mobile'){
		param.search_like_mobile = value;
	}else if(searchParam == 'realName'){
		param.search_like_realName = value;
	}
	getData(ORDER_ORDER,param,afterGetDatas);
}