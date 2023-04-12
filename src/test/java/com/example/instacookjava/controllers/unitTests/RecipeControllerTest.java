package com.example.instacookjava.controllers.unitTests;

import com.example.instacookjava.controllers.RecipeController;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @Captor
    ArgumentCaptor<Recipe> argumentCaptor;

    @BeforeEach
    public void setUp() throws Exception {
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void showById() {
        Integer id = 1;
        Recipe recipeTest = new Recipe();
        recipeTest.setRecipeId(id);

        when(recipeService.getRecipeById(id)).thenReturn(recipeTest);

        String viewName = recipeController.getById(id, model);
        assertEquals("recipes/recipeDetails", viewName);
        verify(recipeService, times(1)).getRecipeById(id);

        verify(model, times(1))
                .addAttribute(eq("recipe"), argumentCaptor.capture() );

        Recipe recipeArg = argumentCaptor.getValue();
        assertEquals(recipeArg.getRecipeId(), recipeTest.getRecipeId() );
    }

    @Test
    public void testGetRecipeList() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(1);
        recipeList.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(2);
        recipeList.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(recipeList);

        ModelAndView modelAndView = recipeController.recipes();

        assertEquals("recipes/recipeList", modelAndView.getViewName());
        assertEquals(recipeList, modelAndView.getModel().get("recipes"));
        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    public void testShowNewRecipeForm() {
        String viewName = recipeController.showNewRecipeForm();

        assertEquals("recipes/recipeAddForm", viewName);
    }

    @Test
    public void testEditRecipe() {
        Integer id = 1;
        Recipe recipeTest = new Recipe();
        recipeTest.setRecipeId(id);

        when(recipeService.getRecipeById(id)).thenReturn(recipeTest);

        String viewName = recipeController.edit(id, model);
        assertEquals("recipes/recipeEditForm", viewName);
        verify(recipeService, times(1)).getRecipeById(id);

        verify(model, times(1))
                .addAttribute(eq("recipe"), argumentCaptor.capture() );

        Recipe recipeArg = argumentCaptor.getValue();
        assertEquals(recipeArg.getRecipeId(), recipeTest.getRecipeId() );
    }

    @Test
    public void testProcessRecipeForm() {
        Integer id = 1;
        Recipe recipeTest = new Recipe();
        recipeTest.setRecipeId(id);

        String viewName = recipeController.processRecipeForm(id, recipeTest, null);
        assertEquals("redirect:/recipes/", viewName);
        verify(recipeService, times(1)).updateRecipe(id, recipeTest);
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        String viewName = recipeController.deleteById(id);
        assertEquals("redirect:/recipes", viewName);
        verify(recipeService, times(1)).deleteRecipe(id);
    }

//    @Test
//    public void testSaveOrUpdate() {
//        Recipe recipeTest = new Recipe();
//        recipeTest.setRecipeId(1);
//
//        when(recipeService.save(recipeTest)).thenReturn(recipeTest);
//
//        String viewName = recipeController.saveOrUpdate(recipeTest, null);
//
//        assertEquals("redirect:/recipes", viewName);
//        verify(recipeService, times(1)).save(recipeTest);
//    }

}
