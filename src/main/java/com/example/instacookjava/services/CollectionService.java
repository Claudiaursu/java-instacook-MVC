package com.example.instacookjava.services;

import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.CollectionRepository;
import com.example.instacookjava.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CollectionService {

    private CollectionRepository collectionRepository;
    private UserRepository userRepository;

    public CollectionService(CollectionRepository collectionRepository, UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public Collection getCollectionById(Integer id) {
        return collectionRepository.findById(id).orElse(null);
    }

    public Collection createCollection(Collection collection, int userId) {
        User owner = userRepository.findById(userId).orElseThrow(() ->
            new RuntimeException(("User with this id not found"))
        );
        collection.setUser(owner);
        return collectionRepository.save(collection);
    }

    public Collection updateCollection(Integer id, Collection collection) {
        collection.setCollectionId(id);
        return collectionRepository.save(collection);
    }

    public Collection save(Collection collection) {
        Collection savedCollection = collectionRepository.save(collection);
        return savedCollection;
    }

    public void deleteCollection(Integer id) {
        collectionRepository.deleteById(id);
    }
}