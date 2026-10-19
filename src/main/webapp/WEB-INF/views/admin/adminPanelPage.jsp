<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Admin panel" showMenu="true">
    <div class="text-center mt-4">
        <a class="btn btn-primary" href="<c:url value='/admin/users'/>">User listing</a>
    </div>
</common:page>
