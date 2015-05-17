<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="enter">
	<jsp:attribute name="nav">
		<my:enter-nav tab="organization"></my:enter-nav>
	</jsp:attribute>
	<jsp:body>
		<form role="form" class="form-horizontal" method="post">
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">工作室名称:</label>
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
           
       </form>
	</jsp:body>
</my:admin>