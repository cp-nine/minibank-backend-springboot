package controller.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbidenController {

    @GetMapping("/403")
    public String forbidenAcces(){
        return "errors/403";
    }

}
