package com.example.instacookjava.controllers;
import java.util.*;

import com.example.instacookjava.models.Kitchen;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.KitchenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller()
@RequestMapping("/kitchens")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @RequestMapping("")
    public ModelAndView recipes(){
        ModelAndView modelAndView = new ModelAndView("kitchens/kitchenList");
        List<Kitchen> kitchens = kitchenService.getAllKitchens();
        modelAndView.addObject("kitchens", kitchens);
        return modelAndView;
    }


//    @GetMapping
//    public ResponseEntity<List<Kitchen>> getAllKitchens() {
//        return ResponseEntity.ok().body(kitchenService.getAllKitchens());
//    }
//
//    @PostMapping
//    public ResponseEntity<Kitchen> createKitchen(@RequestBody @Valid Kitchen kitchen) {
//        return ResponseEntity.ok().body(kitchenService.createKitchen(kitchen));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Kitchen> getKitchenById(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(kitchenService.getKitchenById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Kitchen> updateKitchen(@PathVariable("id") Integer id, @RequestBody Kitchen kitchen) {
//        return ResponseEntity.ok().body(kitchenService.updateKitchen(id, kitchen));
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteKitchen(@PathVariable("id") Integer id) {
//        kitchenService.deleteKitchen(id);
//    }
}