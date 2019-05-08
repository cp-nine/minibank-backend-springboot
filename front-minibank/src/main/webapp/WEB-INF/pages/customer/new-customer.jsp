<div class="row justify-content-md-center">
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <div class="text-center">
                    <h3>New Customer Mini Bank</h3>
                    <hr>
                    <br>
                </div>

                <% String message = (String) request.getAttribute("message");%>
                <%= (message!=null) ? message : "" %>

                <form action="/register" method="post" id="register-form">
                    <div class="form-group row">
                        <label for="fname" class="col-sm-3 col-form-label">First Name</label>
                        <div class="col-sm-9 grup-input">
                            <input type="text" class="form-control"
                                   name="fname" id="fname" placeholder="First Name" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lname" class="col-sm-3 col-form-label">Last Name</label>
                        <div class="col-sm-9 grup-inpu">
                            <input type="text" class="form-control"
                                   name="lname" id="lname" placeholder="Last Name" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="bdate" class="col-sm-3 col-form-label">Brith Of Date</label>
                        <div class="col-sm-9 grup-input">
                            <input type="text" class="form-control datepicker"
                                   name="brithDate" id="bdate" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="username" class="col-sm-3 col-form-label">Username</label>
                        <div class="col-sm-9 grup-input">
                            <input type="text" class="form-control" name="username"
                                   id="username" placeholder="Username" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-9 grup-input">
                            <input type="password" class="form-control"
                                   name="password" id="password" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password2" class="col-sm-3 col-form-label">Confirm Password</label>
                        <div class="col-sm-9 grup-input">
                            <input type="password" class="form-control"
                                   name="password2" id="password2"
                                   placeholder="Confirm Password" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group row justify-content-sm-center">
                        <div class="col-sm-8">
                            <input type="submit" class="btn btn-primary btn-block" value="Save"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>