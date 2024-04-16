package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Provider {

    @Id
    @GeneratedValue
    private int Id;

    @OneToOne(mappedBy = "provider")
    private User user;
    //public String username;
    @OneToMany
    @JoinColumn(name = "provider_id")
    private final List<Service> services = new ArrayList<>();

    private boolean isGroomer;

    private boolean isSitter;
    private boolean isWalker;
    private boolean isTrainer;
    public Provider(){
        super();
    }

    public List<Service> getServices() {return services;}

    public int getId() {
        return Id;
    }
    public boolean isGroomer() {
        return isGroomer;
    }

    public void setGroomer(boolean groomer) {
        isGroomer = groomer;
    }

    public boolean isSitter() {
        return isSitter;
    }

    public void setSitter(boolean sitter) {
        isSitter = sitter;
    }

    public boolean isWalker() {
        return isWalker;
    }

    public void setWalker(boolean walker) {
        isWalker = walker;
    }

    public boolean isTrainer() {
        return isTrainer;
    }

    public void setTrainer(boolean trainer) {
        isTrainer = trainer;
    }


}
