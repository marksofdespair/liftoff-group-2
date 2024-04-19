package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.Owner;
import org.teamlaika.laikaspetpark.models.User;

import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
    //Optional<Owner> findByUsername(String username);
}
