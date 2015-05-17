<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="enter">
	<jsp:attribute name="nav">
		<my:enter-nav tab="designer"></my:enter-nav>
	</jsp:attribute>
	<jsp:body>
		<form role="form" class="form-horizontal" method="post">
		
           <div class="form-group">
           <label for="name" class="col-sm-2 control-label"></label>
               <div class="col-sm-10" align="right">
               <img src="${t.headPortraitFile.url }" width="200" height="200">
          	   </div>
           </div>
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">姓名:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.name }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="descr" class="col-sm-2 control-label">简介:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.descr}</p>
               </div>
           </div>
           <div class="form-group">
               <label for="organization" class="col-sm-2 control-label">工作室名称:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.organization.name }</p>
               </div>
           </div>
           <c:forEach items="${t.medias}" var="media" varStatus="status">
           <div class="form-group">
               <label for="case" class="col-sm-2 control-label">作品${status.index+1}:</label>
               <div class="col-sm-10">
                   <a class="filename" ><img src="${media.url }" width="200" height="200"></a>
               </div>
           </div>
           </c:forEach>	
            <div class="form-group">
               <label for="organization" class="col-sm-2 control-label">设计师等级:</label>
               <div class="col-sm-10">
                   <c:forEach items="${t.services }" var="serviceLevel">
                   <a>
	            	  <button id="serviceLevel" type="button" class="btn btn-default" >
	            	  ${serviceLevel.serviceType.name }&nbsp;${serviceLevel.name }&nbsp;￥${serviceLevel.price }
	            	  </button>
	               </a>
                   </c:forEach>
               </div>
               
           </div>
       </form>
	</jsp:body>
</my:admin>