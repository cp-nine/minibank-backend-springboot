package controller.customer;

import dao.CustomerWsDao;
import dao.impl.CustomerWsDaoImpl;
import dto.CommonResponse;
import entity.Customer;
import helper.Values;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebMvc
@Controller
public class RegisterController {

    private CustomerWsDao customerWsDao = new CustomerWsDaoImpl();

    @GetMapping("/register")
    public String registerCustomer(ModelMap model) {
        model.addAttribute("page", "register");
        return "index";
    }

    @PostMapping("/register")
    public String createCustomer(ModelMap modelMap, @ModelAttribute("customer") Customer customer) throws IOException {

        String alert = "";
        String message = "";

        if (Values.isNumeric(customer.getFname())){
            alert = "alert-danger";
            message = "First name cannot contain number";
        } else {
            CommonResponse crs = customerWsDao.addCustomer(customer);
            if (!crs.getStatus().equalsIgnoreCase("20")){
                alert = "alert-danger";
                message = crs.getMessage();
            } else {
                alert = "alert-success";
                message = "Registration success";
            }
        }


        String messages = "<div class=\'alert "+alert+"\' role=\'alert\'>\n" +message+"</div>";

        modelMap.addAttribute("message", messages);
        modelMap.addAttribute("page", "register");
        return "index";

    }

}
