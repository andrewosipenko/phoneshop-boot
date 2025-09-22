<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <common:page pageTitle="Phone details" showMenu="true">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-10">
                            <div class="phone-card">
                                <div class="row align-items-center">
                                    <div class="col-md-5 text-center mb-4 mb-md-0">
                                        <img src='<c:url value="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.image}" />'
                                            class="phone-image img-fluid" width="300" alt="Phone image">
                                    </div>

                                    <div class="col-md-7">
                                        <div class="d-flex flex-column">
                                            <div class="mb-3">
                                                <p class="specs-title font-weight-bold">Brand:</p>
                                                <p class="specs-value">
                                                    <c:out value="${phone.brand}" />
                                                </p>
                                            </div>
                                            <div class="mb-3">
                                                <p class="specs-title font-weight-bold">Model:</p>
                                                <p class="specs-value">
                                                    <c:out value="${phone.model}" />
                                                </p>
                                            </div>
                                            <div class="mb-3">
                                                <p class="specs-title font-weight-bold">Price:</p>
                                                <p class="specs-value price-value">
                                                    <c:out value="${phone.price}" /> $
                                                </p>
                                            </div>
                                            <div class="mb-3">
                                                <p class="specs-title font-weight-bold">Description:</p>
                                                <div class="description-box">
                                                    <p>
                                                        <c:out value="${phone.description}" />
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <common:back />
                </div>
            </common:page>