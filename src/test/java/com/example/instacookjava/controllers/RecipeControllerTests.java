//package com.example.instacookjava.controllers;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import java.util.Arrays;
//import java.util.List;
//import com.example.instacookjava.models.Recipe;
//import com.example.instacookjava.services.RecipeService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.event.annotation.BeforeTestExecution;
//
//@ExtendWith(MockitoExtension.class)
//public class RecipeControllerTests {
//    @Mock
//    private RecipeService recipeService;
//
//    @InjectMocks
//    private RecipeController recipeController;
//
//    @BeforeTestExecution
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetAllRecipes() {
//        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe());
//        when(recipeService.getAllRecipes()).thenReturn(expectedRecipes);
//
//        List<Recipe> actualRecipes = recipeController.getAllRecipes();
//
//        assertEquals(expectedRecipes, actualRecipes);
//        verify(recipeService, times(1)).getAllRecipes();
//    }
//
//    @Test
//    public void testCreateRecipe() {
//        Recipe expectedRecipe = new Recipe();
//        when(recipeService.createRecipe(expectedRecipe, 1)).thenReturn(expectedRecipe);
//
//        ResponseEntity<Recipe> actualResponse = recipeController.createRecipe(expectedRecipe, 1);
//        Recipe actualRecipe = actualResponse.getBody();
//
//        assertEquals(expectedRecipe, actualRecipe);
//        verify(recipeService, times(1)).createRecipe(expectedRecipe, 1);
//    }
//
//    @Test
//    public void testCreateRecipeInKitchen() {
//        Recipe expectedRecipe = new Recipe();
//        when(recipeService.createRecipeInKitchen(expectedRecipe, 1, 2)).thenReturn(expectedRecipe);
//
//        ResponseEntity<Recipe> actualResponse = recipeController.createRecipeInKitchen(expectedRecipe, 1, 2);
//        Recipe actualRecipe = actualResponse.getBody();
//
//        assertEquals(expectedRecipe, actualRecipe);
//        verify(recipeService, times(1)).createRecipeInKitchen(expectedRecipe, 1, 2);
//    }
//
//    @Test
//    public void testGetRecipeById() {
//        Recipe expectedRecipe = new Recipe();
//        when(recipeService.getRecipeById(1)).thenReturn(expectedRecipe);
//
//        Recipe actualRecipe = recipeController.getRecipeById(1);
//
//        assertEquals(expectedRecipe, actualRecipe);
//        verify(recipeService, times(1)).getRecipeById(1);
//    }
//
//    @Test
//    public void testUpdateRecipe() {
//        Recipe expectedRecipe = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
//        when(recipeService.updateRecipe(1, expectedRecipe)).thenReturn(expectedRecipe);
//
//        ResponseEntity<Recipe> actualResponse = recipeController.updateRecipe(1, expectedRecipe);
//        assertEquals(expectedRecipe.getRecipeId(), actualResponse.getBody().getRecipeId());
//    }
//}
