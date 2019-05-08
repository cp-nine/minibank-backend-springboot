package controller.wallet;


import dao.CustomerWsDao;
import dao.TrxWsDao;
import dao.WalletWsDao;
import dto.CommonResponse;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import thread.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@EnableWebMvc
@Controller
public class WalletController {

    private static String CIF;
    private static Integer WID;

    @Autowired
    private WalletWsDao walletWsDao;

    @Autowired
    private CustomerWsDao customerWsDao;

    @Autowired
    private TrxWsDao trxWsDao;

    @GetMapping("/wallet")
    public String walletMain(ModelMap model, @CookieValue(name = "user") String cif){
        this.CIF = cif;

        List<Vwallet> wallets = walletWsDao.getWalletCustomer(this.CIF);
        model.addAttribute("wallets", wallets);
        model.addAttribute("page", "wallet");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @GetMapping("/wallet/unreg")
    public void walletUnreg(ModelMap model, @RequestParam(name = "id") Integer wid, HttpServletResponse response) throws IOException {

        CommonResponse<Wallet> wrsp = walletWsDao.unregWallet(wid);

        List<Vwallet> wallets = walletWsDao.getWalletCustomer(this.CIF);
        model.addAttribute("wallets", wallets);
        model.addAttribute("page", "wallet");
        System.out.println(Data.accountNumber.get());
        response.sendRedirect("/wallet");
    }

    @GetMapping("/wallet/profile")
    public String walletProfile(ModelMap model,
                                @RequestParam(name = "id") Integer wid,
                                @CookieValue(name = "user") String cif){
        this.CIF = cif;
        this.WID = wid;


        Wallet wallet = walletWsDao.getWalletProfile(wid);

        model.addAttribute("profile", wallet);
        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("wallet-page", "profile");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @GetMapping("/wallet/top-up")
    public String walletTopUp(ModelMap model){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet-page", "topUp");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @GetMapping("/wallet/transfer")
    public String walletTransfer(ModelMap model){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet-page", "transfer");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @GetMapping("/wallet/cash-withdrawal")
    public String walletWithdrawal(ModelMap model){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("wallet-page", "tarikTunai");
        model.addAttribute("page", "walletProfile");
        return "index";
    }

    @GetMapping("/wallet/wallet-account")
    public String getWalletAccount(ModelMap model){

        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("wallet_account", walletAccounts);
        model.addAttribute("wallet-page", "addAccount");
        model.addAttribute("page", "walletProfile");
        return "index";

    }

    @PostMapping("/wallet/wallet-account")
    public String addWalletAccount(ModelMap model, @ModelAttribute("walletAccount") WalletAccount walletAccount){

        walletAccount.setWalletId(this.WID);

        CommonResponse<WalletAccount> war = walletWsDao.addWalletAccount(walletAccount);
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);

        if (!war.getStatus().equalsIgnoreCase("20")){
            model.addAttribute("message", war.getMessage());
        } else {
            model.addAttribute("message", war.getMessage());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("wallet_account", walletAccounts);
        model.addAttribute("wallet-page", "addAccount");
        model.addAttribute("page", "walletProfile");
        return "index";

    }


    @PostMapping("/wallet/top-up")
    public String walletTopUp(ModelMap model, HttpServletRequest request){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        TrxEntity trxEntity = new TrxEntity();
        trxEntity.setAmount(Double.valueOf(request.getParameter("amount")));
        trxEntity.setAcnCredit(Long.valueOf(request.getParameter("accountNumber")));
        trxEntity.setTrxCode("T0002");

        CommonResponse<TrxEntity> trxResp = trxWsDao.topUp(trxEntity);

        model.addAttribute("message", trxResp.getMessage());
        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet-page", "topUp");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @PostMapping("/wallet/cash-withdrawal")
    public String cash(ModelMap model, HttpServletRequest request){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        TrxEntity trxEntity = new TrxEntity();
        trxEntity.setAmount(Double.valueOf(request.getParameter("amount")));
        trxEntity.setAcnDebet(Long.valueOf(request.getParameter("accountNumber")));
        trxEntity.setTrxCode("T0004");

        CommonResponse<TrxEntity> trxResp = trxWsDao.cashWithDrawal(trxEntity);
        if (!trxResp.getStatus().equalsIgnoreCase("20")){
            model.addAttribute("message", trxResp.getMessage());
        } else {
            model.addAttribute("message", trxResp.getMessage());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet-page", "tarikTunai");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

    @PostMapping("/wallet/transfer")
    public String transfer(ModelMap model, HttpServletRequest request){

        List<Long> listAccount = new ArrayList<>();
        List<WalletAccount> walletAccounts = walletWsDao.getWalletAccount(this.WID);
        for (WalletAccount wc: walletAccounts) {
            listAccount.add(wc.getAccountNumber());
        }

        String account = request.getParameter("destination");
        Long accnToTransfer = null;
        if (account.contains("-")){
            accnToTransfer = Long.valueOf(account.replace("-",""));
        } else {
            accnToTransfer = Long.valueOf(account);
        }

        TrxEntity trxEntity = new TrxEntity();
        trxEntity.setAmount(Double.valueOf(request.getParameter("amount")));
        trxEntity.setAcnDebet(Long.valueOf(request.getParameter("accountNumber")));
        trxEntity.setAcnCredit(accnToTransfer);
        trxEntity.setTrxCode("T0003");

        CommonResponse<TrxEntity> trxResp = trxWsDao.transfer(trxEntity);
        if (!trxResp.getStatus().equalsIgnoreCase("20")){
            model.addAttribute("message", trxResp.getMessage());
        } else {
            model.addAttribute("message", trxResp.getMessage());
        }

        model.addAttribute("wallet_id", this.WID);
        model.addAttribute("accounts", listAccount);
        model.addAttribute("wallet-page", "transfer");
        model.addAttribute("page", "walletProfile");
        System.out.println(Data.accountNumber.get());
        return "index";
    }

}
