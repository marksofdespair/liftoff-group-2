package org.teamlaika.laikaspetpark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.models.ProviderReview;
import org.teamlaika.laikaspetpark.models.dto.ReviewDTO;
import org.teamlaika.laikaspetpark.models.service.ProviderReviewService;

import java.util.List;

// REST Controller to handle incoming requests for managing provider reviews.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/provider-review")
public class ProviderReviewController {
    @Autowired
    private ProviderReviewService reviewService;

    @GetMapping("/provider/{providerId}")
    public List<ProviderReview> getReviewsByProviderId(@PathVariable Long providerId) {
        return reviewService.getReviewsByProviderId(providerId);
    }

    // Other REST endpoints for CRUD  if I need to later :')
}