package com.example.instacookjava.services;
import java.sql.Date;
import java.util.*;
import static org.mockito.Mockito.verify;

import com.example.instacookjava.models.*;
import com.example.instacookjava.repositories.KitchenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenServiceTests {

    @InjectMocks
    private KitchenService kitchenService;

    @Mock
    private KitchenRepository kitchenRepository;

    @Test
    @DisplayName("Get all kitchens happy flow")
    void getAllRecipes() {
        //arrange
        Kitchen kitchen1 = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        Kitchen kitchen2 = new Kitchen("Thailand Kitchen", "Tacos and Tacos", "Thailand");

        List<Kitchen> kitchenList = new ArrayList<>();
        kitchenList.add(kitchen1);
        kitchenList.add(kitchen2);

        when(kitchenRepository.findAll()).thenReturn(kitchenList);

        //check
        List<Kitchen> result = kitchenService.getAllKitchens();

        //assert
        assertEquals(kitchenList.size(), result.size());
    }

    @Test
    void getKitchenByIdHappyFlow() {
        //arrange
        int kitchenId = 1;
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        when(kitchenRepository.findById(kitchenId)).thenReturn(Optional.of(kitchen));

        //check
        Kitchen result = kitchenService.getKitchenById(kitchenId);

        //assert
        assertEquals(kitchen.getKitchenId(), result.getKitchenId());
    }

    @Test
    void createKitchen() {
        //arrange
        int kitchenId = 1;
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        when(kitchenRepository.save(kitchen)).thenReturn(kitchen);

        //check
        Kitchen result = kitchenService.createKitchen(kitchen);

        //assert
        assertEquals(kitchen.getKitchenName(), result.getKitchenName());

    }

    @Test
    void updateKitchen() {
        //arrange
        int kitchenId = 1;
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        when(kitchenRepository.save(kitchen)).thenReturn(kitchen);

        //check
        Kitchen result = kitchenService.updateKitchen(kitchenId, kitchen);

        //assert
        assertEquals(result.getKitchenId(), kitchen.getKitchenId());

    }

    @Test
    void deleteRecipe() {
        //check
        kitchenService.deleteKitchen(1);
        //assert
        verify(kitchenRepository).deleteById(1);
    }

}