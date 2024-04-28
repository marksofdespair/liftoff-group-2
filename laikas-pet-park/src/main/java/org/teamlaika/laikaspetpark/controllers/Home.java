package org.teamlaika.laikaspetpark.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/index")
    public String WelcomePage(Model model) {


        model.addAttribute("login", "Login");
        model.addAttribute("register", "Register");

        return "/index";
    }
}