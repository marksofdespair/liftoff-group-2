package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
//import org.springframework.format.annotation.DateTimeFormat;

import java.util.Objects;

@Entity
public class Service {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String title;
    @ManyToOne
    private Provider provider;
    @OneToOne
    private Pet pet;
    private String location;
    //private DateTimeFormat dateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //public DateTimeFormat getDateTime() {
    //    return dateTime;
    //}

    //public void setDateTime(DateTimeFormat dateTime) {
    //    this.dateTime = dateTime;
    //}

    public int getId() {
        return id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, getTitle(), getProvider(), getPet(), getLocation());
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", provider=" + provider +
                ", pet=" + pet +
                ", location='" + location + '\'' +
                '}';
    }
}
