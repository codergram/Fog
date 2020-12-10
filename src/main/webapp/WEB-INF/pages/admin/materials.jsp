<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%--
  ~ Copyright (c) 2020. Team CoderGram
  ~
  ~ @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
  ~ @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
  ~ @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
  --%>

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