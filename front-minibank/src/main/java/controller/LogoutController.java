package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import thread.Data;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            for (int i=0; i<cookies.length; i++){
                Cookie cookie = cookies[i];
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        Data.accountNumber.remove();

        resp.sendRedirect("/login");

    }

}
