<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" type="java.lang.String" %>
<%@ attribute name="q" required="true" type="java.lang.String" %>
<%@ attribute name="priceFrom" required="true" type="java.lang.String" %>
<%@ attribute name="priceTo" required="true" type="java.lang.String" %>
<%@ attribute name="currentPage" type="java.lang.String" %>
<%@ attribute name="text" type="java.lang.String" %>

<c:url var="url" value="">
    <c:param name="page" value="${page}" />
    <c:if test="${not empty q || not empty priceFrom || not empty priceTo}">
        <c:param name="q" value="${q}" />
    </c:if>
    <c:if test="${not empty priceFrom}">
        <c:param name="priceFrom" value="${priceFrom}" />
    </c:if>
    <c:if test="${not empty priceTo}">
        <c:param name="priceTo" value="${priceTo}" />
    </c:if>
</c:url>
<c:if test="${currentPage == page}">
    <li class="page-item active">
        <a class="page-link" href="${url}">${text}</a>
    </li>
</c:if>
<c:if test="${currentPage != page}">
    <li class="page-item">
        <a class="page-link" href="${url}">${text}</a>
    </li>
</c:if>
