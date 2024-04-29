package org.teamlaika.laikaspetpark.models.dto;

import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.ProviderReviews;

import java.util.ArrayList;
import java.util.List;

public class ProfileFormDTO {

    private String name;
    private String username;
    private String accountType;
    private List<Pet> pets = new ArrayList<>();
    private List<ProviderReviews> reviews = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Pet> getPets() {
        return pets;
    }


    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


}