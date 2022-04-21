package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final UserService userService;

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

        return htmlPageName;
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        Cookie cookie = new Cookie("username", user.getUsername());
        httpSession.setAttribute("username", cookie);

        return "redirect:homepage";
    }

    @GetMapping("/homepage")
    public String homepage (HttpSession httpSession) {
        String htmlPageName;

        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "homepage";
        } else {
            htmlPageName = "redirect:/";
        }

        return htmlPageName;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        Cookie cookie = (Cookie) httpSession.getAttribute("username");
        if (cookie != null) {
            httpSession.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/homepage/{wishlistId}")
    public String wishList (@PathVariable("wishlistId") int wishlistId, HttpSession httpSession) {
        String htmlPageName;

        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "homepage";
        } else {
            htmlPageName = "redirect:/";
        }

        return htmlPageName;
    }

    @GetMapping("/createUser")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/createUser")
    public String userCreated(@ModelAttribute("user") User user){
        //gemmer ny user
        this.userService.createUser(user);

        //retur til login skærmen
        return "redirect:login";
    }


    @GetMapping("/find-user")
    @ResponseBody
    public User findUser () {
        return this.userService.findUserByUsername("Joey Mo");
    }

}

