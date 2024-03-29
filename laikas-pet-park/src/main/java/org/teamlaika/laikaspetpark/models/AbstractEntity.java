package org.teamlaika.laikaspetpark.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {


    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    //@Size
    private String name;

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractEntity that = (AbstractEntity) object;
        return Id == that.Id && Objects.equals(name, that.name);

    }

    @Override
    public int hashCode() {

        return Objects.hash(Id, name);
    }
}



