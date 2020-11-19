<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
        <div class="row mt-4">

            <div class="col-sm-12 col-md-1" style=""></div>
            <div class="col-sm-12 col-md-10" style="">

                <div>
                    <c:if test ="${requestScope.successMessage != null}">
                        <div class="alert alert-success" style="padding-bottom: inherit;">
                            <p>${requestScope.successMessage}</p>
                        </div>
                    </c:if>
                    <c:if test ="${requestScope.errorMessage != null}">
                        <div class="alert alert-danger" style="padding-bottom: inherit;">
                            <p>${requestScope.errorMessage}</p>
                        </div>
                    </c:if>
                </div>
                <form action="DeleteFromCart" method="post">

                    <div class="row">
                        <!--    Padding-left skal ændres i admin CSS files så den flugter med den anden tabel "sb-admin.css" -->
                        <div id="bulkOptionContainer" class="col-sx-6 col-sm-6 col-md-3">
                            <select class="form-control" name="selector_option" id="">
                                <option value="">Select option</option>
                                <option value="DeleteSelected">Delete Selected</option>
                            </select>
                            <p></p>
                        </div>
                        <!--    Padding-left skal ændres i admin CSS files så den flugter med den anden tabel "sb-admin.css" -->
                        <div id="applyChangesPost" class="col-sx-6 col-sm-6 col-md-2">
                            <input type="submit" name="submit_form" class="btn btn-primary" value="Apply">
                        </div>
                    </div>

                    <div class="table-responsive-sm">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><input id="selectAllBoxes" type="checkbox"></th>
                            <th scope="col"></th>
                            <th scope="col">Product</th>
                            <th scope="col">Topping</th>
                            <th scope="col">Bottom</th>
                            <th scope="col">Qty</th>
                            <th scope="col">Subtotal</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="shoppingCart" items="${sessionScope.shoppingCart}" varStatus="array">
                                <tr>
                                    <td class="align-middle"><input class='checkBoxes' type='checkbox' name='checkboxArray' value='${array.index}'></td>
                                    <td class="align-middle"><img src="images/${shoppingCart.item.item_image}.jpg" alt="${shoppingCart.item.item_name}" width="75"></td>
                                    <td class="align-middle">${shoppingCart.item.item_name}</td>
                                    <td class="align-middle">${shoppingCart.topping.topping_name}</td>
                                    <td class="align-middle">${shoppingCart.bottom.bottom_name}</td>
                                    <td class="align-middle">${shoppingCart.qty}</td>
                                    <c:set var = "price_format" value = "${shoppingCart.subTotal}" />
                                    <td class="align-middle"><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </div>
                    <c:set var = "price_format" value = "${requestScope.cartTotal}" />
                    <h3 class="text-center">Order total: <fmt:formatNumber type = "number" minFractionDigits = "2" value = "${price_format}" />$</h3>

                </form>
            </div>
            <div class="col-sm-12 col-md-1" style=""></div>

        </div>
        <c:if test="${sessionScope.shoppingCart.size() > 0}">
        <div class="row mt-4 text-center">
            <div class="col-sm-12 col-md-4" style=""></div>
            <div class="col-sm-12 col-md-2" style="">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ProductView">Order More</a>
            </div>
            <div class="col-sm-12 col-md-2" style="">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/LoginRegisterPage?user_role=${sessionScope.user.userRole}">Checkout</a>
            </div>
            <div class="col-sm-12 col-md-4" style=""></div>
        </div>
        </c:if>
        <c:if test="${sessionScope.shoppingCart.size() == 0}">
        <div class="row mt-4 text-center">
            <div class="col-sm-12 col-md-4" style=""></div>
            <div class="col-sm-12 col-md-4" style="">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ProductView">Order More</a>
            </div>
            <div class="col-sm-12 col-md-4" style=""></div>
        </div>
        </c:if>