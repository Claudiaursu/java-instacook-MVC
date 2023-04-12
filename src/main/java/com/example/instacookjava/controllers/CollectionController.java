package com.example.instacookjava.controllers;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller()
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model){

        Collection collection = collectionService.getCollectionById(id);
        model.addAttribute("collection",
                collection);
        return "collections/collectionDetails";
    }
    @GetMapping("/new")
    public String showNewCollectionForm(){ return "collections/collectionAddForm"; }

    @RequestMapping("")
    public ModelAndView collections(){
        ModelAndView modelAndView = new ModelAndView("collections/collectionList");
        List<Collection> collections = collectionService.getAllCollections();
        modelAndView.addObject("collections", collections);
        return modelAndView;
    }

    @RequestMapping("/edit/{collectionId}")
    public String edit(@PathVariable("collectionId") Integer collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        model.addAttribute("collection", collection);
        return "collections/collectionEditForm";
    }
    @PostMapping("/{id}/edit")
    public String processRecipeForm(@PathVariable Integer id, @Valid @ModelAttribute Collection collection,
                                    BindingResult bindingResult) {
        collectionService.updateCollection(id, collection);
        return "redirect:/collections/";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute Collection collection,
                               BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return "collections/collectionEditForm";
        }

        Collection savedCollection = collectionService.save(collection);
        return "redirect:/collections" ;
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        collectionService.deleteCollection(id);
        return "redirect:/collections";
    }

}