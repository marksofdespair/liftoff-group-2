package org.teamlaika.laikaspetpark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.data.OwnerRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SearchController {
//    @Autowired
//    private ProviderRepository providerRepository;
//
//    @GetMapping("")
//    public String displayProviders (@RequestParam boolean isGroomer,
//                                    @RequestParam boolean isTrainer,
//                                    Model model) {
//
//        if (!isGroomer && !isTrainer) {
//            model.addAttribute("errorMessage", "Please select at least one skill.");
//            return "redirect:";
//        } else if (isGroomer && isTrainer) {
//            Optional<Provider> result = providerRepository.findById(1);
//
//
//        }
//    }
}
