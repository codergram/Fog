<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Forward page to index.jsp if no session is set--%>
<script>
    history.forward();
</script>

<div class="container">
    <!--    Header image-->
    <div>
        <a href="Shop"><img src="${pageContext.request.contextPath}/images/FOG.jpg" class="img-fluid" alt="Responsive image"></a>
    </div>
<%@include file="../includes/navigation.jsp"%>