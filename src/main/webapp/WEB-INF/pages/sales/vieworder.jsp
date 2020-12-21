<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  ~ Copyright (c) 2020. Team CoderGram
  ~
  ~ @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
  ~ @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
  ~ @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
  --%>

<c:set var="order" value="${requestScope.order}"/>

<c:set var="req" value="${pageContext.request}"/>
<c:set var="url"
       value="${req.requestURL.substring(0,req.requestURL.indexOf(req.serverName))}${requestScope.domainname}${req.contextPath}"/>
<c:set var="customURL" value="${url}/ViewOrder/${order.uuid}"/>
<c:set var="downloadURL" value="${url}/GetPDF/${order.uuid}"/>

<br>
<h2 class="mt-4 mb-4 text-center">Order ${order.id}</h2>
<form action="Ordre/View/" method="POST">
    <div class="input-group mb-6">
        <input type="hidden" name="action" value="sendLink">
        <input type="hidden" name="redirect" value="viewOrder">
        <input type="hidden" name="ordrenummer" value="${order.id}"/>
        <input type="hidden" name="ordreurl" value="${customURL}"/>
        <input type="text" name="link" class="form-control text-center" aria-label="link"
               value="${customURL}" aria-describedby="button-link" readonly>
        <div class="input-group-append">
            <button class="btn btn-success" type="button" id="button-link" onclick="this.form.submit()">Send link til kunde</button>
        </div>
    </div>
</form>
<br>
<br>
<br/> <br/>

<div class="row text-center">
    <div class="col-6">
        <h5>Ordre information</h5>
        <b>Dato:</b> ${order.orderDate}<br>
        <b>Anmodet længde:</b> ${order.length}cm<br>
        <b>Anmodet bredde:</b> ${order.width}cm<br>
        <c:choose>
            <c:when test="${order.carport.hasShed()}">
                <b>Ønsker skur:</b> Ja<br>
                <b>Ønsket skurbredde:</b> ${order.carport.shed.width}cm<br>
                <b>Ønsket skurlængde:</b> ${order.carport.shed.length}cm<br>
            </c:when>
            <c:otherwise>
                <b>Ønsker skur:</b> Nej<br>
            </c:otherwise>
        </c:choose>
        <c:if test ="${order.hasSalesman()}">
            <b>Sælger:</b> ${order.salesEmployee.name}<br>
        </c:if>
        <br>
        <label for="statusselect">Status:</label>
        <div class="input-group mb-3">
            <form action="Ordre/View/" method="POST" style="margin: 0 auto;">
                <input type="hidden" name="action" value="changeStatus">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <select class="custom-select text-center" name="statusvalue" id="statusselect" aria-label="statusselect"  onchange="this.form.submit()">
                    <c:forEach items="${requestScope.statuslist}" var="status" varStatus="vs">
                        <option value="${status}"<c:if test ="${order.status.name() == status}"> selected</c:if><c:if test ="${!order.hasSalesman()}">disabled</c:if>>${status}</option>
                    </c:forEach>
                </select>
            </form>
        </div><br>
    </div>
    <div class="col-6">
        <h5>Kunde information</h5>
        <b>Navn:</b> ${order.customer.name}<br>
        <b>E-mail:</b> ${order.customer.email}<br>
        <b>Telefon:</b> ${order.customer.phoneNo}<br>
        <b>Addresse:</b> ${order.customer.address}<br>
        <b>By:</b> ${order.customer.city} ${order.customer.postalCode}<br>
    </div>
</div>

<br>
<div class="row">
<div class="col-2"></div>
<div class="col-8">
<div class="row">
    <form action="Ordre/View/" method="POST" style="width:100%;">
        <label for="salgspris">Salgspris</label>
        <div class="input-group mb-3">
                <input type="hidden" name="action" value="updatePrice">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <div class="input-group-prepend">
                    <span class="input-group-text">kr</span>
                </div>
                <input type="text" id="salgspris" name="salgspris"  class="form-control" aria-label="salgspris" value="${order.carport.nicePrice(order.carport.price + (order.margin/100) * order.carport.price)}" aria-describedby="button-salgspris" min="${order.carport.price * 1.15}" onkeyup="this.value=this.value.replace(/[^\d]/,'')" required>
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
        <input type="text" id="kostpris" class="form-control" aria-label="kostpris" value="${order.carport.nicePrice(order.carport.price)}" onkeyup="this.value=this.value.replace(/[^\d]/,'')" disabled>
    </div>
</div>
<div class="row">
        <div class="col-6">
        <label for="avance">Avance</label>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">kr</span>
            </div>
            <input type="text" id="avance" class="form-control" aria-label="salgspris" value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(order.carport.price + (order.margin/100) * order.carport.price) - order.carport.price}" />" disabled>
        </div>
        </div>
        <div class="col-6">
            <label for="margin">Dækningsgrad</label>
            <div class="input-group mb-3">
                <input type="text" id="margin" class="form-control text-right" aria-label="margin" value="<fmt:formatNumber type="number" value="${order.margin}" />" disabled>
                <div class="input-group-append">
                    <span class="input-group-text">%</span>
                </div>
            </div>
        </div>
</div>
        <c:if test ="${order.status.name() == 'Completed'}">
        <form action="Ordre/View/" method="POST">
            <div class="input-group mb-6">
                <input type="hidden" name="action" value="createPdf">
                <input type="hidden" name="redirect" value="viewOrder">
                <input type="hidden" name="ordrenummer" value="${order.id}" />
                <input type="hidden" name="downloadurl" value="${customURL}" />
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button" id="button-createpdf" onclick="this.form.submit()">Opret og send PDF</button>
                </div>
            </div>
        </form>
        </c:if>
</div>
<div class="col-2"></div>
</div>

<br>
<br>

<div class="row">
    <div class="col-md-12 text-center">
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#sideDrawing" aria-expanded="false" aria-controls="sideDrawing">Se tegning fra siden</button>
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#topDrawing" aria-expanded="false" aria-controls="topDrawing">Se tegning fra toppen</button>
        <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="true" aria-controls="sideDrawing topDrawing">Se begge</button>
    </div>

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
    <div class="col-md-1"></div>
    <div class="col-md-10">
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
    <div class="col-md-1"></div>

</div>