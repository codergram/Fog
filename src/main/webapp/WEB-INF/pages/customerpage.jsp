<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 21/10/2020
  Time: 16.21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
<%@include file="../includes/navigation.jsp"%>


    <div class="row mt-4">
        <div class="col-sm-12 col-md-2" style="border-right: 1px solid gray;">
            <nav class="nav flex-column">
                <a class="nav-link" href="CustomerPage?target=viewProfile">View Profile</a>
                <a class="nav-link" href="CustomerPage?target=orderHistory">Order History</a>
            </nav>
        </div>

        <div class="col-sm-12 col-md-10" style="">

            <c:choose>
                <c:when test="${requestScope.profileMenu == 'viewProfile'}">
                    <jsp:include page="../includes/view_profile.jsp" flush="true"/>
                </c:when>
                <c:when test="${requestScope.profileMenu == 'orderHistory'}">
                    <jsp:include page="../includes/allorderscustomer.jsp" flush="true"/>
                </c:when>
                <c:when test="${requestScope.profileMenu == 'viewOrder'}">
                    <jsp:include page="../includes/view_order.jsp" flush="true"/>
                </c:when>
                <c:otherwise>
                    <h1 class="text-center">Welcome: ${sessionScope.user.userEmail}</h1>
                    <br>
                    <br>
                    <p>In the profile area you can see your profile and credit. You can also view your order history and see order statuses.</p>
                </c:otherwise>
            </c:choose>

            <div>
                <c:if test ="${requestScope.errorMessage != null}">
                    <div class="alert alert-danger" style="padding-bottom: inherit;">
                        <p>${requestScope.errorMessage}</p>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
    <br>
    <br>
    <br>



<%@include file="../includes/footer.jsp"%>