package com.example.instacookjava.services;
import java.util.*;

import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.Comment;
import com.example.instacookjava.models.Kitchen;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.repositories.CollectionRepository;
import com.example.instacookjava.repositories.KitchenRepository;
import com.example.instacookjava.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;
    private CollectionRepository collectionRepository;

    private KitchenRepository kitchenRepository;


    public RecipeService(
            RecipeRepository recipeRepository,
            CollectionRepository collectionRepository,
            KitchenRepository kitchenRepository
    ){
        this.recipeRepository = recipeRepository;
        this.collectionRepository = collectionRepository;
        this.kitchenRepository = kitchenRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe createRecipe(Recipe recipe, int collectionId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new RuntimeException("Could not find a collection with this id"));
        recipe.setCollection(collection);
        return recipeRepository.save(recipe);
    }

    public Recipe createRecipeInKitchen(Recipe recipe, int collectionId, int kitchenId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new RuntimeException("Could not find a collection with this id"));
        Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new RuntimeException("Could not find a kitchen with this id"));
        recipe.setCollection(collection);
        recipe.setKitchen(kitchen);
        return recipeRepository.save(recipe);
    }


    public Recipe updateRecipe(Integer id, Recipe recipe) {
        recipe.setRecipeId(id);
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    public Recipe save(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe;
    }

    public List<Comment> getAllCommentsForRecipe(int recipeId){
        Recipe recipe= recipeRepository
                .findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Could not find recipe with id " + recipeId));
        List<Comment> comments = recipe.getComments();
        return comments;
    }
}
