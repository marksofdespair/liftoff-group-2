package org.teamlaika.laikaspetpark.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.ZipApi;

import java.util.List;

@RestController
@RequestMapping("/api/zipcodes")
public class ZipApiController {

    private final ApiService apiService;

    public ZipApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/by-radius/")
    public List<ZipApi> findZipCodesWithinRadiusZipCode(@RequestParam int zipCode,
                                                        @RequestParam int radius) throws JsonProcessingException {
        return apiService.findZipCodesWithinRadiusZipCode(zipCode,radius);
    }
}
