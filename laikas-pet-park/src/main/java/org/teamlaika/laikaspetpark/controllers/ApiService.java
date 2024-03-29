package org.teamlaika.laikaspetpark.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.teamlaika.laikaspetpark.models.CatApi;
import org.teamlaika.laikaspetpark.models.DogApi;

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
}
