package com.example.instacookjava.repositories.security;

import com.example.instacookjava.models.User;
import com.example.instacookjava.models.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByRole(String role);

}