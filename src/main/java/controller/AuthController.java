package controller;

import model.User;
import model.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.MD5;

/**
 * @author Alex, 1/9/2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(ModelMap model) {

        model.addAttribute("message", "Welcome to login page!");
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(ModelMap model) {

        model.addAttribute("message", "Welcome to register page!");
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, ModelMap model) {

        // Encrypt plain password with MD5 hash
        user.setPassword(MD5.encrypt(user.getPassword()));

        final UserDAO userDAO = new UserDAO();
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("emailRegistered", "This email is already registered");
        } else {
            if (userDAO.addUser(user) != null) {
                model.addAttribute("updates", "Successfully registered!");
            } else {
                model.addAttribute("updates", "Something went wrong!");
            }
        }
        return "auth/register";
    }
}
