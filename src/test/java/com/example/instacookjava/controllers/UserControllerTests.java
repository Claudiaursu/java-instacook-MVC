package com.example.instacookjava.controllers;
import com.example.instacookjava.models.User;
import com.example.instacookjava.services.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User testUser;
    private List<User> testUsers;

    @BeforeTestExecution
    public void setUp() {
        testUser = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240");
        testUsers = Arrays.asList(testUser);
    }

    @Test
    public void testGetAllUsers() {
        //arrange
        when(userService.getAllUsers()).thenReturn(testUsers);

        //check
        List<User> response = userController.getAllUsers();

        //assert
        assertEquals(testUsers, response);
    }

    @Test
    public void testDeleteUser() {
        userController.deleteUser(1);
    }

    @Test
    public void testSaveUser() {
        //arrange
        User user = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240");
        when(userService.saveNewUser(user)).thenReturn(user);

        //check
        ResponseEntity<User> response = userController.saveUser(user);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void deleteUser() {
        userController.deleteUser(1);
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void addRecipeReaction() {
        //arrange
        User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240");
        when(userService.addReaction(1, 1)).thenReturn(u1);

        //check
        ResponseEntity<User> response = userController.addRecipeReaction(1,1);

        //assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(u1, response.getBody());
        verify(userService, times(1)).addReaction(1, 1);
    }

}
