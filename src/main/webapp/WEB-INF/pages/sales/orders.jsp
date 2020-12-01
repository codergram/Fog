<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="mt-4 mb-4 text-center">Ordre</h2>
<br>
<br>
<br/> <br/>
<table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <th>Ordrenummer</th>
    <th>Kundenavn</th>
    <th>Pris</th>
    <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.orderlist}" var="order" varStatus="vs">
    <tr>
        <td>
            <a href="Ordre/View/${order.id}">
                ${order.id}
            </a>
        </td>
        <td>${order.customer.name}</td>
        <td>${order.carport.price}</td>
        <td>${order.status.name()}</td>
    </tr>
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
</div></div>

</body>
</html>
