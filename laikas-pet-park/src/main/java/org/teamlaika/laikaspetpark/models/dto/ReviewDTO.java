package org.teamlaika.laikaspetpark.models.dto;

public class ReviewDTO {

    private Long id;
    private String comment;
    private int rating;
    private Long userId;
    private Long providerId;

    // Getters
    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProviderId() {
        return providerId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
}