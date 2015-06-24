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
var colorDic;
function onload(){
	
	initParam();
	$.getJSON(CATEGORY_QUERY+'color',null,function(data){
	 		colorDic = data.result;
			getDatas();	
	 	});

}
function initParam(){
	
	param={
		token : token,
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
	getData(MEMBER_GROUP_LIST,param,afterGetDatas);
}
function getEnterMembers(verified){
	initParam();
	param.search_eq_verified=verified;
	getDatas();
}

function getDeleteMembers(){
	initParam();
	param.search_eq_delstate = 'true';
	getDatas();
}
function searchMembers(){
	
	initParam();
	//模糊查询，要给天明讨论
	param.search_like_all = document.getElementById('keyword').value;
	getDatas();
}
function afterGetDatas(data){
	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}
function bindData(data){
	
	total = data.totalCount;
	dataModel = ko.mapping.fromJS(data);	
	for(var j in dataModel.result()){
		//alert(j);
	
		var detail = dataModel.result()[j];
		//alert(j+','+detail.color());
		if(typeof dataModel.result()[j].color !== 'function'){
			alert(j+"not func")
	 		//注意：sex 为 null时，是function ,不为null时是object
		 	for(var i in colorDic){
		 		if(colorDic[i].id === dataModel.result()[j].color.id()){
		 			dataModel.result()[j].color = ko.observable(colorDic[i]);
		 			break;
		 		}
		 	}
	 	}else{
	 		dataModel.result()[j].color = ko.observable(colorDic[0]);
	 	}
	 	//dataModel.result()[j] = detail;	 
	}

	dataModel.remove = function(item){
		
		if(ConfDel(0)){
			
			var url = genUrl(MEMBER_GROUP_LIST)+'/'+item.id();  
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
	
	dataModel.updateAll = function(){
		
		//var arr = dataModel.result().slice();
		var groupname_add = document.getElementById("groupname_add").value;
		var expvala_add = document.getElementById("expvala_add").value;
		var expvalb_add = document.getElementById("expvalb_add").value;
		var stars_add = document.getElementById("stars_add").value;
		var color_add = document.getElementById("color_add").value;
		alert(color_add);
		var haveNew = false;
		if(groupname_add!==''
			|| expvala_add !== ''
			|| expvalb_add !== ''
			|| stars_add !== ''
			|| color_add !== ''){
			//有新增
			haveNew = true;
			var newGroup = {
				id : '',
				name : groupname_add,
				minExp : expvala_add,
				maxExp : expvalb_add,
				stars : stars_add,
				color : color_add
			};			
			//arr.push(newGroup);
			dataModel.result.push(newGroup);
		}
			
		var url = genUrl(MEMBER_GROUP_UPDATEALL);
		var param = ko.mapping.toJS(dataModel.result);
		updateAll(url,JSON.stringify(param) ,function(data){
			
			//friendlyTip(JSON.parse(data));
			//dataModel.result = ko.observableArray(data.result);
			afterGetDatas(data);
		});	
	}
	if(!bind){
		bind = true;
		ko.applyBindings(dataModel);
	}
	if(rePage){
		
		//生成分页
		genPaginator(total,pSize,param.page,handlePageChange);
	}

}
function handlePageChange (num, type) {
        	
    if(type == 'change'){
    
    	getDatas4page(num);
    }            
}