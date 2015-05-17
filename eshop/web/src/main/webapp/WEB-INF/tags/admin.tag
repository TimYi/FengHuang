<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="tab" %>
<%@ attribute name="nav" fragment="true" %>
<%@ attribute name="script" fragment="true" %>
<%@ attribute name="style" fragment="true" %>
<%@ attribute name="htmlClass" %>
<!DOCTYPE html>
<html <c:if test="${!empty htmlClass }">class="${htmlClass }"</c:if>>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>backetend</title>
    <link href="<c:url value='/resources/css/app.css'/>" type="text/css" rel="stylesheet"/>
    <jsp:invoke fragment="style"></jsp:invoke>
</head>
<body>	
    <div class="container-fluid">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value='/admin'/>">后台管理</a>
            </div>
            <div>
                <ul class="nav nav-tabs">
                    <li <c:if test="${tab eq 'authentication' }">class="active"</c:if>><a href="<c:url value='/admin/authentication'/>">权限管理</a></li>
                    <li <c:if test="${tab eq 'enter' }">class="active"</c:if>><a href="<c:url value='/admin/enter'/>">数据录入</a></li>
                </ul>
            </div>
        </nav>
        <div class="row">
            <div class="col-sm-2">
            	<jsp:invoke fragment="nav"/>                
            </div>
            <div class="col-sm-10">
            	<jsp:doBody/>
            </div>            
        </div>  
    </div>

    
    <script src="<c:url value='/resources/js/jquery-1.11.1.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootbox.min.js'/>"></script>
    <script src="<c:url value='/resources/js/art-template.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/kindeditor.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/autoheight.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/lang/zh_CN.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap-datepicker.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
    <script src="<c:url value='/resources/js/admin.js'/>"></script>
    <jsp:invoke fragment="script"></jsp:invoke>
</body>
</html>
