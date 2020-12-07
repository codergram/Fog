<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="mt-4 mb-4 text-center">Materialeliste</h2>
<br>
<br>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCreateMaterial">
    Tilføj nyt materiale
</button>
<br/> <br/>
<table id="example" name="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <th>Navn</th>
        <th>Materiale type</th>
        <th>Pris</th>
        <th>Enhed</th>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.materiallist}" var="material" varStatus="vs">
            <tr>
                <td>${material.name}</td>
                <td>${material.typeName()}</td>
                <td>${material.price} kr</td>
                <td>${material.unitString}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="modal fade" id="modalCreateMaterial" tabindex="-1" aria-labelledby="modalCreateMaterialLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tilføj nyt Materiale</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="Materialer" method="POST">
                <div class="modal-body">
                    <input type="hidden" name="action" value="creatematerial">
                    <div class="form-group">
                        <input type="name" class="form-control" id="inputName" name="inputName" placeholder="Indtast navn...">
                    </div>

                    <div class="form-group">
                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Indtast type...">
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="inputPsw" name="inputPsw" placeholder="Indtast usage...">
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="" name="inputRole">
                            <option value="Employee">Employee</option>
                            <option value="Admin">Administrator</option>

                        </select>
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
