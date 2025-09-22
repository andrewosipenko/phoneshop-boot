<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-4 offset-9">
        Welcome, ${requestScope.currentUser.login}
        <c:if test="${requestScope.currentUser.login == 'guest'}">
            <a href="<c:url value="/login"/>">
                <button type="button" class="btn btn-primary">Login</button>
            </a>
        </c:if>
        <c:if test="${requestScope.currentUser.login != 'guest'}">
            <form id="logoutForm" method="GET" action="/logout">
                <button type="submit" class="btn btn-primary">Log out</button>
            </form>
        </c:if>
        
    </div>
    <div class="col-4 offset-9">
        <a href="<c:url value="/admin"/>">
            <p>Admin panel</p>
        </a>
    </div>
</div>