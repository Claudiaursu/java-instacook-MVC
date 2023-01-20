package com.example.instacookjava.controllers;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.services.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public ResponseEntity<List<Collection>> getAllCollections() {
        return ResponseEntity.ok().body(collectionService.getAllCollections());
    }

    @PostMapping
    public ResponseEntity<Collection> createCollection(@RequestBody @Valid Collection collection, @RequestParam int userId) {
        return ResponseEntity.ok().body(collectionService.createCollection(collection, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(collectionService.getCollectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collection> updateCollection(@PathVariable("id") Integer id, @RequestBody Collection collection) {
        return ResponseEntity.ok().body(collectionService.updateCollection(id, collection));
    }

    @DeleteMapping("/{id}")
    public void deleteCollection(@PathVariable("id") Integer id) {
        collectionService.deleteCollection(id);
    }
}