<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="year" value="${now}" pattern="yyyy"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%--
  ~ Copyright (c) 2020. Team CoderGram
  ~
  ~ @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
  ~ @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
  ~ @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
  --%>

<footer class="mb-12 mt-4 fogfooter">
    <span class="align-middle">Copyright &copy; CoderGram ${year}</span>
</footer>