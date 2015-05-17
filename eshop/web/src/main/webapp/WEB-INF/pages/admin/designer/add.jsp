<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<my:admin tab="enter">
	<jsp:attribute name="script">
		<script>
		
		/*ajax请求公用方法*/
		function ajax(url,params,type,async,showData){
			$.ajax({
				url:url,
				data:params,
				type:type,
				dataType:"json",
				success:function(data){
					showData(data);
				},
				async:async
			});
		}
		//初始化页面数据
		$(function(){
			ajax('<c:url value="/admin/organization/datalist"/>',null,
					"get",true,showOrganizationsData);
			ajax('<c:url value="/admin/designer/getServiceTypeData"/>',null,
					"get",false,showServiceTypeData);
			getServiceLevel();
		});

		function showOrganizationsData(data){
			var list=data.result;
			var obj=document.getElementById("organization");
			var option=new Option("请选择...","0");
			obj.add(option);
			if(list!=null){
				for(var i=0;i<list.length;i++){
		    		var organization=list[i];
		    		var option=new Option(organization.name,organization.id); 
		    		obj.add(option);
		    	}
			}
		}
		function showServiceTypeData(data){
			var list=data.result;
			var obj=document.getElementById("serviceType");
			var option=new Option("请选择...","0");
			obj.add(option);
			if(list!=null){
				for(var i=0;i<list.length;i++){
		    		var serviceType=list[i];
		    		var option=new Option(serviceType.name,serviceType.id); 
		    		obj.add(option);
		    	}
			}
		}
		function getServiceLevel(){
	    	var serviceTypeid=$("#serviceType :selected").val()
	    	ajax('<c:url value="/admin/designer/getServiceLevelData"/>',{serviceTypeid:serviceTypeid},
	    			"get",true,showServiceLevelData);

		}
		
		function showServiceLevelData(data){
			document.getElementById("serviceLevel").innerHTML="";
			var list=data.result;
			var obj=document.getElementById("serviceLevel");
			var option=new Option("请选择...","0");
			obj.add(option);
			if(list!=null){
				for(var i=0;i<list.length;i++){
		    		var serviceLevel=list[i];
		    		var option=new Option(serviceLevel.name+" ￥"+serviceLevel.price,serviceLevel.id); 
		    		obj.add(option);
		    	}
			}
		}
		function addServiceLevel(){
			if($("#serviceLevel :selected").val()=="0"){
				alert("请选择等级...");
				return;
			}
			var serviceType=$("#serviceType :selected").text();
			var serviceTypeid=$("#serviceType :selected").val();
			var serviceLevel=$("#serviceLevel :selected").text();
			var serviceLevelid=$("#serviceLevel :selected").val();
			//serviceType不能重复
			var serviceTypeids=$("#serviceLevel_add a").find("input:first");
			for(var i=0;i<serviceTypeids.length;i++){
				var stid=serviceTypeids[i].value;
				if(stid==serviceTypeid){
					alert("该设计师类型已存在...");
					return;
				}
			}
         	var $a=$("<a></a>");
			var $input=$("<input/>");
			$input.attr("type","hidden");
			$input.attr("value",serviceTypeid);
			var $input2=$("<input/>");
			$input2.attr("name","serviceLevelids");
			$input2.attr("type","hidden");
			$input2.attr("value",serviceLevelid);
			var $button=$("<button type='button' class='btn btn-default'></button>");
			$button.html(serviceType+" "+serviceLevel);
			var $delbutton=$("<button type='button' class='btn btn-default btn-sm'>x</button>");
			$delbutton.bind('click',function(){
				$(this).parent().remove();
			});
			$a.append($input);
			$a.append($input2);
			$a.append($button);
			$a.append($delbutton);
			$("#serviceLevel_add").append($a);
			//添加完后清空选项区
			$("#serviceType").val("0");
			$("#serviceLevel").find(":gt(0)").remove();
		}
		</script>
	</jsp:attribute>
	<jsp:attribute name="nav">
		<my:enter-nav tab="designer"></my:enter-nav>
	</jsp:attribute>
	<jsp:body>
		<form action="./" role="form" class="form-horizontal" method="post" enctype="multipart/form-data">
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">姓名:</label>
               <div class="col-sm-10">
                   <input class="form-control" name="name" placeholder="please input name">
               </div>
           </div>
           <div class="form-group">
               <label for="descr" class="col-sm-2 control-label">简介:</label>
               <div class="col-sm-10">
                	<textarea class="form-control" name="descr" rows="3"></textarea>
               </div>
           </div>
           <div class="form-group">
               <label for="headPortrait" class="col-sm-2 control-label">上传头像:</label>
               <div class="col-sm-10">
                   <input type="file" name="headPortraitFile">
               </div>
           </div>
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品1:</label>
               <div class="col-sm-10">
                   <input type="file"  name="mediaFiles">
               </div>
           </div>
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品2:</label>
               <div class="col-sm-10">
                   <input type="file"  name="mediaFiles">
               </div>
           </div>
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品3:</label>
               <div class="col-sm-10">
                   <input type="file"  name="mediaFiles">
               </div>
           </div>
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品4:</label>
               <div class="col-sm-10">
                   <input type="file"  name="mediaFiles">
               </div>
           </div>
           <div class="form-group">
               <label for="organization" class="col-sm-2 control-label">所属组织:</label>
               <div class="col-sm-10">
                   <select id="organization" name="organizationid" class="form-control"></select>
               </div>
           </div>
           <div class="form-group">
               <label for="organization" class="col-sm-2 control-label">设计师等级:</label>
               <div class="col-sm-10">
                   <select id="serviceType" name="serviceTypeid" onchange="getServiceLevel()" class="form-control"></select>
                   <select id="serviceLevel" name="serviceLevelid" class="form-control"></select>
                   <button class="btn btn-default btn-sm" type="button" onclick="addServiceLevel()">+</button>
                   <div id="serviceLevel_add" >
	            	  <!-- <a>
	            	  <input type="hidden" value=""/>
	            	  <input type="hidden" value=""/>
	            	  <button id="serviceLevel" type="button" class="btn btn-default" >
	            	  
	            	  </button>
	            	  <button type="button" >X</button> 
	            	  </a>-->
               </div>
               </div>
               
           </div>
           <div class="form-group">
			   <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">保存</button>
			   </div>
			</div>
       </form>
	</jsp:body>
</my:admin>