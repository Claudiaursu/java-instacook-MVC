package com.example.instacookjava.controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import com.example.instacookjava.models.Kitchen;
import com.example.instacookjava.services.KitchenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class KitchenControllerTests {

    @Mock
    private KitchenService kitchenService;

    @InjectMocks
    private KitchenController kitchenController;

    @Test
    public void testCreateKitchen() {
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        kitchen.setKitchenId(1);
        when(kitchenService.createKitchen(kitchen)).thenReturn(kitchen);

        ResponseEntity<Kitchen> response = kitchenController.createRKitchen(kitchen);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(kitchen, response.getBody());
    }

    @Test
    public void testUpdateKitchen() {
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        kitchen.setKitchenId(1);
        when(kitchenService.updateKitchen(1, kitchen)).thenReturn(kitchen);

        ResponseEntity<Kitchen> response = kitchenController.updateKitchen(1, kitchen);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(kitchen, response.getBody());
    }

    @Test
    void deleteKitchen() {
        kitchenController.deleteKitchen(1);
        verify(kitchenService, times(1)).deleteKitchen(1);
    }

    @Test
    void getAllKitchens() {
        List<Kitchen> kitchens = new ArrayList<>();
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        kitchens.add(kitchen);
        when(kitchenService.getAllKitchens()).thenReturn(kitchens);
        ResponseEntity<List<Kitchen>> response = kitchenController.getAllKitchens();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(kitchens, response.getBody());
        verify(kitchenService, times(1)).getAllKitchens();
    }
}
