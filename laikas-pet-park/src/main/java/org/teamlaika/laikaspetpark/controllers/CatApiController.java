package org.teamlaika.laikaspetpark.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamlaika.laikaspetpark.models.CatApi;

import java.util.List;

@CrossOrigin(origins = "https://localhost:5173")
@RestController
@RequestMapping("/api/cat-breeds")
public class CatApiController {

    private final ApiService apiService;

    public CatApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("")
    public List<CatApi> findAllCats() {
        return apiService.findAllCats();
    }
}