package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Test it out!");
        return "home/hello";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardGet(ModelMap model) {
        model.addAttribute("message", "Test it out!");
        return "user/dashboard";
    }
}