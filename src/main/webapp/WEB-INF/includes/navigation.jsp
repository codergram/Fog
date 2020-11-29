<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<ul class="nav justify-content-end" style="margin-top: -50px;">
    <c:forEach var="i" items="${requestScope.navbar.items}">
        <li class="nav-part <c:if test="${i.active}">active</c:if>">
            <a class="nav-link whiteLink"
               href="<c:url value="${i.url}"/>">
                    ${i.name}
                        <c:if test="${i.active}">
                            <span class="sr-only">(current)</span>
                        </c:if>
            </a>
        </li>
    </c:forEach>
</ul>
<br>
<br>