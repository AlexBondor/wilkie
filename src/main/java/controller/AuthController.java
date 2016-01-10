package controller;

import model.User;
import model.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alex, 1/9/2016.
 */
@Controller
@RequestMapping("/")
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(ModelMap model) {

        model.addAttribute("message", "Welcome to login page!");
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute User user, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Check if user is present in database
        final UserDAO userDAO = new UserDAO();
        final User dbUser = userDAO.getUserByEmail(user.getEmail());
        if (dbUser != null) {
            final boolean passwordMatches = MD5.matches(user.getPassword(), dbUser.getPassword());
            if (passwordMatches) {
                model.addAttribute("updates", "You have succesfully logged in!");
                request.getSession().setAttribute("user", dbUser);
                response.sendRedirect("/dashboard");
                return "user/dashboard";
            } else {
                model.addAttribute("wrongPassword", "The provided password is wrong!");
            }
        } else {
            model.addAttribute("wrongEmail", "The provided email is wrong!");
        }
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(ModelMap model) {

        model.addAttribute("message", "Welcome to register page!");
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Encrypt plain password with MD5 hash
        user.setPassword(MD5.encrypt(user.getPassword()));

        final UserDAO userDAO = new UserDAO();
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("emailRegistered", "This email is already registered");
            return "auth/register";
        } else {
            if (userDAO.addUser(user) != null) {
                model.addAttribute("updates", "Successfully registered!");
            } else {
                model.addAttribute("updates", "Something went wrong!");
                return "auth/register";
            }
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/dashboard");
        return "user/dashboard";
    }
}
