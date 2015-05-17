<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="authentication">
	<jsp:attribute name="nav">
		<my:auth-nav tab="user"></my:auth-nav>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script>
			pagination('${page.totalPages }','${page.size }');
		</script>
	</jsp:attribute>
	<jsp:body>
	<button class="btn btn-default" onclick="window.location.href='<c:url value="/admin/user/add"/>'">添加</button>
		<table class="table table-bordered text-center mgt-2">
            <thead>
                <tr>
                    <th>账户</th>
                    <th>查看</th>
                    <th>修改</th>
                    <!-- <th>删除</th> -->
                </tr>
            </thead>                        
            <tbody>
            	<c:forEach items="${page.result }" var="t">
            	<tr id="${t.id }">
                     <td>${t.username }</td>
                     <td><a href="<c:url value='/admin/user/${t.id }'/>">查看</a></td>
                     <td><a href="<c:url value='/admin/user/${t.id }/edit'/>">修改</a></td>
                     <!-- <td><a onclick="del('user','${t.id }')">删除</a></td> -->
                 </tr>
            	</c:forEach>
            </tbody>
        </table>
		<ul id="pagination"></ul>
	</jsp:body>
</my:admin>