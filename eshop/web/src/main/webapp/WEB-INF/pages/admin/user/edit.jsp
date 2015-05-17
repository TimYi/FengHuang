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
		<form action="./" role="form" class="form-horizontal" method="post" enctype="multipart/form-data">
           <div class="form-group">
               <label for="username" class="col-sm-2 control-label">用户名:</label>
               <div class="col-sm-10">
                   <input value="${t.username }" type="tel" class="form-control" name="username" placeholder="please input username">
               </div>
           </div>
           <div class="form-group">
               <label for="password" class="col-sm-2 control-label">密码:</label>
               <div class="col-sm-10">
                   <input value="${t.password }" type="password" class="form-control" name="password" placeholder="please input password">
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