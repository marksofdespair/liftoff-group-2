package org.teamlaika.laikaspetpark.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamlaika.laikaspetpark.models.DogApi;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dog-breeds")
public class DogApiController {

    private final ApiService apiService;

    public DogApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("")
    public List<DogApi> findAllDogs() {
        return apiService.findAllDogs();
    }

}