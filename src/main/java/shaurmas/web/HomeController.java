package shaurmas.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
