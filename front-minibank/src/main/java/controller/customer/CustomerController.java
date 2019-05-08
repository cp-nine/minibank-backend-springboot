package controller.customer;

import dao.AccountWsDao;
import dao.CustomerWsDao;
import dao.TrxWsDao;
import dao.WalletWsDao;
import dto.CommonResponse;
import entity.Account;
import entity.Customer;
import entity.Vtrx;
import entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import thread.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@EnableWebMvc
@Controller
public class CustomerController {

    private static String CIF;

    @Autowired
    private CustomerWsDao customerWsDao;

    @Autowired
    private AccountWsDao accountWsDao;

    @Autowired
    private TrxWsDao trxWsDao;

    @Autowired
    private WalletWsDao walletWsDao;

    @GetMapping("/customer")
    public String customerMain(ModelMap model, @CookieValue(name = "user") String cook) {

        this.CIF = cook;

        Customer cstm = customerWsDao.getCutomer(cook);

        model.addAttribute("profile", cstm);
        model.addAttribute("customer-page", "profile");
        model.addAttribute("page", "customer");
        return "index";

    }

    @GetMapping("/customer/list-account")
    public String customerList(ModelMap model) {

        Customer cstm = customerWsDao.getCutomer(this.CIF);
        List<Account> listAcn = cstm.getAccounts();
        if (listAcn.size() < 1){
            model.addAttribute("message", "No account found");
        }
        model.addAttribute("accounts", listAcn);
        model.addAttribute("customer-page", "listAccount");
        model.addAttribute("page", "customer");
        return "index";

    }

    @GetMapping("/customer/create-account")
    public String customerCreateAccount(ModelMap model) {

        model.addAttribute("customer-page", "createAccount");
        model.addAttribute("page", "customer");
        return "index";

    }

    @GetMapping("/customer/update-password")
    public String customerUpdatePassword(ModelMap model) {

        model.addAttribute("customer-page", "updatePassword");
        model.addAttribute("page", "customer");
        return "index";

    }

    @GetMapping("/customer/transaction-report")
    public String customerTransaction(ModelMap model) {

        List<Vtrx> trx = trxWsDao.getTransactionReport(this.CIF);

        model.addAttribute("transactions", trx);
        model.addAttribute("customer-page", "transactionReport");
        model.addAttribute("page", "customer");
        return "index";

    }

//    -------------- post -----------------------------

    @GetMapping("/customer/create-wallet")
    public String walletCreate(ModelMap model){

        model.addAttribute("customer-page", "createWallet");
        model.addAttribute("page", "customer");
        System.out.println(Data.accountNumber.get());
        return "index";

    }

    @PostMapping("/customer/create-wallet")
    public String createWallet(ModelMap model, HttpServletRequest request) throws IOException {

        String account = request.getParameter("account");
        Long accnToWallet = null;
        if (account.contains("-")){
            accnToWallet = Long.valueOf(account.replace("-",""));
        } else {
            accnToWallet = Long.valueOf(account);
        }

        Wallet wallet = new Wallet();
        wallet.setCashTag(request.getParameter("cashTag"));
        wallet.setType(request.getParameter("walletname"));

        Customer customer = customerWsDao.getCutomer(this.CIF);
        wallet.setFullName(customer.getFname()+" "+customer.getLname());

        walletWsDao.addWallet(wallet, accnToWallet);
        model.addAttribute("customer-page", "createWallet");
        model.addAttribute("page", "customer");
        return "index";
    }

    @PostMapping("/customer/update-password")
    public String updatePassword(ModelMap model, @ModelAttribute("customer") Customer customer, HttpServletRequest request) throws IOException {

        customer.setCustomerNumber(this.CIF);
        if (!request.getParameter("passwordx").equals(customer.getPassword())){
            model.addAttribute("message", "Password doesn't match");
        } else {
            CommonResponse resp = customerWsDao.updatePassword(customer);
            model.addAttribute("message", resp.getMessage());
        }

        model.addAttribute("customer-page", "updatePassword");
        model.addAttribute("page", "customer");
        return "index";
    }

    @PostMapping("/customer/create-account")
    public String createAccount(ModelMap model, @ModelAttribute("account") Account account) throws IOException {

        if (account.getBallance() < 200000){
            model.addAttribute("message", "Minimum balance is Rp.200,000");
        } else {
            Customer customer = customerWsDao.getCutomer(this.CIF);
            account.setCustomerNumber(customer.getCustomerNumber());
            account.setAccountName(customer.getFname()+" "+customer.getLname());

            accountWsDao.addAccount(account);
            model.addAttribute("message", "Success");
        }
        model.addAttribute("customer-page", "createAccount");
        model.addAttribute("page", "customer");
        return "index";
    }

}
