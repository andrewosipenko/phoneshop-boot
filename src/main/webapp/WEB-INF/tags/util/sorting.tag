<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="field" required="true" type="java.lang.String" %>
<%@ attribute name="currentSort" required="true" type="java.lang.String" %>
<%@ attribute name="q" required="true" type="java.lang.String" %>
<%@ attribute name="priceFrom" required="true" type="java.lang.String" %>
<%@ attribute name="priceTo" required="true" type="java.lang.String" %>
<c:set var="sortAsc" value="${field}-asc" />
<c:set var="sortDesc" value="${field}-desc" />

<c:url var="urlAsc" value="">
    <c:param name="sort" value="${sortAsc}" />
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
<c:url var="urlDesc" value="">
    <c:param name="sort" value="${sortDesc}" />
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

<c:set var="upperArrow" value="&#x25B2;"/>
<c:set var="downArrow" value="&#x25BC;"/>
<c:url var="sortUrl" value="/phones"/>
<c:if test="${sortAsc == currentSort}">
    <a><c:out value="${upperArrow}" escapeXml="false"/></a>
</c:if>
<c:if test="${sortAsc != currentSort}">
    <a href="${urlAsc}"><c:out value="${upperArrow}" escapeXml="false"/></a>
</c:if>
<c:if test="${sortDesc == currentSort}">
    <a><c:out value="${downArrow}" escapeXml="false"/></a>
</c:if>
<c:if test="${sortDesc != currentSort}">
    <a href="${urlDesc}"><c:out value="${downArrow}" escapeXml="false"/></a>
</c:if>