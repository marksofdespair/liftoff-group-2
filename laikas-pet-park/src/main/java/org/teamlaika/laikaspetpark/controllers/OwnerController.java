package org.teamlaika.laikaspetpark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/api/users")
public class OwnerController {

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
    @GetMapping("display/{userId}")
    public ResponseEntity<User> displayOwner(@PathVariable int userId){
        Optional<User> result = userRepository.findById(userId);
        //Optional<Owner> result = ownerRepository.findById(ownerId);
        User aUser = result.get();

        //model.addAttribute("owner", aOwner);
        //model.addAttribute("pets", owner.getPets());
        return new ResponseEntity<User>(aUser, HttpStatus.OK);
    }

//    @GetMapping("delete/{ownerId}")
//    public String displayDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
//                                           Model model){
//        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
//        if (currentUser.getOwner() != null) {
//            Owner owner = (Owner) currentUser.getOwner();
//            model.addAttribute("owner", owner);
//            return "users/delete";
////        Optional<Owner> optOwner = ownerRepository.findByUsername(loginFormDTO.getUsername());
////        if (optOwner.isPresent()) {
////            Owner owner = (Owner) optOwner.get();
////            model.addAttribute("owner", owner);
////            return "users/delete";
//        } else {
//            return "redirect:";
//        }
//    }
    // TODO Review this Post method to work with User instead
//    @PostMapping("delete/{ownerId}")
//    public String postDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Owner owner,
//                                        Errors errors, Model model, @RequestParam String passwordInput){
//        String password = loginFormDTO.getPassword();
//        if (errors.hasErrors()) {
//            return "delete/{ownerId}";
//        }
//        if (passwordInput.equals(password)){
//            ownerRepository.delete(owner);
//            return"/";
//        }
//        return"redirect:";
//    }
//    @GetMapping("update")
//    String displayUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
//                             Model model){
//        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
//        if (currentUser.getOwner() != null) {
//            Owner owner = (Owner) currentUser.getOwner();
//            model.addAttribute("owner", owner);
//            return "users/update";
//
////        Optional<Owner> optOwner = ownerRepository.findByUsername(loginFormDTO.getUsername());
////        if (optOwner.isPresent()) {
////            Owner owner = (Owner) optOwner.get();
////            model.addAttribute("owner", owner);
////            return "users/update";
//        } else {
//            return "redirect:";
//        }
//    }
// TODO Rework this POST to use User instead

//    @PostMapping("update")
//    String submitUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Owner owner,
//                            Errors errors, Model model, @RequestParam String passwordInput){
//        String password = loginFormDTO.getPassword();
//        if (errors.hasErrors()) {
//            return "delete/{ownerId}";
//        }
//        if (passwordInput.equals(password)){
//            ownerRepository.save(owner);
//            return "/";
//        }
//        return"redirect:";
//    }
}