package org.teamlaika.laikaspetpark.models.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.teamlaika.laikaspetpark.models.ProviderReviews;

// Repository interface for managing ProviderReview entities. Supposed to handle database-related operations
public interface ProviderReviewRepository extends JpaRepository<ProviderReviews, Long> {
}
