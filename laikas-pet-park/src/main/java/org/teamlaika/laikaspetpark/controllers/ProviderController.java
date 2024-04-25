package org.teamlaika.laikaspetpark.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.teamlaika.laikaspetpark.models.ZipApi;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderSpecification;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/api/providers")
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    private final ApiService apiService;

    public ProviderController(ApiService apiService) {
        this.apiService = apiService;
    }

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
                                            @RequestParam(required = false) Integer location,
                                            @RequestParam(required = false) Integer radius,
                                            Model model) throws JsonProcessingException {

        List<Provider> matchingProviders = new ArrayList<>();

        List<ZipApi> nearbyZips = new ApiService().findZipcodesWithinRadiusZipcode(location,radius);

        Map<Integer, Float> nearbyZipsMap = new HashMap<>();

        for (ZipApi nearbyZip : nearbyZips) {
            nearbyZipsMap.put(nearbyZip.zipcode(),nearbyZip.distance());
        }

        for (ZipApi nearbyZip : nearbyZips) {

            Integer aZipcode = nearbyZip.zipcode();

            List<Provider> someProviders = providerRepository.findAll(
                    Specification.where(ProviderSpecification.providerFilter(isGroomer, isSitter, isWalker, isTrainer, aZipcode))
            );

            if (!someProviders.isEmpty()) {
                matchingProviders.addAll(someProviders);
            }
        }

        if (matchingProviders.isEmpty()) {
            model.addAttribute("providers", providerRepository.findAll());
        } else {
            model.addAttribute("providers",matchingProviders);
            model.addAttribute("locations",nearbyZipsMap);
        }

        return "providers/search";
    }

//    @GetMapping("search2")
//    public String displayProviderSearchForm2() {return "providers/search2";}
//
//    @PostMapping("search2")
//    public String processProviderSearchForm2(@RequestParam Integer location,
//                                             @RequestParam Integer radius,
//                                             Model model) throws JsonProcessingException {
//
//        model.addAttribute("locations", apiService.findZipcodesWithinRadiusZipcode(location, radius));
//
//        return "providers/search2";
//    }


}
