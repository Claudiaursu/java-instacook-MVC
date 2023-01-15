package com.example.instacookjava.repositories;

import com.example.instacookjava.models.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Integer> {
}
