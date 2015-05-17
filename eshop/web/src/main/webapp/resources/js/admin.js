/**
 * create by ytm
 */

/**
 * 自动执行的方法
 */
$(function(){
	 $("#put").ajaxForm({
		 type:"put",
		 success:function() {
			 alert("保存成功！");
			 //window.location.href="../";
		 },
		 error:function() {
			 alert("保存失败！");
		 }
	 });
	 /*
	 $("#put").submit(function(e){	 
		 $(this).ajaxForm({
			 type:"put",
			 success:function() {
				 alert("保存成功！");
				 window.location.href="../";
			 },
			 error:function() {
				 alert("保存失败！");
			 }
		 });
		 e.preventDefault();
	 });*/
});

function del(url,id) {
	bootbox.confirm("确定要删除它吗？",
			function(result){
		if(!result)return;
		$.ajax({
	        url:url+"/"+id,
	        type:"DELETE",
	        success:function() {
	            $("#"+id).remove();
	        },
	        error:function() {
	            alert("删除失败！");
	        }
	    });
	});    
}

function post(url,success,error) {
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data) {
			if(data.status=="OK") {
				success(data.result);
			} else {
				if(error) {
					error(data.code,data.error,data.errorDescription);
				} else {
					alert(data);
				}
			}
		},
		error:function() {
			alert("请求失败！");
		}
	});
}


/**
 * 分页
 */
function pagination(totalPages,size) {
	size=size?size:12;
	var visiblePages=5;
	$('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: visiblePages,
        first:"首页",
        prev:"上一页",
        next:"下一页",
        last:"尾页",
        paginationClass:'pagination',
        href:"?page={{number}}&size="+size,
    });
}