<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="order" value="${requestScope.order}"/>

<h2 class="mt-4 mb-4 text-center">Order ${order.id}</h2>
<br>
<br>
<br/> <br/>

Order id: ${order.id}<br>
Customer name: ${requestScope.order.customer.name}<br>
Salesperson: ${order.salesEmployee.name}<br>
Salgspris: ${order.carport.price + (order.margin/100) * order.carport.price}<br>
Kostpris: ${order.carport.price}<br>
Status: ${order.status.name()}

<br>
<br>
<a href="<c:url value="${pageContext.request.contextPath}"/><Tilbage til alle ordre</a>

</body>
</html>
