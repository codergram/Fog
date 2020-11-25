<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <h2 class="mt-4 mb-4 text-center">Bestil en lækker carport</h2>
    <br>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-part active">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_1.jpg" alt="Første slide">
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="Shop">Bestil Nu</a>
                        </div>
                    </div>
                    <div class="carousel-part">
                        <img class="d-block w-100" src="${pageContext.request.contextPath}/images/carport_2.jpg" alt="Anden slide">
                        <div class="carousel-caption d-none d-md-block">
                            <a class="btn btn-light" href="Shop">Bestil Nu</a>
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

