package com.example.instacookjava.services;

import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.RecipeRepository;
import com.example.instacookjava.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RecipeRepository recipeRepository;

    @Test
    @DisplayName("Get all users happy flow")
    void getAllUsers() {
        //arrange
        User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "parola", "Romania", "0737526240");
        User u2 = new User("Popescu", "Ana", "popescu.ana@yahoo.com", "parola", "Romania", "0737a26241");

        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);

        when(userRepository.findAll()).thenReturn(userList);

        //check
        List<User> result = userService.getAllUsers();

        //assert
        assertEquals(userList.size(), result.size());
    }

    @Test
    @DisplayName("Saving user in happy flow")
    void saveNewUserHappyFlow(){
        //arrange
        User user = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "parola", "Romania", "0737526240");
        when(userRepository.save(user)).thenReturn(user);

        //check
        User result = userService.saveNewUser(user);

        //assert
        assertEquals(user.getFirstName(), result.getFirstName());
    }

    @Test
    @DisplayName("Add user reaction happy flow")
    void saveReactionFromUser(){
        //arrange
        int userId = 1;
        int recipeId = 2;

        User userId1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "parola", "Romania", "0737526240");
        Recipe recipeId2 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userId1));
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeId2));

        when(userRepository.save(userId1)).thenReturn(userId1);
        when(recipeRepository.save(recipeId2)).thenReturn(recipeId2);

        //act
        User result = userService.addReaction(userId, recipeId);

        //check
        assertEquals(userId1.getRecipeReactions(), result.getRecipeReactions());
    }

    @Test
    void updateUser() {
        //arrange
        int userId = 1;
        User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "parola", "Romania", "0737526240");
        when(userRepository.save(u1)).thenReturn(u1);

        //check
        User result = userService.updateUser(userId, u1);

        //assert
        assertEquals(result.getUserId(), u1.getUserId());

    }

    @Test
    void deleteUser() {
        //check
        userService.deleteUser(1);
        //assert
        verify(userRepository).deleteById(1);
    }
}
