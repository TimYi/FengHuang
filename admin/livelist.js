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
var searchDic;
var Obj = function(name,value){
		this.name = name;
		this.value = value;
	}
function onload(){
	searchDic = [
		new Obj('用户名','userName'),
		new Obj('ID','id'),
		new Obj('姓名','realName'),
		new Obj('手机','mobile')
	];
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
	getData(LIVE_LIVE,param,afterGetDatas);
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
		results[i].shouldShowDisp = results[i].shouldShow?'可见':'不可见'	
	}
	dataModel = ko.mapping.fromJS(data);
	dataModel.selectedSearch = ko.observable();	
	dataModel.remove = function(item){
		
		if(ConfDel(0)){
			
			var url = genUrl(LIVE_LIVE)+'/'+item.id();
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
		
		window.location.href='liveedit.htm?id='+item.id();
	}
	dataModel.detail = function(item){
		
		window.location.href='livedetaillist.htm?liveId='+item.id();
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
function add(){
		window.location.href="liveadd.htm";
}
function doSearch(){
	rePage = true;
	curPage = 1;
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
	}else if(searchParam == 'mobile'){
		param.search_like_user={
			mobile : value
		}
	}else if(searchParam == 'realName'){
		param.search_like_user ={
			realName : value
		}
	}else if(searchParam == 'userName'){
		param.search_like_user={
			username : value
		}
	}
	getData(LIVE_LIVE,param,afterGetDatas);
}
function getAll(){
	rePage = true;
	curPage = 1;
	initParam();
	getData(LIVE_LIVE,param,afterGetDatas);
}