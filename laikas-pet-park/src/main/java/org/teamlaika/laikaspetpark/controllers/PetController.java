package org.teamlaika.laikaspetpark.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.*;
import org.teamlaika.laikaspetpark.models.data.PetPageRepository;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/pets")
public class PetController {

    //private static List<Pet> pets = new ArrayList<>();

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

    @GetMapping("")
    public ResponseEntity<List<Pet>> displayAllPets(@RequestHeader("Authorization") String token) {

        Claims claims = JwtGenerator.decodeToken(token);

        String userId = claims.getSubject();

        Optional<User> optUser = userRepository.findById(Integer.parseInt(userId));
        User user = optUser.get();

        List<Pet> pets = user.getPets();

        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("create-dog")
    public ResponseEntity<List<DogApi>> displayCreateDogForm() {
        return new ResponseEntity<List<DogApi>>(apiService.findAllDogs(), HttpStatus.OK);
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

        return new ResponseEntity<List<CatApi>>(apiService.findAllCats(), HttpStatus.OK);
    }

    @PostMapping("/add-cat")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dog. Please try again.");
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