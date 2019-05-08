package controller;

import dao.AuthWsDao;
import dao.impl.AuthWsDaoImpl;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebMvc
@Controller
public class LoginController {

    @Autowired
    private AuthWsDao authWsDao;


    @GetMapping("/login")
    public String login(ModelMap model) {
        model.addAttribute("page", "login");
        return "index";
    }

    @PostMapping("/login")
    public void postLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Customer customer = new Customer();
        customer.setUsername(req.getParameter("username"));
        customer.setPassword(req.getParameter("password"));

        Customer cs = authWsDao.loginCustomer(customer);

        if (cs != null) {
            // set new cookie if login success
            Cookie cookie = new Cookie("user", cs.getCustomerNumber());
            Cookie cookie2 = new Cookie("page", "customer");
            resp.addCookie(cookie);
            resp.addCookie(cookie2);
            // redirect to siswa page if login success
            resp.sendRedirect("customer");
        } else {
            resp.sendRedirect("login");
        }

    }


}
