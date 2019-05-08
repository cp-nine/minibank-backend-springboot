<div class="card">
    <div class="card-body">
        <div>
            <h4>Update your Password</h4>
            <hr>
            <br>
            <%
                String message = (String) request.getAttribute("message");
                if (message!=null){
            %>
            <div class="alert alert-info" role="alert">
                <%= message %>
            </div>
            <% } %>

        </div>
        <form action="/customer/update-password" method="post">
            <div class="form-group row">
                <label for="newpassword" class="col-sm-3 col-form-label">New Password</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" name="password" id="newpassword"
                           placeholder="Password" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="newpasswordx" class="col-sm-3 col-form-label">Confirm Password</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" name="passwordx" id="newpasswordx"
                           placeholder="Confirm Password" required>
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