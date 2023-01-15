package com.example.instacookjava.controllers;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.services.CollectionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<Collection> getAllCollections() {
        return collectionService.getAllCollections();
    }

    @PostMapping
    public Collection createCollection(@RequestBody Collection collection) {
        return collectionService.createCollection(collection);
    }

    @GetMapping("/{id}")
    public Collection getCollectionById(@PathVariable("id") Integer id) {
        return collectionService.getCollectionById(id);
    }

    @PutMapping("/{id}")
    public Collection updateCollection(@PathVariable("id") Integer id, @RequestBody Collection collection) {
        return collectionService.updateCollection(id, collection);
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable("id") Integer id) {
        collectionService.deleteCollection(id);
    }
}