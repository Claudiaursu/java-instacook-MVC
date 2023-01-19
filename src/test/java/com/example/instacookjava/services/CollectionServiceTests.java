package com.example.instacookjava.services;
import java.sql.Date;
import java.util.*;
import static org.mockito.Mockito.verify;

import com.example.instacookjava.models.*;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.repositories.CollectionRepository;
import com.example.instacookjava.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionServiceTests {

    @InjectMocks
    private CollectionService collectionService;

    @Mock
    private CollectionRepository collectionRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    @DisplayName("Get all collections happy flow")
    void getAllCollections() {
        //arrange
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        Collection col2 = new Collection("Fast foods in my style", "A few fast burgers and shaorma ideas", false, "");

        List<Collection> collList = new ArrayList<>();
        collList.add(col1);
        collList.add(col2);

        when(collectionRepository.findAll()).thenReturn(collList);

        //check
        List<Collection> result = collectionService.getAllCollections();

        //assert
        assertEquals(collList.size(), result.size());
    }

    @Test
    void getCollectionByIdHappyFlow() {
        //arrange
        int colId = 1;
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        when(collectionRepository.findById(colId)).thenReturn(Optional.of(col1));

        //check
        Collection result = collectionService.getCollectionById(colId);

        //assert
        assertEquals(col1.getCollectionId(), result.getCollectionId());
    }

    @Test
    void createCollection() {
        //arrange
        User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240");
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        when(collectionRepository.save(col1)).thenReturn(col1);

        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(u1));

        //check
        Collection result = collectionService.createCollection(col1, userId);

        //assert
        assertEquals(col1.getCollectionTitle(), result.getCollectionTitle());

    }


    @Test
    void updateCollection() {
        //arrange
        int collId = 1;
        Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
        when(collectionRepository.save(col1)).thenReturn(col1);

        //check
        Collection result = collectionService.updateCollection(collId, col1);

        //assert
        assertEquals(result.getCollectionId(), col1.getCollectionId());

    }

    @Test
    void deleteRecipe() {
        //check
        collectionService.deleteCollection(1);
        //assert
        verify(collectionRepository).deleteById(1);
    }

}