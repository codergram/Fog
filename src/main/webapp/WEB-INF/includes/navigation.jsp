<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" %>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-button"
     style="background-color:#004687;color:#ffffff;width:100%;">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">
            <img src="${pageContext.request.contextPath}/images/logo.png" class="img-fluid" alt="Responsive image">
        </a>
        <button data-toggle="collapse" data-target="#navcol-1" class="navbar-toggler">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="nav navbar-nav ml-auto">
                <c:forEach var="i" items="${requestScope.navbar.items}">
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${i.active}">active</c:if>" style="color:#ffffff;" href="<c:url value="${i.url}"/>">
                            <i class="fa fa-${i.icon}"></i>
                                ${i.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>