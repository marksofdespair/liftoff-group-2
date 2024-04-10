package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.Provider;

import java.util.Optional;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {
    Optional<Provider> findByUsername(String username);
}
