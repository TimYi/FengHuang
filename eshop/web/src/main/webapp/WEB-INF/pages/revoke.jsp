<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>revoke</title>
</head>
<body>
	<script type="text/javascript">
		<c:if test='${result}'>var url="http://www.ifhzj.com/api/payend?result=true&orderId=${orderid}"</c:if>
		<c:if test='${!result}'>var url="http://www.ifhzj.com/api/payend?result=false&reason=${reason}"</c:if>
		window.location.href=url;
	</script>
</body>
</html>