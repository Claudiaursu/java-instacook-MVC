package com.example.instacookjava.services;
import java.util.*;

import com.example.instacookjava.models.Kitchen;
import com.example.instacookjava.repositories.KitchenRepository;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    private KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public List<Kitchen> getAllKitchens() {
        return kitchenRepository.findAll();
    }

    public Kitchen getKitchenById(Integer id) {
        return kitchenRepository.findById(id).orElse(null);
    }

    public Kitchen createKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public Kitchen updateKitchen(Integer id, Kitchen kitchen) {
        kitchen.setKitchenId(id);
        return kitchenRepository.save(kitchen);
    }

    public void deleteKitchen(Integer id) {
        kitchenRepository.deleteById(id);
    }
}
