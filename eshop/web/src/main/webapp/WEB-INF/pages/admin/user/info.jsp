<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="authentication">
	<jsp:attribute name="nav">
		<my:auth-nav tab="user"></my:auth-nav>
	</jsp:attribute>
	<jsp:body>
		<form role="form" class="form-horizontal" method="post">
           <div class="form-group">
               <label for="username" class="col-sm-2 control-label">用户名:</label>
               <div class="col-sm-10">
                   <p class="form-control">${t.username }</p>
               </div>
           </div>
       </form>
	</jsp:body>
</my:admin>