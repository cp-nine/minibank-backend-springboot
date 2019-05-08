<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-body">
        <div>
            <h4>Top up Your Wallet</h4>
            <hr>
            <% String message = (String) request.getAttribute("message");
                if (message!=null){ %>
            <div class="alert alert-info text-center"><%= message %></div>
            <% }%>
        </div>
        <form action="/wallet/top-up" method="post">
            <div class="form-group row">
                <label for="acountNumber" class="col-sm-3 col-form-label">Account Number</label>
                <div class="col-sm-9">
                    <select class="form-control" name="accountNumber" id="acountNumber" required>
                        <option>--- Select Account ---</option>
                        <c:forEach items="${accounts}" var="ac">
                        <option value="${ac}">${ac}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label for="amount" class="col-sm-3 col-form-label">Top Up Nominal</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" name="amount" id="amount"
                           placeholder="Nominal">
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-primary btn-block">Ok</button>
                </div>
            </div>
        </form>
    </div>
</div>