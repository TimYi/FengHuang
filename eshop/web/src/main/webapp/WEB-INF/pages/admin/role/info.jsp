<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="authentication">
	<jsp:attribute name="nav">
		<my:auth-nav tab="role"></my:auth-nav>
	</jsp:attribute>
	<jsp:body>
		<form role="form" class="form-horizontal">
           <div class="form-group">
               <label class="col-sm-2 control-label">名称:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.name }</p>
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2 control-label">描述:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.description }</p>
               </div>
           </div>           
           <div class="form-group">
               <label class="col-sm-2 control-label">用户:</label>
               <div class="col-sm-10">
                   <c:forEach items="${t.users }" var="user">
                   <button type="button" class="btn btn-default">${user.username }</button>
                   </c:forEach>
               </div>
           </div>
           <div class="form-group">
               <label class="col-sm-2 control-label">权限:</label>
               <div class="col-sm-10">
                   <c:forEach items="${t.authorities }" var="authority">
                   <button type="button" class="btn btn-default">${authority.authority }</button>
                   </c:forEach>
               </div>
           </div>
       </form>
	</jsp:body>
</my:admin>