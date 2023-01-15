package com.example.instacookjava.repositories;

import com.example.instacookjava.models.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<Kitchen, Integer> {
}
