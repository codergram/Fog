<%@ page import="domain.material.materials.Tree" %>
<%@ page import="domain.carport.Carport" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="order" value="${requestScope.order}"/>

<div class="row">
    <a href="javascript:history.back()" class="btn-danger">Tilbage til alle ordre</a>
</div>
<h2 class="mt-4 mb-4 text-center">Order ${order.id}</h2>
<br>
<br>
<br/> <br/>

Order id: ${order.id}<br>
Customer name: ${requestScope.order.customer.name}<br>
Salesperson: ${order.salesEmployee.name}<br>
Salgspris: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.carport.price + (order.margin/100) * order.carport.price}" /> kr<br>
Kostpris: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.carport.price}" /> kr<br>
Avance: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(order.carport.price + (order.margin/100) * order.carport.price) - order.carport.price}" /> kr<br>
Status: ${order.status.name()}
<br>
<br>

<div class="row">
    <p>
    <div class="col-md-12 text-center">
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#sideDrawing" aria-expanded="false" aria-controls="sideDrawing">Se tegning fra siden</button>
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#topDrawing" aria-expanded="false" aria-controls="topDrawing">Se tegning fra toppen</button>
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="true" aria-controls="sideDrawing topDrawing">Se begge</button>
    </div>
    </p>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="collapse multi-collapse" id="sideDrawing">
            <div style="text-align: center">
                ${requestScope.svgSide}
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="collapse multi-collapse" id="topDrawing">
            <div style="text-align: center">
                ${requestScope.svgTop}
            </div>
        </div>
    </div>
</div>

<div class="row">
    <table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <th>Materiale</th>
        <th>Længde</th>
        <th>Antal</th>
        <th>Enhed</th>
        <th>Beskrivelse</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.carport.partslist.partList}" var="part" varStatus="vs">
        <tr>
            <td>
               ${part.material.name}
            </td>
            <td>
                <!-- TODO: Fix -->
                <c:if test ="${part.material.getClass().name == 'Tree'}">
                ${part.material.length}
                </c:if>
            </td>
            <td>
                ${part.amount}
            </td>
            <td>
               ${part.material.unitString}
            </td>
            <td>
                ${part.description}
            </td>
        </tr>
        </c:forEach>
    </table>
</div>