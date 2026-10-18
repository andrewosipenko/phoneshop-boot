<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-4 offset-9">
        <c:choose>
            <c:when test="${not empty pageContext.request.userPrincipal}">
                Hello, dear  <b><c:out value="${pageContext.request.userPrincipal.name}"/></b>!
                <form method="post" action="<c:url value='/logout'/>" class="d-inline">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm">Logout</button>
                </form>
            </c:when>
            <c:otherwise>
                Hello, dear  <b>guest</b>!
                <a class="btn btn-primary btn-sm" href="<c:url value='/login'/>">Login</a>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="col-4 offset-9">
        <a href="<c:url value="/admin"/>">
            <p>Admin panel</p>
        </a>
    </div>

</div>