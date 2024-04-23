package org.teamlaika.laikaspetpark.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.teamlaika.laikaspetpark.models.Owner;

import java.util.Optional;

@Controller
public class Home {
    @GetMapping("/index")
    public String WelcomePage(Model model) {


        model.addAttribute("login", "Login");
        model.addAttribute("register", "Register");

        return "/index";
    }
}