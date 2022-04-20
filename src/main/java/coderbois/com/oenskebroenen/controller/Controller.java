package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.LoadUsers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.stereotype.Controller;
import java.util.ArrayList;

@Controller
public class Controller {




    @GetMapping("/")
    public String index(Model model){
        LoadUsers myUsers = new LoadUsers();
        return "index";
    }
}
