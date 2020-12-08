<%@ page import="domain.material.materials.Tree" %>
<%@ page import="domain.carport.Carport" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="order" value="${requestScope.order}"/>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${fn:replace(req.requestURL, fn:substring(req.requestURI, 0, fn:length(req.requestURI)), req.contextPath)}" />
<c:set var="customURL" value="${baseURL}/ViewOrder/${order.uuid}" />
<c:set var="downloadURL" value="${baseURL}/GetPDF/${order.uuid}" />

<div class="row">
    <a href="javascript:history.back()" class="btn-danger">Tilbage til alle ordre</a>
</div>
<h2 class="mt-4 mb-4 text-center">Order ${order.id}</h2>
<br>
<br>
<br/> <br/>

<div class="row">
    <div class="col-4">
        <h5>Ordre information</h5>
        Kunde: ${requestScope.order.customer.name}<br>
        Sælger: ${order.salesEmployee.name}<br>
        <br>
        <label for="status">Status:</label>
        <div class="input-group mb-3">
            <form action="Ordre/View/" method="POST">
                <input type="hidden" name="action" value="changeStatus">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <select class="custom-select" name="statusvalue" id="status" aria-label="status"  onchange="this.form.submit()">
                    <c:forEach items="${requestScope.statuslist}" var="status" varStatus="vs">
                        <option value="${status}"<c:if test ="${order.status.name() == status}"> selected</c:if><c:if test ="${!order.hasSalesman()}">disabled</c:if>>${status}</option>
                    </c:forEach>
                </select>
            </form>
        </div>

    </div>
    <div class="col-8 my-auto">
        <form action="Ordre/View/" method="POST">
            <div class="input-group mb-6">
                <input type="hidden" name="action" value="sendLink">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <input type="hidden" name="ordreurl" value="${customURL}" />
                <input type="text" name="link" class="form-control" aria-label="link" value="${customURL}" aria-describedby="button-link" readonly>
                <div class="input-group-append">
                    <button class="btn btn-success" type="button" id="button-link" onclick="this.form.submit()">Send link til kunde</button>
                </div>

                <c:if test="${requestScope.alert} && ${requestScope.alertType == 'success'}">
                    <div class="alert alert-${requestScope.alertType}" role="alert">
                            ${requestScope.alertMsg}
                    </div>
                </c:if>
            </div>
        </form>
    </div>

</div>



<br>

<div class="row">
    <div class="col-4">
<form action="Ordre/View/" method="POST">
<label for="salgspris">Salgspris</label>
<div class="input-group mb-3">

        <input type="hidden" name="action" value="updatePrice">
        <input type="hidden" name="redirect" value="viewOrder">
        <input type="hidden" name="ordrenummer" value="${order.id}" />
        <div class="input-group-prepend">
            <span class="input-group-text">kr</span>
        </div>
        <input type="number" id="salgspris" name="salgspris" class="form-control" aria-label="salgspris" value="<fmt:formatNumber type="number" pattern="###.###" value="${order.carport.price + (order.margin/100) * order.carport.price}"/>" aria-describedby="button-salgspris" min="${order.carport.price * 1.15}" required>
        <div class="input-group-append">
            <button class="btn btn-success" type="button" id="button-salgspris" onclick="this.form.submit()">Gem pris</button>
        </div>
    <c:if test="${requestScope.alert && requestScope.alertType == 'alert'}">
        <div class="alert alert-${requestScope.alertType}" role="alert">
                ${requestScope.alertMsg}
        </div>
    </c:if>
</div>
</form>

<label for="kostpris">Kostpris</label>
<div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text">kr</span>
    </div>
    <input type="number" id="kostpris" class="form-control" aria-label="salgspris" value="<fmt:formatNumber type="number" pattern="###.###" value="${order.carport.price}"/>" disabled>
</div>

        <label for="avance">Avance</label>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">kr</span>
            </div>
            <input type="text" id="avance" class="form-control" aria-label="salgspris" value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(order.carport.price + (order.margin/100) * order.carport.price) - order.carport.price}" />" disabled>
        </div>

        <form action="Ordre/View/" method="POST">
            <div class="input-group mb-6">
                <input type="hidden" name="action" value="createPdf">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <input type="hidden" name="downloadurl" value="${downloadURL}" />
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button" id="button-createpdf" onclick="this.form.submit()">Opret og send PDF</button>
                </div>
            </div>
        </form>

    </div>
</div>

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
<br><br>
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
<br><br><br>
<div class="row">
    <table id="example" name="stykliste" class="table table-striped table-bordered" style="width:100%">
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