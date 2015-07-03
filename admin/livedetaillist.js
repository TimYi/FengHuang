var dataModel;
var bind = false;//数据绑定标识

var param;
function onload(){
	var id = getUrlParam(window.location.search,"id");	
	getData(LIVE_LIVE+'/'+id,param,afterGetDatas);
}
function afterGetDatas(data){

	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}
function bindData(data){	
	var results = data.lives;
	for(var i in results){
		results[i].selected = false;
	}
	dataModel = ko.mapping.fromJS(data);
	
	dataModel.remove = function(item){
		
		if(ConfDel(0)){
			
			var url = genUrl(LIVE_DETAIL)+'/'+item.id();
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
		
		window.location.href='livedetailedit.htm?id='+item.id();
	}
	dataModel.picMgr = function(item){
		
		window.location.href='livedetail_pic.htm?id='+item.id();
	}
	dataModel.interPicMgr = function(item){
		
		window.location.href='livedetail_interpic.htm?id='+item.id();
	}	
	if(!bind){
		bind = true;
		ko.applyBindings(dataModel);
	}
}

function add(){
		window.location.href="memberadd.html";
}