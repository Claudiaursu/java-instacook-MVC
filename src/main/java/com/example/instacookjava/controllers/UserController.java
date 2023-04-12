package com.example.instacookjava.controllers;
import java.util.List;

import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.models.User;
import com.example.instacookjava.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user){
        return ResponseEntity.ok().body(userService.saveNewUser(user));
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute User user,
                               BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return "login/register";
        }

        User saveUser = userService.saveNewUser(user);
        return "redirect:/kitchens" ;
    }
    @GetMapping("/register")
    public String showRegisterForm(){ return "login/register"; }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }

    @PostMapping("/{userId}/reactions")
    public ResponseEntity<User> addRecipeReaction(@PathVariable(value = "userId") Integer userId, @RequestParam Integer recipeId) {
        return ResponseEntity.ok().body(userService.addReaction(userId, recipeId));
    }
}
