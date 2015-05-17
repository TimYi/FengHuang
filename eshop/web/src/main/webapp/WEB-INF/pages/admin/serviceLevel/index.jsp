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
	<div class="row">
	<div class="col-sm-1">
	<button class="btn btn-default" onclick="window.location.href='<c:url value="/admin/serviceLevel/add"/>'">添加</button>
	</div>
	<div class="dropdown col-sm-2">
	   <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" 
	      data-toggle="dropdown">
	      	服务类型
	      <span class="caret"></span>
	   </button>
	   <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	   	  <li role="presentation">
	          <a role="menuitem" tabindex="0" href="<c:url value='/admin/serviceLevel'/>">全部</a>
	        </li>
	 	  <c:forEach items="${services }" var="s" varStatus="status">
	 	  	<li role="presentation">
	          <a role="menuitem" tabindex="${status.count }" href="<c:url value='/admin/serviceLevel?typeid=${s.id }'/>">${s.name }</a>
	        </li>
	 	  </c:forEach>	  
	   </ul>
	</div>	
	</div>
		<table class="table table-bordered text-center mgt-2">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>价格</th>
                    <th>查看</th>
                    <th>修改</th>
                    <th>删除</th>
                </tr>
            </thead>                        
            <tbody>
            	<c:forEach items="${list }" var="t">
            	<tr id="${t.id }">
                     <td>${t.name }</td>
                     <td>${t.price }</td>
                     <td><a href="<c:url value='/admin/serviceLevel/${t.id }'/>">查看</a></td>
                     <td><a href="<c:url value='/admin/serviceLevel/${t.id }/edit'/>">修改</a></td>
                     <td><a onclick="del('<c:url value="/admin/serviceLevel"/>','${t.id }')">删除</a></td>
                 </tr>
            	</c:forEach>
            </tbody>
        </table>
	</jsp:body>
</my:admin>