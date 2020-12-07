<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="mt-4 mb-4 text-center">Kundeliste</h2>
<br>
<br>
<br/> <br/>
<table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
                <th>Navn</th>
                <th>E-mail</th>
                <th>Telefon</th>
                <th>Addresse</th>
                <th>By</th>
                <th>Postnummer</th>
        </thead>
        <tbody>
                <c:forEach items="${requestScope.customerlist}" var="customer" varStatus="vs">
                        <tr>
                                <td>${customer.name}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phoneNo}</td>
                                <td>${customer.address}</td>
                                <td>${customer.city}</td>
                                <td>${customer.postalCode}</td>
                        </tr>
                </c:forEach>
</table>