package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
import coderbois.com.oenskebroenen.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model){
        WishService myWishService = new WishService();

        model.addAttribute("user", new User());

        return "index";
    }

    @PostMapping("/")
    public String indexPost(@ModelAttribute("user") User user, Model model){
        WishService myWishService = new WishService();

        System.out.println(user.getUsername() +" "+ user.getPassword());

        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage () {
        return "homepage";
    }
}

