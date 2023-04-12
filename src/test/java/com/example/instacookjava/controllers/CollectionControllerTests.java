//package com.example.instacookjava.controllers;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import com.example.instacookjava.models.Collection;
//import com.example.instacookjava.services.CollectionService;
//
//@ExtendWith(MockitoExtension.class)
//public class CollectionControllerTests {
//
//    @Mock
//    private CollectionService collectionService;
//
//    @InjectMocks
//    private CollectionController collectionController;
//
//    @Test
//    public void testGetAllCollections() {
//        //arrange
//        Collection collection1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
//        Collection collection2 = new Collection("Fast foods in my style", "A few fast burgers and shaorma ideas", false, "");
//
//        List<Collection> expectedCollections = Arrays.asList(collection1, collection2);
//        when(collectionService.getAllCollections()).thenReturn(expectedCollections);
//
//        //check
//        ResponseEntity<List<Collection>> actualResponse = collectionController.getAllCollections();
//        List<Collection> actualCollections = actualResponse.getBody();
//
//        //assert
//        assertEquals(expectedCollections, actualCollections);
//        verify(collectionService, times(1)).getAllCollections();
//    }
//
//    @Test
//    public void testCreateCollection() {
//        //arrange
//        Collection expectedCollection = new Collection("My Deserts", "This is how I do my deserts", false, "");
//        when(collectionService.createCollection(expectedCollection, 1)).thenReturn(expectedCollection);
//
//        //check
//        ResponseEntity<Collection> actualResponse = collectionController.createCollection(expectedCollection, 1);
//        Collection actualCollection = actualResponse.getBody();
//
//        //assert
//        assertEquals(expectedCollection, actualCollection);
//        verify(collectionService, times(1)).createCollection(expectedCollection, 1);
//    }
//
//    @Test
//    public void testGetCollectionById() {
//        //arrange
//        Collection expectedCollection = new Collection("My Deserts", "This is how I do my deserts", false, "");
//        when(collectionService.getCollectionById(1)).thenReturn(expectedCollection);
//
//        //check
//        ResponseEntity<Collection> actualResponse = collectionController.getCollectionById(1);
//        Collection actualCollection = actualResponse.getBody();
//
//        //assert
//        assertEquals(expectedCollection, actualCollection);
//        verify(collectionService, times(1)).getCollectionById(1);
//    }
//
//    @Test
//    public void testUpdateCollection() {
//        //arrange
//        Collection expectedCollection = new Collection("My Deserts", "This is how I do my deserts", false, "");
//        when(collectionService.updateCollection(1, expectedCollection)).thenReturn(expectedCollection);
//
//        //check
//        ResponseEntity<Collection> actualResponse = collectionController.updateCollection(1, expectedCollection);
//        Collection actualCollection = actualResponse.getBody();
//
//        //assert
//        assertEquals(expectedCollection, actualCollection);
//        verify(collectionService, times(1)).updateCollection(1, expectedCollection);
//    }
//
//    @Test
//    public void testDeleteCollection() {
//        collectionController.deleteCollection(1);
//        verify(collectionService, times(1)).deleteCollection(1);
//    }
//
//}
