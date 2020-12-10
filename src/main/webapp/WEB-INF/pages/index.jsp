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

<h2 class="mt-4 mb-4 text-center">Bestil en lækker carport</h2>
    <br>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <a href="${pageContext.request.contextPath}/Shop">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_1.jpg" alt="Første slide">
                        </a>
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="${pageContext.request.contextPath}/Shop">Bestil Nu</a>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <a href="${pageContext.request.contextPath}/Shop">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_2.jpg" alt="Anden slide">
                        </a>
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="${pageContext.request.contextPath}/Shop">Bestil Nu</a>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Forrige</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Næste</span>
                </a>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>

