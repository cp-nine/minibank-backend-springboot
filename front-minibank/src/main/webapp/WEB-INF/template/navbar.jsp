<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    boolean isLogin = false;
    String account = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null){
        for (Cookie ck: cookies) {
            if (ck.getName().equalsIgnoreCase("user")){
                isLogin = true;
            }
            if (ck.getName().equalsIgnoreCase("page")){
                account = ck.getValue();
            }
        }
    }
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
    <a class="navbar-brand" href="/">Mini Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <% if (account.equalsIgnoreCase("customer")){%>
            <li class="nav-item active">
                <a class="nav-link" href="/customer">Dashboard <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/wallet">E-Wallet</a>
            </li>
            <% }%>
        </ul>
        <form class="form-inline my-2 my-lg-0 auth-btn">
            <% if (isLogin) { %>
            <a href="/logout" class="btn btn-danger"> Logout </a>
            <% } else { %>
            <a href="/register" class="btn btn-primary"> Register </a>
            <a href="/login" class="btn btn-success" style="margin-left: 15px"> Login </a>
            <%}%>
        </form>
    </div>
    </div>
</nav>