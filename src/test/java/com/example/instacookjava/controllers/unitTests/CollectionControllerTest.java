package com.example.instacookjava.controllers.unitTests;

import com.example.instacookjava.controllers.CollectionController;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.services.CollectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CollectionControllerTest {

    @Mock
    Model model;

    @Mock
    CollectionService collectionService;

    CollectionController collectionController;

    @Captor
    ArgumentCaptor<Collection> argumentCaptor;

    @BeforeEach
    public void setUp() throws Exception {
        collectionController = new CollectionController(collectionService);
    }

    @Test
    public void showById() {
        Integer id = 1;
        Collection collectionTest = new Collection();
        collectionTest.setCollectionId(id);

        when(collectionService.getCollectionById(id)).thenReturn(collectionTest);

        String viewName = collectionController.getById(id, model);
        assertEquals("collections/collectionDetails", viewName);
        verify(collectionService, times(1)).getCollectionById(id);

        verify(model, times(1))
                .addAttribute(eq("collection"), argumentCaptor.capture() );

        Collection collectionArg = argumentCaptor.getValue();
        assertEquals(collectionArg.getCollectionId(), collectionTest.getCollectionId() );

    }

    @Test
    public void showNewCollectionForm() {
        // Act
        String viewName = collectionController.showNewCollectionForm();

        // Assert
        assertEquals("collections/collectionAddForm", viewName);
    }

    @Test
    public void getCollectionsList() {
        // Arrange
        List<Collection> collectionsTest = new ArrayList<>();
        Collection collection1 = new Collection();
        Collection collection2 = new Collection();
        collectionsTest.add(collection1);
        collectionsTest.add(collection2);

        when(collectionService.getAllCollections()).thenReturn(collectionsTest);

        // Act
        ModelAndView modelAndView = collectionController.collections();

        // Assert
        assertEquals("collections/collectionList", modelAndView.getViewName());
        List<Collection> collectionsArg = (List<Collection>) modelAndView.getModel().get("collections");
        assertEquals(collectionsArg.size(), collectionsTest.size());
    }

    @Test
    public void testCollectionEditForm() {
        // Arrange
        Integer collectionId = 1;
        Collection collectionTest = new Collection();
        collectionTest.setCollectionId(collectionId);

        when(collectionService.getCollectionById(collectionId)).thenReturn(collectionTest);

        // Act
        String viewName = collectionController.edit(collectionId, model);

        // Assert
        assertEquals("collections/collectionEditForm", viewName);
        verify(collectionService, times(1)).getCollectionById(collectionId);
        verify(model, times(1)).addAttribute(eq("collection"), argumentCaptor.capture());
        Collection collectionArg = argumentCaptor.getValue();
        assertEquals(collectionArg.getCollectionId(), collectionTest.getCollectionId());
    }

}
