package com.example.instacookjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    public User(){
    }

    public User(String firstName, String lastName, String email, String country, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.totalPoints = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotNull(message="First Name cannot be empty")
    @NotEmpty(message="First Name cannot be empty")
    private String firstName;
    @NotNull(message="Last Name cannot be empty")
    @NotEmpty(message="Last Name cannot be empty")
    private String lastName;
    @NotNull(message="Email cannot be empty")
    @NotEmpty(message="Email cannot be empty")
    @Email(message="You should enter a valid email")
    private String email;
    private String country;
    private String phoneNumber;
    private Integer totalPoints;

    @OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Collection> collections = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "reactions")
    @JsonIgnore
    private List<Recipe> recipeReactions = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Recipe> getRecipeReactions() {
        return recipeReactions;
    }

    public void setRecipeReactions(List<Recipe> recipeReactions) {
        this.recipeReactions = recipeReactions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
