<%@ page import="entity.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="helper.Values" %>
<%
    String message = (String) request.getAttribute("message");
    if (message!=null){
%>
<div class="alert alert-info" role="alert">
    <%= message %>
</div>
<% } %>
<table id="tb-customer" class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Account Name</th>
        <th scope="col">Account Number</th>
        <th scope="col">Balance</th>
        <th scope="col">Open Date</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Account> accountList = (List<Account>) request.getAttribute("accounts");
        int no = 1;
        for (Account list : accountList) { %>

    <tr>
        <th scope="row"><%= no++ %></th>
        <td><%= list.getAccountName()%></td>
        <td><%= Values.balance(list.getAccountNumber().toString())%></td>
        <td><%= Values.rupiah(list.getBallance())%></td>
        <td><%= list.getOpenDate()%></td>
    </tr>

    <% } %>
    </tbody>
</table>