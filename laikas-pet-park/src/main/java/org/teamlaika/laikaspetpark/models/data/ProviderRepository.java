package org.teamlaika.laikaspetpark.models.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.Provider;

import java.util.Optional;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer>, JpaSpecificationExecutor<Provider> {
    Optional<Provider> findByUsername(String username);

}
