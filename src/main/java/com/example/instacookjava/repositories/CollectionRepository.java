package com.example.instacookjava.repositories;

import com.example.instacookjava.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
