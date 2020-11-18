<%--
  Created by IntelliJ IDEA.
  User: Tweny
  Date: 17/11/2020
  Time: 09.12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="da">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>${pageContext.response.status}</title>
</head>
<body>
<main role="main" class="container">
    <div class="content">
        <h1 class="error-status">${pageContext.response.status}</h1>
        <p><%=request.getAttribute("javax.servlet.error.message")%>
        </p>
        <p>Passerer De <a href="${pageContext.request.contextPath}">start</a>
            modtager de ikke kr 4000,-</p>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>

</body>
</html>
