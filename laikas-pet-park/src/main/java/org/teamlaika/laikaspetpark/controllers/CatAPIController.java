package org.teamlaika.laikaspetpark.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamlaika.laikaspetpark.models.CatApi;

import java.util.List;

@RestController
@RequestMapping("api/cat-breeds")

public class CatAPIController {
    private final ApiService apiService;

    public CatAPIController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("")
    public List<CatApi> findAllCats() {
        return apiService.findAllCats();

    }
}
