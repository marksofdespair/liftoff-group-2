package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Provider extends User {

    @OneToMany
    @JoinColumn(name = "owner_id")
    private final List<String> skills = new ArrayList<>();
    public Provider(){
        super();
    }

    public List<String> getSkills() {
        return skills;
    }

    //public void setSkills(List<String> skills) {
    //    this.skills = skills;
    //}
}
