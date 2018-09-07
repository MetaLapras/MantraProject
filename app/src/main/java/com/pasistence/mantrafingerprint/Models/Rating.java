package com.pasistence.mantrafingerprint.Models;

public class Rating {
    private String ratingValue, comment;

    public Rating(String ratingValue, String comment) {
        this.ratingValue = ratingValue;
        this.comment = comment;
    }

    public Rating() {
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingValue='" + ratingValue + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
