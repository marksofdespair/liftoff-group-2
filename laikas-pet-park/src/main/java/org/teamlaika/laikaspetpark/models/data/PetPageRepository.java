package org.teamlaika.laikaspetpark.models.data;

import org.springframework.data.repository.CrudRepository;
import org.teamlaika.laikaspetpark.models.PetInfo;

public interface PetPageRepository extends CrudRepository <PetInfo, Integer>{

}
