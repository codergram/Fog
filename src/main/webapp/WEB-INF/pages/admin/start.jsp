<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <c:choose>
            <c:when test="${sessionScope.userrole == 'Admin'}">
                <!-- ADMIN HTML HER -->>
                <h1>Hej admin!</h1>
                <h3>Current user object:</h3>
                <p>${sessionScope.user}</p>
            </c:when>
            <c:otherwise>
                <script>
                    window.location = "${pageContext.request.contextPath}";
                </script>
            </c:otherwise>
        </c:choose>