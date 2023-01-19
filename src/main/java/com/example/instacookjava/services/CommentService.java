package com.example.instacookjava.services;
import java.util.*;
import com.example.instacookjava.models.Comment;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.CommentRepository;
import com.example.instacookjava.repositories.RecipeRepository;
import com.example.instacookjava.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    public CommentService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            RecipeRepository recipeRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment, int userId, int recipeId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("Could not find a user with this id" + userId));
        Recipe recipe = recipeRepository
                .findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Could not find a recipe with this id" + recipeId));
        comment.setUser(user);
        comment.setRecipe(recipe);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer id, Comment comment) {
        comment.setCommentId(id);
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}