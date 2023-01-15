package com.example.instacookjava.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    public Recipe() {
    }

    public Recipe(String recipeTitle, String ingredients, String instructions, String photoPath, String videoPath, boolean contestRegistered) {
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.photoPath = photoPath;
        this.videoPath = videoPath;
        this.contestRegistered = contestRegistered;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    private String recipeTitle;

    private String ingredients;

    private String instructions;

    private String photoPath;

    private String videoPath;

    private boolean contestRegistered;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="user_recipe_reaction", joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> reactions = new ArrayList<>();


    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public boolean isContestRegistered() {
        return contestRegistered;
    }

    public void setContestRegistered(boolean contestRegistered) {
        this.contestRegistered = contestRegistered;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getReactions() {
        return reactions;
    }

    public void setReactions(List<User> reactions) {
        this.reactions = reactions;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }
}
