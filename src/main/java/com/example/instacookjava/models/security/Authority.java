package com.example.instacookjava.models.security;

import com.example.instacookjava.models.User;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer authorityId;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}