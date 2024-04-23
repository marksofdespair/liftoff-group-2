package org.teamlaika.laikaspetpark.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;
import org.teamlaika.laikaspetpark.models.CatApi;
import org.teamlaika.laikaspetpark.models.DogApi;
import org.teamlaika.laikaspetpark.models.ZipApi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ApiService {

    private final RestClient dogRestClient;

    private final RestClient catRestClient;

    private final RestClient zipRestClient;

    private final String zipCodeApiKey = System.getenv("ZIPCODEAPI_KEY");

    public ApiService() {
        dogRestClient = RestClient.builder()
                .baseUrl("https://api.thedogapi.com")
                .build();

        catRestClient = RestClient.builder()
                .baseUrl("https://api.thecatapi.com")
                .build();

        zipRestClient = RestClient.builder()
                .baseUrl("https://www.zipcodeapi.com/rest/")
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

    public List<CatApi> findCatByBreed(String breed) {
        return catRestClient.get()
                .uri("/v1/breeds/search?q={breed}", breed)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CatApi>>() {});
    }

    public List<ZipApi> findZipCodesWithinRadiusZipCode(Integer zipCode, Integer radius) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = zipRestClient.get()
                .uri(zipCodeApiKey + "/radius.json/{zipCode}/{radius}/mile",zipCode, radius)
                .retrieve()
                .body(String.class);

        JsonNode zipsNode = objectMapper.readValue(json, JsonNode.class);

        String jsonZipCodeArray = zipsNode.get("zip_codes").toString();

        List<ZipApi> zipApis = objectMapper.readValue(jsonZipCodeArray, new TypeReference<List<ZipApi>>(){});

        Collections.sort(zipApis);

        return zipApis;
    }
}
