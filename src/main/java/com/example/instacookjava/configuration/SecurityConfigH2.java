package com.example.instacookjava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("h2")
public class SecurityConfigH2 extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("claudia_guest")
                .password(passwordEncoder.encode("parola12345"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("claudia_admin")
                .password(passwordEncoder.encode("parola12345"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
//                .authorizeRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/products/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .headers(headers -> headers.frameOptions().sameOrigin())
//                .httpBasic(withDefaults())
//                .build();
//    }


//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//    }


    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll();
    }

}

