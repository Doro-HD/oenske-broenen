package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.service.UserService;
import coderbois.com.oenskebroenen.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final UserService userService;
    private final WishlistService wishlistService;

    @Autowired
    public MainController(UserService userService, WishlistService wishlistService) {
        this.userService = userService;
        this.wishlistService = wishlistService;
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
            htmlPageName = "login";
            model.addAttribute("user", new User());
        }

        return htmlPageName;
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        User myUser = userService.findUserByUsername(user.getUsername());
        if (myUser != null) {
            if (myUser.getPassword().equals(user.getPassword())) {
                Cookie cookieUsername = new Cookie("username", user.getUsername());
                Cookie cookieUserId = new Cookie("id", String.valueOf(myUser.getId()));

                httpSession.setAttribute("username", cookieUsername);
                httpSession.setAttribute("id", cookieUserId);
            }
        }

        return "redirect:homepage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        Cookie cookie = (Cookie) httpSession.getAttribute("username");
        if (cookie != null) {
            httpSession.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/homepage")
    public String homepage (HttpSession httpSession, Model model) {
        String htmlPageName;
        User user;

        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "homepage";
            user = this.userService.findUserByUsername(cookie.getValue());

            model.addAttribute("wishlists", this.wishlistService.getUserWishlists(user.getId()));
        } else {
            htmlPageName = "redirect:/";
        }

        return htmlPageName;
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
    public String createUser(Model model, HttpSession httpSession){
        model.addAttribute("user", new User());
        String htmlPageName;

        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "homepage";
        } else {
            htmlPageName = "createUser";
        }

        return htmlPageName;
    }

    @PostMapping("/createUser")
    public String userCreated(@ModelAttribute("user") User user){
        //gemmer ny user

        if (userService.findUserByUsername(user.getUsername()) == null) {
            this.userService.createUser(user);
        }

        //retur til login skærmen
        return "redirect:login";
    }


    @GetMapping("/test")
    @ResponseBody
    public Cookie findUser (HttpSession httpSession) {
        Cookie cookie = (Cookie) httpSession.getAttribute("id");
        return cookie;
    }




}

