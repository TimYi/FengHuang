var dataModel;
var bind = false;//数据绑定标识
var rePage = true;
/*
分页功能变量定义
*/
var total ;//总数据条数
var curPage = 1;//当前页码,初始为1	
var param;
var packages ;
var packageDic;
var selectedPackage;
var packageId;//可能是一级的也可能是二级的
var Package = function(id,name){
			this.id = id;
			this.name = name;
}
function onload(){
	initParam();
	//先获取page信息，并提供下列列表，在选择下列列表的基础上展示对应的轮播图片
	getData(ORDER_PACKAGE+'/all',param,afterGetPackage4Select);
}
function afterGetPackage4Select(data){
	if(!isErrorData(data)){
		packageDic = new Array(); 
		packages = data.result.result;
		for(var i in packages){
			var pac = new Package(packages[i].id,packages[i].name);
			packageDic.push(pac);
		}
		//默认显示全部
		getData(PACKAGE_SPACE,param,afterGetDatas);
		/*var pacId = selectedPackage.id;
		if(typeof pacId != 'undefined'){
			getData(PACKAGE_SPACE+'/bypackage/'+pacId,param,afterGetDatas);
		}else{
			getData(PACKAGE_SPACE,param,afterGetDatas);
		}*/
	}
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
	getData(PACKAGE_SPACE,param,afterGetDatas);
}
function afterGetDatas(data){
	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}

function bindData(data){
	total = data.totalCount;//用于分页
	if(typeof data.result == 'undefined'){
		data.result = new Array();
	}
	var results = data.result;
	for(var i in results){
		results[i].selected = false;
	}
	if(!bind){
		dataModel = ko.mapping.fromJS(data);
		dataModel.selectablePackage = ko.observableArray(packageDic);
		dataModel.selectedPackage = ko.observable(selectedPackage);
	}else{
		ko.mapping.fromJS(data, dataModel);
		dataModel.selectedPackage(selectedPackage);
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(PACKAGE_SPACE)+'/'+item.id();
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
		
		window.location.href='spaceedit.htm?id='+item.id();
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
	window.location.href="spaceadd.htm";
}
function onPackageChange(){
	selectedPackage = dataModel.selectedPackage();
	if(typeof dataModel.selectedPackage() !='undefined'){
		packageId = dataModel.selectedPackage().id;
		getData(PACKAGE_SPACE+'/bypackage/'+packageId,param,afterGetDatas);
	}
}