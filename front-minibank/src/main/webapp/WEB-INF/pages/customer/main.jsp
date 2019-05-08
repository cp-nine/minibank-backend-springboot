<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
    String cp = (String) request.getAttribute("customer-page");

    String profile = "";
    String list = "";
    String create = "";
    String password = "";
    String report = "";
    String wallet = "";

    if (cp.equalsIgnoreCase("profile")){
        profile = "active";
    } else if (cp.equalsIgnoreCase("listAccount")){
        list = "active";
    } else if (cp.equalsIgnoreCase("createAccount")){
        create = "active";
    } else if (cp.equalsIgnoreCase("updatePassword")){
        password = "active";
    } else if (cp.equalsIgnoreCase("transactionReport")){
        report = "active";
    } else if (cp.equalsIgnoreCase("createWallet")){
        wallet = "active";
    }

%>
<div class="row">
    <div class="col-md-3" style="margin-bottom: 20px">
        <div class="card">
            <div class="card-body">
                <div class="content-center">
                    <img src='<c:url value="/resources/images/github.png"/>'
                         class="rounded mx-auto d-block img-profile" alt="Profile Image">
                    <hr>
                </div>
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class='nav-link <%= profile %>'
                       href="/customer">Profile</a>
                    <a class="nav-link <%= list %>"
                       href="/customer/list-account">List Account</a>
                    <a class="nav-link <%= create %>"
                       href="/customer/create-account">Create Account</a>
                    <a class="nav-link <%= report %>"
                       href="/customer/transaction-report">Transactions Report</a>
                    <a class="nav-link <%= password %>"
                       href="/customer/update-password">Update Password</a>
                    <a class="nav-link <%= wallet %>"
                       href="/customer/create-wallet">Create Wallet</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <div class="tab-content" id="v-pills-tabContent">

           <% if (profile.equalsIgnoreCase("active")){ %>
                <jsp:include page="/WEB-INF/pages/customer/profile.jsp"/>
           <% } else if (create.equalsIgnoreCase("active")){ %>
                <jsp:include page="/WEB-INF/pages/customer/create-account.jsp"/>
           <% } else if (password.equalsIgnoreCase("active")){ %>
                <jsp:include page="/WEB-INF/pages/customer/update-password.jsp"/>
           <% } else if (list.equalsIgnoreCase("active")) { %>
                <jsp:include page="/WEB-INF/pages/customer/list-account.jsp"/>
            <% } else if (report.equalsIgnoreCase("active")){ %>
                <jsp:include page="/WEB-INF/pages/customer/transaction-report.jsp"/>
            <% } else if (wallet.equalsIgnoreCase("active")){ %>
                <jsp:include page="/WEB-INF/pages/wallet/create-wallet.jsp"/>
            <% } %>

        </div>
    </div>
</div>