package org.teamlaika.laikaspetpark.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.PetInfo;
import org.teamlaika.laikaspetpark.models.data.PetPageRepository;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:5173")
@Controller
@RequestMapping("/pets")
public class PetController {

    //private static List<Pet> pets = new ArrayList<>();
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetPageRepository petPageRepository;

    private final ApiService apiService;

    public PetController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public String displayAllPets(Model model, Owner owner){
        model.addAttribute("pets", owner.getPets());
        model.addAttribute("title", "Pets");
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
        model.addAttribute("species", "Dog");
        model.addAttribute("title", "Add Your Dog");
        return "create-dog";
    }

    @PostMapping("create-dog")
    public String processCreateDogForm(@RequestParam Pet pet) {
        //String species = "Dog";
        petRepository.save(pet);

        return "redirect:precreate";
    }

    @GetMapping("create-cat")
    public String displayCreateCatForm(Model model) {
        model.addAttribute("breeds", apiService.findAllCats());
        model.addAttribute("species", "Cat");
        model.addAttribute("title", "Add Your Cat");
        return "create-cat";
    }

    @PostMapping("create-cat")
    public String processCreateCatForm(@RequestParam Pet pet) {
        //String species = "Cat";
        petRepository.save(pet);
        return "redirect:precreate";

    }
    @GetMapping("update/{petId}")
    public String displayUpdatePetForm(Model model, @RequestParam int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        if (optPet.isPresent()) {
            Pet pet = (Pet) optPet.get();
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Update Your Pet");
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
            model.addAttribute("title", "Remove Pet");
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
        } else{
            PetInfo petInfo = pet.getPetInfo();
            Optional<PetInfo> optInfo = petPageRepository.findById(petInfo.getId());
            if(optInfo.isPresent()){
                petPageRepository.deleteById(petInfo.getId());
            }
            petRepository.delete(pet);
            return "redirect:../pets";
        }
    }

    @GetMapping("add-pet-profile/{petId}")
    public String displayPetProfileForm(Model model, @PathVariable int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        if (optPet.isPresent()) {
            Pet pet = (Pet) optPet.get();
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Add Pet Profile");
            return "/pets/add-pet-profile";
        } else {
            return "redirect:";
        }
    }
    @PostMapping("add-pet-profile/{petId}")
    public String postAddPetProfileForm(@ModelAttribute @Valid Pet pet, @Valid PetInfo petInfo,
                                        Errors errors, Model model){
        if(errors.hasErrors()){
            return "add-pet-profile/{petId}";
        } else{
            petPageRepository.save(petInfo);
            //petRepository.save(pet);
            return "redirect:";
        }
    }

    @GetMapping("update-pet-profile/{petId}")
    public String getUpdatePetProfileForm(Model model, @PathVariable int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        if(optPet.isPresent()){
            Pet pet = (Pet) optPet.get();
            PetInfo petInfo = pet.getPetInfo();
            Optional<PetInfo> optInfo = petPageRepository.findById(petInfo.getId());
            if(optInfo.isPresent()){
                model.addAttribute("pet", pet);
                model.addAttribute("petInfo", petInfo);
                model.addAttribute("title", "Update Pet Profile");
                return "pets/update-pet-profile";
            }
            return "pets/add-pet-profile";
        } else{
            return "redirect:";
        }
    }
    @PostMapping("update-pet-profile/{petId}")
    public String postUpdatePetProfileForm(@ModelAttribute @Valid Pet pet, @Valid PetInfo petInfo,
                                           Errors errors, Model model){
     if(errors.hasErrors()){
         return "update-pet-info/{petId}";
     }   else{
         petPageRepository.save(petInfo);
         //petRepository.save(pet);
         return "redirect:";
     }
    }
}
