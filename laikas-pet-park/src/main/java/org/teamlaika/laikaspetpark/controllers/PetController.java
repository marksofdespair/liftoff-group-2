package org.teamlaika.laikaspetpark.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/pet")
public class PetController {

    private static final List<Pet> pets = new ArrayList<>();
    private final ApiService apiService;

    private final AuthenticationController authenticationController;

    public PetController(ApiService apiService, AuthenticationController authenticationController) {
        this.apiService = apiService;
        this.authenticationController = authenticationController;
    }

    @GetMapping("my-pets")
    public String displayAllPets(Model model) {
        model.addAttribute("pets", pets);
        return "display";
    }

    @GetMapping("breed-info")
    public String displayBreedInfoByBreed(@RequestParam String species, @RequestParam String breed, Model model) {
        if (species.equals("Dog")) {
            List<DogApi> dog = apiService.findDogByBreed(breed);
            model.addAttribute("breed", dog.get(0));
            return "dog-breed-info";
        } else {
            List<CatApi> cat = apiService.findCatByBreed(breed);
            model.addAttribute("breed", cat.get(0));
            return "cat-breed-info";
        }
    }

    @GetMapping("precreate")
    public String displayPreCreatePetForm() {
        return "precreate";
    }

    @PostMapping("precreate")
    public String processPreCreatePetForm(@RequestParam String species) {
        if (species.equals("dog")) {
            return "redirect:create-dog";
        } else {
            return "redirect:create-cat";
        }
    }

    @GetMapping("create-dog")
    public String displayCreateDogForm(Model model) {
        model.addAttribute("breeds", apiService.findAllDogs());
        return "create-dog";
    }

    @PostMapping("create-dog")
    public String processCreateDogForm(@RequestParam String name, String breed, HttpSession session) {
        String species = "Dog";
        User user = authenticationController.getUserFromSession(session);
        Owner owner = (Owner) user;
        pets.add(new Pet(name, species, breed, owner));
        return "redirect:precreate";
    }

    @GetMapping("create-cat")
    public String displayCreateCatForm(Model model) {
        model.addAttribute("breeds", apiService.findAllCats());
        return "create-cat";
    }

    @PostMapping("create-cat")
    public String processCreateCatForm(@RequestParam String name, String breed, HttpSession session) {
        String species = "Cat";
        User user = authenticationController.getUserFromSession(session);
        Owner owner = (Owner) user;
        pets.add(new Pet(name, species, breed, owner));
        return "redirect:precreate";

    }
}
