package com.example.instacookjava.services;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    public UserService(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public User saveNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Integer id){
       userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User user){
        user.setUserId(id);
        return userRepository.save(user);
    }

    public User addReaction(Integer userId, Integer recipeId){
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("Could not fin user with id " + userId));

        Recipe recipe = recipeRepository
                .findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Could not find recipe with id " + recipeId));
        List<Recipe> recipeList = new ArrayList<>(Arrays.asList(recipe));
        List<User> userList = new ArrayList<>(Arrays.asList(user));

        user.setRecipeReactions(recipeList);
        userRepository.save(user);

        recipe.setReactions(userList);
        recipeRepository.save(recipe);

        return user;
    }
}
