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
var brandDic;
var productDic;
var typeDic;
var packageDic;
var dataModel;
var brands;
var brandId;
var productId;
var selectedBrand;
var selectedProduct;
var material;
var param;
var Brand = function(id,name,productDic){
	this.id = id;
	this.name = name;
	this.productDic = productDic;
}
var Product = function(id,name){
	this.id = id;
	this.name = name;
}
function onload(){
	initParam();
	//先获取品牌
	getData(MATERIAL_BRAND+'/all',null,afterGetBrands4Select);	
}
function afterGetBrands4Select(data){
	if(!isErrorData(data)){
		brands = data.result;
		brandDic = new Array();
		for(var i in brands){
			var products = brands[i].products;
			productDic = new Array();
			for(var j in products){
				var product = products[j];
				var pr = new Product(product.id,product.name);
				productDic.push(pr);
			}
			var br = new Brand(brands[i].id,brands[i].name,productDic);
			brandDic.push(br);
		}
		//默认选取第一个品牌
		selectedBrand = brandDic[0];		
		//取第一个作为默认产品类型
		selectedProduct = selectedBrand.productDic[0];
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
	if(typeof selectedProduct === 'undefined'){
		getData(MATERIAL_MATERIAL,param,afterGetDatas);
	}else{
		getData(MATERIAL_MATERIAL+'/byproduct/'+selectedProduct.id,param,afterGetDatas);
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
		var packages = results[i].packages;
		var packageStr='';
		for(var j in packages){
			packageStr += packages[j].name+',';
		}
		packageStr = packageStr.substring(0,packageStr.length-1);
		results[i].packageStr = packageStr;
	}
	if(!bind){
		dataModel = ko.mapping.fromJS(data);
		dataModel.selectedBrand = ko.observable(selectedBrand);	
		dataModel.brandDic = ko.observableArray(brandDic);
		dataModel.selectedProduct = ko.observable(selectedProduct);
		//dataModel.productDic = ko.observableArray(productDic);
	}else{
		ko.mapping.fromJS(data, dataModel);
		dataModel.selectedBrand(selectedBrand);	
		dataModel.brandDic(brandDic);
		dataModel.selectedProduct(selectedProduct);
		//dataModel.productDic(productDic);
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(MATERIAL_MATERIAL)+'/'+item.id();
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
	dataModel.up = function(item){
		if(typeof selectedProduct === 'undefined'){
			//当前显示为全部轮播图片时
			alert('请先选择品牌及产品类型，然后再进行操作');
			return ;
		}
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
		if(typeof selectedProduct === 'undefined'){
			//当前显示为全部轮播图片时
			alert('请先选择品牌及产品类型，然后再进行操作');
			return ;
		}
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
	dataModel.modify = function(item){
		window.location.href='materialedit.htm?id='+item.id();
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
	window.location.href="materialadd.htm";
}
function reorder(){	
	if(typeof selectedProduct === 'undefined'){
		//当前显示为全部轮播图片时
		alert('请先选择品牌及产品类型，然后再进行操作');
		return ;
	}
	var param ='{';
	var results = dataModel.result();
	for(var i in results){
		param += '"'+ results[i].id()+'":'+results[i].ordernum()+',';		
	}
	param = param.substring(0,param.length-1);
	param += "}";
	//提交
	var url = genUrl(MATERIAL_MATERIAL)+'/order';
	postByJsonString(url,param,function(data){
		friendlyTip(data);
		window.location.href='materiallist.htm?';
	});
}
function onProductChange(){
	selectedProduct = dataModel.selectedProduct();
	getDatas();
}
function onBrandChange(){
	selectedBrand = dataModel.selectedBrand();
	var selectedBrandId = selectedBrand.id;
	productDic =  selectedBrand.productDic;
	//根据brandid切换
	selectedProduct = productDic[0];
	dataModel.selectedProduct(selectedProduct);
	getDatas();
}