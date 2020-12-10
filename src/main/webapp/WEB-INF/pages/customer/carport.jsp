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

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="mt-4 mb-4 text-center">Sammensæt din helt egen carport herunder</h2>
        <br>
        <form class="form-carport" action="Confirmation" method="GET">
            <div class="form-group">
                <label for="length">Carportens længde</label>
                <select class="form-control" id="length" name="length" required>
                    <c:forEach begin="580" end="780" step="10" varStatus="lengthLoop">
                        <option value="${lengthLoop.index}" <c:if test ="${sessionScope.carport.length == lengthLoop.index}"> selected </c:if>>${lengthLoop.index}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="width">Carportens bredde</label>
                <select class="form-control" id="width" name="width" required>
                    <c:forEach begin="300" end="600" step="10" varStatus="widthLoop">
                        <option value="${widthLoop.index}" <c:if test ="${sessionScope.carport.width == widthLoop.index}"> selected </c:if>>${widthLoop.index}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="roof">Carportens tagtype</label>
                <select class="form-control" id="roof" name="roof" required>
                    <option value="flat" <c:if test ="${sessionScope.carport.roofType.name() == 'Flat'}"> selected </c:if>>Fladt</option>
                    <option value="peak" <c:if test ="${sessionScope.carport.roofType.name() == 'Peak'}"> selected </c:if>>Med 15 graders hældning</option>
                </select>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="shedOption" name="shedOption" onchange="document.getElementById('shedSize').disabled = !this.checked;" <c:if test ="${sessionScope.carport.hasShed()}"> checked </c:if>>
                <label class="custom-control-label" for="shedOption">Jeg ønsker et skur</label>
            </div>
            <div class="form-group">
                <label for="shedSize">Skur type</label>
                <c:choose>
                    <c:when test="${sessionScope.carport.hasShed()}">
                        <select class="form-control" id="shedSize" name="shedSize">
                    </c:when>
                    <c:otherwise>
                        <select class="form-control" id="shedSize" name="shedSize" disabled>
                    </c:otherwise>
                </c:choose>

                        <c:choose>
                            <c:when test="${sessionScope.carport.roofType.name() == 'Peak'}}">
                                <option value="whole" <c:if test ="${sessionScope.carport.shed.width == sessionScope.carport.width-40.0}"> selected </c:if>>Hel bredde</option>
                                <option value="half" <c:if test ="${sessionScope.carport.shed.width == (sessionScope.carport.width / 2 ) - 40.0}"> selected </c:if>>Halv bredde</option>
                            </c:when>
                            <c:otherwise>
                                <option value="whole" <c:if test ="${sessionScope.carport.shed.width == sessionScope.carport.width-75.0}"> selected </c:if>>Hel bredde</option>
                                <option value="half" <c:if test ="${sessionScope.carport.shed.width == (sessionScope.carport.width / 2 ) - 75.0}"> selected </c:if>>Halv bredde</option>
                            </c:otherwise>
                        </c:choose>
                </select>
            </div>

            <div style="text-align: center">
                <button type="submit" name="action" value="preview" class="btn btn-primary">Vis tegning af carporten</button>
                <button type="submit" name="action" value="continue" class="btn btn-success">Fortsæt din bestilling</button>
            </div>


            <c:if test ="${requestScope.showCarport == true}">
                <br><br>
                <div>
                    <div style="text-align: center">
                            ${requestScope.svgSide}
                    </div>
                    <div style="text-align: center">
                            ${requestScope.svgTop}
                    </div>
                </div>
            </c:if>
        </form>
    </div>
    <div class="col-md-2"></div>
</div>


