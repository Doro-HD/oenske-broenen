package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
import coderbois.com.oenskebroenen.service.WishService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String index(Model model, HttpServletRequest httpServletRequest){
        String htmlPageName;

        HttpSession httpSession = httpServletRequest.getSession();
        Cookie cookie = (Cookie) httpSession.getAttribute("username");

        if (cookie != null) {
            htmlPageName = "redirect:homepage";
        } else {
            htmlPageName = "index";
            model.addAttribute("user", new User());
        }

        WishService myWishService = new WishService();


        return htmlPageName;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        Cookie cookie = new Cookie("username", user.getUsername());
        httpSession.setAttribute("username", cookie);

        WishService myWishService = new WishService();

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


    @GetMapping("/find-user")
    @ResponseBody
    public User findUser () {
        UserRepository myRep = new UserRepository();

        return myRep.findUser("Joey Mo");
    }

}

