package com.example.instacookjava.controllers;
import java.util.*;

import com.example.instacookjava.models.Comment;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe,  @RequestParam int collectionId) {
        return ResponseEntity.ok().body(recipeService.createRecipe(recipe, collectionId));
    }

    @PostMapping("/kitchen")
    public ResponseEntity<Recipe> createRecipeInKitchen(@RequestBody Recipe recipe,  @RequestParam int collectionId,  @RequestParam int kitchenId) {
        return ResponseEntity.ok().body(recipeService.createRecipeInKitchen(recipe, collectionId, kitchenId));
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        return recipeService.getRecipeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok().body(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("id") Integer id) {
        recipeService.deleteRecipe(id);
    }

    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable("recipeId") Integer recipeId) {
        return ResponseEntity.ok().body(recipeService.getAllCommentsForRecipe(recipeId));
    }
}