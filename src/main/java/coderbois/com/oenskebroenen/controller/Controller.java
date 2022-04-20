package coderbois.com.oenskebroenen.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Controller {




    @GetMapping("/index")
    public String index(){

        return "index";
    }
}
