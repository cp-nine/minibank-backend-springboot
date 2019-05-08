<div class="card">
    <div class="card-body">
        <div>
            <h4>Create Mini Wallet</h4>
            <hr>
          </div>
        <form action="/customer/create-wallet" method="post">
            <div class="form-group row">
                <label for="cashtag" class="col-sm-3 col-form-label">Cash Tag</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="cashTag" id="cashtag"
                           placeholder="Cash Tag" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="account" class="col-sm-3 col-form-label">Account Number</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="account" id="account"
                           placeholder="Account Number" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="walletname" class="col-sm-3 col-form-label">Wallet Name</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="walletname" id="walletname"
                           placeholder="Wallet Name" required>
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