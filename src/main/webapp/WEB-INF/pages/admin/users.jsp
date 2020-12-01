<%--
  Created by IntelliJ IDEA.
  User: Jacob
  Date: 29-11-2020
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2 class="mt-4 mb-4 text-center">Brugere</h2>
<br>

<h3 class="mt-4 mb-4 text-center">${requestScope.valSet}</h3>
<br>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCreateUser">
    Opret ny bruger
</button>
<br/> <br/>
<table class="table">
    <thead>

        <th>Bruger id</th>
        <th>Navn</th>
        <th>Telefonnummer</th>
        <th>E-mail</th>
        <th class="no-sort"> </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <form action="Test" method="post">
            <input type="hidden" name="valOne" value="1"/>
            <td>1</td>
            <td>Peter petersen</td>
            <td>12312322</td>
            <td>Peter@petersen.com</td>
            <td><input type="submit" value="Slet bruger"/></td>
        </form>
    </tr>
    <tr>
        <td>2</td>
        <td>Emil Emilsen</td>
        <td>44223311</td>
        <td>Emil@emilsen.com</td>
        <td><input type="submit" value="Slet bruger"/></td>
    </tr>
    <tr>
        <td>2</td>
        <td>jacob Emilsen</td>
        <td>11223377</td>
        <td>jacob@emilsen.com</td>
        <td><input type="submit" value="Slet bruger"/></td>
    </tr>
</table>
</body>
</html>
