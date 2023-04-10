package com.example.instacookjava.repositories.security;

import com.example.instacookjava.models.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}