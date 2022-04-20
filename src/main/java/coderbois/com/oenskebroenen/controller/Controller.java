package coderbois.com.oenskebroenen.controller;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.LoadUsers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {




    @GetMapping("/index")
    public ArrayList<User> index(){
        LoadUsers myUsers = new LoadUsers();
        return myUsers.getAllUsers();
    }
}
