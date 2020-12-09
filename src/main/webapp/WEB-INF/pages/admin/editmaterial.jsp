<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<c:set var="material" value="${requestScope.material}"/>
<c:set var="usages" value="${requestScope.usages}"/>
<c:set var="types" value="${requestScope.types}"/>
<c:set var="units" value="${requestScope.units}"/>
<c:set var="req" value="${pageContext.request}" />
<c:set var="usedUsage" value="${requestScope.usedUsage}" />


<div class="row">
    <a href="javascript:history.back()" class="btn-danger">Tilbage til alle materialer</a>
</div>
<h2 class="mt-4 mb-4 text-center">${material.name}</h2>
<br>
<br>
<br/> <br/>

<div class="row">
    <div class="col-6">
        <form action="Materials/Edit/" method="POST">
            <input type="hidden" name="materialid" value="${material.id}">
            <div class="row">
                <label for="navn">Navn:</label>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Materiale navn" aria-label="Navn" aria-describedby="navn" id="navn" name="matName" value="${material.name}">
                </div>
            </div>
            <div class="row">
                <label for="pris">Pris:</label>
                <div class="input-group mb-3">
                    <input type="number" min="0" step=any class="form-control" placeholder="Pris" aria-label="Pris" aria-describedby="pris" name="matPrice" value="${material.price}">
                    <span class="input-group-text" id="pris">kr</span>
                </div>
            </div>
            <div class="row">
                <label for="unit">Enhed:</label>
                <select class="form-select" id="unit" aria-label="unit" name="matUnit">
                    <c:forEach items="${units}" var="unit" varStatus="vs">
                            <option value="${unit}" <c:if test ="${unit == material.unit.name()}"> selected</c:if>>${unit}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row">
                <button class="btn btn-primary" type="submit">Gem ændringer</button>
                <c:if test="${requestScope.alert} && ${requestScope.alertType == 'success'}">
                    <div class="alert alert-${requestScope.alertType}" role="alert">
                        ${requestScope.alertMsg}
                    </div>
                </c:if>
            </div>
        </form>
    </div>
</div>