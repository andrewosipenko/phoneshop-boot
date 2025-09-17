<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <common:page pageTitle="Phone details" showMenu="true">

                <div class="row justify-content-md-center">
                    <div class="d-flex bd-highlight">
                        <div class="p-2 flex-fill bd-highlight"><img
                                src='<c:url value="
                        https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}" />' width="300" height="300"
                                alt="Phone image"></div>
                        <div class="p-2 flex-fill bd-highlight">
                            <div class="d-flex flex-column bd-highlight mb-3">
                                <div class="p-2 bd-highlight">
                                    <p class="font-weight-bold">Brand:</p>
                                    <c:out value="${phone.brand}" />
                                </div>
                                <div class="p-2 bd-highlight">
                                    <p class="font-weight-bold">Model:</p>
                                    <c:out value="${phone.model}" />
                                </div>
                                <div class="p-2 bd-highlight">
                                    <p class="font-weight-bold">Price:</p>
                                    <c:out value="${phone.price}" /> $
                                </div>
                                <div class="p-2 bd-highlight col-5">
                                    <p class="font-weight-bold">Description:</p>
                                    <c:out value="${phone.description}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <common:back />
                </div>
            </common:page>