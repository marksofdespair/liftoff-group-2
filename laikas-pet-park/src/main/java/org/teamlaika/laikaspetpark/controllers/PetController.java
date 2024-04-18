package org.teamlaika.laikaspetpark.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/pets")
public class PetController {

    //private static List<Pet> pets = new ArrayList<>();
    @Autowired
    private PetRepository petRepository;

    private final ApiService apiService;

    public PetController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public String displayAllPets(Model model, Owner owner){
        model.addAttribute("pets", owner.getPets());
        return"/";
    }
//    @GetMapping
//    public String displayAllPets(Model model, @PathVariable int petId) {
//        model.addAttribute("pets", petRepository.findAll());
//        return "display";
//    }

    @GetMapping("precreate")
    public String displayPreCreatePetForm() {
        return "precreate";
    }

    @PostMapping("precreate")
    public String processPreCreatePetForm(@RequestParam String species) {
        if (species.equals("dog")) {
            return "redirect:create-dog";
        } else if (species.equals("cat")){
            return "redirect:create-cat";
        }{
            return "redirect:";
        }
    }

    @GetMapping("create-dog")
    public String displayCreateDogForm(Model model) {
        model.addAttribute("breeds", apiService.findAllDogs());
        return "create-dog";
    }

    @PostMapping("create-dog")
    public String processCreateDogForm(@RequestParam Pet pet) {
        String species = "Dog";
        petRepository.save(pet);

        return "redirect:precreate";
    }

    @GetMapping("create-cat")
    public String displayCreateCatForm(Model model) {
        model.addAttribute("breeds", apiService.findAllCats());
        return "create-cat";
    }

    @PostMapping("create-cat")
    public String processCreateCatForm(@RequestParam Pet pet) {
        String species = "Cat";
        petRepository.save(pet);
        return "redirect:precreate";

    }
    @GetMapping("update/{petId}")
    public String displayUpdatePetForm(Model model, @RequestParam int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        if (optPet.isPresent()) {
            Pet pet = (Pet) optPet.get();
            model.addAttribute("pet", pet);
            return "edit";
        } else {
            return "redirect:../pets";
        }
    }
    @PostMapping("update/{petId}")
    String submitUpdateForm(@RequestParam Pet pet){
        petRepository.save(pet);
        return "redirect:../pets";
    }
    @GetMapping("delete/{petId}")
    public String displayDeletePetorm(Model model, @PathVariable int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        if (optPet.isPresent()) {
            Pet pet = (Pet) optPet.get();
            model.addAttribute("pet", pet);
            return "pets/delete";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("delete/{petId}")
    public String postDeletePetForm(@ModelAttribute @Valid Pet pet,
                                        Errors errors, Model model){
        if (errors.hasErrors()) {
            return "delete/{petId}";
        }
        petRepository.delete(pet);
        return "redirect:../pets";
    }
}
