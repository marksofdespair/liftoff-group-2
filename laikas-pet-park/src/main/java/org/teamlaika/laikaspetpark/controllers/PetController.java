package org.teamlaika.laikaspetpark.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.PetRepository;

@Controller
@RequestMapping("users/pets")
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @GetMapping("/")
    public String index(Model model){
        return "list";
    }
    @GetMapping("add")
    public String displayNewOwnerForm(Model model){
        model.addAttribute(new User());
        return "new";
    }
    @PostMapping("add")
    String submitNewOwnerForm(@ModelAttribute @Valid Pet newPet, Error errors, Model model){

        petRepository.save(newPet);
        return "redirect:";
    }
}
