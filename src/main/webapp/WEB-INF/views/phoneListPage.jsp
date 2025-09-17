<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
        <%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
            <%@ taglib prefix="phone" tagdir="/WEB-INF/tags/phone" %>
                <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                    <common:page pageTitle="Phone list" showMenu="true">
                        <div class="row justify-content-center font-italic mb-3">
                            Found
                            <c:out value="${phones.totalElements}" /> results!
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Image</th>
                                    <th scope="col">Brand
                                        <util:sorting />
                                    </th>
                                    <th scope="col">Model
                                        <util:sorting />
                                    </th>
                                    <th scope="col">Price
                                        <util:sorting />
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
                                        <li class="page-item"><a class="page-link"
                                                href="?page=${paginationInfo.currentPage - 1}">Previous</a></li>
                                    </c:if>
                                    <c:forEach var="counter" begin="${1}" end="${paginationInfo.totalPages}">
                                        <c:if test="${paginationInfo.currentPage == counter}">
                                            <li class="page-item active"><a class="page-link"
                                                    href="?page=${counter}">${counter}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${paginationInfo.currentPage != counter}">
                                            <li class="page-item"><a class="page-link"
                                                    href="?page=${counter}">${counter}</a>
                                            </li>
                                        </c:if>

                                    </c:forEach>
                                    <c:if test="${paginationInfo.hasRightArrow}">
                                        <li class="page-item"><a class="page-link"
                                                href="?page=${paginationInfo.currentPage + 1}">Next</a></li>
                                    </c:if>

                                </ul>
                            </nav>
                        </div>
                    </common:page>