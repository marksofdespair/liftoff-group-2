package org.teamlaika.laikaspetpark.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model){
        return "users/index";
    }
    @GetMapping("display/{ownerId}")
    public String displayOwner(Model model, Owner owner, @PathVariable int ownerId){
//
        Optional<Owner> result = ownerRepository.findById(ownerId);
        if(result.isPresent()){
            Owner aOwner = result.get();
            model.addAttribute("owner", aOwner);
            model.addAttribute("pets", owner.getPets());
            return "users/display";
        }
        else{
            return "redirect:";
        }
    }
    @GetMapping("display")
    public String list(Model model, Owner owner, @RequestParam int ownerId){
        model.addAttribute("owner", ownerRepository.findById(ownerId));
        model.addAttribute("pets", owner.getPets());
        model.addAttribute("title", "Your Pets");
        return "display";
    }
    @GetMapping("delete/{ownerId}")
    public String displayDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                           Model model){
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getOwner() != null) {
            Owner owner = (Owner) currentUser.getOwner();
            model.addAttribute("owner", owner);
            model.addAttribute("title", "Delete Account?");
            return "users/delete";
//        Optional<Owner> optOwner = ownerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optOwner.isPresent()) {
//            Owner owner = (Owner) optOwner.get();
//            model.addAttribute("owner", owner);
//            return "users/delete";
        } else {
            return "redirect:";
        }
    }
    @PostMapping("delete/{ownerId}")
    public String postDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Owner owner,
                                        Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "delete/{ownerId}";
        }
        if (passwordInput.equals(password)){
            for(Pet pet : owner.getPets()){
                petRepository.deleteById(pet.getId());
            }
            ownerRepository.delete(owner);
            return"/";
        }
        return"redirect:";
    }
    @GetMapping("update")
    String displayUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                            Model model){
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getOwner() != null) {
            Owner owner = (Owner) currentUser.getOwner();
            model.addAttribute("owner", owner);
            model.addAttribute("title", "Update Owner Account");
            return "users/update";

//        Optional<Owner> optOwner = ownerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optOwner.isPresent()) {
//            Owner owner = (Owner) optOwner.get();
//            model.addAttribute("owner", owner);
//            return "users/update";
        } else {
            return "redirect:";
        }
    }
    @PostMapping("update")
    String submitUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Owner owner,
                            Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "delete/{ownerId}";
        }
        if (passwordInput.equals(password)){
            ownerRepository.save(owner);
            return "/";
        }
        return"redirect:";
    }
    }
