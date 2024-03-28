package org.teamlaika.laikaspetpark.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamlaika.laikaspetpark.models.DogApi;

import java.util.List;

@RestController
@RequestMapping("api/dog-breeds")
public class DogAPIController {

    private final ApiService apiService;

    public DogAPIController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("")
    public List<DogApi> findAllDogs() {
        return apiService.findAllDogs();
    }

}