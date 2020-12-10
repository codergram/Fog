<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%--
  ~ Copyright (c) 2020. Team CoderGram
  ~
  ~ @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
  ~ @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
  ~ @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
  --%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${fn:replace(req.requestURL, fn:substring(req.requestURI, 0, fn:length(req.requestURI)), req.contextPath)}" />
<c:set var="customURL" value="${baseURL}/ViewOrder/" />
<div class="row">
    <div class="col-md-12">
        <h2 class="mt-6 mb-6 text-center">Tegning af din helt egen carport herunder</h2>
        <br>
        <div class="row">
            <p>
                <div class="col-md-12 text-center">
                    <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#sideDrawing" aria-expanded="false" aria-controls="sideDrawing">Se tegning fra siden</button>
                    <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#topDrawing" aria-expanded="false" aria-controls="topDrawing">Se tegning fra toppen</button>
                    <button class="btn btn-outline-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="sideDrawing topDrawing">Se begge</button>
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
        <div>


        </div>
        <br>
        <h3>Indtast dine oplysninger</h3>
        <form action="${pageContext.request.contextPath}/Confirmation" method="POST">
            <input type="hidden" name="orderurl" value="${customURL}">
            <div class="form-group">
                <label for="name">Dit navn</label>
                <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" required>
            </div>
            <div class="form-group">
                <label for="email">Din email</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <label for="address">Adresse</label>
                <input type="text" class="form-control" id="address" name="address" aria-describedby="addressHelp" required>
            </div>
            <div class="form-group">
                <label for="zip">Post nummer</label>
                <input type="number" class="form-control" id="zip" name="zip" maxlength="4" required>
            </div>
            <div class="form-group">
                <label for="city">By</label>
                <input type="text" class="form-control" id="city" name="city" aria-describedby="addressHelp" required>
            </div>
            <div class="form-group">
                <label for="phone">Dit telefon nummer</label>
                <input type="tel" class="form-control" id="phone" pattern="[0-9]{8}" name="phone" required>
            </div>

            <div style="text-align: center">
            <button type="submit" class="btn btn-primary">Send forespørgelse</button>
            </div>
        </form>
    </div>
    <div class="col-md-2"></div>
</div>


