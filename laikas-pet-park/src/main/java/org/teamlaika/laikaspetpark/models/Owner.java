package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends User {
    @OneToMany
    @JoinColumn(name = "owner_id")
    private final List<Pet> pets = new ArrayList<>();
    public Owner(){
        super();
    }

    public List<Pet> getPet() {
        return pets;
    }


}
