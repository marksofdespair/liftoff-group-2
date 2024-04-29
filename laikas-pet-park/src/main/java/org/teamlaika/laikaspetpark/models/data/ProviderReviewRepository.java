package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.ProviderReview;

import java.util.List;

@Repository
public interface ProviderReviewRepository extends JpaRepository<ProviderReview, Long> {
    List<ProviderReview> findByProviderId(Long providerId);
}