<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="enter">
	<jsp:attribute name="nav">
		<my:enter-nav tab="serviceLevel"></my:enter-nav>
	</jsp:attribute>
	<jsp:body>
		<form role="form" class="form-horizontal">
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">名称:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.name }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">描述:</label>
               <div class="col-sm-10">
               	   <p class="form-control">${t.description }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">类型:</label>
               <div class="col-sm-10">
               	   <p class="form-control">${t.serviceType.name }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="price" class="col-sm-2 control-label">价格（元/小时）:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.price }</p>
               </div>
           </div>
           <div class="form-group">
               <label for="priority" class="col-sm-2 control-label">序号:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.priority }</p>
               </div>
           </div>
       </form>
	</jsp:body>
</my:admin>