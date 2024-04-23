package org.teamlaika.laikaspetpark.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamlaika.laikaspetpark.models.DogApi;

import java.util.List;

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

    @GetMapping("/by-id/{id}")
    public DogApi findDogById(@PathVariable int id) {
        return apiService.findDogById(id);
    }

    @GetMapping("/by-breed/{breed}")
    public List<DogApi> findDogByBreed(@PathVariable String breed) {
        return apiService.findDogByBreed(breed);
    }

}
