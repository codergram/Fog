<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>

<h2 class="mt-4 mb-4 text-center">Ordre</h2>
<br>
<br>
<br/> <br/>
<table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
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
        <c:if test ="${order.salesEmployee.email == sessionScope.user.email || !order.hasSalesman()}">
    <tr>
        <td class="text-center">
            <a href="Ordre/View/${order.id}">
                ${order.id}
            </a>
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

<div class="modal fade" id="modalCreateUser" tabindex="-1" aria-labelledby="modalCreateUserLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Opret ny bruger</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="Users" method="POST">
                <div class="modal-body">
                    <input type="hidden" name="action" value="createUser">
                    <div class="form-group">
                        <input type="name" class="form-control" id="inputName" name="inputName" placeholder="Indtast navn...">
                    </div>

                    <div class="form-group">
                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Indtast e-mail...">
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="inputPsw" name="inputPsw" placeholder="Indtast kodeord...">
                    </div>

                    <div class="form-group">
                        <select class="form-control" id="inputRole" name="inputRole">
                            <option value="Employee">Sælger</option>
                            <option value="Admin">Administrator</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Annuller</button>
                    <button type="submit" class="btn btn-success">Opret bruger</button>
                </div>
            </form>
        </div>
    </div>
</div>