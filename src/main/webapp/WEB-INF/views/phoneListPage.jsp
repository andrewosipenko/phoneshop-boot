<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="phone" tagdir="/WEB-INF/tags/phone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:page pageTitle="Phone list" showMenu="true">
    <div class="row justify-content-center font-italic mb-3">
        Found <c:out value="${phones.totalElements}"/> results!
    </div>
    <form method="get" action="<c:url value='/phones'/>" class="border rounded p-3">
        <label for="query" class="form-label">Search Term</label>
        <input type="text" name="query" value="<c:out value='${param.query}'/>"/>
        <label for="priceFrom" class="form-label">Price From</label>
        <input type="text" name="priceFrom" value="<c:out value='${param.priceFrom}'/>"/>
        <label for="priceTo" class="form-label">Price To</label>
        <input type="text" name="priceTo" value="<c:out value='${param.priceTo}'/>"/>
        <button type="submit" class="btn btn-primary btn-sm">Search</button>
    </form>
    <a href="<c:url value='/phones'/>">Clear</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Image</th>
            <th scope="col">Brand <util:sorting field="brand"/></th>
            <th scope="col">Model <util:sorting field="model"/></th>
            <th scope="col">Price <util:sorting field="price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="phone" items="${phones.content}">
            <phone:tile phone="${phone}"/>
        </c:forEach>
        </tbody>
    </table>
    <nav>
      <p>Page ${pagination.currentPage} of ${pagination.totalPages}</p>

      <ul class="pagination">
        <li class="page-item ${pagination.hasPrevious ? '' : 'disabled'}">
          <a class="page-link"
             href="<util:pageUrl pageNumber="${pagination.previousPage}"/>">Previous</a>
        </li>

        <c:forEach begin="${pagination.first}" end="${pagination.last}" var="p">
          <li class="page-item ${p == pagination.currentPage ? 'active' : ''}">
            <a class="page-link" href="<util:pageUrl pageNumber="${p}"/>">${p}</a>
          </li>
        </c:forEach>

        <li class="page-item ${pagination.hasNext ? '' : 'disabled'}">
          <a class="page-link"
             href="<util:pageUrl pageNumber="${pagination.nextPage}"/>">Next</a>
        </li>
      </ul>
    </nav>
</common:page>