<%@ page import="entity.Customer" %>
<h3>Customer Profile</h3>
<hr>
<table class="table table-hover">
    <% Customer cs = (Customer) request.getAttribute("profile"); %>
    <tbody>
    <tr>
        <th style="width: 150px">Name</th>
        <td><%= cs.getFname() + " " + cs.getLname() %>
        </td>
    </tr>
    <tr>
        <th>Username</th>
        <td>@<%= cs.getUsername() %>
        </td>
    </tr>
    <tr>
        <th>Brith of Date</th>
        <td><%= cs.getBrithDate() %>
        </td>
    </tr>
    </tbody>
</table>