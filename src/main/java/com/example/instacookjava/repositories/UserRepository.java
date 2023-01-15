package com.example.instacookjava.repositories;

import com.example.instacookjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
