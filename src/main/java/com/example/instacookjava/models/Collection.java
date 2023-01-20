package com.example.instacookjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Collection {

    public Collection(){
    }

    public Collection(String collectionTitle, String collectionDescription, boolean isPublic, String photoPath) {
        this.collectionTitle = collectionTitle;
        this.collectionDescription = collectionDescription;
        this.isPublic = isPublic;
        this.photoPath = photoPath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collectionId;
    @NotNull(message="Collection title cannot be empty")
    @NotEmpty(message="Collection title cannot be empty")
    private String collectionTitle;
    private String collectionDescription;
    private boolean isPublic;
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection")
    @JsonIgnore
    private List<Recipe> recipes = new ArrayList<>();

    public String getCollectionTitle() {
        return collectionTitle;
    }

    public void setCollectionTitle(String collectionTitle) {
        this.collectionTitle = collectionTitle;
    }

    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
}
