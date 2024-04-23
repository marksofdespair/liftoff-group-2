package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Id;

    @OneToOne(mappedBy = "owner")
    private User user;
//    public String username;

    @OneToMany
    @JoinColumn(name = "owner_id")
    private final List<Pet> pets = new ArrayList<>();
    public Owner(){
        super();
    }

    public List<Pet> getPets() {
        return pets;
    }

    public int getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
