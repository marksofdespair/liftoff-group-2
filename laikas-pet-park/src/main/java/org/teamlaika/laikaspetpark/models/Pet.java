package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
@Entity
public class Pet extends AbstractEntity {

    @ManyToOne
    private Owner owner;
    @OneToOne
    private PetInfo petInfo;
    @NotNull
    private String species;
    @NotNull
    private String breed;
//    @Size(max = 1000, message = "Description is too long. Leave description under 1000 characters.")
//    private String description;


    public Pet(){
        super();
    }
    public Pet(String name, String species, String breed, Owner owner){
        //this();
        this.setName(name);
        this.species = species;
        this.breed = breed;
        this.owner = owner;
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


//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

     @Override
     public String toString() {
         return "Pet{" +
                 "id=" + getId() +
                 ", name='" + getName() + '\'' +
                 ", owner='" + owner.getName() + '\'' +
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
