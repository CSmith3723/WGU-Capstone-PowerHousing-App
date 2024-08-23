package cs.capstone.powerhousing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    // --- HOME PAGE --- //

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String redirectHome(){
        return "redirect:/home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }


}
