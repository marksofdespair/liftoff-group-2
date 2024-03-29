package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.Provider;
@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {
}
