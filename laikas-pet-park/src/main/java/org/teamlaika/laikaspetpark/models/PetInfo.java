package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PetInfo {
    @Id
    @GeneratedValue
    private int Id;
    @OneToOne
    private Pet pet;
    private String nickName;
    private String description;
    private String conditions;


}
