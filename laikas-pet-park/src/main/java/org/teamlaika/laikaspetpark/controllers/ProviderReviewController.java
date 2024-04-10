package org.teamlaika.laikaspetpark.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.ProviderReviews;
import org.teamlaika.laikaspetpark.models.data.ProviderReviewRepository;

import java.util.List;

// REST Controller to handle incoming requests for managing provider reviews.
@RestController
@RequestMapping
public class ProviderReviewController {

    @Autowired
    private ProviderReviewRepository providerReviewRepository

    @GetMapping
    public List<ProviderReviews> getAllProviderReviews() {
        return providerReviewRepository.findAll();
    }

    @PostMapping
    public ProviderReviews addProviderReview(@RequestBody ProviderReview review) {
        return providerReviewRepository.save(review);
    }
}
