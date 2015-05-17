<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="enter">
	<jsp:attribute name="script">
		<script>
		function ajax(url,params,type,async,showOrganizationsData){
			$.ajax({
				url:url,
				data:params,
				type:type,
				dataType:"json",
				success:function(data){
					showOrganizationsData(data);
				},
				async:async
			});
		}
		$(function(){
			uploadEnabled();
			ajax("<c:url value='/admin/organization/datalist'/>",null,
					"get",true,showOrganizationsData);
			ajax('<c:url value="/admin/designer/getServiceTypeData"/>',null,
					"get",false,showServiceTypeData);
			getServiceLevel();
		});
		function uploadEnabled(){
			//如果存在附件则当前上传按钮不可用
			var files=$("input[name='mediaFiles']");
			files.each(function(){
				var file=$(this);
				if(file.next("a").length>0){
					file.attr("disabled","disabled");
				}
				
			});
		}
		function showOrganizationsData(data){
			var list=data.result;
			var obj=document.getElementById("organization");
			var option=new Option("请选择...","0");
			obj.add(option);
			if(list!=null){
				for(var i=0;i<list.length;i++){
		    		var organization=list[i];
		    		var option=new Option(organization.name,organization.id);
		    		if(option.value=="${t.organization.id}"){
		    			option.selected=true;
		    		} 
		    		obj.add(option);
		    	}
			}
			
		}
		function deleteDesignerMedia(designerid,mediaid,obj){
			ajax("<c:url value='/admin/designer/delMedia'/>",{designerid:designerid,mediaid:mediaid},"get",true,function(data){
				if(data.status=="SUCCESS"){
					$(obj).prev().prev().removeAttr("disabled"); 
					$(obj).parent().find("a").remove();
				}
				
			});
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
		function delDesignerLevel(obj){
			$(obj).parent().remove();
		}
		
		</script>
	</jsp:attribute>
	<jsp:attribute name="nav">
		<my:enter-nav tab="designer"></my:enter-nav>
	</jsp:attribute>
	<jsp:body>
		<form action="./" role="form" class="form-horizontal" method="post" enctype="multipart/form-data">
           <div class="form-group">
           <label for="name" class="col-sm-2 control-label"></label>
               <div class="col-sm-10" align="right">
               <img src="${t.headPortraitFile.url }" width="200" height="200">
          	   </div>
           </div>
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">姓名:</label>
               <div class="col-sm-10">
                   <input value="${t.name }" type="tel" class="form-control" name="name" placeholder="please input name">
               </div>
           </div>
           <div class="form-group">
               <label for="descr" class="col-sm-2 control-label">简介:</label>
               <div class="col-sm-10">
               <textarea class="form-control" name="descr" rows="3">${t.descr }</textarea>
               </div>
           </div>
           <div class="form-group">
               <label for="headPortrait" class="col-sm-2 control-label">修改头像:</label>
               <div class="col-sm-10">
                   <input name="headPortraitFile" type="file" />
               </div>
           </div>
           <c:set var="i" value="0" />
           <c:forEach items="${t.medias}" var="media" varStatus="status">
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品${status.index+1}:</label>
               <div class="col-sm-10">
                   <input  type="file" name="mediaFiles"/>
                   <a class="filename" ><img src="${media.url }" width="200" height="200"></a>
				   <a class="filebutton" href="javascript:;" onclick="deleteDesignerMedia('${media.designerid}','${media.mediaid}',this)">删除</a>
               </div>
           </div>
           <c:set var="i" value="${status.index+1}" />
           </c:forEach>	
           <c:if test="${i lt 4 }">
		   <c:forEach begin="${i+1 }" end="4" varStatus="status">
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品${status.index}:</label>
               <div class="col-sm-10">
                   <input type="file" name="mediaFiles"/>
               </div>
           </div>
           </c:forEach>
		   </c:if>
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
                   <c:forEach items="${t.services }" var="serviceLevel">
                   <a>
                      <input type="hidden" value="${serviceLevel.serviceType.id }"/>
	            	  <input name="serviceLevelids" type="hidden" value="${serviceLevel.id }"/>
	            	  <button id="serviceLevel" type="button" class="btn btn-default" >
	            	  ${serviceLevel.serviceType.name }&nbsp;${serviceLevel.name }&nbsp;￥${serviceLevel.price }
	            	  </button>
	            	  <button class="btn btn-default btn-sm" type="button" onclick="delDesignerLevel(this);">x</button> 
	               </a>
                   </c:forEach>
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