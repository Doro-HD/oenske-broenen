package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
import coderbois.com.oenskebroenen.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class MainController {




    @GetMapping("/index")
    public ArrayList<User> index(){
        WishService myWishService = new WishService();

        return myWishService.getAllUsers();
    }
}
