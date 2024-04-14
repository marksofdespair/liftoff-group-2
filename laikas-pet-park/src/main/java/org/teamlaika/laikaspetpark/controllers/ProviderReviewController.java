package org.teamlaika.laikaspetpark.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.ProviderReviews;
import org.teamlaika.laikaspetpark.models.data.ProviderReviewRepository;

import java.util.List;

// REST Controller to handle incoming requests for managing provider reviews.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/provider-reviews") // Using this as the base path for all endpoints in this controller
public class ProviderReviewController {

    @Autowired
    private ProviderReviewRepository providerReviewRepository;

    @GetMapping // Maps to GET /api/provider-reviews
    public List<ProviderReviews> getAllProviderReviews() {
        return providerReviewRepository.findAll();
    }

    @PostMapping // Maps to POST /api/provider-reviews
    public ProviderReviews addProviderReview(@RequestBody ProviderReviews review) {
        return providerReviewRepository.save(review);
    }
}
