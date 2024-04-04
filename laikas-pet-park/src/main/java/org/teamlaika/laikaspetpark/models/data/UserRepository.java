package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.teamlaika.laikaspetpark.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}

