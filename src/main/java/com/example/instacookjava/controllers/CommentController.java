package com.example.instacookjava.controllers;
import java.util.*;
import com.example.instacookjava.models.Comment;
import com.example.instacookjava.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody @Valid Comment comment, @RequestParam int userId, @RequestParam int recipeId) {
        return ResponseEntity.ok().body(commentService.createComment(comment, userId, recipeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(commentService.getCommentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Integer id, @RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentService.updateComment(id, comment));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteComment(id);
    }
}