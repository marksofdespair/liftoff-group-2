package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotNull
    @Size(min = 3, max = 100, message = "Please make sure the service provided is between 3 and 100 characters.")
    private String serviceProvided;
    @NotNull
    @Size(min = 3, max = 100, message = "Please make sure the location is between 3 and 100 characters.")
    private String location;
    @NotNull
    @Size(min = 3, max = 50, message = "Please input your date and time as a string between 3 and 50 characters.")
    private String dateAndTime;
//    private DateTimeFormat dateTime;

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

    public String getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
    //    public DateTimeFormat getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(DateTimeFormat dateTime) {
//        this.dateTime = dateTime;
//    }

    public int getId() {
        return id;
    }
}
