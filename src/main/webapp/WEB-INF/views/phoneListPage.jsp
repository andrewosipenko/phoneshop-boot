<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
        <%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
            <%@ taglib prefix="phone" tagdir="/WEB-INF/tags/phone" %>
                <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                    <common:page pageTitle="Phone list" showMenu="true">
                        <div class="search-container">
                            <h2 class="form-title"><i class="bi bi-search me-2"></i>Advanced Search</h2>
                            <form action="/phones">
                                <div class="mb-4">
                                    <label for="query" class="form-label">Search Term</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-search"></i></span>
                                        <input type="text" class="form-control" id="query" name="q"
                                            placeholder="Enter your search terms..." value="${param.q}">
                                    </div>
                                </div>

                                <div class="mb-4">
                                    <label for="priceFrom" class="form-label">Price From</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-tag"></i></span>
                                        <input type="text" class="form-control" id="priceFrom" name="priceFrom"
                                            placeholder="Enter price from" value="${param.priceFrom}">
                                    </div>
                                </div>

                                <div class="mb-4">
                                    <label for="priceTo" class="form-label">Price To</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="bi bi-calendar"></i></span>
                                        <input type="text" class="form-control" id="priceTo" name="priceTo"
                                            placeholder="Enter price to" value="${param.priceTo}">
                                    </div>
                                </div>

                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="bi bi-search me-2"></i>Search
                                    </button>
                                </div>
                            </form>
                        </div>
                        </div>
                        <div class="row justify-content-center font-italic mb-3">
                            Found
                            <c:out value="${phones.totalElements}" /> results!
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Image</th>
                                    <th scope="col">Brand
                                        <util:sorting q="${param.q}" priceFrom="${param.priceFrom}"
                                            priceTo="${param.priceTo}" field="brand" currentSort="${param.sort}" />
                                    </th>
                                    <th scope="col">Model
                                        <util:sorting q="${param.q}" priceFrom="${param.priceFrom}"
                                            priceTo="${param.priceTo}" field="model" currentSort="${param.sort}" />
                                    </th>
                                    <th scope="col">Price
                                        <util:sorting q="${param.q}" priceFrom="${param.priceFrom}"
                                            priceTo="${param.priceTo}" field="price" currentSort="${param.sort}" />
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="phone" items="${phones.content}">
                                    <phone:tile phone="${phone}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-flex justify-content-center">
                            <nav>
                                <ul class="pagination">
                                    <c:if test="${paginationInfo.hasLeftArrow}">
                                        <common:paginationUrl q="${param.q}" page="${counter}"
                                            priceFrom="${param.priceFrom}" priceTo="${param.priceTo}" text="Previous" />
                                    </c:if>
                                    <c:forEach var="counter" begin="${1}" end="${paginationInfo.totalPages}">
                                        <common:paginationUrl q="${param.q}" page="${counter}"
                                            priceFrom="${param.priceFrom}" priceTo="${param.priceTo}"
                                            currentPage="${paginationInfo.currentPage}" text="${counter}" />
                                    </c:forEach>
                                    <c:if test="${paginationInfo.hasRightArrow}">
                                        <common:paginationUrl q="${param.q}" page="${counter}"
                                            priceFrom="${param.priceFrom}" priceTo="${param.priceTo}" text="Next" />
                                    </c:if>

                                </ul>
                            </nav>
                        </div>
                    </common:page>