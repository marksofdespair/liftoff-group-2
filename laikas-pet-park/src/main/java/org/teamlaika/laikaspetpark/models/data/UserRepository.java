package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.teamlaika.laikaspetpark.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}

