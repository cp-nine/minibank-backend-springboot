<div class="card">
    <div class="card-body">
        <div>
            <h4>Add Wallet Account</h4>
            <hr>
            <% String message = (String) request.getAttribute("message");
                if (message!=null){ %>
            <div class="alert alert-info text-center"><%= message %></div>
            <% }%>
        </div>
        <form action="/wallet/add-wallet-account" method="post">
            <div class="form-group row">
                <label for="account" class="col-sm-3 col-form-label">Account Number</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="accountNumber" id="account"
                           placeholder="Account Number" required>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-primary btn-block">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>