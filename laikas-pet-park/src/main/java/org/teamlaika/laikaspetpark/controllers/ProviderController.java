package org.teamlaika.laikaspetpark.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderSpecification;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("owner", providerRepository.findAll());
        return "providers/index";
    }

    @GetMapping("index/{providerId}")
    public String listProvider(@PathVariable int providerId, Model model) {
        model.addAttribute("provider", providerRepository.findById(providerId));
//        model.addAttribute("services", provider.getServices());
        return "providers/display";
    }

    @GetMapping("delete/{providerId}")
    public String displayDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                           Model model) {
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getProvider() != null){
            Provider provider = (Provider) currentUser.getProvider();
            model.addAttribute("provider", provider);
            return "providers/delete";
        }
//        Optional<Provider> optProvider = providerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optProvider.isPresent()) {
//            Provider provider = (Provider) optProvider.get();
//            model.addAttribute("provider", provider);
//            return "providers/delete";
         else {
            return "redirect:";
        }
    }

    @PostMapping("delete/{providerId}")
    public String postDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Provider provider,
                                        Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "providers/delete/{providerId}";
        }
        if (passwordInput.equals(password)){
            providerRepository.delete(provider);
            return"/";
        }
        return"redirect:";
    }
    @GetMapping("update/{providerId}")
    String displayUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                             Model model){
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getProvider() != null) {
            Provider provider = (Provider) currentUser.getProvider();
            model.addAttribute("provider", provider);
            return "providers/update";

//        Optional<Provider> optProvider = providerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optProvider.isPresent()) {
//            Provider provider = (Provider) optProvider.get();
//            model.addAttribute("provider", provider);
//            return "providers/update";
        } else {
            return "redirect:";
        }
    }
    @PostMapping("update/{providerId}")
    String submitUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Provider provider,
                            Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "providers/update";
        }
        if (passwordInput.equals(password)){
            providerRepository.save(provider);
            return "providers";
        }
        return"redirect:";
    }

    @GetMapping("search")
    public String displayProviderSearchForm() { return "providers/search";}

    @PostMapping("search")
    public String processProviderSearchForm(@RequestParam(required = false) String isGroomer,
                                            @RequestParam(required = false) String isSitter,
                                            @RequestParam(required = false) String isTrainer,
                                            @RequestParam(required = false) String isWalker,
                                            Model model) {

        List<Provider> providers = providerRepository.findAll(
                Specification.where(ProviderSpecification.hasSkills(isGroomer,isSitter,isWalker,isTrainer))
        );

        if (providers.isEmpty()) {
            model.addAttribute("providers", providerRepository.findAll());
        } else {
            model.addAttribute("providers",providers);
        }

        return "redirect:search";
    }

}
