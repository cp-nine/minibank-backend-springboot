<div class="card">
    <div class="card-body">
        <div>
            <h4>Create New Account</h4>
            <hr>
            <br>
            <%
            String message = (String) request.getAttribute("message");
            if (message!=null){ %>
            <div class="alert alert-info text-center"><%= message %></div>
            <% } else { %>
            <div class="alert alert-warning text-center"><p>Minimum balance Rp. 200.000,00</p></div>
            <% } %>
        </div>
        <form action="/customer/create-account" method="post">
            <div class="form-group row">
                <label for="balance" class="col-sm-3 col-form-label">Balance</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" name="ballance" id="balance"
                           placeholder="First balance" required>
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