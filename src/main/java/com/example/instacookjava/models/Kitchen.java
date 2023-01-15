package com.example.instacookjava.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kitchen {
    public Kitchen() {
    }

    public Kitchen(String kitchenName, String kitchenDescription, String region) {
        this.kitchenName = kitchenName;
        this.kitchenDescription = kitchenDescription;
        this.region = region;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kitchenId;

    private String kitchenName;

    private String kitchenDescription;

    private String region;

    @OneToMany(mappedBy = "kitchen")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "kitchen")
    private List<Contest> contests = new ArrayList<>();

    public String getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public String getKitchenDescription() {
        return kitchenDescription;
    }

    public void setKitchenDescription(String kitchenDescription) {
        this.kitchenDescription = kitchenDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public int getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(int kitchenId) {
        this.kitchenId = kitchenId;
    }
}
