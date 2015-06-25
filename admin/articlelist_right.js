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
function onload(){
	initParam();
	getDatas();	
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
	getData(ARTICLE_ARTICLE_LIST,param,afterGetMembers);
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
function afterGetMembers(data){

	//先判断并处理错误数据
	if(!isErrorData(data))
		//数据正确时进行绑定
	bindData(data.result);	
}
function bindData(data){
	total = data.totalCount;
	dataModel = new PagingViewModel(data);
	dataModel.add = function(){
		
		alert('add');
	}
	dataModel.remove = function(item){
		
		alert('remove'+item.id);
		if(ConfDel(0)){
			remove();
		}
	}
	dataModel.removeSelected = function(){
				
		if(ConfDelAll(0)){
			
			//todo 删除
			alert(filterSelected(dataModel.result()).length);
		}
	}
	dataModel.modify = function(item){
		
		alert('modify'+item.id);
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