<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">Minibank</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">E-Wallet</a>
                </li>
            </ul>
        </div>
        <div class="credential float-right" style="padding-right: 10px">
            <%--<a href="#" class="btn btn-primary"> Register </a>--%>
            <%--<a href="#" class="btn btn-success"> LoginController </a>--%>
            <a href="#" class="btn btn-danger"> Logout </a>
        </div>
    </div>
</nav>