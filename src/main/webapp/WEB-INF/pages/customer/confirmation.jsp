<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="mt-4 mb-4 text-center">Tegning af din helt egen carport herunder</h2>
        <br>
        <div>
            <div style="text-align: center">
                ${requestScope.svgSide}
            </div>
            <div style="text-align: center">
                ${requestScope.svgTop}
            </div>
        </div>
        <br>
        <form action="${pageContext.request.contextPath}/Confirmation" method="POST">
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
                <input type="number" class="form-control" id="zip" name="zip" required>
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


