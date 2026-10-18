<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="field" required="true" %>
<%@ attribute name="order" required="true" %>
<%@ attribute name="arrow" required="true" %>
<c:choose>
    <c:when test="${param.sort == field && param.order == order}">
        <span class="text-dark"><c:out value="${arrow}" escapeXml="false"/></span>
    </c:when>
    <c:otherwise>
        <c:url var="sortUrl" value="/phones">
            <c:if test="${param.query != null}"><c:param name="query" value="${param.query}"/></c:if>
            <c:if test="${not empty param.priceFrom}"><c:param name="priceFrom" value="${param.priceFrom}"/></c:if>
            <c:if test="${not empty param.priceTo}"><c:param name="priceTo" value="${param.priceTo}"/></c:if>
            <c:param name="sort" value="${field}"/>
            <c:param name="order" value="${order}"/>
        </c:url>
        <a href="${sortUrl}"><c:out value="${arrow}" escapeXml="false"/></a>
    </c:otherwise>
</c:choose>
