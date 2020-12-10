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

<br>
<br>
<div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="text-center">
                <form class="form-signin" action="Login" method="POST" >

                    <input type="hidden" name="target" value="login">

                    <input type="hidden" name="checkoutProcess" value="false">

                    <img class="mb-2" src="${pageContext.request.contextPath}/images/logo.png" alt="" width="72" >

                    <h1 class="h3 mb-3 font-weight-normal">Log Ind</h1>

                    <label for="inputEmail" class="sr-only">Email adresse</label>
                    <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="E-mailadresse" required=true autofocus=true <c:if test="${requestScope.providedMail != null}">value="${requestScope.providedMail}"</c:if>>
                    <br>
                    <label for="inputPassword" class="sr-only">Kodeord</label>
                    <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Kodeord" required=true>

                    <br>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Log Ind
                    </button>
                </form>
                <div>
                    <c:if test="${requestScope.error}">
                        <div class="alert alert-danger" role="alert">
                                ${requestScope.errorMsg}
                        </div>
                    </c:if>
                </div>
            </div>
            <a href="Shop">
                Tilbage
            </a>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>