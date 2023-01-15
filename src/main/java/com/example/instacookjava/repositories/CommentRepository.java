package com.example.instacookjava.repositories;

import com.example.instacookjava.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
