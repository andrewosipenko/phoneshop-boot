<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Phone details" showMenu="true">
    <div class="row mb-3">
        <common:back/>
    </div>


 <div class="row justify-content-center">
        <div class="col-md-3 text-center">
            <c:if test="${not empty phone.image}">
                <img class="img-fluid"
                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}"
                     alt="<c:out value='${phone.brand} ${phone.model}'/>"/>
            </c:if>
        </div>

        <div class="col-md-4">
            <p><strong>Brand:</strong> <c:out value="${phone.brand}"/></p>
            <p><strong>Model:</strong> <c:out value="${phone.model}"/></p>
            <p>
                <strong>Price:</strong>
                <c:choose>
                    <c:when test="${not empty phone.price}">
                        <fmt:formatNumber value="${phone.price}" type="number"
                                          minFractionDigits="2" maxFractionDigits="2"
                                          groupingUsed="false"/> $
                    </c:when>
                    <c:otherwise>not available</c:otherwise>
                </c:choose>
            </p>
            <p><strong>Description:</strong> <c:out value="${phone.description}"/></p>
        </div>
    </div>
</common:page>
