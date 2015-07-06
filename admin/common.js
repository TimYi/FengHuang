//用于构造集合对应的viewmodel
/*function PagingViewModel(data){
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
}*/
//用于构造单个对象对应的viewmodel

function PagingJson2ViewModel(data){

	return ko.mapping.fromJS(data);
}

function genUrl1(baseUrl,sn,pageIndex,pageNum){
	var url =HOST+ baseUrl+'?token='+token+'&page='+pageIndex+'&size=';
	if (pageNum!= 'undefined' && pageNum!= null && pageNum != '')
		
	return url+pageNum;
	else return url+pSize;
}
function genUrl(baseUrl,id){
	if(id === null || id === undefined || id ==='')
	{	return url =HOST+ baseUrl;}
	else 
	{	return url =HOST+ baseUrl+'/'+id;}
}
ko.observableArray.fn.filterByProperty = function(propName, matchValue) {
    return ko.pureComputed(function() {
        var allItems = this(), matchingItems = [];
        for (var i = 0; i < allItems.length; i++) {
            var current = allItems[i];
            if (ko.unwrap(current[propName]) === matchValue)
                matchingItems.push(current);
        }
        return matchingItems;
    }, this);
}
function filterSelected(items){
	var selectedIds = [];
	for(var i in items){
		if(items[i].selected){
			
			selectedIds.push(items[i].id)
		}
	}
	return selectedIds;
}
function isEmpty(data){
	if(data === null || data===undefined||data===''){
			return true;
	}
	return false;
}
//判断获取的数据是否正确
function isErrorData(data){
	if(isEmpty(data)){
		alert("返回数据错误！")
	}else if(data.status === 'ERROR'){
		alert(data.error+'代码('+data.code+')，描述信息：'+data.errorDescription);
	}else if(data.status === 'OK'){
		return false;
	}
	return true;
}
function genPaginator(total,pSize,curPage,handlePageChange,visibleP){
	
	if(visibleP === null) 
		visibleP = 10;
	
	 $('.pageList').jqPaginator({
        totalCounts: total,
        pageSize : pSize,
        visiblePages: visibleP,
        currentPage: curPage,
        activeClass:'active',
        disableClass:'disabled',
        first: '<a href="javascript:;">&lt;&lt;</a>',
        prev: '<a href="javascript:;">&lt;</a>',        
        page: '<span ><a href="javascript:;" class="num">{{page}}</a></span>',
        next: '<a href="javascript:;">&gt;</a>',
        last: '<a href="javascript:;">&gt;&gt;</a>',
        onPageChange: handlePageChange
    });
}
//获取数据集合
function getData(moduleUrl,param,callback){
	
	var url = genUrl(moduleUrl);
	requestByGetJSON(url,param,callback);
}
//根据id获取数据
function getDataById(moduleUrl,id,callback){
	var url = genUrl(moduleUrl,id);
	requestByGetJSON(url,null,callback);
}
function getUrlParam(paramStr,name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = paramStr.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
function friendlyTip(data){
	
	alert(data.status+','+data.result);
}
