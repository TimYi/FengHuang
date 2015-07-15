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
var brandId;
var brandDic;
var selectedBrand;
var Brand = function(id,name){
	this.id = id;
	this.name = name;
}
function onload(){
	initParam();
	//获取所有品牌
	getData(MATERIAL_BRAND+'/all',null,afterGetBrands4Select);
}
function afterGetBrands4Select(data){
	if(!isErrorData(data)){
		brands = data.result;
		brandDic = new  Array();
		for(var i in brands){
			var brand = new Brand(brands[i].id,brands[i].name);
			brandDic.push(brand);
		}
		//默认选取第一个
		selectedBrand = brandDic[0];
		brandId = selectedBrand.id;
		//获取产品类型数据
		getDatas();
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
	if(typeof brandId != 'undefined'){
		getData(MATERIAL_PRODUCT+'/bybrand/'+brandId,param,afterGetDatas);
	}else{
		getData(MATERIAL_PRODUCT,param,afterGetDatas);
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
		dataModel.selectedBrand = ko.observable(selectedBrand);	
		dataModel.brandDic = ko.observableArray(brandDic);
	}else{
		ko.mapping.fromJS(data, dataModel);
		dataModel.selectedBrand(selectedBrand);	
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(MATERIAL_PRODUCT)+'/'+item.id();
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
		window.location.href='materialproductedit.htm?id='+item.id();
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
function add(){
	window.location.href="materialproductadd.htm";
}
function reorder(){
		var param ='{';
		var results = dataModel.result();
		for(var i in results){
			param += '"'+ results[i].id()+'":'+results[i].ordernum()+',';		
		}
		param = param.substring(0,param.length-1);
		param += "}";
		//提交
		var url = genUrl(MATERIAL_PRODUCT)+'/order';
		postByJsonString(url,param,function(data){
			friendlyTip(data);
			window.location.href='materialproductlist.htm?';
		});
}
function onBrandChange(){
	brandId = dataModel.selectedBrand().id;
	selectedBrand = dataModel.selectedBrand();
	getDatas();
}