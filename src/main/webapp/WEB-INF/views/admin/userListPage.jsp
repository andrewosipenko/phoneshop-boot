<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="User listing page" showMenu="true">
    <div class="row mb-3">
        <a class="btn btn-primary" href="<c:url value='/admin'/>">&lt;&lt; Back to admin panel</a>
    </div>
    <div class="row justify-content-center font-italic mb-3">
        There are currently <c:out value="${users.totalElements}"/> users registered!
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Location</th>
            <th scope="col">Date registered</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users.content}" varStatus="status">
            <tr>
                <td>${users.number * users.size + status.count}</td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.location}"/></td>
                <td><c:out value="${user.registeredAt}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item ${pagination.hasPrevious ? '' : 'disabled'}">
                <a class="page-link" href="?page=${pagination.previousPage}">&lt;&lt;</a>
            </li>
            <c:forEach begin="${pagination.first}" end="${pagination.last}" var="p">
                <li class="page-item ${p == pagination.currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${p}">${p}</a>
                </li>
            </c:forEach>
            <li class="page-item ${pagination.hasNext ? '' : 'disabled'}">
                <a class="page-link" href="?page=${pagination.nextPage}">&gt;&gt;</a>
            </li>
        </ul>
    </nav>
</common:page>
