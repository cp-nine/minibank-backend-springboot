<%@ page import="entity.Customer" %>
<%@ page import="entity.Wallet" %>
<h3>Wallet Profile</h3>
<hr>
<table class="table table-hover">
    <% Wallet cs = (Wallet) request.getAttribute("profile"); %>
    <tbody>
    <tr>
        <th style="width: 150px">Name</th>
        <td><%= cs.getFullName() %>
        </td>
    </tr>
    <tr>
        <th>Cash Tag</th>
        <td>$<%= cs.getCashTag() %>
        </td>
    </tr>
    <tr>
        <th>Wallet Name</th>
        <td>
            <%= cs.getType() %>
        </td>
    </tr>
    </tbody>
</table>