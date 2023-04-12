package com.example.instacookjava.services;
import java.sql.Date;
import java.util.*;
import static org.mockito.Mockito.verify;

import com.example.instacookjava.models.*;
import com.example.instacookjava.repositories.CommentRepository;
import com.example.instacookjava.repositories.RecipeRepository;
import com.example.instacookjava.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTests {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    @DisplayName("Get all comments happy flow")
    void getAllComments() {
        //arrange
        Comment comment1 = new Comment("What a good recipe", new Date(2022, 2, 3));
        Comment comment2 = new Comment("What a good desert", new Date(2022, 2, 3));

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);

        when(commentRepository.findAll()).thenReturn(commentList);

        //check
        List<Comment> result = commentService.getAllComments();

        //assert
        assertEquals(commentList.size(), result.size());
    }

    @Test
    void getCommentByIdHappyFlow() {
        //arrange
        int commentId = 1;
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        //check
        Comment result = commentService.getCommentById(commentId);

        //assert
        assertEquals(comment.getCommentId(), result.getCommentId());
    }

    @Test
    void createComment() {
        //arrange
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentRepository.save(comment)).thenReturn(comment);

        int userId = 1;
        User userId1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "parola", "Romania", "0737526240");
        int recipeId = 2;
        Recipe recipeId2 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userId1));
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeId2));

        //check
        Comment result = commentService.createComment(comment, userId, recipeId);

        //assert
        assertEquals(comment.getRecipe(), result.getRecipe());

    }

    @Test
    void updateComment() {
        //arrange
        int commentId = 1;
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentRepository.save(comment)).thenReturn(comment);

        //check
        Comment result = commentService.updateComment(commentId, comment);

        //assert
        assertEquals(result.getCommentId(), comment.getCommentId());

    }

    @Test
    void deleteRecipe() {
        //check
        commentService.deleteComment(1);
        //assert
        verify(commentRepository).deleteById(1);
    }

}