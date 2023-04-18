package com.example.instacookjava.controllers;
import java.util.*;

import com.example.instacookjava.exceptionHandling.ResourceNotFoundException;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller()
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("")
    public ModelAndView recipes(){
        ModelAndView modelAndView = new ModelAndView("recipes/recipeList");
        List<Recipe> recipes = recipeService.getAllRecipes();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model){

        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe",
                recipe);
        return "recipes/recipeDetails";
    }
    @GetMapping("/new")
    public String showNewRecipeForm(){ return "recipes/recipeAddForm"; }

    @RequestMapping("/edit/{recipeId}")
    public String edit(@PathVariable("recipeId") Integer recipeId, Model model) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        model.addAttribute("recipe", recipe);
        return "recipes/recipeEditForm";
    }

    @PostMapping("/{id}/edit")
    public String processRecipeForm(@PathVariable Integer id, @Valid @ModelAttribute Recipe recipe,
                                    BindingResult bindingResult) {
        recipeService.updateRecipe(id, recipe);
        return "redirect:/recipes/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute Recipe recipe,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "recipeEditForm";
        }

        Recipe savedRecipe = recipeService.save(recipe);
        return "redirect:/recipes" ;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handlerNotFoundException(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("exception",exception);
        modelAndView.setViewName("fragments/notFound");
        return modelAndView;
    }

}