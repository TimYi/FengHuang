<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="tab" %>
<ul class="nav nav-pills nav-stacked">
    <li <c:if test="${tab eq 'organization' }">class="active"</c:if>><a href="<c:url value='/admin/organization'/>">设计师组织</a></li>
    <li <c:if test="${tab eq 'designer' }">class="active"</c:if>><a href="<c:url value='/admin/designer'/>">设计师资料</a></li>
    <li <c:if test="${tab eq 'serviceLevel' }">class="active"</c:if>><a href="<c:url value='/admin/serviceLevel'/>">服务等级</a></li>
</ul>
