package org.teamlaika.laikaspetpark.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.ZipApi;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/zipcodes")
public class ZipApiController {

    private final ApiService apiService;

    public ZipApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/by-radius/")
    public List<ZipApi> findZipcodesWithinRadiusZipcode(@RequestParam Integer zipcode,
                                                        @RequestParam Integer radius) throws JsonProcessingException {
        return apiService.findZipcodesWithinRadiusZipcode(zipcode,radius);
    }
}
