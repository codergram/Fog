<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container">
    <%@include file="../includes/navigation.jsp"%>
    <br>
    <br>
    <h2 class="mt-4 mb-4 text-center">Bestil en lækker carport</h2>

    <br>

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_1.jpg" alt="Første slide">
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="">Bestil Nu</a>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_2.jpg" alt="Anden slide">
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="">Bestil Nu</a>
                        </div>
                    </div>
<%--                    <c:forEach var="item" items="${requestScope.getAllItemsFromDB}" varStatus="rowNumber">--%>
<%--                        <c:if test="${rowNumber.index == 0 }" >--%>
<%--                            <div class="carousel-item active">--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${rowNumber.index > 0 }" >--%>
<%--                            <div class="carousel-item">--%>
<%--                        </c:if>--%>
<%--                            <a href="ItemPage?item_id=${item.item_id}">--%>
<%--                            <img class="d-block w-100"  src="${pageContext.request.contextPath}/images/${item.item_image}.jpg" alt="${item.item_image}">--%>
<%--                            </a>--%>
<%--                            <div class="carousel-caption d-none d-md-block">--%>
<%--                                <a class="btn btn-light" href="ItemPage?item_id=${item.item_id}">Buy Now</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Forrige</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Næste</span>
                </a>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
    <br>
    <br>
<%@include file="../includes/footer.jsp"%>

