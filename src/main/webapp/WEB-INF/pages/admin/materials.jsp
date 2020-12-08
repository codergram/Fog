<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="mt-4 mb-4 text-center">Materialer</h2>
<br>
<br>
<br/> <br/>
<table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <th>Navn</th>
        <th>Pris</th>
        <th>Enhed</th>
        <th class="no-sort">Handling</th>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.materiallist}" var="material" varStatus="vs">
            <tr>
                <td>${material.name}</td>
                <td>${material.price} kr</td>
                <td>${material.unitString}</td>
                <td>
                    <a href="Materials/Edit/${material.id}" class="btn btn-primary">
                        Ændre
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>