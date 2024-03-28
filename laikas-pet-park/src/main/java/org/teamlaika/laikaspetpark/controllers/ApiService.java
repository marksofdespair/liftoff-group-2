package org.teamlaika.laikaspetpark.controllers;

import aj.org.objectweb.asm.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;
import org.teamlaika.laikaspetpark.models.CatApi;
import org.teamlaika.laikaspetpark.models.DogApi;

import java.io.InputStream;
import java.util.List;

@Service
public class ApiService {

    private final RestClient dogRestClient;

    private final RestClient catRestClient;

    public ApiService() {
        dogRestClient = RestClient.builder()
                .baseUrl("https://api.thedogapi.com")
                .build();

        catRestClient = RestClient.builder()
                .baseUrl("https://api.thecatapi.com")
                .build();
    }

    public List<DogApi> findAllDogs() {
        return dogRestClient.get()
                .uri("/v1/breeds")
                .retrieve()
                .body(new ParameterizedTypeReference<List<DogApi>>() {});
    }

    public List<CatApi> findAllCats() {
        return catRestClient.get()
                .uri("/v1/breeds")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CatApi>>() {});
    }

    public DogApi findDogById(int id) {
        return dogRestClient.get()
                .uri("/v1/breeds/{id}", id)
                .retrieve()
                .body(DogApi.class);
    }

    public CatApi findCatById(String id) {
        return catRestClient.get()
                .uri("/v1/breeds/{id}", id)
                .retrieve()
                .body(CatApi.class);
    }

    public List<DogApi> findDogByBreed(String breed) {
        return dogRestClient.get()
                .uri("/v1/breeds/search?q={breed}", breed)
                .retrieve()
                .body(new ParameterizedTypeReference<List<DogApi>>() {});
    }

    public CatApi findCatByBreed(String breed) {
        return catRestClient.get()
                .uri("/v1/breeds/search?q={breed}", breed)
                .retrieve()
                .body(CatApi.class);
    }
}
