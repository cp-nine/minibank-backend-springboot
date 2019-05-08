<%@ page import="java.util.List" %>
<%@ page import="entity.Vtrx" %>
<%@ page import="helper.Values" %>

<%
    String message = (String) request.getAttribute("message");
    if (message!=null){
%>
<div class="alert alert-info" role="alert">
    <%= message %>
</div>
<% } %>

<table id="tb-transaction" class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Transaction</th>
        <th scope="col">Account Debet</th>
        <th scope="col">Account Credit</th>
        <th scope="col">Amount</th>
        <th scope="col">Transaction Date</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Vtrx> vtrxes = (List<Vtrx>) request.getAttribute("transactions");

        if (vtrxes != null) {
            int no = 1;
            if (!vtrxes.isEmpty()) {
                for (Vtrx tr : vtrxes) {
    %>

    <tr>
        <th scope="row">
            <%= (no++) %>
        </th>
        <td scope="row">
            <%= tr.getTrxCode() %>
        </td>
        <td scope="row" style="text-align: center">
            <%= (tr.getAcnDebet()==null) ? " - ": Values.balance(String.valueOf(tr.getAcnDebet()))%>
        </td>
        <td scope="row" style="text-align: center">
            <%= (tr.getAcnCredit()==null) ? " - ": Values.balance(String.valueOf(tr.getAcnCredit()))%>
        </td>
        <td scope="row">
            <%= Values.rupiah(tr.getAmount()) %>
        </td>
        <td scope="row">
            <%= Values.dateFormat(tr.getTrxDate()) %>
        </td>
    </tr>
    <% }
    }
    } else {%>
    <div class="alert alert-info" role="alert">
        No transaction found.
    </div>
    <%}%>

    </tbody>
</table>
