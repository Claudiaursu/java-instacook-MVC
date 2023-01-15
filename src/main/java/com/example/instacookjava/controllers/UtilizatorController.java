package com.example.instacookjava.controllers;

import com.example.instacookjava.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilizator")
public class UtilizatorController {

    private final UserService utilizatorService;

    public UtilizatorController(UserService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }
}
