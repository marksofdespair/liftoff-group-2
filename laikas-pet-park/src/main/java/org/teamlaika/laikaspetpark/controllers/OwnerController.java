package org.teamlaika.laikaspetpark.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;

import java.util.Optional;

@Controller
@RequestMapping("users")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/")
    public String index(Model model){
        return "users/index";
    }
    @GetMapping("add")
    public String displayNewOwnerForm(Model model){
        model.addAttribute(new User());
        return "users/newaccount";
    }
    @PostMapping("add")
    String submitNewOwnerForm(@ModelAttribute @Valid Owner newOwner, Error errors, Model model){
        //if(errors.hasErrors()){
            //return "users/newaccount";
        //}

        ownerRepository.save(newOwner);
        return "redirect:";
    }
    @GetMapping("newaccount")
    public String displayNewAccountForm(Model model){
        return "newaccount";
    }
    @GetMapping("users/{userId}")
    public String displayViewUser(Model model, @PathVariable int userId) {
        Optional<Owner> optUser = ownerRepository.findById(userId);

        if (optUser.isPresent()){
            Owner owner = (Owner) optUser.get();
            model.addAttribute("owner", owner);
            return "user/list";
        }
            else{
                return "redirect:../users";
            }
        }
    }
