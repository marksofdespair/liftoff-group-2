package org.teamlaika.laikaspetpark.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;

@Controller
@RequestMapping("users")
public class ProviderController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("owner", providerRepository.findAll());
        return "users/index";
    }

    @GetMapping("add")
    public String displayNewOwnerForm(Model model) {
        model.addAttribute(new User());
        return "users/newaccount";
    }

    @PostMapping("add")
    String submitNewOwnerForm(@ModelAttribute @Valid Provider newProvider, Error errors, Model model) {
        //if(errors.hasErrors()){
        //return "users/newaccount";
        //}

        providerRepository.save(newProvider);
        return "redirect:";
    }
}
