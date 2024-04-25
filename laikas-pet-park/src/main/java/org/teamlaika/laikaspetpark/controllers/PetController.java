package org.teamlaika.laikaspetpark.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.*;
import org.teamlaika.laikaspetpark.models.data.PetPageRepository;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PetPageRepository petPageRepository;

    private final ApiService apiService;

    public PetController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public String displayAllPets(Model model, User user){
        model.addAttribute("pets", user.getPets());
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
    public ResponseEntity<List<DogApi>> displayCreateDogForm() {
        return new ResponseEntity<List<DogApi>>(apiService.findAllDogs(), HttpStatus.OK);
    }

    @PostMapping("create-dog")
    public ResponseEntity<Pet> processCreateDogForm(@RequestParam String name,@RequestParam String breed) {
        String species = "Dog";
        Optional<User> user = userRepository.findById(1);
        if(user.isPresent()){
            User user1 = user.get();
            Pet pet = new Pet(name, species, breed, user1);
            return new ResponseEntity<Pet>(petRepository.save(pet), HttpStatus.OK);
        }
        else{
            Pet pet = new Pet();
            return new ResponseEntity<Pet>(pet, HttpStatus.OK);
        }
    }

    @PostMapping("/add-dog")
    public ResponseEntity<String> addDog(@RequestBody String body) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            String name = jsonNode.get("name").asText();
            String breed = jsonNode.get("breed").asText();
            String username = jsonNode.get("username").asText();
            User user = userRepository.findByUsername(username);
            Pet pet = new Pet(name, "Dog",breed, user);
            petRepository.save(pet);
            return ResponseEntity.ok("Dog added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dog. Please try again.");
        }
    }

    @GetMapping("create-cat")
    public ResponseEntity<List<CatApi>> displayCreateCatForm() {
//        model.addAttribute("breeds", apiService.findAllCats());
//        return "create-cat";
        return new ResponseEntity<List<CatApi>>(apiService.findAllCats(), HttpStatus.OK);
    }

    @PostMapping("create-cat")
    public ResponseEntity<String> addCat(@RequestBody String body) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            String name = jsonNode.get("name").asText();
            String breed = jsonNode.get("breed").asText();
            String username = jsonNode.get("username").asText();
            User user = userRepository.findByUsername(username);
            Pet pet = new Pet(name, "Cat",breed, user);
            petRepository.save(pet);
            return ResponseEntity.ok("Cat added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add cat. Please try again.");
        }

    }
    @GetMapping("update/{petId}")
    public ResponseEntity<Optional<Pet>> displayUpdatePetForm(@PathVariable int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        return new ResponseEntity<Optional<Pet>>(optPet, HttpStatus.OK);

    }
    @PostMapping("update/{petId}")
    String submitUpdateForm(@RequestParam Pet pet){
        petRepository.save(pet);
        return "redirect:../pets";
    }
    @GetMapping("delete/{petId}")
    public ResponseEntity<?> displayDeletePetForm(@PathVariable int petId){
        Optional<Pet> optPet = petRepository.findById(petId);
        return new ResponseEntity<Optional<Pet>>(optPet, HttpStatus.OK);
    }
    @DeleteMapping("delete/{petId}")
    public ResponseEntity<Pet> postDeletePetForm(@RequestParam Pet pet){
        petRepository.delete(pet);
        return new ResponseEntity<Pet>(HttpStatus.ACCEPTED);
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