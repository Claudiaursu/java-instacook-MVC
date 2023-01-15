package com.example.instacookjava.services;

import com.example.instacookjava.models.Collection;
import com.example.instacookjava.repositories.CollectionRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CollectionService {

    private CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public Collection getCollectionById(Integer id) {
        return collectionRepository.findById(id).orElse(null);
    }

    public Collection createCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public Collection updateCollection(Integer id, Collection collection) {
        collection.setCollectionId(id);
        return collectionRepository.save(collection);
    }

    public void deleteCollection(Integer id) {
        collectionRepository.deleteById(id);
    }
}