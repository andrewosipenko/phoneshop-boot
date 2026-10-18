<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageNumber" required="true" type="java.lang.Integer" %>
<c:url value="/phones">
    <c:param name="page" value="${pageNumber}"/>
    <c:if test="${param.query != null}"><c:param name="q" value="${param.query}"/></c:if>
    <c:if test="${not empty param.priceFrom}"><c:param name="priceFrom" value="${param.priceFrom}"/></c:if>
    <c:if test="${not empty param.priceTo}"><c:param name="priceTo" value="${param.priceTo}"/></c:if>
</c:url>
