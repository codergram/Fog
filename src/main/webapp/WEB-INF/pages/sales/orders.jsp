<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>

<%--
  ~ Copyright (c) 2020. Team CoderGram
  ~
  ~ @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
  ~ @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
  ~ @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
  --%>

<h2 class="mt-4 mb-4 text-center">Ordre</h2>
<br>
<br>
<br/> <br/>
<table id="example1" name="example1" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <th>Ordrenummer</th>
    <th>Dato</th>
    <th>Kundenavn</th>
    <th>Salgspris</th>
    <th>Status</th>
    <c:if test ="${requestScope.currentUser.admin}">
        <th>Sælger</th>
    </c:if>
    <th class="no-sort">Handling</th>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.orderlist}" var="order" varStatus="vs">
        <c:if test ="${order.salesEmployee.email == sessionScope.user.email || !order.hasSalesman() || sessionScope.user.admin}">
    <tr>
        <td class="text-center">
            <c:choose>
                <c:when test="${order.salesEmployee.email == sessionScope.user.email || sessionScope.user.admin}">
                    <a href="Ordre/View/${order.id}">
                            ${order.id}
                    </a>
                </c:when>
                <c:otherwise>
                    ${order.id}
                </c:otherwise>
            </c:choose>
        </td>
        <td>
                ${order.orderDate}
        </td>
        <td>
                ${order.customer.name}
        </td>
        <td class="text-right">
            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.carport.price + (order.margin/100) * order.carport.price}" /> kr
        </td>
        <td>
                    <form action="Ordre" method="POST">
                        <input type="hidden" name="action" value="changeStatus">
                        <input type="hidden" name="ordrenummer" value="${order.id}" />
                        <select class="custom-select" name="statusvalue" onchange="this.form.submit()" <c:if test ="${!order.hasSalesman()}">disabled</c:if>>
                            <c:forEach items="${requestScope.statuslist}" var="status" varStatus="vs">
                                <option value="${status}"<c:if test ="${order.status.name() == status}"> selected</c:if>>${status}</option>
                            </c:forEach>
                        </select>
                    </form>
        </td>
        <c:if test ="${requestScope.currentUser.admin}">
            <td>${order.salesEmployee.name}</td>
        </c:if>
        <td>
            <c:if test ="${!order.hasSalesman()}">
                <form action="Ordre" method="POST">
                    <input type="hidden" name="action" value="assignOrder">
                    <input type="hidden" name="ordrenummer" value="${order.id}" />
                    <input type="submit" class="btn-secondary" value="Tag ordre"/>
                </form>
            </c:if>
            <c:if test ="${order.hasSalesman()}">
                <form action="Ordre" method="POST">
                    <input type="hidden" name="action" value="releaseOrder">
                    <input type="hidden" name="ordrenummer" value="${order.id}" />
                    <input type="submit" class="btn-danger" value="Frigiv ordre" onclick="return confirm('Er du sikker?')"/>
                </form>
            </c:if>
        </td>
    </tr>
    </c:if>
    </c:forEach>
</table>