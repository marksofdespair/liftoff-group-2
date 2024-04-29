package org.teamlaika.laikaspetpark.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamlaika.laikaspetpark.models.ProviderReview;
import org.teamlaika.laikaspetpark.models.data.ProviderReviewRepository;

import java.util.List;

@Service
public class ProviderReviewService {
    @Autowired
    private ProviderReviewRepository reviewRepository;

    public List<ProviderReview> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }

    // Other methods for CRUD operations I might add later
}