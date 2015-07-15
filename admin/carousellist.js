var dataModel;
var bind = false;//数据绑定标识
var rePage = true;
/*
分页功能变量定义
*/
var total ;//总数据条数
var curPage = 1;//当前页码,初始为1	
var param;
var navs ;
var navDic;
var selectedNav;
var selectedSubNav;
var subNavDic;

var navId;//可能是一级的也可能是二级的
var Nav = function(id,title,type){
			this.id = id;
			this.title = title;
			this.type = type;
}
function onload(){
	initParam();
	//先获取page信息，并提供下列列表，在选择下列列表的基础上展示对应的轮播图片
	getData(SYSTEM_NAVIGATION+'/all',param,afterGetNav4Select);
}
function afterGetNav4Select(data){
	if(!isErrorData(data)){
		navDic = new Array(); 
		navs = data.result;		
		for(var i in navs){			
			var nav = new Nav(navs[i].id,navs[i].title,navs[i].type);
			navDic.push(nav);
		}
		selectedNav = navDic[0];		
		//判断一级菜单的类型
		if(selectedNav.type === 'DROPDOWN'){
			//选择默认二级菜单并显示
			$("#subNavSelector").show();
			subNavDic = new Array();
			var subNavs = navs[0].subNavigations;
			for(var j in subNavs){
				var sub = new Nav(subNavs[j].id,subNavs[j].title,subNavs[j].type);
				subNavDic.push(sub);
			}
			if(subNavDic.length !== 0){
				selectedSubNav = subNavDic[0];
				navId = selectedSubNav.id;
			}
		}else{
			navId = selectedNav.id;
		}
		if(typeof navId != 'undefined'){
			getData(TEMPLATE_CAROUSEL+'/bypage/'+navId,param,afterGetDatas);
		}else{
			getData(TEMPLATE_CAROUSEL,param,afterGetDatas);
		}
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
	getData(TEMPLATE_CAROUSEL,param,afterGetDatas);
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
		dataModel.selectableNav = ko.observableArray(navDic);
		dataModel.selectedNav = ko.observable(selectedNav);
		dataModel.selectedSubNav = ko.observable(selectedSubNav);
		dataModel.selectableSubNav = ko.observableArray(subNavDic);
	}else{
		ko.mapping.fromJS(data, dataModel);
		dataModel.selectedNav(selectedNav);		
		dataModel.selectedSubNav(selectedSubNav);
		dataModel.selectableSubNav(subNavDic);
	}
	dataModel.remove = function(item){
		if(ConfDel(0)){
			var url = genUrl(TEMPLATE_CAROUSEL)+'/'+item.id();
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
		
		window.location.href='carouseledit.htm?id='+item.id();
	}
	dataModel.up = function(item){
		if(typeof navId === 'undefined'){
			//当前显示为全部轮播图片时
			alert('请先选择一个菜单，然后再进行操作');
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
		if(typeof navId === 'undefined'){
			//当前显示为全部轮播图片时
			alert('请先选择一个菜单，然后再进行操作');
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
		window.location.href="carouseladd.htm";
}
function reorder(){	
		if(typeof navId === 'undefined'){
			//当前显示为全部轮播图片时
			alert('请先选择一个菜单，然后再进行操作');
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
		var url = genUrl(TEMPLATE_CAROUSEL)+'/order';
		postByJsonString(url,param,function(data){
			friendlyTip(data);
			window.location.href='carousellist.htm?';
		});
}
function onNavChange(){
	//alert(dataModel.selectedNav().type);
	//先判断是否含有子菜单
	if(dataModel.selectedNav().type == 'DROPDOWN'){
		//显示二级下拉列表
		$("#subNavSelector").show();
		var selectedNavId = dataModel.selectedNav().id;
		//dataModel.selectableSubNav.removeAll();	
		for(var i in navs){
			var nav = navs[i];		
			if(nav.id == selectedNavId){
				subNavDic = new Array();
				selectedNav = navDic[i];
				var subNav = nav.subNavigations;
				for(var j in subNav){
					var sub = new Nav(subNav[j].id,subNav[j].title,subNav[j].type);
					subNavDic.push(sub);
				}
				break;
			}
		}
		dataModel.selectedSubNav();
		dataModel.selectableSubNav(subNavDic);
	}else{
		$("#subNavSelector").hide();
		navId = dataModel.selectedNav().id
		getData(TEMPLATE_CAROUSEL+'/bypage/'+navId,param,afterGetDatas);
	}
}
function onSubNavChange(){
	//alert(dataModel.selectedSubNav().title);
	selectedSubNav = dataModel.selectedSubNav()
	navId = dataModel.selectedSubNav().id;
	getData(TEMPLATE_CAROUSEL+'/bypage/'+navId,param,afterGetDatas);
}