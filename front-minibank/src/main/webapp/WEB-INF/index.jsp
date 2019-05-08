<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/datatables.min.css"/>

    <title>Mini Bank</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/style.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/datepicker/css/datepicker.css" />'>

</head>
<body>
<jsp:include page="template/navbar.jsp"/>

<br><br>
<div class="container" style="min-height: 380px">

    <% String halaman = (String) request.getAttribute("page");
        if (!halaman.equals("")) {
            if (halaman.equals("login")) { %>
                <jsp:include page="pages/auth/login.jsp"/>
            <% } else if (halaman.equals("register")) { %>
                <jsp:include page="pages/customer/new-customer.jsp"/>
            <% } else if (halaman.equals("customer")) { %>
                <jsp:include page="pages/customer/main.jsp"/>
            <% } else if (halaman.equals("home")) { %>
                <jsp:include page="pages/home.jsp"/>
            <% } else if (halaman.equals("wallet")) { %>
            <jsp:include page="pages/wallet/list-wallet.jsp"/>
            <% } else if (halaman.equals("walletProfile")) { %>
    <jsp:include page="pages/wallet/main.jsp"/>
    <% }
        } else { %>
            <jsp:include page="pages/home.jsp"/>
        <%}%>
</div>

<jsp:include page="template/footer.jsp"/>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/datatables.min.js"></script>

<script src='<c:url value="/resources/datepicker/js/bootstrap-datepicker.js"/>'></script>

<jsp:include page="script.jsp"/>

</body>
</html>
