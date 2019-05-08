<div class="row justify-content-md-center">
    <div class="col-md-5">
        <div class="card">
            <div class="card-body">
                <div class="text-center">
                    <h3>Mini Bank</h3>
                    <hr>
                    <br>
                </div>
                <form action="/login" method="post">
                    <div class="form-group row">
                        <label for="username" class="col-sm-3 col-form-label">Username</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control"
                                   name="username" id="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control"
                                   name="password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <br>
                    <div class="form-group row justify-content-sm-center">
                        <div class="col-sm-8">
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>