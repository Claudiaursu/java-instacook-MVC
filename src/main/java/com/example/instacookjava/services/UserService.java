package com.example.instacookjava.services;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
