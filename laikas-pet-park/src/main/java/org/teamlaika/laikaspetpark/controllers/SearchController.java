package org.teamlaika.laikaspetpark.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
//    @GetMapping
//    public String displayProviderSearchForm() {
//
//    }
//
////    @PostMapping("search")
//    public String searchProvidersBySkills(@RequestParam String isGroomer,
//                                          @RequestParam String isSitter,
//                                          @RequestParam String isTrainer,
//                                          @RequestParam String isWalker,
//                                          Model model) {
//
//    }

}
