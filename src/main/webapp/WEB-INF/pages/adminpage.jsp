<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 16/10/2020
  Time: 18.43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>

    <c:choose>
            <c:when test="${sessionScope.user.userRole == 'admin'}">

                <div class="row mt-4">
                    <div class="col-sm-12 col-md-2" style="border-right: 1px solid gray;">
                        <nav class="nav flex-column">
                            <a class="nav-link" href="AdminPage?target=addCustomer">Add Customer</a>
                            <a class="nav-link" href="AdminPage?target=allCustomers">All Customers</a>
                            <a class="nav-link" href="AdminPage?target=allOrders">All Orders</a>
                        </nav>
                    </div>

                    <div class="col-sm-12 col-md-10" style="">

                        <c:choose>
                            <c:when test="${requestScope.adminMenu == 'addCustomer'}">
                                <jsp:include page="../includes/addcustomer.jsp" flush="true"/>
                            </c:when>
                            <c:when test="${requestScope.adminMenu == 'allCustomers'}">
                                <jsp:include page="../includes/allcustomers.jsp" flush="true"/>
                            </c:when>
                            <c:when test="${requestScope.adminMenu == 'editCustomer'}">
                                <jsp:include page="../includes/editcustomer.jsp" flush="true"/>
                            </c:when>
                            <c:when test="${requestScope.adminMenu == 'allOrders'}">
                                <jsp:include page="../includes/allordersadmin.jsp" flush="true"/>
                            </c:when>
                            <c:when test="${requestScope.adminMenu == 'viewOrder'}">
                                <jsp:include page="../includes/view_order.jsp" flush="true"/>
                            </c:when>
                            <c:when test="${requestScope.adminMenu == 'orderHistory'}">
                                <jsp:include page="../includes/allordersfromcustomeradminpage.jsp" flush="true"/>
                            </c:when>
                            <c:otherwise>
                                <h1 class="text-center">Welcome: ${sessionScope.user.userEmail}</h1>
                                <br>
                                <br>
                                <p>In the admin area you can add, edit or delete customers. You can also view and delete orders, plus update order statuses.</p>
                            </c:otherwise>
                        </c:choose>

                    </div>

                </div>
                <br>
                <br>
                <br>

            </c:when>

            <c:otherwise>
                <script>
                    window.location = "${pageContext.request.contextPath}";
                </script>
            </c:otherwise>
        </c:choose>






<%@include file="../includes/footer.jsp"%>

