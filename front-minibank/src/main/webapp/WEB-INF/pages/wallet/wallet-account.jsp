<%@ page import="entity.WalletAccount" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-12">

        <h4 style="float: left">Your Wallets</h4>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-sm"
                data-toggle="modal" data-target="#exampleModalCenter" style="float: right">
            Add Account
        </button>
        <br>
        <hr style="margin-top: 20px">
        <table id="dataTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Account Number</th>
            </tr>
            </thead>
            <tbody>
            <%
            List<WalletAccount> vwalletList = (List<WalletAccount>) request.getAttribute("wallet_account");
            int no = 1;
            for (WalletAccount wa: vwalletList) {
            %>
            <tr>
            <td scope="col"><%= (no++)%></td>
            <td scope="col"><%= wa.getAccountNumber()%></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/wallet/wallet-account" method="post">
                <div class="modal-body">

                    <div class="form-group row">
                        <label for="account" class="col-sm-3 col-form-label">Account Number</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="accountNumber" id="account"
                                   placeholder="Account Number">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>