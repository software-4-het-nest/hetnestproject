package be.hetnest.hetnestproject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @GetMapping("/login-error")
    public String loginerror(Model model) {
        model.addAttribute("error", true);
        return "login";
    }
}
