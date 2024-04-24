package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
@Entity
public class Pet {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @ManyToOne
    private Owner owner;
    @OneToOne
    private PetInfo petInfo;
    private String species;
    private String breed;
    private String description;


    public Pet(){

    }
    public Pet(String name, String species, String breed, Owner owner){
        //this();
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.owner = owner;
    }

    public int getId() {
        return id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PetInfo getPetInfo() {
        return petInfo;
    }

    public void setPetInfo(PetInfo petInfo) {
        this.petInfo = petInfo;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     @Override
     public String toString() {
         return "Pet{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", owner='" + owner + '\'' +
                 ", species='" + species + '\'' +
                 ", breed='" + breed + '\''+
                 '}';
     }

//  @Override
//    public String toString() {
//        return name;}
      
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return getId() == pet.getId() && Objects.equals(getName(), pet.getName()) && Objects.equals(getOwner(), pet.getOwner()) && Objects.equals(getSpecies(), pet.getSpecies()) && Objects.equals(getBreed(), pet.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOwner(), getSpecies(), getBreed());

    }
}
