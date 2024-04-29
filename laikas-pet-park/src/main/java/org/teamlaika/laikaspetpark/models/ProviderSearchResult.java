package org.teamlaika.laikaspetpark.models;

public class ProviderSearchResult {
    private String name;
    private Integer zipcode;
    private Float distance;
    private String groomer;
    private String sitter;
    private String walker;
    private String trainer;

    private Integer userId;

    public ProviderSearchResult(String name, Integer zipcode, Float distance, String groomer, String sitter, String walker, String trainer, Integer userId) {
        this.name = name;
        this.zipcode = zipcode;
        this.distance = distance;
        this.groomer = groomer;
        this.sitter = sitter;
        this.walker = walker;
        this.trainer = trainer;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getGroomer() {
        return groomer;
    }

    public void setGroomer(String groomer) {
        this.groomer = groomer;
    }

    public String getSitter() {
        return sitter;
    }

    public void setSitter(String sitter) {
        this.sitter = sitter;
    }

    public String getWalker() {
        return walker;
    }

    public void setWalker(String walker) {
        this.walker = walker;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
