package com.example.instacookjava.bootstrap;

import com.example.instacookjava.models.User;
import com.example.instacookjava.models.security.Authority;
import com.example.instacookjava.repositories.UserRepository;
import com.example.instacookjava.repositories.security.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
//@Component
@Profile("mysql")
public class DataLoader {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public void loadUserData() {
       // if (userRepository.count() == 0){
            Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority guestRole = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());

            User admin = User.builder()
                    .email("ursu.claudia99@yahoo.com")
                    .password(passwordEncoder.encode("12345"))
                    .authority(adminRole)
                    .build();

            User guest = User.builder()
                    .email("test123@yahoo.com")
                    .password(passwordEncoder.encode("12345"))
                    .authority(guestRole)
                    .build();

            userRepository.save(admin);
            userRepository.save(guest);
        System.out.println("a intrat aici");
        //}
    }


//    @Override
//    public void run(String... args) throws Exception {
//        loadUserData();
//    }
}
