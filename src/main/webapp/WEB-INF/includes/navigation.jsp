<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!--    Nav bar-->
<%--<ul class="nav justify-content-end backgroundImageHeader paddingHeader img-fluid" style="height: 126px;">--%>
<ul class="nav justify-content-end" style="margin-top: -50px;">
    <!--        Visible to non logged in users-->
    <li class="nav-item">
        <a class="nav-link whiteLink" href="Shop">Hjem</a>
    </li>
    <c:choose>
        <c:when test="${sessionScope.user.userRole == 'customer'}">

        </c:when>
        <c:when test="${sessionScope.user.userRole == 'admin'}">

        </c:when>
        <c:otherwise>
            <li class="nav-item">
                <a class="nav-link whiteLink" href="Login">Log Ind</a>
            </li>
            <li class="nav-item">
                <a class="nav-link whiteLink" href="Register">Opret Profil</a>
            </li>
        </c:otherwise>
    </c:choose>
    <!--        Visible to logged in users-->
    <c:if test="${sessionScope.user.userRole == 'admin' || sessionScope.user.userRole == 'customer' }">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle whiteLink" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    ${sessionScope.user.userEmail}
            </a>
            <c:if test="${sessionScope.user.userRole == 'admin'}">
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="AdminPage?target=">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                        </svg>
                        Admin
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-box-arrow-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                        </svg>
                        Log Ud
                    </a>
                </div>
            </c:if>
            <c:if test="${sessionScope.user.userRole == 'customer' }">
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="CustomerPage?target=">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                        </svg>
                        Profil
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px" class="bi bi-box-arrow-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                        </svg>
                        Log Ud
                    </a>
                </div>
            </c:if>
        </li>
    </c:if>

<%--    Cart--%>
    <li class="nav-item">
        <a class="nav-link whiteLink" href="${pageContext.request.contextPath}/Show_Cart">
            <svg width="1em" height="1em" viewBox="0 0 16 16" style="margin-top: -3px"  class="bi bi-cart3" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
            </svg>
            <c:choose>
                <c:when test="${sessionScope.shoppingCart == null}">
                    (0)
                </c:when>
                <c:otherwise>
                    (${sessionScope.shoppingCart.size()})
                </c:otherwise>
            </c:choose>
        </a>
    </li>
</ul>


