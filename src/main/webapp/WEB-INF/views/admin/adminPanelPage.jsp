<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <common:page pageTitle="Admin panel" showMenu="false">
                <div class="row mb-3">
                    <common:back />
                </div>
                <div class="row justify-content-center">
                    <h1 class="text-center"><i class="me-2"></i>User Information</h1>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th><i class="me-1"></i> Login</th>
                                    <th><i class="me-1"></i> Avatar</th>
                                    <th><i class=" me-1"></i> Location</th>
                                    <th><i class="me-1"></i> Company</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>
                                            <c:out value="${user.login}" />
                                        </td>
                                        <td><img src="${user.avatarUrl}" class="avatar" alt="Without image"></td>
                                        <td>
                                            <c:out value="${user.location}" />
                                        </td>
                                        <td>
                                            <c:out value="${user.company}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </common:page>