<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    </br>
    <h1 class="mt-5">Sticky footer with fixed navbar</h1>
    <p class="lead">Pin a footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS. A fixed navbar has been added with <code>padding-top: 60px;</code> on the <code>main &gt; .container</code>.</p>
    <p>Back to the default sticky footer minus the navbar.</p>

    <form action="" method="post">
        <div class="form-group">
            <label for="inputName">Item name</label>
            <input type="text" class="form-control" id="inputName" name="inputName" aria-describedby="inputHelp">
            <small id="inputHelp" class="form-text text-muted">We'll always share your items with everyone like Loogle.</small>
        </div>
        <button type="submit" class="btn btn-primary">Add item</button>
    </form>
    </br></br>
<div class="list-group">
    <h2>Items in Database:</h2>
<c:forEach items="${requestScope.items}" var="item" varStatus="vs">
        <button type="button" class="list-group-item list-group-item-action" data-toggle="modal" data-target="#${vs.index}Modal">
            ${item.name}
        </button>
    </div>
</c:forEach>
</div>
<c:forEach items="${requestScope.items}" var="item" varStatus="vs">
<!-- Start of Modal -->
<div class="modal fade" id="${vs.index}Modal" tabindex="-1" aria-labelledby="${vs.index}ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Item no. ${item.id}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Item Name: ${item.name}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</c:forEach>
<!-- End of Modal -->