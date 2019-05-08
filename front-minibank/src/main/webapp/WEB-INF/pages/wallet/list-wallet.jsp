<%@ page import="java.util.List" %>
<%@ page import="entity.Vwallet" %>
<%@ page import="helper.Values" %>
<div class="row">
    <div class="col-md-12">


        <% List<Vwallet> vwallets = (List<Vwallet>) request.getAttribute("wallets");
            if (vwallets != null) { %>

        <h4>Your Wallets</h4>
        <hr>
        <table id="dataTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Wallet ID</th>
                <th scope="col">Wallet Name</th>
                <th scope="col">Account Number</th>
                <th scope="col">Active Balance</th>
                <th scope="col">Open Date</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>

            <% int no = 1;
                for (Vwallet w : vwallets) { %>


            <tr>
                <th scope="row"><%= (no++)%>
                </th>
                <td><%= w.getWalletId() %>
                </td>
                <td>
                    <a href="/wallet/profile?id=<%= w.getWalletId() %>">
                        <%= w.getType() %>
                    </a>
                </td>
                <td><%= Values.balance(String.valueOf(w.getAccountNumber())) %>
                </td>
                <td><%= Values.rupiah(w.getBallance()) %>
                </td>
                <td>
                    <%= Values.dateFormat(w.getCreateAt()) %>
                </td>
                <td>
                    <button type="button" class="btn btn-primary btn-sm btn-unreg"
                            data-toggle="modal" data-target="#unreg" data-key="<%= w.getWalletId() %>">
                        Unreg
                    </button>
                </td>
            </tr>

            <% }
            } else { %>
            <div class="card">
                <div class="card-body content-center text-center">
                    <h3>You don't have any wallet, would you like to create wallet?</h3>
                    <br>
                    <p class="text-center">
                        <a href="/customer/create-wallet" class="btn btn-primary">Create Wallet</a>
                    </p>
                </div>
            </div>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="unreg" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Wallet</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
                <div class="modal-body">

                    <p>Unreg this wallet ?</p>

                </div>
                <div class="modal-footer" id="button-grup">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancle</button>
                </div>
        </div>
    </div>
</div>