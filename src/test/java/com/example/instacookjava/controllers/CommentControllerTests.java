package com.example.instacookjava.controllers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Date;
import java.util.*;
import com.example.instacookjava.models.Comment;
import com.example.instacookjava.services.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CommentControllerTests {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    void createComment() {
        //arrange
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentService.createComment(comment, 1, 1)).thenReturn(comment);

        //check
        ResponseEntity<Comment> response = commentController.createComment(comment, 1, 1);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).createComment(comment, 1, 1);
    }

    @Test
    void updateComment() {
        //arrange
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentService.updateComment(1, comment)).thenReturn(comment);

        //check
        ResponseEntity<Comment> response = commentController.updateComment(1, comment);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).updateComment(1, comment);
    }

    @Test
    void getAllComments() {
        //arrange
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        when(commentService.getAllComments()).thenReturn(comments);

        //check
        ResponseEntity<List<Comment>> response = commentController.getAllComments();

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(comments, response.getBody());
        verify(commentService, times(1)).getAllComments();
    }

    @Test
    void deleteComment() {
        commentController.deleteComment(1);
        verify(commentService, times(1)).deleteComment(1);
    }

    @Test
    void getCommentById() {
        //arrange
        Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
        when(commentService.getCommentById(1)).thenReturn(comment);

        //check
        ResponseEntity<Comment> response = commentController.getCommentById(1);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(comment, response.getBody());
        verify(commentService, times(1)).getCommentById(1);
    }

}
