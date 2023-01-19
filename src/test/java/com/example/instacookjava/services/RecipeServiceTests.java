package com.example.instacookjava.services;
import java.sql.Date;
import java.util.*;
import static org.mockito.Mockito.verify;

import com.example.instacookjava.models.*;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.repositories.CollectionRepository;
import com.example.instacookjava.repositories.KitchenRepository;
import com.example.instacookjava.repositories.RecipeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTests {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CollectionRepository collectionRepository;

    @Mock
    private KitchenRepository kitchenRepository;

    @Test
    @DisplayName("Get all recipes happy flow")
    void getAllRecipes() {
        //arrange
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        Recipe recipe2 = new Recipe("Briose", "cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe1);
        recipeList.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(recipeList);

        //check
        List<Recipe> result = recipeService.getAllRecipes();

        //assert
        assertEquals(recipeList.size(), result.size());
    }

    @Test
    void getRecipeByIdHappyFlow() {
        //arrange
        int recipeId = 1;
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe1));

        //check
        Recipe result = recipeService.getRecipeById(recipeId);

        //assert
        assertEquals(recipe1.getRecipeId(), result.getRecipeId());
    }

    @Test
    void getRecipeByIdSadFlow() {
        //arrange
        String expected = "Could not find a collection with this id";
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);

        //check
        RuntimeException exceptionResult = assertThrows(RuntimeException.class, () -> recipeService.createRecipe(recipe1, -1));

        //assert
        assertEquals(expected, exceptionResult.getMessage());
    }

    @Test
    void createRecipe() {
        //arrange
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        when(recipeRepository.save(recipe1)).thenReturn(recipe1);

        int collectionId = 1;
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        when(collectionRepository.findById(collectionId)).thenReturn(Optional.of(col1));

        //check
        Recipe result = recipeService.createRecipe(recipe1, collectionId);

        //assert
        assertEquals(recipe1.getRecipeTitle(), result.getRecipeTitle());

    }

    @Test
    void createRecipeInKitchen() {
        //arrange
        int collectionId = 1;
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        when(collectionRepository.findById(collectionId)).thenReturn(Optional.of(col1));

        int kitchenId = 1;
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        when(kitchenRepository.findById(kitchenId)).thenReturn(Optional.of(kitchen));

        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        when(recipeRepository.save(recipe1)).thenReturn(recipe1);


        //check
        Recipe result = recipeService.createRecipeInKitchen(recipe1, collectionId, kitchenId);

        //assert
        assertEquals(recipe1.getCollection(), result.getCollection());
        assertEquals(recipe1.getKitchen(), result.getKitchen());
    }

    @Test
    void updateRecipe() {
        //arrange
        int recipeId = 1;
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        when(recipeRepository.save(recipe1)).thenReturn(recipe1);

        //check
        Recipe result = recipeService.updateRecipe(recipeId, recipe1);

        //assert
        assertEquals(result.getRecipeId(), recipe1.getRecipeId());

    }

    @Test
    void deleteRecipe() {
        //check
        recipeService.deleteRecipe(1);
        //assert
        verify(recipeRepository).deleteById(1);
    }

    @Test
    void getAllCommentsForRecipe() {
        int recipeId = 1;
        Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        recipe1.setComments(comments);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe1));

        //check
        List<Comment> commentsResult = recipeService.getAllCommentsForRecipe(recipeId);

        //assert
        assertEquals(recipe1.getComments(), commentsResult);
    }
}