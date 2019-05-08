<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
    String wp = (String) request.getAttribute("wallet-page");
    Integer WALLET_ID = (Integer) request.getAttribute("wallet_id");

    String profile = "";
    String topUp = "";
    String transfer = "";
    String tarik = "";
    String addAccount = "";

    if (wp.equalsIgnoreCase("profile")){
        profile = "active";
    } else if (wp.equalsIgnoreCase("topUp")){
        topUp = "active";
    } else if (wp.equalsIgnoreCase("transfer")){
        transfer = "active";
    } else if (wp.equalsIgnoreCase("tarikTunai")){
        tarik = "active";
    } else if (wp.equalsIgnoreCase("addAccount")){
        addAccount = "active";
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
                       href="/wallet/profile?id=<%= WALLET_ID %>">Profile</a>
                    <a class="nav-link <%= topUp %>"
                       href="/wallet/top-up">Top Up</a>
                    <a class="nav-link <%= transfer %>"
                       href="/wallet/transfer">Transfer</a>
                    <a class="nav-link <%= tarik %>"
                       href="/wallet/cash-withdrawal">Cash Withdrawal</a>
                    <a class="nav-link <%= addAccount %>"
                       href="/wallet/wallet-account">Wallet Account</a>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <div class="tab-content" id="v-pills-tabContent">

            <% if (profile.equalsIgnoreCase("active")){ %>
            <jsp:include page="/WEB-INF/pages/wallet/profile.jsp"/>
            <% } else if (topUp.equalsIgnoreCase("active")){ %>
            <jsp:include page="/WEB-INF/pages/wallet/top-up.jsp"/>
            <% } else if (transfer.equalsIgnoreCase("active")){ %>
            <jsp:include page="/WEB-INF/pages/wallet/transfer.jsp"/>
            <% } else if (tarik.equalsIgnoreCase("active")) { %>
            <jsp:include page="/WEB-INF/pages/wallet/withdrawal.jsp"/>
            <% } else if (addAccount.equalsIgnoreCase("active")) { %>
            <jsp:include page="/WEB-INF/pages/wallet/wallet-account.jsp"/>
            <% } %>


        </div>
    </div>
</div>