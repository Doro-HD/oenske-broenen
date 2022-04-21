package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String loginGet(Model model, HttpSession httpSession){
        String htmlPageName;
        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "redirect:homepage";
        } else {
            htmlPageName = "index";
            model.addAttribute("user", new User());
        }

        UserService myWishService = new UserService();


        return htmlPageName;
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        Cookie cookie = new Cookie("username", user.getUsername());
        httpSession.setAttribute("username", cookie);

        UserService myWishService = new UserService();

        //System.out.println(user.getUsername() +" "+ user.getPassword());

        return "redirect:homepage";
    }

    @GetMapping("/homepage")
    public String homepage (HttpServletRequest httpServletRequest) {
        String htmlPageName;

        HttpSession httpSession = httpServletRequest.getSession();
        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "homepage";
        } else {
            htmlPageName = "redirect:/";
        }

        return htmlPageName;
    }
}

