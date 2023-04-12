package com.example.instacookjava.services;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.models.security.Authority;
import com.example.instacookjava.repositories.*;
import com.example.instacookjava.repositories.security.AuthorityRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, RecipeRepository recipeRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.authorityRepository = authorityRepository;
    }

    public User saveNewUser(User user){
        Authority guestRole = authorityRepository.findByRole("ROLE_GUEST");

        user.setTotalPoints(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNotExpired(true);
        user.setEnabled(true);
        user.setAccountNotLocked(true);
        user.setCredentialsNotExpired(true);
        user.setAuthorities( new HashSet<>(Arrays.asList(guestRole)));

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
