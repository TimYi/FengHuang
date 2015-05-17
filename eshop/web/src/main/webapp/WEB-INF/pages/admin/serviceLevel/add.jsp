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
		<form action="./" role="form" class="form-horizontal" method="post" enctype="multipart/form-data">
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">名称:</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" name="name" placeholder="please input name">
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">描述:</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" name="description" placeholder="please input description">
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">类型:</label>
               <div class="col-sm-10">
               	   <select class="form-control" name="serviceTypeId">
               	       <c:forEach items="${services }" var="s">
               	           <option value="${s.id }">${s.name }</option>
               	       </c:forEach>
               	   </select>
               </div>
           </div>
           <div class="form-group">
               <label for="price" class="col-sm-2 control-label">价格（元/小时）:</label>
               <div class="col-sm-10">
                   <input type="number" class="form-control" name="price" placeholder="please input number">
               </div>
           </div>
           <div class="form-group">
               <label for="priority" class="col-sm-2 control-label">序号:</label>
               <div class="col-sm-10">
                   <input type="number" class="form-control" name="priority" placeholder="please input priority">
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